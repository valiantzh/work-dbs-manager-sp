package com.dcdzsoft.dto.business;

/**
* 指定投递员派送
*/

public class InParamPTTransportPackExe implements java.io.Serializable
{
	public String FunctionID = "331018"; //功能编号

	public String OperID = ""; //管理员编号
	public String ZoneID = ""; //分拣区域编号
	public String ZoneIDDes = ""; //目的分拣区域编号
	public String PackageID = ""; //订单号
	public String TerminalNo = ""; //柜体编号
	public String DropAgentID = ""; //投递员编号
	public String ItemStatus = ""; //当前订单状态
	public String ReportOrderID = ""; //投递清单号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "331018";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "331018";
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

	public String getZoneID()
	{
		return ZoneID;
	}
	public void setZoneID(String ZoneID)
	{
		this.ZoneID = ZoneID;
	}

	public String getZoneIDDes()
	{
		return ZoneIDDes;
	}
	public void setZoneIDDes(String ZoneIDDes)
	{
		this.ZoneIDDes = ZoneIDDes;
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

	public String getDropAgentID()
	{
		return DropAgentID;
	}
	public void setDropAgentID(String DropAgentID)
	{
		this.DropAgentID = DropAgentID;
	}

	public String getItemStatus()
	{
		return ItemStatus;
	}
	public void setItemStatus(String ItemStatus)
	{
		this.ItemStatus = ItemStatus;
	}

	public String getReportOrderID()
	{
		return ReportOrderID;
	}
	public void setReportOrderID(String ReportOrderID)
	{
		this.ReportOrderID = ReportOrderID;
	}

}