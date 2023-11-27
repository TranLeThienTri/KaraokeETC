package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import connectDB.ConnectDB;
import entitys.KhachHang;
import entitys.LoaiHoaDon;
import entitys.LoaiKhachHang;
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.NhanVien;
import entitys.Phong;
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
		for (KhachHang kh : dsMa) {
			KhachHang khachhang = dao.getKHTheoMa(kh.getMaKhachHang());
			if (!list.contains(khachhang))
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

	public int getSoHDTheoMaTheoNgay(String ma, Date ngaybd, Date ngaykt) {
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
		for (NhanVien kh : dsMa) {
			NhanVien nhanvien = dao.getNhanVienTheoMa(kh.getMaNhanVien());
			if (!list.contains(nhanvien))
				list.add(nhanvien);
		}
		return list;
	}

	public int tongSoNVTheoNgay(Date ngaybd, Date ngaykt) {
		int tong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getTongSoNVTheoNgay(?,?)}";
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

	public ArrayList<HoaDonPhong> getDSHDTheoNgay(Date ngaybd, Date ngaykt) {
		ArrayList<HoaDonPhong> list = new ArrayList<HoaDonPhong>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSHDTheoNgay(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setDate(1, ngaybd);
			myCall.setDate(2, ngaykt);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				LocalDate ngaylap = LocalDate.parse(rs.getString(2));
				LocalTime giothue = LocalTime.parse(rs.getString(3));
				LocalTime giotra = LocalTime.parse(rs.getString(4));
				String maloaihd = rs.getString(9);
				LoaiHoaDon lhd = new LoaiHoaDon(maloaihd);
				String makh = rs.getString(7);
				String tenkh = getTenKHTheoMa(makh);
				KhachHang kh = new KhachHang(makh, tenkh);
				String manv = rs.getString(8);
				String tennv = getTenNVTheoMa(manv);
				NhanVien nv = new NhanVien(manv, tennv);
				String map = rs.getString(10);
				Phong phong = new Phong(map);
				float tongtien = rs.getFloat(11);
				HoaDonPhong hd = new HoaDonPhong(maHD, phong, nv, kh, lhd, ngaylap, giothue, giotra, ngaylap, giotra,
						tongtien);
				list.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getTenNVTheoMa(String ma) {
		String ten = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getTenNVTheoMa(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				ten = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ten;
	}

	public String getTenKHTheoMa(String ma) {
		String ten = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getTenKHTheoMa(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				ten = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ten;
	}

	public String getSDTKHTheoMa(String ma) {
		String ten = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getSDTKHTheoMa(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				ten = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ten;
	}

	public float tongTienHDTheoNgay(Date ngaybd, Date ngaykt) {
		float tong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getTongTienHD(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setDate(1, ngaybd);
			myCall.setDate(2, ngaykt);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				tong = rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}

	public ArrayList<DichVu> getDSDVTheoNgay(Date ngaybd, Date ngaykt) {
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSDVTheoNgay(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setDate(1, ngaybd);
			myCall.setDate(2, ngaykt);
			ResultSet rs = myCall.executeQuery();
			ArrayList<DichVu> dsdv = new ArrayList<DichVu>();
			while (rs.next()) {
				String madv = rs.getString(1);
				DichVu dv = new DichVu(madv);
				dsdv.add(dv);
			}
			list = getDSDVTheoDSMaDV(dsdv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<DichVu> getDSDVTheoDSMaDV(ArrayList<DichVu> dsMa) {
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		DanhSachDichVu dao = new DanhSachDichVu();
		for (DichVu dv : dsMa) {
			DichVu dichvu = dao.getDVTheoMa(dv.getMaDichVu());
			list.add(dichvu);
		}
		return list;
	}

	public int tongSoTheoNgay(Date ngaybd, Date ngaykt) {
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

	public int getSoDVTheoMaTheoNgay(String ma, Date ngayBatDau, Date ngayKetThuc) {
		int tong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getSoDVTheoMaTheoNgay(?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			myCall.setDate(2, ngayBatDau);
			myCall.setDate(3, ngayKetThuc);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				tong = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}

	public ArrayList<HoaDonPhong> getDSHD() {
		ArrayList<HoaDonPhong> list = new ArrayList<HoaDonPhong>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSHD}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {

				String maHD = rs.getString(1);
				LocalDate ngaylap = LocalDate.parse(rs.getString(2));
				LocalTime giothue = LocalTime.parse(rs.getString(3));
				String maloaihd = rs.getString(9);
				LoaiHoaDon lhd = new LoaiHoaDon(maloaihd);
				String makh = rs.getString(7);
				String tenkh = getTenKHTheoMa(makh);
				String sodt = getSDTKHTheoMa(makh);
				KhachHang kh = new KhachHang(makh, tenkh, sodt);
				String manv = rs.getString(8);
				String tennv = getTenNVTheoMa(manv);
				NhanVien nv = new NhanVien(manv, tennv);
				String map = rs.getString(10);
				Phong phong = new Phong(map);
				float tongtien = rs.getFloat(11);
				HoaDonPhong hd = new HoaDonPhong(maHD, phong, nv, kh, lhd, ngaylap, giothue);
				list.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public DichVu getDVDuocDatNhieuNhat(Date ngaybd, Date ngaykt) {
		DichVu dv = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getSoLanDVDat(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setDate(1, ngaybd);
			myCall.setDate(2, ngaykt);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String madv = rs.getString(1);
				String tendv = rs.getString(3);
				dv = new DichVu(madv, tendv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dv;
	}

	public ArrayList<HoaDonPhong> getDSHDThue() {
		ArrayList<HoaDonPhong> list = new ArrayList<HoaDonPhong>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSHDThue(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, "HDT");
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				LocalDate ngaylap = LocalDate.parse(rs.getString(2));
				LocalTime giothue = LocalTime.parse(rs.getString(3));
				String maloaihd = rs.getString(9);
				LoaiHoaDon lhd = new LoaiHoaDon(maloaihd);
				String makh = rs.getString(7);
				String tenkh = getTenKHTheoMa(makh);
				String sodt = getSDTKHTheoMa(makh);
				KhachHang kh = new KhachHang(makh, tenkh, sodt);
				String manv = rs.getString(8);
				String tennv = getTenNVTheoMa(manv);
				NhanVien nv = new NhanVien(manv, tennv);
				String map = rs.getString(10);
				Phong phong = new Phong(map);
				float tongtien = rs.getFloat(11);
				HoaDonPhong hd = new HoaDonPhong(maHD, phong, nv, kh, lhd, ngaylap, giothue);
				list.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getLanDatDVNNTheoMa(Date ngayBatDau, Date ngayKetThuc) {
		int tong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getSoLanDVDat(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setDate(1, ngayBatDau);
			myCall.setDate(2, ngayKetThuc);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				tong = rs.getInt(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}

	public ArrayList<HoaDonPhong> getDSHDTheoMaKH(String ma) {
		ArrayList<HoaDonPhong> list = new ArrayList<HoaDonPhong>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSHDTheoMaKH(?)}";
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
				String tenkh = getTenKHTheoMa(makh);
				String sodt = getSDTKHTheoMa(makh);
				KhachHang kh = new KhachHang(makh, tenkh, sodt);
				String manv = rs.getString(8);
				String tennv = getTenNVTheoMa(manv);
				NhanVien nv = new NhanVien(manv, tennv);
				String map = rs.getString(10);
				Phong phong = new Phong(map);
				float tongtien = rs.getFloat(11);
				HoaDonPhong hd = new HoaDonPhong(maHD, phong, nv, kh, lhd, ngaylap, giothue);
				list.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// lấy danh sách hoá đơn đặt
	public ArrayList<HoaDonPhong> getDSHDDTheoMaKH(String ma) {
		ArrayList<HoaDonPhong> list = new ArrayList<HoaDonPhong>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSHDDTheoMaKH(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maPhong = rs.getString(10);
				String maKhachhang = rs.getString(7);
				String gioDattt = rs.getString(6);
				String[] gioDatt = gioDattt.split("\\.");
				String gioDat = gioDatt[0];
				String maNhanVien = rs.getString(8);
				String ngayDat = rs.getString(5);
//				String ngayLapHoaDon = rs.getString(2);
				Phong p = new Phong(maPhong);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
				// Chuyển đổi chuỗi thành LocalTime
				LocalTime localTime = LocalTime.parse(gioDat, formatter);

				DanhSachKhachHang dskh = new DanhSachKhachHang();
				DanhSachNhanVien dsnv = new DanhSachNhanVien();
				NhanVien nv = dsnv.getNhanVienTheoMa(maNhanVien);
				KhachHang kh = dskh.getKHTheoMa(maKhachhang);

				HoaDonPhong hd = new HoaDonPhong(maHD, p, nv, kh, new LoaiHoaDon("HDD"), null,
						LocalDate.parse(ngayDat, DateTimeFormatter.ofPattern("yyyy-MM-dd")), localTime);
				list.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public float TongTienDV(Date ngayBatDau, Date ngayKetThuc) {
		float tong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getTongDoanhThuDichVu(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setDate(1, ngayBatDau);
			myCall.setDate(2, ngayKetThuc);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				tong += rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}
}
