package com.simpolor.app.common.component;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailComponent {

	@Value("${mail.smtp.user}")
	private String user;
	
	@Value("${mail.smtp.host}")
	private String host;
	
	@Value("${mail.smtp.port}")
	private String port;
	
	@Value("${mail.address.from}")
	private String from;
	
	@Value("${mail.charset}")
	private String charset;
	
	@Value("${mail.gmail.account}")
	private String account;
	
	@Value("${mail.gmail.password}")
	private String password;
	
	public boolean mailSender(String recipient, String subject, String content){
        Properties prop = new Properties();
        prop.put("mail.smtp.user", user); // mail.smtp.user
        prop.put("mail.smtp.host", host); // mail.smtp.host
        prop.put("mail.smtp.port", port); // mail.smtp.port
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        //prop.put("mail.smtp.debug", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        
        try {
        	// GMail 인증
            Authenticator auth = new Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(account, password);
                }
            };
            Session session = Session.getInstance(prop, auth);
            // session.setDebug(true); // 메일을 전송할 때 상세한 상황을 콘솔에 출력
            
            MimeMessage msg = new MimeMessage(session);
            
            Address fromAddr = new InternetAddress(from);
            Address recipientAddr = new InternetAddress(recipient);
            
            /* Recipients ( 다수 발송 )
            InternetAddress[] toArray = new InternetAddress[3]; 
            toArray[0] = new InternetAddress("ktko0@ktko0.com"); 
            toArray[1] = new InternetAddress("ktko1@ktko1.com"); 
            toArray[2] = new InternetAddress("ktko2@ktko2.com"); 
            msg.addRecipients(Message.RecipientType.TO, toArray);*/
            
            msg.setFrom(fromAddr);
            msg.addRecipient(Message.RecipientType.TO, recipientAddr);
            //msg.setSubject(subject);
            //msg.setSubject(MimeUtility.encodeText(subject, "EUC-KR", "B"));
            //msg.setContent(content, "text/html; charset=EUC-KR");
            //msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B")); // B:Base64, Q:quoted-printable
            //msg.setContent(content, "text/html; charset=UTF-8");
            msg.setSubject(MimeUtility.encodeText(subject, charset, "B")); // B:Base64, Q:quoted-printable
            msg.setContent(content, "text/html; charset="+charset);
            
            Transport.send(msg);
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

