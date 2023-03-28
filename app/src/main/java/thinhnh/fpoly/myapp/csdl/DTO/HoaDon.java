package thinhnh.fpoly.myapp.csdl.DTO;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "HoaDon", foreignKeys = {
        @ForeignKey(entity = KhungGio.class, parentColumns = "id_khunggio", childColumns = "id_khunggio", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = San.class, parentColumns = "id_san", childColumns = "id_san", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = TrangThaiHoaDon.class, parentColumns = "id_trangthaihd", childColumns = "id_trangthaihd", onDelete = ForeignKey.CASCADE),})
public class HoaDon implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id_hoadon;
    String tenkh;
    @ColumnInfo(name = "id_san")
    int id_san;
    String tensan;
    String giasan;
    @ColumnInfo(name = "id_khunggio")
    int id_khunggio;
    String khunggio;

    int id_trangthaihd;
    String tentrangthai;

    int bong,nuoc,ao,tongtien;

    public HoaDon() {
    }

    public HoaDon(String tenkh, int id_san, String tensan, String giasan, int id_khunggio, String khunggio, int id_trangthaihd, String tentrangthai, int bong, int nuoc, int ao, int tongtien) {
        this.tenkh = tenkh;
        this.id_san = id_san;
        this.tensan = tensan;
        this.giasan = giasan;
        this.id_khunggio = id_khunggio;
        this.khunggio = khunggio;
        this.id_trangthaihd = id_trangthaihd;
        this.tentrangthai = tentrangthai;
        this.bong = bong;
        this.nuoc = nuoc;
        this.ao = ao;
        this.tongtien = tongtien;
    }

    public int getId_hoadon() {
        return id_hoadon;
    }

    public void setId_hoadon(int id_hoadon) {
        this.id_hoadon = id_hoadon;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
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

    public int getBong() {
        return bong;
    }

    public void setBong(int bong) {
        this.bong = bong;
    }

    public int getNuoc() {
        return nuoc;
    }

    public void setNuoc(int nuoc) {
        this.nuoc = nuoc;
    }

    public int getAo() {
        return ao;
    }

    public void setAo(int ao) {
        this.ao = ao;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}
