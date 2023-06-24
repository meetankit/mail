package com.msl.mo.mail;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.msl.mo.delegate.Delegate;
import com.msl.mo.vo.mail.MailVO;

/**
 * @author 196913
 *
 */
public class SendMail implements Serializable{

	/**
	 * default serial version id generated
	 */
	private static final long serialVersionUID = -391348408079556834L;
	private static Logger log = Logger.getLogger(SendMail.class);
	private static Folder inboxFolder = null;
	private static Folder processedFolder = null;
	Delegate delegate;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SendMail sm=new SendMail();
		sm.insert();
	}
	public void send()
	{
		// TODO Auto-generated method stub
		List<MailVO> msgTosend= new ArrayList<MailVO>();
		delegate = new Delegate();
		try{
		msgTosend = delegate.getMailService().getMessagesToSend();
		}catch (Exception e){
			log.info("Exception:"+e);
		}
		Session session = getMailSession();
		if (session != null) {
			// Create a default MimeMessage object.
			for(int i=0;i<msgTosend.size();i++)
			{MailVO mailVo=new MailVO();
			 String msg_html = "";

				try{
			MimeMessage message = new MimeMessage(session);
			message.setSentDate(new java.util.Date());
			// Set From: header field of the header.
			if (mailVo.getMsgsender() != null)
				message.setFrom(new InternetAddress(mailVo.getMsgsender()));
			
			//message.setFrom(new InternetAddress("testuser@motesting.com"));
			// Set To: header field of the header.
			if (mailVo.getMsgto() != null)
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(mailVo.getMsgto()));
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress("newuser@motesting.com"));
			// Set Cc: header field of the header.
			if (mailVo.getMsgcc() != null)
				message.addRecipient(Message.RecipientType.CC,new InternetAddress(mailVo.getMsgcc()));

			// Set Subject: header field
			message.setSubject(mailVo.getSubject());
			//message.setSubject("Phunsuk Wangdu Mantra");

			// Now set the actual message
			BodyPart bodyPart = new MimeBodyPart();
			MimeMultipart multipart = new MimeMultipart();
			//message.setText("Al is Wll");
			bodyPart.setText(mailVo.getMsgbodytext());
			multipart.addBodyPart(bodyPart);

            //setting body for message
			message.setContent(multipart);

			try {
              //static method to send mail
				Transport.send(message);

			} catch (Exception e){

				// wait 2 seconds and try again in case transport cannot connect to server

			    Thread.sleep(2000);

				try {

					Transport.send(message);

				} catch (Exception e2){

					// wait 2 seconds and try again in case transport cannot connect to server
					Thread.sleep(2000);

					try {
						Transport.send(message);

					} catch (Exception e3){
					    log.error("ERROR sending mail : " , e3);
					    return;
                          //return e3.getMessage();
					}

				}

			}
			

		} catch (Exception e){
			log.error("ERROR sending mail : " ,e);
			return;
               //  return e.getMessage();

		}
		try{
			delegate.getMailService().updateStatusToSent(mailVo.getMsgid());
		}catch (Exception e){
			log.info("Exception:"+e);
		}
					}
		}
		return;
	} 

	public void insert()
	{
		delegate = new Delegate();
		MailVO mailVo=new MailVO();
		String msgbodytext=new String();
		msgbodytext="RECEIVER: +442032176666"+"\n"+"WO: 999999999901"+"\n"+"REMARKS: CN updates rejected-Incorrect container no.";
		mailVo.setMsgbodytext(msgbodytext);
		java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
		mailVo.setMsgcreatedwhen(sqlDate);
		mailVo.setMsgsender("testuser@motesting.com");
		mailVo.setMsgto("newuser@motesting.com");
		mailVo.setMsgtype("Loginet");
		mailVo.setSubject("testing loginet");
		try{
			delegate.getMailService().insertMailRecord(mailVo);
		}catch (Exception e){
			log.info("Exception:"+e);
		}
	}
	public void receive()
	{
		//boolean lockAcquired = false;
	//	MailVO mailVO = new MailVO();
		try {
		//	if(mailVO.getAcquireProcessingLock()) {
				//lockAcquired = true;
				String emailServer = "localhost";
				String emailUser = "testuser";
				String emailPassword = "password";
				Store store = null;
				Folder failedFolder = null;
				log.debug("*********************Starting email processing******************************");
				try {
					Properties props = System.getProperties();
					Session session = Session.getDefaultInstance(props, null);
					store = session.getStore("pop3");
		
					try {
						store.connect(emailServer, emailUser, emailPassword);
					} catch(AuthenticationFailedException afe) {
						//Globals.logErrorMessageForRemedy(SendMail.class, Constants.MEDIUM_SEVERITY, Constants.ERROR_CODE_1001, "receive()", "Error connecting to the email server due to invalid authentication parameters");
						log.error("Exception in receive() method. Error connecting to the email server due to invalid authentication parameters.");
						throw new Exception("Invalid userid and/or password");
					} catch(Exception e) {
						//Globals.logErrorMessageForRemedy(SendMail.class, Constants.MEDIUM_SEVERITY, Constants.ERROR_CODE_1002, "receive()", "Error connecting to the email server " + e.toString());
						log.error("Exception in receive() method. Error connecting to the email server.");
						throw new Exception("Could not connect to the email server");
					}
					if(! store.isConnected()) {
						//Globals.logErrorMessageForRemedy(SendMail.class, Constants.MEDIUM_SEVERITY, Constants.ERROR_CODE_1002, "receive()", "Error connecting to the email server");
						log.error("Exception in receive() method. Error connecting to the email server.");
						throw new Exception("Could not connect to the email server");
					}
		            inboxFolder = store.getFolder("INBOX");
		            if (inboxFolder == null) { 
		            //Globals.logErrorMessageForRemedy(SendMail.class, Constants.MEDIUM_SEVERITY, Constants.ERROR_CODE_1003, "receive()", "Error locating the Inbox folder on the email server");
		            	log.error("Exception in receive() method. Error locating the Inbox folder on the email server.");
		            	throw new Exception("No Inbox folder");
		            }
		            try {
			            processedFolder = store.getFolder("Processed");
			            if (processedFolder == null || !processedFolder.exists()) { 
			            	processedFolder.create(Folder.HOLDS_MESSAGES);
			            }
			 
			            failedFolder = store.getFolder("Failed");
			            if (failedFolder == null || !failedFolder.exists()) { 
			            	failedFolder.create(Folder.HOLDS_MESSAGES);
			            }
			            
		            } catch (Exception e) {
		            	//Globals.logErrorMessageForRemedy(SendMail.class, Constants.MEDIUM_SEVERITY, Constants.ERROR_CODE_1004, "receive()", "Error locating or creating the necessary folders on the email server " + e.toString());
		            	log.error("Exception in receive() method. Error locating or creating the necessary folders on the email server.", e);
		            	throw new Exception("Cannot locate/create necessary folders");
		            }
		            try {
		            	inboxFolder.open(Folder.READ_WRITE);
		            } catch(Exception e) {
		            	//Globals.logErrorMessageForRemedy(SendMail.class, Constants.MEDIUM_SEVERITY, Constants.ERROR_CODE_1005, "receive()", "Error opening the Inbox folder in Read-Write mode " + e.toString());
		            	log.error("Exception in receive() method. Error opening the Inbox folder in Read-Write mode.", e);
		            	throw new Exception("Cannot open Inbox");
		            }
		            Message[] msgs = null;
		            try {
		            	msgs = inboxFolder.getMessages();
		            } catch(Exception e) {
		            	//Globals.logErrorMessageForRemedy(SendMail.class, Constants.MEDIUM_SEVERITY, Constants.ERROR_CODE_1006, "receive()", "Error reading the messages from the Inbox folder " + e.toString());
		            	log.error("Exception in receive() method. Error reading the messages from the Inbox folder.", e);
		            	throw new Exception("Cannot read messages from Inbox");
		            }
		            for (int msgNum = 0; msgNum < msgs.length; msgNum++) {
		            	Message message = msgs[msgNum];
		            	if(message.isSet(Flag.ANSWERED) && message.isSet(Flag.FLAGGED)) {
		            		log.error("Exception in receive() method. This email has already been successfully processed and hence skipping it. " +
		            						"Move the email manually to Processed folder. Email Subject: " + message.getSubject() + "Email Sender: " + ((InternetAddress)message.getFrom()[0]).getAddress());
		            		continue;
		            	}
		            	try {
		            		//String senderName = "";
		            		String senderEmail = "";
		            		String subject = "";
		            		String cc = "";
		            		boolean isLoginet = false;
		            		try {
			            	//	senderName = ((InternetAddress)message.getFrom()[0]).getPersonal();
			           			senderEmail = ((InternetAddress)message.getFrom()[0]).getAddress();
		           				
			           					//to check if mail is coming from loginet
			           					if(senderEmail.equalsIgnoreCase("loginet.com"))	{						
			           						isLoginet = true;
			           					} else {
			           						isLoginet = false;
			           					}
			           				}		           				
			           			 catch(Exception e) {
				            	log.error("Exception in receive() method. Error reading sender's information from the email.", e);
				            	throw new Exception("Cannot read sender's info from email");
		            		}
		           			if(isLoginet) {
		           				try {
				            		javax.mail.Address[] recipients = message.getRecipients(Message.RecipientType.CC);
				            		if(recipients != null) {
					            		for(int i = 0; i < recipients.length; i++) {
					            			javax.mail.Address recipient = recipients[i];
				            				String emailAddr = ((InternetAddress)recipient).getAddress();
				            				cc = cc.length() == 0 ?emailAddr:";" +emailAddr; 
					            		}
				            		}	
				            		subject = message.getSubject();
				            		Part messagePart = message;
				            //		Object content = messagePart.getContent();
				            		/*if(content instanceof Multipart) {
				            			Multipart tempContent = (Multipart)content;
				            			messagePart = getMessagePart(tempContent, "text/html");
				            			if(messagePart == null) {
				            				messagePart = getMessagePart(tempContent, "text/plain");
				            			}
				            		}
				            		if(messagePart == null) {
				            			messagePart = ((Multipart)content).getBodyPart(0);
				            			if(messagePart.getContentType().toLowerCase().startsWith("multipart")) {
				            				messagePart = ((Multipart)messagePart.getContent()).getBodyPart(0);
				            			}
				            		}*/
				            		String contentType=messagePart.getContentType();
				            		contentType = contentType == null ? new String("") : contentType.toLowerCase();
				            		if (contentType.startsWith("text/plain") || contentType.startsWith("text/html")) {
				            			InputStream is = messagePart.getInputStream();
				            			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				            			String thisLine=reader.readLine();
				                        String wo[]=thisLine.split(":"); 
				                        if (wo[0].equals(new String("wo")))
				                        {
				                        	//get workorderno. from wo[1]
				                        }
				                        thisLine=reader.readLine();
				                        String mobNo[]=thisLine.split(":"); 
				                        if (mobNo[0].equals(new String("address")))
				                        {
				                        	//get mobno from mobNo[1]
				                        }
				                        thisLine=reader.readLine();
				                        String dateVar=thisLine.substring(0, 3);
				                        if (dateVar.equals(new String("date")))
				                        {
				                        	//get date message is received
				                        	//String date=thisLine.substring(6);
				                        }
				                        thisLine=reader.readLine();
				                        String status[]=thisLine.split(":"); 
				                        if(status[0].equals(new String("status")))
				                        {
				                        	String changeField=status[1].substring(1, 2);
				                        	if(changeField.equals(new String("CN")))
				                        	{
				                        		 String CnNumber=status[1].substring(4);
				                        	}
				                        	else if (changeField.equals(new String("SN")))
				                        	{
				                        		String SealNumber=status[1].substring(4);
				                        	}
				                        	else if (changeField.equals(new String("AC")))
				                        	{
				                        		//WorkOrderNumber=wo[1].substring(4,12);
				                        		//seqno=wo[1].substring(13);
				                        		String ArrivalDate=status[1].substring(4);
				                        		if (ArrivalDate.length()==8)
				                        		{
				                        			ArrivalDate=ArrivalDate.concat("0000");
				                        		}
				                        	}
				                        }
				            		}   
				            		moveEmailItemToProcessed(message);
				            	} catch(Exception e) {
						           	log.error("Exception in receive() method. Error parsing the content of the email.", e);
						           	throw new Exception("Cannot parse the email");
				            	}
			                    	
		           			}	
		            	} catch(Exception e) {
		            		//Globals.logErrorMessageForRemedy(SendMail.class, Constants.LOW_SEVERITY, Constants.ERROR_CODE_1007, "receive()", "Error processing the email, moving the email to Failed folder " + e.toString());
	            			log.error("Exception in receive() method. Error processing the email, moving the email to Failed folder", e);
	            			moveEmailItemToFolder(message, failedFolder);
		            	}
		            }
				} catch (Exception ex) {
		            log.error("Exception in receive() method", ex);
		        } finally {
		        	try {
		        		if (inboxFolder!=null) {
		        			inboxFolder.close(true);
		            	}
		        		log.debug("*********************Ending email processing******************************");
		            } catch (Exception ex2) {
		            	log.error("Exception in receive() method", ex2);
		            }
		        }
		//	}
		} catch(Exception e) {
			log.error("Exception in receive() method", e);
		} finally {
			try {
				/*if(lockAcquired) {
			        if(! appVariablesDAO.releaseProcessingLock()) {
			        	Thread.sleep(3000);
			        	appVariablesDAO.releaseProcessingLock();
			        }
				}*/    
			} catch (Exception e) {
	           	log.error("Exception in receive() method", e);
	        }
		}
	}
	/**
	 * This method returns session for Java Mail
	 * 
	 * @return Session
	 */
private Session getMailSession() {
		// Set properties
		Properties props = new Properties();

	//	props.setProperty(Constants.MAIL_SMTP_HOST, ConfigurationManager
		//		.getInstance().getProperty(Constants.SMTP_HOST));
		props.setProperty("mail.smtp.host","localhost");
		Session mailSession = null;
	
			mailSession = Session.getDefaultInstance(props);
		return mailSession;
	}
private static Part getMessagePart(Multipart tempContent, String partType) throws Exception {
	Part returnPart = null;
	for(int i = 0; i < tempContent.getCount(); i++) {
		if(tempContent.getBodyPart(i).getContent() instanceof Multipart) {
			returnPart = getMessagePart((Multipart)tempContent.getBodyPart(i).getContent(), partType);
		} else if(tempContent.getBodyPart(i) instanceof Part) {
			if(tempContent.getBodyPart(i).getContentType().toLowerCase().startsWith(partType)) {
				returnPart = tempContent.getBodyPart(i);
			} else if(tempContent.getBodyPart(i).getContentType().toLowerCase().startsWith("multipart")) {
				returnPart = getMessagePart((Multipart)tempContent.getBodyPart(i).getContent(), partType);
			}
		}
	}
	return returnPart;
}
public static boolean moveEmailItemToFolder(Message message, Folder destFolder) throws Exception {
	   boolean returnVal = false;
	   try {
   	   inboxFolder.copyMessages(new Message[] {message}, destFolder);
 	   message.setFlag(Flag.DELETED, true);
 	   inboxFolder.expunge();
 	   returnVal = true;
	   } catch(Exception e) {
		   log.error("Exception in moveEmailItemToFolder() method. Error moving the email to " + destFolder.getName() + " folder." + 
				   " Email Subject: " + message.getSubject() + " Email Sender: " + ((InternetAddress)message.getFrom()[0]).getAddress(), e);
	   }
	   return returnVal;
	}
	
	public static boolean moveEmailItemToProcessed(Message message) throws Exception {
		return moveEmailItemToFolder(message, processedFolder);
	}


}
