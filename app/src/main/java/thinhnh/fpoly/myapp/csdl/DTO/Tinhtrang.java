package thinhnh.fpoly.myapp.csdl.DTO;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "TinhTrang",foreignKeys = {
        @ForeignKey(entity = San.class,parentColumns = "id_san",childColumns = "id_san",onDelete = ForeignKey.CASCADE)
})

public class Tinhtrang {
    @PrimaryKey(autoGenerate = true)

    int id_tinhtrang;
    String tinhtrang;
    String hoatdong;
    int id_san;
    String san;
    public Tinhtrang() {
    }

    public Tinhtrang(String tinhtrang, String hoatdong, int san) {
        this.tinhtrang = tinhtrang;
        this.hoatdong = hoatdong;
        this.id_san = san;
    }

    public Tinhtrang(String tinhtrang, String hoatdong, int id_san, String san) {
        this.tinhtrang = tinhtrang;
        this.hoatdong = hoatdong;
        this.id_san = id_san;
        this.san = san;
    }


    public String getSan() {
        return san;
    }

    public void setSan(String san) {
        this.san = san;
    }

    public int getId_tinhtrang() {
        return id_tinhtrang;
    }

    public void setId_tinhtrang(int id_tinhtrang) {
        this.id_tinhtrang = id_tinhtrang;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getHoatdong() {
        return hoatdong;
    }

    public void setHoatdong(String hoatdong) {
        this.hoatdong = hoatdong;
    }

    public int getId_san() {
        return id_san;
    }

    public void setId_san(int id_san) {
        this.id_san = id_san;
    }
    }
