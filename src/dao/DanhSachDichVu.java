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
			list = new ArrayList();
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
				if (maLDV.equals("FOOD"))
					tenLDV = "Thực phẩm";
				else
					tenLDV = "Nước uống";
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
			String sql = "{call themDichVu(?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			Dao_PhatSinhMa dao = new Dao_PhatSinhMa();
			String maDa = dao.getMaDATuDong();
			String maNU = dao.getMaNUTuDong();
			String ma;
			myCall.setString(1, dv.getMaDichVu());
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

	public DichVu getDVTheoMa(String ma) {
		DichVu dv = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDVTheoMa(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maDV = rs.getString(1);
				String tenDV = rs.getString(2);
				String maLDV = rs.getString(3);
				int soLuongTon = rs.getInt(4);
				Double donGia = rs.getDouble(5);
				String tenLDV;
				if (maLDV.equals("FOOD"))
					tenLDV = "Thực phẩm";
				else
					tenLDV = "Nước uống";
				LoaiDichVu maLoaiDV = new LoaiDichVu(maLDV, tenLDV);
				dv = new DichVu(maDV, tenDV, maLoaiDV, soLuongTon, donGia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dv;
	}

	public ArrayList<DichVu> getDSDichVuTheoLoai(String maLDV) {
		ArrayList<DichVu> dao = new ArrayList();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSDVTheoLoai(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, maLDV);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String tenDV = rs.getString(2);
				DichVu dv = new DichVu(ma, tenDV);
				dao.add(dv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao;
	}

	public boolean suaDichVu(DichVu dv) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call SuaDichVu(?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, dv.getMaDichVu());
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
	public boolean suaSLTDichVu(DichVu dv) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call updateSLTDV(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setInt(1, dv.getSoLuongTon());
			myCall.setString(2, dv.getTenDichVu());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public int getSLTheoMaDV(String ma) {
		int tong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getSLTheoMaDV(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				tong = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}
	public float getDGTheoMaDV(String ma) {
		float tong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDGTheoMaDV(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				tong = rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}
	public String getMaDVTheoTen(String ma) {
		String madv = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getMaDVTheoTen(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				madv = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return madv;
	}
	public int getSLTonTheoMaDV(String ma) {
		int tong = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getSLTonTheoMaDV(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				tong = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}
	public boolean updateSLTon(String ma,int sl) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call updateSLTonTheoMaDV(?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			myCall.setInt(2, sl);
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public DichVu getDVTheoTen(String ten) {
		DichVu dv = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDVTheoTen(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ten);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maDV = rs.getString(1);
				String tenDV = rs.getString(2);
				String maLDV = rs.getString(3);
				int soLuongTon = rs.getInt(4);
				Double donGia = rs.getDouble(5);
				String tenLDV;
				if (maLDV.equals("FOOD"))
					tenLDV = "Thực phẩm";
				else
					tenLDV = "Nước uống";
				LoaiDichVu maLoaiDV = new LoaiDichVu(maLDV, tenLDV);
				dv = new DichVu(maDV, tenDV, maLoaiDV, soLuongTon, donGia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dv;
	}

	
}

