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

public class Frm_ThanhToan extends JFrame {
	JPanel pnDSDichVu;
	JLabel lbDSDichVu, lbBGQLDV, lbHoaDon, lbDiaChi, lbSDT, lbSoLuongTon, lbMaPhong;
	JTextField txtSDT;
	Panel pnThanhToan;
	private JTable tableDSDichVu;
	private DefaultTableModel model;
	private JLabel lbTenNV, lblNguynVn, lbSDT_1, lblHd, lblMp, lblNguynVnA, lbHDTT, lbTenKH, lbDiemTL, lbGioBatDau,
			lbGioKetThuc, lbThoiGian, lbTenKH_1, lbDiemTL_1, lbGioBatDau_1, lbGioKetThuc_1, lbThoiGianThue, lbTongTienDV,
			lbPhuThu, lbTongTienGio, lbGiamGia, lbPhiVAT, lbTongHD, lbTongThanhToan, lbTongTienDV_1, lblVn, lblVn_1,
			lbPhiVAT_1, lblVn_2, lblVn_3, lblVn_4, lbCamOn;

	public Panel getFrmThanhToan() {
		return this.pnThanhToan;
	}

	public Frm_ThanhToan() {
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

		lbHoaDon = new JLabel("KARAOKE ETC");
		lbHoaDon.setFont(new Font("Tahoma", Font.BOLD, 50));
		lbHoaDon.setForeground(new Color(255, 255, 255));
		lbHoaDon.setBounds(360, 10, 500, 50);
		pnThanhToan.add(lbHoaDon);

		lbDiaChi = new JLabel("Địa chỉ:");
		lbDiaChi.setForeground(Color.WHITE);
		lbDiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDiaChi.setBounds(60, 65, 200, 25);
		pnThanhToan.add(lbDiaChi);

		lbSDT = new JLabel("SĐT:");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(60, 85, 100, 25);
		pnThanhToan.add(lbSDT);

		lbSoLuongTon = new JLabel("Mã hóa đơn: ");
		lbSoLuongTon.setForeground(Color.ORANGE);
		lbSoLuongTon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSoLuongTon.setBounds(60, 105, 200, 25);
		pnThanhToan.add(lbSoLuongTon);

		lbMaPhong = new JLabel("Mã phòng:");
		lbMaPhong.setForeground(Color.ORANGE);
		lbMaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbMaPhong.setBounds(60, 125, 200, 25);
		pnThanhToan.add(lbMaPhong);

		FixButton btnXacNhan = new FixButton("Làm mới");
		btnXacNhan.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_xacnhan.png")));
		btnXacNhan.setText("Xác nhận");
		btnXacNhan.setBounds(820, 735, 140, 40);
		pnThanhToan.add(btnXacNhan);
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 15));

		FixButton btnHuy = new FixButton("Hủy đặt phòng");
		btnHuy.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_huydv.png")));
		btnHuy.setText("Hủy");
		btnHuy.setBounds(650, 735, 140, 40);
		pnThanhToan.add(btnHuy);
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(60, 301, 870, 234);
		pnThanhToan.add(pnDSDichVu);
		pnDSDichVu.setLayout(null);

		lbDSDichVu = new JLabel("Danh sách dịch vụ");
		lbDSDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSDichVu.setBounds(10, 0, 150, 25);
		pnDSDichVu.add(lbDSDichVu);

		tableDSDichVu = new JTable(
				new DefaultTableModel(
						new Object[][] { { "MMH001", "Bia Tiger", "Thức uống", "100", "30.000 VNĐ", "300.000 VNĐ" },
								{ "MMH002", "Khô bò miếng", "Đồ ăn", "50",
										"100.000 VNĐ", "5.000.000 VNĐ" },
								{ null, null, null, null, null }, { null, null, null, null, null },
								{ null, null, null, null, null }, { null, null, null, null, null }, },
						new String[] { "STT", "Tên dịch vụ", "Loại dịch vụ",
								"Số lượng", "Đơn giá", "Tổng tiền" }));
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
		lbTenNV.setBounds(60, 145, 200, 25);
		pnThanhToan.add(lbTenNV);

		lblNguynVn = new JLabel("12 Nguyễn Văn Bảo, P4, Quận Gò Vấp, TP.HCM ");
		lblNguynVn.setForeground(Color.WHITE);
		lblNguynVn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNguynVn.setBounds(267, 60, 500, 35);
		pnThanhToan.add(lblNguynVn);

		lbSDT_1 = new JLabel("03996581777");
		lbSDT_1.setForeground(Color.WHITE);
		lbSDT_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT_1.setBounds(268, 85, 150, 25);
		pnThanhToan.add(lbSDT_1);

		lblHd = new JLabel("HD001");
		lblHd.setForeground(Color.ORANGE);
		lblHd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHd.setBounds(267, 105, 200, 25);
		pnThanhToan.add(lblHd);

		lblMp = new JLabel("MP001");
		lblMp.setForeground(Color.ORANGE);
		lblMp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMp.setBounds(267, 125, 200, 25);
		pnThanhToan.add(lblMp);

		lblNguynVnA = new JLabel("Nguyễn Văn A");
		lblNguynVnA.setForeground(Color.WHITE);
		lblNguynVnA.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNguynVnA.setBounds(267, 145, 200, 25);
		pnThanhToan.add(lblNguynVnA);

		lbHDTT = new JLabel("HÓA ĐƠN TÍNH TIỀN");
		lbHDTT.setForeground(Color.WHITE);
		lbHDTT.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbHDTT.setBounds(372, 160, 300, 30);
		pnThanhToan.add(lbHDTT);

		lbTenKH = new JLabel("Tên khách hàng: ");
		lbTenKH.setForeground(Color.ORANGE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(60, 185, 200, 25);
		pnThanhToan.add(lbTenKH);

		lbDiemTL = new JLabel("Điểm tích lũy: ");
		lbDiemTL.setForeground(Color.WHITE);
		lbDiemTL.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDiemTL.setBounds(60, 205, 200, 25);
		pnThanhToan.add(lbDiemTL);
		lbGioBatDau = new JLabel("Giờ bắt đầu: ");
		lbGioBatDau.setForeground(Color.WHITE);
		lbGioBatDau.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioBatDau.setBounds(60, 225, 200, 25);
		pnThanhToan.add(lbGioBatDau);

		lbGioKetThuc = new JLabel("Giờ kết thúc: ");
		lbGioKetThuc.setForeground(Color.WHITE);
		lbGioKetThuc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioKetThuc.setBounds(60, 245, 200, 25);
		pnThanhToan.add(lbGioKetThuc);

		lbThoiGian = new JLabel("Thời gian: ");
		lbThoiGian.setBackground(Color.WHITE);
		lbThoiGian.setForeground(Color.ORANGE);
		lbThoiGian.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbThoiGian.setBounds(60, 265, 200, 25);
		pnThanhToan.add(lbThoiGian);

		lbTenKH_1 = new JLabel("Trần Quốc Huy");
		lbTenKH_1.setForeground(Color.ORANGE);
		lbTenKH_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH_1.setBounds(790, 185, 200, 25);
		pnThanhToan.add(lbTenKH_1);

		lbDiemTL_1 = new JLabel("10");
		lbDiemTL_1.setForeground(Color.WHITE);
		lbDiemTL_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDiemTL_1.setBounds(790, 205, 200, 25);
		pnThanhToan.add(lbDiemTL_1);

		lbGioBatDau_1 = new JLabel("16:00 11/08/2023");
		lbGioBatDau_1.setForeground(Color.WHITE);
		lbGioBatDau_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioBatDau_1.setBounds(789, 225, 200, 25);
		pnThanhToan.add(lbGioBatDau_1);

		lbGioKetThuc_1 = new JLabel("22:00 11/08/2023");
		lbGioKetThuc_1.setForeground(Color.WHITE);
		lbGioKetThuc_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioKetThuc_1.setBounds(789, 245, 200, 25);
		pnThanhToan.add(lbGioKetThuc_1);

		lbThoiGianThue = new JLabel("8 giờ 00 phút");
		lbThoiGianThue.setBackground(Color.WHITE);
		lbThoiGianThue.setForeground(Color.ORANGE);
		lbThoiGianThue.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbThoiGianThue.setBounds(790, 265, 200, 25);
		pnThanhToan.add(lbThoiGianThue);

		lbTongTienDV = new JLabel("Tổng tiền dịch vụ: ");
		lbTongTienDV.setForeground(Color.WHITE);
		lbTongTienDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongTienDV.setBounds(60, 544, 200, 25);
		pnThanhToan.add(lbTongTienDV);

		lbPhuThu = new JLabel("Phụ thu: ");
		lbPhuThu.setForeground(Color.WHITE);
		lbPhuThu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbPhuThu.setBounds(60, 564, 200, 25);
		pnThanhToan.add(lbPhuThu);

		lbTongTienGio = new JLabel("Tổng tiền giờ: ");
		lbTongTienGio.setForeground(Color.WHITE);
		lbTongTienGio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongTienGio.setBounds(60, 584, 200, 25);
		pnThanhToan.add(lbTongTienGio);

		lbGiamGia = new JLabel("Giảm giá: ");
		lbGiamGia.setForeground(Color.WHITE);
		lbGiamGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGiamGia.setBounds(60, 604, 200, 25);
		pnThanhToan.add(lbGiamGia);

		lbPhiVAT = new JLabel("Phí VAT: ");
		lbPhiVAT.setForeground(Color.WHITE);
		lbPhiVAT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbPhiVAT.setBounds(60, 644, 200, 25);
		pnThanhToan.add(lbPhiVAT);

		lbTongHD = new JLabel("Tổng hóa đơn: ");
		lbTongHD.setForeground(Color.WHITE);
		lbTongHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongHD.setBounds(60, 624, 200, 25);
		pnThanhToan.add(lbTongHD);

		lbTongThanhToan = new JLabel("Tổng thanh toán: ");
		lbTongThanhToan.setBackground(Color.WHITE);
		lbTongThanhToan.setForeground(Color.ORANGE);
		lbTongThanhToan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongThanhToan.setBounds(60, 664, 200, 25);
		pnThanhToan.add(lbTongThanhToan);

		lbTongTienDV_1 = new JLabel("5.030.000 VNĐ");
		lbTongTienDV_1.setForeground(Color.WHITE);
		lbTongTienDV_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongTienDV_1.setBounds(810, 544, 200, 25);
		pnThanhToan.add(lbTongTienDV_1);

		lblVn = new JLabel("50.000 VNĐ");
		lblVn.setForeground(Color.WHITE);
		lblVn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVn.setBounds(810, 564, 200, 25);
		pnThanhToan.add(lblVn);

		lblVn_1 = new JLabel("1.350.000 VNĐ");
		lblVn_1.setForeground(Color.WHITE);
		lblVn_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVn_1.setBounds(810, 584, 200, 25);
		pnThanhToan.add(lblVn_1);

		lbPhiVAT_1 = new JLabel("6.430.000 VNĐ");
		lbPhiVAT_1.setForeground(Color.WHITE);
		lbPhiVAT_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbPhiVAT_1.setBounds(810, 624, 200, 25);
		pnThanhToan.add(lbPhiVAT_1);

		lblVn_2 = new JLabel("13.500 VNĐ");
		lblVn_2.setForeground(Color.WHITE);
		lblVn_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVn_2.setBounds(810, 604, 200, 25);
		pnThanhToan.add(lblVn_2);

		lblVn_3 = new JLabel("64.300 VNĐ");
		lblVn_3.setForeground(Color.WHITE);
		lblVn_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVn_3.setBounds(810, 644, 200, 25);
		pnThanhToan.add(lblVn_3);

		lblVn_4 = new JLabel("6.494.300 VNĐ");
		lblVn_4.setForeground(Color.ORANGE);
		lblVn_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVn_4.setBounds(810, 664, 200, 25);
		pnThanhToan.add(lblVn_4);

		lbCamOn = new JLabel("XIN CẢM ƠN QUÝ KHÁCH VÀ HẸN GẶP LẠI ");
		lbCamOn.setForeground(Color.WHITE);
		lbCamOn.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbCamOn.setBounds(330, 701, 400, 25);
		pnThanhToan.add(lbCamOn);

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_trong.png")));
		lbBGQLDV.setBounds(10, 10, 1000, 820);
		pnThanhToan.add(lbBGQLDV);

	}

	public static void main(String[] args) {
		new Frm_ThanhToan().setVisible(true);

	}

}
