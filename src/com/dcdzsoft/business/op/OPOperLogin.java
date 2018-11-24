package com.dcdzsoft.business.op;

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
 * <p>Description: 管理员登陆 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class OPOperLogin extends ActionBean
{

    public RowSet doBusiness(InParamOPOperLogin in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.OperPassword))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //3.	调用CommonDAO.checkPassword(管理员编号,管理员密码,管理员类别)检查管理员密码。
        OPOperatorDAO operDAO = daoFactory.getOPOperatorDAO();
        OPOperator oper = new OPOperator();
        oper.OperID = in.OperID;

        try
        {
        	oper = operDAO.find(oper);
        }catch(EduException e)
        {
        	JDBCFieldArray whereCols = new JDBCFieldArray();
        	whereCols.add("UserID", in.OperID);
        	RowSet rset1 = operDAO.select(whereCols);
        	if(RowSetUtils.rowsetNext(rset1))
        	{
        		oper.OperID = RowSetUtils.getStringValue(rset1, "OperID");
        		oper = operDAO.find(oper);
        	}else{
        		throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);
        	}
        	
        }
        

        if (oper.OperStatus.compareTo("0") != 0)
            throw new EduException(ErrorCode.ERR_OPERSTATUSABNORMAL);
        
        if (oper.OperPassword.compareTo(in.OperPassword) != 0)
            throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);

        //4.	调用CommonDAO. modifyOperOnline()修改管理员在线信息，同时获得默认管理员编号。
        OPOperOnline opOnline = new OPOperOnline();
        opOnline.OperID = oper.OperID;
        opOnline.OperType = oper.OperType;
        opOnline.ZoneID = oper.ZoneID;
        opOnline.LoginInTime = sysDateTime;
        opOnline.NetCardAddr = in.NetCardAddr;
        opOnline.LoginIPAddr = in.LoginIPAddr;
        opOnline.OnlineStatus = "1";

        commonDAO.modifyOperOnline(opOnline);

        //5.	调用CommonDAO.operMenuQry(默认管理员编号，null)获得管理员菜单信息。
        rset = commonDAO.operMenuQry(oper.OperID,null);

        //6.	返回管理员菜单信息。

        return rset;
    }
}
