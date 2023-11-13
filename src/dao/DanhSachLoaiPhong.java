package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.LoaiPhong;

public class DanhSachLoaiPhong {
	ArrayList<LoaiPhong> listTypes = new ArrayList<LoaiPhong>();

	public ArrayList<LoaiPhong> getAllRoomTypes() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call allRoomTypes}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String type_id = rs.getString(1);
				String type_name = rs.getString(2);

				LoaiPhong lp = new LoaiPhong(type_id, type_name);
				listTypes.add(lp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTypes;
	}

	public LoaiPhong getTypeById(String id) {
		LoaiPhong lp = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getRoomById(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, id);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String type_id = rs.getString(1);
				String type_name = rs.getString(2);

				lp = new LoaiPhong(type_id, type_name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lp;
	}
}
