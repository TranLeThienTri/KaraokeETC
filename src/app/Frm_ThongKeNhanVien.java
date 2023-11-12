package app;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Panel;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.mindfusion.scheduling.Cursor;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DanhSachHoaDon;
import dao.DanhSachKhachHang;
import dao.DanhSachNhanVien;
import entitys.KhachHang;
import entitys.NhanVien;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class Frm_ThongKeNhanVien extends JFrame implements ActionListener, MouseListener {
	private LocalDate now;
	private int ngay;
	private int thang;
	private int nam;
	private Date dNow;
	private JDateChooser dateChooserThongKeNgayBatDau;
	private JDateChooser dateChooserThongKeNgayKetThuc;
	private JTable table;
	private Panel panel_tong;
	private DefaultTableModel model;
	private JLabel lbltg, lbltongtk1, lbltongtk2, lblthongke1, lblthongke2;
	private DecimalFormat df;
	private DecimalFormat dfs;
	private DecimalFormat dfh;
	private SimpleDateFormat sf;
	private JScrollPane scrollPane;
	private JPanel panel_tknv, panel_ngay, panel_thongke1, panel_thongke2;
	private JLabel lbltknv, lbltgtk, lblnbd, lblnkt, lbliconthongke1, lbliconthongke2, lblbackground;
	private JTableHeader tbHeader;
	private JButton btnThongKe, btnLamMoi;
	DanhSachNhanVien dsNV;
	DanhSachHoaDon dsHD;

	public Panel getFrmThongKeNhanVien() {
		return this.panel_tong;
	}

	public static void main(String[] args) {
		new Frm_ThongKeNhanVien().setVisible(true);

	}

	/**
	 * Create the application.
	 */
	public Frm_ThongKeNhanVien() {
		setTitle("THỐNG KÊ NHÂN VIÊN");
		setSize(1400, 670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);

		gui();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void gui() {
		getContentPane().setLayout(null);
		panel_tong = new Panel();
		panel_tong.setBounds(0, 0, 1400, 670);
		getContentPane().add(panel_tong);
		panel_tong.setLayout(null);

		panel_tknv = new JPanel();
		panel_tknv.setBackground(new Color(0, 0, 0));
		panel_tknv.setBounds(536, 10, 298, 41);
		panel_tong.add(panel_tknv);
		panel_tknv.setLayout(null);
		lbltg = new JLabel("");
		lbltg.setForeground(new Color(255, 255, 255));
		lbltg.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltg.setBounds(550, 62, 310, 41);
		panel_tong.add(lbltg);

		lbltknv = new JLabel("THỐNG KÊ NHÂN VIÊN");
		lbltknv.setForeground(new Color(255, 255, 255));
		lbltknv.setBounds(52, 10, 223, 20);
		panel_tknv.add(lbltknv);
		lbltknv.setFont(new Font("Tahoma", Font.BOLD, 18));

		lbltgtk = new JLabel("Thời gian thống kê:");
		lbltgtk.setForeground(new Color(255, 255, 255));
		lbltgtk.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltgtk.setBounds(394, 70, 165, 25);
		panel_tong.add(lbltgtk);
		panel_ngay = new JPanel();
		panel_ngay.setBackground(new Color(190, 157, 157, 190));
		panel_ngay.setBounds(10, 112, 427, 228);
		panel_tong.add(panel_ngay);
		panel_ngay.setLayout(null);

		lblnbd = new JLabel("Ngày bắt đầu:");
		lblnbd.setForeground(new Color(255, 255, 255));
		lblnbd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnbd.setBounds(10, 22, 128, 38);
		panel_ngay.add(lblnbd);
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue() - 1;
		nam = now.getYear() - 1900;

		dNow = new Date(nam, thang, ngay);

		dateChooserThongKeNgayBatDau = new JDateChooser();
		dateChooserThongKeNgayBatDau.getCalendarButton()
				.setIcon(new ImageIcon(Frm_ThongKeNhanVien.class.getResource("/imgs/calendar.png")));
		dateChooserThongKeNgayBatDau.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooserThongKeNgayBatDau.setDateFormatString("dd/MM/yyyy");

		dateChooserThongKeNgayBatDau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooserThongKeNgayBatDau.getCalendarButton().setPreferredSize(new Dimension(40, 30));
		dateChooserThongKeNgayBatDau
				.setIcon(new ImageIcon(Frm_ThongKeNhanVien.class.getResource("/imgs/calendar.png")));

		dateChooserThongKeNgayBatDau.setBounds(149, 22, 226, 38);
		dateChooserThongKeNgayBatDau.setDate(dNow);
		panel_ngay.add(dateChooserThongKeNgayBatDau);

		lblnkt = new JLabel("Ngày kết thúc:");
		lblnkt.setForeground(new Color(255, 255, 255));
		lblnkt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnkt.setBounds(10, 80, 128, 38);
		panel_ngay.add(lblnkt);
		dateChooserThongKeNgayKetThuc = new JDateChooser();
		dateChooserThongKeNgayKetThuc.setDateFormatString("dd/MM/yyyy");

		dateChooserThongKeNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooserThongKeNgayKetThuc.getCalendarButton().setPreferredSize(new Dimension(40, 30));
		dateChooserThongKeNgayKetThuc
				.setIcon(new ImageIcon(Frm_ThongKeNhanVien.class.getResource("/imgs/calendar.png")));

		dateChooserThongKeNgayKetThuc.setBounds(149, 80, 226, 38);
		dateChooserThongKeNgayKetThuc.setDate(dNow);
		panel_ngay.add(dateChooserThongKeNgayKetThuc);

		btnThongKe = new FixButton("Thống kê");
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThongKe.setBounds(84, 128, 253, 42);
		btnThongKe.setIcon(new ImageIcon(Frm_ThongKeNhanVien.class.getResource("/imgs/icon_thongke.png")));
		panel_ngay.add(btnThongKe);

		btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBounds(84, 176, 253, 42);
		btnLamMoi.setIcon(new ImageIcon(Frm_ThongKeNhanVien.class.getResource("/imgs/icon_lammoi.png")));
		panel_ngay.add(btnLamMoi);

		panel_thongke1 = new JPanel();
		panel_thongke1.setBackground(new Color(190, 157, 157, 190));
		panel_thongke1.setBounds(447, 112, 471, 228);
		panel_tong.add(panel_thongke1);
		panel_thongke1.setLayout(null);

		lbliconthongke1 = new JLabel("");
		lbliconthongke1.setForeground(new Color(255, 255, 255));
		lbliconthongke1.setBounds(205, 25, 64, 64);
		panel_thongke1.add(lbliconthongke1);
		lbliconthongke1.setIcon(new ImageIcon(Frm_ThongKeNhanVien.class.getResource("/imgs/icon_tong.png")));

		lblthongke1 = new JLabel("");
		lblthongke1.setForeground(new Color(255, 255, 255));
		lblthongke1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblthongke1.setBounds(136, 114, 199, 33);
		panel_thongke1.add(lblthongke1);
		lbltongtk1 = new JLabel("");
		lbltongtk1.setForeground(new Color(255, 255, 255));
		lbltongtk1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltongtk1.setBounds(220, 131, 70, 43);
		panel_thongke1.add(lbltongtk1);

		panel_thongke2 = new JPanel();
		panel_thongke2.setBackground(new Color(190, 157, 157, 190));
		panel_thongke2.setBounds(928, 112, 448, 228);
		panel_tong.add(panel_thongke2);
		panel_thongke2.setLayout(null);

		lbliconthongke2 = new JLabel("");
		lbliconthongke2.setBounds(205, 25, 64, 64);
		panel_thongke2.add(lbliconthongke2);
		lbliconthongke2.setIcon(new ImageIcon(Frm_ThongKeNhanVien.class.getResource("/imgs/icon_funnel.png")));

		lblthongke2 = new JLabel("");
		lblthongke2.setForeground(new Color(255, 255, 255));
		lblthongke2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblthongke2.setBounds(91, 114, 265, 28);
		panel_thongke2.add(lblthongke2);
		lbltongtk2 = new JLabel("");
		lbltongtk2.setForeground(new Color(255, 255, 255));
		lbltongtk2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltongtk2.setBounds(200, 131, 79, 41);
		panel_thongke2.add(lbltongtk2);
		String col[] = { "M\u00E3 NV", "H\u1ECD v\u00E0 t\u00EAn", "N\u0103m sinh", "CCCD", "Gi\u1EDBi t\u00EDnh",
				"\u0110\u1ECBa ch\u1EC9", "SDT", "Ch\u1EE9c v\u1EE5" };
		model = new DefaultTableModel(col, 0);
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tbHeader = table.getTableHeader();
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		table.setBackground(Color.white);
		table.setFont(new Font("SansSerif", Font.PLAIN, 14));
		table.setSelectionBackground(new Color(158, 207, 0));
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setRowHeight(30);
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 350, 1400, 268);
		scrollPane.getHorizontalScrollBar();
		scrollPane.setViewportView(table);
		panel_tong.add(scrollPane);
		lblbackground = new JLabel("");
		lblbackground.setBounds(0, 0, 1400, 700);
		panel_tong.add(lblbackground);
		lblbackground.setIcon(new ImageIcon(Frm_ThongKeNhanVien.class.getResource("/imgs/bg_chot1.png")));
		// khai bao định dạng
		df = new DecimalFormat("###,### VNĐ");
		dfs = new DecimalFormat("### p");
		dfh = new DecimalFormat("### h");
		sf = new SimpleDateFormat("dd/MM/yyy");
		// add su kien button
		btnThongKe.addActionListener(this);
		btnLamMoi.addActionListener(this);
		table.addMouseListener(this);
		// kết nối data
		ConnectDB.getInstance().connect();
		// Danh sach Khach Hang
		dsNV = new DanhSachNhanVien();
		dsHD = new DanhSachHoaDon();
	}

	/**
	 * Đưa dữ liệu từ danh sách lên bảng
	 */
	public void upTable(ArrayList<NhanVien> list) {
		int i = 0;
		for (NhanVien nv  : list) {
			Object[] obj = new Object[8];
			obj[0] = nv.getMaNhanVien().trim();
			obj[1] = nv.getHoTenNhanVien().trim();
			obj[2] = nv.getNgaySinh();
			obj[3] = nv.getSoCCCD().trim();
			String gt;
			if (!nv.isGioiTinh())
				gt = "Nữ";
			else
				gt = "Nam";
			obj[4] = gt;
			obj[5] = nv.getDiaChi().trim();
			obj[6] = nv.getSdt().trim();
			obj[7] = nv.getchucVu().getTenChucVu();
			if (table.getRowCount() == 0)
				model.addRow(obj);
			else {
				for (i = 0; i < table.getRowCount(); i++) {
					if (obj[0].toString().equals(table.getValueAt(i, 0)))
						break;
				}
				if (i == table.getRowCount())
					model.addRow(obj);
			}
		}

	}

	/**
	 * Thống kê số nhân viên theo ngày
	 */
	public void loadThongKeNhanVien() {

		java.util.Date utilngayBD = dateChooserThongKeNgayBatDau.getDate();
		java.util.Date utilngayKT = dateChooserThongKeNgayKetThuc.getDate();
		@SuppressWarnings("deprecation")
		Date ngayBatDau = new Date(utilngayBD.getYear(), utilngayBD.getMonth(), utilngayBD.getDate());
		@SuppressWarnings("deprecation")
		Date ngayKetThuc = new Date(utilngayKT.getYear(), utilngayKT.getMonth(), utilngayKT.getDate());
		if (ngayBatDau.before(ngayKetThuc) || ngayBatDau.equals(ngayKetThuc)) {
			ArrayList<NhanVien> listHD = dsHD.getDSNVTheoNgay(ngayBatDau, ngayKetThuc);
			int tong = dsHD.tongSoNVTheoNgay(ngayBatDau, ngayKetThuc);
			lblthongke1.setText("Tổng số nhân viên:");
			lbltongtk1.setText(String.valueOf(tong));
			upTable(listHD);
		} else
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước hoặc bằng ngày kết thúc!");
	}

	/**
	 * 
	 * @param ma là mã nhân viên
	 * @return tổng số hóa đơn theo mã nhân viên trong ngày được chọn thống kê
	 */
	public int soHDTheoMaTheoNgay(String ma) {
		int tong = 0;
		java.util.Date utilngayBD = dateChooserThongKeNgayBatDau.getDate();
		java.util.Date utilngayKT = dateChooserThongKeNgayKetThuc.getDate();
		DanhSachKhachHang dao = new DanhSachKhachHang();
		@SuppressWarnings("deprecation")
		Date ngayBatDau = new Date(utilngayBD.getYear(), utilngayBD.getMonth(), utilngayBD.getDate());
		@SuppressWarnings("deprecation")
		Date ngayKetThuc = new Date(utilngayKT.getYear(), utilngayKT.getMonth(), utilngayKT.getDate());
		tong = dsHD.getSoHDTheoMaTheoNgay(ma, ngayBatDau, ngayKetThuc);
		return tong;
	}

	/**
	 * sự kiện click cột trong table hiện lên thống kê tổng số hóa đơn của nhân viên
	 * 
	 */
	public void setTextTB() {
		int row = table.getSelectedRow();
		String ma = (String) table.getValueAt(row, 0);
		int tong = soHDTheoMaTheoNgay(ma);
		lblthongke2.setText("Tổng số hóa đơn của nhân viên:");
		lbltongtk2.setText(String.valueOf(tong));
	}

	/**
	 * đưa ngày được chọn thống kê lên thời gian thống kê
	 */
	public void loadThongKeSoGio() {
		java.util.Date utilngayBD = dateChooserThongKeNgayBatDau.getDate();
		java.util.Date utilngayKT = dateChooserThongKeNgayKetThuc.getDate();
		@SuppressWarnings("deprecation")
		Date ngayden = new Date(utilngayBD.getYear(), utilngayBD.getMonth(), utilngayBD.getDate());
		@SuppressWarnings("deprecation")
		Date ngayKT = new Date(utilngayKT.getYear(), utilngayKT.getMonth(), utilngayKT.getDate());
		lbltg.setText(sf.format(ngayden) + " - " + sf.format(ngayKT));

	}

	/**
	 * xóa tất cả dữ liệu trên frame đưa về mặc định
	 */
	public void resetAll() {
		dateChooserThongKeNgayBatDau.setDate(dNow);
		dateChooserThongKeNgayKetThuc.setDate(dNow);
		lbltongtk1.setText("");
		lbltongtk2.setText("");
		lblthongke1.setText("");
		lblthongke2.setText("");
		lbltg.setText("");
		clearTable();

	}

	/**
	 * xóa dữ liệu của table
	 */
	public void clearTable() {
		while (table.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	/**
	 * xóa dữ liệu của thống kê tổng số hóa đơn của nhân viên
	 */
	public void clearTK2() {
		lbltongtk2.setText("");
		lblthongke2.setText("");
	}

	/**
	 * sự kiện click chuột vào table
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		setTextTB();
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

	/**
	 * Sự kiện nút thống kê và làm mới
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnThongKe) {
			clearTable();
			clearTK2();
			loadThongKeSoGio();
			loadThongKeNhanVien();
		} else if (o == btnLamMoi) {
			resetAll();
		}
	}
}
