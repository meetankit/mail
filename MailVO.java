package com.msl.mo.vo.mail;

import java.sql.Timestamp;

/**
 * AccessRight entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class MailVO implements java.io.Serializable{
	
	private String msgid;
	private String msgto;
	private String msgcc;
	private String msgsender;
	private String msgstatus;
	private String msgbodytext;
	private String msgtype;
	private String subject;
	private Timestamp msgcreatedwhen;
	private String msgcreatedby;
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getMsgto() {
		return msgto;
	}
	public void setMsgto(String msgto) {
		this.msgto = msgto;
	}
	public String getMsgcc() {
		return msgcc;
	}
	public void setMsgcc(String msgcc) {
		this.msgcc = msgcc;
	}
	public String getMsgsender() {
		return msgsender;
	}
	public void setMsgsender(String msgsender) {
		this.msgsender = msgsender;
	}
	public String getMsgstatus() {
		return msgstatus;
	}
	public void setMsgstatus(String msgstatus) {
		this.msgstatus = msgstatus;
	}
	public String getMsgbodytext() {
		return msgbodytext;
	}
	public void setMsgbodytext(String msgbodytext) {
		this.msgbodytext = msgbodytext;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Timestamp getMsgcreatedwhen() {
		return msgcreatedwhen;
	}
	public void setMsgcreatedwhen(Timestamp msgcreatedwhen) {
		this.msgcreatedwhen = msgcreatedwhen;
	}
	public String getMsgcreatedby() {
		return msgcreatedby;
	}
	public void setMsgcreatedby(String msgcreatedby) {
		this.msgcreatedby = msgcreatedby;
	}
	

}
