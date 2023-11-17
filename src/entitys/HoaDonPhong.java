package entitys;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class HoaDonPhong {

	private String maHoaDon;
	private Phong phong;
	private NhanVien maNhanVien;
	private KhachHang maKhachHang;
	private LoaiHoaDon maLoaiHoaDon;
	private LocalDate ngayLapHoaDon;
	private LocalTime gioBatDauThue;
	private LocalTime gioTraPhong;
	private LocalDate ngayDat;
	private LocalTime gioDat;
	private float thanhTien;

	
	
	
	public HoaDonPhong(String maHoaDon, Phong phong, NhanVien maNhanVien, KhachHang maKhachHang,
			LoaiHoaDon maLoaiHoaDon, LocalDate ngayLapHoaDon, LocalDate ngayDat, LocalTime gioDat) {
		super();
		this.maHoaDon = maHoaDon;
		this.phong = phong;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.maLoaiHoaDon = maLoaiHoaDon;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.ngayDat = ngayDat;
		this.gioDat = gioDat;
	}

	public HoaDonPhong(String maHoaDon, Phong phong, NhanVien maNhanVien,
			KhachHang maKhachHang, LoaiHoaDon maLoaiHoaDon, LocalDate ngayLapHoaDon, LocalTime gioBatDauThue,
			LocalTime gioTraPhong, LocalDate ngayDat, LocalTime gioDat, float thanhTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.phong = phong;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.maLoaiHoaDon = maLoaiHoaDon;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.gioBatDauThue = gioBatDauThue;
		this.gioTraPhong = gioTraPhong;
		this.ngayDat = ngayDat;
		this.gioDat = gioDat;
		this.thanhTien = thanhTien;
	}

	public HoaDonPhong(String maHoaDon, Phong phong, NhanVien maNhanVien,
			KhachHang maKhachHang, LoaiHoaDon maLoaiHoaDon, LocalDate ngayLapHoaDon, LocalTime gioBatDauThue, LocalTime gioTraPhong) {
		super();
		this.maHoaDon = maHoaDon;
		this.phong = phong;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.maLoaiHoaDon = maLoaiHoaDon;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.gioBatDauThue = gioBatDauThue;
		this.gioTraPhong = gioTraPhong;
	}
	public HoaDonPhong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HoaDonPhong(String ma) {
		super();
		this.maHoaDon = ma;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}


	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}


	public Phong getPhong() {
		return phong;
	}


	public void setPhong(Phong phong) {
		this.phong = phong;
	}


	public NhanVien getMaNhanVien() {
		return maNhanVien;
	}


	public void setMaNhanVien(NhanVien maNhanVien) {
		this.maNhanVien = maNhanVien;
	}


	public KhachHang getMaKhachHang() {
		return maKhachHang;
	}


	public void setMaKhachHang(KhachHang maKhachHang) {
		this.maKhachHang = maKhachHang;
	}


	public LoaiHoaDon getMaLoaiHoaDon() {
		return maLoaiHoaDon;
	}


	public void setMaLoaiHoaDon(LoaiHoaDon maLoaiHoaDon) {
		this.maLoaiHoaDon = maLoaiHoaDon;
	}


	public LocalDate getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}


	public void setNgayLapHoaDon(LocalDate ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}


	public LocalTime getGioBatDauThue() {
		return gioBatDauThue;
	}


	public void setGioBatDauThue(LocalTime gioBatDauThue) {
		this.gioBatDauThue = gioBatDauThue;
	}


	public LocalTime getGioTraPhong() {
		return gioTraPhong;
	}


	public void setGioTraPhong(LocalTime gioTraPhong) {
		this.gioTraPhong = gioTraPhong;
	}


	public LocalDate getNgayDat() {
		return ngayDat;
	}


	public void setNgayDat(LocalDate ngayDat) {
		this.ngayDat = ngayDat;
	}


	public LocalTime getGioDat() {
		return gioDat;
	}


	public void setGioDat(LocalTime gioDat) {
		this.gioDat = gioDat;
	}


	public float getThanhTien() {
		return thanhTien;
	}


	public void setThanhTien(float thanhTien) {
		this.thanhTien = thanhTien;
	}

	
	public double tinhTienPhong() {
		double mT = this.getGioBatDauThue().getHour() * 60 + this.getGioBatDauThue().getMinute(); 
		double mTra = this.getGioTraPhong().getHour() * 60 + this.getGioTraPhong().getMinute();
		double tongGioThue = (mTra - mT) / 60;
		return (double)(this.getPhong().getGiaPhong() * tongGioThue);
	}


	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDonPhong other = (HoaDonPhong) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

}
