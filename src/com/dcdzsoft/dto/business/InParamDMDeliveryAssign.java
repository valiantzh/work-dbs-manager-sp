package com.dcdzsoft.dto.business;

/**
* 分配揽件员
*/

public class InParamDMDeliveryAssign implements java.io.Serializable
{
	public String FunctionID = "341021"; //功能编号

	public String OperID = ""; //管理员编号
	public String PackageID = ""; //订单号
	public String PostmanID = ""; //揽件员编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "341021";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "341021";
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

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

}