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
import entitys.ChucVu;
import entitys.KhachHang;
import entitys.NhanVien;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Frm_NhanVien extends JFrame implements MouseListener, ActionListener {
	private JTextField txtHoTen, txtTenKH, txtDiaChi, txtChucVu, txtSDT, txtGioiTinh, txtNgaySinh, txtCCCD, txtMess;
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

	public Frm_NhanVien() {
		setTitle("QUẢN LÝ Nhân Viên");
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
		JPanel panel = new JPanel();
		panel.setBackground(new Color(190, 157, 157, 181));
		panel.setBounds(102, 60, 1200, 286);
		pnQLNV.add(panel);
		panel.setLayout(null);

		JLabel lbTTDV = new JLabel("Thông tin nhân viên");
		lbTTDV.setBounds(102, 30, 250, 20);
		pnQLNV.add(lbTTDV);
		lbTTDV.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTTDV.setForeground(new Color(255, 255, 255));

		JLabel lblHoTen = new JLabel("Họ và tên: ");
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblHoTen.setBounds(79, 21, 91, 27);
		lblHoTen.setForeground(new Color(255, 255, 255));
		panel.add(lblHoTen);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSDT.setBounds(679, 20, 123, 28);
		lblSDT.setForeground(new Color(255, 255, 255));
		panel.add(lblSDT);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDiaChi.setBounds(79, 71, 72, 28);
		lblDiaChi.setForeground(new Color(255, 255, 255));
		panel.add(lblDiaChi);

		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCCCD.setBounds(79, 114, 72, 36);
		lblCCCD.setForeground(new Color(255, 255, 255));
		panel.add(lblCCCD);

		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblChucVu.setBounds(79, 160, 81, 36);
		lblChucVu.setForeground(new Color(255, 255, 255));
		panel.add(lblChucVu);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNgaySinh.setForeground(new Color(255, 255, 255));
		lblNgaySinh.setBounds(74, 228, 96, 27);
		panel.add(lblNgaySinh);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGioiTinh.setForeground(new Color(255, 255, 255));
		lblGioiTinh.setBounds(679, 114, 95, 36);
		panel.add(lblGioiTinh);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
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

		btnThem = new FixButton("Thêm");
		btnThem.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_them.png")));
		btnThem.setText("Thêm");
		btnThem.setBounds(680, 223, 150, 36);
		panel.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnSua = new FixButton("Sửa");
		btnSua.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_sua.png")));
		btnSua.setText("Sửa");
		btnSua.setBounds(850, 223, 150, 36);
		panel.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_lammoi.png")));
		btnLamMoi.setText("Làm mới");
		btnLamMoi.setBounds(1020, 223, 150, 36);
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
		comboTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Đang làm việc", "Ngưng làm việc" }));
		comboTrangThai.setBounds(812, 165, 300, 31);
		panel.add(comboTrangThai);

// Add the menu bar to the NORTH of the content pane
		JPanel pnDSP = new JPanel();
		pnDSP.setBounds(100, 370, 1200, 270);
		pnDSP.setLayout(null);

		JLabel lbDSPhong = new JLabel("Danh Sách Nhân Viên");
		lbDSPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPhong.setBounds(10, 0, 150, 25);
		pnDSP.add(lbDSPhong);

		String col[] = { "Mã NV", "Họ tên", "Chức vụ", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "CCCD", "Trạng thái",
				"Mật khẩu" };
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
		scrollPane.setBounds(0, 20, 1200, 700);
		pnDSP.add(scrollPane);
		pnQLNV.add(pnDSP);

		JLabel lbBG = new JLabel();
		lbBG.setBounds(0, 0, 1400, 700);
		lbBG.setIcon(new ImageIcon(Frm_NhanVien.class.getResource("/imgs/bg_chot1.png")));
		pnQLNV.add(lbBG);

		table.addMouseListener(this);

		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);

		ConnectDB.getInstance().connect();
		dsNV = new DanhSachNhanVien();
		upTable();

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
			System.out.println(nv.getchucVu().getTenChucVu());
			obj[2] = nv.getchucVu().getTenChucVu();
			obj[3] = nv.isGioiTinh() ? "Nam" : "Nữ";
			obj[4] = nv.getNgaySinh().toString();
			obj[5] = nv.getDiaChi().trim();
			obj[6] = nv.getSdt().trim();
			obj[7] = nv.getSoCCCD().toString();
			obj[8] = nv.isTinhTrang() ? "Đang làm việc" : "Đã thôi việc";
			;
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
				btnThem.setText("Lưu");
				btnSua.setText("Huỷ");
//		themNhanVien();			
			} else {
				btnThem.setText("Thêm");
				btnSua.setText("Sửa");
			}
		} else if (o.equals(btnSua)) {
			if (btnSua.getText().equalsIgnoreCase("Sửa")) {
				btnThem.setText("Lưu");
				btnSua.setText("Huỷ");
//		themNhanVien();			
			} else {
				btnThem.setText("Thêm");
				btnSua.setText("Sửa");
			}
		} else {
			xoaTrang();
		}

	}

}
