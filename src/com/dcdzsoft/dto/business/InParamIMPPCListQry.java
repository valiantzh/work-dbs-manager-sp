package com.dcdzsoft.dto.business;

/**
* 包裹处理中心列表查询
*/

public class InParamIMPPCListQry implements java.io.Serializable
{
	public String FunctionID = "250046"; //功能编号

	public String OperID = ""; //管理员编号
	public String CompanyID = ""; //包裹服务商编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "250046";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "250046";
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

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

}