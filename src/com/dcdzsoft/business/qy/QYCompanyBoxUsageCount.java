package com.dcdzsoft.business.qy;

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
 * <p>Description: 格口使用统计数量 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class QYCompanyBoxUsageCount extends ActionBean
{

    public int doBusiness(InParamQYCompanyBoxUsageCount in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		if(in.BeginDate==null){
			in.BeginDate = DateUtils.addDate(sysDate, -60);//查询近2个月的订单记录
		}
		if(in.EndDate==null){
			in.EndDate = sysDate;
		}
		in.EndDate = DateUtils.addDate(in.EndDate, 1);//
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("CompanyID", in.CompanyID);
        whereSQL.add("TakedTime", ">=",in.BeginDate);
        whereSQL.add("TakedTime", "<=",in.EndDate);
        
        String sql = "SELECT COUNT(PackageID) FROM V_QYServiceOwnerBoxUsage a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();
        result = dbSession.executeQueryCount(sql,whereSQL);

        return result;
    }
}
