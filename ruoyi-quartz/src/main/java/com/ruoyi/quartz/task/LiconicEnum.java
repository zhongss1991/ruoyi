package com.ruoyi.quartz.task;

public enum LiconicEnum {

	CREATING(0, "挑管任务生成中..."), PICK(1, "挑管中..."), EXPORT(2, "出库中..."), CONSOLIDATING(3, "冰箱整理任务生成中..."),
	CONSOLIDATED(4, "冰箱整理中..."), DONE(5, "出库完成"), FAILURE(6, "出库任务结束");
	
	private int step;
	private String name;
	
	LiconicEnum(int step, String name)
	{
		this.step = step;
		this.name = name;
	}
	
	public static LiconicEnum getLiconicEnum(int step)
	{
		for(LiconicEnum liconicEnum : LiconicEnum.values())
		{
			if(liconicEnum.getStep() == step)
			{
				return liconicEnum;
			}
		}
		return FAILURE;
	}
	
	public int getStep()
	{
		return step;
	}
	
	public String getName()
	{
		return name;
	}
}
