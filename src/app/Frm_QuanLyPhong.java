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
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DanhSachKhachHang;
import dao.DanhSachPhong;
import dao.Dao_PhatSinhMa;
import entitys.KhachHang;
import entitys.LoaiKhachHang;
import entitys.LoaiPhong;
import entitys.NhanVien;
import entitys.Phong;
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
import javax.swing.JFormattedTextField;
//
public class Frm_QuanLyPhong extends JFrame implements ActionListener, MouseListener {
	private JPanel pnLoaiPhong, pnDSP, pnTTDDP;
	private JLabel lbLoaiPhongTK, lbDSPhong, lbBGQLDP, lbTTDDP, lbLoaiPhong, lblDinTch, lblMPhng, lblGiPhng, lbSDT,
			lbTenKH, lbTB;
	private JTableHeader tbHeader1;
	private FixButton2 btnTatCa, btnPhongThuong, btnPhongVip;
	Panel pnQLDP;
	private JComboBox comboLoaiPhong, comboTinhTrang, comboSucChua;
	private int ngay, thang, nam;
	private JTable tableDSPhong1;
	private DefaultTableModel model1;
	private JTextField txtGia, txtMaPhong, txtDienTich;
	private FixButton btnSua, btnThem, btnLamMoi;
	private DecimalFormat df;
	private DecimalFormat dfs;
	NhanVien nv;
	DanhSachPhong dsPhong;
	

	public Panel getFrmQuanLyPhong() {
		return this.pnQLDP;
	}

	public Frm_QuanLyPhong(NhanVien nv) {
		this.nv = nv;
		setTitle("QUẢN LÝ PHÒNG");
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

		pnTTDDP = new JPanel();

		pnTTDDP.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		pnTTDDP.setBackground(new java.awt.Color(207, 169, 0));

		pnTTDDP.setBounds(100, 23, 1200, 280);


		pnQLDP.add(pnTTDDP);
		pnTTDDP.setLayout(null);

		lbSDT = new JLabel("Tình trạng phòng :");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(55, 37, 150, 25);
		pnTTDDP.add(lbSDT);

		lbTenKH = new JLabel("Sức chứa:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(55, 93, 140, 25);
		pnTTDDP.add(lbTenKH);

		lbTTDDP = new JLabel("Thông tin phòng");
		lbTTDDP.setBounds(10, 10, 190, 20);
		pnTTDDP.add(lbTTDDP);
		lbTTDDP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTTDDP.setForeground(new Color(255, 255, 255));

		lbLoaiPhong = new JLabel("Loại phòng:");
		lbLoaiPhong.setForeground(Color.WHITE);
		lbLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiPhong.setBounds(55, 154, 126, 25);
		pnTTDDP.add(lbLoaiPhong);

		lblGiPhng = new JLabel("Giá phòng:");
		lblGiPhng.setForeground(Color.WHITE);
		lblGiPhng.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiPhng.setBounds(676, 37, 126, 25);
		pnTTDDP.add(lblGiPhng);

		lblDinTch = new JLabel("Diện tích:");
		lblDinTch.setForeground(Color.WHITE);
		lblDinTch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDinTch.setBounds(676, 93, 126, 25);
		pnTTDDP.add(lblDinTch);

		lblMPhng = new JLabel("Mã phòng:");
		lblMPhng.setForeground(Color.WHITE);
		lblMPhng.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMPhng.setBounds(676, 154, 126, 25);
		pnTTDDP.add(lblMPhng);

		lbTB = new JLabel();
		lbTB.setHorizontalAlignment(SwingConstants.LEFT);

		lbTB.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTB.setBounds(55, 185, 500, 36);
		lbTB.setForeground(Color.RED);
		pnTTDDP.add(lbTB);

		btnThem = new FixButton("Thêm");
		btnThem.setIcon(new ImageIcon(Frm_QuanLyPhong.class.getResource("/imgs/icon_btn_them.png")));

		btnThem.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnThem.setBounds(357, 220, 150, 40);


		btnThem.setBackground(new java.awt.Color(153, 36, 36));
		pnTTDDP.add(btnThem);

		btnSua = new FixButton("Sửa");
		btnSua.setIcon(new ImageIcon(Frm_QuanLyPhong.class.getResource("/imgs/icon_btn_sua.png")));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSua.setBackground(new java.awt.Color(153, 36, 36));

		btnSua.setBounds(557, 220, 150, 40);

		pnTTDDP.add(btnSua);

		btnLamMoi = new FixButton("Làm mới");
		
		btnLamMoi.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_lammoi.png")));

		btnLamMoi.setBounds(757, 220, 150, 40);

		pnTTDDP.add(btnLamMoi);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 17));

		txtGia = new JTextField();
		txtGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtGia.setBounds(795, 37, 323, 30);
		txtGia.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnTTDDP.add(txtGia);

		txtDienTich = new JTextField();
		txtDienTich.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtDienTich.setBounds(795, 93, 323, 30);
		txtDienTich.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnTTDDP.add(txtDienTich);

		txtMaPhong = new JTextField();
		txtMaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtMaPhong.setBounds(795, 151, 323, 28);
		txtMaPhong.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtMaPhong.setEditable(false);
		pnTTDDP.add(txtMaPhong);

		comboLoaiPhong = new JComboBox();
		comboLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboLoaiPhong.setBounds(215, 154, 323, 28);
		pnTTDDP.add(comboLoaiPhong);

		comboTinhTrang = new JComboBox();
		comboTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboTinhTrang.setBounds(215, 33, 323, 28);

		pnTTDDP.add(comboTinhTrang);

		comboSucChua = new JComboBox();
		comboSucChua.setModel(new DefaultComboBoxModel(new String[] { "10", "15", "20" }));
		comboSucChua.setSelectedIndex(0);
		comboSucChua.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboSucChua.setBounds(215, 93, 323, 28);
		comboSucChua.setSelectedIndex(0);
		pnTTDDP.add(comboSucChua);

		pnLoaiPhong = new JPanel();
		pnLoaiPhong.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		pnLoaiPhong.setBackground(Color.ORANGE);

		pnLoaiPhong.setBounds(391, 329, 650, 71);

		pnQLDP.add(pnLoaiPhong);
		pnLoaiPhong.setLayout(null);

		lbLoaiPhongTK = new JLabel("Loại phòng:");
		lbLoaiPhongTK.setForeground(Color.WHITE);
		lbLoaiPhongTK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiPhongTK.setBounds(32, 20, 108, 25);
		pnLoaiPhong.add(lbLoaiPhongTK);
		pnLoaiPhong.setBackground(new java.awt.Color(189, 0, 88));

		pnQLDP.add(pnLoaiPhong);

		btnTatCa = new FixButton2("Tất cả");
		btnTatCa.setBounds(163, 20, 100, 30);
		pnLoaiPhong.add(btnTatCa);

		btnPhongVip = new FixButton2("Phòng VIP");
		btnPhongVip.setBounds(292, 20, 120, 30);
		pnLoaiPhong.add(btnPhongVip);

		btnPhongThuong = new FixButton2("Phòng thường");
		btnPhongThuong.setBounds(447, 20, 150, 30);
		pnLoaiPhong.add(btnPhongThuong);
//talbe
		String col1[] = { "Mã phòng", "Tình trạng ", "Sức chứa", "Loại phòng", "Giá phòng", "Diện tích" };
		model1 = new DefaultTableModel(col1, 0){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Không cho phép chỉnh sửa ô
			}
		};

		tableDSPhong1 = new JTable(model1);

		// Set màu cho cột tiêu đề
		tbHeader1 = tableDSPhong1.getTableHeader();
		tbHeader1.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader1.setPreferredSize(new Dimension(100, 30));
		tbHeader1.setForeground(Color.WHITE);
		tbHeader1.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

		tableDSPhong1.setBackground(Color.white);
		tableDSPhong1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableDSPhong1.setSelectionBackground(new Color(158, 207, 0));
		tableDSPhong1.setRowHeight(30);

		JScrollPane scrollPane1 = new JScrollPane(tableDSPhong1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane1.setBounds(100, 410, 1200, 230);
		pnQLDP.add(scrollPane1);

		// add background ở cuối
		lbBGQLDP = new JLabel();
		lbBGQLDP.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDP.setBounds(0, 0, 1400, 700);
		pnQLDP.add(lbBGQLDP);
		ConnectDB.getInstance().connect();
		// Danh sach Mat Hang
		dsPhong = new DanhSachPhong();
		upTable();
		upCombobox();
		upCombobox2();

		btnTatCa.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnPhongVip.addActionListener(this);
		btnPhongThuong.addActionListener(this);
		tableDSPhong1.addMouseListener(this);
		
		df = new DecimalFormat("###,### VNĐ");
		dfs = new DecimalFormat("##,## M2");
		
		
	}

//	public static void main(String[] args) {
//		new Frm_QuanLyPhong(nv).setVisible(true);
//
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm")) {
				comboTinhTrang.setSelectedIndex(1);
				comboTinhTrang.enable(false);
				comboTinhTrang.setForeground(Color.black);
				btnThem.setText("Xác nhận");
				btnSua.setText("Huỷ");
			} else if (btnThem.getText().equalsIgnoreCase("Xác nhận")) {
				if (themPhong()) {
					btnSua.setText("Sửa");
					btnThem.setText("Thêm");
				}
			} else if (btnThem.getText().equals("Xác nhận ")) {
				if (suaPhong()) {
					btnThem.setText("Thêm");
					btnSua.setText("Sửa");
				}
			}
		} else if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Huỷ")) {
				comboTinhTrang.enable(true);
				comboTinhTrang.setSelectedIndex(0);
				btnThem.setText("Thêm");
				btnSua.setText("Sửa");
			} else if (btnSua.getText().equals("Sửa")) {
				comboTinhTrang.enable(false);
				comboTinhTrang.setSelectedIndex(1);
				btnThem.setText("Xác nhận ");
				btnSua.setText("Huỷ");
			}
		} else if (o.equals(btnLamMoi)) {
			xoaTrang();

		} else if (o.equals(btnPhongVip)) {
			locTheoLoaiPhongVIP();
		} else if (o.equals(btnPhongThuong)) {
			
			locTheoLoaiPhongThuong();
		}
		else if (o.equals(btnTatCa)) {
			clearTable();
			upTable();
		}
	}

	public boolean themPhong() {
		Object[] obj = new Object[6];
		if (ktraDuLieu()) {
			Dao_PhatSinhMa maP = new Dao_PhatSinhMa();
			String ma = maP.getMaPhongCuoi();
			String tenLoaiPhong = String.valueOf(comboLoaiPhong.getSelectedItem());
			int sucChua = Integer.parseInt((String) comboSucChua.getSelectedItem());
			String tenTinhTang = String.valueOf(comboTinhTrang.getSelectedItem());
			float Gia = Float.parseFloat(txtGia.getText());
			float dienTich = Float.parseFloat(txtDienTich.getText());
			String maLP = null;
			if (tenLoaiPhong.equals("Phòng thường")) {
				maLP = "NOR";
			} else
				maLP = "VIP";
			LoaiPhong lp = new LoaiPhong(maLP);
			TinhTrangPhong tt = new TinhTrangPhong("EMPT");
			Phong p = new Phong(ma, lp, sucChua, Gia, tt, dienTich);
			if (!dsPhong.themPhong(p)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				obj[0] = ma;
				obj[1] = tt.getTenTinhTrangPhong();
				obj[2] = sucChua;
				obj[3] = tenLoaiPhong;
				obj[4] = Gia;
				obj[5] = dienTich;
				model1.addRow(obj);
				xoaTrang();
				return true;
			}
		}
		return false;
	}

	public boolean suaPhong() {
		int row = tableDSPhong1.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Chọn phòng cần sửa");
		} else {
			Object[] obj = new Object[7];
			if (ktraDuLieu()) {
				String ma = tableDSPhong1.getValueAt(row, 0).toString();
				String tenLoaiPhong = String.valueOf(comboLoaiPhong.getSelectedItem());
				int sucChua = Integer.parseInt((String) comboSucChua.getSelectedItem());
				String tenTinhTang = String.valueOf(comboTinhTrang.getSelectedItem());
				float Gia = Float.parseFloat(txtGia.getText());
				float dienTich = Float.parseFloat(txtDienTich.getText());
				String maLP = null;
				if (tenLoaiPhong.equals("Phòng thường")) {
					maLP = "NOR";
				} else
					maLP = "VIP";
				LoaiPhong lp = new LoaiPhong(maLP);
				TinhTrangPhong tt = new TinhTrangPhong("EMPT");
				Phong p = new Phong(ma, lp, sucChua, Gia, tt, dienTich);
				obj[0] = ma;
				obj[1] = tt.getTenTinhTrangPhong();
				obj[2] = sucChua;
				obj[3] = tenLoaiPhong;
				obj[4] = Gia;
				obj[5] = dienTich;
				if (!dsPhong.suaPhong(p)) {
					JOptionPane.showMessageDialog(this, "Sửa thành công");
					tableDSPhong1.setValueAt(obj[2], row, 2);
					tableDSPhong1.setValueAt(obj[3], row, 3);
					tableDSPhong1.setValueAt(obj[4], row, 4);
					tableDSPhong1.setValueAt(obj[5], row, 5);
					xoaTrang();
					return true;
				}
			}
		}

		return false;
	}

	public void xoaTrang() {
		txtGia.setText("");
		txtMaPhong.setText("");
		txtDienTich.setText("");
		tableDSPhong1.clearSelection();
		lbTB.setText("");
	}

	public void upTable() {
		int i = 0;
		ArrayList<Phong> list = dsPhong.getDSPhong();
		df = new DecimalFormat("###,### VNĐ");
		dfs = new DecimalFormat("##,## M2");
		for (Phong p : list) {
			Object[] obj = new Object[7];
			obj[0] = p.getMaPhong().trim();
			obj[1] = p.getMaTinhTrangPhong().getTenTinhTrangPhong();
			obj[2] = p.getSucChua();
			obj[3] = p.getMaLoaiPhong().getTenLoaiPhong();
			obj[4] = df.format(p.getGiaPhong());
			obj[5] = dfs.format(p.getDienTich());
			model1.addRow(obj);
		}
		xoaTrang();
	}
	// them phong

	public void upCombobox() {
		List<TinhTrangPhong> list = dsPhong.getDSTinhTrang();
		int i = 0;
		for (TinhTrangPhong s : list) {
			comboTinhTrang.addItem(s.getTenTinhTrangPhong());
			comboTinhTrang.setSelectedIndex(i);
		}
	}

	public void upCombobox2() {
		int i = 0;
		List<LoaiPhong> list = dsPhong.getDSLoatPhong();
		for (LoaiPhong a : list) {
			comboLoaiPhong.addItem(a.getTenLoaiPhong());
			comboLoaiPhong.setSelectedIndex(i);
		}
	}

	public void setTextTB() {
		int row = tableDSPhong1.getSelectedRow();
		txtGia.setText(tableDSPhong1.getValueAt(row, 4).toString());
		txtMaPhong.setText(tableDSPhong1.getValueAt(row, 0).toString());
		txtDienTich.setText(tableDSPhong1.getValueAt(row, 5).toString());

		int i = 2;
		if (tableDSPhong1.getValueAt(row, 1).toString().equals("Phòng trống")) {
			i = 1;
		} else if (tableDSPhong1.getValueAt(row, 1).toString().equals("Phòng đã đặt")) {
			i = 0;
		}
		comboTinhTrang.setSelectedIndex(i);

		int a = 1;
		if (tableDSPhong1.getValueAt(row, 2).toString().equals("10")) {
			a = 0;
		} else if (tableDSPhong1.getValueAt(row, 2).toString().equals("20")) {
			a = 2;
		}
		comboSucChua.setSelectedIndex(a);

		int b = 0;
		if (tableDSPhong1.getValueAt(row, 3).toString().equals("Phòng VIP")) {
			b = 1;
		}
		comboLoaiPhong.setSelectedIndex(b);
	}

	public void showMessage(String message) {
		lbTB.setText(message);
	}

	public boolean ktraDuLieu() {

		try {
			float donGia = Float.parseFloat(txtGia.getText());
			if (donGia <= 0) {
				showMessage("(*)Đơn giá phải lớn hơn 0");
				txtGia.requestFocus();
				return false;
			}
		} catch (Exception e) {
			showMessage("(*)Đơn giá phải là số và không được để trống");
			txtGia.requestFocus();
			return false;
		}

		try {
			float dt = Float.parseFloat(txtDienTich.getText());
			if (dt <= 0) {
				showMessage("(*)Diện tích phải lớn hơn 0");
				txtGia.requestFocus();
				return false;
			}
		} catch (Exception e) {
			showMessage("(*)Diện tích phải là số và không được để trống");
			txtDienTich.requestFocus();
			return false;
		}
		return true;
	}

	// Lọc phòng theo loại
	public void locTheoLoaiPhongVIP() {
		clearTable();
		df = new DecimalFormat("###,### VNĐ");
		dfs = new DecimalFormat("##,## M2");
		ArrayList<Phong> list = dsPhong.getDSPhong();
		int i = 0;
		for (Phong p : list) {
			if (p.getMaLoaiPhong().getMaLoaiPhong().equals("VIP")) {
				Object[] obj = new Object[6];
				obj[0] = p.getMaPhong().trim();
				obj[1] = p.getMaTinhTrangPhong().getTenTinhTrangPhong();
				obj[2] = p.getSucChua();
				obj[3] = p.getMaLoaiPhong().getTenLoaiPhong();
				obj[4] = df.format(p.getGiaPhong());
				obj[5] = dfs.format(p.getDienTich());
				model1.addRow(obj);
			}
		}
	}

	// Lọc phòng theo loại
	public void locTheoLoaiPhongThuong() {
		clearTable();
		df = new DecimalFormat("###,### VNĐ");
		dfs = new DecimalFormat("##,## M2");
		ArrayList<Phong> list = dsPhong.getDSPhong();
		int i = 0;
		for (Phong p : list) {
			if (p.getMaLoaiPhong().getMaLoaiPhong().equals("NOR")) {
				Object[] obj = new Object[6];
				obj[0] = p.getMaPhong().trim();
				obj[1] = p.getMaTinhTrangPhong().getTenTinhTrangPhong();
				obj[2] = p.getSucChua();
				obj[3] = p.getMaLoaiPhong().getTenLoaiPhong();
				obj[4] = df.format(p.getGiaPhong());
				obj[5] = dfs.format(p.getDienTich());
				model1.addRow(obj);
			}
		}
	}

	public void clearTable() {
		while (tableDSPhong1.getRowCount() > 0) {
			model1.removeRow(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
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

}