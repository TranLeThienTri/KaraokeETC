package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entitys.HoaDonPhong;
import entitys.PhuThu;


public class DanhSachPhuThu {

	public PhuThu getPTTheoMa(String ma) {
		PhuThu pt = new PhuThu();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getPTTheoMa(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maPT = rs.getString(1);
				String lydo = rs.getString(2);
				float tien = rs.getFloat(3);
				pt = new PhuThu(maPT, lydo, tien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pt;
	}
	public String getPTTheoMaHD(String ma) {
		String maPT = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getPTTheoMaHD(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				maPT = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maPT;
	}
	public boolean themPTTheoMa(String ma) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call themPTTheoMa(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
}
