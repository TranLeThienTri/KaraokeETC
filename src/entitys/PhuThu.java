package entitys;

import java.util.Objects;

public class PhuThu {
	private String maPhuThu, lyDoPhuThu;
	private double soTien;
	public PhuThu(String maPhuThu, String lyDoPhuThu, double soTien) {
		this.maPhuThu = maPhuThu;
		this.lyDoPhuThu = lyDoPhuThu;
		this.soTien = soTien;
	}
	public PhuThu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaPhuThu() {
		return maPhuThu;
	}
	public void setMaPhuThu(String maPhuThu) {
		this.maPhuThu = maPhuThu;
	}
	public String getLyDoPhuThu() {
		return lyDoPhuThu;
	}
	public void setLyDoPhuThu(String lyDoPhuThu) {
		this.lyDoPhuThu = lyDoPhuThu;
	}
	public double getSoTien() {
		return soTien;
	}
	public void setSoTien(double soTien) {
		this.soTien = soTien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhuThu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhuThu other = (PhuThu) obj;
		return Objects.equals(maPhuThu, other.maPhuThu);
	}
	
	
}
