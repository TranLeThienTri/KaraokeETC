package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.*;
import dao.DanhSachKhachHang;
import dao.Dao_PhatSinhMa;
import entitys.KhachHang;
import entitys.LoaiKhachHang;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Frm_KhachHang extends JFrame implements ActionListener, MouseListener {
	private JLabel lbTTKH, lblHoTen, lblSDT, lblCCCD, lblGioiTinh, lblLoaiKhachHgang, lblDTL, lbDSPhong, lbBG, lbTB;
	private JTextField txtTenKH, txtLoaiKH, txtSDT, txtCCCD, txtDTL;

	private DefaultTableModel model;
	private JTableHeader tbHeader;
	private JTable table;

	private JScrollPane scrollPane;
	private FixButton btnLamMoi, btnSua, btnThem;
	private JComboBox comboGT, comboLKH;
	DanhSachKhachHang dsKh;

	Panel pnQLKH;
	JPanel panel, pnDSP;

	public Panel getFrmQuanLyKhachHang() {
		return this.pnQLKH;
	}

	public Frm_KhachHang() throws SQLException {
		// getContentPane().setBackground(Color.CYAN);
		setTitle("QUẢN LÝ Khách Hàng");
		setSize(1400, 670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		gui();
	}

	public void gui() throws SQLException {
		getContentPane().setLayout(null);

		pnQLKH = new Panel();
		pnQLKH.setBounds(0, 0, 1400, 670);
		getContentPane().add(pnQLKH);
		pnQLKH.setLayout(null);
		//

		panel = new JPanel();

		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	    panel.setBackground(new Color(207, 169, 0));	

		panel.setBounds(102, 51, 1200, 271);
		pnQLKH.add(panel);
		panel.setLayout(null);

		lbTTKH = new JLabel("Thông tin khách hàng");
		lbTTKH.setBounds(102, 21, 250, 20);
		pnQLKH.add(lbTTKH);
		lbTTKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTTKH.setForeground(new Color(255, 255, 255));

		lblHoTen = new JLabel("Họ và tên: ");

		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblHoTen.setForeground(new Color(255, 255, 255));
		lblHoTen.setBounds(79, 21, 91, 27);
		panel.add(lblHoTen);

		lblSDT = new JLabel("Số điện thoại:");

		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSDT.setForeground(new Color(255, 255, 255));
		lblSDT.setBounds(679, 20, 123, 28);
		panel.add(lblSDT);

		lblCCCD = new JLabel("CCCD:");

		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCCCD.setForeground(new Color(255, 255, 255));
		lblCCCD.setBounds(79, 81, 72, 36);
		panel.add(lblCCCD);

		lblGioiTinh = new JLabel("Giới tính:");

		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGioiTinh.setForeground(new Color(255, 255, 255));
		lblGioiTinh.setBounds(679, 81, 95, 36);
		panel.add(lblGioiTinh);

		lblLoaiKhachHgang = new JLabel("Loại KH:");

		lblLoaiKhachHgang.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLoaiKhachHgang.setForeground(new Color(255, 255, 255));
		lblLoaiKhachHgang.setBounds(79, 143, 81, 36);
		panel.add(lblLoaiKhachHgang);

		lblDTL = new JLabel("ĐIểm tích luỹ:");
		lblDTL.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDTL.setForeground(new Color(255, 255, 255));
		lblDTL.setBounds(679, 141, 123, 41);
		panel.add(lblDTL);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtTenKH.setBounds(180, 19, 300, 30);
		panel.add(txtTenKH);
		txtTenKH.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setBounds(812, 19, 300, 30);
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(txtSDT);
		txtSDT.setColumns(10);

		txtCCCD = new JTextField();
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(180, 83, 300, 30);
		txtCCCD.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(txtCCCD);

		txtLoaiKH = new JTextField();
		txtLoaiKH.setColumns(10);
		txtLoaiKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtLoaiKH.setBounds(180, 146, 300, 30);
		txtLoaiKH.setEditable(false);
		panel.add(txtLoaiKH);

		txtDTL = new JTextField();
		txtDTL.setColumns(10);
		txtDTL.setBounds(812, 145, 300, 31);
		txtDTL.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtDTL.setEditable(false);
		panel.add(txtDTL);

		lbTB = new JLabel();
		lbTB.setHorizontalAlignment(SwingConstants.LEFT);
		lbTB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTB.setBounds(400, 178, 500, 36);
		lbTB.setForeground(Color.RED);
		panel.add(lbTB);

		btnThem = new FixButton("Thêm");
		btnThem.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_them.png")));
		btnThem.setBounds(375, 223, 150, 36);
		panel.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnSua = new FixButton("Sửa");
		btnSua.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_sua.png")));
		btnSua.setBounds(550, 223, 150, 36);
		panel.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_lammoi.png")));
		btnLamMoi.setBounds(720, 223, 150, 36);
		panel.add(btnLamMoi);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));

		comboGT = new JComboBox();
		comboGT.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		comboGT.setSelectedIndex(0);
		comboGT.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboGT.setBounds(812, 82, 300, 31);
		panel.add(comboGT);

		// Danh sách khách hàng table
		pnDSP = new JPanel();

		pnDSP.setBounds(100, 350, 1200, 270);
		pnDSP.setLayout(null);

		lbDSPhong = new JLabel("Danh sách khách hàng");
		lbDSPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPhong.setBounds(10, 0, 200, 25);
		pnDSP.add(lbDSPhong);

		String col[] = { "Mã KH", "Họ tên", "Loại KH", "Giới tính", "SĐT", "CCCD", "Điểm tích luỹ" };
		model = new DefaultTableModel(col, 0);
		table = new JTable(model);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		tbHeader = table.getTableHeader();
		
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng
		table.setShowHorizontalLines(true);
		table.setShowGrid(true);
		table.setBackground(Color.white);
		table.setFont(new Font("SansSerif", Font.PLAIN, 13));
		table.setSelectionBackground(new Color(158, 207, 155));

		table.setRowHeight(30);
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 20, 1200, 250);
		scrollPane.getHorizontalScrollBar();
		pnDSP.add(scrollPane);
		scrollPane.setViewportView(table);
		pnQLKH.add(pnDSP);
// backgroud	

		lbBG = new JLabel();
		lbBG.setBounds(0, 0, 1400, 700);

		lbBG.setIcon(new ImageIcon(Frm_KhachHang.class.getResource("/imgs/bg_chot1.png")));
		pnQLKH.add(lbBG);

		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		table.addMouseListener(this);

		ConnectDB.getInstance().connect();

		dsKh = new DanhSachKhachHang();
		upTable();

	}

	public static void main(String[] args) throws SQLException {

		new Frm_KhachHang().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm")) {
				btnThem.setText("Xác nhận");
				btnSua.setText("Huỷ");
			}  else if (btnThem.getText().equalsIgnoreCase("Xác nhận")) {
				if(themKH()) {
				btnSua.setText("Sửa");
				btnThem.setText("Thêm");
				}
			} else if (btnThem.getText().equals("Xác nhận ")) {
				if(suaKH()) {
				btnThem.setText("Thêm");
				btnSua.setText("Sửa");
				}
			}
		} else if (o.equals(btnSua)) {
			if (btnSua.getText().equalsIgnoreCase("Huỷ")) {
				btnThem.setText("Thêm");
				btnSua.setText("Sửa");
			} else if (btnSua.getText().equalsIgnoreCase("Sửa")) {
				btnThem.setText("Xác nhận ");
				btnSua.setText("Huỷ");
			}
		} else if (o.equals(btnLamMoi)) {
			xoaTrang();

		}

	}

	public void xoaTrang() {
		txtTenKH.setText("");
		txtSDT.setText("");
		txtCCCD.setText("");
		txtLoaiKH.setText("");
		txtDTL.setText("");
		lbTB.setText("");
		table.clearSelection();
	}

	public void upTable() {
		int i = 0;
		ArrayList<KhachHang> list = dsKh.getDSKhachHang();
		for (KhachHang kh : list) {
			Object[] obj = new Object[7];
			obj[0] = kh.getMaKhachHang().trim();
			obj[1] = kh.getHoTenKhachHang().trim();
			obj[2] = kh.getLoaiKhachHang().getTenLoaiKhachHang();
			obj[3] = kh.getGioiTinh() ? "Nam" : "Nữ";
			obj[4] = kh.getSoDienThoai().trim();
			obj[5] = kh.getSoCCCD().trim();
			obj[6] = kh.getDiemTichLuy();
			model.addRow(obj);
		}
		xoaTrang();
	}

	// theem khach hang
	public boolean themKH() {
		Object[] obj = new Object[7];
		if (ktraDuLieu()) {
			Dao_PhatSinhMa makh = new Dao_PhatSinhMa();
			String ma = makh.getMaKHCuoi();
			String ten = txtTenKH.getText();
			String dt = txtSDT.getText();
			String cccd = txtCCCD.getText();
			String gt = (String) comboGT.getSelectedItem();
			boolean gioitinh;
			if (gt.equals("Nam")) {
				gioitinh = true;
			} else
				gioitinh = false;
			LoaiKhachHang lkh = new LoaiKhachHang("NOR", "Khách hàng thường");
			KhachHang kh = new KhachHang(ma, ten, cccd, dt, 0, gioitinh, lkh);
			if (!dsKh.themKhachHang(kh)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				obj[0] = ma;
				obj[1] = ten;
				obj[2] = lkh.getTenLoaiKhachHang();
				obj[3] = gt;
				obj[4] = dt;
				obj[5] = cccd;
				obj[6] = kh.getDiemTichLuy();
				model.addRow(obj);
				xoaTrang();
				return true;
			}
		}
		return false;
	}

	// update khach hang
	public boolean suaKH() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Chọn nhân viên cần sửa");
		} else {
			Object[] obj = new Object[7];
			if (ktraDuLieu()) {
				String ma = table.getValueAt(row, 0).toString();
				String ten = txtTenKH.getText().toString();
				String dt = txtSDT.getText().toString();
				String cccd = txtCCCD.getText().toString();
				String gt = (String) comboGT.getSelectedItem();
				boolean gioitinh;
				if (gt.equals("Nam")) {
					gioitinh = true;
				} else
					gioitinh = false;
				LoaiKhachHang lkh = new LoaiKhachHang("NOR", "Khách hàng thường");
				obj[0] = ma;
				obj[1] = ten;
				obj[2] = lkh.getTenLoaiKhachHang();
				obj[3] = gt;
				obj[4] = dt;
				obj[5] = cccd;
				obj[6] = 0;

				KhachHang kh = new KhachHang(ma, ten, cccd, dt, 0, gioitinh, lkh);
				if (!dsKh.suaKhachHang(kh)) {
					JOptionPane.showMessageDialog(this, "Sửa thành công");
					table.setValueAt(obj[1], row, 1);
					table.setValueAt(obj[3], row, 3);
					table.setValueAt(obj[4], row, 4);
					table.setValueAt(obj[5], row, 5);
					xoaTrang();
					return true;
				}
			}
		}
		return false;
	}
	// click table

	public void setTextTB() {
		int row = table.getSelectedRow();
		txtTenKH.setText(table.getValueAt(row, 1).toString());
		txtCCCD.setText(table.getValueAt(row, 5).toString());
		txtLoaiKH.setText(table.getValueAt(row, 2).toString());
		int i = 1;
		if (table.getValueAt(row, 3).toString().equals("Nam"))
			i = 0;
		comboGT.setSelectedIndex(i);
		txtSDT.setText(table.getValueAt(row, 4).toString());
		txtDTL.setText(table.getValueAt(row, 6).toString());
	}

	public void showMessage(String message) {
		lbTB.setText(message);
	}

	// kiểm tra regex
	public boolean ktraDuLieu() {

		String ten = txtTenKH.getText();
		if (ten.equals("") || !ten.matches("\\b\\p{Lu}[\\p{L} ]*\\b")) {
			showMessage("(*) Tên không được để trống và viết hoa chữ cái đầu ");
			txtTenKH.requestFocus();
			return false;
		}
		String dt = txtSDT.getText();
		for (int i = 0; i < table.getRowCount(); i++) {
			if (dt.equals(table.getValueAt(i, 4).toString())) {
				showMessage("(*)Số điện thoại đã tồn tại");
				txtSDT.requestFocus();
				return false;
			}
		}
		if (dt.equals("") || !dt.matches("\\d{10}")) {
			showMessage("(*)Số điện thoại không để trống và chỉ được 10 số");
			txtSDT.requestFocus();
			return false;
		}

		String cccd = txtCCCD.getText();
		if (cccd.equals("")) {
			showMessage("(*) CCCD không được để trống ");
			txtCCCD.requestFocus();
			return false;
		} else if (!cccd.matches("\\d{12}")) {
			showMessage("(*) CCCD không được dùng kí tự và chỉ được 12 số ");
			txtCCCD.requestFocus();
			return false;
		}
		return true;
	}
	
	
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
	

}
