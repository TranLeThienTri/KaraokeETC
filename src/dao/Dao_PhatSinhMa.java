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
public class Dao_PhatSinhMa {
	public String getMaNVCuoi()
	{
		String ma = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getMaKHTuDong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while(rs.next())
				ma = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}
}