package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.ChiTietHoaDon;
import entitys.ChucVu;
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.LoaiHoaDon;
import entitys.NhanVien;
import entitys.Phong;
import entitys.PhuThu;
import dao.DanhSachPhong;
import dao.DanhSachHoaDon;

public class DanhSachThuePhong {
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

	public HoaDonPhong getHDTheoMa(String ma) {
		HoaDonPhong hd = null;
		DanhSachHoaDon dao = new DanhSachHoaDon();
		DanhSachPhong daop = new DanhSachPhong();
		DanhSachKhachHang daokh = new DanhSachKhachHang();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getHDTheoMa(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				LocalDate ngaylap = LocalDate.parse(rs.getString(2));
				LocalTime giothue = LocalTime.parse(rs.getString(3));
				String maloaihd = rs.getString(9);
				LoaiHoaDon lhd = new LoaiHoaDon(maloaihd);
				String makh = rs.getString(7);
				String tenkh = dao.getTenKHTheoMa(makh);
				String sodt = dao.getSDTKHTheoMa(makh);
				int diem = daokh.getDTLTheoMa(makh);
				KhachHang kh = new KhachHang(makh, tenkh, sodt, diem);
				String manv = rs.getString(8);
				String tennv = dao.getTenNVTheoMa(manv);
				NhanVien nv = new NhanVien(manv, tennv);
				String map = rs.getString(10);
				Phong phong = new Phong(map);
				float tongtien = rs.getFloat(11);
				hd = new HoaDonPhong(maHD, phong, nv, kh, lhd, ngaylap, giothue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hd;
	}

	public ArrayList<ChiTietHoaDon> getCTHDTheoMa(String ma) {
		ArrayList<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
		DanhSachDichVu daov = new DanhSachDichVu();
		DanhSachPhuThu daopt = new DanhSachPhuThu();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getCTHDTheoMa(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String mahd = rs.getString(1);
				String madv = rs.getString(2);
				int sl = rs.getInt(3);
				String mapt = rs.getString(4);
				HoaDonPhong p = getHDTheoMa(mahd);
				DichVu dv = daov.getDVTheoMa(madv);
				PhuThu pt = daopt.getPTTheoMa(mapt);
				ChiTietHoaDon ct = new ChiTietHoaDon(p, dv, sl, pt);
				list.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean tinhTien(String mahd,float tien,LocalTime giotra) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call setTTHD(?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, mahd);
			myCall.setFloat(2, tien);
			myCall.setString(3,giotra.toString());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean congDTL(String makh) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call updateDTL(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, makh);
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean chuyenPhong(String mahd,String map) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call chuyenPhong(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, mahd);
			myCall.setString(2, map);
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean themDVTheoMa(String mahd,String madv,int sl) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call themDVTheoMa(?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, mahd);
			myCall.setString(2, madv);
			myCall.setInt(3, sl);
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean suaDVTheoMa(String mahd,String madv,int sl) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call suaDVTheoMa(?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, mahd);
			myCall.setString(2, madv);
			myCall.setInt(3, sl);
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean deleteDV(String ma) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call deleteDV(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
}
