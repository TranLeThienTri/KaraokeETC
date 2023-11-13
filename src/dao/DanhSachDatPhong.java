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
	ArrayList<Phong> listRoomByDate = new ArrayList<Phong>();
	ArrayList<Phong> listRoomByType = new ArrayList<Phong>();
	ArrayList<HoaDonPhong> listHD = new ArrayList<HoaDonPhong>();
	

	// lấy danh sách phòng theo ngày
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
				
				if(maLoaiHoaDon.equalsIgnoreCase("HDD")) {
					ttp = new TinhTrangPhong("BOOK", "Đã được đặt");
				}else if(maLoaiHoaDon.equalsIgnoreCase("EMPT")){
					ttp = new TinhTrangPhong("EMPT", "Còn trống");					
				}else
					ttp = new TinhTrangPhong("RENT", "Đang Thuê");
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
	public ArrayList<Phong> getAllRoomByType(String type) {
		listRoomByType.clear();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			// getAllRoomByDate => đưa vào ngày tháng năm và lấy tất cả các phòng dựa vào
			// ngày đưa vào
			String sql = "{call getAllRoomByType(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, type);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maP = rs.getString(1);
				String maLoaiPhong = rs.getString(2);
				int sucChua = rs.getInt(3);
				float giaPhong = rs.getFloat(4);
				String maTinhTrang = rs.getString(5);

				DanhSachLoaiPhong lsTypes = new DanhSachLoaiPhong();
				DanhSachTinhTrang lsStatus = new DanhSachTinhTrang();

				LoaiPhong lp = lsTypes.getTypeById(maLoaiPhong);
				TinhTrangPhong ttp = lsStatus.getStatusRoomById(maTinhTrang);
				Phong p = new Phong(maP, lp, sucChua, giaPhong, ttp);
				listRoomByType.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listRoomByType;
	}

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
				String ngayLapHoaDon = rs.getString(7);
				Phong p = new Phong(maPhong);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
				 // Chuyển đổi chuỗi thành LocalTime
			    LocalTime localTime = LocalTime.parse(gioDat, formatter);

				DanhSachKhachHang dskh = new DanhSachKhachHang();
				DanhSachNhanVien dsnv = new DanhSachNhanVien();
				NhanVien nv = dsnv.getNhanVienTheoMa(maNhanVien);
				KhachHang kh = dskh.getKHTheoMa(maKhachhang);
				
				// Chuyển đổi chuỗi thành đối tượng LocalDate
		        LocalDate date = LocalDate.parse(ngayLapHoaDon);
		        
				HoaDonPhong hd = new HoaDonPhong(maHD, p, nv, kh, new LoaiHoaDon("HDD"), date, LocalDate.parse(ngayDat,DateTimeFormatter.ofPattern("yyyy-MM-dd")), localTime);
				listHD.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listHD;
	}
	
	

}
