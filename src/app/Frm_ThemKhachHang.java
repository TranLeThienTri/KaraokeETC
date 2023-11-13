package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.TitledBorder;

public class Frm_ThemKhachHang extends JFrame implements ActionListener {
	Panel pnThemKhachHang;
	private DefaultTableModel model;
	private JTextField txtKhachHang, txtSDT;
	private JPanel pnTTDDP;
	private JLabel lbGioiTinh, lbTenKH, lbBGQLDV, lbThemKH;;
	private JTextField txtTinhTrang, txtLoaiPhong, txtGiaPhong;
	FixButton btnHuy, btnChuyen;
	private JTextField txtCCCD;
	private JTextField txtGioiTinh;
	private JComboBox comboGT;

	public Panel getFrmThemKhachHang() {
		return this.pnThemKhachHang;
	}

	public Frm_ThemKhachHang() {
		setTitle("THÊM KHÁCH HÀNG");
		setSize(1000, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnThemKhachHang = new Panel();
		pnThemKhachHang.setBounds(0, 0, 1000, 500);
		getContentPane().add(pnThemKhachHang);
		pnThemKhachHang.setLayout(null);

		lbThemKH = new JLabel("THÊM KHÁCH HÀNG");
		lbThemKH.setForeground(Color.WHITE);
		lbThemKH.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbThemKH.setBounds(370, 52, 465, 50);
		pnThemKhachHang.add(lbThemKH);

		pnTTDDP = new JPanel();
		pnTTDDP.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 8), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnTTDDP.setLayout(null);
		pnTTDDP.setBackground(new Color(189, 0, 88));
		pnTTDDP.setBounds(34, 100, 926, 269);
		pnThemKhachHang.add(pnTTDDP);

		lbGioiTinh = new JLabel("Giới tính: ");
		lbGioiTinh.setForeground(Color.WHITE);
		lbGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioiTinh.setBounds(519, 99, 150, 25);
		pnTTDDP.add(lbGioiTinh);
		
		

		lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(21, 47, 200, 25);
		pnTTDDP.add(lbTenKH);

		txtSDT = new JTextField();
		txtSDT.setBounds(654, 47, 250, 30);
		pnTTDDP.add(txtSDT);

		txtKhachHang = new JTextField();
		txtKhachHang.setBounds(171, 47, 250, 30);
		pnTTDDP.add(txtKhachHang);

		JLabel lbCCCD = new JLabel("CCCD:");
		lbCCCD.setForeground(Color.WHITE);
		lbCCCD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbCCCD.setBounds(21, 99, 200, 25);
		pnTTDDP.add(lbCCCD);

		txtCCCD = new JTextField();
		txtCCCD.setBounds(171, 99, 250, 30);
		pnTTDDP.add(txtCCCD);

		JLabel lbSDT_1 = new JLabel("Số điện thoại:");
		lbSDT_1.setForeground(Color.WHITE);
		lbSDT_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT_1.setBounds(519, 47, 150, 25);
		pnTTDDP.add(lbSDT_1);
		
		comboGT = new JComboBox();
		comboGT.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		comboGT.setSelectedIndex(0);
		comboGT.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboGT.setBounds(654, 94, 250, 30);
		pnTTDDP.add(comboGT);

		btnHuy = new FixButton("Hủy đặt phòng");
		btnHuy.setBounds(57, 412, 140, 40);
		pnThemKhachHang.add(btnHuy);
		btnHuy.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_huydv.png")));
		btnHuy.setText("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnChuyen = new FixButton("Làm mới");
		btnChuyen.setBounds(807, 412, 140, 40);
		pnThemKhachHang.add(btnChuyen);
		btnChuyen.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_xacnhan.png")));
		btnChuyen.setText("Xác nhận");
		btnChuyen.setFont(new Font("Tahoma", Font.BOLD, 15));

		FixButton btnLamMoi = new FixButton("Hủy đặt phòng");
		btnLamMoi.setIcon(new ImageIcon(Frm_ThemKhachHang.class.getResource("/imgs/btn_lammoi.png")));
		btnLamMoi.setText("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBounds(627, 412, 140, 40);
		pnThemKhachHang.add(btnLamMoi);

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_trong.png")));
		lbBGQLDV.setBounds(0, 0, 1000, 820);
		pnThemKhachHang.add(lbBGQLDV);

		btnHuy.addActionListener(this);

	}

	public static void main(String[] args) {
		new Frm_ThemKhachHang().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnHuy) {
		}
	}
}
