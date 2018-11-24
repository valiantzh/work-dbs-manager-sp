package com.dcdzsoft.dto.business;

/**
* 个人客户信息增加
*/

public class InParamIMCustomerAdd implements java.io.Serializable
{
	public String FunctionID = "250071"; //功能编号

	public String OperID = ""; //管理员编号
	public String CustomerID = ""; //个人客户编号
	public String Mobile = ""; //手机
	public String CustomerName = ""; //个人客户名称
	public String TerminalNo = ""; //设备号
	public String IDCard = ""; //身份证号
	public String Email = ""; //邮箱
	public String Active = ""; //激活状态（1-激活）
	public int Months; //有效期，超过有效期，重新注册
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "250071";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "250071";
		else
			this.FunctionID = FunctionID;
	}

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

	public String getMobile()
	{
		return Mobile;
	}
	public void setMobile(String Mobile)
	{
		this.Mobile = Mobile;
	}

	public String getCustomerName()
	{
		return CustomerName;
	}
	public void setCustomerName(String CustomerName)
	{
		this.CustomerName = CustomerName;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getIDCard()
	{
		return IDCard;
	}
	public void setIDCard(String IDCard)
	{
		this.IDCard = IDCard;
	}

	public String getEmail()
	{
		return Email;
	}
	public void setEmail(String Email)
	{
		this.Email = Email;
	}

	public String getActive()
	{
		return Active;
	}
	public void setActive(String Active)
	{
		this.Active = Active;
	}

	public int getMonths()
	{
		return Months;
	}
	public void setMonths(int Months)
	{
		this.Months = Months;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}