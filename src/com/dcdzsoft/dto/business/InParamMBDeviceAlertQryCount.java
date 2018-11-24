package com.dcdzsoft.dto.business;

/**
* 设备警报信息查询数量
*/

public class InParamMBDeviceAlertQryCount implements java.io.Serializable
{
	public String FunctionID = "150315"; //功能编号

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String AlertType = ""; //报警种类
	public String AlertLevel = ""; //报警等级
	public String BoxNo = "";//格口编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150315";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150315";
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

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

	public String getAlertType()
	{
		return AlertType;
	}
	public void setAlertType(String AlertType)
	{
		this.AlertType = AlertType;
	}

	public String getAlertLevel()
	{
		return AlertLevel;
	}
	public void setAlertLevel(String AlertLevel)
	{
		this.AlertLevel = AlertLevel;
	}
    public String getBoxNo() {
        return BoxNo;
    }
    public void setBoxNo(String boxNo) {
        BoxNo = boxNo;
    }

	
}