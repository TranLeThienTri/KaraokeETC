package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entitys.KhachHang;
import entitys.LoaiKhachHang;
import entitys.LoaiPhong;
import entitys.Phong;
import entitys.TinhTrangPhong;

public class DanhSachPhong {
	// code ở đây
	ArrayList<Phong> list;

	public DanhSachPhong() {
		list = new ArrayList<Phong>();
	}

	public ArrayList<Phong> getDSPhong() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSPhong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString(1);
				String malPhong = rs.getString(2);
				int sucChua = rs.getInt(3);
				Float giaPhong = rs.getFloat(4);
				String matinhTrang = rs.getString(5);
				String tenTinhTrangPhong = "";
				if (matinhTrang.equals("BOOK")) {
					tenTinhTrangPhong = "Phòng đã đặt";
				} else if (matinhTrang.equals("EMPT")) {
					tenTinhTrangPhong = "Phòng trống";
				} else if (matinhTrang.equals("RENT")) {
					tenTinhTrangPhong = "Phòng đang thuê";
				}
				float dienTich = rs.getFloat(6);
				String tenLoaiPhong = "";
				if(malPhong.equals("NOR"))
					tenLoaiPhong = "Phòng thường";
				else tenLoaiPhong = "Phòng VIP";
				LoaiPhong maLoaiPhong = new LoaiPhong(malPhong,tenLoaiPhong);
				TinhTrangPhong maTinhTrangP = new TinhTrangPhong(matinhTrang,tenTinhTrangPhong);
				Phong phong = new Phong(maPhong, maLoaiPhong, sucChua, giaPhong, maTinhTrangP, dienTich);
				list.add(phong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean themPhong(Phong p) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call themPhong(?,?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			Dao_PhatSinhMa dao = new Dao_PhatSinhMa();
			String maKH = dao.getMaPhongCuoi();
			myCall.setString(1, maKH);
			myCall.setString(2, p.getMaLoaiPhong().getMaLoaiPhong());
			myCall.setInt(3, p.getSucChua());
			myCall.setFloat(4, p.getGiaPhong());
			myCall.setString(5, p.getMaTinhTrangPhong().getMaTinhTrangPhong());
			myCall.setFloat(6, p.getDienTich());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public ArrayList<TinhTrangPhong> getDSTinhTrang() {
		ArrayList<TinhTrangPhong> dao = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getTinhTrang}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				TinhTrangPhong ttp = new TinhTrangPhong(ma, ten);
				dao.add(ttp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao;
	}

	public ArrayList<LoaiPhong> getDSLoatPhong() {
		ArrayList<LoaiPhong> dao = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getLoaiPhong}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				LoaiPhong loaiPhong = new LoaiPhong(ma, ten);
				dao.add(loaiPhong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao;
	}
	public ArrayList<Phong> getDSPhongTheoMa() {
		ArrayList<Phong> dao = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getPhongTheoMa}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString(6);
				String malPhong = rs.getString(1);
				int sucChua = rs.getInt(2);
				Float giaPhong = rs.getFloat(3);
				String matinhTrang = rs.getString(4);
				String tenTinhTrangPhong = "";
				if (matinhTrang.equals("BOOK")) {
					tenTinhTrangPhong = "Phòng đã đặt";
				} else if (matinhTrang.equals("EMPT")) {
					tenTinhTrangPhong = "Phòng trống";
				} else if (matinhTrang.equals("RENT")) {
					tenTinhTrangPhong = "Phòng đang thuê";
				}
				float dienTich = rs.getFloat(5);
				String tenLoaiPhong = "";
				if(malPhong.equals("NOR"))
					tenLoaiPhong = "Phòng thường";
				else tenLoaiPhong = "Phòng VIP";
				LoaiPhong maLoaiPhong = new LoaiPhong(malPhong,tenLoaiPhong);
				TinhTrangPhong maTinhTrangP = new TinhTrangPhong(matinhTrang,tenTinhTrangPhong);
				Phong phong = new Phong(maPhong, maLoaiPhong, sucChua, giaPhong, maTinhTrangP, dienTich);
				dao.add(phong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao;
	}
	
	
	public boolean suaPhong(Phong p) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call updatePhong(?,?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(6, p.getMaPhong());
			myCall.setString(1, p.getMaLoaiPhong().getMaLoaiPhong());
			myCall.setInt(2, p.getSucChua());
			myCall.setFloat(3, p.getGiaPhong());
			myCall.setString(4, p.getMaTinhTrangPhong().getMaTinhTrangPhong());
			myCall.setFloat(5, p.getDienTich());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
}
