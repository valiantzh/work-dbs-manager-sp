package com.dcdzsoft.business.pt;

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
 * <p>Description: 定期消息发送（人工设定时间和消息内容） </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PTManSendMessage extends ActionBean
{

    public int doBusiness(InParamPTManSendMessage in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.PackageID)
			||StringUtils.isEmpty(in.MsgType)
			||StringUtils.isEmpty(in.MsgContent)
			//||StringUtils.isEmpty(in.Phone)
			//||StringUtils.isEmpty(in.Email)
			||in.SendingTime == null )
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		if("1".equals(in.MsgType)){//Send SMS
			if(StringUtils.isEmpty(in.Phone)){
				throw new EduException(ErrorCode.ERR_PARMERR);
			}
		}else if("2".equals(in.MsgType)){//Send Email
			if(StringUtils.isEmpty(in.Email)){
				throw new EduException(ErrorCode.ERR_PARMERR);
			}
		}else{
			throw new EduException(ErrorCode.ERR_PARMERR);
		}

		//2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);


		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = "";

		commonDAO.addOperatorLog(log);


        return result;
    }
}
