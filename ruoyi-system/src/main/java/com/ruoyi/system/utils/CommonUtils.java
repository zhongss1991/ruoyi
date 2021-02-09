package com.ruoyi.system.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.LiconicJob;

public class CommonUtils {

	public LiconicJob getLatestJob(List<LiconicJob> jobs) 
	{
		LiconicJob liconicJob = null;
		if(jobs.size() == 1) 
		{
			liconicJob = jobs.get(0);
		}
		else 
		{
			for (int i =0 ;i < jobs.size() - 1; i++) 
			{
				if(jobs.get(i).getTaskId().compareTo(jobs.get(i + 1).getTaskId()) > 0)
				{
					liconicJob = jobs.get(i);
				}
				else
				{
					liconicJob = jobs.get(i + 1);
				}
			}
		}
		
		return liconicJob;
	}
	
	public String getCorn() 
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间
        String[] a = time.split("-");
        String y = a[0];
        String m = a[1];
        String h = a[3];
        String corn = "0/10 * * * * ？";
        
		return null;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s1 = df.format(new Date());
        String format = "yyyy-MM-dd HH:mm:ss";
        String s2 = "2020-11-19 19:00:01";
 
        Long day = dateDiff(s1, s2, format, "h");
        System.out.println(day); //369
        System.out.println(dateDiff(s1, s2, format, "h")); //8856
        System.out.println(dateDiff(s1, s2, format, "m")); //531360
        // 时间相差：369天0小时0分钟1秒。
    }
 
    /**
     *
     * @param startTime
     * @param endTime
     * @param format
     * @param str  d-天,h-小时,m-秒 表示返回相差的时间单位
     * @return
     */
    public static Long dateDiff(String startTime, String endTime, String format, String str) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        String result = "0";
        // 获得两个时间的毫秒时间差异
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            hour = diff % nd / nh + day * 24;// 计算差多少小时
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
        System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
                + (min - day * 24 * 60) + "分钟" + sec + "秒。");
 
        if ("d".equalsIgnoreCase(str)){
            return day;
        } else if ("h".equalsIgnoreCase(str)) {
            return hour;
        } else if ("m".equalsIgnoreCase(str)){
            return min;
        } else {
            return 0L;
        }
 
    }
}
