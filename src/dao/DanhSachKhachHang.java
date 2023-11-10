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
				LoaiKhachHang maLoaiKH = new LoaiKhachHang(malkh);
				int dtl=rs.getInt(6);
				KhachHang kh = new KhachHang(maKH, tenKH, cccd, sdt, false, dtl);
				list.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//
// boolean themKhachHang(KhachHang kh) {
//		boolean b = true;
//		try {
//			C.getInstance();
//			Connection con = Database.getConnection();
//			String sql = "{call addKH(?,?,?,?,?,?,?)}";
//			CallableStatement myCall = con.prepareCall(sql);
//			String maKH = getMaNVCuoi();
//			myCall.setString(1, maKH);
//			myCall.setString(2, kh.getHoTenKhachHang());
//			myCall.setString(3, kh.getSoCCCD());
//			myCall.setBoolean(4, kh.getGioiTinh());
//			myCall.setString(5, kh.getSoDienThoai());
//			myCall.setInt(6, kh.getDiemTichLuy());
//			myCall.setString(7, kh.getLoaiKhachHang().getMaLoaiKhachHang());
//			b = myCall.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return b;
//	}
//
////	public boolean suaKhachHang(KhachHang kh) {
////		boolean b = true;
////		try {
////			Database.getInstance();
////			Connection con = Database.getConnection();
////			String sql = "{call updateKH(?,?,?,?,?,?)}";
////			CallableStatement myCall = con.prepareCall(sql);
////			myCall.setString(1, kh.getMaKhachHang());
////			myCall.setString(2, kh.getHoTenKhachHang());
////			myCall.setString(3, kh.getSoCCCD());
////			myCall.setString(4, kh.getSoDienThoai());
////			//myCall.setString(5, kh.getDiaChi());
////			myCall.setBoolean(6, kh.getGioiTinh());
////			b = myCall.execute();
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////		return b;
////	}
//	public String getMaNVCuoi()
//	{
//		String ma = "";
//		try {
//			Database.getInstance();
//			Connection con = Database.getConnection();
//			String sql1 = "{call getMaKHCuoi}";
//			CallableStatement myCall1 = con.prepareCall(sql1);
//			String manv = myCall1.getString(0);
//			String so = "";
//			for(int i = 2;i<=manv.length();i++) {
//				so += manv.charAt(i);
//			}
//			int ma1 = Integer.parseInt(so) + 1;
//			if(ma1 < 10)
//				ma = "KH00" + ma1;
//			else if(ma1 <100)
//				ma = "KH0" + ma1;
//			else 
//				ma = "KH" + ma1;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ma;
//	}
	
	//quá mệt mỏi

}
