package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import connectDB.ConnectDB;
import entitys.ChucVu;
import entitys.KhachHang;
import entitys.NhanVien;

public class ThuePhong {
	public KhachHang ktraKHTheoSDt(String sdt) {
		KhachHang kh =null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call kiemTraKhTheoSDT(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, sdt);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				kh = new KhachHang(ma, ten);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}
}
