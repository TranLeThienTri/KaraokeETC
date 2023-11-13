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
import entitys.LoaiPhong;
import entitys.Phong;
import entitys.TinhTrangPhong;

public class Dao_PhatSinhMa {
	public String getMaKHCuoi() {
		String ma = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getMaKHTuDong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next())
				ma = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}

	// getmaNVCuoi
	public String getMaNVCuoi() {
		String ma = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getMaNVTuDong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next())
				ma = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}

	public String getMaDATuDong() {

		String ma = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getMaDATuDong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next())
				ma = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}

	public String getMaNUTuDong() {
		String ma = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getMaNUTuDong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next())
				ma = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}

	public String getMaPhongCuoi() {
		String ma = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getMaPhongTuDong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next())
				ma = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}

	public String getMaHDCuoi() {
		String ma = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getMaHDTuDong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next())
				ma = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}
}
