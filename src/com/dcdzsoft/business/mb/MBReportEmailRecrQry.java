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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 接收邮件管理员查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBReportEmailRecrQry extends ActionBean
{

    public RowSet doBusiness(InParamMBReportEmailRecrQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;
        OPOperOnline operOnline = null;
		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)){
			throw new EduException(ErrorCode.ERR_PARMERR);
		}else if(!"SendBoxUsedEmail".equals(in.OperID)){
		//2.调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
			operOnline = commonDAO.isOnline(in.OperID);
		}


		//3.调用UtilDAO.getSysDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

//		String limitSQL = "";
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
//      whereCols.checkAdd("CreateTime", in.CreateTime);
//      whereCols.checkAdd("LastModifyTime", in.LastModifyTime);
//		whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        whereSQL.checkAdd("OperID", in.UserID);

		String sql = "SELECT * FROM V_MBSendReportEmailJS a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();

		rset = dbSession.executeQuery(sql,whereSQL);

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		if(!"SendBoxUsedEmail".equals(in.OperID)){
			OPOperatorLog log = new OPOperatorLog();
			log.OperID = in.OperID;
			log.FunctionID = in.getFunctionID();
			log.OccurTime = sysDateTime;
			log.StationAddr = operOnline.LoginIPAddr;
			log.Remark = "";
			commonDAO.addOperatorLog(log);
		}

        return rset;
    }
}
