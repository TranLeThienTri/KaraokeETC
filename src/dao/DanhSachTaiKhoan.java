package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.NhanVien;
import entitys.TaiKhoan;

public class DanhSachTaiKhoan {
	ArrayList<TaiKhoan> dsTK = new ArrayList<TaiKhoan>();
	
	public ArrayList<TaiKhoan> getDSTaiKhoan() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call [getNVTheoMa]}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String mkNV = rs.getString(2);
				TaiKhoan tk = new TaiKhoan(maNV, mkNV);
				dsTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	
	
	public TaiKhoan getTaiKhoanTheoMa(String ma) {
		connectDB.ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		TaiKhoan tk = null;
		
		try {
			String sql = "{call getTaiKhoanTheoMa(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {	
				String maNV = rs.getString(1);
				String mkNV = rs.getString(2);
				tk = new TaiKhoan(maNV, mkNV);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tk;
	}
	public boolean updatePassword(String ma,String newPass) {
		connectDB.ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		boolean flag = true;
		try {
			String sql = "{call updatePassword(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			myCall.setString(2, newPass);
			flag = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
