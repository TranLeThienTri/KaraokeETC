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
import java.sql.SQLException;
import java.util.ArrayList;

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

import connectDB.ConnectDB;
import dao.DanhSachDichVu;
import dao.DanhSachKhachHang;
import entitys.DichVu;
import entitys.KhachHang;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Frm_QuanLyDichVu extends JFrame implements ActionListener {
	JPanel pnDSDichVu, pnTTDV;
	JLabel lbDSDichVu, lbBGQLDV, lbTTDV, lbLoaiDichVu, lbTenDV, lbSoLuongTon, lbDonGia;
	JComboBox comboTDV, comboLDV;
	JTextField  txtDonGia, txtSoLuongTon;
	Panel pnQLDV;
	FixButton btnLamMoi,btnThem,btnSua;
	private JTable tableDSDichVu;
	private DefaultTableModel model;
	DanhSachDichVu dsDV;

	public Panel getFrmQuanLyDichVu() {
		return this.pnQLDV;
	}

	public Frm_QuanLyDichVu() throws SQLException {
		setTitle("QUẢN LÝ DỊCH VỤ");
		setSize(1400, 670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		gui();
	}

	public void gui() throws SQLException {
		getContentPane().setLayout(null);

		pnQLDV = new Panel();
		pnQLDV.setBounds(0, 0, 1400, 670);
		getContentPane().add(pnQLDV);
		pnQLDV.setLayout(null);

		pnTTDV = new JPanel();
		pnTTDV.setBackground(new java.awt.Color(190, 157, 157));
		pnTTDV.setBounds(40, 45, 1280, 181);
		pnQLDV.add(pnTTDV);
		pnTTDV.setLayout(null);

		lbTTDV = new JLabel("Thông tin dịch vụ");
		lbTTDV.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTTDV.setForeground(new Color(255, 255, 255));
		lbTTDV.setBounds(30, 15, 250, 20);
		pnQLDV.add(lbTTDV);

		lbLoaiDichVu = new JLabel("Loại dịch vụ:");
		lbLoaiDichVu.setForeground(Color.WHITE);
		lbLoaiDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiDichVu.setBounds(36, 29, 200, 25);
		pnTTDV.add(lbLoaiDichVu);

		lbTenDV = new JLabel("Tên dịch vụ:");
		lbTenDV.setForeground(Color.WHITE);
		lbTenDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenDV.setBounds(36, 69, 200, 25);
		pnTTDV.add(lbTenDV);

		lbSoLuongTon = new JLabel("Số lượng tồn:");
		lbSoLuongTon.setForeground(Color.WHITE);
		lbSoLuongTon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSoLuongTon.setBounds(687, 29, 200, 25);
		pnTTDV.add(lbSoLuongTon);

		lbDonGia = new JLabel("Đơn giá:");
		lbDonGia.setForeground(Color.WHITE);
		lbDonGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDonGia.setBounds(687, 69, 200, 25);
		pnTTDV.add(lbDonGia);

		comboLDV = new JComboBox();
		comboLDV.setModel(new DefaultComboBoxModel(new String[] { "Đồ ăn", "Thức uống" }));
		comboLDV.setSelectedIndex(0);
		comboLDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboLDV.setBounds(226, 29, 300, 30);
		pnTTDV.add(comboLDV);

		comboTDV = new JComboBox();
		comboTDV.setModel(new DefaultComboBoxModel(new String[] { "Bia Tiger", "Bia Heineken", "Bia Sài Gòn Xanh",
				"Nước Suối", "Sting", "Pepsi", "Coca", "Snack Oishi", "Snack Lays", "Snack Swing", "Khô Bò Miếng",
				"Khô Heo Cháy Tỏi", "Mực Rim Me", "Mực Hấp Nước Dừa", "Ghẹ Rim Me", "Trái Cây Tổng Hợp" }));
		comboTDV.setSelectedIndex(0);
		comboTDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboTDV.setBounds(226, 69, 300, 30);
		pnTTDV.add(comboTDV);

		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setBounds(877, 29, 300, 30);
		pnTTDV.add(txtSoLuongTon);

		txtDonGia = new JTextField();
		txtDonGia.setBounds(877, 69, 300, 30);
		pnTTDV.add(txtDonGia);
// các nút CRUD
		btnThem = new FixButton("Thêm");
		btnThem.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_them.png")));
		btnThem.setBounds(350, 128, 190, 40);
		pnTTDV.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnSua = new FixButton("Sửa");
		btnSua.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_sua.png")));
		btnSua.setBounds(600, 128, 190, 40);
		pnTTDV.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_lammoi.png")));
		btnLamMoi.setBounds(850, 128, 190, 40);
		pnTTDV.add(btnLamMoi);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 16));

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(40, 310, 1280, 270);
		pnQLDV.add(pnDSDichVu);
		pnDSDichVu.setLayout(null);

		lbDSDichVu = new JLabel("Danh sách dịch vụ");
		lbDSDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSDichVu.setBounds(10, 0, 150, 25);
		pnDSDichVu.add(lbDSDichVu);
		//bảng table 
		String col[] = { "Mã DV","Tên Dịch Vụ", "Loại Dịch Vụ", "Số Lượng Tồn","Giá Bán"};
		model = new DefaultTableModel(col, 0);
		
		tableDSDichVu = new JTable(model);
		tableDSDichVu.setBackground(Color.WHITE);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader = tableDSDichVu.getTableHeader();
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

		tableDSDichVu.setBackground(Color.white);
		tableDSDichVu.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSDichVu.setSelectionBackground(new Color(158, 207, 0));
		tableDSDichVu.setSelectionForeground(new Color(255, 255, 255));
		tableDSDichVu.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(tableDSDichVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 25, 1280, 300);
		scrollPane.getHorizontalScrollBar();
		pnDSDichVu.add(scrollPane);

		scrollPane.setViewportView(tableDSDichVu);

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDV.setBounds(0, 0, 1400, 670);
		pnQLDV.add(lbBGQLDV);
		
		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		//kết nối data
		ConnectDB.getInstance().connect();
		// Danh sach Mat Hang
		dsDV = new DanhSachDichVu();
		upTable();
	}

	public static void main(String[] args) throws SQLException {
		new Frm_QuanLyDichVu().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o =e.getSource();
		if(o == btnLamMoi) {
			xoaTrang();
		}
		
	}
	
	public void xoaTrang() {
		txtSoLuongTon.setText("");
		txtDonGia.setText("");
		tableDSDichVu.clearSelection();
	}
	
	public void upTable() {
		int i = 0;
		ArrayList<DichVu> list = dsDV.getDSDichVu();
		for (DichVu dv : list) {
			Object[] obj = new Object[7];
			obj[0] = dv.getMaDichVu().trim();
			obj[1] = dv.getTenDichVu().trim();
			obj[2] = dv.getloaiDichVu().getTenLoaiDichVu();
			obj[3] = dv.getSoLuongTon();
			obj[4] = dv.getDonGia();
			model.addRow(obj);
		}
		xoaTrang();
	}
	
}
