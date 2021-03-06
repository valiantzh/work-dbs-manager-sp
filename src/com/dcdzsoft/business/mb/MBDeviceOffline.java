package com.dcdzsoft.business.mb;


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
 * <p>Description: 设备离线 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class MBDeviceOffline extends ActionBean
{

    public int doBusiness(InParamMBDeviceOffline in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalNo))
            throw new EduException(ErrorCode.ERR_PARMERR);

        MBSignInfoDAO signInfoDAO = daoFactory.getMBSignInfoDAO();
        JDBCFieldArray setCols1 = new JDBCFieldArray();
        JDBCFieldArray whereCols1 = new JDBCFieldArray();

        setCols1.add("OnlineStatus", "0");
        setCols1.add("LastModifyTime", utilDAO.getCurrentDateTime());
        whereCols1.add("TerminalNo", in.TerminalNo);
        whereCols1.add("OnlineStatus","<>", "0");

        result = signInfoDAO.update(setCols1, whereCols1);

        return result;
    }
}
