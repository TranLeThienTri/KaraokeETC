package entitys;

import java.util.Objects;

public class LoaiHoaDon {
	private String maLoaiHoaDon, tenLoaiHoaDon;

	public LoaiHoaDon(String maLoaiHoaDon, String tenLoaiHoaDon) {
		this.maLoaiHoaDon = maLoaiHoaDon;
		this.tenLoaiHoaDon = tenLoaiHoaDon;
	}

	public LoaiHoaDon(String ma) {
		super();
		this.maLoaiHoaDon = ma;
	}

	public String getMaLoaiHoaDon() {
		return maLoaiHoaDon;
	}

	public void setMaLoaiHoaDon(String maLoaiHoaDon) {
		this.maLoaiHoaDon = maLoaiHoaDon;
	}

	public String getTenLoaiHoaDon() {
		return tenLoaiHoaDon;
	}

	public void setTenLoaiHoaDon(String tenLoaiHoaDon) {
		this.tenLoaiHoaDon = tenLoaiHoaDon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiHoaDon other = (LoaiHoaDon) obj;
		return Objects.equals(maLoaiHoaDon, other.maLoaiHoaDon);
	}
	
	
}
