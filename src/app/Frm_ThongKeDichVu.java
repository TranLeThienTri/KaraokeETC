package app;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.mindfusion.scheduling.Cursor;
import com.toedter.calendar.JDateChooser;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frm_ThongKeDichVu extends JFrame{

	private JFrame frame;
	private LocalDate now;
	private int ngay;
	private int thang;
	private int nam;
	private Date dNow;
	private JDateChooser dateChooserThongKeNgayBatDau;
	private JDateChooser dateChooserThongKeNgayKetThuc;
	private JTable table;
	private Panel panel_tong;

	public Panel getFrmThongKeDichVu() {
		return this.panel_tong;
	}
	public static void main(String[] args) {
		new Frm_ThongKeDichVu().setVisible(true);

	}

	/**
	 * Create the application.
	 */
	public Frm_ThongKeDichVu() {
		setTitle("THỐNG KÊ DỊCH VỤ");
		setSize(1400, 670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);

		gui();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void gui() {
		getContentPane().setLayout(null);
		panel_tong = new Panel();
		panel_tong.setBounds(0, 0, 1400, 670);
		getContentPane().add(panel_tong);
		panel_tong.setLayout(null);


		JPanel panel_tkdv = new JPanel();
		panel_tkdv.setBackground(new Color(0, 0, 0));
		panel_tkdv.setBounds(536, 10, 286, 41);
		panel_tong.add(panel_tkdv);
		panel_tkdv.setLayout(null);

		JLabel lbltknv = new JLabel("THỐNG KÊ DỊCH VỤ");
		lbltknv.setForeground(new Color(255, 255, 255));
		lbltknv.setBounds(52, 10, 223, 20);
		panel_tkdv.add(lbltknv);
		lbltknv.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lbltgtk = new JLabel("Thời gian thống kê:");
		lbltgtk.setForeground(new Color(255, 255, 255));
		lbltgtk.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltgtk.setBounds(394, 70, 165, 25);
		panel_tong.add(lbltgtk);

		JPanel panel_ngay = new JPanel();
		panel_ngay.setBackground(new Color(190, 157, 157, 190));
		panel_ngay.setBounds(10, 112, 427, 228);
		panel_tong.add(panel_ngay);
		panel_ngay.setLayout(null);

		JLabel lblnbd = new JLabel("Ngày bắt đầu:");
		lblnbd.setForeground(new Color(255, 255, 255));
		lblnbd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnbd.setBounds(10, 22, 128, 38);
		panel_ngay.add(lblnbd);
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue()-1;
		nam = now.getYear()-1900;

		dNow = new Date(nam,thang,ngay);

		dateChooserThongKeNgayBatDau = new JDateChooser();
		dateChooserThongKeNgayBatDau.getCalendarButton().setIcon(new ImageIcon(Frm_ThongKeDichVu.class.getResource("/imgs/calendar.png")));
		dateChooserThongKeNgayBatDau.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooserThongKeNgayBatDau.setDateFormatString("dd/MM/yyyy");

		dateChooserThongKeNgayBatDau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooserThongKeNgayBatDau.getCalendarButton().setPreferredSize(new Dimension(40, 30));
		dateChooserThongKeNgayBatDau.setIcon(new ImageIcon(Frm_ThongKeDichVu.class.getResource("/imgs/calendar.png")));

		dateChooserThongKeNgayBatDau.setBounds(149, 22, 226, 38);
		dateChooserThongKeNgayBatDau.setDate(dNow);
		panel_ngay.add(dateChooserThongKeNgayBatDau);

		JLabel lblnkt = new JLabel("Ngày kết thúc:");
		lblnkt.setForeground(new Color(255, 255, 255));
		lblnkt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnkt.setBounds(10, 80, 128, 38);
		panel_ngay.add(lblnkt);
		dateChooserThongKeNgayKetThuc = new JDateChooser();
		dateChooserThongKeNgayKetThuc.setDateFormatString("dd/MM/yyyy");

		dateChooserThongKeNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooserThongKeNgayKetThuc.getCalendarButton().setPreferredSize(new Dimension(40, 30));
		dateChooserThongKeNgayKetThuc.setIcon(new ImageIcon(Frm_ThongKeDichVu.class.getResource("/imgs/calendar.png")));

		dateChooserThongKeNgayKetThuc.setBounds(149, 80, 226, 38);
		dateChooserThongKeNgayKetThuc.setDate(dNow);
		panel_ngay.add(dateChooserThongKeNgayKetThuc);

		JButton btnThongKe = new FixButton("Thống kê");
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThongKe.setBounds(84, 128, 253, 42);
		btnThongKe.setIcon(new ImageIcon(Frm_ThongKeDichVu.class.getResource("/imgs/icon_thongke.png")));
		panel_ngay.add(btnThongKe);

		JButton btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBounds(84, 176, 253, 42);
		btnLamMoi.setIcon(new ImageIcon(Frm_ThongKeDichVu.class.getResource("/imgs/icon_lammoi.png")));
		panel_ngay.add(btnLamMoi);

		JPanel panel_thongke1 = new JPanel();
		panel_thongke1.setBackground(new Color(190, 157, 157, 190));
		panel_thongke1.setBounds(447, 112, 471, 228);
		panel_tong.add(panel_thongke1);
		panel_thongke1.setLayout(null);

		JLabel lbliconthongke1 = new JLabel("");
		lbliconthongke1.setForeground(new Color(255, 255, 255));
		lbliconthongke1.setBounds(205, 25, 64, 64);
		panel_thongke1.add(lbliconthongke1);
		lbliconthongke1.setIcon(new ImageIcon(Frm_ThongKeDichVu.class.getResource("/imgs/icon_tong.png")));

		JLabel lblthongke1 = new JLabel("Tổng các dịch vụ đã bán: ");
		lblthongke1.setForeground(new Color(255, 255, 255));
		lblthongke1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblthongke1.setBounds(149, 114, 199, 33);
		panel_thongke1.add(lblthongke1);

		JPanel panel_thongke2 = new JPanel();
		panel_thongke2.setBackground(new Color(190, 157, 157, 190));
		panel_thongke2.setBounds(928, 112, 448, 228);
		panel_tong.add(panel_thongke2);
		panel_thongke2.setLayout(null);

		JLabel lbliconthongke2 = new JLabel("");
		lbliconthongke2.setBounds(205, 25, 64, 64);
		panel_thongke2.add(lbliconthongke2);
		lbliconthongke2.setIcon(new ImageIcon(Frm_ThongKeDichVu.class.getResource("/imgs/icon_funnel.png")));

		JLabel lblthongke2 = new JLabel("Tổng tiền các dịch vụ:");
		lblthongke2.setForeground(new Color(255, 255, 255));
		lblthongke2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblthongke2.setBounds(148, 114, 265, 28);
		panel_thongke2.add(lblthongke2);

		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 DV", "T\u00EAn DV", "Lo\u1EA1i DV", "Gi\u00E1 b\u00E1n", "S\u1ED1 l\u01B0\u1EE3ng"
			}
		));
		JTableHeader tbHeader = table.getTableHeader();
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		table.setBackground(Color.white);
		table.setFont(new Font("SansSerif", Font.PLAIN, 14));
		table.setSelectionBackground(new Color(158, 207, 0));
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setRowHeight(30);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 350, 1400, 267);
		scrollPane.getHorizontalScrollBar();
		scrollPane.setViewportView(table);
		panel_tong.add(scrollPane);
		JLabel lblbackground = new JLabel("");
		lblbackground.setBounds(0, 0, 1400, 700);
		panel_tong.add(lblbackground);
		lblbackground.setIcon(new ImageIcon(Frm_ThongKeDichVu.class.getResource("/imgs/bg_chot1.png")));
		
	}
}