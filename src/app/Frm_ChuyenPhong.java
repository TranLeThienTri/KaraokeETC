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

public class Frm_ChuyenPhong extends JFrame implements ActionListener {
	JPanel pnDSDichVu;
	Panel pnChuyenPhong;
	private JTable tableDSDichVu;
	private DefaultTableModel model;
	private JTextField txtKhachHang, txtSDT;
	private JPanel pnTTPHT, pnTTKH;
	private JLabel lbSucChua, lbMaPhong, lbLoaiPhong, lbSDT, lbTenKH, lbThongTinPhongHienTai, lbTinhTrang, lbGiaPhong,
			lbDSDichVu, lbBGQLDV, lbTTKH, lbChuyenPhong;
	private JTextField txtMaPhong, txtTinhTrang, txtLoaiPhong, txtGiaPhong, txtSucChua;
	private JComboBox comboTTP, comboLP, comboGP;
	FixButton btnHuy, btnChuyen;

	public Panel getFrmChuyenPhong() {
		return this.pnChuyenPhong;
	}

	public Frm_ChuyenPhong() {
		setTitle("CHUYỂN PHÒNG");
		setSize(1000, 820);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnChuyenPhong = new Panel();
		pnChuyenPhong.setBounds(0, 0, 1000, 820);
		getContentPane().add(pnChuyenPhong);
		pnChuyenPhong.setLayout(null);

		lbTTKH = new JLabel("Thông tin khách hàng");
		lbTTKH.setForeground(Color.WHITE);
		lbTTKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTTKH.setBounds(46, 76, 200, 25);
		pnChuyenPhong.add(lbTTKH);

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(0, 537, 980, 250);
		pnChuyenPhong.add(pnDSDichVu);
		pnDSDichVu.setLayout(null);

		lbDSDichVu = new JLabel("Danh sách dịch vụ");
		lbDSDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSDichVu.setBounds(10, 0, 150, 25);
		pnDSDichVu.add(lbDSDichVu);

		tableDSDichVu = new JTable(new DefaultTableModel(
				new Object[][] { { "MP001", "Trống", "20", "Phòng VIP", "300.000 VNĐ", "50m2" },
						{ "MP002", "Trống", "20", "Phòng VIP", "300.000 VNĐ", "50m2" },
						{ "MP003", "Trống", "20", "Phòng VIP", "300.000 VNĐ", "50m2" },
						{ null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "Mã phòng", "Tình trạng", "Sức chứa", "Loại phòng", "Giá phòng", "Diện tích" }));
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
		scrollPane.setBounds(0, 25, 980, 220);
		scrollPane.getHorizontalScrollBar();
		pnDSDichVu.add(scrollPane);

		scrollPane.setViewportView(tableDSDichVu);

		pnTTKH = new JPanel();
		pnTTKH.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnTTKH.setLayout(null);
		pnTTKH.setBackground(new Color(207, 169, 0));
		pnTTKH.setBounds(34, 111, 926, 92);
		pnChuyenPhong.add(pnTTKH);

		lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(513, 30, 150, 25);
		pnTTKH.add(lbSDT);

		lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(10, 30, 200, 25);
		pnTTKH.add(lbTenKH);

		txtSDT = new JTextField();
		txtSDT.setBounds(643, 30, 250, 30);
		pnTTKH.add(txtSDT);

		txtKhachHang = new JTextField();
		txtKhachHang.setBounds(160, 30, 250, 30);
		pnTTKH.add(txtKhachHang);

		lbThongTinPhongHienTai = new JLabel("Thông tin phòng hiện tại");
		lbThongTinPhongHienTai.setForeground(Color.WHITE);
		lbThongTinPhongHienTai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbThongTinPhongHienTai.setBounds(46, 213, 200, 25);
		pnChuyenPhong.add(lbThongTinPhongHienTai);

		pnTTPHT = new JPanel();
		pnTTPHT.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnTTPHT.setLayout(null);
		pnTTPHT.setBackground(new Color(189, 0, 88));
		pnTTPHT.setBounds(34, 240, 926, 208);
		pnChuyenPhong.add(pnTTPHT);

		lbSucChua = new JLabel("Sức chứa: ");
		lbSucChua.setForeground(Color.WHITE);
		lbSucChua.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSucChua.setBounds(513, 30, 150, 25);
		pnTTPHT.add(lbSucChua);

		lbMaPhong = new JLabel("Mã phòng: ");
		lbMaPhong.setForeground(Color.WHITE);
		lbMaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbMaPhong.setBounds(10, 30, 200, 25);
		pnTTPHT.add(lbMaPhong);

		txtSucChua = new JTextField();
		txtSucChua.setBounds(643, 30, 250, 30);
		pnTTPHT.add(txtSucChua);

		txtMaPhong = new JTextField();
		txtMaPhong.setBounds(160, 30, 250, 30);
		pnTTPHT.add(txtMaPhong);

		lbTinhTrang = new JLabel("Tình trạng: ");
		lbTinhTrang.setForeground(Color.WHITE);
		lbTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTinhTrang.setBounds(10, 80, 200, 25);
		pnTTPHT.add(lbTinhTrang);

		comboTTP = new JComboBox();
		comboTTP.setModel(new DefaultComboBoxModel(new String[] { "Đang thuê", "Đã đặt", "Trống" }));
		comboTTP.setSelectedIndex(0);
		comboTTP.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboTTP.setBounds(643, 80, 250, 30);
		pnTTPHT.add(comboTTP);

		lbLoaiPhong = new JLabel("Loại phòng: ");
		lbLoaiPhong.setForeground(Color.WHITE);
		lbLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiPhong.setBounds(513, 80, 150, 25);
		pnTTPHT.add(lbLoaiPhong);

		comboLP = new JComboBox();
		comboLP.setModel(new DefaultComboBoxModel(new String[] { "Phòng thường", "Phòng VIP" }));
		comboLP.setSelectedIndex(0);
		comboLP.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboLP.setBounds(160, 80, 250, 30);
		pnTTPHT.add(comboLP);

		comboGP = new JComboBox();
		comboGP.setModel(new DefaultComboBoxModel(new String[] { "150.000", "300.000", "500.000" }));
		comboGP.setEditable(true);
		comboGP.setSelectedIndex(0);
		comboGP.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboGP.setBounds(160, 130, 250, 30);
		pnTTPHT.add(comboGP);

		lbGiaPhong = new JLabel("Giá phòng: ");
		lbGiaPhong.setForeground(Color.WHITE);
		lbGiaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGiaPhong.setBounds(10, 130, 200, 25);
		pnTTPHT.add(lbGiaPhong);

		btnHuy = new FixButton("Hủy đặt phòng");
		btnHuy.setBounds(337, 467, 140, 40);
		pnChuyenPhong.add(btnHuy);
		btnHuy.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_huydv.png")));
		btnHuy.setText("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnChuyen = new FixButton("Làm mới");
		btnChuyen.setBounds(507, 467, 140, 40);
		pnChuyenPhong.add(btnChuyen);
		btnChuyen.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_xacnhan.png")));
		btnChuyen.setText("Xác nhận");
		btnChuyen.setFont(new Font("Tahoma", Font.BOLD, 15));

		lbChuyenPhong = new JLabel("CHUYỂN PHÒNG");
		lbChuyenPhong.setForeground(Color.WHITE);
		lbChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbChuyenPhong.setBounds(429, 40, 300, 35);
		pnChuyenPhong.add(lbChuyenPhong);

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_trong.png")));
		lbBGQLDV.setBounds(0, 0, 1000, 820);
		pnChuyenPhong.add(lbBGQLDV);

		btnHuy.addActionListener(this);

	}

	public static void main(String[] args) {
		new Frm_ChuyenPhong().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnHuy) {
			new Frm_ThuePhong().setVisible(true);
			dispose();
		}
	}
}
