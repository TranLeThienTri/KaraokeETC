package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.ChiTietHoaDon;
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.NhanVien;
import entitys.PhuThu;

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
	
	public boolean themDVCTHD(ChiTietHoaDon CTHD) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call themDichVuPhong(?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, CTHD.getMaHoaDonPhong().getMaHoaDon());
			myCall.setString(2, CTHD.getDichVu().getMaDichVu());
			myCall.setInt(3, CTHD.getSoLuong());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
//	public ArrayList<ChiTietHoaDon> getCTHD() {
//		ArrayList<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
//		DanhSachDichVu daov = new DanhSachDichVu();
//		DanhSachPhuThu daopt = new DanhSachPhuThu();
//		ThuePhong daoTP = new ThuePhong();
//		try {
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			String sql = "{call getCTHD}";
//			CallableStatement myCall = con.prepareCall(sql);
//			ResultSet rs = myCall.executeQuery();
//			while (rs.next()) {
//				String mahd = rs.getString(1);
//				String madv = rs.getString(2);
//				int sl = rs.getInt(3);
//				String mapt = rs.getString(4);
//				HoaDonPhong p = daoTP.getHDTheoMa(mahd);
//				DichVu dv = daov.getDVTheoMa(madv);
//				PhuThu pt = daopt.getPTTheoMa(mapt);
//				ChiTietHoaDon ct = new ChiTietHoaDon(p, dv, sl, pt);
//				list.add(ct);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

}
