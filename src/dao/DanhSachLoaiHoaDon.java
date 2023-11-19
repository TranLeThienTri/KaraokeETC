package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.ChucVu;
import entitys.LoaiHoaDon;
import entitys.NhanVien;

public class DanhSachLoaiHoaDon {

	ArrayList<LoaiHoaDon> list = new ArrayList<LoaiHoaDon>();

	public ArrayList<LoaiHoaDon> layDanhSachLoaiHoaDon() {

		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getListLHD}";
			CallableStatement myCall = con.prepareCall(sql);

			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maLoaiHoaDon = rs.getString(1);
				String tenLoaiHoaDon = rs.getString(2);

				LoaiHoaDon lhd = new LoaiHoaDon(maLoaiHoaDon, tenLoaiHoaDon);
				list.add(lhd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public LoaiHoaDon layLoaiHoaDonTheoMa(String ma) {
		LoaiHoaDon lhd = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getLHDById(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maLoaiHoaDon = rs.getString(1);
				String tenLoaiHoaDon = rs.getString(2);
				lhd = new LoaiHoaDon(maLoaiHoaDon, tenLoaiHoaDon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lhd;
	}

}
