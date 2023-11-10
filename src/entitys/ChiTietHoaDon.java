package entitys;

import java.time.LocalDate;
import java.time.LocalTime;

public class ChiTietHoaDon {
	private HoaDonPhong maHoaDonPhong;
	private DichVu dichVu;
	private int soLuong;
	public ChiTietHoaDon(HoaDonPhong maHoaDonPhong, DichVu dichVu, double giamGia, int soLuong) {
		
		this.maHoaDonPhong = maHoaDonPhong;
		this.dichVu = dichVu;
		
		this.soLuong = soLuong;
	}
	public HoaDonPhong getMaHoaDonPhong() {
		return maHoaDonPhong;
	}
	public void setMaHoaDonPhong(HoaDonPhong maHoaDonPhong) {
		this.maHoaDonPhong = maHoaDonPhong;
	}
	public DichVu getdichVu() {
		return dichVu;
	}
	public void setdichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}
	
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public double thanhTien() {
		return (double) (this.getSoLuong() * this.getdichVu().getDonGia());
	}
	
	
}
