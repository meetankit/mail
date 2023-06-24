package com.msl.mo.mail.dao;

import java.util.List;

import javax.ejb.Local;

import com.msl.mo.vo.mail.MailVO;

@Local
public interface MailDao {
	public List<MailVO> getMessagesToSend();
	public void updateStatusToSent(String msgId);
	public void insertMailRecord(MailVO mailVo);
}
