package thinhnh.fpoly.myapp.csdl.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import thinhnh.fpoly.myapp.csdl.DTO.Tinhtrang;

@Dao
public interface DAO_TINHTRANG {
    @Insert
    void insertTinhtrang(Tinhtrang tinhtrang);
    @Delete
    void deleteTinhTrang(Tinhtrang tinhtrang);
    @Update
    void updateTinhtrang(Tinhtrang tinhtrang);

    @Query("select * from TinhTrang")
    List<Tinhtrang> getAllTT();
    @Query("select *from TinhTrang where id_tinhtrang = :id")
    List<Tinhtrang> getList(int id);
}
