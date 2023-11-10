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
import java.awt.SystemColor;

public class Frm_QuanLyDatPhong extends JFrame {
	JPanel pnLoaiPhong, pnDSP, pnTTDDP;
	JLabel lbLoaiPhongTK, lbTinhTrang, lbDSPhong, lbBGQLDP, lbTTDDP, lbSDT, lbTenKH, lbLoaiKH, lbNgayDat, lbThoiGianDat,
			lbIconSearch;
	JComboBox comboLKH;
	JTextField txtSDT, txtKhachHang, txtGio, txtPhut;
	FixButton btnLamMoi, btnHuyDatPhong, btnDatPhong, btnNhanPhong;
	FixButton2 btnTatCa, btnPhongThuong, btnPhongVip;
	JDateChooser ngayDatPhong;
	JRadioButton radioDangDat, radioTrong;
	private Date ngayHienTai;
	Panel pnQLDP;
	private int ngay, thang, nam;
	private JTable tableDSPhong, tableDSPhong1;
	private DefaultTableModel model, model1;

	public Panel getFrmQuanLyDatPhong() {
		return this.pnQLDP;
	}

	public Frm_QuanLyDatPhong() {
		setTitle("QUẢN LÝ ĐẶT PHÒNG");
		setSize(1400, 670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);

		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnQLDP = new Panel();
		pnQLDP.setBounds(0, 0, 1400, 670);
		getContentPane().add(pnQLDP);
		pnQLDP.setLayout(null);

		pnTTDDP = new JPanel();
		pnTTDDP.setBackground(new java.awt.Color(190, 157, 157, 190));
		pnTTDDP.setBounds(32, 10, 650, 320);
		pnQLDP.add(pnTTDDP);
		pnTTDDP.setLayout(null);

		lbTTDDP = new JLabel("Thông tin đơn đặt phòng");
		lbTTDDP.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTTDDP.setForeground(new Color(255, 255, 255));
		lbTTDDP.setBounds(10, 10, 250, 20);
		pnTTDDP.add(lbTTDDP);

		lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(80, 50, 200, 25);
		pnTTDDP.add(lbSDT);

		lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(80, 85, 200, 25);
		pnTTDDP.add(lbTenKH);

		lbLoaiKH = new JLabel("Loại khách hàng:");
		lbLoaiKH.setForeground(Color.WHITE);
		lbLoaiKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiKH.setBounds(80, 120, 200, 25);
		pnTTDDP.add(lbLoaiKH);

		lbNgayDat = new JLabel("Ngày đặt:");
		lbNgayDat.setForeground(Color.WHITE);
		lbNgayDat.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbNgayDat.setBounds(80, 155, 200, 25);
		pnTTDDP.add(lbNgayDat);

		lbThoiGianDat = new JLabel("Thời gian:");
		lbThoiGianDat.setForeground(Color.WHITE);
		lbThoiGianDat.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbThoiGianDat.setBounds(80, 195, 200, 25);
		pnTTDDP.add(lbThoiGianDat);

		txtSDT = new JTextField();
		txtSDT.setBounds(230, 50, 300, 25);
		pnTTDDP.add(txtSDT);

		txtKhachHang = new JTextField();
		txtKhachHang.setBounds(230, 85, 300, 25);
		pnTTDDP.add(txtKhachHang);

		comboLKH = new JComboBox();
		comboLKH.setModel(new DefaultComboBoxModel(new String[] { "Khách hàng VIP", "Khách hàng thường" }));
		comboLKH.setSelectedIndex(0);
		comboLKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboLKH.setBounds(230, 120, 300, 30);
		pnTTDDP.add(comboLKH);

		txtGio = new JTextField();
		txtGio.setBounds(231, 195, 49, 25);
		pnTTDDP.add(txtGio);

		txtPhut = new JTextField();
		txtPhut.setBounds(293, 195, 49, 25);
		pnTTDDP.add(txtPhut);

		lbIconSearch = new JLabel("New label");
		lbIconSearch.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/icon_search.png")));
		lbIconSearch.setBounds(540, 52, 20, 20);
		pnTTDDP.add(lbIconSearch);

		ngayDatPhong = new JDateChooser();
		ngayDatPhong.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ngayDatPhong.setDateFormatString("dd/MM/yyyy");

		ngayDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ngayDatPhong.getCalendarButton().setPreferredSize(new Dimension(40, 30));
		ngayDatPhong.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/calendar.png")));

		ngayDatPhong.setBounds(230, 155, 300, 30);

		ngayHienTai = new Date(nam, thang, ngay);
		ngayDatPhong.setDate(ngayHienTai);
		pnTTDDP.add(ngayDatPhong);

		pnLoaiPhong = new JPanel();
		pnLoaiPhong.setBackground(new java.awt.Color(250, 154, 0));
		pnLoaiPhong.setBounds(700, 10, 650, 80);
		pnQLDP.add(pnLoaiPhong);
		pnLoaiPhong.setLayout(null);

		lbLoaiPhongTK = new JLabel("Loại phòng:");
		lbLoaiPhongTK.setForeground(Color.WHITE);
		lbLoaiPhongTK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiPhongTK.setBounds(46, 11, 150, 25);
		pnLoaiPhong.add(lbLoaiPhongTK);
//		pnLoaiPhong.setBackground(new java.awt.Color(255, 255, 255, 80));

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
		radioDangDat.setOpaque(false);
		radioDangDat.setFont(new Font("Tahoma", Font.BOLD, 15));
		radioDangDat.setSelected(true);
		radioDangDat.setBounds(164, 50, 103, 21);
		pnLoaiPhong.add(radioDangDat);

		radioTrong = new JRadioButton("Trống");
		radioTrong.setContentAreaFilled(false);
		radioTrong.setFont(new Font("Tahoma", Font.BOLD, 15));
		radioTrong.setBounds(295, 50, 103, 21);
		pnLoaiPhong.add(radioTrong);

		ButtonGroup bg = new ButtonGroup();
		bg.add(radioTrong);
		bg.add(radioDangDat);

		pnDSP = new JPanel();
		pnDSP.setBackground(Color.WHITE);
		pnDSP.setBounds(700, 100, 650, 230);
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
		tableDSPhong.setBackground(Color.WHITE);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader = tableDSPhong.getTableHeader();
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

//		tableDSPhong.setShowHorizontalLines(true);
//		tableDSPhong.setShowGrid(true);
		tableDSPhong.setBackground(Color.white);
		tableDSPhong.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSPhong.setSelectionBackground(new Color(158, 207, 0));
		tableDSPhong.setSelectionForeground(new Color(255, 255, 255));
		tableDSPhong.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(tableDSPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 25, 650, 200);
		scrollPane.getHorizontalScrollBar();
		pnDSP.add(scrollPane);

		scrollPane.setViewportView(tableDSPhong);

		FixButton btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_lammoi.png")));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBounds(220, 350, 190, 40);
		pnQLDP.add(btnLamMoi);

		FixButton btnHuyDatPhong = new FixButton("Hủy đặt phòng");
		btnHuyDatPhong.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_huydv.png")));
		btnHuyDatPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuyDatPhong.setBounds(470, 350, 190, 40);
		pnQLDP.add(btnHuyDatPhong);

		FixButton btnDatPhong = new FixButton("Đặt phòng");
		btnDatPhong.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_xacnhan.png")));
		btnDatPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDatPhong.setBounds(720, 350, 190, 40);
		pnQLDP.add(btnDatPhong);

		FixButton btnNhanPhong = new FixButton("Nhận phòng");
		btnNhanPhong.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_nhanphong.png")));
		btnNhanPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNhanPhong.setBounds(970, 350, 190, 40);
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

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader1 = tableDSPhong1.getTableHeader();
		tbHeader1.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader1.setForeground(Color.WHITE);
		tbHeader1.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

//		tableDSPhong1.setShowHorizontalLines(true);
//		tableDSPhong1.setShowGrid(true);
		tableDSPhong1.setBackground(Color.white);
		tableDSPhong1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSPhong1.setSelectionBackground(new Color(158, 207, 0));
		tableDSPhong1.setSelectionForeground(new Color(255, 255, 255));
		tableDSPhong1.setRowHeight(30);

		JScrollPane scrollPane1 = new JScrollPane(tableDSPhong1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane1.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane1.setBackground(Color.BLACK);
		scrollPane1.setBounds(0, 440, 1385, 200);
		scrollPane1.getHorizontalScrollBar();
		pnQLDP.add(scrollPane1);

		scrollPane1.setViewportView(tableDSPhong1);

		// add background ở cuối
		lbBGQLDP = new JLabel("");
		lbBGQLDP.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDP.setBounds(0, 0, 1400, 670);
		pnQLDP.add(lbBGQLDP);
	}

	public static void main(String[] args) {
		new Frm_QuanLyDatPhong().setVisible(true);

	}
}
