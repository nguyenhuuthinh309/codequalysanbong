package thinhnh.fpoly.myapp.csdl.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import thinhnh.fpoly.myapp.csdl.DTO.HoaDon;

@Dao
public interface DAO_HOADON {

    @Insert
    void insertHOADON(HoaDon hoaDon);
    @Update
    void updataHOADON(HoaDon trangThaiHoaDon);
    @Delete
    void deleteHOADON(HoaDon trangThaiHoaDon);

    @Query("select * from HoaDon")
    List<HoaDon> getAllHOADON();

    @Query("SELECT * FROM HoaDon WHERE id_trangthaihd= :tthd ")
    List<HoaDon> getabctthd(int tthd);
    @Query("SELECT * FROM HoaDon WHERE id_khunggio= :khunggio ")
    List<HoaDon> gettimkiemkhunggio(int khunggio);
    @Query("Select * from HoaDon Where ngaythue = :tenkhachhang")
    List<HoaDon> gettten(String tenkhachhang);
    @Query("SELECT COUNT(*) FROM HoaDon")
    int getCount();

    @Query("select * from HoaDon where ngaythue between :tuNgay and :denNgay")
    List<HoaDon> getDoanhThu(String tuNgay, String denNgay);


}
