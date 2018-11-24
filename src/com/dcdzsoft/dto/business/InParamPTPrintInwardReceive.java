package com.dcdzsoft.dto.business;

/**
* R5报告打印（寄件接收清单报告）
*/

public class InParamPTPrintInwardReceive implements java.io.Serializable
{
	public String FunctionID = "331085"; //功能编号

	public String OperID = ""; //管理员编号
	public String ReportID = ""; //Report编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "331085";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "331085";
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

	public String getReportID()
	{
		return ReportID;
	}
	public void setReportID(String ReportID)
	{
		this.ReportID = ReportID;
	}

}