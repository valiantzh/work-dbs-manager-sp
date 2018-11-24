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
 * <p>Description: 揽件区域信息删除 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class IMCollectZoneDel extends ActionBean
{

    public int doBusiness(InParamIMCollectZoneDel in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.CollectZoneID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
        
		//揽件订单表
        DMCollectionParcelDAO collectDAO = daoFactory.getDMCollectionParcelDAO();
        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("CollectZoneID",in.CollectZoneID);
        if(collectDAO.isExist(whereCols0)>0){
        	throw new EduException(ErrorCode.ERR_FORBIDDELETE);
        }
		//揽件历史表
        DMHistoryItemDAO dmHistoryDAO = daoFactory.getDMHistoryItemDAO();
        if(dmHistoryDAO.isExist(whereCols0)>0){
        	throw new EduException(ErrorCode.ERR_FORBIDDELETE);
        }
        
        //判断所属揽件区域的商业合作伙伴是否存在
        IMBusinessPartnerDAO partnerDAO = daoFactory.getIMBusinessPartnerDAO();
        if (partnerDAO.isExist(whereCols0) > 0){
            throw new EduException(ErrorCode.ERR_FORBIDDELCOLLECTZONE_PARTNER);
        }
        
        //清除投递员所属的揽件区域
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        JDBCFieldArray setCols = new JDBCFieldArray();
        
        setCols.add("CollectZoneID", "");
        whereCols.add("CollectZoneID", in.CollectZoneID);
        postmanDAO.update(setCols, whereCols);
        
        //
        IMCollectZoneDAO collectZoneDAO = daoFactory.getIMCollectZoneDAO();
        IMCollectZone collectZone = new IMCollectZone();
        collectZone.CollectZoneID = in.CollectZoneID;
        collectZone = collectZoneDAO.find(collectZone);
        
        collectZoneDAO.delete(collectZone);
        
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.CollectZoneID;

		commonDAO.addOperatorLog(log);

        return result;
    }
}
