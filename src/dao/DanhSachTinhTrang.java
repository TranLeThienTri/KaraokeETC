package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.TinhTrangPhong;

public class DanhSachTinhTrang {
	ArrayList<TinhTrangPhong> listTypes = new ArrayList<TinhTrangPhong>();

	public ArrayList<TinhTrangPhong> getAllRoomTypes() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getAllRoomStatus}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String type_id = rs.getString(1);
				String type_name = rs.getString(2);
				TinhTrangPhong ttp = new TinhTrangPhong(type_id, type_name);
				listTypes.add(ttp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTypes;
	}

	public TinhTrangPhong getStatusRoomById(String id) {
		TinhTrangPhong ttp = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getStatusRoomById(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, id);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String type_id = rs.getString(1);
				String type_name = rs.getString(2);
				ttp = new TinhTrangPhong(type_id, type_name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ttp;
	}
}
