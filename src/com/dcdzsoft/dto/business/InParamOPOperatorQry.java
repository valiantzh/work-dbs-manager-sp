package com.dcdzsoft.dto.business;

/**
* 查询管理员信息
*/

public class InParamOPOperatorQry implements java.io.Serializable
{
	public String FunctionID = "132004"; //功能编号

	public int recordBegin; 
	public int recordNum; 

	public String OperID = ""; //管理员编号
	public String UserID = ""; //被操作的管理员编号
	public String ByOperID = ""; //被操作的管理员编号
	public String OperName = ""; //管理员姓名
	public int OperType; //管理员类型
	public String DepartmentID = ""; //运营网点编号
	public String ZoneID = ""; //分拣区域中心编号
	public String OperStatus = ""; //管理员状态
	public String Email = ""; //电子邮件
	public int RoleID; //角色编号


	@Override
	public String toString() {
		return "InParamOPOperatorQry [FunctionID=" + FunctionID
				+ ", recordBegin=" + recordBegin + ", recordNum=" + recordNum
				+ ", OperID=" + OperID + ", UserID=" + UserID + ", ByOperID="
				+ ByOperID + ", OperName=" + OperName + ", OperType="
				+ OperType + ", DepartmentID=" + DepartmentID + ", ZoneID="
				+ ZoneID + ", OperStatus=" + OperStatus + ", Email=" + Email
				+ ", RoleID=" + RoleID + "]";
	}
	public int getRecordBegin()
	{
		return recordBegin;
	}
	public void setRecordBegin(int recordBegin)
	{
		this.recordBegin = recordBegin;
	}


	public int getRecordNum()
	{
		return recordNum;
	}
	public void setRecordNum(int recordNum)
	{
		this.recordNum = recordNum;
	}

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "132004";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "132004";
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

	public String getUserID()
	{
		return UserID;
	}
	public void setUserID(String UserID)
	{
		this.UserID = UserID;
	}

	public String getByOperID()
	{
		return ByOperID;
	}
	public void setByOperID(String ByOperID)
	{
		this.ByOperID = ByOperID;
	}

	public String getOperName()
	{
		return OperName;
	}
	public void setOperName(String OperName)
	{
		this.OperName = OperName;
	}

	public int getOperType()
	{
		return OperType;
	}
	public void setOperType(int OperType)
	{
		this.OperType = OperType;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getZoneID()
	{
		return ZoneID;
	}
	public void setZoneID(String ZoneID)
	{
		this.ZoneID = ZoneID;
	}

	public String getOperStatus()
	{
		return OperStatus;
	}
	public void setOperStatus(String OperStatus)
	{
		this.OperStatus = OperStatus;
	}

	public String getEmail()
	{
		return Email;
	}
	public void setEmail(String Email)
	{
		this.Email = Email;
	}

	public int getRoleID()
	{
		return RoleID;
	}
	public void setRoleID(int RoleID)
	{
		this.RoleID = RoleID;
	}

}