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
import java.awt.event.ActionEvent;

public class Frm_KhachHang extends JFrame implements ActionListener,MouseListener{
	private JTextField  txtTenKH, txtLoaiKH, txtSDT,  txtCCCD ,txtDTL;
	private DefaultTableModel model;
	private JTable table;
	private FixButton btnLamMoi,btnSua, btnThem;
	private JComboBox comboGT,comboLKH ;
	DanhSachKhachHang dsKh;
	
	Panel pnQLKH;

	public Panel getFrmQuanLyKhachHang() {
		return this.pnQLKH;
	}
	
	public Frm_KhachHang() throws SQLException {
	//getContentPane().setBackground(Color.CYAN);
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

	JPanel panel = new JPanel();
	    panel.setBackground(new Color(190, 157, 157,181));	
		panel.setBounds(102, 51, 1200, 271);
		pnQLKH.add(panel);
		panel.setLayout(null);
		
		JLabel lbTTDV = new JLabel("Thông tin khách hàng");
		lbTTDV.setBounds(102, 21, 250, 20);
		pnQLKH.add(lbTTDV);
		lbTTDV.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTTDV.setForeground(new Color(255, 255, 255));
		
		JLabel lblHoTen = new JLabel("Họ và tên: ");
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblHoTen.setForeground(new Color(255, 255, 255));
		lblHoTen.setBounds(79, 21, 91, 27);
		panel.add(lblHoTen);
		
		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSDT.setForeground(new Color(255, 255, 255));
		lblSDT.setBounds(679, 20, 123, 28);
		panel.add(lblSDT);
		
		
		
		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCCCD.setForeground(new Color(255, 255, 255));
		lblCCCD.setBounds(79, 81, 72, 36);
		panel.add(lblCCCD);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGioiTinh.setForeground(new Color(255, 255, 255));
		lblGioiTinh.setBounds(679, 81, 95, 36);
		panel.add(lblGioiTinh);
		
		
		JLabel lblLoaiKhachHgang = new JLabel("Loại KH:");
		lblLoaiKhachHgang.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLoaiKhachHgang.setForeground(new Color(255, 255, 255));
		lblLoaiKhachHgang.setBounds(79, 143, 81, 36);
		panel.add(lblLoaiKhachHgang);
		
		
		JLabel lblDTL = new JLabel("ĐIểm tích luỹ:");
		lblDTL.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDTL.setForeground(new Color(255, 255, 255));
		lblDTL.setBounds(679, 141, 123, 41);
		panel.add(lblDTL);
		
		txtTenKH = new JTextField();
//		txtHoTen = new JTextField();
		txtTenKH.setBounds(180, 19,300 , 30);
		panel.add(txtTenKH);
		txtTenKH.setColumns(10);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(812, 19, 300, 30);
		panel.add(txtSDT);
		txtSDT.setColumns(10);
		
	
		
		
		txtCCCD = new JTextField();
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(180, 83, 300, 30);
		panel.add(txtCCCD);
		
		txtLoaiKH = new JTextField();
		txtLoaiKH.setColumns(10);
		txtLoaiKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtLoaiKH.setBounds(180, 146, 300, 30);
		txtLoaiKH.enable(false);
		panel.add(txtLoaiKH);
		//CRUD
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
		
		
		txtDTL = new JTextField();
		txtDTL.setColumns(10);
		txtDTL.setBounds(812, 145, 300, 31);
		txtDTL.enable(false);
		panel.add(txtDTL);
		
		


	// Add the menu bar to the NORTH of the content pane
		JPanel pnDSP = new JPanel();
		pnDSP.setBounds(100, 350, 1200, 270);
		pnDSP.setLayout(null);

		JLabel lbDSPhong = new JLabel("Danh sách khách hàng");
		lbDSPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPhong.setBounds(10, 0, 200, 25);
		pnDSP.add(lbDSPhong);
		
		String col[] = { "Mã KH","Họ tên", "Loại KH", "Giới tính","SĐT", "CCCD", "Điểm tích luỹ"};
		model = new DefaultTableModel(col, 0);

		table = new JTable(model);
				
		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader = table.getTableHeader();
		tbHeader.setPreferredSize(new Dimension(100, 30));
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng
//
		table.setShowHorizontalLines(true);
		table.setShowGrid(true);
		table.setBackground(Color.white);
		table.setFont(new Font("SansSerif", Font.PLAIN, 13));
		table.setSelectionBackground(new Color(158, 207, 155));
	
		table.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 20, 1200, 300);
		scrollPane.getHorizontalScrollBar();
		pnDSP.add(scrollPane);
		scrollPane.setViewportView(table);
		pnQLKH.add(pnDSP);
// backgroud	
		JLabel lbBG = new JLabel();
		lbBG.setBounds(0,0,1400,700);
		lbBG.setIcon(new ImageIcon(Frm_KhachHang.class.getResource("/imgs/bg_chot1.png")));
		pnQLKH.add(lbBG);
		
		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		table.addMouseListener(this);
		
	// kết nối data
		ConnectDB.getInstance().connect();
		// Danh sach Mat Hang
		dsKh = new DanhSachKhachHang();
		upTable();

	}


	public static void main(String[] args) throws SQLException {
	new Frm_KhachHang().setVisible(true);
	
	System.out.println("dsds");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o == btnLamMoi ) {
			xoaTrang();
		}
		else if(o == btnThem) {	
			themKH();
		}
		else if(o == btnSua) {
				suaKH();
		}
	}
	
	public void xoaTrang() {
		txtTenKH.setText("");
		txtSDT.setText("");
		txtCCCD.setText("");
		table.clearSelection();
	}

//	String maKH = rs.getString(1);

	
	public void upTable() {
		int i = 0;
		ArrayList<KhachHang> list = dsKh.getDSKhachHang();
		for (KhachHang kh : list) {
			Object[] obj = new Object[7];
			obj[0] = kh.getMaKhachHang().trim();
			obj[1] = kh.getHoTenKhachHang().trim();
			obj[2] = kh.getLoaiKhachHang().getTenLoaiKhachHang();
			obj[4] = kh.getSoDienThoai().trim();
			obj[5] = kh.getSoCCCD().trim();
			String gt;
			if(kh.getGioiTinh())
				gt = "Nam";
			else gt = "Nữ";
			obj[3] = gt;
			obj[6] = kh.getDiemTichLuy();
			model.addRow(obj);
		}
		xoaTrang();
	}
	// theem khach hang
	public boolean themKH() {
		Object[] obj = new Object[7];
		Dao_PhatSinhMa makh = new Dao_PhatSinhMa();
			String ma = makh.getMaNVCuoi();
			String ten = txtTenKH.getText();
			String dt = txtSDT.getText();
			String cccd = txtCCCD.getText();
			String gt = (String) comboGT.getSelectedItem();
			boolean gioitinh;
			if(gt.equals("Nam")) {
				gioitinh = true;
			}else gioitinh = false;
			LoaiKhachHang lkh = new LoaiKhachHang("NOR","Khách hàng thường");
			KhachHang kh = new KhachHang(ma, ten, cccd, dt,0, gioitinh, lkh);
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
	

		return false;
	}
	//update khach hang
	public boolean suaKH() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Chọn nhân viên cần sửa");
		} else {
			Object[] obj = new Object[7];
				String ma = table.getValueAt(row, 0).toString();
				String ten = txtTenKH.getText().toString();
				String dt = txtSDT.getText().toString();
				String cccd = txtCCCD.getText().toString();
				String gt = (String) comboGT.getSelectedItem();
				boolean gioitinh;
				if(gt.equals("Nam")) {
					gioitinh = true;
				}else gioitinh = false;
				LoaiKhachHang lkh = new LoaiKhachHang("NOR","Khách hàng thường");
				obj[0] = ma;
				obj[1] = ten;
				obj[2] = lkh.getTenLoaiKhachHang();
				obj[3] = gt;
				obj[4] = dt;
				obj[5] = cccd;
				obj[6] = 0;
			
				KhachHang kh = new KhachHang(ma, ten, cccd, dt,0,gioitinh, lkh);
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
		
		return false;
	}
	//click table
	public void setTextTB() {
		int row = table.getSelectedRow();
		txtTenKH.setText(table.getValueAt(row, 1).toString());
		txtCCCD.setText(table.getValueAt(row, 5).toString());
		txtLoaiKH.setText(table.getValueAt(row, 2).toString());
		int i = 1;
		if(table.getValueAt(row, 3).toString().equals("Nam"))
			i =0;
		comboGT.setSelectedIndex(i);
		txtSDT.setText(table.getValueAt(row, 4).toString());
		txtDTL.setText(table.getValueAt(row, 6).toString());
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
