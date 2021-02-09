package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 耗材领用对象 con_receive_list
 * 
 * @author zss
 * @date 2020-05-25
 */
public class ConReceiveList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long conid;

    /** 耗材类型 */
    private Long contypeid;

    /** 领用人 */
    @Excel(name = "领用人")
    private String receiveuser;

    /** 领用时间 */
    @Excel(name = "领用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receivetime;

    /** 领用数量 */
    @Excel(name = "领用数量")
    private Long receivenum;

    @Excel(name = "耗材", targetAttr = "name", type = Type.EXPORT)
    private ConType conType;
    
    /** 操作类型 0 领用， 1 采购*/
    private long opetype;
    
    public long getOpetype() {
		return opetype;
	}

	public void setOpetype(long opetype) {
		this.opetype = opetype;
	}

	public ConType getConType() {
		return conType;
	}

	public void setConType(ConType conType) {
		this.conType = conType;
	}

	public void setConid(Long conid) 
    {
        this.conid = conid;
    }

    public Long getConid() 
    {
        return conid;
    }
    public void setContypeid(Long contypeid) 
    {
        this.contypeid = contypeid;
    }

    public Long getContypeid() 
    {
        return contypeid;
    }
    public void setReceiveuser(String receiveuser) 
    {
        this.receiveuser = receiveuser;
    }

    public String getReceiveuser() 
    {
        return receiveuser;
    }
    public void setReceivetime(Date receivetime) 
    {
        this.receivetime = receivetime;
    }

    public Date getReceivetime() 
    {
        return receivetime;
    }
    public void setReceivenum(Long receivenum) 
    {
        this.receivenum = receivenum;
    }

    public Long getReceivenum() 
    {
        return receivenum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("conid", getConid())
            .append("contypeid", getContypeid())
            .append("receiveuser", getReceiveuser())
            .append("receivetime", getReceivetime())
            .append("receivenum", getReceivenum())
            .append("opetype", getOpetype())
            .toString();
    }
}
