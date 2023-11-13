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

public class Frm_GuiMail extends JFrame implements ActionListener {
	private JButton btnDangNhap;
	private JPanel pnSendMail;
	private JTextField txtTaiKhoan;
	private JLabel ngayLabel;
	Date ngayHienTai;
	ImageIcon originalIcon, resizedIcon;
	JLabel lbQuenMatKhau;
	Image image, resizedImage;
	public TaiKhoan tk;
	public JPanel get_FrmSendMail() {
		return this.pnSendMail;
	}

	public Frm_GuiMail(TaiKhoan tk) {
		this.tk = tk;
		getContentPane().setBackground(SystemColor.controlHighlight);
		setTitle("Gửi Mail");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		showGui();
	}


	private void formSendMail() {
		btnDangNhap();

		JLabel lbMatKhau_1 = new JLabel("");
		lbMatKhau_1.setIcon(new ImageIcon(Frm_GuiMail.class.getResource("/imgs/icon_lock.png")));
		lbMatKhau_1.setForeground(Color.WHITE);
		lbMatKhau_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbMatKhau_1.setBackground(UIManager.getColor("Button.background"));
		lbMatKhau_1.setBounds(38, 70, 64, 72);
		pnSendMail.add(lbMatKhau_1);

		lbQuenMatKhau = new JLabel("Quên mật khẩu?");
		lbQuenMatKhau.setForeground(SystemColor.window);
		lbQuenMatKhau.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbQuenMatKhau.setBackground(UIManager.getColor("Button.background"));
		lbQuenMatKhau.setBounds(112, 70, 196, 72);
		pnSendMail.add(lbQuenMatKhau);

		JLabel lbTextNoti = new JLabel("Vui lòng nhập email để lấy mã xác thực OTP!");
		lbTextNoti.setForeground(new Color(255, 255, 255));
		lbTextNoti.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbTextNoti.setBackground(UIManager.getColor("Button.background"));
		lbTextNoti.setBounds(48, 162, 522, 36);
		pnSendMail.add(lbTextNoti);

		JLabel lbEmail = new JLabel("Email:");
		lbEmail.setBounds(48, 208, 90, 36);
		pnSendMail.add(lbEmail);
		lbEmail.setForeground(new Color(255, 255, 255));
		lbEmail.setBackground(new Color(240, 240, 240));
		lbEmail.setFont(new Font("Tahoma", Font.BOLD, 22));

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setBounds(48, 239, 316, 47);
		pnSendMail.add(txtTaiKhoan);
		txtTaiKhoan.setIgnoreRepaint(true);
		txtTaiKhoan.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtTaiKhoan.setBackground(SystemColor.window);
		txtTaiKhoan.setFont(new Font("Dialog", Font.PLAIN, 20));

	}

	private void opacity() {
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(117, 154, 169, 100)); // 100 là alpha
		panelHeader.setBounds(0, 0, 800, 85);

	}

	private void btnDangNhap() {
		JLabel lbSendMail = new JLabel("SEND MAIL");
		lbSendMail.setBackground(new Color(192, 192, 192));
		lbSendMail.setForeground(new Color(255, 255, 255));
		lbSendMail.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbSendMail.setBounds(218, 21, 183, 45);
		pnSendMail.add(lbSendMail);
		btnDangNhap = new FixButton("Đăng Nhập");
		btnDangNhap.setText("Gửi");
		btnDangNhap.setFocusPainted(false);
		btnDangNhap.setHideActionText(true);
		btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnDangNhap.setBounds(422, 242, 148, 46);
		pnSendMail.add(btnDangNhap);
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
		pnSendMail = new JPanel();
		pnSendMail.setBounds(83, 83, 620, 331);
		pnBGR.add(pnSendMail);
		pnSendMail.setBackground(new Color(192, 192, 192, 150));
		pnSendMail.setLayout(null);

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
		btnDangNhap.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnDangNhap) {
			sendMail();
		}
	}

	public void sendMail() {
		String mail = txtTaiKhoan.getText().trim();
		Config sendMail = new Config(mail);
		sendMail.main(null);// send mail
		String rd = sendMail.getRd();

		xoaTrang();
		Frm_NhapOTP frmOtp = new Frm_NhapOTP(tk,rd);
		frmOtp.setVisible(true);
		this.setVisible(false);
	}

	public void xoaTrang() {
		txtTaiKhoan.setText("");
	}
}
