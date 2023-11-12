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
import entitys.Phong;
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
	
	public String getMaDATuDong()
	{
		String ma = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getMaDATuDong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while(rs.next())
				ma = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}
	
	public String getMaNUTuDong()
	{
		String ma = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getMaNUTuDong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while(rs.next())
				ma = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}
	public ArrayList<Phong> getDSPhong() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSPhong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString(1);
				String malPhong = rs.getString(2);
				int sucChua = rs.getInt(3);
				Float giaPhong = rs.getFloat(4);
				String matinhTrang = rs.getString(5);
				float dienTich = rs.getFloat(6);
				Loai maLoaiPhong = new LoaiPhong(malPhong);
				TinhTrangPhong maTinhTrang = new TinhTrangPhong(matinhTrang);
				Phong phong = new Phong(maPhong, maLoaiPhong, sucChua, giaPhong, maTinhTrang, dienTich);
				list.add(phong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
