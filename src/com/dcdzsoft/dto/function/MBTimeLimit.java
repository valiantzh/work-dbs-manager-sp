package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBTimeLimit implements java.io.Serializable
{
	public String StatusID = "";
	public String StatusName = "";
	public int TimeLimit;
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}