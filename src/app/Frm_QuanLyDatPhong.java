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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DanhSachDatPhong;
import dao.DanhSachKhachHang;
import dao.DanhSachNhanVien;
import dao.Dao_PhatSinhMa;
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.LoaiDichVu;
import entitys.NhanVien;
import entitys.Phong;

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
import java.awt.Component;
import javax.swing.SwingConstants;

public class Frm_QuanLyDatPhong extends JFrame implements ActionListener, MouseListener, PropertyChangeListener {
	private JScrollPane scrDSPDD;
	private JScrollPane scrollPane;
	private JPanel pnLoaiPhong, pnDSP, pnTTDDP;
	private JLabel lbLoaiPhongTK, lbTinhTrang, lbDSPhong, lbBGQLDP, lbTTDDP, lbSDT, lbTenKH, lbLoaiKH, lbNgayDat,
			lbThoiGianDat, lbIconSearch;
	private JComboBox comboLKH, cbGio, cbPhut;
	private JTextField txtSDT, txtKhachHang;
	private FixButton btnLamMoi, btnHuyDatPhong, btnDatPhong, btnNhanPhong;
	private FixButton2 btnTatCa, btnPhongThuong, btnPhongVip;
	private JDateChooser ngayDatPhong;
	private JRadioButton rDangDat, radioTrong;
	private Date ngayHienTai;
	private Panel pnQLDP;
	private int ngay, thang, nam;
	private JTable tableDSPhong, tableDSPhong1;
	private DefaultTableModel model, model1;
	private ButtonGroup bg;
	public DanhSachDatPhong dsDP;
	public DanhSachKhachHang dsKH;
	Date ngayDat;
	String dateString;

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

		JPanel pnDSPDD = new JPanel();
		pnDSPDD.setLayout(null);
		pnDSPDD.setBackground(Color.WHITE);
		pnDSPDD.setBounds(32, 358, 982, 264);
		pnQLDP.add(pnDSPDD);

		JLabel lbDSPDD = new JLabel("Danh phòng đã đặt");
		lbDSPDD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPDD.setBounds(10, 0, 145, 25);
		pnDSPDD.add(lbDSPDD);

		pnTTDDP = new JPanel();
		pnTTDDP.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnTTDDP.setBackground(new java.awt.Color(207, 169, 0));
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
		lbSDT.setBounds(80, 41, 200, 38);
		pnTTDDP.add(lbSDT);

		lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(80, 76, 200, 38);
		pnTTDDP.add(lbTenKH);

		lbLoaiKH = new JLabel("Loại khách hàng:");
		lbLoaiKH.setForeground(Color.WHITE);
		lbLoaiKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiKH.setBounds(80, 116, 200, 38);
		pnTTDDP.add(lbLoaiKH);

		lbNgayDat = new JLabel("Ngày đặt:");
		lbNgayDat.setForeground(Color.WHITE);
		lbNgayDat.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbNgayDat.setBounds(80, 155, 95, 38);
		pnTTDDP.add(lbNgayDat);

		lbThoiGianDat = new JLabel("Thời gian:");
		lbThoiGianDat.setForeground(Color.WHITE);
		lbThoiGianDat.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbThoiGianDat.setBounds(80, 203, 90, 25);
		pnTTDDP.add(lbThoiGianDat);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtSDT.setBounds(230, 50, 300, 25);
		pnTTDDP.add(txtSDT);

		txtKhachHang = new JTextField();
		txtKhachHang.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtKhachHang.setBounds(230, 85, 300, 25);
		pnTTDDP.add(txtKhachHang);

		comboLKH = new JComboBox();
		comboLKH.setEnabled(false);
		comboLKH.setModel(new DefaultComboBoxModel(new String[] { "Khách hàng VIP", "Khách hàng thường" }));
		comboLKH.setSelectedIndex(0);
		comboLKH.setFont(new Font("Tahoma", Font.ITALIC, 15));
		comboLKH.setBounds(230, 120, 300, 30);
		pnTTDDP.add(comboLKH);

		lbIconSearch = new JLabel();
		lbIconSearch.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/icon_search.png")));
		lbIconSearch.setBounds(540, 52, 29, 20);
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

		LocalDateTime localDateTime = LocalDateTime.now();
		ngay = localDateTime.getDayOfMonth();
		thang = localDateTime.getMonthValue();
		nam = localDateTime.getYear();
		ngayHienTai = new Date(nam - 1900, thang - 1, ngay);
		ngayDatPhong.setDate(ngayHienTai);
		pnTTDDP.add(ngayDatPhong);

		cbGio = new JComboBox();
		cbGio.setFont(new Font("Tahoma", Font.BOLD, 18));
		cbGio.setModel(new DefaultComboBoxModel(new String[] { "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24" }));
		cbGio.setBounds(230, 199, 50, 30);
		pnTTDDP.add(cbGio);

		cbPhut = new JComboBox();
		cbPhut.setModel(new DefaultComboBoxModel(new String[] { "00", "15", "30", "45" }));
		cbPhut.setFont(new Font("Tahoma", Font.BOLD, 18));
		cbPhut.setBounds(290, 199, 50, 30);
		pnTTDDP.add(cbPhut);

		pnLoaiPhong = new JPanel();
		pnLoaiPhong.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnLoaiPhong.setBackground(new java.awt.Color(189, 0, 88));
		pnLoaiPhong.setBounds(700, 10, 650, 80);
		pnQLDP.add(pnLoaiPhong);
		pnLoaiPhong.setLayout(null);

		lbLoaiPhongTK = new JLabel("Loại phòng:");
		lbLoaiPhongTK.setForeground(Color.WHITE);
		lbLoaiPhongTK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiPhongTK.setBounds(46, 11, 100, 25);
		pnLoaiPhong.add(lbLoaiPhongTK);

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

		lbTinhTrang.setBounds(46, 50, 100, 25);

		pnLoaiPhong.add(lbTinhTrang);

		rDangDat = new JRadioButton("Đang đặt");
		rDangDat.setOpaque(false);
		rDangDat.setFont(new Font("Tahoma", Font.BOLD, 15));
		rDangDat.setSelected(true);
		rDangDat.setBounds(164, 50, 111, 21);
		pnLoaiPhong.add(rDangDat);

		radioTrong = new JRadioButton("Trống");
		radioTrong.setContentAreaFilled(false);
		radioTrong.setFont(new Font("Tahoma", Font.BOLD, 15));
		radioTrong.setBounds(295, 50, 103, 21);
		pnLoaiPhong.add(radioTrong);

		bg = new ButtonGroup();
		bg.add(radioTrong);
		bg.add(rDangDat);

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
		model1 = new DefaultTableModel(col, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Không cho phép chỉnh sửa ô
			}
		};

		tableDSPhong = new JTable(model1);
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

		scrollPane = new JScrollPane(tableDSPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 25, 650, 200);
		scrollPane.getHorizontalScrollBar();
		pnDSP.add(scrollPane);

		scrollPane.setViewportView(tableDSPhong);

		String col1[] = { "Mã hóa đơn", "Mã phòng", "Tên khách hàng", "SĐT", "Ngày", "Thời gian", "Tên nhân viên" };
		model = new DefaultTableModel(col1, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Không cho phép chỉnh sửa ô
			}
		};
		tableDSPhong1 = new JTable(model);
		tableDSPhong1.setBackground(Color.WHITE);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader1 = tableDSPhong1.getTableHeader();
		tbHeader1.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader1.setForeground(Color.WHITE);
		tbHeader1.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

		tableDSPhong1.setBackground(Color.white);
		tableDSPhong1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSPhong1.setSelectionBackground(new Color(158, 207, 0));
		tableDSPhong1.setSelectionForeground(new Color(255, 255, 255));
		tableDSPhong1.setRowHeight(30);

		scrDSPDD = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrDSPDD.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrDSPDD.setBackground(Color.BLACK);
		scrDSPDD.setBounds(0, 25, 982, 229);
		pnDSPDD.add(scrDSPDD);
		scrDSPDD.setViewportView(tableDSPhong1);

		btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_lammoi.png")));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBounds(1116, 571, 190, 51);
		pnQLDP.add(btnLamMoi);

		btnHuyDatPhong = new FixButton("Hủy đặt phòng");
		btnHuyDatPhong.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_huydv.png")));
		btnHuyDatPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuyDatPhong.setBounds(1116, 499, 190, 51);
		pnQLDP.add(btnHuyDatPhong);

		btnDatPhong = new FixButton("Đặt phòng");
		btnDatPhong.setMouseHoverEnable(true);
		btnDatPhong.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_xacnhan.png")));
		btnDatPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDatPhong.setBounds(1116, 430, 190, 51);
		pnQLDP.add(btnDatPhong);

		btnNhanPhong = new FixButton("Nhận phòng");
		btnNhanPhong.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_nhanphong.png")));
		btnNhanPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNhanPhong.setBounds(1116, 358, 190, 51);
		pnQLDP.add(btnNhanPhong);

		// add background ở cuối
		lbBGQLDP = new JLabel("");
		lbBGQLDP.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDP.setBounds(0, 0, 1400, 670);
		pnQLDP.add(lbBGQLDP);

		btnDatPhong.addActionListener(this);
		btnHuyDatPhong.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnNhanPhong.addActionListener(this);
		btnPhongThuong.addActionListener(this);
		btnPhongVip.addActionListener(this);
		btnTatCa.addActionListener(this);

		btnDatPhong.addMouseListener(this);
		btnHuyDatPhong.addMouseListener(this);
		btnLamMoi.addMouseListener(this);
		btnNhanPhong.addMouseListener(this);
		btnPhongThuong.addMouseListener(this);
		btnPhongVip.addMouseListener(this);
		btnTatCa.addMouseListener(this);
		lbIconSearch.addMouseListener(this);
		tableDSPhong.addMouseListener(this);
		tableDSPhong1.addMouseListener(this);
		ngayDatPhong.addPropertyChangeListener(this);
		ConnectDB.getInstance().connect();
		dsDP = new DanhSachDatPhong();
		dsKH = new DanhSachKhachHang();

		ngayDat = ngayDatPhong.getDate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateString = dateFormat.format(ngayDat);

		upTable1(dsDP.getAllRoomByDate(dateString));
		upTable2(dsDP.getAllRoomStatusByDate());
		getIndexRow();
	}

	public static void main(String[] args) {
		new Frm_QuanLyDatPhong().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnDatPhong) {
			boolean isBooked = datPhong();
		} else if (o == btnNhanPhong) {

		} else if (o == btnLamMoi) {
			xoaTrang();
		} else if (o == btnPhongThuong) {
			ArrayList<Phong> listN = dsDP.getAllRoomByType("NOR");
			upTable1(listN);
		} else if (o == btnTatCa) {
			ArrayList<Phong> list = dsDP.getAllRoomByDate(dateString);
			upTable1(list);
		} else if (o == btnPhongVip) {
			ArrayList<Phong> listT = dsDP.getAllRoomByType("VIP");
			upTable1(listT);
		} else {

		}
	}

	public void getIndexRow() {
		int isSelected1 = tableDSPhong.getSelectedRow();
		int isSelected2 = tableDSPhong1.getSelectedRow();

		if (isSelected1 == -1 && isSelected2 == -1) {
			btnDatPhong.setEnabled(false);
			btnNhanPhong.setEnabled(false);
			tableDSPhong.clearSelection();
			tableDSPhong1.clearSelection();
		}

		if (isSelected1 != -1) {
			btnDatPhong.setEnabled(true);
			btnNhanPhong.setEnabled(false);
			tableDSPhong1.clearSelection();
		}

		if (isSelected2 != -1) {
			btnDatPhong.setEnabled(false);
			btnNhanPhong.setEnabled(true);
			tableDSPhong.clearSelection();
		}
	}

	public void upTable1(ArrayList<Phong> arr) {
		model1.setRowCount(0);
		for (Phong p : arr) {
			Object[] obj = new Object[5];
			obj[0] = p.getMaPhong().trim();
			obj[1] = p.getMaLoaiPhong().getTenLoaiPhong();
			obj[2] = p.getSucChua();
			obj[3] = p.getGiaPhong();
			obj[4] = p.getMaTinhTrangPhong().getTenTinhTrangPhong();
			model1.addRow(obj);
		}
	}

	public void upTable2(ArrayList<HoaDonPhong> arr) {
		model.setRowCount(0);
		for (HoaDonPhong hd : arr) {
			Object[] obj = new Object[7];
			obj[0] = hd.getMaHoaDon().trim();
			obj[1] = hd.getPhong().getMaPhong().trim();
			obj[2] = hd.getMaKhachHang().getHoTenKhachHang().trim();
			obj[3] = hd.getMaKhachHang().getSoDienThoai().trim();

			obj[4] = hd.getNgayDat().toString();
			obj[5] = hd.getGioDat().toString();
			obj[6] = hd.getMaNhanVien().getHoTenNhanVien().trim();
			model.addRow(obj);
		}
	}

	public void ktraKHDAT() {
		String sdt = txtSDT.getText();
		KhachHang kh = dsKH.getKhachHangTheoSDT(sdt);
		if (kh != null) {
			txtKhachHang.setText(kh.getHoTenKhachHang());
			if (kh.getLoaiKhachHang().getMaLoaiKhachHang().equalsIgnoreCase("VIP"))
				comboLKH.setSelectedIndex(0);
			else
				comboLKH.setSelectedIndex(1);
		} else {
			int opt = JOptionPane.showConfirmDialog(this, "Khách hàng chưa có trong hệ thống,\nThêm khách hàng!",
					"Thông báo", JOptionPane.YES_NO_OPTION);
			if (opt == JOptionPane.YES_OPTION) {
				Frm_ThemKhachHang frm_ThemKH = new Frm_ThemKhachHang();
				frm_ThemKH.setVisible(true);
			}
		}
	}

	public void xoaTrang() {
		txtKhachHang.setText("");
		ngayDatPhong.setDate(ngayHienTai);
		txtSDT.setText("");
		comboLKH.setSelectedIndex(0);
		cbGio.setSelectedIndex(0);
		cbPhut.setSelectedIndex(0);
		tableDSPhong.clearSelection();
		tableDSPhong1.clearSelection();
	}

	private boolean datPhong() {
		String sdt = txtSDT.getText().trim();
		String tenKhachString = txtKhachHang.getText().trim();

		KhachHang kh = dsKH.getKhachHangTheoSDT(sdt);
		if (kh != null) {
			ngayDat = ngayDatPhong.getDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = dateFormat.format(ngayDat);

			Object[] obj = new Object[8];

//	    	String ma = tableDSPhong1.getValueAt(row, 0).toString();

//	    	String ten = txtHoTen.getText();
//			String sdt = txtSDT.getText();
//			String dt = txtDiaChi.getText();
//			String cccd = txtCCCD.getText();
//			String gt = (String) comboGT.getSelectedItem();
//	        
		}

		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		getIndexRow();
		Object o = e.getSource();
		if (o == lbIconSearch) {
			ktraKHDAT();
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

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		Object o = evt.getSource();
		 if (o == ngayDatPhong) {
			ngayDat = ngayDatPhong.getDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateString = dateFormat.format(ngayDat);
			upTable1(dsDP.getAllRoomByDate(dateString));
//			getIndexRow();
			ArrayList<Phong> list = dsDP.getAllRoomByDate(dateString);
			upTable1(list);
		}
	}

}
