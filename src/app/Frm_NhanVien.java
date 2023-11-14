package app;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.ArrayDocument;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DanhSachChucVu;
import dao.DanhSachKhachHang;
import dao.DanhSachNhanVien;
import dao.Dao_PhatSinhMa;
import entitys.ChucVu;
import entitys.KhachHang;
import entitys.LoaiKhachHang;
import entitys.LoaiPhong;
import entitys.NhanVien;
import entitys.Phong;
import entitys.TinhTrangPhong;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Frm_NhanVien extends JFrame implements MouseListener, ActionListener {

	private JTextField txtHoTen, txtDiaChi, txtChucVu, txtSDT, txtGioiTinh, txtNgaySinh, txtCCCD, txtMess;

	private JLabel lbTB, lbTTDV, lblHoTen, lblSDT, lblDiaChi, lblCCCD, lblChucVu, lblNgaySinh, lblGioiTinh,
			lblTrangThai, lbDSPhong;

	private DefaultTableModel model;
	private FixButton btnLamMoi, btnSua, btnThem;
	private JTable table;
	private JComboBox comboGT, comboTrangThai;
	Panel pnQLNV;
	JComboBox comboChucVu;
	JDateChooser ngaySinh;
	private Date ngayHienTai;
	private int ngay, thang, nam;
	DanhSachNhanVien dsNV;
	JPanel pnDSP, panel;

	public Frm_NhanVien() {
		setTitle("QUẢN LÝ NHÂN VIÊN");
		setSize(1400, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		gui();
	}

	public Panel getFrmQuanLyNhanVien() {
		return this.pnQLNV;
	}

	public void gui() {

		getContentPane().setLayout(null);
		pnQLNV = new Panel();
		pnQLNV.setBounds(0, 0, 1400, 700);
		getContentPane().add(pnQLNV);
		pnQLNV.setLayout(null);
//

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBackground(new Color(207, 169, 0));
		panel.setBounds(102, 60, 1200, 286);
		pnQLNV.add(panel);
		panel.setLayout(null);


		lbTTDV = new JLabel("Thông tin nhân viên");
		lbTTDV.setBounds(102, 30, 250, 20);
		pnQLNV.add(lbTTDV);
		lbTTDV.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTTDV.setForeground(new Color(255, 255, 255));

		lblHoTen = new JLabel("Họ và tên: ");
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblHoTen.setBounds(79, 21, 91, 27);
		lblHoTen.setForeground(new Color(255, 255, 255));
		panel.add(lblHoTen);

		lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSDT.setBounds(679, 20, 123, 28);
		lblSDT.setForeground(new Color(255, 255, 255));
		panel.add(lblSDT);

		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDiaChi.setBounds(79, 71, 72, 28);
		lblDiaChi.setForeground(new Color(255, 255, 255));
		panel.add(lblDiaChi);

		lblCCCD = new JLabel("CCCD:");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCCCD.setBounds(79, 114, 72, 36);
		lblCCCD.setForeground(new Color(255, 255, 255));
		panel.add(lblCCCD);

		lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblChucVu.setBounds(79, 160, 81, 36);
		lblChucVu.setForeground(new Color(255, 255, 255));
		panel.add(lblChucVu);

		lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNgaySinh.setForeground(new Color(255, 255, 255));
		lblNgaySinh.setBounds(74, 228, 96, 27);
		panel.add(lblNgaySinh);

		lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGioiTinh.setForeground(new Color(255, 255, 255));
		lblGioiTinh.setBounds(679, 114, 95, 36);
		panel.add(lblGioiTinh);

		lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTrangThai.setForeground(new Color(255, 255, 255));
		lblTrangThai.setBounds(679, 161, 123, 41);
		panel.add(lblTrangThai);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtHoTen.setBounds(180, 19, 300, 30);
		panel.add(txtHoTen);
		txtHoTen.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSDT.setBounds(812, 19, 300, 30);
		panel.add(txtSDT);
		txtSDT.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDiaChi.setBounds(180, 69, 932, 30);
		panel.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(180, 115, 300, 30);
		panel.add(txtCCCD);

		comboChucVu = new JComboBox();
		comboChucVu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboChucVu.setModel(new DefaultComboBoxModel(new String[] { "Quản Lý", "Nhân Viên" }));
		comboChucVu.setSelectedIndex(0);
		comboChucVu.setBounds(180, 166, 300, 30);
		panel.add(comboChucVu);

	

		ngaySinh = new JDateChooser();
		ngaySinh.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ngaySinh.setDateFormatString("dd/MM/yyyy");
		ngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ngaySinh.getCalendarButton().setPreferredSize(new Dimension(40, 30));
		ngaySinh.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/calendar.png")));

		ngaySinh.setBounds(180, 229, 300, 30);

		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		ngay = localDateTime.getDayOfMonth();
		thang = localDateTime.getMonthValue();
		nam = localDateTime.getYear();
		ngayHienTai = new Date(nam - 1900, thang - 1, ngay);
		ngaySinh.setDate(ngayHienTai);
		panel.add(ngaySinh);

		lbTB = new JLabel();
		lbTB.setHorizontalAlignment(SwingConstants.LEFT);
		lbTB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTB.setBounds(620, 200, 570, 36);
		lbTB.setForeground(Color.RED);
		panel.add(lbTB);

		btnThem = new FixButton("Thêm");
		btnThem.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_them.png")));
		btnThem.setText("Thêm");
		btnThem.setBounds(680, 240, 150, 36);
		panel.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnSua = new FixButton("Sửa");
		btnSua.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_sua.png")));
		btnSua.setText("Sửa");
		btnSua.setBounds(850, 240, 150, 36);
		panel.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_lammoi.png")));
		btnLamMoi.setText("Làm mới");
		btnLamMoi.setBounds(1020, 240, 150, 36);
		;
		panel.add(btnLamMoi);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));

		comboGT = new JComboBox();
		comboGT.setFont(new Font("Tahoma", Font.BOLD, 17));
		comboGT.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		comboGT.setBounds(812, 119, 300, 31);
		panel.add(comboGT);

		comboTrangThai = new JComboBox();
		comboTrangThai.setFont(new Font("Tahoma", Font.BOLD, 17));
		comboTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Đang làm việc", "Đã thôi việc" }));
		comboTrangThai.setBounds(812, 165, 300, 31);
		panel.add(comboTrangThai);

// Add the menu bar to the NORTH of the content pane
		pnDSP = new JPanel();
		pnDSP.setBounds(100, 370, 1200, 270);
		pnDSP.setLayout(null);

		lbDSPhong = new JLabel("Danh Sách Nhân Viên");
		lbDSPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPhong.setBounds(10, 0, 200, 25);
		pnDSP.add(lbDSPhong);

		String col[] = { "Mã NV", "Họ tên", "Chức vụ", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "CCCD",
				"Trạng thái" };
		model = new DefaultTableModel(col, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Không cho phép chỉnh sửa ô
			}
		};

		table = new JTable(model);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader = table.getTableHeader();
		tbHeader.setPreferredSize(new Dimension(100, 30));
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

		table.setFont(new Font("SansSerif", Font.PLAIN, 13));
		table.setSelectionBackground(new Color(158, 207, 155));
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 20, 1200, 260);
		pnDSP.add(scrollPane);
		pnQLNV.add(pnDSP);

		JLabel lbBG = new JLabel();
		lbBG.setBounds(0, 0, 1400, 700);
		lbBG.setIcon(new ImageIcon(Frm_NhanVien.class.getResource("/imgs/bg_chot1.png")));
		pnQLNV.add(lbBG);

	btnLamMoi.addActionListener(this);
	btnSua.addActionListener(this);
	btnThem.addActionListener(this);
	
	table.addMouseListener(this);
	
	ConnectDB.getInstance().connect();
			dsNV = new DanhSachNhanVien();
			upTable();	
			
			comboTrangThai.setEnabled(false);
			
}

//xoá trắng
public void xoaTrang() {
	txtHoTen.setText("");
	txtDiaChi.setText("");
	txtCCCD.setText("");
	comboChucVu.setSelectedIndex(0);
	ngaySinh.setDate(ngayHienTai);
	txtSDT.setText("");
	comboGT.setSelectedIndex(0);
	comboTrangThai.setSelectedIndex(0);
	table.clearSelection();
}


public void upTable() {
	ArrayList<NhanVien> listE = dsNV.getAllDanhSachNV();
	for (NhanVien nv : listE) {
		Object[] obj = new Object[9];
		obj[0] = nv.getMaNhanVien().trim();
		obj[1] = nv.getHoTenNhanVien().trim();
		obj[2] = nv.getchucVu().getTenChucVu();
		obj[3] = nv.isGioiTinh() ? "Nam" : "Nữ";
		obj[4] = nv.getNgaySinh().toString();
		obj[5] = nv.getDiaChi().trim();
		obj[6] = nv.getSdt().trim();
		obj[7] = nv.getSoCCCD().toString();
		obj[8] = nv.isTinhTrang() ? "Đang làm việc" : "Đã thôi việc";;
		model.addRow(obj);
	}
	xoaTrang();
}


	public void setTextTB() {
		int row = table.getSelectedRow();
		txtHoTen.setText(table.getValueAt(row, 1).toString());

		if (table.getValueAt(row, 2).toString().equalsIgnoreCase("Quản Lí"))
			comboChucVu.setSelectedIndex(0);
		else
			comboChucVu.setSelectedIndex(1);

		if (table.getValueAt(row, 3).toString().equalsIgnoreCase("Nam"))
			comboGT.setSelectedIndex(0);
		else
			comboGT.setSelectedIndex(1);

		String selectedDate = table.getValueAt(row, 4).toString();
		LocalDate curent = LocalDate.parse(selectedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		ngay = curent.getDayOfMonth();
		thang = curent.getMonthValue();
		nam = curent.getYear();
		ngayHienTai = new Date(nam - 1900, thang - 1, ngay);
		ngaySinh.setDate(ngayHienTai);

		txtDiaChi.setText(table.getValueAt(row, 5).toString());

		txtSDT.setText(table.getValueAt(row, 6).toString());
		txtCCCD.setText(table.getValueAt(row, 7).toString());
		if (table.getValueAt(row, 8).toString().equalsIgnoreCase("Đang làm việc"))
			comboTrangThai.setSelectedIndex(0);
		else
			comboTrangThai.setSelectedIndex(1);
	}

	public static void main(String[] args) {
		new Frm_NhanVien().setVisible(true);
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
		//

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm")) {
				btnThem.setText("Xác nhận");
				btnSua.setText("Huỷ");
			} else if (btnThem.getText().equalsIgnoreCase("Xác nhận")) {
				if (themNV()) {
					btnSua.setText("Sửa");
					btnThem.setText("Thêm");
				}
			} else if (btnThem.getText().equals("Xác nhận ")) {
				if (suaNV()) {
					btnThem.setText("Thêm");
					btnSua.setText("Sửa");
				}
			}
		} else if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Huỷ")) {
				btnThem.setText("Thêm");
				btnSua.setText("Sửa");
			} else if (btnSua.getText().equals("Sửa")) {
				btnThem.setText("Xác nhận ");
				btnSua.setText("Huỷ");
			}
		} else if (o.equals(btnLamMoi)) {
			xoaTrang();
		}

	}

	// them nhan vien
	public boolean themNV() {
		Object[] obj = new Object[9];
		if (ktraDuLieu()) {
			Dao_PhatSinhMa makh = new Dao_PhatSinhMa();
			String ma = makh.getMaNVCuoi();
			String ten = txtHoTen.getText();
			String sdt = txtSDT.getText();
			String dt = txtDiaChi.getText();
			String cccd = txtCCCD.getText();
			String gt = (String) comboGT.getSelectedItem();
			boolean gioitinh;
			if (gt.equals("Nam")) {
				gioitinh = true;
			} else
				gioitinh = false;
			String tt = (String) comboGT.getSelectedItem();
			boolean trangThai;
			if (tt.equals("Đang làm việc")) {
				trangThai = true;
			} else
				trangThai = false;

			Date date = ngaySinh.getDate();
			Date ngaySinhh = new Date(date.getYear(), date.getMonth(), date.getDate());

			String tenChucVu = String.valueOf(comboChucVu.getSelectedItem());
			String maChucVu = null;
			if (tenChucVu.equals("Quản lý")) {
				maChucVu = "QL";
			} else
				maChucVu = "NV";
			LocalDate ngayinh = ngaySinhh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			ChucVu cv = new ChucVu(maChucVu);
			NhanVien nv = new NhanVien(ma, ten, cccd, maChucVu, dt, gioitinh, cv, ngayinh, trangThai);
			if (!dsNV.themNhanVien(nv)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				obj[0] = ma;
				obj[1] = ten;
				obj[2] = cv.getTenChucVu();
				obj[3] = gt;
				obj[4] = ngayinh;
				obj[5] = dt;
				obj[6] = sdt;
				obj[7] = cccd;
				obj[8] = tt;
				model.addRow(obj);
				xoaTrang();
				return true;
			}
		}
		return false;
	}

	public boolean suaNV() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Chọn nhân viên cần sửa");
		} else {
			Object[] obj = new Object[9];
			if (ktraDuLieu()) {

				String ma = table.getValueAt(row, 0).toString();
				String ten = txtHoTen.getText();
				String sdt = txtSDT.getText();
				String dt = txtDiaChi.getText();
				String cccd = txtCCCD.getText();
				String gt = (String) comboGT.getSelectedItem();
				boolean gioitinh;
				if (gt.equals("Nam")) {
					gioitinh = true;
				} else
					gioitinh = false;
				String tt = (String) comboGT.getSelectedItem();
				boolean trangThai;
				if (tt.equals("Đang làm việc")) {
					trangThai = true;
				} else
					trangThai = false;

				Date date = ngaySinh.getDate();
				Date ngaySinhh = new Date(date.getYear(), date.getMonth(), date.getDate());

				String tenChucVu = String.valueOf(comboChucVu.getSelectedItem());
				String maChucVu = null;
				if (tenChucVu.equals("Quản lý")) {
					maChucVu = "QL";
				} else
					maChucVu = "NV";
				LocalDate ngayinh = ngaySinhh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				ChucVu cv = new ChucVu(maChucVu);
				NhanVien nv = new NhanVien(ma, ten, cccd, maChucVu, dt, gioitinh, cv, ngayinh, trangThai);
				obj[0] = ma;
				obj[1] = ten;
				obj[2] = cv.getTenChucVu();
				obj[3] = gt;
				obj[4] = ngayinh;
				obj[5] = dt;
				obj[6] = sdt;
				obj[7] = cccd;
				obj[8] = tt;
				if (!dsNV.suaNhanVien(nv)) {
					JOptionPane.showMessageDialog(this, "Sửa thành công");
					table.setValueAt(obj[1], row, 1);
					table.setValueAt(obj[2], row, 2);
					table.setValueAt(obj[3], row, 3);
					table.setValueAt(obj[4], row, 4);
					table.setValueAt(obj[5], row, 5);
					table.setValueAt(obj[6], row, 6);
					table.setValueAt(obj[7], row, 7);
					table.setValueAt(obj[8], row, 8);
					xoaTrang();
					return true;
				}
			}

		}
		return false;
	}

	//
	public void showMessage(String message) {
		lbTB.setText(message);
	}

	public boolean ktraDuLieu() {
		String ten = txtHoTen.getText();
		if (ten.equals("") || !ten.matches(
				"^[A-Z][ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*")) {
			showMessage("(*) Tên không được để trống và viết hoa chữ cái đầu");
			txtHoTen.requestFocus();
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
		if (dt.equals("") || !dt.matches("^(0[0-9]{9})$")) {
			showMessage("(*)Số điện thoại không để trống và chỉ được 10 số, bắt đầu bằng số 0");
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
		String dc = txtDiaChi.getText();
		if (dc.equals("") || !dc.matches("[A-Z][a-zA-Z \\d]*")) {
			showMessage("Địa chỉ không được để trống và viết hoa chữ cái đầu");
			txtDiaChi.requestFocus();
			return false;
		}
		return true;
	}
}
