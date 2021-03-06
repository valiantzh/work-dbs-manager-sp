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
 * <p>Description: 角色特殊权限设置增加 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class OPRoleSpeRightAdd extends ActionBean
{

    public int doBusiness(InParamOPRoleSpeRightAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || in.RoleID <= 0)
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用OPRoleDAO.isExist (角色编号)查询角色信息是否存在，如果不存在返回ERR_OPROLENODATA。
        OPRoleSpeRightDAO roleSpeRightDAO = daoFactory.getOPRoleSpeRightDAO();
        OPRoleDAO roleDAO = daoFactory.getOPRoleDAO();
        OPRole role = new OPRole();
        role.RoleID = in.RoleID;

        role = roleDAO.find(role);

        if (StringUtils.isEmpty(in.SpePrivID)) {//输入特殊权限为空，删除角色特殊权限？
            JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("RoleID", in.RoleID);
            roleSpeRightDAO.delete(whereCols0);
        }

        //4.	调用OPRoleSpeRightDAO.isExist (角色编号, 特殊权限号)查询角色特殊权限设置信息是否存在，如果存在返回ERR_OPROLESPERIGHTEXISTS。
		



        //5.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //6.	调用OPRoleSpeRightDAO.insert ()插入角色特殊权限设置信息。
        //输入参数如下： 
        //RoleID＝角色编号
        //SpePrivID=特殊权限号
        OPRoleSpeRight obj = new OPRoleSpeRight();
        obj.RoleID = in.RoleID;

        String[] speRightIDs = StringUtils.tokenize(in.SpePrivID, ",");
        if (speRightIDs != null && speRightIDs.length > 0) {
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("RoleID", in.RoleID);
            roleSpeRightDAO.delete(whereCols);

            for (int i = 0; i < speRightIDs.length; i++) {
                obj.SpePrivID = NumberUtils.parseInt(speRightIDs[i]);
                roleSpeRightDAO.insert(obj);
            }
        }


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
