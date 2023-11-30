
package app;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import dao.DanhSachNhanVien;
import dao.DanhSachTaiKhoan;
import entitys.NhanVien;
import entitys.TaiKhoan;
import mail.Config;

import javax.swing.UIManager;
import org.apache.poi.ss.formula.functions.T;

public class Frm_NhapOTP extends JFrame implements ActionListener {
	private JButton btnSend;
	private JPanel pnSendOTP;
	private JTextField txtOtp;
	private JLabel ngayLabel;
	private Date ngayHienTai;
	ImageIcon originalIcon, resizedIcon;
	Image image, resizedImage;
	String otp;
	public TaiKhoan tk;
	public JPanel get_FrmSendMail() {
		return this.pnSendOTP;
	}

	public Frm_NhapOTP(TaiKhoan tk,String otp) {
		this.tk =tk;
		this.otp = otp;
		getContentPane().setBackground(SystemColor.controlHighlight);
		setTitle("Nhập OTP");
		setSize(800, 500);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		showGui();
	}

	private void formSendMail() {
		btnDangNhap();

		JLabel lbTextNoti1 = new JLabel("Mã xác thực OTP đã được gửi đến email của bạn,");
		lbTextNoti1.setForeground(new Color(255, 255, 255));
		lbTextNoti1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbTextNoti1.setBackground(UIManager.getColor("Button.background"));
		lbTextNoti1.setBounds(48, 98, 562, 36);
		pnSendOTP.add(lbTextNoti1);

		JLabel lbOTP = new JLabel("OTP:");
		lbOTP.setBounds(48, 208, 90, 36);
		pnSendOTP.add(lbOTP);
		lbOTP.setForeground(new Color(255, 255, 255));
		lbOTP.setBackground(new Color(240, 240, 240));
		lbOTP.setFont(new Font("Tahoma", Font.BOLD, 22));

		txtOtp = new JTextField();
		txtOtp.setBounds(48, 239, 316, 47);
		pnSendOTP.add(txtOtp);
		txtOtp.setIgnoreRepaint(true);
		txtOtp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtOtp.setBackground(SystemColor.window);
		txtOtp.setFont(new Font("Dialog", Font.PLAIN, 20));

		JLabel lbTextNoti2 = new JLabel("vui lòng kiểm tra và xác thực!");
		lbTextNoti2.setForeground(Color.WHITE);
		lbTextNoti2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbTextNoti2.setBackground(UIManager.getColor("Button.background"));
		lbTextNoti2.setBounds(48, 144, 539, 36);
		pnSendOTP.add(lbTextNoti2);

	}

	private void opacity() {
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(117, 154, 169, 100)); // 100 là alpha
		panelHeader.setBounds(0, 0, 800, 85);
	}

	private void btnDangNhap() {
		JLabel lbIndexOTP = new JLabel("OTP");
		lbIndexOTP.setBackground(new Color(192, 192, 192));
		lbIndexOTP.setForeground(new Color(255, 255, 255));
		lbIndexOTP.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbIndexOTP.setBounds(278, 21, 64, 45);
		pnSendOTP.add(lbIndexOTP);
		btnSend = new FixButton("Đăng Nhập");
		btnSend.setText("Gửi");
		btnSend.setFocusPainted(false);
		btnSend.setHideActionText(true);
		btnSend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnSend.setBounds(422, 242, 148, 46);
		pnSendOTP.add(btnSend);
	}

	// set background
	private void setBgr() {
		lbNgayHienTai();
		opacity();
		getContentPane().setLayout(null);
		JPanel pnBGR = new JPanel();
		pnBGR.setBounds(0, 0, 786, 463);
		getContentPane().add(pnBGR);

		pnBGR.setLayout(null);
		pnSendOTP = new JPanel();
		pnSendOTP.setBounds(83, 83, 620, 331);
		pnBGR.add(pnSendOTP);
		pnSendOTP.setBackground(new Color(192, 192, 192, 150));
		pnSendOTP.setLayout(null);

		originalIcon = new ImageIcon(Frm_DangNhap.class.getResource("/imgs/bg_chot1.png"));
		Image image = originalIcon.getImage();
		// resize lại ảnh
		resizedImage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
		resizedIcon = new ImageIcon(resizedImage);
		pnBGR.setLayout(null);

		// add ảnh
		JLabel lbBGR = new JLabel(resizedIcon);
		lbBGR.setBounds(0, 0, 800, 500);
		pnBGR.add(lbBGR);
	}

	// Hiển thị ngày hiện tại
	public void lbNgayHienTai() {
		ngayLabel = new JLabel();
		ngayLabel.setForeground(SystemColor.text);
		ngayLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		ngayHienTai = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String ngayHienTaiChuoi = sdf.format(ngayHienTai);
		ngayLabel.setText(ngayHienTaiChuoi);
		ngayLabel.setBounds(310, 0, 166, 81);
		getContentPane().add(ngayLabel);
	}

	public void showGui() {
		getContentPane().setLayout(null);
		// bgr
		setBgr();
		formSendMail();
		btnSend.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnSend) {
			if(ktraDuLieu()) {
				compareOTP();				
			}
		}
	}

	public void compareOTP() {
		String s = txtOtp.getText().trim();
		if(s.equalsIgnoreCase(otp)) {
			Frm_DoiMatKhau frmDoiMatKhau = new Frm_DoiMatKhau(tk);
			frmDoiMatKhau.setVisible(true);
			this.setVisible(false);
		}else {
			txtOtp.setText("Nhập sai OTP rồi ông cóc");
		}
			
	}

	public boolean ktraDuLieu() {
		String otp = txtOtp.getText();
		if (otp.equals("") || !otp.matches(
				"\\d{6}")) {
			JOptionPane.showMessageDialog(this, "OTP không được Trống và phải là số!!");
			txtOtp.requestFocus();
			return false;
		}
		return true;
	}
	
	public void xoaTrang() {
		txtOtp.setText("");
	}
}
