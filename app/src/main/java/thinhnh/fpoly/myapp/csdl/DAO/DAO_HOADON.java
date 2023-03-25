package thinhnh.fpoly.myapp.csdl.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import thinhnh.fpoly.myapp.csdl.DTO.HoaDon;
import thinhnh.fpoly.myapp.csdl.DTO.TrangThaiHoaDon;

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
}
