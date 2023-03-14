package thinhnh.fpoly.myapp.csdl.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import thinhnh.fpoly.myapp.csdl.DAO.DAO_DV;
import thinhnh.fpoly.myapp.csdl.DAO.DAO_LOAISAN;
import thinhnh.fpoly.myapp.csdl.DAO.DAO_NV;
import thinhnh.fpoly.myapp.csdl.DAO.DAO_SAN;
import thinhnh.fpoly.myapp.csdl.DTO.DichVu;
import thinhnh.fpoly.myapp.csdl.DTO.LoaiSan;
import thinhnh.fpoly.myapp.csdl.DTO.NhanVien;
import thinhnh.fpoly.myapp.csdl.DTO.San;


@Database(entities = {NhanVien.class, LoaiSan.class, San.class, DichVu.class}, version = 1)
public abstract class DataBaSe extends RoomDatabase {
    private static final String DATABASE_NAME = "db.db1";
    private static DataBaSe instance;
    public static synchronized DataBaSe getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), DataBaSe.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract DAO_DV dao_dv();
    public abstract DAO_SAN dao_san();
    public abstract DAO_LOAISAN dao_loaisan();

    public abstract DAO_NV dao_nv();


}