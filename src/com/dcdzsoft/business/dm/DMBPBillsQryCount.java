package com.dcdzsoft.business.dm;

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
 * <p>Description: BP账单数量查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class DMBPBillsQryCount extends ActionBean
{

    public int doBusiness(InParamDMBPBillsQryCount in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		if(in.BeginDate==null){
			in.BeginDate = DateUtils.addDate(sysDate, -365);//查询近的订单记录
		}
		if(in.EndDate==null){
			in.EndDate = sysDate;
		}
		in.EndDate = DateUtils.addDate(in.EndDate, 1);//
		
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("BPartnerID", in.BPartnerID);
        whereSQL.add("BillTime", ">=",in.BeginDate);
        whereSQL.add("BillTime", "<=",in.EndDate);
        whereSQL.checkAdd("BillType", in.BillType);
        whereSQL.checkAdd("TradeWaterID", in.TradeWaterID);
        String sql = "SELECT COUNT(TradeWaterID) FROM V_PYServiceBillWater  WHERE 1=1 " + whereSQL.getPreparedWhereSQL() ;
        result = dbSession.executeQueryCount(sql,whereSQL);

        return result;
    }
}
