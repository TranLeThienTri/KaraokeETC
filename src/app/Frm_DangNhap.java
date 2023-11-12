package app;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Canvas;
import javax.swing.JSlider;
import javax.swing.JTextField;

import java.awt.SystemColor;
import java.awt.CardLayout;
import javax.swing.JDesktopPane;
import java.awt.FlowLayout;
import java.awt.TextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuListener;

import connectDB.ConnectDB;
import dao.DanhSachNhanVien;
import dao.DanhSachTaiKhoan;
import entitys.NhanVien;
import entitys.TaiKhoan;

import java.awt.Cursor;
import java.awt.ComponentOrientation;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Frm_DangNhap extends JFrame implements MouseListener, ActionListener {
	private JButton btnDangNhap;
	private JPanel formDangNhap, pnTaiKhoan;
	private JTextField txtTaiKhoan;
	private JPasswordField pwdNv;
	private JLabel ngayLabel;
	private JLabel lblNewLabel;
	private Frm_Chinh frmChinh;
	private JLabel lbQuenMatKhau;
	public  TaiKhoan tk;
	private DanhSachTaiKhoan dsTk;
	private DanhSachNhanVien dsNV;

	public Frm_DangNhap() {
		ConnectDB.getInstance().connect();
		frmChinh = new Frm_Chinh();
		getContentPane().setBackground(SystemColor.controlHighlight);
		setTitle("Đăng Nhập");
		setSize(800, 500);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		dsTk = new DanhSachTaiKhoan();
		dsNV = new DanhSachNhanVien();
		showGui();
	}

	private void formDangNhap() {
		btnDangNhap();

		JPanel pnTaiKhoan = new JPanel();
		pnTaiKhoan.setBackground(new Color(255, 255, 255, 0));
		pnTaiKhoan.setLayout(null);
		pnTaiKhoan.setBounds(35, 78, 549, 51);
		formDangNhap.add(pnTaiKhoan);

		JLabel lbTenTaiKhoan_1 = new JLabel("Tài Khoản:");
		lbTenTaiKhoan_1.setForeground(new Color(255, 255, 255));
		lbTenTaiKhoan_1.setBounds(0, 7, 140, 36);
		pnTaiKhoan.add(lbTenTaiKhoan_1);
		lbTenTaiKhoan_1.setFont(new Font("Tahoma", Font.BOLD, 22));

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setText("NV001");
		txtTaiKhoan.setIgnoreRepaint(true);
		txtTaiKhoan.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtTaiKhoan.setBackground(SystemColor.window);
		txtTaiKhoan.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtTaiKhoan.setBounds(152, 7, 373, 36);
		pnTaiKhoan.add(txtTaiKhoan);

		JPanel pnMatKhau = new JPanel();
		pnMatKhau.setBackground(new Color(255, 255, 255, 0));
		pnMatKhau.setLayout(null);
		pnMatKhau.setBounds(35, 151, 549, 51);
		formDangNhap.add(pnMatKhau);

		JLabel lbMatKhau = new JLabel("Mật Khẩu:");
		lbMatKhau.setForeground(new Color(255, 255, 255));
		lbMatKhau.setBackground(new Color(240, 240, 240));
		lbMatKhau.setBounds(2, 7, 140, 36);
		pnMatKhau.add(lbMatKhau);
		lbMatKhau.setFont(new Font("Tahoma", Font.BOLD, 22));

		pwdNv = new JPasswordField();
		pwdNv.setText("nv001");
		pwdNv.setBackground(SystemColor.window);
		pwdNv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pwdNv.setBounds(152, 7, 373, 36);
		pnMatKhau.add(pwdNv);

		lblNewLabel = new JLabel("FORM LOGIN");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(202, 23, 215, 45);
		formDangNhap.add(lblNewLabel);

		lbQuenMatKhau = new JLabel("Quên mật khẩu?");
		lbQuenMatKhau.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lbQuenMatKhau.setBounds(428, 212, 139, 25);
		formDangNhap.add(lbQuenMatKhau);
		lbQuenMatKhau.addMouseListener(this);

	}

	private void btnDangNhap() {
		btnDangNhap = new FixButton("Đăng Nhập");
		btnDangNhap.setFocusPainted(false);
		btnDangNhap.setHideActionText(true);
		btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnDangNhap.setBounds(224, 257, 171, 51);
		formDangNhap.add(btnDangNhap);
	}

	private void opacity() {
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(117, 154, 169, 100)); // 100 là alpha
		panelHeader.setBounds(0, 0, 800, 85);

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
		formDangNhap = new JPanel();
		formDangNhap.setBounds(83, 83, 620, 331);
		pnBGR.add(formDangNhap);
		formDangNhap.setBackground(new Color(192, 192, 192, 100));
		formDangNhap.setLayout(null);

		ImageIcon originalIcon = new ImageIcon(Frm_DangNhap.class.getResource("/imgs/bg_chot1.png"));
		Image image = originalIcon.getImage();
		// resize lại ảnh
		Image resizedImage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
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
		Date ngayHienTai = new Date();
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
		// form
		formDangNhap();

		btnDangNhap.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDangNhap)) {
			dangNhap();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == lbQuenMatKhau) {
			quenMatKhau();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		btnDangNhap.setBackground(new Color(117, 154, 169, 100));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void dangNhap() {
		String maNV = txtTaiKhoan.getText().toString().trim();
		String mk = pwdNv.getText().toString().trim();
		tk = dsTk.getTaiKhoanTheoMa(maNV);
		if (tk == null) {
			JOptionPane.showMessageDialog(this, "Tài khoản không đúng!\nVui lòng kiểm tra lại.");
			txtTaiKhoan.requestFocus();
		} else if (!tk.getMatKhau().equals(mk)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không đúng!\nVui lòng kiểm tra lại.");
			pwdNv.requestFocus();
		} else {
			NhanVien nv = dsNV.getNhanVienTheoMa(maNV);
			frmChinh.addInfoStaff(nv);
			Frm_Chinh frm_Chinh = new Frm_Chinh(nv);
			frm_Chinh.setVisible(true);
			this.setVisible(false);
		}
	}

	public void quenMatKhau() {
		String maNV = txtTaiKhoan.getText().toString().trim();
		String mk = pwdNv.getText().toString().trim();
		tk = dsTk.getTaiKhoanTheoMa(maNV);
		Frm_GuiMail frmSendMail = new Frm_GuiMail(tk);
		frmSendMail.setVisible(true);
		this.setVisible(false);
	}
	public static void main(String[] args) {
		new Frm_DangNhap().setVisible(true);
	}
}