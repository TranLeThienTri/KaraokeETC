package app;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.SimpleTimeZone;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.apache.poi.examples.hsmf.Msg2txt;

import connectDB.ConnectDB;
import dao.ThuePhong;
import dao.DanhSachHoaDon;
import dao.DanhSachPhong;
import dao.Dao_PhatSinhMa;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.LoaiHoaDon;
import entitys.NhanVien;
import entitys.Phong;
import app.Frm_ChuyenPhong;

public class Frm_ThuePhong extends JFrame implements MouseListener, ActionListener {
	JPanel pnLoaiPhong, pnDSP, pnDSP1, pnCRUD, pnDSP2;
	JLabel lbLoaiPhongTK, lbTinhTrang, lbDSPhong, lbBGQLDP, lbDSPhong1;
	FixButton btnLamMoi, btnHuyDatPhong, btnDatPhong, btnNhanPhong,btnChuyenPhong,btnTinhTien,btnThemDV;
	FixButton2 btnTatCa, btnPhongThuong, btnPhongVip;
	JRadioButton radioDangThue, radioTrong;
	private Date ngayHienTai;
	private Panel pnQLDP;
	private int ngay, thang, nam;
	private JTable tableDSPhong, tableDSPhong1, tableDSPhong2, tableDSDichVu;
	private DefaultTableModel model, model1, model2;
	private JLabel lbIconSearch, lbSDT, lbTenKH, lbTTDDP;
	private JTextField txtSDT, txtKhachHang;
	private FixButton btnThuePhong;
	private DecimalFormat df;
	private DecimalFormat dfs;
	private DecimalFormat dfh;
	private SimpleDateFormat sf;
	private DateTimeFormatter dt;
	ButtonGroup bg;
	ThuePhong dsTP;
	DanhSachPhong dsPhong;
	HoaDonPhong hdP;
	DanhSachHoaDon dsHD;
	NhanVien nv;

	Panel getFrmQuanLyThuePhong() {
		return this.pnQLDP;
	}

	public Frm_ThuePhong(NhanVien nv) {
		setTitle("QUẢN LÝ Thuê phòng");
		this.nv = nv;
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
		pnTTDDP.setBackground(new java.awt.Color(190, 157, 157, 190));
		pnTTDDP.setBounds(30, 10, 579, 97);
		pnQLDP.add(pnTTDDP);
		pnTTDDP.setLayout(null);

		lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(38, 25, 126, 25);
		pnTTDDP.add(lbSDT);

		lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(41, 60, 140, 25);
		pnTTDDP.add(lbTenKH);

		txtSDT = new JTextField();
		txtSDT.setBounds(174, 25, 323, 25);
		pnTTDDP.add(txtSDT);

		txtKhachHang = new JTextField();
		txtKhachHang.setForeground(new Color(0, 0, 0));
		txtKhachHang.setBounds(174, 62, 323, 25);
		txtKhachHang.setEditable(false);
		pnTTDDP.add(txtKhachHang);

		lbIconSearch = new JLabel("New label");
		lbIconSearch.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/icon_search.png")));
		lbIconSearch.setBounds(507, 29, 22, 20);
		Border bottomBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
		lbIconSearch.setBorder(bottomBorder);
		pnTTDDP.add(lbIconSearch);
		lbTTDDP = new JLabel("Thông tin đơn thuê phòng");
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

		radioDangThue = new JRadioButton("Đang thuê");
		radioDangThue.setOpaque(false);
		radioDangThue.setFont(new Font("Tahoma", Font.BOLD, 15));
		radioDangThue.setSelected(true);
		radioDangThue.setBounds(164, 50, 120, 21);
		pnLoaiPhong.add(radioDangThue);

		radioTrong = new JRadioButton("Trống");
		radioTrong.setContentAreaFilled(false);
		radioTrong.setFont(new Font("Tahoma", Font.BOLD, 15));
		radioTrong.setBounds(295, 50, 103, 21);
		pnLoaiPhong.add(radioTrong);

		bg = new ButtonGroup();
		bg.add(radioTrong);
		bg.add(radioDangThue);
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

		tableDSPhong = new JTable(model);
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
		String col2[] = { "M\u00E3 ph\u00F2ng", "Lo\u1EA1i d\u1ECBch v\u1EE5", "T\u00EAn d\u1ECBch v\u1EE5",
				"S\u1ED1 l\u01B0\u1EE3ng", "Giá bán" };
		model2 = new DefaultTableModel(col2, 0);
		tableDSDichVu = new JTable(model2);
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
		pnCRUD.setBackground(new Color(158, 207, 0, 1));
		pnCRUD.setBounds(106, 572, 1184, 57);
		pnQLDP.add(pnCRUD);
		pnCRUD.setLayout(null);
		btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setIcon(new ImageIcon(Frm_ThuePhong.class.getResource("/imgs/icon_btn_lammoi.png")));
		// btnLamMoi.setBackground(new java.awt.Color(153, 36, 36));
		btnLamMoi.setBounds(712, 20, 150, 30);
		pnCRUD.add(btnLamMoi);

		btnTinhTien = new FixButton("Tính tiền");

		btnTinhTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTinhTien.setBounds(939, 20, 150, 30);
		// btnTinhTien.setBackground(new java.awt.Color(153, 36, 36));
		pnCRUD.add(btnTinhTien);

		btnThuePhong = new FixButton("Thuê phòng");
		btnThuePhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		// btnThuePhong.setBackground(new java.awt.Color(153, 36, 36));
		btnThuePhong.setBounds(476, 20, 150, 30);
		pnCRUD.add(btnThuePhong);

		btnChuyenPhong = new FixButton("Chuyển phòng");

		// btnChuyenPhong.setBackground(new java.awt.Color(153, 36, 36));

		btnChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnChuyenPhong.setBounds(25, 20, 150, 30);
		pnCRUD.add(btnChuyenPhong);
		btnThemDV = new FixButton("Thêm dịch vụ");
		// btnThemDV.setBackground(new java.awt.Color(153, 36, 36));
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

		String col1[] = { "Mã hóa đơn", "Mã phòng", "Tên khách hàng", "SĐT", "Ngày", "Thời gian" };
		model1 = new DefaultTableModel(col1, 0);
		tableDSPhong2 = new JTable(model1);
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
		scrollPane2.setBounds(0, 25, 750, 200);
		scrollPane2.getHorizontalScrollBar();
		pnDSP2.add(scrollPane2);
		// add background ở cuối
		lbBGQLDP = new JLabel("");
		lbBGQLDP.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDP.setBounds(0, 0, 1400, 700);
		pnQLDP.add(lbBGQLDP);
		// kết nối data
		ConnectDB.getInstance().connect();
		lbIconSearch.addMouseListener(this);
		btnChuyenPhong.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnThuePhong.addActionListener(this);
		btnTinhTien.addActionListener(this);
		btnThemDV.addActionListener(this);
		tableDSPhong.addMouseListener(this);
		btnPhongThuong.addActionListener(this);
		btnPhongVip.addActionListener(this);
		btnTatCa.addActionListener(this);
		dsPhong = new DanhSachPhong();
		dsTP = new ThuePhong();
		dsHD = new DanhSachHoaDon();
		df = new DecimalFormat("###,### VNĐ");
		dfs = new DecimalFormat("### p");
		dfh = new DecimalFormat("### h");
		sf = new SimpleDateFormat("dd/MM/yyy");
		dt = DateTimeFormatter.ofPattern("HH:mm");
		upTable1();
		upTable2();
	}
	public void upTable1() {
		int i = 0;
		ArrayList<Phong> list = dsPhong.getDSPhong();
		for (Phong p : list) {
			Object[] obj = new Object[5];
			obj[0] = p.getMaPhong().trim();
			obj[4] = p.getMaTinhTrangPhong().getTenTinhTrangPhong();
			System.out.println(p.getMaTinhTrangPhong().getMaTinhTrangPhong());
			if (p.getMaTinhTrangPhong().getMaTinhTrangPhong().trim().equals("BOOK")) {
				break;
			}
			obj[2] = p.getSucChua();
			obj[1] = p.getMaLoaiPhong().getTenLoaiPhong();
			obj[3] = df.format(p.getGiaPhong());
			model.addRow(obj);
		}
	}

	public void upTable2() {
		int i = 0;
		ArrayList<HoaDonPhong> list = dsHD.getDSHD();
		for (HoaDonPhong p : list) {
			if (p.getMaLoaiHoaDon().getMaLoaiHoaDon().equals("HDT")) {
				Object[] obj = new Object[6];
				obj[0] = p.getMaHoaDon().trim();
				obj[1] = p.getPhong().getMaPhong().trim();
				obj[2] = p.getMaKhachHang().getMaKhachHang().trim();
				obj[3] = p.getMaKhachHang().getSoDienThoai().trim();
				obj[4] = p.getNgayLapHoaDon();
				obj[5] = p.getGioBatDauThue().format(dt);
				model1.addRow(obj);
			}
		}
	}

	public boolean ktraRegex() {
		String tenkh = txtKhachHang.getText();
		String sdtkh = txtSDT.getText();
		int row = tableDSPhong.getSelectedRow();
		if (sdtkh.equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
			txtSDT.requestFocus();
			return false;
		}
		if (!sdtkh.matches("\\d{10}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không quá 10 số và ko có kí tự");
			txtSDT.setText("");
			txtSDT.requestFocus();
			return false;
		}
		if (tenkh.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên khách hàng không được để trống");
			return false;
		}
		if (row < 0) {
			JOptionPane.showMessageDialog(this, "Chọn phòng cần thuê");
			return false;
		}
		if (tableDSPhong.getValueAt(row, 4).toString().equals("Phòng đang thuê")) {
			JOptionPane.showMessageDialog(this, "Phòng đã cho thuê!!!\n Chọn phòng khác");
			return false;
		}
		return true;
	}

	public boolean thuePhong() {
		int row = tableDSPhong.getSelectedRow();
		Dao_PhatSinhMa dao = new Dao_PhatSinhMa();
		String maHD = dao.getMaHDCuoi();
		String tenkh = txtKhachHang.getText();
		String sdtkh = txtSDT.getText();
		String map = (String) tableDSPhong.getValueAt(row, 0);
		KhachHang kh = dsTP.ktraKHTheoSDt(sdtkh);
		String makh = kh.getMaKhachHang();
		String manv = nv.getMaNhanVien();
		Phong p = new Phong(map);
		LoaiHoaDon lhd = new LoaiHoaDon("HDT");
		LocalDate ngaythue = LocalDate.now();
		LocalTime giothue = LocalTime.now();
		HoaDonPhong phong = new HoaDonPhong(maHD, p, nv, kh, lhd, ngaythue, giothue);
		if (!dsTP.themHDThue(phong) && !dsTP.setTTPhongTheoMa(map, "RENT")) {
//			Object[] obj = new Object[6];
//			obj[0] = phong.getMaHoaDon().trim();
//			obj[1] = map;
//			obj[2] = tenkh;
//			obj[3] = sdtkh;
//			obj[4] = ngaythue;
//			obj[5] = giothue;
//			model1.addRow(obj);
			JOptionPane.showMessageDialog(this, "Thuê phòng thành công");
			clearTable();
			upTable2();
			upTable1();
			return true;
		}
		return false;
	}

	public void clearTable1() {
		while (tableDSPhong.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
	public void clearTable2() {
		while (tableDSPhong2.getRowCount() > 0) {
			model1.removeRow(0);
		}
	}
	public void clearTable3() {
		while (tableDSDichVu.getRowCount() > 0) {
			model2.removeRow(0);
		}
	}
	public void clearTable() {
		clearTable1();
		clearTable2();
		clearTable3();
	}

	public void ktraKH() {
		String sdt = txtSDT.getText();
		KhachHang kh = dsTP.ktraKHTheoSDt(sdt);
		if (kh != null) {
			txtKhachHang.setText(kh.getHoTenKhachHang());
		} else {
			JOptionPane.showMessageDialog(this, "Khách hàng chưa có trong hệ thống \nthêm khách hàng mới!!!");
		}
		if (sdt.equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
			txtSDT.requestFocus();
		} else if (!sdt.matches("\\d{10}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không quá 10 số và ko có kí tự");
			txtSDT.setText("");
			txtSDT.requestFocus();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnThuePhong) {
			if (ktraRegex())
				thuePhong();
		}
		if(o == btnLamMoi) {
			lamMoi();
		}
		if(o == btnTatCa) {
			loaiPALL();
		}
		if(o == btnChuyenPhong) {
			new Frm_ChuyenPhong().setVisible(true);
		}
	}
	public void loaiPALL() {
		clearTable1();
		if(radioDangThue.isSelected()) {
			int i = 0;
			ArrayList<Phong> list = dsPhong.getDSPhong();
			for (Phong p : list) {
				Object[] obj = new Object[6];
				obj[0] = p.getMaPhong().trim();
				obj[4] = p.getMaTinhTrangPhong().getTenTinhTrangPhong();
				if (p.getMaTinhTrangPhong().getMaTinhTrangPhong().equals("BOOK")
						|| p.getMaTinhTrangPhong().getMaTinhTrangPhong().equals("EMPT"))
					break;
				obj[2] = p.getSucChua();
				obj[1] = p.getMaLoaiPhong().getTenLoaiPhong();
				obj[3] = df.format(p.getGiaPhong());
				model.addRow(obj);
			}
		}else {
			int i = 0;
			ArrayList<Phong> list = dsPhong.getDSPhong();
			for (Phong p : list) {
				Object[] obj = new Object[6];
				obj[0] = p.getMaPhong().trim();
				obj[4] = p.getMaTinhTrangPhong().getTenTinhTrangPhong();
				if (p.getMaTinhTrangPhong().getMaTinhTrangPhong().equals("BOOK")
						|| p.getMaTinhTrangPhong().getMaTinhTrangPhong().equals("RENT"))
					break;
				obj[2] = p.getSucChua();
				obj[1] = p.getMaLoaiPhong().getTenLoaiPhong();
				obj[3] = df.format(p.getGiaPhong());
				model.addRow(obj);
			}
		}
	}
	public void clickTable() {
		int row = tableDSPhong.getSelectedRow();
		int i = 0;
		while (tableDSPhong2.getRowCount() > 0) {
			if (tableDSPhong2.getValueAt(i, 1).equals(tableDSPhong.getValueAt(row, 0))) {
				tableDSPhong2.setRowSelectionInterval(i, i);
				break;
			}
			i++;
		}
	}
	public void lamMoi() {
		clearTable();
		upTable1();
		upTable2();
		txtSDT.setText("");
		txtKhachHang.setText("");
		tableDSPhong.clearSelection();
		tableDSPhong2.clearSelection();
		tableDSDichVu.clearSelection();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o == lbIconSearch)
			ktraKH();
		if (o == tableDSPhong)
			clickTable();
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
		Border bottomBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
		// Đặt đường gạch chân cho JLabel
		lbIconSearch.setBorder(bottomBorder);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK);
		// Đặt đường gạch chân cho JLabel
		lbIconSearch.setBorder(bottomBorder);
	}
}
