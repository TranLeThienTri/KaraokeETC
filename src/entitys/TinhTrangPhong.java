package entitys;

import java.util.Objects;

public class TinhTrangPhong {
	private String maTinhTrangPhong, tenTinhTrangPhong;


	public TinhTrangPhong(String maTinhTrangPhong) {
		this.maTinhTrangPhong = maTinhTrangPhong;
	}
	
	public TinhTrangPhong(String maTinhTrangPhong, String tenTinhTrangPhong) {
		this.maTinhTrangPhong = maTinhTrangPhong;
		this.tenTinhTrangPhong = tenTinhTrangPhong;
	}

	public String getMaTinhTrangPhong() {
		return maTinhTrangPhong;
	}

	public void setMaTinhTrangPhong(String maTinhTrangPhong) {
		this.maTinhTrangPhong = maTinhTrangPhong;
	}

	public String getTenTinhTrangPhong() {
		return tenTinhTrangPhong;
	}

	public void setTenTinhTrangPhong(String tenTinhTrangPhong) {
		this.tenTinhTrangPhong = tenTinhTrangPhong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTinhTrangPhong, tenTinhTrangPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TinhTrangPhong other = (TinhTrangPhong) obj;
		return Objects.equals(maTinhTrangPhong, other.maTinhTrangPhong)
				&& Objects.equals(tenTinhTrangPhong, other.tenTinhTrangPhong);
	}

	
	
	
}
