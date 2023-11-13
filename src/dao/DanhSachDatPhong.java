package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.ChucVu;
import entitys.LoaiPhong;
import entitys.NhanVien;
import entitys.Phong;
import entitys.TinhTrangPhong;

public class DanhSachDatPhong {
	ArrayList<Phong> listRoomByDate = new ArrayList<Phong>();
	ArrayList<Phong> listRoomByType = new ArrayList<Phong>();

	// lấy danh sách phòng theo ngày
	public ArrayList<Phong> getAllRoom() {
		listRoomByDate.clear();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// getAllRoomByDate => đưa vào ngày tháng năm và lấy tất cả các phòng dựa vào
			// ngày đưa vào
			String sql = "{call getAllRoom}";
			CallableStatement myCall = con.prepareCall(sql);
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

	// Lấy danh sách phòng theo ngày và theo loại
	
	

	// danh sách phòng theo tình trạng

}
