package com.dcdzsoft.dto.business;

/**
* 发送离线警报信息
*/

public class InParamMBSendOfflineInfo implements java.io.Serializable
{
	public String FunctionID = "150319"; //功能编号

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150319";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150319";
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

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

}