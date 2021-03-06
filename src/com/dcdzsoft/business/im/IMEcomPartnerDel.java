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
 * <p>Description: 电商伙伴删除 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class IMEcomPartnerDel extends ActionBean
{

    public int doBusiness(InParamIMEcomPartnerDel in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.EPartnerID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

		//4.	调用IMEcommercePartnerDAO.delete()删除电商伙伴信息。
		IMEcommercePartnerDAO ecommercePartnerDAO = daoFactory.getIMEcommercePartnerDAO();

		IMEcommercePartner ecommercePartner = new IMEcommercePartner();		
		ecommercePartner.EPartnerID = in.EPartnerID;
		
		ecommercePartner = ecommercePartnerDAO.find(ecommercePartner);
		
		result = ecommercePartnerDAO.delete(ecommercePartner);
		
		//#start 删除UserKey映射
        APUserKeyMapDAO userKeyMapDAO = daoFactory.getAPUserKeyMapDAO();
        JDBCFieldArray whereCols2 = new JDBCFieldArray();
        whereCols2.add("UserKey", ecommercePartner.Password);
        userKeyMapDAO.delete(whereCols2);
        //#end
        
        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = in.EPartnerID;

		commonDAO.addOperatorLog(log);

        return result;
    }
}
