package com.dcdzsoft.business.mb;

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
 * <p>Description: 设备签到信息查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class MBDeviceSignQry extends ActionBean
{

    public RowSet doBusiness(InParamMBDeviceSignQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
        
        String limitSQL = "";//utilDAO.getLimitSQL(operOnline.DepartmentID);
        
        /////////////////////////////////////////////////////////////
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        //whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        //whereSQL.checkAdd("TerminalName", in.TerminalName);
        if(StringUtils.isNotEmpty(in.TerminalName))
           whereSQL.add("TerminalName", " LIKE ", "%" + in.TerminalName + "%");

        whereSQL.checkAdd("OnlineStatus", in.OnlineStatus);
        whereSQL.checkAdd("TerminalStatus", in.TerminalStatus);

        if("true".equalsIgnoreCase(in.VersionFlag))
            whereSQL.addSQL(" AND SoftwareVersion<>ServerVersion ");

        String sql = "SELECT * FROM V_MBSignInfo a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;

        java.util.LinkedList list = new java.util.LinkedList();
        list.add("LastModifyTime DESC");

        rset = dbSession.executeQuery(sql, list, in.recordBegin, in.recordNum, whereSQL);

        return rset;
    }
}
