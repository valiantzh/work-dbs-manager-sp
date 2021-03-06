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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 修改订单状态 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTModPackageStatus extends ActionBean {

    public int doBusiness(InParamPTModPackageStatus in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.PackageID) ||
        	StringUtils.isEmpty(in.TerminalNo) ||
        	StringUtils.isEmpty(in.PackageStatus))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //4.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        //////////////////////////////////////////////////////////////
        PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();

        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("PackageID", in.PackageID);
        whereCols.add("TerminalNo", in.TerminalNo);

        if (inboxPackDAO.isExist(whereCols) == 0)
            throw new EduException(ErrorCode.ERR_PACKAGENOTEXISTS);

        ////////////////////////////////////////////////////////////////
        JDBCFieldArray setCols = new JDBCFieldArray();
        setCols.add("PackageStatus",in.PackageStatus);
        //setCols.add("LastModifyTime",sysDateTime);
        
        inboxPackDAO.update(setCols,whereCols);
        
        ///推送到设备端
      ///try
      ///{
      ///	com.dcdzsoft.businessproxy.PushBusinessProxy.doBusiness(in);
      ///}catch(EduException e)
      ///{
      /// 	
      ///}


        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = in.PackageID;

        commonDAO.addOperatorLog(log);

        return result;
    }
}
