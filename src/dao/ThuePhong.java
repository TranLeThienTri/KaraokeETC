package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import connectDB.ConnectDB;
import entitys.ChucVu;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.NhanVien;
import dao.DanhSachPhong;

public class ThuePhong {
	public KhachHang ktraKHTheoSDt(String sdt) {
		KhachHang kh = null;
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

	public boolean themHDThue(HoaDonPhong p) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call themHDThuePhong(?,?,?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, p.getMaHoaDon());
			myCall.setString(2, p.getNgayLapHoaDon().toString());
			myCall.setString(3, p.getGioBatDauThue().toString());
			myCall.setString(4, p.getMaKhachHang().getMaKhachHang());
			myCall.setString(5, p.getMaNhanVien().getMaNhanVien());
			myCall.setString(6, p.getMaLoaiHoaDon().getMaLoaiHoaDon());
			myCall.setString(7, p.getPhong().getMaPhong());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean setTTPhongTheoMa(String ma, String tinhtrang) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call setTinhTrangPhongTheoMa(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			myCall.setString(2, tinhtrang);
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
}
