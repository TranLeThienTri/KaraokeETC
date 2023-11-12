package entitys;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien, hoTenNhanVien, soCCCD,diaChi,sdt;
	private boolean gioiTinh, tinhTrang;
	private ChucVu chucVu;
	private LocalDate ngaySinh;
	
	public NhanVien(String maNhanVien, String hoTenNhanVien, String soCCCD, String diaChi, String sdt,
			boolean gioiTinh, ChucVu chucVu, LocalDate ngaySinh2, Boolean tinhTrang) {
		this.maNhanVien = maNhanVien;
		this.hoTenNhanVien = hoTenNhanVien;
		this.soCCCD = soCCCD;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
		this.chucVu = chucVu;
		this.ngaySinh = ngaySinh2;
		this.tinhTrang = tinhTrang;
	}
	
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String manv) {
		super();
		this.maNhanVien = manv;
	}
	public NhanVien(String manv,String ten) {
		super();
		this.maNhanVien = manv;
		this.hoTenNhanVien = ten;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getHoTenNhanVien() {
		return hoTenNhanVien;
	}
	public void setHoTenNhanVien(String hoTenNhanVien) {
		this.hoTenNhanVien = hoTenNhanVien;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public ChucVu getchucVu() {
		return chucVu;
	}
	public void setchucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}
	public String getSoCCCD() {
		return soCCCD;
	}
	public void setSoCCCD(String soCCCD) {
		this.soCCCD = soCCCD;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public boolean isTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
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
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}
	

}
