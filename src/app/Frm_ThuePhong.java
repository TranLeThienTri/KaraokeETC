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

//import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Frm_ThuePhong extends JFrame {
	JPanel pnLoaiPhong, pnDSP;
	JLabel lbLoaiPhongTK, lbTinhTrang, lbDSPhong, lbBGQLDP;
	FixButton btnLamMoi, btnHuyDatPhong, btnDatPhong, btnNhanPhong;
	FixButton2 btnTatCa, btnPhongThuong, btnPhongVip;
	JRadioButton radioDangDat, radioTrong;
	private Date ngayHienTai;
	Panel pnQLDP;
	private int ngay, thang, nam;
	private JTable tableDSPhong, tableDSPhong1;
	private DefaultTableModel model, model1;

	public Panel getFrmQuanLyThuePhong() {
		return this.pnQLDP;
	}

	public Frm_ThuePhong() {
		setTitle("QUẢN LÝ Thuê phòng");
		setSize(1400, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnQLDP = new Panel();
		pnQLDP.setBounds(0, 0, 1400, 700);
		getContentPane().add(pnQLDP);
		pnQLDP.setLayout(null);

		JPanel pnTTDDP = new JPanel();
		pnTTDDP.setBackground(new java.awt.Color(190, 157, 157,190));
		pnTTDDP.setBounds(38, 23, 625, 101);
		pnQLDP.add(pnTTDDP);
		pnTTDDP.setLayout(null);

		JLabel lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(65, 29, 126, 25);
		pnTTDDP.add(lbSDT);

		JLabel lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(65, 64, 140, 25);
		pnTTDDP.add(lbTenKH);

		JTextField txtSDT = new JTextField();
		txtSDT.setBounds(207, 31, 323, 25);
		pnTTDDP.add(txtSDT);

		JTextField txtKhachHang = new JTextField();
		txtKhachHang.setBounds(207, 66, 323, 25);
		pnTTDDP.add(txtKhachHang);

		JLabel lbIconSearch = new JLabel("New label");
		lbIconSearch.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/icon_search.png")));
		lbIconSearch.setBounds(542, 33, 20, 20);
		pnTTDDP.add(lbIconSearch);
		
				JLabel lbTTDDP = new JLabel("Thông tin đơn đặt phòng");
				lbTTDDP.setBounds(10, -1, 190, 20);
				pnTTDDP.add(lbTTDDP);
				lbTTDDP.setFont(new Font("Tahoma", Font.BOLD, 15));
				lbTTDDP.setForeground(new Color(255, 255, 255));

		ngayHienTai = new Date(nam, thang, ngay);

		pnLoaiPhong = new JPanel();
		pnLoaiPhong.setBackground(Color.ORANGE);
		pnLoaiPhong.setBounds(700, 23, 650, 101);
		pnQLDP.add(pnLoaiPhong);
		pnLoaiPhong.setLayout(null);

		lbLoaiPhongTK = new JLabel("Loại phòng:");
		lbLoaiPhongTK.setForeground(Color.WHITE);
		lbLoaiPhongTK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiPhongTK.setBounds(46, 11, 150, 25);
		pnLoaiPhong.add(lbLoaiPhongTK);
		pnLoaiPhong.setBackground(new java.awt.Color(255, 255, 255, 80));

		pnQLDP.add(pnLoaiPhong);

		btnTatCa = new FixButton2("Tất cả");
		btnTatCa.setBounds(165, 10, 100, 25);
		pnLoaiPhong.add(btnTatCa);

		btnPhongVip = new FixButton2("Phòng VIP");
		btnPhongVip.setBounds(295, 10, 120, 25);
		pnLoaiPhong.add(btnPhongVip);

		btnPhongThuong = new FixButton2("Phòng thường");
		btnPhongThuong.setBounds(440, 10, 150, 25);
		pnLoaiPhong.add(btnPhongThuong);

		lbTinhTrang = new JLabel("Tình trạng:");
		lbTinhTrang.setForeground(Color.WHITE);
		lbTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTinhTrang.setBounds(46, 50, 150, 25);
		pnLoaiPhong.add(lbTinhTrang);

		radioDangDat = new JRadioButton("Đang đặt");
		radioDangDat.setFont(new Font("Tahoma", Font.BOLD, 15));
		radioDangDat.setSelected(true);
		radioDangDat.setBounds(164, 50, 103, 21);
		pnLoaiPhong.add(radioDangDat);

		radioTrong = new JRadioButton("Trống");
		radioTrong.setFont(new Font("Tahoma", Font.BOLD, 15));
		radioTrong.setBounds(295, 50, 103, 21);
		pnLoaiPhong.add(radioTrong);

		pnDSP = new JPanel();
		pnDSP.setBackground(Color.WHITE);
		pnDSP.setBounds(38, 136, 1312, 230);
		pnQLDP.add(pnDSP);
		pnDSP.setLayout(null);

		lbDSPhong = new JLabel("Danh sách phòng");
		lbDSPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPhong.setBounds(10, 0, 150, 25);
		pnDSP.add(lbDSPhong);
		String col[] = { "Mã phòng", "Loại phòng", "Sức chứa", "Giá phòng", "Tình trạng" };
		model = new DefaultTableModel(col, 0);

		tableDSPhong = new JTable(new DefaultTableModel(
				new Object[][] {
						{ "MP001", "Ph\u00F2ng th\u01B0\u1EDDng", "20 ng\u01B0\u1EDDi", "300.000 VN\u0110",
								"Tr\u1ED1ng" },
						{ "MP002", "Ph\u00F2ng VIP", "20 ng\u01B0\u1EDDi", "500.000 VN\u0110", "Đang đặt" },
						{ null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, },
				new String[] { "M\u00E3 ph\u00F2ng", "Lo\u1EA1i ph\u00F2ng", "S\u1EE9c ch\u1EE9a",
						"Gi\u00E1 ph\u00F2ng", "T\u00ECnh tr\u1EA1ng" }));
		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader = tableDSPhong.getTableHeader();
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

		tableDSPhong.setShowHorizontalLines(true);
		tableDSPhong.setShowGrid(true);
		tableDSPhong.setBackground(Color.white);
		tableDSPhong.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSPhong.setSelectionBackground(new Color(158, 207, 0));
		tableDSPhong.setSelectionForeground(new Color(255, 255, 255));
		tableDSPhong.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(tableDSPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 25, 1300, 200);
		scrollPane.getHorizontalScrollBar();
		pnDSP.add(scrollPane);

		scrollPane.setViewportView(tableDSPhong);

		FixButton btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setIcon(new ImageIcon(Frm_ThuePhong.class.getResource("/imgs/icon_btn_lammoi.png")));
		btnLamMoi.setBackground(new java.awt.Color(153, 36, 36));
		btnLamMoi.setBounds(253, 400, 150, 30);
		pnQLDP.add(btnLamMoi);

		FixButton btnHuyDatPhong = new FixButton("Hủy đặt phòng");
		btnHuyDatPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuyDatPhong.setBounds(488, 400, 150, 30);
		btnHuyDatPhong.setBackground(new java.awt.Color(153, 36, 36));
		pnQLDP.add(btnHuyDatPhong);

		FixButton btnDatPhong = new FixButton("Đặt phòng");
		btnDatPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDatPhong.setBackground(new java.awt.Color(153, 36, 36));
		btnDatPhong.setBounds(735, 400, 150, 30);
		pnQLDP.add(btnDatPhong);

		FixButton btnNhanPhong = new FixButton("Nhận phòng");
		btnNhanPhong.setBackground(new java.awt.Color(153, 36, 36));
		
		btnNhanPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNhanPhong.setBounds(989, 400, 150, 30);
		pnQLDP.add(btnNhanPhong);

		String col1[] = { "Mã hóa đơn", "Mã phòng", "Tên khách hàng", "SĐT", "Ngày", "Thời gian", "Tên nhân viên" };
		model1 = new DefaultTableModel(col1, 0);

		tableDSPhong1 = new JTable(new DefaultTableModel(
				new Object[][] {
						{ "HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34",
								"Nguy\u1EC5n V\u0103n A" },
						{ "HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07",
								"Nguy\u1EC5n V\u0103n B" },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, },
				new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 ph\u00F2ng", "T\u00EAn kh\u00E1ch h\u00E0ng",
						"S\u0110T", "Ng\u00E0y", "Th\u1EDDi gian", "T\u00EAn nh\u00E2n vi\u00EAn" }));
		tableDSPhong1.setBackground(Color.WHITE);
//		tableDSPhong1.setColumnSelectionAllowed(true);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader1 = tableDSPhong1.getTableHeader();
		tbHeader1.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader1.setForeground(Color.WHITE);
		tbHeader1.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

		tableDSPhong1.setShowHorizontalLines(true);
		tableDSPhong1.setShowGrid(true);
		tableDSPhong1.setBackground(Color.white);
		tableDSPhong1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSPhong1.setSelectionBackground(new Color(158, 207, 0));
		tableDSPhong1.setSelectionForeground(new Color(255, 255, 255));
		tableDSPhong1.setRowHeight(30);

		JScrollPane scrollPane1 = new JScrollPane(tableDSPhong1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane1.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane1.setBackground(Color.BLACK);
		scrollPane1.setBounds(0, 470, 1400, 200);
		scrollPane1.getHorizontalScrollBar();
		pnQLDP.add(scrollPane1);

		scrollPane1.setViewportView(tableDSPhong1);

		// add background ở cuối
		 lbBGQLDP = new JLabel("");
		lbBGQLDP.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDP.setBounds(0, 0, 1400, 700);
		pnQLDP.add(lbBGQLDP);
	}

	public static void main(String[] args) {
		new Frm_ThuePhong().setVisible(true);

	}
}

