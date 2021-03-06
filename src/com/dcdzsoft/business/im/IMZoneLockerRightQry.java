package com.dcdzsoft.business.im;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 自提柜后台运营系统</p>
 * <p>Description: 分拣区域柜体权限查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class IMZoneLockerRightQry extends ActionBean
{

    public RowSet doBusiness(InParamIMZoneLockerRightQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.ZoneID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
		
		IMZoneDAO zoneDAO = daoFactory.getIMZoneDAO();
        IMZone zone = new IMZone();
        zone.ZoneID = in.ZoneID;
        zone = zoneDAO.find(zone);
        
        IMCompanyDAO companyDAO = daoFactory.getIMCompanyDAO();
		IMCompany company = new IMCompany();
		company.CompanyID = zone.CompanyID;
		company = companyDAO.find(company);
		
        String wheresql = " WHERE 1=1 ";
		if(StringUtils.isNotEmpty(in.TerminalNo)){
			wheresql += " AND TerminalNo="+StringUtils.addQuote(in.TerminalNo);
		}
		
		String limitsql = "";
		String selectsql = "SELECT TerminalNo, TerminalName, Location"//, Address
				+ ","+StringUtils.addQuote(zone.ZoneID)+" AS ZoneID"
				+ ","+StringUtils.addQuote(zone.ZoneName)+" AS ZoneName "
				+ " FROM TBTerminal a ";
		
		if("1".equals(in.QryType)){//1-查询可选箱
			//公司已分配的柜体
			String compLocker = " AND EXISTS(SELECT c.TerminalNo From IMCompanyBoxRight c WHERE c.CompanyID="+StringUtils.addQuote(company.CompanyID)
					+ " AND c.TerminalNo=a.TerminalNo"
					+ ") ";
			String temp = company.CompanyID+"%";//同一柜体不能分配给同一公司的不同AZC
			limitsql = compLocker+" AND NOT EXISTS(SELECT TerminalNo From IMZoneLockerRight b WHERE b.ZoneID LIKE "+StringUtils.addQuote(temp)
					+ " AND b.TerminalNo=a.TerminalNo"
					+ ") ";
		}else{
			//查询已选箱
			limitsql = " AND EXISTS(SELECT TerminalNo From IMZoneLockerRight b WHERE b.ZoneID="+StringUtils.addQuote(in.ZoneID)
					+ " AND b.TerminalNo=a.TerminalNo"
					+ ") ";
		}
		
		//wheresql在limitsql之前
		String sql = selectsql +wheresql+limitsql;
		
		java.util.List<String> orderByField = new java.util.LinkedList<String>();
        orderByField.add("TerminalName ASC");
        
        rset = dbSession.executeQuery(sql, orderByField, in.recordBegin,in.recordNum);
        
        return rset;
    }
}
