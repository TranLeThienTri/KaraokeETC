USE [master]
GO
/****** Object:  Database [DBQuanLyKaraoke]    Script Date: 10-Nov-23 6:07:10 PM ******/
CREATE DATABASE [DBQuanLyKaraoke]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DBQuanLyKaraoke', FILENAME = N'C:\Java\KaraokeETC\QUANLY_KARAOKE_ETC\src\data\DBQuanLyKaraoke.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DBQuanLyKaraoke_log', FILENAME = N'C:\Java\KaraokeETC\QUANLY_KARAOKE_ETC\src\data\DBQuanLyKaraoke_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [DBQuanLyKaraoke] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DBQuanLyKaraoke].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DBQuanLyKaraoke] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET ARITHABORT OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET  ENABLE_BROKER 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET  MULTI_USER 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DBQuanLyKaraoke] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [DBQuanLyKaraoke] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [DBQuanLyKaraoke] SET QUERY_STORE = ON
GO
ALTER DATABASE [DBQuanLyKaraoke] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [DBQuanLyKaraoke]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[maHoaDonPhong] [nvarchar](10) NOT NULL,
	[maDichVu] [nvarchar](10) NOT NULL,
	[soLuongDichVu] [int] NULL,
	[maPhuThu] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_ChiTiet] PRIMARY KEY CLUSTERED 
(
	[maPhuThu] ASC,
	[maDichVu] ASC,
	[maHoaDonPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[maChucVu] [nvarchar](10) NOT NULL,
	[tenChucVu] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[maChucVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DichVu]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DichVu](
	[maDichVu] [nvarchar](10) NOT NULL,
	[tenDichVu] [nvarchar](50) NULL,
	[maLoaiDichVu] [nvarchar](10) NOT NULL,
	[soLuongTon] [int] NULL,
	[donGia] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[maDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonPhong]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonPhong](
	[maHoaDon] [nvarchar](10) NOT NULL,
	[ngayLapHoaDon] [datetime] NULL,
	[gioBatDauThuePhong] [time](7) NULL,
	[gioTraPhong] [time](7) NULL,
	[ngayDat] [datetime] NULL,
	[gioDat] [datetime] NULL,
	[maKhachHang] [nvarchar](10) NOT NULL,
	[maNhanVien] [nvarchar](10) NOT NULL,
	[maLoaiHoaDon] [nvarchar](10) NOT NULL,
	[maPhong] [nvarchar](10) NOT NULL,
	[thanhTien] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [nvarchar](10) NOT NULL,
	[tenKhachHang] [nvarchar](30) NOT NULL,
	[soCCCD] [nvarchar](12) NULL,
	[gioiTinh] [bit] NOT NULL,
	[soDienThoai] [nvarchar](11) NOT NULL,
	[diemTichLuy] [int] NULL,
	[maLoaiKhachHang] [nvarchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiDichVu]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiDichVu](
	[maLoaiDichVu] [nvarchar](10) NOT NULL,
	[tenLoaiDichVu] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[maLoaiDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[loaiHoaDon]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[loaiHoaDon](
	[maLoaiHoaDon] [nvarchar](10) NOT NULL,
	[tenLoaiHoaDon] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[maLoaiHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiKhachHang]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiKhachHang](
	[maLoaiKhachHang] [nvarchar](10) NOT NULL,
	[tenLoaiKhachHang] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[maLoaiKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiPhong]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiPhong](
	[maLoaiPhong] [nvarchar](10) NOT NULL,
	[tenLoaiPhong] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[maLoaiPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [nvarchar](10) NOT NULL,
	[tenNhanVien] [nvarchar](30) NOT NULL,
	[maChucVu] [nvarchar](10) NOT NULL,
	[gioiTinh] [bit] NULL,
	[ngaySinh] [date] NULL,
	[diaChi] [nvarchar](200) NULL,
	[sdt] [nvarchar](11) NOT NULL,
	[cccd] [nvarchar](12) NULL,
	[tinhTrang] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[maPhong] [nvarchar](10) NOT NULL,
	[maLoaiPhong] [nvarchar](10) NOT NULL,
	[sucChua] [int] NULL,
	[giaPhong] [float] NOT NULL,
	[maTinhTrang] [nvarchar](10) NOT NULL,
	[dienTich] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhuThu]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhuThu](
	[maPhuThu] [nvarchar](10) NOT NULL,
	[lyDoPhuThu] [nvarchar](50) NOT NULL,
	[soTien] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[maPhuThu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[maNhanVien] [nvarchar](10) NOT NULL,
	[matKhau] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TinhTrangPhong]    Script Date: 10-Nov-23 6:07:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TinhTrangPhong](
	[maTinhTrang] [nvarchar](10) NOT NULL,
	[tenTinhTrang] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maTinhTrang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ChucVu] ([maChucVu], [tenChucVu]) VALUES (N'NV', N'Nhân viên')
INSERT [dbo].[ChucVu] ([maChucVu], [tenChucVu]) VALUES (N'QL', N'Quản lí')
GO
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [soLuongTon], [donGia]) VALUES (N'DA001', N'Khô bò', N'FOOD', 10, 100000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [soLuongTon], [donGia]) VALUES (N'DA002', N'Khô gà', N'FOOD', 10, 80000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [soLuongTon], [donGia]) VALUES (N'NU001', N'Heineken chai', N'WATER', 96, 450000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [soLuongTon], [donGia]) VALUES (N'NU002', N'Heineken lon', N'WATER', 96, 450000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [soLuongTon], [donGia]) VALUES (N'NU003', N'Heineken Silver chai', N'WATER', 96, 470000)
INSERT [dbo].[DichVu] ([maDichVu], [tenDichVu], [maLoaiDichVu], [soLuongTon], [donGia]) VALUES (N'NU004', N'Heineken Silver lon', N'WATER', 96, 470000)
GO
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soCCCD], [gioiTinh], [soDienThoai], [diemTichLuy], [maLoaiKhachHang]) VALUES (N'KH001', N'Trần Lê Thiên Trí', N'032165423152', 1, N'0321654987', 0, N'VIP')
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [soCCCD], [gioiTinh], [soDienThoai], [diemTichLuy], [maLoaiKhachHang]) VALUES (N'KH002', N'Lê Trọng Thiện', N'032165498712', 1, N'0215487963', 0, N'NOR')
GO
INSERT [dbo].[LoaiDichVu] ([maLoaiDichVu], [tenLoaiDichVu]) VALUES (N'FOOD', N'Thực phẩm')
INSERT [dbo].[LoaiDichVu] ([maLoaiDichVu], [tenLoaiDichVu]) VALUES (N'WATER', N'Nước uống')
GO
INSERT [dbo].[loaiHoaDon] ([maLoaiHoaDon], [tenLoaiHoaDon]) VALUES (N'HDD', N'Hóa đơn đặt')
INSERT [dbo].[loaiHoaDon] ([maLoaiHoaDon], [tenLoaiHoaDon]) VALUES (N'HDT', N'Hóa đơn thuê')
GO
INSERT [dbo].[LoaiKhachHang] ([maLoaiKhachHang], [tenLoaiKhachHang]) VALUES (N'NOR', N'KH Thường')
INSERT [dbo].[LoaiKhachHang] ([maLoaiKhachHang], [tenLoaiKhachHang]) VALUES (N'VIP', N'KH VIP')
GO
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong]) VALUES (N'NOR', N'Phòng thường')
INSERT [dbo].[LoaiPhong] ([maLoaiPhong], [tenLoaiPhong]) VALUES (N'VIP', N'Phòng VIP')
GO
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [maChucVu], [gioiTinh], [ngaySinh], [diaChi], [sdt], [cccd], [tinhTrang]) VALUES (N'NV001', N'Trương Công Duy', N'QL', 1, CAST(N'2003-05-11' AS Date), N'Gia Lai', N'0382173105', N'064203001952', 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [maChucVu], [gioiTinh], [ngaySinh], [diaChi], [sdt], [cccd], [tinhTrang]) VALUES (N'NV002', N'Trần Quốc Huy', N'NV', 1, CAST(N'2003-04-15' AS Date), N'Tây Ninh', N'0325658585', N'065655445213', 1)
GO
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [sucChua], [giaPhong], [maTinhTrang], [dienTich]) VALUES (N'MP001', N'VIP', 10, 500000, N'EMPT', 50)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [sucChua], [giaPhong], [maTinhTrang], [dienTich]) VALUES (N'MP002', N'NOR', 20, 200000, N'EMPT', 80)
INSERT [dbo].[Phong] ([maPhong], [maLoaiPhong], [sucChua], [giaPhong], [maTinhTrang], [dienTich]) VALUES (N'MP003', N'NOR', 10, 150000, N'EMPT', 30)
GO
INSERT [dbo].[PhuThu] ([maPhuThu], [lyDoPhuThu], [soTien]) VALUES (N'EVEN', N'Lễ', 70000)
INSERT [dbo].[PhuThu] ([maPhuThu], [lyDoPhuThu], [soTien]) VALUES (N'WEEK', N'Cuối tuần', 50000)
GO
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV001', N'nv001')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV002', N'nv002')
GO
INSERT [dbo].[TinhTrangPhong] ([maTinhTrang], [tenTinhTrang]) VALUES (N'BOOK', N'Phòng đã đặt')
INSERT [dbo].[TinhTrangPhong] ([maTinhTrang], [tenTinhTrang]) VALUES (N'EMPT', N'Phòng trống')
INSERT [dbo].[TinhTrangPhong] ([maTinhTrang], [tenTinhTrang]) VALUES (N'RENT ', N'Phòng đang thuê')
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD FOREIGN KEY([maDichVu])
REFERENCES [dbo].[DichVu] ([maDichVu])
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD FOREIGN KEY([maHoaDonPhong])
REFERENCES [dbo].[HoaDonPhong] ([maHoaDon])
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD FOREIGN KEY([maPhuThu])
REFERENCES [dbo].[PhuThu] ([maPhuThu])
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD FOREIGN KEY([maLoaiDichVu])
REFERENCES [dbo].[LoaiDichVu] ([maLoaiDichVu])
GO
ALTER TABLE [dbo].[HoaDonPhong]  WITH CHECK ADD FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[HoaDonPhong]  WITH CHECK ADD FOREIGN KEY([maLoaiHoaDon])
REFERENCES [dbo].[loaiHoaDon] ([maLoaiHoaDon])
GO
ALTER TABLE [dbo].[HoaDonPhong]  WITH CHECK ADD FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDonPhong]  WITH CHECK ADD FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD FOREIGN KEY([maLoaiKhachHang])
REFERENCES [dbo].[LoaiKhachHang] ([maLoaiKhachHang])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([maChucVu])
REFERENCES [dbo].[ChucVu] ([maChucVu])
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD FOREIGN KEY([maLoaiPhong])
REFERENCES [dbo].[LoaiPhong] ([maLoaiPhong])
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD FOREIGN KEY([maTinhTrang])
REFERENCES [dbo].[TinhTrangPhong] ([maTinhTrang])
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
/****** Object:  StoredProcedure [dbo].[getDSNV]    Script Date: 10-Nov-23 6:07:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[getDSNV] 
as
select *
from NhanVien
GO
/****** Object:  StoredProcedure [dbo].[getNVTheoMa]    Script Date: 10-Nov-23 6:07:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[getNVTheoMa] @manv nvarchar(5)
as
select *
from NhanVien
where maNhanVien = @manv
GO
/****** Object:  StoredProcedure [dbo].[getTaiKhoan]    Script Date: 10-Nov-23 6:07:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[getTaiKhoan] 
as
select *
from TaiKhoan
GO
/****** Object:  StoredProcedure [dbo].[getTaiKhoanTheoMa]    Script Date: 10-Nov-23 6:07:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[getTaiKhoanTheoMa] @matk nvarchar(5)
as
select *
from TaiKhoan
where maNhanVien = @matk
GO
/****** Object:  StoredProcedure [dbo].[themNhanVien]    Script Date: 10-Nov-23 6:07:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[themNhanVien] @manv nvarchar(5), @hoten nvarchar(30), 
@namsinh date, @sdt nvarchar(10),@cccd nvarchar(12), 
@diachi nvarchar(50), @gt bit, @chucvu nvarchar(10),
@tinhtrang bit
as
insert into [dbo].[NhanVien]
values(@manv,@hoten,@chucvu,@gt,@namsinh,@diachi,@sdt,@cccd,@tinhtrang)
GO
/****** Object:  StoredProcedure [dbo].[themTaiKhoan]    Script Date: 10-Nov-23 6:07:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[themTaiKhoan] @matk nvarchar(5), @mk nvarchar(30)
as
insert into [dbo].[TaiKhoan]
values(@matk,@mk)
GO
USE [master]
GO
ALTER DATABASE [DBQuanLyKaraoke] SET  READ_WRITE 
GO

-- Get DS Dịch vụ
create proc getDSDichVu 
as 
select * from DichVu
exec getDSDichVu

--Them Dịch vụ


create proc themDichVu (@madv nvarchar(10), @tendv nvarchar(30), 
@loaidichvu nvarchar(10),@slt int, @dongia float)
as
insert into DichVu
values(@madv,@tendv,@loaidichvu,@slt, @dongia)
GO

exec themDichVu 'DA003', N'Khô bò', FOOD, 10, 10000
create proc getDSDVTheoLoai @ma nvarchar(10)
as
select *
from DichVu
where maLoaiDichVu = @ma
exec getDSDVTheoLoai 'FOOD'
-- Sửa dịch vụ
go
create proc SuaDichVu (@ma nvarchar(10), @tendichvu nvarchar(10),@maLoaiDichVu nvarchar(10), @soLuongTon int , @donGia float)
as
begin
update DichVu
set 
tenDichVu = @tendichvu,
maLoaiDichVu = @maLoaiDichVu,
soLuongTon = @soLuongTon,
donGia = @donGia
where maDichVu = @ma
end;
GO
exec SuaDichVu 'DA002', N'Thịt gà', N'WATER', 15, 1

select * from DichVu
