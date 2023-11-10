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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frm_KhachHang extends JFrame{
	private JTextField txtHoTen, txtTenKH, txtDiaChi, txtChucVu, txtSDT, txtGioiTinh,txtNgaySinh , txtCCCD,txtMess;
	private DefaultTableModel model;
	private JButton btnLamMoi,btnSua, btnThem;
	private JTable table;
	private JComboBox comboGT,comboTrangThai ;
	Panel pnQLKH;

	public Panel getFrmQuanLyKhachHang() {
		return this.pnQLKH;
	}
	
	public Frm_KhachHang() {
	//getContentPane().setBackground(Color.CYAN);
	setTitle("QUẢN LÝ Khách Hàng");
	setSize(1400, 700);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);

	gui();
	}
	
	public void gui() {
		getContentPane().setLayout(null);

		pnQLKH = new Panel();
		pnQLKH.setBounds(0, 0, 1400, 700);
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
		lblCCCD.setBounds(79, 67, 72, 36);
		panel.add(lblCCCD);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGioiTinh.setForeground(new Color(255, 255, 255));
		lblGioiTinh.setBounds(679, 67, 95, 36);
		panel.add(lblGioiTinh);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDiaChi.setForeground(new Color(255, 255, 255));
		lblDiaChi.setBounds(79, 122, 72, 28);
		panel.add(lblDiaChi);
		
		JLabel lblLoaiKhachHgang = new JLabel("Loại KH:");
		lblLoaiKhachHgang.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLoaiKhachHgang.setForeground(new Color(255, 255, 255));
		lblLoaiKhachHgang.setBounds(79, 163, 81, 36);
		panel.add(lblLoaiKhachHgang);
		
		
		JLabel lblDTL = new JLabel("ĐIểm tích luỹ:");
		lblDTL.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDTL.setForeground(new Color(255, 255, 255));
		lblDTL.setBounds(679, 161, 123, 41);
		panel.add(lblDTL);
		
		txtHoTen = new JTextField();
//		txtHoTen = new JTextField();
		txtHoTen.setBounds(180, 19,300 , 30);
		panel.add(txtHoTen);
		txtHoTen.setColumns(10);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(812, 19, 300, 30);
		panel.add(txtSDT);
		txtSDT.setColumns(10);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(180, 120, 932, 30);
		panel.add(txtDiaChi);
		txtDiaChi.setColumns(10);
		
		
		txtCCCD = new JTextField();
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(180, 69, 300, 30);
		panel.add(txtCCCD);
		
		txtChucVu = new JTextField();
		txtChucVu.setColumns(10);
		txtChucVu.setBounds(180, 166, 300, 30);
		panel.add(txtChucVu);
		

		
		
		FixButton btnThem = new FixButton("Thêm");
		btnThem.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_them.png")));
		btnThem.setBounds(375, 223, 150, 36);
		panel.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));

		FixButton btnSua = new FixButton("Sửa");
		btnSua.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_sua.png")));
		btnSua.setBounds(550, 223, 150, 36);
		panel.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));

		FixButton btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_lammoi.png")));
		btnLamMoi.setBounds(720, 223, 150, 36);
		panel.add(btnLamMoi);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));

//		btnSua.setForeground(Color.WHITE);
//		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 20));
//		btnSua.setBackground(Color.RED);
//		btnSua.setBounds(727, 223, 106, 36);
//		panel.add(btnSua);
//		
//		JButton btnMoi = new FixButton("Làm mới");
//		btnMoi.setForeground(Color.WHITE);
//		btnMoi.setFont(new Font("Times New Roman", Font.BOLD, 20));
//		btnMoi.setBackground(Color.RED);
//		btnMoi.setBounds(535, 223, 123, 36);
//		panel.add(btnMoi);
//
//
//		JButton btnNewButton = new FixButton("Thêm ");
//		btnNewButton.setBackground(new Color(255, 0, 0));
//		btnNewButton.setForeground(new Color(255, 255, 255));
//		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
//		
//		btnNewButton.setBounds(374, 223, 106, 36);
//		panel.add(btnNewButton);
		
		JComboBox comboGT = new JComboBox();
		comboGT.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		comboGT.setSelectedIndex(0);
		comboGT.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboGT.setBounds(812, 68, 300, 31);
		panel.add(comboGT);
		
		JComboBox comboTrangThai = new JComboBox();
		
		comboTrangThai.setBounds(812, 165, 300, 31);
		panel.add(comboTrangThai);
		
		


	// Add the menu bar to the NORTH of the content pane
		JPanel pnDSP = new JPanel();
		pnDSP.setBounds(100, 350, 1200, 270);
		pnDSP.setLayout(null);

		JLabel lbDSPhong = new JLabel("Danh sách khách hàng");
		lbDSPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPhong.setBounds(10, 0, 200, 25);
		pnDSP.add(lbDSPhong);
		
//		
		String col[] = { "Mã KH","Họ tên", "Loại KH", "Giới tính","Địa chỉ", "SĐT", "CCCD", "Điẻm tích luỹ"};
		model = new DefaultTableModel(col, 0);

		table = new JTable(new DefaultTableModel(
				new Object[][] {
					{"HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34", "Nguy\u1EC5n V\u0103n A"},
					{"HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07", "Nguy\u1EC5n V\u0103n B"},
					{"HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34", "Nguy\u1EC5n V\u0103n A"},
					{"HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07", "Nguy\u1EC5n V\u0103n B"},
					{"HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34", "Nguy\u1EC5n V\u0103n A"},
					{"HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07", "Nguy\u1EC5n V\u0103n B"},
					{"HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34", "Nguy\u1EC5n V\u0103n A"},
					{"HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07", "Nguy\u1EC5n V\u0103n B"},
					{"HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34", "Nguy\u1EC5n V\u0103n A"},
					{"HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07", "Nguy\u1EC5n V\u0103n B"},
					{"HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34", "Nguy\u1EC5n V\u0103n A"},
					{"HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07", "Nguy\u1EC5n V\u0103n B"},
					{"HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34", "Nguy\u1EC5n V\u0103n A"},
					{"HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07", "Nguy\u1EC5n V\u0103n B"},
					{"HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34", "Nguy\u1EC5n V\u0103n A"},
					{"HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07", "Nguy\u1EC5n V\u0103n B"},
					{"HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34", "Nguy\u1EC5n V\u0103n A"},
					{"HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07", "Nguy\u1EC5n V\u0103n B"},
					{"HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34", "Nguy\u1EC5n V\u0103n A"},
					{"HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07", "Nguy\u1EC5n V\u0103n B"},
					{"HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34", "Nguy\u1EC5n V\u0103n A"},
					{"HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07", "Nguy\u1EC5n V\u0103n B"},
					
				},
				new String[] {
						"Mã KH","Họ tên", "Loại KH", "Giới tính","Địa chỉ", "SĐT", "CCCD", "Điẻm tích luỹ"
				}));
//
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
		scrollPane.setBounds(0, 20, 1400, 300);
		scrollPane.getHorizontalScrollBar();
		pnDSP.add(scrollPane);
		scrollPane.setViewportView(table);
		pnQLKH.add(pnDSP);
		
		JLabel lbBG = new JLabel();
		lbBG.setBounds(0,0,1400,700);
		lbBG.setIcon(new ImageIcon(Frm_KhachHang.class.getResource("/imgs/bg_chot1.png")));
		pnQLKH.add(lbBG);
		
		

	}


	public static void main(String[] args) {
	new Frm_KhachHang().setVisible(true);
	}
}
