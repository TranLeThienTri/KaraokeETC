package mail;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;



import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;

public class Config {
	static String to;//nhập mail người nhận từ đây
	
	public Config(String to) {
		super();
		this.setTo(to);
	}
	
	
	public void setTo(String to) {
		this.to = to;
	}


	// Config: tungletest1.Config@gmail.com
	// Password: zblv oyaq rgyl qriq
	static final String from = "duyngayxua105@gmail.com";
	static final String password = "zblvoyaqrgylqriq";
	public static void main(String[] args) {
		// Properties : khai báo các thuộc tính
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
		props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		// create Authenticator
		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, password);
			}
			
		};
		
		// Phiên làm việc
		Session session = Session.getInstance(props, auth);
	
		// Tạo một tin nhắn
		MimeMessage msg = new MimeMessage(session);

		try {
			// Kiểu nội dung
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

			// Người gửi
			msg.setFrom(from);

			// Người nhận
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

			// Tiêu đề Config
			msg.setSubject("Sử dụng OTP để lấy lại mật khẩu!!");

			// Quy đinh ngày gửi
			msg.setSentDate(new Date());

			// Quy định Config nhận phản hồi
			// msg.setReplyTo(InternetAddress.parse(from, false))

			RandomNumber r = new RandomNumber();
			String rd =r.getSoNgauNhien();
			
			// Nội dung
			msg.setContent(rd, "text/HTML; charset=UTF-8");

			// Gửi Config
			Transport.send(msg);
			System.out.println("Gửi mail thành công");
		} catch (Exception e) {
			System.out.println("Gặp lỗi trong quá trình gửi Config");
			e.printStackTrace();
		}
	}

	

}