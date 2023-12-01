package entitys;

import java.time.LocalDate;
import java.time.LocalTime;

public class ChiTietHoaDon {
	private HoaDonPhong maHoaDonPhong;
	private DichVu dichVu;
	private int soLuong;
	private PhuThu phuThu;
	private Phong maPhong;
	private LocalTime gioChuyen;
	private LocalTime gioVao;

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

	public ChiTietHoaDon(HoaDonPhong maHoaDonPhong, Phong maPhong, LocalTime gioChuyen,LocalTime gioVao) {
		super();
		this.maHoaDonPhong = maHoaDonPhong;
		this.maPhong = maPhong;
		this.gioChuyen = gioChuyen;
		this.gioVao = gioVao;
	}

	public ChiTietHoaDon(HoaDonPhong p, DichVu dv, int sl, PhuThu pt, Phong map, LocalTime gioChuyen,
			LocalTime gioVao) {
		this.maHoaDonPhong = p;
		this.dichVu = dv;
		this.phuThu = pt;
		this.soLuong = sl;
		this.maPhong = map;
		this.gioChuyen = gioChuyen;
		this.gioVao = gioVao;
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

	public Phong getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(Phong maPhong) {
		this.maPhong = maPhong;
	}

	public LocalTime getGioChuyen() {
		return gioChuyen;
	}

	public void setGioChuyen(LocalTime gioChuyen) {
		this.gioChuyen = gioChuyen;
	}

	public LocalTime getGioVao() {
		return gioVao;
	}

	public void setGioVao(LocalTime gioVao) {
		this.gioVao = gioVao;
	}

}
