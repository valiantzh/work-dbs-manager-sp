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
 * <p>Description: 管理员删除 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class OPOperatorDel extends ActionBean
{

    public int doBusiness(InParamOPOperatorDel in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
        		||StringUtils.isEmpty(in.ByOperID))
            throw new EduException(ErrorCode.ERR_PARMERR);

		// 2. 调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		// 3. 调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		OPOperatorDAO operatorDAO = daoFactory.getOPOperatorDAO();
		OPOperator operObj = new OPOperator();
		operObj.OperID = in.ByOperID;

		operObj = operatorDAO.find(operObj);
		
		//////////////////////////////////////////////////////////////被删除管理员在线，则删除失败
		OPOperOnlineDAO operOnlineDAO = daoFactory.getOPOperOnlineDAO();
		JDBCFieldArray whereCols0 = new JDBCFieldArray();
		whereCols0.add("OperID", in.ByOperID);
		
		if(operOnlineDAO.isExist(whereCols0) > 0)
			throw new EduException(ErrorCode.ERR_FORBIDDELOPERATOR);
		
		if((operOnline.OperType >= operObj.OperType) //防止低级别管理员误删，超级管理员为1 2-master管理员，3-现场管理员，4-操作员
				||(in.ByOperID == "181818" || in.ByOperID == "Master")){//系统初始创建的超级用户“181818”和master管理员"Master"不允许删除
			throw new EduException(ErrorCode.ERR_FORBIDDELOPERATOR);
		}
		////////////////////////////////////////////////////////////////删除操作员菜单记录
		OPOperToMenuDAO operToMenuDAO = daoFactory.getOPOperToMenuDAO();
		JDBCFieldArray whereCols1 = new JDBCFieldArray();
		whereCols1.add("OperID", in.ByOperID);
		
		operToMenuDAO.delete(whereCols1);
		
		////////////////////////////////////////////////////////////////删除操作员特殊权限记录
		OPOperSpeRightDAO operSpeRightDAO = daoFactory.getOPOperSpeRightDAO();
		JDBCFieldArray whereCols2 = new JDBCFieldArray();
		whereCols2.add("OperID", in.ByOperID);
		
		operSpeRightDAO.delete(whereCols2);

		////////////////////////////////////////////////////////////////删除操作员角色记录
		OPOperRoleDAO operRoleDAO = daoFactory.getOPOperRoleDAO();
		JDBCFieldArray whereCols3 = new JDBCFieldArray();
		whereCols3.add("OperID", in.ByOperID);
		
		operRoleDAO.delete(whereCols3);
		
		//////////////////////////////////////////////////////////////删除操作员记录
		result = operatorDAO.delete(operObj);

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.ByOperID;

		commonDAO.addOperatorLog(log);

		return result;
	}
}
