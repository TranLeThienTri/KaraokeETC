package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entitys.KhachHang;
import entitys.LoaiKhachHang;
import dao.Dao_PhatSinhMa;

public class DanhSachKhachHang {
	ArrayList<KhachHang> list;

	public DanhSachKhachHang() {
		list = new ArrayList<KhachHang>();
	}
	
	public ArrayList<KhachHang> getDSKhachHang() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSKhachHang}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String sdt = rs.getString(5);
				String cccd = rs.getString(3);
				Boolean gioiTinh = rs.getBoolean(4);
				String malkh = rs.getString(7);
				String tenlkh;
				if(malkh.equals("NOR"))
					tenlkh = "Khách hàng thường";
				else tenlkh = "Khách hàng VIP";
				LoaiKhachHang maLoaiKH = new LoaiKhachHang(malkh,tenlkh);
				int dtl=rs.getInt(6);
				KhachHang kh = new KhachHang(maKH, tenKH, cccd, sdt, dtl, gioiTinh,maLoaiKH);
				list.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//
 public boolean themKhachHang(KhachHang kh) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call themKhachHang(?,?,?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			Dao_PhatSinhMa dao = new Dao_PhatSinhMa();
			String maKH = dao.getMaNVCuoi();
			myCall.setString(1, maKH);
			myCall.setString(2, kh.getHoTenKhachHang());
			myCall.setString(3, kh.getSoCCCD());
			myCall.setString(4, kh.getSoDienThoai());
			myCall.setInt(5, kh.getDiemTichLuy());
			myCall.setBoolean(6, kh.getGioiTinh());
			myCall.setString(7, kh.getLoaiKhachHang().getMaLoaiKhachHang());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean suaKhachHang(KhachHang kh) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call updateKH(?,?,?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
//			Dao_PhatSinhMa dao = new Dao_PhatSinhMa();
//			String maKH = dao.getMaNVCuoi();
			myCall.setString(1, kh.getMaKhachHang());
			myCall.setString(1, kh.getHoTenKhachHang());
			myCall.setString(2, kh.getSoCCCD());
			myCall.setString(3, kh.getSoDienThoai());
			myCall.setInt(4, kh.getDiemTichLuy());
			myCall.setBoolean(5, kh.getGioiTinh());
			myCall.setString(6, kh.getLoaiKhachHang().getMaLoaiKhachHang());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}


}
