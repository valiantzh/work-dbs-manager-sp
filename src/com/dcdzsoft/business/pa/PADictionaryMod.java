package com.dcdzsoft.business.pa;

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
 * <p>Description: 修改系统字典。 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PADictionaryMod extends ActionBean
{

    public int doBusiness(InParamPADictionaryMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || in.DictTypeID <= 0
            || StringUtils.isEmpty(in.DictID)
            || StringUtils.isEmpty(in.DictName))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //4.	调用PADictionaryDAO.isExist()查询系统字典表记录，系统字典表记录不存在，返回ErrorCode.ERR_PADICTIONARYNODATA。
        PADictionaryDAO dictionaryDAO = daoFactory.getPADictionaryDAO();

        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("DictTypeID", in.DictTypeID);
        whereCols.add("DictID", in.DictID);

        if (dictionaryDAO.isExist(whereCols) <= 0)
            throw new EduException(ErrorCode.ERR_PADICTIONARYNODATA);

        //5.	调用PADictionaryDAO.update()修改系统字典。
        JDBCFieldArray setCols = new JDBCFieldArray();
        setCols.add("DictName", in.DictName);
        setCols.add("Remark", in.Remark);

        dictionaryDAO.update(setCols, whereCols);

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
