package cn.edu.nju.onlineexam.Util;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
/**
 * Created by mac on 2017/12/13.
 */
public class MailUtil {

    public static void send(String txt,String sub,String receiver) throws Exception{
        Properties props = new Properties();
        props.setProperty("mail.debug", "false");
        props.setProperty("mail.smtp.ssl.enable", "true");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.transport.protocol", "smtp");
        Session session = Session.getInstance(props);
        Message msg = new MimeMessage(session);
        msg.setSubject(sub);
        msg.setText(txt);
        msg.setFrom(new InternetAddress("zy812818@163.com"));
        Transport transport = session.getTransport();
        transport.connect("zy812818@163.com", "zhouyao123");
        transport.sendMessage(msg, new Address[] {new InternetAddress(receiver)});
        transport.close();
    }

    public static void main(String[] args) throws Exception{
        send("1","1","online_exam@126.com");
    }

}
