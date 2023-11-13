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
import java.util.ArrayList;
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

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DanhSachDichVu;
import dao.Dao_PhatSinhMa;
import entitys.DichVu;
import entitys.LoaiDichVu;
import entitys.LoaiPhong;
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

import org.apache.poi.hssf.record.cf.BorderFormatting;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

public class Frm_ThemDichVu extends JFrame implements MouseListener,ActionListener {
	JPanel pnDSDichVu, pnDV;
	JLabel lbDSDichVu, lbBGQLDV, lbDV, lbLoaiDichVu, lbTenDV, lbSoLuong, lbGiamSL, lbTangSL;
	JComboBox comboTDV, comboLDV;
	JTextField txtSoLuongTon;
	Panel pnTDV;
	FixButton btnHuyDV, btnXacNhan, btnLamMoi,btnThemDV;
	FixButton2 btnQuayLai;
	private JTable tableDSDichVu;
	private DefaultTableModel model;

	private JLabel lbTongTien;
	private JLabel lbPhong;
	private JLabel lbTenKH;
	private JLabel lbGioVao;
	DanhSachDichVu dsDV;

	public Panel getFrmThemDichVu() {
		return this.pnTDV;
	}

	public Frm_ThemDichVu() {
		setTitle("THÊM DỊCH VỤ");
		setSize(1400, 670);
		setResizable(true);
		setLocationRelativeTo(null);
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
		

		txtSoLuongTon = new JTextField("3");
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

		lbTongTien = new JLabel("Tổng tiền:");
		lbTongTien.setBackground(Color.WHITE);
		lbTongTien.setForeground(Color.WHITE);
		lbTongTien.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTongTien.setBounds(14, 347, 200, 25);
		pnDV.add(lbTongTien);

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
		String col[] = { "STT","Loại Dịch Vụ", "Tên Dịch Vụ", "Số Lượng ", "Giá Bán","Thành tiền" };
		model = new DefaultTableModel(col, 0);
		tableDSDichVu = new JTable(model);

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
		pnTTPhong.setBackground(new java.awt.Color(255, 255, 255,80));
		pnTTPhong.setBounds(562, 0, 780, 75);
		pnTTPhong.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin ph\u00F2ng", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
		
		
		pnTDV.add(pnTTPhong);
		pnTTPhong.setLayout(null);
		
		lbPhong = new JLabel("Phòng: ");
		lbPhong.setForeground(Color.WHITE);
		lbPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbPhong.setBounds(10, 10, 100, 20);
		pnTTPhong.add(lbPhong);
		
		lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(10, 52, 150, 20);
		pnTTPhong.add(lbTenKH);
		
		lbGioVao = new JLabel("Giờ vào:");
		lbGioVao.setForeground(Color.WHITE);
		lbGioVao.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioVao.setBounds(591, 52, 150, 20);
		pnTTPhong.add(lbGioVao);
		

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDV.setBounds(0, 0, 1400, 670);
		pnTDV.add(lbBGQLDV);
		
		lbTangSL.addMouseListener(this);
		lbGiamSL.addMouseListener(this);
		btnQuayLai.addActionListener(this);
		comboLDV.addActionListener(this);
		btnThemDV.addActionListener(this);
		
		ConnectDB.getInstance().connect();
		dsDV = new DanhSachDichVu();
		phanLoaiCombobox();

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == lbTangSL)
			ktTangSL();
		if (o == lbGiamSL)
			ktGiamSL();
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
	
	public static void main(String[] args) {
		new Frm_ThemDichVu().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == comboLDV) {
			phanLoaiCombobox();
		}else if(o == btnThemDV) {
			//upTable2();
			themDVvaoTable();
		}
//		if(o == btnQuayLai ) {
//			dispose();
//		}
	}
	
	public void themDVvaoTable() {
		Object[] obj = new Object[5];
			
			String loai = (String) comboLDV.getSelectedItem();
			String tendv = (String) comboTDV.getSelectedItem();
			int sl = Integer.parseInt(txtSoLuongTon.getText());
			double giaban = 0 ;
			Frm_QuanLyDichVu qldv = new Frm_QuanLyDichVu();
			
			double thanhTien = sl*giaban;
				obj[0] = loai;
				obj[1] = tendv;
				obj[2] = sl;
				obj[3] = giaban;
				obj[4] =giaban *sl;
				model.addRow(obj);
	
	}
	
//	public boolean themDV() {
//		Object[] obj = new Object[6];
//	
//			Dao_PhatSinhMa matp1 = new Dao_PhatSinhMa();
//			String mada = matp1.getMaDATuDong();
//			String manu = matp1.getMaNUTuDong();
//			String loai = (String) comboLDV.getSelectedItem();
//			String tendv = (String) comboTDV.getSelectedItem();
//			LoaiDichVu ldv;
//			String ma;
//			if (loai.equals("Thực phẩm")) {
//				ma = mada;
//				ldv = new LoaiDichVu("FOOD", "Thực phẩm");
//
//			} else {
//				ldv = new LoaiDichVu("WATER", "Nước uống");
//				ma = manu;
//			}
//			int sl = Integer.parseInt(txtSoLuongTon.getText());
//			
//			DichVu dv = new DichVu(ma, tendv, ldv, sl, giaban);
//			double giaban = dv.getDonGia();
//			double thanhTien = sl*giaban;
//
//			if (!dsDV.themDichVu(dv)) {
//				JOptionPane.showMessageDialog(this, "Thêm thành công");
//				obj[0] = ma;
//				obj[1] = tendv;
//				obj[2] = ldv.getTenLoaiDichVu();
//				obj[3] = slt;
//				obj[4] = giaban;
//				obj[5] = thanhTien;
//				model.addRow(obj);
//			
//				return true;
//			}
//		
//		return false;
//	}
	
	public void upTable2() {
		
		Dao_PhatSinhMa matp1 = new Dao_PhatSinhMa();
		String mada = matp1.getMaDATuDong();
		String manu = matp1.getMaNUTuDong();
		String loai = (String) comboLDV.getSelectedItem();
		String tendv = (String) comboTDV.getSelectedItem();
		LoaiDichVu ldv;
		String ma;
		if (loai.equals("Thực phẩm")) {
			ma = mada;
			ldv = new LoaiDichVu("FOOD", "Thực phẩm");

		} else {
			ldv = new LoaiDichVu("WATER", "Nước uống");
			ma = manu;
		}
		int sl = Integer.parseInt(txtSoLuongTon.getText());
		
		
		int i = 0;
		ArrayList<DichVu> list = dsDV.getDSDichVu();
		for (DichVu dv : list) {
			if (dv.getMaDichVu().equals(ma)) {
				Object[] obj = new Object[6];
				obj[0] = ma;
				obj[1] = loai;
				obj[2] = tendv;	
				obj[3] = sl;
				obj[4] = dv.getDonGia();
				obj[5] = dv.getDonGia()*sl;
				model.addRow(obj);
			}
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
		int tang =  Integer.parseInt(txtSoLuongTon.getText());
		int value = tang +1 ;
		txtSoLuongTon.setText(String.valueOf(value));	
	}
	
	public void ktGiamSL() {
		int giam =  Integer.parseInt(txtSoLuongTon.getText());
		int value = Math.max(0, giam-1) ;
		txtSoLuongTon.setText(String.valueOf(value));	
	}
	
}
