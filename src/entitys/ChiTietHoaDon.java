package entitys;

import java.time.LocalDate;
import java.time.LocalTime;

public class ChiTietHoaDon {
	private HoaDonPhong maHoaDonPhong;
	private DichVu dichVu;
	private int soLuong;
	private PhuThu phuThu;

	public ChiTietHoaDon(HoaDonPhong maHoaDonPhong, DichVu dichVu, int soLuong, PhuThu phuThu) {

		this.maHoaDonPhong = maHoaDonPhong;
		this.dichVu = dichVu;
		this.phuThu = phuThu;
		this.soLuong = soLuong;
	}

	public ChiTietHoaDon(HoaDonPhong maHoaDonPhong, DichVu dichVu, int soLuong) {
		super();
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

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public PhuThu getPhuThu() {
		return phuThu;
	}

	public void setPhuThu(PhuThu phuThu) {
		this.phuThu = phuThu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

}
