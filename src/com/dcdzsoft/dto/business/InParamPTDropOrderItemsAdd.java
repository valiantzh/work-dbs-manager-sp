package com.dcdzsoft.dto.business;

/**
* 添加订单到投递清单
*/

public class InParamPTDropOrderItemsAdd implements java.io.Serializable
{
	public String FunctionID = "331031"; //功能编号

	public String OperID = ""; 			//管理员编号
	public String ZoneID = ""; 			//分拣区域编号
	public String TerminalNo = ""; 		//柜体编号
	public String PackageID = ""; 		//订单号
	public String ItemStatus = ""; 		//当前订单状态
	public String ReportOrderID = ""; 	//投递清单号
	public String TerminalType = ""; 	//柜体类型
	
	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "331031";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "331031";
		else
			this.FunctionID = FunctionID;
	}
	
	public String getTerminalType() {
		return TerminalType;
	}
	public void setTerminalType(String terminalType) {
		TerminalType = terminalType;
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

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
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