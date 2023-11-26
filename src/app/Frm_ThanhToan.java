package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.standard.PrintQuality;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mindfusion.common.Duration;
import com.toedter.calendar.JDateChooser;

import dao.DanhSachDichVu;
import dao.DanhSachHoaDon;
import dao.DanhSachPhong;
import dao.ThuePhong;
import entitys.ChiTietHoaDon;
import entitys.DichVu;
import entitys.HoaDonPhong;
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

public class Frm_ThanhToan extends JFrame implements ActionListener {
	JPanel pnDSDichVu;
	JLabel lbDSDichVu, lbBGQLDV, lbHoaDon, lbDiaChi, lbSDT, lbSoLuongTon, lbMaPhong;
	JTextField txtSDT;
	JPanel pnThanhToan;
	private JTable tableDSDichVu;
	private DefaultTableModel model;
	private JLabel lbTenNV, lblNguynVn, lbSDT_1, lblHd, lblMp, lblNguynVnA, lbHDTT, lbTenKH, lbDiemTL, lbGioBatDau,
			lbGioKetThuc, lbThoiGian, lbTenKH_1, lbDiemTL_1, lbGioBatDau_1, lbGioKetThuc_1, lbThoiGianThue,
			lbTongTienDV, lbPhuThu, lbTongTienGio, lbGiamGia, lbPhiVAT, lbTongHD, lbTongThanhToan, lbTongTienDV_1,
			lblPhuThu, lblTonggio, lblVAT, lblGiamGia, lblTongtien, lblTongthanhtoan, lbCamOn, lbTongGT, lbTongGT_1;
	FixButton btnXacNhan, btnHuy;
	private DecimalFormat df;
	private DateTimeFormatter dt;
	private SimpleDateFormat sf;
	DanhSachHoaDon dsHD;
	HoaDonPhong hd;
	ChiTietHoaDon ct;
	DanhSachPhong p;
	ThuePhong tp;
	String mahd, makh, map, tinhtrang;
	Float tien;
	LocalTime giotra;
	private JLabel lbSDTQuan;

	public JPanel getFrmThanhToan() {
		return this.pnThanhToan;
	}

	public Frm_ThanhToan(HoaDonPhong hd) {
		setTitle("THANH TOÁN HÓA ĐƠN");
		setSize(700, 820);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		this.hd = hd;
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnThanhToan = new JPanel();
		pnThanhToan.setBounds(0, 0, 700, 820);
		getContentPane().add(pnThanhToan);
		pnThanhToan.setLayout(null);

		lbHoaDon = new JLabel("KARAOKE ETC");
		lbHoaDon.setFont(new Font("Tahoma", Font.BOLD, 40));
		lbHoaDon.setForeground(Color.BLACK);
		lbHoaDon.setBounds(215, 10, 357, 50);
		pnThanhToan.add(lbHoaDon);

		lbDiaChi = new JLabel("Địa chỉ:");
		lbDiaChi.setForeground(Color.BLACK);
		lbDiaChi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbDiaChi.setBounds(22, 65, 73, 25);
		pnThanhToan.add(lbDiaChi);

		lbSDT = new JLabel("SĐT:");
		lbSDT.setForeground(Color.BLACK);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbSDT.setBounds(22, 85, 58, 25);
		pnThanhToan.add(lbSDT);

		lbSoLuongTon = new JLabel("Mã hóa đơn: ");
		lbSoLuongTon.setForeground(Color.BLACK);
		lbSoLuongTon.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbSoLuongTon.setBounds(22, 105, 105, 25);
		pnThanhToan.add(lbSoLuongTon);

		lbMaPhong = new JLabel("Mã phòng:");
		lbMaPhong.setForeground(Color.BLACK);
		lbMaPhong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbMaPhong.setBounds(22, 125, 98, 25);
		pnThanhToan.add(lbMaPhong);

		btnXacNhan = new FixButton("Làm mới");
		btnXacNhan.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_xacnhan.png")));
		btnXacNhan.setText("Xác nhận");
		btnXacNhan.setBounds(514, 731, 140, 40);
		pnThanhToan.add(btnXacNhan);
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnHuy = new FixButton("Hủy đặt phòng");
		btnHuy.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_huydv.png")));
		btnHuy.setText("Hủy");
		btnHuy.setBounds(344, 731, 140, 40);
		pnThanhToan.add(btnHuy);
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(22, 340, 630, 200);
		pnThanhToan.add(pnDSDichVu);
		pnDSDichVu.setLayout(null);

		lbDSDichVu = new JLabel("Danh sách dịch vụ");
		lbDSDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSDichVu.setBounds(10, 0, 150, 25);
		pnDSDichVu.add(lbDSDichVu);
		String col[] = { "STT", "Tên dịch vụ", "Loại dịch vụ", "Số lượng", "Đơn giá", "Tổng tiền" };
		model = new DefaultTableModel(col, 0);
		tableDSDichVu = new JTable(model);
		tableDSDichVu.setBackground(Color.WHITE);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader = tableDSDichVu.getTableHeader();
		tbHeader.setBackground(Color.BLACK);
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
		scrollPane.setBounds(0, 25, 630, 170);
		scrollPane.getHorizontalScrollBar();
		pnDSDichVu.add(scrollPane);
		scrollPane.setViewportView(tableDSDichVu);

		lbTenNV = new JLabel("Nhân viên: ");
		lbTenNV.setForeground(Color.BLACK);
		lbTenNV.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTenNV.setBounds(22, 145, 87, 25);
		pnThanhToan.add(lbTenNV);

		lblNguynVn = new JLabel("12 Nguyễn Văn Bảo, Phường 4, Quận Gò Vấp, TP.HCM");
		lblNguynVn.setForeground(Color.BLACK);
		lblNguynVn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNguynVn.setBounds(137, 65, 596, 25);
		pnThanhToan.add(lblNguynVn);

		lbSDT_1 = new JLabel("0382173105");
		lbSDT_1.setForeground(Color.BLACK);
		lbSDT_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbSDT_1.setBounds(535, 222, 243, 25);
		pnThanhToan.add(lbSDT_1);

		lblHd = new JLabel("");
		lblHd.setForeground(Color.BLACK);
		lblHd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHd.setBounds(137, 105, 292, 25);
		pnThanhToan.add(lblHd);

		lblMp = new JLabel("");
		lblMp.setForeground(Color.BLACK);
		lblMp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMp.setBounds(137, 125, 292, 25);
		pnThanhToan.add(lblMp);

		lblNguynVnA = new JLabel("");
		lblNguynVnA.setForeground(Color.BLACK);
		lblNguynVnA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNguynVnA.setBounds(137, 145, 292, 25);
		pnThanhToan.add(lblNguynVnA);

		lbHDTT = new JLabel("HÓA ĐƠN TÍNH TIỀN");
		lbHDTT.setForeground(Color.BLACK);
		lbHDTT.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbHDTT.setBounds(215, 180, 263, 30);
		pnThanhToan.add(lbHDTT);

		lbTenKH = new JLabel("Tên khách hàng: ");
		lbTenKH.setForeground(Color.BLACK);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTenKH.setBounds(22, 205, 140, 25);
		pnThanhToan.add(lbTenKH);

		lbDiemTL = new JLabel("Điểm tích lũy: ");
		lbDiemTL.setForeground(Color.BLACK);
		lbDiemTL.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbDiemTL.setBounds(22, 240, 122, 25);
		pnThanhToan.add(lbDiemTL);
		lbGioBatDau = new JLabel("Giờ bắt đầu: ");
		lbGioBatDau.setForeground(Color.BLACK);
		lbGioBatDau.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbGioBatDau.setBounds(22, 260, 105, 25);
		pnThanhToan.add(lbGioBatDau);

		lbGioKetThuc = new JLabel("Giờ kết thúc: ");
		lbGioKetThuc.setForeground(Color.BLACK);
		lbGioKetThuc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbGioKetThuc.setBounds(22, 280, 105, 25);
		pnThanhToan.add(lbGioKetThuc);

		lbThoiGian = new JLabel("Tổng thời gian:");
		lbThoiGian.setBackground(Color.WHITE);
		lbThoiGian.setForeground(Color.BLACK);
		lbThoiGian.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbThoiGian.setBounds(22, 300, 200, 25);
		pnThanhToan.add(lbThoiGian);
		lbTongGT = new JLabel("Ngày:");
		lbTongGT.setBackground(Color.WHITE);
		lbTongGT.setForeground(Color.BLACK);
		lbTongGT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTongGT.setBounds(22, 315, 112, 25);
		pnThanhToan.add(lbTongGT);

		lbTenKH_1 = new JLabel("");
		lbTenKH_1.setForeground(Color.BLACK);
		lbTenKH_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTenKH_1.setBounds(535, 205, 200, 25);
		pnThanhToan.add(lbTenKH_1);

		lbDiemTL_1 = new JLabel("");
		lbDiemTL_1.setForeground(Color.BLACK);
		lbDiemTL_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbDiemTL_1.setBounds(536, 240, 200, 25);
		pnThanhToan.add(lbDiemTL_1);

		lbGioBatDau_1 = new JLabel("");
		lbGioBatDau_1.setForeground(Color.BLACK);
		lbGioBatDau_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbGioBatDau_1.setBounds(536, 260, 200, 25);
		pnThanhToan.add(lbGioBatDau_1);

		lbGioKetThuc_1 = new JLabel("");
		lbGioKetThuc_1.setForeground(Color.BLACK);
		lbGioKetThuc_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbGioKetThuc_1.setBounds(536, 280, 200, 25);
		pnThanhToan.add(lbGioKetThuc_1);

		lbThoiGianThue = new JLabel("");
		lbThoiGianThue.setBackground(Color.WHITE);
		lbThoiGianThue.setForeground(Color.BLACK);
		lbThoiGianThue.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbThoiGianThue.setBounds(536, 300, 200, 25);
		pnThanhToan.add(lbThoiGianThue);
		lbTongGT_1 = new JLabel("");
		lbTongGT_1.setBackground(Color.WHITE);
		lbTongGT_1.setForeground(Color.BLACK);
		lbTongGT_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbTongGT_1.setBounds(536, 315, 200, 25);
		pnThanhToan.add(lbTongGT_1);
		lbTongTienDV = new JLabel("Tổng tiền dịch vụ: ");
		lbTongTienDV.setForeground(Color.BLACK);
		lbTongTienDV.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTongTienDV.setBounds(22, 545, 200, 25);
		pnThanhToan.add(lbTongTienDV);

		lbPhuThu = new JLabel("Phụ thu: ");
		lbPhuThu.setForeground(Color.BLACK);
		lbPhuThu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbPhuThu.setBounds(22, 565, 200, 25);
		pnThanhToan.add(lbPhuThu);

		lbTongTienGio = new JLabel("Tổng tiền giờ: ");
		lbTongTienGio.setForeground(Color.BLACK);
		lbTongTienGio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTongTienGio.setBounds(22, 585, 200, 25);
		pnThanhToan.add(lbTongTienGio);

		lbGiamGia = new JLabel("Giảm giá: ");
		lbGiamGia.setForeground(Color.BLACK);
		lbGiamGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbGiamGia.setBounds(22, 625, 200, 25);
		pnThanhToan.add(lbGiamGia);

		lbPhiVAT = new JLabel("Phí VAT: ");
		lbPhiVAT.setForeground(Color.BLACK);
		lbPhiVAT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbPhiVAT.setBounds(22, 645, 200, 25);
		pnThanhToan.add(lbPhiVAT);

		lbTongHD = new JLabel("Tổng hóa đơn: ");
		lbTongHD.setForeground(Color.BLACK);
		lbTongHD.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTongHD.setBounds(22, 605, 200, 25);
		pnThanhToan.add(lbTongHD);

		lbTongThanhToan = new JLabel("Tổng thanh toán: ");
		lbTongThanhToan.setBackground(Color.WHITE);
		lbTongThanhToan.setForeground(Color.BLACK);
		lbTongThanhToan.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbTongThanhToan.setBounds(22, 665, 200, 25);
		pnThanhToan.add(lbTongThanhToan);

		lbTongTienDV_1 = new JLabel("");
		lbTongTienDV_1.setForeground(Color.BLACK);
		lbTongTienDV_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTongTienDV_1.setBounds(536, 550, 200, 25);
		pnThanhToan.add(lbTongTienDV_1);

		lblPhuThu = new JLabel("");
		lblPhuThu.setForeground(Color.BLACK);
		lblPhuThu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhuThu.setBounds(536, 570, 200, 25);
		pnThanhToan.add(lblPhuThu);

		lblTonggio = new JLabel("");
		lblTonggio.setForeground(Color.BLACK);
		lblTonggio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTonggio.setBounds(536, 590, 200, 25);
		pnThanhToan.add(lblTonggio);

		lblVAT = new JLabel("");
		lblVAT.setForeground(Color.BLACK);
		lblVAT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVAT.setBounds(536, 645, 200, 25);
		pnThanhToan.add(lblVAT);

		lblGiamGia = new JLabel("");
		lblGiamGia.setBackground(Color.BLACK);
		lblGiamGia.setForeground(Color.BLACK);
		lblGiamGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGiamGia.setBounds(536, 625, 200, 25);
		pnThanhToan.add(lblGiamGia);

		lblTongtien = new JLabel("");
		lblTongtien.setForeground(Color.BLACK);
		lblTongtien.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongtien.setBounds(536, 605, 200, 25);
		pnThanhToan.add(lblTongtien);

		lblTongthanhtoan = new JLabel("");
		lblTongthanhtoan.setForeground(Color.BLACK);
		lblTongthanhtoan.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTongthanhtoan.setBounds(536, 665, 200, 25);
		pnThanhToan.add(lblTongthanhtoan);

		lbCamOn = new JLabel("XIN CẢM ƠN QUÝ KHÁCH VÀ HẸN GẶP LẠI ");
		lbCamOn.setForeground(Color.BLACK);
		lbCamOn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbCamOn.setBounds(180, 690, 365, 25);
		pnThanhToan.add(lbCamOn);

		JLabel lbSDTKH = new JLabel("SĐT khách hàng:");
		lbSDTKH.setForeground(Color.BLACK);
		lbSDTKH.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbSDTKH.setBounds(22, 222, 140, 25);
		pnThanhToan.add(lbSDTKH);

		lbSDTQuan = new JLabel("0989999999");
		lbSDTQuan.setForeground(Color.BLACK);
		lbSDTQuan.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbSDTQuan.setBounds(137, 85, 140, 25);
		pnThanhToan.add(lbSDTQuan);
		df = new DecimalFormat("###,### VNĐ");
		sf = new SimpleDateFormat("dd/MM/yyyy");
		dt = DateTimeFormatter.ofPattern("HH:mm");
		p = new DanhSachPhong();
		dsHD = new DanhSachHoaDon();
		tp = new ThuePhong();
		btnHuy.addActionListener(this);
		btnXacNhan.addActionListener(this);
		upTT();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnHuy) {
			dispose();
		}
		if (o == btnXacNhan)
			if (setTTHD(mahd, tien, giotra, makh, map, "EMPT")) {
				JOptionPane.showMessageDialog(this, "thành công");
				dispose();
				try {
					xuatHoaDonFilePDF();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}

	public void upTT() {
		mahd = hd.getMaHoaDon();
		lblHd.setText(mahd);
		Phong phong = p.getPhongTheoMa(hd.getPhong().getMaPhong());
		map = phong.getMaPhong();
		lblMp.setText(map);
		makh = hd.getMaKhachHang().getMaKhachHang();
		lblNguynVnA.setText(hd.getMaNhanVien().getHoTenNhanVien());
		lbTenKH_1.setText(hd.getMaKhachHang().getHoTenKhachHang());
		lbDiemTL_1.setText(String.valueOf(hd.getMaKhachHang().getDiemTichLuy()));
		lbGioBatDau_1.setText(dt.format(hd.getGioBatDauThue()));
		giotra = hd.getGioTraPhong();
		lbGioKetThuc_1.setText(dt.format(hd.getGioTraPhong()));
		lbTongGT_1.setText(String.valueOf(hd.getNgayLapHoaDon()));
		DanhSachDichVu dsdv = new DanhSachDichVu();
		ArrayList<ChiTietHoaDon> list = tp.getCTHDTheoMa(hd.getMaHoaDon());
		int i = 1;
		float tongtiendv = 0;
		for (ChiTietHoaDon p : list) {
			DichVu dv = dsdv.getDVTheoMa(p.getDichVu().getMaDichVu());
			Object[] obj = new Object[7];
			obj[0] = i++;
			obj[1] = dv.getTenDichVu();
			obj[2] = dv.getloaiDichVu().getTenLoaiDichVu();
			obj[3] = dsdv.getSLTheoMaDV(p.getDichVu().getMaDichVu());
			obj[4] = df.format(dsdv.getDGTheoMaDV(p.getDichVu().getMaDichVu()));
			float tong = dsdv.getSLTheoMaDV(p.getDichVu().getMaDichVu())
					* dsdv.getDGTheoMaDV(p.getDichVu().getMaDichVu());
			tongtiendv += tong;
			obj[5] = df.format(tong);
			model.addRow(obj);
		}
		lbTongTienDV_1.setText(df.format(tongtiendv));
		int gioVao = hd.getGioBatDauThue().getHour();
		int phutVao = hd.getGioBatDauThue().getMinute();
		int gioRa = hd.getGioTraPhong().getHour();
		int phutRa = hd.getGioTraPhong().getMinute();
		int tongThoiGian = (gioRa * 60 + phutRa) - (gioVao * 60 + phutVao);
		int gio = tongThoiGian / 60;
		int phutConLai = tongThoiGian % 60;
		lbThoiGianThue.setText(String.valueOf(gio) + ":" + String.valueOf(phutConLai));
		float tienphong = phong.getGiaPhong() / 60 * tongThoiGian;
		lblTonggio.setText(df.format(tienphong));
		float giamgia = 0;
		if (hd.getMaKhachHang().getDiemTichLuy() >= 10) {
			giamgia = 10 / 100;
			lblGiamGia.setText("10%");
		} else
			lblGiamGia.setText("0%");
		float vat = 10 / 100;
		float phuthu = 0;
		lblPhuThu.setText("0 VND");
		lblVAT.setText("10%");
		float tonghd = tongtiendv - phuthu + tienphong;
		lblTongtien.setText(df.format(tonghd));
		float tienvat = tonghd * vat;
		float tiengiam = tonghd * giamgia;
		tien = tonghd + tienvat - tiengiam;
		lblTongthanhtoan.setText(df.format(tien));
	}

	public boolean setTTHD(String mahd, float tien, LocalTime giotra, String makh, String map, String tinhtrang) {
		if (!tp.tinhTien(mahd, tien, giotra) && !tp.congDTL(makh) && !tp.setTTPhongTheoMa(map, tinhtrang))
			return true;
		return false;
	}

	public void xuatHoaDonFilePDF() throws Exception {
		int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất hóa đơn này ?", "Thông báo",
				JOptionPane.YES_NO_OPTION);
		if (chon == JOptionPane.YES_OPTION) {
//		 Thiết lập chọn đường dẫn
			String path = "src\\printInvoice\\";
			// Set size file pdf muốn xuất
			Document doc = new Document(PageSize.ARCH_B);
			try {
				PdfWriter.getInstance(doc, new FileOutputStream(path + hd.getMaHoaDon() + ".pdf"));
				doc.open();
				// Chuyển đổi từ Jpanel sang Image
				BufferedImage image = chuyenDoiHinhTuJpanel(pnThanhToan);
				com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(image, null);
				doc.add(pdfImage);

				JOptionPane.showMessageDialog(null, "Đã xuất hóa đơn PDF thành công");

			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Lỗi !!!");
			}
			doc.close();
			// Mở tệp PDF bằng ứng dụng mặc định của hệ thống
			File pdfFile = new File(path + hd.getMaHoaDon() + ".pdf");
			try {
				Desktop.getDesktop().open(pdfFile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	public BufferedImage chuyenDoiHinhTuJpanel(JPanel panel) {
		int width = pnThanhToan.getWidth();
		int height = 720;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		panel.paint(g);
		g.dispose();
		return image;
	}
}
