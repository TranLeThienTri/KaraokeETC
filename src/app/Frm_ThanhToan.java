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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

import com.mindfusion.common.Duration;
import com.toedter.calendar.JDateChooser;

import dao.DanhSachDichVu;
import dao.DanhSachHoaDon;
import dao.DanhSachPhong;
import dao.DanhSachPhuThu;
import dao.DanhSachThuePhong;
import entitys.ChiTietHoaDon;
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.Phong;
import entitys.PhuThu;

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
	Panel pnThanhToan;
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
	DanhSachPhuThu dsPT;
	ChiTietHoaDon ct;
	DanhSachPhong p;
	DanhSachThuePhong tp;
	String mahd, makh, map, tinhtrang;
	Float tien;
	LocalTime giotra;

	public Panel getFrmThanhToan() {
		return this.pnThanhToan;
	}

	public Frm_ThanhToan(HoaDonPhong hd) {
		setTitle("THANH TOÁN HÓA ĐƠN");
		setSize(1000, 820);
		setResizable(true);
		setLocationRelativeTo(null);
		this.hd = hd;
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnThanhToan = new Panel();
		pnThanhToan.setBounds(0, 0, 1000, 820);
		getContentPane().add(pnThanhToan);
		pnThanhToan.setLayout(null);

		lbHoaDon = new JLabel("KARAOKE ETC");
		lbHoaDon.setFont(new Font("Tahoma", Font.BOLD, 50));
		lbHoaDon.setForeground(new Color(255, 255, 255));
		lbHoaDon.setBounds(321, 10, 357, 50);
		pnThanhToan.add(lbHoaDon);

		lbDiaChi = new JLabel("Địa chỉ:");
		lbDiaChi.setForeground(Color.WHITE);
		lbDiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDiaChi.setBounds(60, 65, 73, 25);
		pnThanhToan.add(lbDiaChi);

		lbSDT = new JLabel("SĐT:");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(60, 85, 58, 25);
		pnThanhToan.add(lbSDT);

		lbSoLuongTon = new JLabel("Mã hóa đơn: ");
		lbSoLuongTon.setForeground(new Color(255, 255, 255));
		lbSoLuongTon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSoLuongTon.setBounds(60, 105, 105, 25);
		pnThanhToan.add(lbSoLuongTon);

		lbMaPhong = new JLabel("Mã phòng:");
		lbMaPhong.setForeground(new Color(255, 255, 255));
		lbMaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbMaPhong.setBounds(60, 125, 98, 25);
		pnThanhToan.add(lbMaPhong);

		btnXacNhan = new FixButton("Làm mới");
		btnXacNhan.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_xacnhan.png")));
		btnXacNhan.setText("Xác nhận");
		btnXacNhan.setBounds(820, 735, 140, 40);
		pnThanhToan.add(btnXacNhan);
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnHuy = new FixButton("Hủy đặt phòng");
		btnHuy.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_huydv.png")));
		btnHuy.setText("Hủy");
		btnHuy.setBounds(650, 735, 140, 40);
		pnThanhToan.add(btnHuy);
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(60, 326, 870, 209);
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
		scrollPane.setBounds(0, 25, 870, 207);
		scrollPane.getHorizontalScrollBar();
		pnDSDichVu.add(scrollPane);

		scrollPane.setViewportView(tableDSDichVu);

		lbTenNV = new JLabel("Nhân viên: ");
		lbTenNV.setForeground(Color.WHITE);
		lbTenNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenNV.setBounds(60, 145, 87, 25);
		pnThanhToan.add(lbTenNV);

		lblNguynVn = new JLabel("12 Nguyễn Văn Bảo, Phường 4, Quận Gò Vấp, TP.HCM");
		lblNguynVn.setForeground(Color.WHITE);
		lblNguynVn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNguynVn.setBounds(175, 65, 596, 25);
		pnThanhToan.add(lblNguynVn);

		lbSDT_1 = new JLabel("0382173105");
		lbSDT_1.setForeground(Color.WHITE);
		lbSDT_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT_1.setBounds(175, 85, 243, 25);
		pnThanhToan.add(lbSDT_1);

		lblHd = new JLabel("");
		lblHd.setForeground(Color.ORANGE);
		lblHd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHd.setBounds(175, 105, 292, 25);
		pnThanhToan.add(lblHd);

		lblMp = new JLabel("");
		lblMp.setForeground(Color.ORANGE);
		lblMp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMp.setBounds(175, 125, 292, 25);
		pnThanhToan.add(lblMp);

		lblNguynVnA = new JLabel("");
		lblNguynVnA.setForeground(Color.WHITE);
		lblNguynVnA.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNguynVnA.setBounds(175, 145, 292, 25);
		pnThanhToan.add(lblNguynVnA);

		lbHDTT = new JLabel("HÓA ĐƠN TÍNH TIỀN");
		lbHDTT.setForeground(Color.WHITE);
		lbHDTT.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbHDTT.setBounds(369, 170, 263, 30);
		pnThanhToan.add(lbHDTT);

		lbTenKH = new JLabel("Tên khách hàng: ");
		lbTenKH.setForeground(new Color(255, 255, 255));
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(60, 205, 140, 25);
		pnThanhToan.add(lbTenKH);

		lbDiemTL = new JLabel("Điểm tích lũy: ");
		lbDiemTL.setForeground(Color.WHITE);
		lbDiemTL.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDiemTL.setBounds(60, 225, 122, 25);
		pnThanhToan.add(lbDiemTL);
		lbGioBatDau = new JLabel("Giờ bắt đầu: ");
		lbGioBatDau.setForeground(Color.WHITE);
		lbGioBatDau.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioBatDau.setBounds(60, 245, 105, 25);
		pnThanhToan.add(lbGioBatDau);

		lbGioKetThuc = new JLabel("Giờ kết thúc: ");
		lbGioKetThuc.setForeground(Color.WHITE);
		lbGioKetThuc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioKetThuc.setBounds(60, 265, 105, 25);
		pnThanhToan.add(lbGioKetThuc);

		lbThoiGian = new JLabel("Tổng thời gian:");
		lbThoiGian.setBackground(Color.WHITE);
		lbThoiGian.setForeground(new Color(255, 255, 255));
		lbThoiGian.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbThoiGian.setBounds(60, 285, 200, 25);
		pnThanhToan.add(lbThoiGian);
		lbTongGT = new JLabel("Ngày:");
		lbTongGT.setBackground(Color.WHITE);
		lbTongGT.setForeground(new Color(255, 255, 255));
		lbTongGT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongGT.setBounds(60, 305, 112, 25);
		pnThanhToan.add(lbTongGT);

		lbTenKH_1 = new JLabel("");
		lbTenKH_1.setForeground(Color.ORANGE);
		lbTenKH_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH_1.setBounds(780, 205, 200, 25);
		pnThanhToan.add(lbTenKH_1);

		lbDiemTL_1 = new JLabel("");
		lbDiemTL_1.setForeground(Color.WHITE);
		lbDiemTL_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDiemTL_1.setBounds(780, 225, 200, 25);
		pnThanhToan.add(lbDiemTL_1);

		lbGioBatDau_1 = new JLabel("");
		lbGioBatDau_1.setForeground(Color.WHITE);
		lbGioBatDau_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioBatDau_1.setBounds(780, 245, 200, 25);
		pnThanhToan.add(lbGioBatDau_1);

		lbGioKetThuc_1 = new JLabel("");
		lbGioKetThuc_1.setForeground(Color.WHITE);
		lbGioKetThuc_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioKetThuc_1.setBounds(780, 265, 200, 25);
		pnThanhToan.add(lbGioKetThuc_1);

		lbThoiGianThue = new JLabel("");
		lbThoiGianThue.setBackground(Color.WHITE);
		lbThoiGianThue.setForeground(Color.ORANGE);
		lbThoiGianThue.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbThoiGianThue.setBounds(780, 285, 200, 25);
		pnThanhToan.add(lbThoiGianThue);
		lbTongGT_1 = new JLabel("");
		lbTongGT_1.setBackground(Color.WHITE);
		lbTongGT_1.setForeground(Color.ORANGE);
		lbTongGT_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongGT_1.setBounds(780, 305, 200, 25);
		pnThanhToan.add(lbTongGT_1);
		lbTongTienDV = new JLabel("Tổng tiền dịch vụ: ");
		lbTongTienDV.setForeground(Color.WHITE);
		lbTongTienDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongTienDV.setBounds(60, 545, 200, 25);
		pnThanhToan.add(lbTongTienDV);

		lbPhuThu = new JLabel("Phụ thu(Cuối tuần): ");
		lbPhuThu.setForeground(Color.WHITE);
		lbPhuThu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbPhuThu.setBounds(60, 565, 250, 25);
		pnThanhToan.add(lbPhuThu);

		lbTongTienGio = new JLabel("Tổng tiền giờ: ");
		lbTongTienGio.setForeground(Color.WHITE);
		lbTongTienGio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongTienGio.setBounds(60, 585, 200, 25);
		pnThanhToan.add(lbTongTienGio);

		lbGiamGia = new JLabel("Giảm giá: ");
		lbGiamGia.setForeground(Color.WHITE);
		lbGiamGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGiamGia.setBounds(60, 625, 200, 25);
		pnThanhToan.add(lbGiamGia);

		lbPhiVAT = new JLabel("Phí VAT: ");
		lbPhiVAT.setForeground(Color.WHITE);
		lbPhiVAT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbPhiVAT.setBounds(60, 645, 200, 25);
		pnThanhToan.add(lbPhiVAT);

		lbTongHD = new JLabel("Tổng hóa đơn: ");
		lbTongHD.setForeground(Color.WHITE);
		lbTongHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongHD.setBounds(60, 605, 200, 25);
		pnThanhToan.add(lbTongHD);

		lbTongThanhToan = new JLabel("Tổng thanh toán: ");
		lbTongThanhToan.setBackground(Color.WHITE);
		lbTongThanhToan.setForeground(Color.ORANGE);
		lbTongThanhToan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongThanhToan.setBounds(60, 665, 200, 25);
		pnThanhToan.add(lbTongThanhToan);

		lbTongTienDV_1 = new JLabel("");
		lbTongTienDV_1.setForeground(Color.WHITE);
		lbTongTienDV_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongTienDV_1.setBounds(780, 545, 200, 25);
		pnThanhToan.add(lbTongTienDV_1);

		lblPhuThu = new JLabel("");
		lblPhuThu.setForeground(Color.WHITE);
		lblPhuThu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhuThu.setBounds(780, 565, 200, 25);
		pnThanhToan.add(lblPhuThu);

		lblTonggio = new JLabel("");
		lblTonggio.setForeground(Color.WHITE);
		lblTonggio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTonggio.setBounds(780, 585, 200, 25);
		pnThanhToan.add(lblTonggio);

		lblVAT = new JLabel("");
		lblVAT.setForeground(Color.WHITE);
		lblVAT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVAT.setBounds(780, 645, 200, 25);
		pnThanhToan.add(lblVAT);

		lblGiamGia = new JLabel("");
		lblGiamGia.setForeground(Color.WHITE);
		lblGiamGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiamGia.setBounds(780, 625, 200, 25);
		pnThanhToan.add(lblGiamGia);

		lblTongtien = new JLabel("");
		lblTongtien.setForeground(Color.WHITE);
		lblTongtien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongtien.setBounds(780, 605, 200, 25);
		pnThanhToan.add(lblTongtien);

		lblTongthanhtoan = new JLabel("");
		lblTongthanhtoan.setForeground(Color.ORANGE);
		lblTongthanhtoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongthanhtoan.setBounds(780, 665, 200, 25);
		pnThanhToan.add(lblTongthanhtoan);

		lbCamOn = new JLabel("XIN CẢM ƠN QUÝ KHÁCH VÀ HẸN GẶP LẠI ");
		lbCamOn.setForeground(Color.WHITE);
		lbCamOn.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbCamOn.setBounds(317, 701, 365, 25);
		pnThanhToan.add(lbCamOn);

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_trong.png")));
		lbBGQLDV.setBounds(10, 10, 1000, 820);
		pnThanhToan.add(lbBGQLDV);
		df = new DecimalFormat("###,### VNĐ");
		sf = new SimpleDateFormat("dd/MM/yyyy");
		dt = DateTimeFormatter.ofPattern("HH:mm");
		p = new DanhSachPhong();
		dsHD = new DanhSachHoaDon();
		dsPT = new DanhSachPhuThu();
		tp = new DanhSachThuePhong();
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
				if (tp.getDTLTheoMa(makh) >= 10)
					tp.updateKHVIPTheoMa(makh);
				JOptionPane.showMessageDialog(this, "thành công");
				dispose();
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
			if (p.getDichVu() != null) {
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
			giamgia = (float) 0.1;
			lblGiamGia.setText("10%");
		} else
			lblGiamGia.setText("0%");
		float vat = (float) 0.1;
		String ldpt = dsPT.getPTTheoMaHD(mahd);
		System.out.println(ldpt);
		PhuThu pt = dsPT.getPTTheoMa(ldpt);
		float phuthu = (float) pt.getSoTien();
		System.out.println(phuthu);
		lblPhuThu.setText(df.format(phuthu));
		lblVAT.setText("10%");
		float tonghd = tongtiendv + phuthu + tienphong;
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
}
