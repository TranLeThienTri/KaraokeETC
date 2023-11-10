package entitys;

import java.util.Objects;

public class Phong {
	private String maPhong;
	private LoaiPhong maLoaiPhong;
	private int sucChua;
	private double giaPhong;
	private TinhTrangPhong maTinhTrangPhong;
	private double dienTich;
	public Phong(String maPhong, LoaiPhong maLoaiPhong, int sucChua, double giaPhong, TinhTrangPhong maTinhTrangPhong,
			double dienTich) {
		
		this.maPhong = maPhong;
		this.maLoaiPhong = maLoaiPhong;
		this.sucChua = sucChua;
		this.giaPhong = giaPhong;
		this.maTinhTrangPhong = maTinhTrangPhong;
		this.dienTich = dienTich;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public LoaiPhong getMaLoaiPhong() {
		return maLoaiPhong;
	}
	public void setMaLoaiPhong(LoaiPhong maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}
	public int getSucChua() {
		return sucChua;
	}
	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}
	public double getGiaPhong() {
		return giaPhong;
	}
	public void setGiaPhong(double giaPhong) {
		this.giaPhong = giaPhong;
	}
	public TinhTrangPhong getMaTinhTrangPhong() {
		return maTinhTrangPhong;
	}
	public void setMaTinhTrangPhong(TinhTrangPhong maTinhTrangPhong) {
		this.maTinhTrangPhong = maTinhTrangPhong;
	}
	public double getDienTich() {
		return dienTich;
	}
	public void setDienTich(double dienTich) {
		this.dienTich = dienTich;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}
	
	
	
}	
