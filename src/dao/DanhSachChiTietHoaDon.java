package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.NhanVien;

public class DanhSachChiTietHoaDon {
	public boolean themDVTheoMaPhong(KhachHang kh) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call themKhachHang(?,?,?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			Dao_PhatSinhMa dao = new Dao_PhatSinhMa();
			String maKH = dao.getMaKHCuoi();
			myCall.setString(1, maKH);
			myCall.setString(2, kh.getHoTenKhachHang());
			myCall.setString(3, kh.getSoCCCD());
			myCall.setString(4, kh.getSoDienThoai());
			myCall.setInt(5, kh.getDiemTichLuy());
			myCall.setBoolean(6, kh.getGioiTinh());
			myCall.setString(7, kh.getLoaiKhachHang().getMaLoaiKhachHang());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public ArrayList<DichVu> getDSDVTheoDSMaDV(ArrayList<DichVu> dsMadv) {
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		DanhSachDichVu dao = new DanhSachDichVu();
		for (DichVu dv : dsMadv) {
			DichVu dichVu = dao.getDVTheoMa(dv.getMaDichVu());
			if (!list.contains(dichVu))
				list.add(dichVu);
		}
		return list;
	}
	
//	public ArrayList<HoaDonPhong> getDSHDTheoDSHoaDonPhong(ArrayList<HoaDonPhong> dsMaHoaDon) {
//		ArrayList<HoaDonPhong> list = new ArrayList<HoaDonPhong>();
//		DanhSachHoaDon dao = new DanhSachHoaDon();
//		for (HoaDonPhong hdp : dsMaHoaDon) {
//			HoaDonPhong hoaDonPhong = dao.getds(dv.getMaDichVu());
//			if (!list.contains(dichVu))
//				list.add(dichVu);
//		}
//		return list;
//	}

}
