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
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.NhanVien;

public class DanhSachHoaDon {
	public ArrayList<KhachHang> getDSKhachHangTheoNgay() {
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSKhachHangTheoNgay}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String sdt = rs.getString(5);
				String cccd = rs.getString(3);
				Boolean gioiTinh = rs.getBoolean(4);
				String malkh = rs.getString(7);
				String tenlkh;
				if(malkh.equals("NOR"))
					tenlkh = "Khách hàng thường";
				else tenlkh = "Khách hàng VIP";
				LoaiKhachHang maLoaiKH = new LoaiKhachHang(malkh,tenlkh);
				int dtl=rs.getInt(6);
				KhachHang kh = new KhachHang(maKH, tenKH, cccd, sdt, dtl, gioiTinh,maLoaiKH);
				list.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
