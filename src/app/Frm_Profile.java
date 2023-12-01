package app;

import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.DanhSachTaiKhoan;
import entitys.NhanVien;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Dimension;

public class Frm_Profile extends JFrame implements ActionListener {
	private JLabel lbBGQLDP, lbBG;
	private JPanel pnBGR;
	private JPanel pnInfo;
	private NhanVien nv;

	Date currentDate;
	int ngay, thang, nam;
	FixButton btnDoiPass, btnQuayLai;
	private DanhSachTaiKhoan dsTK;

	public JPanel getFrm_Profile() {
		return this.pnInfo;
	}

	public Frm_Profile(NhanVien nv) {
		this.nv = nv;
		setTitle("PROFILE");
		setSize(971, 648);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		gui();
	}

	public void gui() {

		getContentPane().setLayout(null);

		pnBGR = new JPanel();
		pnBGR.setBounds(0, 0, 971, 633);
		getContentPane().add(pnBGR);
		pnBGR.setLayout(null);

		

	
		ImageIcon originalIcon = new ImageIcon(Frm_DoiMatKhau.class.getResource("/imgs/bg_chot1.png"));
		Image image = originalIcon.getImage();
		// resize lại ảnh
		Image resizedImage = image.getScaledInstance(971, 633, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		pnBGR.setLayout(null);

		btnDoiPass = new FixButton("Đổi Mật Khẩu");
		btnDoiPass.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/btn_lammoi.png")));
		btnDoiPass.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDoiPass.setBounds(693, 530, 205, 46);
		pnBGR.add(btnDoiPass);

		btnQuayLai = new FixButton("Xác Nhận");
		btnQuayLai.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/btn_quaylai.png")));
		btnQuayLai.setText("Quay Lại");
		btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnQuayLai.setBounds(417, 530, 215, 46);
		pnBGR.add(btnQuayLai);

		pnInfo = new JPanel();
		pnInfo.setBackground(new Color(51, 204, 255));
		pnInfo.setBounds(370, 90, 571, 408);
		pnBGR.add(pnInfo);
		pnInfo.setLayout(null);

		JLabel lbSDT = new JLabel("   Số Điện Thoại:");
		lbSDT.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/phone.png")));
		lbSDT.setBounds(10, 154, 191, 32);
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnInfo.add(lbSDT);

		JLabel lbCCCD = new JLabel("   Số CCCD:");
		lbCCCD.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/id-card.png")));
		lbCCCD.setBounds(10, 92, 156, 32);
		lbCCCD.setForeground(Color.WHITE);
		lbCCCD.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnInfo.add(lbCCCD);

		JLabel lbNgaySinh = new JLabel("  Ngày Sinh:");
		lbNgaySinh.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/icon_calender.png")));
		lbNgaySinh.setBounds(0, 338, 191, 37);
		lbNgaySinh.setForeground(Color.WHITE);
		lbNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnInfo.add(lbNgaySinh);

		JLabel lbDiaChi = new JLabel("   Địa Chỉ:");
		lbDiaChi.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/location.png")));
		lbDiaChi.setBounds(10, 216, 156, 32);
		lbDiaChi.setForeground(Color.WHITE);
		lbDiaChi.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnInfo.add(lbDiaChi);

		JLabel lbGioiTinh = new JLabel("   Giới Tính:");
		lbGioiTinh.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/gender-fluid.png")));
		lbGioiTinh.setBounds(10, 278, 160, 32);
		lbGioiTinh.setForeground(Color.WHITE);
		lbGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnInfo.add(lbGioiTinh);

		JLabel lbHoTen = new JLabel("   Họ Và Tên:");
		lbHoTen.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/contract.png")));
		lbHoTen.setBounds(10, 30, 156, 32);
		lbHoTen.setForeground(Color.WHITE);
		lbHoTen.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnInfo.add(lbHoTen);

		JLabel lblTnN = new JLabel(nv.getHoTenNhanVien().toString());
		lblTnN.setForeground(Color.WHITE);
		lblTnN.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTnN.setBounds(225, 30, 313, 32);
		pnInfo.add(lblTnN);

		JLabel lblCccd = new JLabel(nv.getSoCCCD().toString());
		lblCccd.setForeground(Color.WHITE);
		lblCccd.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCccd.setBounds(225, 92, 313, 37);
		pnInfo.add(lblCccd);

		JLabel lblSdt = new JLabel(nv.getSdt().toString());
		lblSdt.setForeground(Color.WHITE);
		lblSdt.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSdt.setBounds(225, 154, 313, 32);
		pnInfo.add(lblSdt);

		JLabel lblDiaChi = new JLabel(nv.getDiaChi().toString());
		lblDiaChi.setForeground(Color.WHITE);
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDiaChi.setBounds(225, 216, 313, 32);
		pnInfo.add(lblDiaChi);

		JLabel lblGioiTinh = new JLabel(nv.isGioiTinh() ? "Nam" : "Nữ");
		lblGioiTinh.setForeground(Color.WHITE);
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGioiTinh.setBounds(225, 278, 102, 32);
		pnInfo.add(lblGioiTinh);

		JLabel lblNgaySinh = new JLabel(nv.getNgaySinh().toString());
		lblNgaySinh.setForeground(Color.WHITE);
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNgaySinh.setBounds(225, 340, 313, 32);
		pnInfo.add(lblNgaySinh);

		JLabel lbIMG = new JLabel("");
		lbIMG.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/repair.png")));
		lbIMG.setBounds(0, 0, 350, 604);
		pnBGR.add(lbIMG);

		ImageIcon originalIconRP = new ImageIcon(Frm_DoiMatKhau.class.getResource("/imgs/repair.png"));
		Image imagerp = originalIconRP.getImage();
		// resize lại ảnh
		Image resizedImageRp = imagerp.getScaledInstance(350, 633, Image.SCALE_SMOOTH);
		ImageIcon resizedIconRP = new ImageIcon(resizedImageRp);
		pnBGR.setLayout(null);

		JLabel lbHeader = new JLabel("EDIT PROFILE");
		lbHeader.setBounds(390, 26, 190, 29);
		pnBGR.add(lbHeader);
		lbHeader.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lbHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setForeground(new Color(255, 255, 255));
		lbHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		// add ảnh
		JLabel lbBGR = new JLabel(resizedIcon);
		lbBGR.setBounds(0, 0, 957, 623);
		pnBGR.add(lbBGR);
		dsTK = new DanhSachTaiKhoan();
		btnDoiPass.addActionListener(this);
		btnQuayLai.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnQuayLai) {
			dispose();
		} else {
			this.dispose();
			doiPass();
		}
	}
	
	
	private void doiPass() {
		new Frm_DoiMatKhau(dsTK.getTaiKhoanTheoMa(nv.getMaNhanVien())).setVisible(true);
	}
}
