package dao;

import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import entitys.ChucVu;
import entitys.KhachHang;
import entitys.LoaiKhachHang;
import entitys.NhanVien;



public class DanhSachNhanVien {
	ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
	
	public ArrayList<NhanVien> getAllDanhSachNV() {
		try {
			dsNV = new ArrayList<>();
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getDSNV}";
			CallableStatement myCall = con.prepareCall(sql);
			ResultSet rs = myCall.executeQuery();
			while(rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String maChucVu = rs.getString(3);
				Boolean phai = rs.getBoolean(4);
				LocalDate ngaySinh = LocalDate.parse(rs.getString(5));
				String diaChi = rs.getString(6);
				String sdt = rs.getString(7);
				String cccd = rs.getString(8);
				Boolean tinhTrang = rs.getBoolean(9);			
				DanhSachChucVu listRole = new DanhSachChucVu();
				ChucVu role = listRole.getRoleById(maChucVu);
				
				NhanVien nv = new NhanVien(maNV, tenNV, cccd, diaChi, sdt, phai, role, ngaySinh, tinhTrang);
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	
	
	
	public NhanVien getNhanVienTheoMa(String ma) {
		NhanVien nv =null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call getNVTheoMa(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, ma);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String maChucVu = rs.getString(3);
				Boolean phai = rs.getBoolean(4);
				LocalDate ngaySinh = LocalDate.parse(rs.getString(5));
				String diaChi = rs.getString(6);
				String sdt = rs.getString(7);
				String cccd = rs.getString(8);
				Boolean tinhTrang = rs.getBoolean(9);			
				ChucVu cv = new ChucVu();
				DanhSachChucVu listRole = new DanhSachChucVu();
				cv = listRole.getRoleById(maChucVu);
				nv = new NhanVien(maNV, tenNV, cccd, diaChi, sdt, phai, cv, ngaySinh, tinhTrang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
	public boolean themNhanVien(NhanVien nv) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call themNhanVien(?,?,?,?,?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			Dao_PhatSinhMa dao = new Dao_PhatSinhMa();
			String maNV = dao.getMaNVCuoi();
			myCall.setString(1, maNV);
			myCall.setString(2, nv.getHoTenNhanVien());
			myCall.setString(3, nv.getNgaySinh().toString());
			myCall.setString(4, nv.getSdt());
			myCall.setString(5, nv.getSoCCCD());
			myCall.setString(6, nv.getDiaChi());
			myCall.setBoolean(7, nv.isGioiTinh());
			myCall.setString(8, nv.getchucVu().getMaChucVu());
			myCall.setBoolean(9, nv.isTinhTrang());		
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean suaNhanVien(NhanVien nv) {
		boolean b = true;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call updateNV(?,?,?,?,?,?,?,?,?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, nv.getHoTenNhanVien());
			myCall.setString(2, nv.getNgaySinh().toString());
			myCall.setString(3, nv.getSdt());
			myCall.setString(4, nv.getSoCCCD());
			myCall.setString(5, nv.getDiaChi());
			myCall.setBoolean(6, nv.isGioiTinh());
			myCall.setString(7, nv.getchucVu().getMaChucVu());
			myCall.setBoolean(8, nv.isTinhTrang());	
			myCall.setString(9, nv.getMaNhanVien());
			b = myCall.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public NhanVien getNhanVienTheoSDT(String s) {
		NhanVien nv = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "{call kiemTraNVTheoSDT(?)}";
			CallableStatement myCall = con.prepareCall(sql);
			myCall.setString(1, s);
			ResultSet rs = myCall.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String maChucVu = rs.getString(3);
				Boolean phai = rs.getBoolean(4);
				LocalDate ngaySinh = LocalDate.parse(rs.getString(5));
				String diaChi = rs.getString(6);
				String sdt = rs.getString(7);
				String cccd = rs.getString(8);
				Boolean tinhTrang = rs.getBoolean(9);			
				ChucVu cv = new ChucVu();
				DanhSachChucVu listRole = new DanhSachChucVu();
				cv = listRole.getRoleById(maChucVu);
				nv = new NhanVien(maNV, tenNV, cccd, diaChi, sdt, phai, cv, ngaySinh, tinhTrang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
	
}
