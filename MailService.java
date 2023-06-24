package com.msl.mo.mail.service;

import java.util.List;

import javax.ejb.Remote;

import com.msl.mo.utility.MOnlineService;
import com.msl.mo.vo.mail.MailVO;

@Remote
public interface MailService extends MOnlineService {
	public List<MailVO> getMessagesToSend();
	public void updateStatusToSent(String msgId);
	public void insertMailRecord(MailVO mailVo);
}
