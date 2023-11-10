
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
import javax.swing.JFormattedTextField;

public class Frm_QuanLyPhong extends JFrame {
	JPanel pnLoaiPhong, pnDSP;
	JLabel lbLoaiPhongTK, lbDSPhong, lbBGQLDP;
	FixButton btnLamMoi, btnHuyDatPhong, btnDatPhong, btnNhanPhong;
	FixButton2 btnTatCa, btnPhongThuong, btnPhongVip;
	private Date ngayHienTai;
	Panel pnQLDP;
	private int ngay, thang, nam;
	private JTable tableDSPhong, tableDSPhong1;
	private DefaultTableModel model, model1;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	public Panel getFrmQuanLyPhong() {
		return this.pnQLDP;
	}

	public Frm_QuanLyPhong() {
		setTitle("QUẢN LÝ Phòng");
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

		JPanel pnTTDDP = new JPanel();
		pnTTDDP.setBackground(new java.awt.Color(190, 157, 157,190));
		pnTTDDP.setBounds(100, 23, 1200, 239);
		pnQLDP.add(pnTTDDP);
		pnTTDDP.setLayout(null);

		JLabel lbSDT = new JLabel("Tình trạng phòng :");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(55, 37, 150, 25);
		pnTTDDP.add(lbSDT);

		JLabel lbTenKH = new JLabel("Sức chứa:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(55, 93, 140, 25);
		pnTTDDP.add(lbTenKH);
		
				JLabel lbTTDDP = new JLabel("Thông tin đơn đặt phòng");
				lbTTDDP.setBounds(10, -1, 190, 20);
				pnTTDDP.add(lbTTDDP);
				lbTTDDP.setFont(new Font("Tahoma", Font.BOLD, 15));
				lbTTDDP.setForeground(new Color(255, 255, 255));
				
				JLabel lbSDT_1 = new JLabel("Loại phòng:");
				lbSDT_1.setForeground(Color.WHITE);
				lbSDT_1.setFont(new Font("Tahoma", Font.BOLD, 15));
				lbSDT_1.setBounds(55, 154, 126, 25);
				pnTTDDP.add(lbSDT_1);
				
				JLabel lblGiPhng = new JLabel("Giá phòng:");
				lblGiPhng.setForeground(Color.WHITE);
				lblGiPhng.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblGiPhng.setBounds(676, 37, 126, 25);
				pnTTDDP.add(lblGiPhng);
				
				JLabel lblDinTch = new JLabel("Diện tích:");
				lblDinTch.setForeground(Color.WHITE);
				lblDinTch.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblDinTch.setBounds(676, 93, 126, 25);
				pnTTDDP.add(lblDinTch);
				
				JLabel lblMPhng = new JLabel("Mã phòng:");
				lblMPhng.setForeground(Color.WHITE);
				lblMPhng.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblMPhng.setBounds(676, 154, 126, 25);
				pnTTDDP.add(lblMPhng);
				
				FixButton fxbtnThm = new FixButton("Thêm");
				fxbtnThm.setIcon(new ImageIcon(Frm_QuanLyPhong.class.getResource("/imgs/icon_btn_them.png")));
				fxbtnThm.setFont(new Font("Tahoma", Font.BOLD, 15));
				fxbtnThm.setBounds(412, 199, 150, 30);
				fxbtnThm.setBackground(new java.awt.Color(153, 36, 36));
				pnTTDDP.add(fxbtnThm);
				
				FixButton fxbtnSa = new FixButton("Sửa");
				fxbtnSa.setIcon(new ImageIcon(Frm_QuanLyPhong.class.getResource("/imgs/icon_btn_sua.png")));
				fxbtnSa.setFont(new Font("Tahoma", Font.BOLD, 15));
				fxbtnSa.setBackground(new java.awt.Color(153, 36, 36));
				fxbtnSa.setBounds(657, 199, 150, 30);
				pnTTDDP.add(fxbtnSa);
				
				textField_6 = new JTextField();
				textField_6.setBounds(795, 37, 323, 30);
				pnTTDDP.add(textField_6);
				
				textField_7 = new JTextField();
				textField_7.setBounds(795, 93, 323, 30);
				pnTTDDP.add(textField_7);
				
				textField_8 = new JTextField();
				textField_8.setBounds(795, 151, 323, 28);
				pnTTDDP.add(textField_8);
				
				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] { "Phòng VIP", "Phòng Thường" }));
				comboBox.setSelectedIndex(0);
				comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
				comboBox.setBounds(215, 154, 323, 28);
				pnTTDDP.add(comboBox);
				
				JComboBox comboBox_1 = new JComboBox();
				comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Trống", "Đang Thuê","Đã Đặt" }));
				comboBox_1.setSelectedIndex(0);
				comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 15));
				comboBox_1.setBounds(215, 33, 323, 28);
				pnTTDDP.add(comboBox_1);
				
				JComboBox comboBox_2 = new JComboBox();
				comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "10", "15","20" }));
				comboBox_2.setSelectedIndex(0);
				comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 15));
				comboBox_2.setBounds(215, 93, 323, 28);
				pnTTDDP.add(comboBox_2);

		

		pnLoaiPhong = new JPanel();
		pnLoaiPhong.setBackground(Color.ORANGE);
		pnLoaiPhong.setBounds(393, 293, 650, 71);
		pnQLDP.add(pnLoaiPhong);
		pnLoaiPhong.setLayout(null);

		lbLoaiPhongTK = new JLabel("Loại phòng:");
		lbLoaiPhongTK.setForeground(Color.WHITE);
		lbLoaiPhongTK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiPhongTK.setBounds(32, 23, 108, 25);
		pnLoaiPhong.add(lbLoaiPhongTK);
		pnLoaiPhong.setBackground(new java.awt.Color(250, 154, 0));

		pnQLDP.add(pnLoaiPhong);

		btnTatCa = new FixButton2("Tất cả");
		btnTatCa.setBounds(163, 23, 100, 25);
		pnLoaiPhong.add(btnTatCa);

		btnPhongVip = new FixButton2("Phòng VIP");
		btnPhongVip.setBounds(292, 23, 120, 25);
		pnLoaiPhong.add(btnPhongVip);

		btnPhongThuong = new FixButton2("Phòng thường");
		btnPhongThuong.setBounds(447, 23, 150, 25);
		pnLoaiPhong.add(btnPhongThuong);
//
		String col1[] = { "Mã hóa đơn", "Mã phòng", "Tên khách hàng", "SĐT", "Ngày", "Thời gian", "Tên nhân viên" };
		model1 = new DefaultTableModel(col1, 0);

		tableDSPhong1 = new JTable(new DefaultTableModel(
				new Object[][] {
						{ "HD001", "MP001", "Tr\u1EA7n Qu\u1ED1c Huy", "0923456789", "04/11/2023", "12:34",
								"Nguy\u1EC5n V\u0103n A" },
						{ "HD002", "MP002", "L\u00EA Th\u1ECB An", "0972829123", "01/02/2023", "15:07",
								"Nguy\u1EC5n V\u0103n B" },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, },
				new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 ph\u00F2ng", "T\u00EAn kh\u00E1ch h\u00E0ng",
						"S\u0110T", "Ng\u00E0y", "Th\u1EDDi gian", "T\u00EAn nh\u00E2n vi\u00EAn" }));

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader1 = tableDSPhong1.getTableHeader();
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
		scrollPane1.setBounds(100, 400, 1200, 230);
		pnQLDP.add(scrollPane1);

		// add background ở cuối
		lbBGQLDP = new JLabel();
		lbBGQLDP.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDP.setBounds(0, 0, 1400, 700);
		pnQLDP.add(lbBGQLDP);
	}

	public static void main(String[] args) {
		new Frm_QuanLyPhong().setVisible(true);

	}
}