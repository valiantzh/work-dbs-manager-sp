package com.dcdzsoft.business.ap;

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
 * <p>Description: 收件人未取包裹列表 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APCustomerInboxPackage extends ActionBean
{

    public java.util.List<OutParamAPCustomerInboxPackage> doBusiness(InParamAPCustomerInboxPackage in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        java.util.List<OutParamAPCustomerInboxPackage> outList = new java.util.LinkedList<OutParamAPCustomerInboxPackage>();

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.CustomerMobile))
			throw new EduException(ErrorCode.ERR_PARMERR);


		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		whereSQL.add("CustomerMobile", in.CustomerMobile);
		whereSQL.checkAdd("PackageID", in.PackageID);
		whereSQL.checkAdd("TerminalNo", in.TerminalNo);

		String sql = "SELECT * FROM V_InboxPackage1 a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();
		
		RowSet rset = dbSession.executeQuery(sql, whereSQL);

		while (RowSetUtils.rowsetNext(rset)) {
			OutParamAPCustomerInboxPackage outParam = new OutParamAPCustomerInboxPackage();

			outParam.TerminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
			outParam.TerminalName = RowSetUtils.getStringValue(rset, "TerminalName");
			outParam.Location = RowSetUtils.getStringValue(rset, "Location");
			outParam.PackageID = RowSetUtils.getStringValue(rset, "PackageID");
			outParam.StoredTime = RowSetUtils.getTimestampValue(rset, "StoredTime");
			outParam.OpenBoxKey = RowSetUtils.getStringValue(rset, "OpenBoxKey");
			outParam.OpenBoxKey2 = "[][" + RowSetUtils.getStringValue(rset, "OpenBoxKey") + "]";
			outParam.PostmanID = RowSetUtils.getStringValue(rset, "PostmanID");
			outParam.PostmanName = RowSetUtils.getStringValue(rset, "PostmanName");
			outParam.CompanyName = RowSetUtils.getStringValue(rset, "CompanyName");
			outParam.TradeWaterNo = RowSetUtils.getStringValue(rset, "TradeWaterNo");
			outParam.BoxNo = RowSetUtils.getStringValue(rset, "BoxNo");

			outList.add(outParam);
		}

        return outList;
    }
}
