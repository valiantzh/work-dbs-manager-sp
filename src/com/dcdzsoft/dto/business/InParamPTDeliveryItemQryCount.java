package com.dcdzsoft.dto.business;

/**
* 投递订单查询数量
*/

public class InParamPTDeliveryItemQryCount implements java.io.Serializable
{
	public String FunctionID = "331015"; //功能编号

	public String OperID = ""; //管理员编号
	public String PackageID = ""; //订单号
	public String PPCID = ""; //包裹处理中心编号
	public String ZoneID = ""; //分拣区域编号
	public String CustomerMobile = ""; //取件人手机
	public String CustomerID = ""; //取件人编号
	public String CompanyID = ""; //包裹服务商编号
	public String RefNo = ""; //参考编号
	public String TerminalNo = ""; //设备号
	public String BoxNo = ""; //箱门编号
	public String PostmanID = ""; //投递员编号
	public String ItemStatus = ""; //订单状态集
	public String RunStatus = ""; //订单执行状态
	public String DADFlag = ""; //直投标志
	public String PaneFlag = ""; //Pane标志
	public String ReturnOrderID = ""; //退单号
	public java.sql.Date BeginDate; //开始日期
	public java.sql.Date EndDate; //结束日期

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "331015";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "331015";
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

	public String getPPCID()
	{
		return PPCID;
	}
	public void setPPCID(String PPCID)
	{
		this.PPCID = PPCID;
	}

	public String getZoneID()
	{
		return ZoneID;
	}
	public void setZoneID(String ZoneID)
	{
		this.ZoneID = ZoneID;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getRefNo()
	{
		return RefNo;
	}
	public void setRefNo(String RefNo)
	{
		this.RefNo = RefNo;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getItemStatus()
	{
		return ItemStatus;
	}
	public void setItemStatus(String ItemStatus)
	{
		this.ItemStatus = ItemStatus;
	}

	public String getRunStatus()
	{
		return RunStatus;
	}
	public void setRunStatus(String RunStatus)
	{
		this.RunStatus = RunStatus;
	}

	public String getDADFlag()
	{
		return DADFlag;
	}
	public void setDADFlag(String DADFlag)
	{
		this.DADFlag = DADFlag;
	}

	public String getPaneFlag()
	{
		return PaneFlag;
	}
	public void setPaneFlag(String PaneFlag)
	{
		this.PaneFlag = PaneFlag;
	}

	public String getReturnOrderID()
	{
		return ReturnOrderID;
	}
	public void setReturnOrderID(String ReturnOrderID)
	{
		this.ReturnOrderID = ReturnOrderID;
	}

	public java.sql.Date getBeginDate()
	{
		return BeginDate;
	}
	public void setBeginDate(java.sql.Date BeginDate)
	{
		this.BeginDate = BeginDate;
	}

	public java.sql.Date getEndDate()
	{
		return EndDate;
	}
	public void setEndDate(java.sql.Date EndDate)
	{
		this.EndDate = EndDate;
	}

}