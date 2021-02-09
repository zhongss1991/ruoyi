package com.ruoyi.quartz.task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.stereotype.Component;

import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.quartz.service.impl.SysJobServiceImpl;
import com.ruoyi.system.domain.LiconicJob;
import com.ruoyi.system.domain.LiconicOutputList;
import com.ruoyi.system.domain.LiconicSample;
import com.ruoyi.system.domain.MgTecanSample;
import com.ruoyi.system.service.IMgTecanSampleService;
import com.ruoyi.system.service.IService;
import com.ruoyi.system.utils.CommonUtils;
import com.ruoyi.system.utils.FileUtils;
import com.ruoyi.system.utils.UrlConnectionUtil;
import com.ruoyi.system.utils.XmlUtils;
import com.ruoyi.quartz.task.LiconicEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskUtil {
	//日志
	public static Logger log = LoggerFactory.getLogger(TaskUtil.class);
	
	private String job = "http://192.115.110.11:8080/Scheduler/webresources/scheduler/jobs";
	
	private String exportUrl = "http://192.115.110.11:8080/Scheduler/webresources/xml/export";
	
	private String pick = "http://192.115.110.11:8080/Scheduler/webresources/xml/pick";

	public void getLiconicSample() {
		UrlConnectionUtil url = new UrlConnectionUtil();
		List<LiconicSample> lico = url.getRsult();
		System.out.println("liconic同步量:" + lico.size());
		SpringUtils.getBean(IService.class).addOrModifySample(lico);
	}
	
	public void getTecanSample(){
		String url = "\\\\192.115.110.14\\output\\Sample Tracking\\Bak";
		FileUtils fileUtils = new FileUtils();
		List<MgTecanSample> tecanSamples = fileUtils.getTecanSample(url);
		System.out.println("共 " + tecanSamples.size() + " 条样本信息需要同步");
		SpringUtils.getBean(IMgTecanSampleService.class).insertBatch(tecanSamples);
	}
	
	//监控liconic定时任务
	public void liconicListener(String param)
	{
		System.out.println("执行定时任务");
		UrlConnectionUtil conn = new UrlConnectionUtil();
		XmlUtils xmlUtils = new XmlUtils();
		CommonUtils commonUtils = new CommonUtils();
		//打印运行状态日志
		String statusXml = conn.urlGet(job);
		log.info("运行状态: " + statusXml);
		String status = "";
		boolean normalCode = true;
		boolean resultCode = false;
		List<LiconicSample> liconicSamples = null;
		SysJob job = new SysJob();
		LiconicOutputList liconicOutput = new LiconicOutputList();
		
		LiconicEnum.CREATING.getStep();
		
		//获取当前任务进度
		int step = 0;
		Date insertTime = null;
		LiconicOutputList outputList = new LiconicOutputList();
		outputList.setJobNo(param);
		List<LiconicOutputList> currentLiconicJob = SpringUtils.getBean(IService.class).selectOutputList(outputList);
		for (int i = 0; i < currentLiconicJob.size(); i++) 
		{
			insertTime = currentLiconicJob.get(i).getOutputTime();
			step = currentLiconicJob.get(i).getStep();
		}
		
		List<LiconicJob> liconicJobs = xmlUtils.parseJob(statusXml, param);
		List<LiconicJob> consolidateJobs = xmlUtils.parseJob(statusXml, param + "b");
		LiconicJob latestJob = commonUtils.getLatestJob(liconicJobs);
		
		switch(LiconicEnum.getLiconicEnum(step))
		{
		case CREATING: 
			if(liconicJobs.size() <= 0)
			{
				status = LiconicEnum.CREATING.getName();
				step = LiconicEnum.CREATING.getStep();
			}
			else
			{
				status = LiconicEnum.PICK.getName();
				step = LiconicEnum.PICK.getStep();
			}
			break;
		case PICK:
			status = LiconicEnum.PICK.getName();
			step = LiconicEnum.PICK.getStep();
			if(liconicJobs.size() >= 2)
			{
				//挑管已完成,提交出库任务
				String exportXml = conn.export(param);
				String exportres = conn.urlPost(exportUrl, exportXml);
				Map map = xmlUtils.parsePickTubesResult(exportres);
				if("OK".equals(map.get("status"))) {
					status = LiconicEnum.EXPORT.getName();
					step = LiconicEnum.EXPORT.getStep();
				} else {
					status = (String) map.get("errMsg");
				}
				log.info("出库盒子任务命令：" + exportXml);
				log.info("出库盒子任务返回结果:" + exportres);
			}
			else if (liconicJobs.size() == 1)
			{
				//挑管中
				status = liconicJobs.get(0).getErrinfo() == null || "".equals(liconicJobs.get(0).getErrinfo()) ? 
						LiconicEnum.PICK.getName() : "挑管错误，错误原因：" + liconicJobs.get(0).getErrinfo();
				step = LiconicEnum.PICK.getStep();
			}
			break;
		case EXPORT:
			status = LiconicEnum.EXPORT.getName();
			step = LiconicEnum.EXPORT.getStep();
			for(int i = 0; i < liconicJobs.size(); i++)
			{
				if(!liconicJobs.get(i).getCstat().equals("Done"))
				{
					normalCode = false;
				}
				else if(liconicJobs.get(i).getErrinfo() != null)
				{
					//出库出错
					step = LiconicEnum.EXPORT.getStep();
					status = "出库错误，错误原因 ： " + liconicJobs.get(i).getErrinfo();
				}
			}
			if(normalCode && consolidateJobs.size() == 0)
			{	
				//出库完成，提交整理冰箱任务
				liconicSamples = SpringUtils.getBean(IService.class).selectExportPlate(param);
				String partition = liconicSamples.get(0).getPartitions();
				String consolidateXml = conn.consolidation(param, partition, liconicSamples);
				log.info("整理任务命令：" + consolidateXml);
				String conres = conn.urlPost(pick, consolidateXml);
				Map map = xmlUtils.parsePickTubesResult(conres);
				if("OK".equals(map.get("status"))) {
					status = LiconicEnum.CONSOLIDATING.getName();
					step = LiconicEnum.CONSOLIDATING.getStep();
				} else {
					status = (String) map.get("errMsg");
				}
				log.info("整理任务结果: " + conres);
			}
			break;
		case CONSOLIDATING:
			status = LiconicEnum.CONSOLIDATING.getName();
			step = LiconicEnum.CONSOLIDATING.getStep();
			if(consolidateJobs.size() > 0) 
			{
				status = LiconicEnum.CONSOLIDATED.getName();
				step = LiconicEnum.CONSOLIDATED.getStep();
			}  
			break;
		case CONSOLIDATED:
			status = LiconicEnum.CONSOLIDATED.getName();
			step = LiconicEnum.CONSOLIDATED.getStep();
			if(consolidateJobs.size() <= 0) 
			{
				//已整理完成
				resultCode = true;
			}
			for (int i = 0; i < consolidateJobs.size(); i++) 
			{
				if(consolidateJobs.get(i).getErrinfo() != null)
				{
					status = "整理冰箱出错，错误原因: " + consolidateJobs.get(i).getErrinfo();
//					step = LiconicEnum.CONSOLIDATING.getStep();
				}
			}
			break;
		default:
			status = LiconicEnum.FAILURE.getName();
			step = LiconicEnum.FAILURE.getStep();
			break;
		}
		//计算任务运行时间差，达到8小时或出库任务结束则停止任务
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endTime = df.format(new Date());
		String startTime = df.format(insertTime);
		Long diffHour = commonUtils.dateDiff(startTime, endTime, "yyyy-MM-dd HH:mm:ss", "h");
		if(diffHour >= 8 || resultCode)
		{
			job.setInvokeTarget(param);
			List<SysJob> jobs = SpringUtils.getBean(ISysJobService.class).selectJobList(job);
			for (int i = 0; i < jobs.size(); i++) {
				try {
					SpringUtils.getBean(ISysJobService.class).pauseJob(jobs.get(i));
				} catch (BeansException | SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			status = LiconicEnum.FAILURE.getName();
			step = LiconicEnum.FAILURE.getStep();
		}
		//更新样本状态
		liconicOutput.setStatus(status);
		liconicOutput.setJobNo(param);
		liconicOutput.setStep(step);
		SpringUtils.getBean(IService.class).updateOutputList(liconicOutput);
	}
	
	/**
	 * BIMS直接出库任务
	 */
	public void liconicOutputListener() 
	{
		
	}
}
