package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class IMCompanyBoxRight implements java.io.Serializable
{
	public String CompanyID = "";
	public String TerminalNo = "";
	public String BoxNo = "";
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}