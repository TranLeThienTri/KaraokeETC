package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import connectDB.ConnectDB;
import entitys.KhachHang;
import entitys.LoaiKhachHang;
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.NhanVien;
import dao.DanhSachKhachHang;

public class DanhSachHoaDon {
	public ArrayList<KhachHang> getDSKhachHangTheoNgay(Date ngaybd, Date ngaykt) {
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSKhachHangTheoNgay(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setDate(1, ngaybd);
			myCall.setDate(2, ngaykt);
			ResultSet rs = myCall.executeQuery();
			ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
			while (rs.next()) {
				String maKH = rs.getString(1);
				KhachHang kh = new KhachHang(maKH);
				dskh.add(kh);
			}
			list = getDSKHTheoDSMaKhachHang(dskh);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<KhachHang> getDSKHTheoDSMaKhachHang(ArrayList<KhachHang> dsMa) {
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		DanhSachKhachHang dao = new DanhSachKhachHang();
		for(KhachHang kh : dsMa) {
			KhachHang khachhang = dao.getKHTheoMa(kh.getMaKhachHang());
			if(!list.contains(khachhang))
				list.add(khachhang);
		}
		return list;
	}
	public int tongSoKHTheoNgay(Date ngaybd, Date ngaykt) {
		int tong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getTongSoKHTheoNgay(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setDate(1, ngaybd);
			myCall.setDate(2, ngaykt);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				tong = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}
	public int getSoHDTheoMaTheoNgay(String ma,Date ngaybd, Date ngaykt) {
		int tong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getSoHDTheoMaTheoNgay(?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			myCall.setDate(2, ngaybd);
			myCall.setDate(3, ngaykt);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				tong = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}
	public ArrayList<NhanVien> getDSNVTheoNgay(Date ngaybd, Date ngaykt) {
		ArrayList<NhanVien> list = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSNVTheoNgay(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setDate(1, ngaybd);
			myCall.setDate(2, ngaykt);
			ResultSet rs = myCall.executeQuery();
			ArrayList<NhanVien> dskh = new ArrayList<NhanVien>();
			while (rs.next()) {
				String maNV = rs.getString(1);
				NhanVien nv = new NhanVien(maNV);
				dskh.add(nv);
			}
			list = getDSNVTheoDSMaNV(dskh);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<NhanVien> getDSNVTheoDSMaNV(ArrayList<NhanVien> dsMa) {
		ArrayList<NhanVien> list = new ArrayList<NhanVien>();
		DanhSachNhanVien dao = new DanhSachNhanVien();
		for(NhanVien kh : dsMa) {
			NhanVien nhanvien = dao.getNhanVienTheoMa(kh.getMaNhanVien());
			if(!list.contains(nhanvien))
				list.add(nhanvien);
		}
		return list;
	}
	public int tongSoNVTheoNgay(Date ngaybd, Date ngaykt) {
		int tong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getTongSoKHTheoNgay(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setDate(1, ngaybd);
			myCall.setDate(2, ngaykt);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				tong = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}
}
