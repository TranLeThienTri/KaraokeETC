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
	JPanel pnLoaiPhong, pnDSP,pnDSP1,pnCRUD,pnDSP2;
	JLabel lbLoaiPhongTK, lbTinhTrang, lbDSPhong, lbBGQLDP,lbDSPhong1;
	FixButton btnLamMoi, btnHuyDatPhong, btnDatPhong, btnNhanPhong;
	FixButton2 btnTatCa, btnPhongThuong, btnPhongVip;
	JRadioButton radioDangDat, radioTrong;
	private Date ngayHienTai;
	Panel pnQLDP;
	private int ngay, thang, nam;
	private JTable tableDSPhong, tableDSPhong1 ,tableDSPhong2,tableDSDichVu;
	private DefaultTableModel model, model1,model2;

	public Panel getFrmQuanLyThuePhong() {
		return this.pnQLDP;
	}

	public Frm_ThuePhong() {
		setTitle("QUẢN LÝ Thuê phòng");
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

		JPanel pnTTDDP = new JPanel();
		pnTTDDP.setBackground(new java.awt.Color(190, 157, 157,190));
		pnTTDDP.setBounds(30, 10, 579, 97);
		pnQLDP.add(pnTTDDP);
		pnTTDDP.setLayout(null);

		JLabel lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(38, 25, 126, 25);
		pnTTDDP.add(lbSDT);

		JLabel lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(41, 60, 140, 25);
		pnTTDDP.add(lbTenKH);

		JTextField txtSDT = new JTextField();
		txtSDT.setBounds(174, 25, 323, 25);
		pnTTDDP.add(txtSDT);

		JTextField txtKhachHang = new JTextField();
		txtKhachHang.setBounds(174, 62, 323, 25);
		pnTTDDP.add(txtKhachHang);

		JLabel lbIconSearch = new JLabel("New label");
		lbIconSearch.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/icon_search.png")));
		lbIconSearch.setBounds(522, 33, 20, 20);
		pnTTDDP.add(lbIconSearch);
		
				JLabel lbTTDDP = new JLabel("Thông tin đơn đặt phòng");
				lbTTDDP.setBounds(10, -1, 190, 20);
				pnTTDDP.add(lbTTDDP);
				lbTTDDP.setFont(new Font("Tahoma", Font.BOLD, 15));
				lbTTDDP.setForeground(new Color(255, 255, 255));


		pnLoaiPhong = new JPanel();
		pnLoaiPhong.setBackground(new java.awt.Color(250, 154, 0));
		pnLoaiPhong.setBounds(689, 10, 655, 97);
		pnQLDP.add(pnLoaiPhong);
		pnLoaiPhong.setLayout(null);

		lbLoaiPhongTK = new JLabel("Loại phòng:");
		lbLoaiPhongTK.setForeground(Color.WHITE);
		lbLoaiPhongTK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiPhongTK.setBounds(46, 11, 150, 25);
		pnLoaiPhong.add(lbLoaiPhongTK);


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
//table danh sách phòng
		pnDSP = new JPanel();
		pnDSP.setBackground(Color.WHITE);
		pnDSP.setBounds(30, 136, 1321, 230);
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
		scrollPane.setBounds(0, 25, 1321, 200);
		scrollPane.getHorizontalScrollBar();
		pnDSP.add(scrollPane);
//////		
//////// table danh sach dịch vụ
		pnDSP1 = new JPanel();
		pnDSP1.setBackground(Color.WHITE);
		pnDSP1.setBounds(800, 380, 550, 200);
		pnQLDP.add(pnDSP1);
		pnDSP1.setLayout(null);
		lbDSPhong = new JLabel("Thông tin dich vụ");
		lbDSPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPhong.setBounds(10, 0, 150, 25);
		pnDSP1.add(lbDSPhong);

		tableDSDichVu = new JTable(new DefaultTableModel(
				new Object[][] { { "1","HD001", "Thức uống", "Bia Tiger", "10", "30.000 VNĐ", "300.000 VNĐ" },
						{ "2", "Đồ ăn", "Mực Rim Me", "5", "100.000 VNĐ", "500.000 VNĐ" },
						{ null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "STT","Mã phòng", "Loại dịch vụ", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền" }));
		tableDSDichVu.setBackground(Color.WHITE);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader3 = tableDSDichVu.getTableHeader();
		tbHeader3.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader3.setForeground(Color.WHITE);
		tbHeader3.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

		tableDSDichVu.setBackground(Color.white);
		tableDSDichVu.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSDichVu.setSelectionBackground(new Color(158, 207, 0));
		tableDSDichVu.setSelectionForeground(new Color(255, 255, 255));
		tableDSDichVu.setRowHeight(30);

		JScrollPane scrollPaneDV = new JScrollPane(tableDSDichVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneDV.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPaneDV.setBackground(Color.BLACK);
		scrollPaneDV.setBounds(0, 25, 550, 200);
		scrollPaneDV.getHorizontalScrollBar();
		pnDSP1.add(scrollPaneDV);

// các nut button
		
		pnCRUD = new JPanel();
		pnCRUD.setBackground(new Color(158, 207, 0,1));
		pnCRUD.setBounds(106, 572, 1184, 57);
		pnQLDP.add(pnCRUD);
		pnCRUD.setLayout(null);
		FixButton btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setIcon(new ImageIcon(Frm_ThuePhong.class.getResource("/imgs/icon_btn_lammoi.png")));
		//btnLamMoi.setBackground(new java.awt.Color(153, 36, 36));
		btnLamMoi.setBounds(712, 20, 150, 30);
		pnCRUD.add(btnLamMoi);

		FixButton btnTinhTien = new FixButton("Tính tiền");
		
		btnTinhTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTinhTien.setBounds(939, 20, 150, 30);
		//btnTinhTien.setBackground(new java.awt.Color(153, 36, 36));
		pnCRUD.add(btnTinhTien);

		FixButton btnThuePhong = new FixButton("Thuê phòng");
		btnThuePhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		//btnThuePhong.setBackground(new java.awt.Color(153, 36, 36));
		btnThuePhong.setBounds(476, 20, 150, 30);
		pnCRUD.add(btnThuePhong);

		FixButton btnChuyenPhong = new FixButton("Chuyển phòng");
		
		//btnChuyenPhong.setBackground(new java.awt.Color(153, 36, 36));
		
		btnChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnChuyenPhong.setBounds(25, 20, 150, 30);
		pnCRUD.add(btnChuyenPhong);
		FixButton btnThemDV = new FixButton("Thêm dịch vụ");
		//btnThemDV.setBackground(new java.awt.Color(153, 36, 36));
		btnThemDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThemDV.setBounds(240, 20, 150, 30);
		pnCRUD.add(btnThemDV);
//table thong tin thuê phòng
		

		pnDSP2 = new JPanel();
		pnDSP2.setBackground(Color.WHITE);
		pnDSP2.setBounds(30, 380, 750, 200);
		pnQLDP.add(pnDSP2);
		pnDSP2.setLayout(null);
		lbDSPhong1 = new JLabel("Thông tin Thuê phòng");
		lbDSPhong1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPhong1.setBounds(0, 0, 200, 25);
		pnDSP2.add(lbDSPhong1);
		
		String col2[] = { "Mã hóa đơn", "Mã phòng", "Tên khách hàng", "SĐT", "Ngày", "Thời gian", "Tên nhân viên" };
		model2 = new DefaultTableModel(col2, 0);

		tableDSPhong2 = new JTable(new DefaultTableModel(
				new Object[][] {
						{ "HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34",
								"Nguy\u1EC5n V\u0103n A" },
						 },
				new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 ph\u00F2ng", "T\u00EAn kh\u00E1ch h\u00E0ng",
						"S\u0110T", "Ng\u00E0y", "Th\u1EDDi gian", "T\u00EAn nh\u00E2n vi\u00EAn" }));
		tableDSPhong2.setBackground(Color.WHITE);
//		tableDSPhong1.setColumnSelectionAllowed(true);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader2 = tableDSPhong2.getTableHeader();
		tbHeader2.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader2.setForeground(Color.WHITE);
		tbHeader2.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

		tableDSPhong2.setShowHorizontalLines(true);
		tableDSPhong2.setShowGrid(true);
		tableDSPhong2.setBackground(Color.white);
		tableDSPhong2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSPhong2.setSelectionBackground(new Color(158, 207, 0));
		tableDSPhong2.setSelectionForeground(new Color(255, 255, 255));
		tableDSPhong2.setRowHeight(25);

		JScrollPane scrollPane2 = new JScrollPane(tableDSPhong2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane2.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
//		scrollPane2.setBackground(Color.BLACK);
		scrollPane2.setBounds(0, 25, 750, 200);
		scrollPane2.getHorizontalScrollBar();
		pnDSP2.add(scrollPane2);

	

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

