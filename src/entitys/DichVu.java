package entitys;

import java.util.Objects;

public class DichVu {
	private String maDichVu, tenDichVu;
	private LoaiDichVu loaiDichVu;
	private int soLuongTon;
	private double donGia;

	public DichVu(String maDichVu, String tenDichVu, LoaiDichVu loaiDichVu, int soLuongTon, double donGia) {
		super();
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.loaiDichVu = loaiDichVu;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
	}
	public DichVu(String maDichVu) {
		super();
		this.maDichVu = maDichVu;
	}


	public DichVu(String maDichVu, String tenDichVu) {
		super();
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
	}

	public String getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public LoaiDichVu getloaiDichVu() {
		return loaiDichVu;
	}

	public void setloaiDichVu(LoaiDichVu loaiDichVu) {
		this.loaiDichVu = loaiDichVu;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDichVu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDichVu, other.maDichVu);
	}

}
