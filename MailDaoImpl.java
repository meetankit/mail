package com.msl.mo.mail.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.msl.mo.db.entities.MoMail;
import com.msl.mo.utility.Constants;
import com.msl.mo.vo.mail.MailVO;

@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless(name="MailDao", mappedName="ejb/MailDao")
public class MailDaoImpl implements MailDao {
	@PersistenceContext(unitName="monlinePU")
	private EntityManager em;
	private static Logger log = Logger.getLogger(MailDaoImpl.class);
	public List<MailVO> getMessagesToSend()
	{
		List<MailVO> mailList =new ArrayList<MailVO>();
		List<MoMail> list=new ArrayList<MoMail>();
		Session session=(Session)em.getDelegate();
		try{
				Criteria criteria = session.createCriteria(MoMail.class);
				criteria.add(Restrictions.eq("msgstatus",Constants.MAIL_NOT_SENT));
				list =criteria.list();
				for(int i=0; i<list.size(); i++)
				{
					MailVO mailVo=new MailVO();
					mailVo.setMsgbodytext(list.get(i).getMsgbodytext());
					if(!list.get(i).getMsgcc().equals(null))
					{
					mailVo.setMsgcc(list.get(i).getMsgcc());
					}
					mailVo.setMsgid(list.get(i).getMsgid());
					mailVo.setMsgsender(list.get(i).getMsgsender());
					mailVo.setMsgto(list.get(i).getMsgto());
					mailVo.setMsgtype(list.get(i).getMsgtype());
					mailVo.setSubject(list.get(i).getSubject());
					mailList.add(mailVo);
				}
		}catch (Exception e){
					log.info("Exception:"+e);
				}
		
		return mailList;
	}
	public void updateStatusToSent(String msgId)
	{
		Session session=(Session)em.getDelegate();
		try{
		Criteria criteria = session.createCriteria(MoMail.class);
		criteria.add(Restrictions.eq("msgid",msgId));
		List<MoMail> list =criteria.list();
		list.get(0).setMsgstatus(Constants.MAIL_SENT);
		session.saveOrUpdate(list.get(0));
		}catch (Exception e){
			log.info("Exception:"+e);
		}
	}
	public void insertMailRecord(MailVO mailVo)
	{
		Session session = (Session) em.getDelegate();
		MoMail moMail=new MoMail();
		moMail.setMsgbodytext(mailVo.getMsgbodytext());
		if(mailVo.getMsgcc()!=null)
		moMail.setMsgcc(mailVo.getMsgcc());
		String reqId=UUID.randomUUID().toString().replace("-", "").toUpperCase();
		moMail.setMsgid(reqId);
		moMail.setMsgsender(mailVo.getMsgsender());
		moMail.setMsgstatus(Constants.MAIL_NOT_SENT);
		moMail.setMsgto(mailVo.getMsgto());
		moMail.setMsgtype(mailVo.getMsgtype());
		moMail.setMsgcreatedwhen(mailVo.getMsgcreatedwhen());
		session.save(moMail);
	}
	
}
