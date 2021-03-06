package com.dcdzsoft.dto.business;

/**
* 远程开箱
*/

public class InParamRMRemoteOpenBox implements java.io.Serializable
{
	public String FunctionID = "550028"; //功能编号

	public String OperID = ""; //管理员编号
	public String AppealNo = ""; //求助编号
	public String OpenBoxKey = ""; //开箱密码
	public String TerminalNo = ""; //设备号
	public String RemoteFlag = ""; //远程操作标志

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "550028";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "550028";
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

	public String getAppealNo()
	{
		return AppealNo;
	}
	public void setAppealNo(String AppealNo)
	{
		this.AppealNo = AppealNo;
	}

	public String getOpenBoxKey()
	{
		return OpenBoxKey;
	}
	public void setOpenBoxKey(String OpenBoxKey)
	{
		this.OpenBoxKey = OpenBoxKey;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getRemoteFlag()
	{
		return RemoteFlag;
	}
	public void setRemoteFlag(String RemoteFlag)
	{
		this.RemoteFlag = RemoteFlag;
	}

}