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

public class Frm_ChuyenPhong extends JFrame {
	JPanel pnDSDichVu;
	JLabel lbDSDichVu, lbBGQLDV, lbTTKH;
	Panel pnThanhToan;
	private JTable tableDSDichVu;
	private DefaultTableModel model;
	private JTextField txtKhachHang, txtSDT;
	private JPanel pnTTDDP_1;
	private JLabel lbSucChua;
	private JLabel lbMaPhong;
	private JTextField txtMaPhong;
	private JTextField txtTinhTrang;
	private JTextField txtLoaiPhong;
	private JTextField txtGiaPhong;
	private JLabel lbGiaPhong;

	public Panel getFrmThanhToan() {
		return this.pnThanhToan;
	}

	public Frm_ChuyenPhong() {
		setTitle("THANH TOÁN HÓA ĐƠN");
		setSize(1000, 820);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnThanhToan = new Panel();
		pnThanhToan.setBounds(0, 0, 1000, 820);
		getContentPane().add(pnThanhToan);
		pnThanhToan.setLayout(null);

		lbTTKH = new JLabel("Thông tin khách hàng");
		lbTTKH.setForeground(Color.WHITE);
		lbTTKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTTKH.setBounds(46, 76, 200, 25);
		pnThanhToan.add(lbTTKH);

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(0, 537, 980, 250);
		pnThanhToan.add(pnDSDichVu);
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

		JPanel pnTTDDP = new JPanel();
		pnTTDDP.setLayout(null);
		pnTTDDP.setBackground(new Color(190, 157, 157, 190));
		pnTTDDP.setBounds(34, 111, 926, 92);
		pnThanhToan.add(pnTTDDP);

		JLabel lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(513, 30, 150, 25);
		pnTTDDP.add(lbSDT);

		JLabel lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(10, 30, 200, 25);
		pnTTDDP.add(lbTenKH);

		txtSDT = new JTextField();
		txtSDT.setBounds(643, 30, 250, 30);
		pnTTDDP.add(txtSDT);

		txtKhachHang = new JTextField();
		txtKhachHang.setBounds(160, 30, 250, 30);
		pnTTDDP.add(txtKhachHang);

		JLabel lbThongTinPhongHienTai = new JLabel("Thông tin phòng hiện tại");
		lbThongTinPhongHienTai.setForeground(Color.WHITE);
		lbThongTinPhongHienTai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbThongTinPhongHienTai.setBounds(46, 213, 200, 25);
		pnThanhToan.add(lbThongTinPhongHienTai);

		pnTTDDP_1 = new JPanel();
		pnTTDDP_1.setLayout(null);
		pnTTDDP_1.setBackground(new Color(190, 157, 157, 190));
		pnTTDDP_1.setBounds(34, 240, 926, 208);
		pnThanhToan.add(pnTTDDP_1);

		lbSucChua = new JLabel("Sức chứa: ");
		lbSucChua.setForeground(Color.WHITE);
		lbSucChua.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSucChua.setBounds(513, 30, 150, 25);
		pnTTDDP_1.add(lbSucChua);

		lbMaPhong = new JLabel("Mã phòng: ");
		lbMaPhong.setForeground(Color.WHITE);
		lbMaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbMaPhong.setBounds(10, 30, 200, 25);
		pnTTDDP_1.add(lbMaPhong);

		JTextField txtSucChua = new JTextField();
		txtSucChua.setBounds(643, 30, 250, 30);
		pnTTDDP_1.add(txtSucChua);

		txtMaPhong = new JTextField();
		txtMaPhong.setBounds(160, 30, 250, 30);
		pnTTDDP_1.add(txtMaPhong);

		JLabel lbTinhTrang = new JLabel("Tình trạng: ");
		lbTinhTrang.setForeground(Color.WHITE);
		lbTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTinhTrang.setBounds(10, 80, 200, 25);
		pnTTDDP_1.add(lbTinhTrang);

		txtTinhTrang = new JTextField();
		txtTinhTrang.setBounds(160, 80, 250, 30);
		pnTTDDP_1.add(txtTinhTrang);

		JLabel lbLoaiPhong = new JLabel("Loại phòng: ");
		lbLoaiPhong.setForeground(Color.WHITE);
		lbLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiPhong.setBounds(513, 80, 150, 25);
		pnTTDDP_1.add(lbLoaiPhong);

		txtLoaiPhong = new JTextField();
		txtLoaiPhong.setBounds(643, 80, 250, 30);
		pnTTDDP_1.add(txtLoaiPhong);

		txtGiaPhong = new JTextField();
		txtGiaPhong.setBounds(160, 130, 250, 30);
		pnTTDDP_1.add(txtGiaPhong);

		lbGiaPhong = new JLabel("Giá phòng: ");
		lbGiaPhong.setForeground(Color.WHITE);
		lbGiaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGiaPhong.setBounds(10, 130, 200, 25);
		pnTTDDP_1.add(lbGiaPhong);

		FixButton btnHuy = new FixButton("Hủy đặt phòng");
		btnHuy.setBounds(337, 467, 140, 40);
		pnThanhToan.add(btnHuy);
		btnHuy.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_huydv.png")));
		btnHuy.setText("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));

		FixButton btnChuyen = new FixButton("Làm mới");
		btnChuyen.setBounds(507, 467, 140, 40);
		pnThanhToan.add(btnChuyen);
		btnChuyen.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_xacnhan.png")));
		btnChuyen.setText("Xác nhận");
		btnChuyen.setFont(new Font("Tahoma", Font.BOLD, 15));
		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_trong.png")));
		lbBGQLDV.setBounds(0, 0, 1000, 820);
		pnThanhToan.add(lbBGQLDV);

	}

	public static void main(String[] args) {
		new Frm_ChuyenPhong().setVisible(true);

	}
}
