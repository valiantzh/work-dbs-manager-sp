package com.dcdzsoft.dto.business;

/**
* 确认收到超期未取包裹
*/

public class InParamPTExpParcelsReturn implements java.io.Serializable
{
	public String FunctionID = "331043"; //功能编号

	public String OperID = ""; //管理员编号
	public String PackageID = ""; //订单号
	public String TerminalNo = ""; //设备号
	public String ZoneID = ""; //分拣区域编号
	public String ItemStatus = ""; //当前订单状态

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "331043";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "331043";
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

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getZoneID()
	{
		return ZoneID;
	}
	public void setZoneID(String ZoneID)
	{
		this.ZoneID = ZoneID;
	}

	public String getItemStatus()
	{
		return ItemStatus;
	}
	public void setItemStatus(String ItemStatus)
	{
		this.ItemStatus = ItemStatus;
	}

}