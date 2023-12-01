package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.ChiTietHoaDon;
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.NhanVien;
import entitys.Phong;
import entitys.PhuThu;

public class DanhSachChiTietHoaDon {
	public boolean luuTTChuyenPhong(ChiTietHoaDon CTHD) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call luuTTChuyenPhong(?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, CTHD.getMaHoaDonPhong().getMaHoaDon());
			myCall.setString(2, CTHD.getMaPhong().getMaPhong());
			myCall.setString(3, CTHD.getGioChuyen().toString());
			myCall.setString(4, CTHD.getGioVao().toString());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public ChiTietHoaDon getCTHDTheoMaPhong(String ma) {
		ChiTietHoaDon ct = null;
		DanhSachThuePhong tp = new DanhSachThuePhong();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getCTHDTheoMaPhong(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String mahd = rs.getString(1);
				String map = rs.getString(2);
				LocalTime gioChuyen = LocalTime.parse(rs.getString(3));
				LocalTime gioVao = LocalTime.parse(rs.getString(4));
				HoaDonPhong hd = tp.getHDTheoMa(mahd);
				DanhSachPhong dsP = new DanhSachPhong();
				Phong p = dsP.getPhongTheoMa(map);
				ct = new ChiTietHoaDon(hd, p, gioChuyen, gioVao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ct;
	}
	public ArrayList<ChiTietHoaDon> getCTHDPTheoMa(String ma) {
		ArrayList<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
		DanhSachThuePhong tp = new DanhSachThuePhong();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getCTHDPTheoMa(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String mahd = rs.getString(1);
				String map = rs.getString(2);
				LocalTime gioChuyen = LocalTime.parse(rs.getString(3));
				LocalTime gioVao = LocalTime.parse(rs.getString(4));
				HoaDonPhong hd = tp.getHDTheoMa(mahd);
				DanhSachPhong dsP = new DanhSachPhong();
				Phong p = dsP.getPhongTheoMa(map);
				ChiTietHoaDon ct = new ChiTietHoaDon(hd, p, gioChuyen, gioVao);
				list.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
