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
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.DoubleToLongFunction;

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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.SpinnerUI;

//import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DanhSachChiTietHoaDon;
import dao.DanhSachDichVu;
import dao.DanhSachHoaDon;
import dao.DanhSachPhong;
import dao.Dao_PhatSinhMa;
import dao.ThuePhong;
import entitys.ChiTietHoaDon;
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.LoaiDichVu;
import entitys.LoaiPhong;
import entitys.Phong;
import entitys.PhuThu;
import entitys.TinhTrangPhong;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import org.apache.poi.hssf.record.cf.BorderFormatting;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

public class Frm_ThemDichVu extends JFrame implements ActionListener, MouseListener {

	JPanel pnDSDichVu, pnDV;
	JLabel lbDSDichVu, lbBGQLDV, lbDV, lbLoaiDichVu, lbTenDV, lbSoLuong, lbGiamSL, lbTangSL, lbldongia;
	JComboBox comboTDV, comboLDV;
	JTextField txtSoLuongTon;
	Panel pnTDV;
	FixButton btnHuyDV, btnXacNhan, btnLamMoi, btnThemDV;
	FixButton2 btnQuayLai;
	private JTable tableDSDichVu;
	private DefaultTableModel model;
	private DateTimeFormatter dt;
	private JLabel lbTongTien;
	private JLabel lbPhong, lbShowMaPhong;
	private JLabel lbTenKH, lbShowTenKhh;
	private JLabel lbGioVao, lbShowGioVao;
	DanhSachDichVu dsDV;
	DanhSachHoaDon dsHD;
	DanhSachChiTietHoaDon dsCTHD;
	HoaDonPhong hd;
	ChiTietHoaDon ct;
	DanhSachPhong p;
	ThuePhong tp;
	PhuThu pt;
	int STT = 0;
	private DecimalFormat df;

	public Panel getFrmThemDichVu() {
		return this.pnTDV;
	}

	public Frm_ThemDichVu(HoaDonPhong hd) {
		setTitle("THÊM DỊCH VỤ");
		setSize(1400, 670);
		setResizable(true);
		setLocationRelativeTo(null);
		this.hd = hd;
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnTDV = new Panel();
		pnTDV.setBounds(0, 0, 1400, 670);
		getContentPane().add(pnTDV);
		pnTDV.setLayout(null);

		pnDV = new JPanel();
		pnDV.setBackground(new java.awt.Color(255, 255, 255, 80));
		pnDV.setBounds(20, 90, 481, 424);
		pnTDV.add(pnDV);
		pnDV.setLayout(null);

		lbDV = new JLabel("Dịch vụ");
		lbDV.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbDV.setForeground(new Color(255, 255, 255));
		lbDV.setBounds(421, 60, 250, 20);
		pnTDV.add(lbDV);

		lbLoaiDichVu = new JLabel("Loại dịch vụ:");
		lbLoaiDichVu.setForeground(Color.WHITE);
		lbLoaiDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiDichVu.setBounds(14, 45, 200, 25);
		pnDV.add(lbLoaiDichVu);

		lbTenDV = new JLabel("Tên dịch vụ:");
		lbTenDV.setForeground(Color.WHITE);
		lbTenDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenDV.setBounds(14, 85, 200, 25);
		pnDV.add(lbTenDV);

		lbSoLuong = new JLabel("Số lượng:");
		lbSoLuong.setForeground(Color.WHITE);
		lbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSoLuong.setBounds(14, 125, 200, 25);
		pnDV.add(lbSoLuong);

		comboLDV = new JComboBox();
		comboLDV.setModel(new DefaultComboBoxModel(new String[] { "Thực phẩm", "Nước uống" }));
		comboLDV.setSelectedIndex(0);
		comboLDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboLDV.setBounds(129, 45, 300, 30);
		pnDV.add(comboLDV);

		comboTDV = new JComboBox();
		comboTDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboTDV.setBounds(129, 85, 300, 30);
		comboTDV.setEditable(true);
		pnDV.add(comboTDV);

		txtSoLuongTon = new JTextField("");
		txtSoLuongTon.setBounds(200, 125, 80, 30);
		txtSoLuongTon.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnDV.add(txtSoLuongTon);

		btnThemDV = new FixButton();
		btnThemDV.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/cart.png")));
		btnThemDV.setText("Thêm dịch vụ");
		btnThemDV.setBounds(129, 206, 300, 35);
		pnDV.add(btnThemDV);
		btnThemDV.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnLamMoi = new FixButton("Hủy đặt phòng");
		btnLamMoi.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_lammoi.png")));
		btnLamMoi.setText("Làm mới");
		btnLamMoi.setBounds(129, 262, 300, 35);
		pnDV.add(btnLamMoi);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));

		lbGiamSL = new JLabel("");
		lbGiamSL.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_giam.png")));
		lbGiamSL.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGiamSL.setBounds(158, 125, 30, 27);
//		Border bottomBorder1 = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
//		lbGiamSL.setBorder(bottomBorder1);
		pnDV.add(lbGiamSL);

		lbTangSL = new JLabel("");
		lbTangSL.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_them.png")));
		lbTangSL.setForeground(Color.WHITE);
		lbTangSL.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTangSL.setBounds(286, 125, 30, 27);
//		lbTangSL.setBorder(bottomBorder1);
		pnDV.add(lbTangSL);

		lbTongTien = new JLabel("Đơn giá:");
		lbTongTien.setBackground(Color.WHITE);
		lbTongTien.setForeground(Color.WHITE);
		lbTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongTien.setBounds(14, 165, 111, 30);
		pnDV.add(lbTongTien);

		lbldongia = new JLabel("");
		lbldongia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbldongia.setBounds(145, 165, 151, 30);
		pnDV.add(lbldongia);

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(562, 95, 780, 350);
		pnTDV.add(pnDSDichVu);
		pnDSDichVu.setLayout(null);

		btnQuayLai = new FixButton2("Quay lại");
		btnQuayLai.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_quaylai.png")));
		btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnQuayLai.setBounds(20, 20, 140, 40);
		pnTDV.add(btnQuayLai);

		lbDSDichVu = new JLabel("Danh sách dịch vụ");
		lbDSDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSDichVu.setBounds(10, 0, 150, 25);
		pnDSDichVu.add(lbDSDichVu);

		String col[] = { "STT","Mã dịch vụ", "Loại dịch vụ", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền" };
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
		scrollPane.setBounds(0, 25, 780, 320);
		scrollPane.getHorizontalScrollBar();
		pnDSDichVu.add(scrollPane);

		scrollPane.setViewportView(tableDSDichVu);

		btnHuyDV = new FixButton("Hủy đặt phòng");
		btnHuyDV.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_huydv.png")));
		btnHuyDV.setText("Hủy dịch vụ");
		btnHuyDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuyDV.setBounds(700, 470, 200, 40);
		pnTDV.add(btnHuyDV);

		btnXacNhan = new FixButton();
		btnXacNhan.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_xacnhan.png")));
		btnXacNhan.setText("Xác nhận");
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXacNhan.setBounds(990, 469, 200, 40);
		pnTDV.add(btnXacNhan);

		JPanel pnTTPhong = new JPanel();
		pnTTPhong.setBackground(new java.awt.Color(255, 255, 255, 80));
		pnTTPhong.setBounds(562, 0, 780, 75);
		pnTTPhong.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin ph\u00F2ng", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));

		pnTDV.add(pnTTPhong);
		pnTTPhong.setLayout(null);

		lbPhong = new JLabel("Mã Phòng:");
		lbPhong.setForeground(Color.WHITE);
		lbPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbPhong.setBounds(10, 15, 100, 20);
		pnTTPhong.add(lbPhong);

		lbShowMaPhong = new JLabel("Mã Phòng: ");
		lbShowMaPhong.setForeground(Color.WHITE);
		lbShowMaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbShowMaPhong.setBounds(90, 15, 100, 20);
		pnTTPhong.add(lbShowMaPhong);

		lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(10, 52, 150, 20);
		pnTTPhong.add(lbTenKH);

		lbShowTenKhh = new JLabel("Tên khách hàng:");
		lbShowTenKhh.setForeground(Color.WHITE);
		lbShowTenKhh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbShowTenKhh.setBounds(140, 52, 150, 20);
		pnTTPhong.add(lbShowTenKhh);

		lbGioVao = new JLabel("Giờ vào:");
		lbGioVao.setForeground(Color.WHITE);
		lbGioVao.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioVao.setBounds(570, 52, 150, 20);
		pnTTPhong.add(lbGioVao);

		lbShowGioVao = new JLabel("Giờ vào:");
		lbShowGioVao.setForeground(Color.WHITE);
		lbShowGioVao.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbShowGioVao.setBounds(650, 52, 150, 20);
		pnTTPhong.add(lbShowGioVao);

		lbTangSL.addMouseListener(this);
		lbGiamSL.addMouseListener(this);

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDV.setBounds(0, 0, 1400, 670);
		pnTDV.add(lbBGQLDV);

		btnQuayLai.addActionListener(this);
		btnThemDV.addActionListener(this);
		btnHuyDV.addActionListener(this);
		btnLamMoi.addActionListener(this);
		comboLDV.addActionListener(this);
		btnXacNhan.addActionListener(this);
		tableDSDichVu.addMouseListener(this);

		ConnectDB.getInstance().connect();
		dsDV = new DanhSachDichVu();
		dt = DateTimeFormatter.ofPattern("HH:mm");
		df = new DecimalFormat("###,### VNĐ");
		dsCTHD = new DanhSachChiTietHoaDon();
		tp = new ThuePhong();
		phanLoaiCombobox();
		upLBL();
		upTableDV();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o == lbTangSL) {
			if (txtSoLuongTon.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Chưa nhập số lượng dịch vụ");
			} else {
				ktTangSL();
			}
		} else if (o == lbGiamSL) {
			if (txtSoLuongTon.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Chưa nhập số lượng dịch vụ");
			} else {
				ktGiamSL();
			}
		} else {
			setTextTB();
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
		Border bottomBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
		// Đặt đường gạch chân cho JLabel
		lbTangSL.setBorder(bottomBorder);
		lbGiamSL.setBorder(bottomBorder);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK);
		// Đặt đường gạch chân cho JLabel
		lbTangSL.setBorder(bottomBorder);
		lbGiamSL.setBorder(bottomBorder);

	}

	@Override

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnThemDV) {
			if (txtSoLuongTon.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Chưa nhập số lượng dịch vụ");
			} else {
				addTable();
			}

		}
		if (o == btnHuyDV) {
			huy();
		}
		if (o == btnLamMoi) {
			xoaTrang();
		}
		if (o == btnQuayLai) {
			dispose();
		}
		if (o == btnXacNhan) {
			xacNhan();

			
		}
		if (o == comboLDV) {
			phanLoaiCombobox();
		}
	}

	public void phanLoaiCombobox() {
		String loai = String.valueOf(comboLDV.getSelectedItem());
		ArrayList<DichVu> list = null;
		comboTDV.removeAllItems();
		if (loai.equals("Thực phẩm")) {
			comboTDV.removeAllItems();
			list = dsDV.getDSDichVuTheoLoai("FOOD");
		} else {
			comboTDV.removeAllItems();
			list = dsDV.getDSDichVuTheoLoai("WATER");
		}
		for (DichVu s : list) {
			comboTDV.addItem(s.getTenDichVu());
		}
	}
	
	

	public void ktTangSL() {
		int tang = Integer.valueOf(txtSoLuongTon.getText());
		int value = tang + 1;
		txtSoLuongTon.setText(String.valueOf(value));
	}

	public void ktGiamSL() {
		int giam = Integer.parseInt(txtSoLuongTon.getText());
		int value = Math.max(0, giam - 1);
		txtSoLuongTon.setText(String.valueOf(value));
	}

//	
	public void upLBL() {
		lbShowMaPhong.setText(hd.getPhong().getMaPhong());
		lbShowTenKhh.setText(hd.getMaKhachHang().getHoTenKhachHang());
		lbShowGioVao.setText(dt.format(hd.getGioBatDauThue()));
	}
	
	public void addTable() {
		int row = tableDSDichVu.getSelectedRow();

		String loai = (String) comboLDV.getSelectedItem();
		String tendv = (String) comboTDV.getSelectedItem();
		int sl = Integer.parseInt(txtSoLuongTon.getText());
		double donGia = 0;
		String maDV="";
		ArrayList<DichVu> list = dsDV.getDSDichVu();
		for (DichVu dv : list) {
			if (dv.getTenDichVu().equalsIgnoreCase(tendv)) {
				donGia = dv.getDonGia();
				maDV = dv.getMaDichVu();
				break;
			}
		}
		Object[] obj = new Object[7];
		obj[1] = maDV;
		obj[2] = loai;
		obj[3] = tendv;
		obj[4] = sl;
		obj[5] = donGia;
		obj[6] = sl * donGia;
//		for (int i = 0; i <tableDSDichVu.getRowCount(); i++) {
//			STT = (int) tableDSDichVu.getValueAt(row, 0);
//			STT++;
//		}
		STT =tableDSDichVu.getRowCount();
		if (!timRow()) {
			STT+=1;
			obj[0] = STT;
			model.addRow(obj);
			xoaTrang();
		} else {
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Dịch vụ đã có trong phòng vui lòng chọn dịch vụ để thêm");
			} else {
				obj[4] = Integer.parseInt(txtSoLuongTon.getText());
				obj[5] =df.format(donGia);
				obj[6] = df.format(Integer.parseInt(txtSoLuongTon.getText()) * donGia);
				tableDSDichVu.setValueAt(obj[4], row, 4);
				tableDSDichVu.setValueAt(obj[6], row, 6);
				xoaTrang();
			}
		}

	}
	
	public void upTableDV() {
		int i = 1;
		Object[] obj = new Object[7];
		DanhSachDichVu dsdv = new DanhSachDichVu();
			ArrayList<ChiTietHoaDon> listt = tp.getCTHDTheoMa(hd.getMaHoaDon());
			float tongtiendv = 0;
			for (ChiTietHoaDon p : listt) {
				DichVu dv = dsdv.getDVTheoMa(p.getDichVu().getMaDichVu());
				obj[0] = i++;
				obj[1] = p.getDichVu().getMaDichVu();
				obj[2] = p.getDichVu().getloaiDichVu().getTenLoaiDichVu();
				obj[3] = p.getDichVu().getTenDichVu();
				obj[4] = dsdv.getSLTheoMaDV(p.getDichVu().getMaDichVu());
				obj[5] = df.format(dsdv.getDGTheoMaDV(p.getDichVu().getMaDichVu()));
				float tong = dsdv.getSLTheoMaDV(p.getDichVu().getMaDichVu())* dsdv.getDGTheoMaDV(p.getDichVu().getMaDichVu());
				tongtiendv += tong;
				obj[6] = df.format(tong);
				model.addRow(obj);
			}
		
	}

	public void xoaTrang() {
		txtSoLuongTon.setText("");
		tableDSDichVu.clearSelection();
	}

	public void setTextTB() {
		int row = tableDSDichVu.getSelectedRow();
		comboLDV.setSelectedItem(tableDSDichVu.getValueAt(row, 2).toString());
		comboTDV.setSelectedItem(tableDSDichVu.getValueAt(row, 3).toString());
		txtSoLuongTon.setText(tableDSDichVu.getValueAt(row, 4).toString());
	}

	public void huy() {
		int row = tableDSDichVu.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Chọn dịch vụ cần huỷ");
		} else {
			for (int i = 0; i < tableDSDichVu.getRowCount(); i++) {
				String tenDV = (String) tableDSDichVu.getValueAt(i, 2);
				int soLuongtb = (int) tableDSDichVu.getValueAt(i, 3);
				
				int sl=0;
				System.out.println(soLuongtb);
				ArrayList<DichVu> list = dsDV.getDSDichVu();
				for (DichVu dv : list) {
					if(!dsDV.suaSLTDichVu(dv))
					if (dv.getTenDichVu().equalsIgnoreCase(tenDV)) {
						sl = dv.getSoLuongTon()+soLuongtb;	
					}
				}
				System.out.println(sl);
				DichVu dv1 = new DichVu(tenDV, sl);
				dsDV.suaSLTDichVu(dv1);
			}
			model.removeRow(row);
		}
	}

	public boolean timRow() {

		for (int i = 0; i < tableDSDichVu.getRowCount(); i++) {
			if (tableDSDichVu.getValueAt(i, 2).toString().equalsIgnoreCase(comboLDV.getSelectedItem().toString())
					&& tableDSDichVu.getValueAt(i, 3).toString()
							.equalsIgnoreCase(comboTDV.getSelectedItem().toString()))
				return true;
		}
		return false;
	}
	
	public void xacNhan() {
		int row = tableDSDichVu.getSelectedRow();
		
		for (int i = 0; i < tableDSDichVu.getRowCount(); i++) {
			String tenDV = (String) tableDSDichVu.getValueAt(i, 3);
			int soLuongtb = (int) tableDSDichVu.getValueAt(i, 4);
			
			int sl=0;
			System.out.println(soLuongtb);
			ArrayList<DichVu> list = dsDV.getDSDichVu();
			for (DichVu dv : list) {
				if(!dsDV.suaSLTDichVu(dv))
				if (dv.getTenDichVu().equalsIgnoreCase(tenDV)) {
					sl = dv.getSoLuongTon()-soLuongtb;	
				}
			}
			DichVu dv1 = new DichVu(tenDV, sl);
			dsDV.suaSLTDichVu(dv1);
			themDV();
		}
		JOptionPane.showMessageDialog(this, "Thêm dịch vụ Thành Công");		
	}
	public void themDV() {
		String maHD = null;
		String maDV =null;
		for (int i = 0; i < tableDSDichVu.getRowCount(); i++) {
		maHD = hd.getMaHoaDon();
		maDV = (String) tableDSDichVu.getValueAt(i, 1);
		}
		int tongsL = tableDSDichVu.getRowCount();
		pt = new PhuThu();
		DichVu dv = new DichVu(maDV);
//		System.out.println(maHD);
//		System.out.println(tongsL);	
//		System.out.println(maDV);
		ChiTietHoaDon cthd = new ChiTietHoaDon(hd, dv, tongsL);
		dsCTHD.themDVCTHD(cthd);
	}
}
