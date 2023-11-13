package entitys;

import java.time.LocalDate;

public class KhachHang {
	private String maKhachHang,hoTenKhachHang,soCCCD,soDienThoai;
	private int diemTichLuy;
	private boolean gioiTinh;
	LoaiKhachHang loaiKhachHang;
	
	public KhachHang(String maKhachHang, String hoTenKhachHang, String soCCCD, String soDienThoai,
			int diemTichLuy, boolean gioiTinh, LoaiKhachHang loaiKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTenKhachHang = hoTenKhachHang;
		this.soCCCD = soCCCD;
		this.soDienThoai = soDienThoai;	
		this.diemTichLuy = diemTichLuy;
		this.gioiTinh = gioiTinh;
		this.loaiKhachHang = loaiKhachHang;
	}

	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KhachHang(String makh) {
		super();
		this.maKhachHang = makh;
	}
	public KhachHang(String makh,String ten) {
		super();
		this.maKhachHang = makh;
		this.hoTenKhachHang = ten;
	}
	public KhachHang(String makh,String ten,String sdt,int diem) {
		super();
		this.maKhachHang = makh;
		this.hoTenKhachHang = ten;
		this.soDienThoai = sdt;
		this.diemTichLuy = diem;
	}
	public KhachHang(String makh,String ten,String sdt) {
		super();
		this.maKhachHang = makh;
		this.hoTenKhachHang = ten;
		this.soDienThoai = sdt;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getHoTenKhachHang() {
		return hoTenKhachHang;
	}

	public void setHoTenKhachHang(String hoTenKhachHang) {
		this.hoTenKhachHang = hoTenKhachHang;
	}

	public String getSoCCCD() {
		return soCCCD;
	}

	public void setSoCCCD(String soCCCD) {
		this.soCCCD = soCCCD;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}


	public int getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	public boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public LoaiKhachHang getLoaiKhachHang() {
		return loaiKhachHang;
	}

	public void setLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}
	
	
}
