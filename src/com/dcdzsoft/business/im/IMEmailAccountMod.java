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
 * <p>Description: 邮件账户信息修改 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class IMEmailAccountMod extends ActionBean
{

    public int doBusiness(InParamIMEmailAccountMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.EmailID)
			||StringUtils.isEmpty(in.EmailName))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		
		//////////////////邮件账户共用表IMGateway
		
		
		//4.	调用IMGatewayDAO.isExist()判断短信邮件接口名称是否存在，如果存在返回ERR_GATEWAYNAMEEXISTS。
		IMGatewayDAO gatewayDAO = daoFactory.getIMGatewayDAO();

        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("GatewayName", in.EmailName);
        whereCols0.add("GatewayID", "<>", in.EmailID);
        if (gatewayDAO.isExist(whereCols0) > 0){
            throw new EduException(ErrorCode.ERR_EMAILACCOUNTNAMEEXISTS);
        }

		//5.	调用IMGatewayDAO. Update()更新短信邮件接口信息。
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        setCols.add("GatewayName", in.EmailName);
        //setCols.add("SMSUsername", in.Username);//Username为邮件地址，用GatewayURL字段存储
        setCols.add("GatewayURL", in.Username);
        setCols.add("SMSPassword", in.Password);
        setCols.add("EmailParam1", in.EmailParam1);
        setCols.add("EmailParam2", in.EmailParam2);
        setCols.add("EmailParam3", in.EmailParam3);
        setCols.add("EmailParam4", in.EmailParam4);
        setCols.add("LastModifyTime", sysDateTime);
        setCols.add("Remark", in.Remark);

        whereCols.add("GatewayID", in.EmailID);

        result = gatewayDAO.update(setCols, whereCols);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = in.EmailName;

        commonDAO.addOperatorLog(log);

        return result;
    }
}
