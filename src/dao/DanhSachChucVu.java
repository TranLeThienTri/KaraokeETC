package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.ChucVu;
import entitys.NhanVien;

public class DanhSachChucVu {
	ArrayList<ChucVu> listRole = new ArrayList<ChucVu>();
	
	public ArrayList<ChucVu> getAllRole() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getListRole}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while(rs.next()) {
				String role_id = rs.getString(1);
				String role_name = rs.getString(2);
				ChucVu cv = new ChucVu(role_id, role_name);
				listRole.add(cv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listRole;
	}
	

	public ChucVu getRoleById(String id) {
		ChucVu cv = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getRoleById(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, id);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String role_id = rs.getString(1);
				String role_name = rs.getString(2);
				cv = new ChucVu(role_id, role_name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cv;
	}
	
}
