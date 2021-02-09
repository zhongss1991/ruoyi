package com.ruoyi.quartz.task;

import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
    	TaskUtil task = new TaskUtil();
    	
    	if (params.equals(JobConstants.GET_LICONIC_SAMPLE) || params == JobConstants.GET_LICONIC_SAMPLE) 
    	{
    		task.getLiconicSample();
    	} 
    	else if (params.equals(JobConstants.LICONIC_OUTPUT_LISTENER) || params == JobConstants.LICONIC_OUTPUT_LISTENER)
    	{
    		//定时直接出库任务
    		
    	}
    	else
    	{
    		task.liconicListener(params);
    	}
//    	else if (params.equals(JobConstants.GET_TECAN_SAMPLE) || params == JobConstants.GET_TECAN_SAMPLE) 
//    	{
//    		task.getTecanSample();
//    	}
        
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }
}
