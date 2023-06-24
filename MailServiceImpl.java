package com.msl.mo.mail.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.msl.mo.mail.dao.MailDao;
import com.msl.mo.vo.mail.MailVO;

@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless(name="MailService", mappedName="ejb/MailService")
public class MailServiceImpl implements MailService{
	@EJB
	MailDao mailDao;
	public List<MailVO> getMessagesToSend()
	{
		return mailDao.getMessagesToSend();
	}
	public void updateStatusToSent(String msgId)
	{
		mailDao.updateStatusToSent(msgId);
	}
	public void insertMailRecord(MailVO mailVo)
	{
		mailDao.insertMailRecord(mailVo);
	}
}
