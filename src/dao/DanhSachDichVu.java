package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.DichVu;
import entitys.KhachHang;
import entitys.LoaiDichVu;
import entitys.LoaiKhachHang;

public class DanhSachDichVu {
	ArrayList<DichVu> list;

	public DanhSachDichVu() {
		list = new ArrayList<DichVu>();
	}
	
	public ArrayList<DichVu> getDSDichVu() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSDichVu}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maDV = rs.getString(1);
				String tenDV = rs.getString(2);
				String maLDV = rs.getString(3);
				int soLuongTon = rs.getInt(4);
				Double donGia = rs.getDouble(5);
				String tenLDV;
				if(maLDV.equals("FOOD"))
					tenLDV = "Thực phẩm";
				else tenLDV = "Nước uống";
				LoaiDichVu maLoaiDV = new LoaiDichVu(maLDV, tenLDV);
				DichVu dv = new DichVu(maDV, tenDV, maLoaiDV, soLuongTon, donGia);
				list.add(dv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean themDichVu(DichVu dv) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call themKhachHang(?,?,?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			Dao_PhatSinhMa dao = new Dao_PhatSinhMa();
			String maKH = dao.getMaNVCuoi();
			myCall.setString(1, maKH);
			myCall.setString(2, dv.getTenDichVu());
			myCall.setString(3, dv.getloaiDichVu().getMaLoaiDichVu());
			myCall.setInt(4, dv.getSoLuongTon());
			myCall.setDouble(5, dv.getDonGia());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
}