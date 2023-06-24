package com.msl.mo.db.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MoMail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MO_MAIL", schema = "MONLINE")
public class MoMail implements java.io.Serializable {

	// Fields
	public boolean fromMets=false; 
	private String msgid;
	private MoUser moUser;
	private String msgto;
	private String msgcc;
	private String msgsender;
	private String msgstatus;
	private String msgbodytext;
	private String msgtype;
	private String subject;
	private Timestamp msgcreatedwhen;

	// Constructors

	/** default constructor */
	public MoMail() {
	}

	/** minimal constructor */
	public MoMail(String msgid, String msgto,String subject,
			String msgstatus, String msgtype) {
		this.msgid = msgid;
		this.subject = subject;
		this.msgto = msgto;
		this.msgstatus = msgstatus;
		this.msgtype = msgtype;
	}

	/** full constructor */
	public MoMail(String msgid, String subject, MoUser moUser,
			String msgto, String msgcc, String msgsender, String msgstatus,
			String msgbodytext, String msgtype, Timestamp msgcreatedwhen) {
		this.msgid = msgid;
		this.subject = subject;
		this.moUser = moUser;
		this.msgto = msgto;
		this.msgcc = msgcc;
		this.msgsender = msgsender;
		this.msgstatus = msgstatus;
		this.msgbodytext = msgbodytext;
		this.msgtype = msgtype;
		this.msgcreatedwhen = msgcreatedwhen;
	}

	// Property accessors
	@Id
	@Column(name = "MSGID", unique = true, nullable = false, length = 32)
	public String getMsgid() {
		return this.msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MSGCREATEDBY")
	public MoUser getMoUser() {
		return this.moUser;
	}

	public void setMoUser(MoUser moUser) {
		this.moUser = moUser;
	}

	@Column(name = "MSGTO", nullable = false, length = 64)
	public String getMsgto() {
		return this.msgto;
	}

	public void setMsgto(String msgto) {
		this.msgto = msgto;
	}

	@Column(name = "MSGCC", length = 256)
	public String getMsgcc() {
		return this.msgcc;
	}

	public void setMsgcc(String msgcc) {
		this.msgcc = msgcc;
	}

	@Column(name = "MSGSENDER", length = 10)
	public String getMsgsender() {
		return this.msgsender;
	}

	public void setMsgsender(String msgsender) {
		this.msgsender = msgsender;
	}

	@Column(name = "MSGSTATUS", nullable = false, length = 3)
	public String getMsgstatus() {
		return this.msgstatus;
	}

	public void setMsgstatus(String msgstatus) {
		this.msgstatus = msgstatus;
	}

	@Column(name = "MSGBODYTEXT")
	public String getMsgbodytext() {
		return this.msgbodytext;
	}

	public void setMsgbodytext(String msgbodytext) {
		this.msgbodytext = msgbodytext;
	}

	@Column(name = "MSGTYPE", nullable = false, length = 3)
	public String getMsgtype() {
		return this.msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	@Column(name = "MSGCREATEDWHEN", length = 11)
	public Timestamp getMsgcreatedwhen() {
		return this.msgcreatedwhen;
	}

	public void setMsgcreatedwhen(Timestamp msgcreatedwhen) {
		this.msgcreatedwhen = msgcreatedwhen;
	}

	/**
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	
}