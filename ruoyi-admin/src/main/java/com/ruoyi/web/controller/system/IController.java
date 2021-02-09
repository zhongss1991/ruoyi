package com.ruoyi.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.swing.text.html.parser.Entity;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jboss.logging.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.system.domain.LiconicJob;
import com.ruoyi.system.domain.LiconicOutputList;
import com.ruoyi.system.domain.LiconicOutputSample;
import com.ruoyi.system.domain.LiconicSample;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IService;
import com.ruoyi.system.utils.UrlConnectionUtil;
import com.ruoyi.system.utils.XmlUtils;

/**
 *  样本库问题统一处理程序
 * @author user
 *
 */
@Controller
@RequestMapping("/system/sample")
public class IController extends BaseController{

	private static Logger log = Logger.getLogger(IController.class);
	
	private String prefix = "system/sample";
	
	@Value("${url.liconic.Picktubes}")
	private String picktubesUrl;
	
	@Value("${url.liconic.jobs}")
	private String jobs;
	
	@Value("${url.liconic.ip}")
	private String ip;
	
	@Autowired
	private IService service;
	
	@Autowired
	private ISysJobService sysJobService;
	
	@RequiresPermissions("system:sample:view")
	@GetMapping()
	public String sample()
	{
		return prefix + "/sample";
	}
	
	/**
	 * 错误样本编码更新
	 * @return
	 */
	@RequiresPermissions("system:sample:edit")
	@RequestMapping("/edit")
	@ResponseBody
	public AjaxResult edit() {
		
		return toAjax(service.modifyNo());
	}

	@RequiresPermissions("system:liconicsample:view")
	@GetMapping("liconicsample") 
	public String liconicsample() {
		return prefix + "/liconicsample";
	}
	
	/**
	 * 同步liconic样本数据
	 */
//	@RequiresPermissions("system:liconicsample:get")
	@RequestMapping("/get")
	public void get() {
		UrlConnectionUtil url = new UrlConnectionUtil();
		List<LiconicSample> lico = url.getRsult();
		service.addOrModifySample(lico);
	}
	
	/**
	 * liconic出库单
	 * @return
	 */
	@RequiresPermissions("system:liconicOutput:view")
	@RequestMapping("/liconicOutput")
	public String liconicOutput() {
		return prefix + "/liconicOutput";
	}
	
	@RequiresPermissions("system:liconicOutput:list")
	@PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LiconicOutputList outputList) {
		startPage();
		List<LiconicOutputList> list = service.selectOutputList(outputList);
		return getDataTable(list);
	}
	
	/**
	 * 新增liconic出库单
	 * @return
	 */
	@GetMapping("add")
	public String add(ModelMap mmap) {
		
		XmlUtils x = new XmlUtils();
		UrlConnectionUtil con = new UrlConnectionUtil();
		String partitionUrl = "http://192.115.110.11:8080/content/webresources/xml/partitions";
		//0.7
		int type1 = 0;
		//1.0
		int type2 = 0;
		String plateUtl = "";
		String plateOutput = "";
		String partitionOutput = con.urlGet(partitionUrl);
		Map<String, Integer> res = new HashMap<String, Integer>();
		//获取所有分区
		List partitionsList = x.partitions(partitionOutput);
		for (int i = 0; i < partitionsList.size(); i++) {
			plateUtl = "http://192.115.110.11:8080/content/webresources/xml/partitions/" + partitionsList.get(i) + "?tubesless=1";
			plateOutput = con.urlGet(plateUtl);
			Map<String, Integer> plate = x.plates2(plateOutput);
			type1 = type1 + plate.get("type1");
			type2 = type2 + plate.get("type2");
		}
		res.put("type1", type1);
		res.put("type2", type2);
		mmap.put("plateNum", res);
		
		return prefix + "/add";
	}
	
	/**
	 * 新增保存liconic出库单
	 * @param outputList
	 * @return
	 */
	@RequiresPermissions("system:liconicOutputList:add")
	@Log(title = "liconic出库", businessType = BusinessType.INSERT)
	@PostMapping("add")
	@ResponseBody
	public AjaxResult addSave(@Validated LiconicOutputList outputList) {
		
		int result = 0;
		
		UrlConnectionUtil conn = new UrlConnectionUtil();
		XmlUtils xml = new XmlUtils();
		
		//获取登录信息
		SysUser currentUser = ShiroUtils.getSysUser();
		String userName = currentUser.getLoginName();
		
		//获取jobNo
		UUID uuid = UUID.randomUUID();
		String jobNo = uuid.toString();
		
		outputList.setCreateUser(userName);
		outputList.setJobNo(jobNo);
		
		//生成出库单
		result = service.insertLiconicOutputList(outputList);
		
		//获取生成的id
		LiconicOutputList liconicOutputList = service.selectLatestOutputList();
		System.out.println("id: " + liconicOutputList.getId());
		
		//更新样本状态
		service.updateLiconicSample(liconicOutputList);
		
		//获取出库样本管
		List<LiconicSample> liconicSample = service.selectOutputSample(liconicOutputList);
		String picktubesXml = conn.getPickTubeXml(liconicSample, jobNo);
		log.info("挑管: " + picktubesXml);
		//发送post请求
		String picktubesRes = conn.urlPost(picktubesUrl, picktubesXml);
		Map<String, String> res = xml.parsePickTubesResult(picktubesRes);
		log.info("挑管结果:" + picktubesRes);
		String status = res.get("status");
		String errMsg = res.get("errMsg");
		
		//生成定时监控任务
		SysJob sysJob = new SysJob();
		sysJob.setJobName("liconic出库监控");
		sysJob.setJobGroup("DEFAULT");
		sysJob.setInvokeTarget("ryTask.ryParams('" + jobNo + "')");
		//每10s执行一次
		sysJob.setCronExpression("0 */1 * * * ?");
		sysJob.setMisfirePolicy("1");
		sysJob.setConcurrent("1");
		sysJob.setStatus("0");
		try {
			sysJobService.insertJob(sysJob);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		liconicOutputList.setStatus(status + ": " + errMsg);
		
		//更新挑管状态
		service.updateOutputList(liconicOutputList);
		
		return toAjax(result);
		
	}
	
	//重试挑管任务
	@RequiresPermissions("system:liconicOutputList:retry")
	@Log(title = "liconoc出库重试", businessType = BusinessType.OTHER)
	@PostMapping("retry")
	@ResponseBody
	public AjaxResult retry(@RequestParam(value = "id") Long id) 
	{
		int n = 0;
		int result = 0;
		String errMsg = null;
		String status = null;
		LiconicOutputList outputList = service.selectListById(id);
		UrlConnectionUtil conn = new UrlConnectionUtil();
		LiconicJob errJob = null;
		XmlUtils xml = new XmlUtils();
		String jobXml = conn.urlGet(jobs);
		log.info("运行状态:" + jobXml);
		String jobNo = outputList.getJobNo();
		List<LiconicJob> liconicJobs = xml.parseJob(jobXml, jobNo);
		for (LiconicJob liconicJob : liconicJobs) 
		{
			String errinfo = liconicJob.getErrinfo() == null ? "0" : "1";
			if(errinfo == "1" || errinfo.equals("1"))
			{
				errJob = liconicJob;
			}
		}
		if(outputList.getStatus().indexOf("ERR") > 0) 
		{
			//重新提交任务
			List<LiconicSample> liconicSample = service.selectOutputSample(outputList);
			String picktubesXml = conn.getPickTubeXml(liconicSample, jobNo);
			log.info("重新挑管: " + picktubesXml);
			//发送post请求
			String picktubesRes = conn.urlPost(picktubesUrl, picktubesXml);
			Map<String, String> res = xml.parsePickTubesResult(picktubesRes);
			log.info("重新提交任务结果:" + picktubesXml);
			errMsg = res.get("errMsg");
			status = res.get("status");
			outputList.setStatus(status + ":" + errMsg);
			service.updateOutputList(outputList);
			if(errMsg == null || errMsg.equals(""))
			{
				//重新提交成功
				result = 1;
			} 
			else 
			{
				//重新提交失败
				result = -1;
			}
		}
		else if (errJob != null)
		{
			String retryUrl = ip + "/Scheduler/webresources/scheduler/tasks/" + errJob.getTaskId() + "/action/retry";
			String retryXml = conn.urlGet(retryUrl);
			Map<String, String> res = xml.parsePickTubesResult(retryXml);
			log.info("重试任务结果:" + retryXml);
			errMsg = res.get("errMsg");
			status = res.get("status");
			outputList.setStatus(status + ":" + errMsg);
			service.updateOutputList(outputList);
			if(res.get("errMsg") == null || res.get("errMsg").equals(""))
			{
				//重试成功
				result = 1;
			} 
			else 
			{
				//重试失败
				result = -1;
			}
		}
		else
		{
			result = -1;
		}
		
		return toAjax(result);
	}
}
