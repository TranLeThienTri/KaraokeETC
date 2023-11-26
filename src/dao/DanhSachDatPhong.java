package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.ChucVu;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.LoaiHoaDon;
import entitys.LoaiPhong;
import entitys.NhanVien;
import entitys.Phong;
import entitys.TinhTrangPhong;

public class DanhSachDatPhong {
	ArrayList<Phong> listRoomByType = new ArrayList<Phong>();
	ArrayList<HoaDonPhong> listHD = new ArrayList<HoaDonPhong>();
	ArrayList<Phong> listRoomByDate = new ArrayList<Phong>();

	// lấy danh sách phòng có tình trạng theo ngày theo ngày
	public ArrayList<Phong> getAllRoomByDate(String date) {
		listRoomByDate.clear();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// getAllRoomByDate => đưa vào ngày tháng năm và lấy tất cả các phòng dựa vào
			// ngày đưa vào
			String sql = "{call getListRoomByDate(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, date);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maP = rs.getString(1);
				String maLoaiPhong = rs.getString(2);
				int sucChua = rs.getInt(3);
				float giaPhong = rs.getFloat(4);
				String maLoaiHoaDon = rs.getString(5);

				TinhTrangPhong ttp;
				DanhSachLoaiPhong lsTypes = new DanhSachLoaiPhong();
				DanhSachTinhTrang lsStatus = new DanhSachTinhTrang();

				if (maLoaiHoaDon == null)
					maLoaiHoaDon = "NULL";

				if (maLoaiHoaDon.equalsIgnoreCase("HDD")) {
					ttp = lsStatus.getStatusRoomById("BOOK");
				} else if (maLoaiHoaDon.equalsIgnoreCase("HDT")) {
					ttp = lsStatus.getStatusRoomById("RENT");
				} else
					ttp = lsStatus.getStatusRoomById("EMPT");

				LoaiPhong lp = lsTypes.getTypeById(maLoaiPhong);

				Phong p = new Phong(maP, lp, sucChua, giaPhong, ttp);
				listRoomByDate.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listRoomByDate;
	}

	// lấy danh sách phòng theo Loại
	public ArrayList<Phong> getAllRoomByType(String date, String type) {
		listRoomByType.clear();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			// getAllRoomByDate => đưa vào ngày tháng năm và lấy tất cả các phòng dựa vào
			// ngày đưa vào
			String sql = "{call getListRoomByDateAndType(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, date);
			myCall.setString(2, type);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maP = rs.getString(1);
				String maLoaiPhong = rs.getString(2);
				int sucChua = rs.getInt(3);
				float giaPhong = rs.getFloat(4);
				String maLoaiHoaDon = rs.getString(5);

				TinhTrangPhong ttp;
				DanhSachLoaiPhong lsTypes = new DanhSachLoaiPhong();
				DanhSachTinhTrang lsStatus = new DanhSachTinhTrang();

				if (maLoaiHoaDon == null)
					maLoaiHoaDon = "NULL";

				if (maLoaiHoaDon.equalsIgnoreCase("HDD")) {
					ttp = lsStatus.getStatusRoomById("BOOK");
				} else if (maLoaiHoaDon.equalsIgnoreCase("HDT")) {
					ttp = lsStatus.getStatusRoomById("RENT");
				} else
					ttp = lsStatus.getStatusRoomById("EMPT");

				LoaiPhong lp = lsTypes.getTypeById(maLoaiPhong);

				Phong p = new Phong(maP, lp, sucChua, giaPhong, ttp);
				listRoomByType.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listRoomByType;
	}

	// ok
	// Lấy danh sách phòng theo ngày và đã được đặt
	public ArrayList<HoaDonPhong> getAllRoomStatusByDate() {
		listHD.clear();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "{call getAllRoomStatusByDate}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maPhong = rs.getString(2);
				String maKhachhang = rs.getString(3);
				String gioDattt = rs.getString(4);
				String[] gioDatt = gioDattt.split("\\.");
				String gioDat = gioDatt[0];
				String maNhanVien = rs.getString(5);
				String ngayDat = rs.getString(6);
//				String ngayLapHoaDon = rs.getString(7);
				Phong p = new Phong(maPhong);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
				// Chuyển đổi chuỗi thành LocalTime
				LocalTime localTime = LocalTime.parse(gioDat, formatter);

				DanhSachKhachHang dskh = new DanhSachKhachHang();
				DanhSachNhanVien dsnv = new DanhSachNhanVien();
				NhanVien nv = dsnv.getNhanVienTheoMa(maNhanVien);
				KhachHang kh = dskh.getKHTheoMa(maKhachhang);

				// Chuyển đổi chuỗi thành đối tượng LocalDate
//				LocalDate date = LocalDate.parse(ngayLapHoaDon);

				HoaDonPhong hd = new HoaDonPhong(maHD, p, nv, kh, new LoaiHoaDon("HDD"), null,
						LocalDate.parse(ngayDat, DateTimeFormatter.ofPattern("yyyy-MM-dd")), localTime);
				listHD.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listHD;
	}

	public boolean themHoaDonDat(HoaDonPhong p) {
		boolean b = false;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call themHoaDonDat(?,?,?,?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, p.getMaHoaDon());
			myCall.setString(2, p.getNgayLapHoaDon().toString());
			myCall.setString(3, p.getNgayDat().toString());
			myCall.setString(4, p.getGioDat().toString());
			myCall.setString(5, p.getMaKhachHang().getMaKhachHang());
			myCall.setString(6, p.getMaNhanVien().getMaNhanVien());
			myCall.setString(7, p.getMaLoaiHoaDon().getMaLoaiHoaDon());
			myCall.setString(8, p.getPhong().getMaPhong());
			b = myCall.execute();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}


	public HoaDonPhong getHoaDonById(String ma) {

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();

		HoaDonPhong hdp = null;
		try {
			String sql = "{call getHoaDonById (?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				LocalDate ngayLapHD = LocalDate.parse(rs.getString(2));
				LocalDate ngayDat = LocalDate.parse(rs.getString(5));
				LocalTime gioDat = LocalTime.parse(rs.getString(6));
				String maKhachhang = rs.getString(7);
				String maNhanVien = rs.getString(8);

				DanhSachLoaiHoaDon dsLHD = new DanhSachLoaiHoaDon();
				LoaiHoaDon lhd = dsLHD.layLoaiHoaDonTheoMa(rs.getString(9));

				DanhSachPhong dsp = new DanhSachPhong();
				Phong p = dsp.getPhongTheoMa(rs.getString(10));

				DanhSachKhachHang dskh = new DanhSachKhachHang();
				DanhSachNhanVien dsnv = new DanhSachNhanVien();
				NhanVien nv = dsnv.getNhanVienTheoMa(maNhanVien);
				KhachHang kh = dskh.getKHTheoMa(maKhachhang);

				hdp = new HoaDonPhong(maHD, p, nv, kh, lhd, ngayLapHD, ngayDat, gioDat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hdp;
	}

	public boolean huyDatPhong(String ma) {

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		boolean flag = false;
		try {
			String sql = "{call deleteBillById(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			flag = myCall.execute();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
}
