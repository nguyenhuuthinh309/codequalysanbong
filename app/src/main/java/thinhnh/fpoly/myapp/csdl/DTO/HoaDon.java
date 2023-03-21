package thinhnh.fpoly.myapp.csdl.DTO;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "HoaDon", foreignKeys = {
        @ForeignKey(entity = LoaiSan.class, parentColumns = "id_loaisan", childColumns = "id_loaisan", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = San.class, parentColumns = "id_san", childColumns = "id_san", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = KhungGio.class, parentColumns = "id_khunggio", childColumns = "id_khunggio", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = DichVu.class, parentColumns = "id_dv", childColumns = "id_dv", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = TrangThaiHoaDon.class, parentColumns = "id_trangthaihd", childColumns = "id_trangthaihd", onDelete = ForeignKey.CASCADE),})
public class HoaDon {
    @PrimaryKey(autoGenerate = true)
    int id_hoadon;
    String tenkh;
    int id_loaisan;
    String tenloai;
    int id_san;
    String tensan;
    String giasan;
    int id_khunggio;
    String khunggio;
    int id_dv;
    String tendv;
    String giadv;
    int id_trangthaihd;
    String tentrangthai;
    String tongtien;

    public HoaDon() {
    }

    public HoaDon(String tenkh, int id_loaisan, String tenloai, int id_san, String tensan, String giasan, int id_khunggio, String khunggio, int id_dv, String tendv, String giadv, int id_trangthaihd, String tentrangthai, String tongtien) {
        this.tenkh = tenkh;
        this.id_loaisan = id_loaisan;
        this.tenloai = tenloai;
        this.id_san = id_san;
        this.tensan = tensan;
        this.giasan = giasan;
        this.id_khunggio = id_khunggio;
        this.khunggio = khunggio;
        this.id_dv = id_dv;
        this.tendv = tendv;
        this.giadv = giadv;
        this.id_trangthaihd = id_trangthaihd;
        this.tentrangthai = tentrangthai;
        this.tongtien = tongtien;
    }

    public int getId_hoadon() {
        return id_hoadon;
    }

    public void setId_hoadon(int id_hoadon) {
        this.id_hoadon = id_hoadon;
    }

    public int getId_loaisan() {
        return id_loaisan;
    }

    public void setId_loaisan(int id_loaisan) {
        this.id_loaisan = id_loaisan;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getId_san() {
        return id_san;
    }

    public void setId_san(int id_san) {
        this.id_san = id_san;
    }

    public String getTensan() {
        return tensan;
    }

    public void setTensan(String tensan) {
        this.tensan = tensan;
    }

    public String getGiasan() {
        return giasan;
    }

    public void setGiasan(String giasan) {
        this.giasan = giasan;
    }

    public int getId_khunggio() {
        return id_khunggio;
    }

    public void setId_khunggio(int id_khunggio) {
        this.id_khunggio = id_khunggio;
    }

    public String getKhunggio() {
        return khunggio;
    }

    public void setKhunggio(String khunggio) {
        this.khunggio = khunggio;
    }

    public int getId_dv() {
        return id_dv;
    }

    public void setId_dv(int id_dv) {
        this.id_dv = id_dv;
    }

    public String getTendv() {
        return tendv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public String getGiadv() {
        return giadv;
    }

    public void setGiadv(String giadv) {
        this.giadv = giadv;
    }

    public int getId_trangthaihd() {
        return id_trangthaihd;
    }

    public void setId_trangthaihd(int id_trangthaihd) {
        this.id_trangthaihd = id_trangthaihd;
    }

    public String getTentrangthai() {
        return tentrangthai;
    }

    public void setTentrangthai(String tentrangthai) {
        this.tentrangthai = tentrangthai;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }
}
