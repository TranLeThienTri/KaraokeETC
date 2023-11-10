package entitys;

import java.util.Objects;

public class TaiKhoan {
	private String maNhanVien;
	private String matKhau;
	public TaiKhoan(String maNhanVien, String matKhau) {
		
		this.maNhanVien = maNhanVien;
		this.matKhau = matKhau;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}
	
	
	
}
