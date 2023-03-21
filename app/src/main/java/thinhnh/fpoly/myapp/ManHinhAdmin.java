package thinhnh.fpoly.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;
import thinhnh.fpoly.myapp.Fragment.FragmentChung;
import thinhnh.fpoly.myapp.Fragment.NguoiDung.DoiMKFragment;
import thinhnh.fpoly.myapp.Fragment.NguoiDung.ThongTinFragment;
import thinhnh.fpoly.myapp.Fragment.admin.KhungGioFragment;
import thinhnh.fpoly.myapp.Fragment.admin.TrangThaihdFragment;
import thinhnh.fpoly.myapp.Fragment.nhanvien.BaoCaoFragment;
import thinhnh.fpoly.myapp.Fragment.nhanvien.HoaDonFragment;
import thinhnh.fpoly.myapp.Fragment.nhanvien.TimKiemFragment;
import thinhnh.fpoly.myapp.Fragment.nhanvien.TrangThaiFragment;
import thinhnh.fpoly.myapp.Fragment.admin.DSDichVuFragment;
import thinhnh.fpoly.myapp.Fragment.admin.DSLoaiSanFragment;
import thinhnh.fpoly.myapp.Fragment.admin.DSNhanVienFragment;
import thinhnh.fpoly.myapp.Fragment.admin.DSSanFragment;
import thinhnh.fpoly.myapp.Fragment.admin.DanhSachTinhTrangfragment;
import thinhnh.fpoly.myapp.Fragment.thongke.ThongKeFragment;
import thinhnh.fpoly.myapp.csdl.DTO.HoaDon;
import thinhnh.fpoly.myapp.csdl.DTO.KhungGio;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;

public class ManHinhAdmin  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    KhungGio khungGio;
     CircleImageView imgNavAvata;
     TextView tvNavName;
     TextView tvNavChucvu;
     DrawerLayout drr;
     Toolbar toolBarMhAdmin, texttoolbar;

     NavigationView navL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_admin);




        drr = (DrawerLayout) findViewById(R.id.drr);
        toolBarMhAdmin = (Toolbar) findViewById(R.id.toolBar_mhAdmin);
        Bundle bundle = getIntent().getExtras();
        String permission = bundle.getString("value");
        //==================navi....
        setSupportActionBar(toolBarMhAdmin);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drr, toolBarMhAdmin, R.string.open_dr, R.string.close_dr);
        toggle.syncState();




        navL = findViewById(R.id.nav_l);
        tvNavName = navL.getHeaderView(0).findViewById(R.id.tv_nav_name);
        tvNavChucvu = navL.getHeaderView(0).findViewById(R.id.tv_nav_chucvu);
        imgNavAvata = navL.getHeaderView(0).findViewById(+R.id.img_nav_avata);
        navL.setNavigationItemSelectedListener(this);
        reFragment(FragmentChung.newInstance());





        if(permission.equalsIgnoreCase("ADMIN")){
            tvNavName.setText("Admin");
            tvNavChucvu.setText("Admin123@admin.com");

            Menu menu = navL.getMenu();
            menu.findItem(R.id.hoadon).setVisible(false);
            menu.findItem(R.id.baocao).setVisible(false);
            menu.findItem(R.id.timkiem).setVisible(false);
            menu.findItem(R.id.tt).setVisible(false);



        }else if(permission.equalsIgnoreCase("Nhân Viên")){
            tvNavName.setText("Nhân Viên");
            tvNavChucvu.setText("NhanVien123@admin.com");

            Menu menu = navL.getMenu();
            menu.findItem(R.id.dsnhanvien).setVisible(false);
            menu.findItem(R.id.dssan).setVisible(false);
            menu.findItem(R.id.dsloaisan).setVisible(false);
            menu.findItem(R.id.dsdichvu).setVisible(false);
            menu.findItem(R.id.dstinhtrangsan).setVisible(false);
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.dsnhanvien:
                reFragment(DSNhanVienFragment.newInstance());
                break;
            case R.id.dssan:
                reFragment(DSSanFragment.newInstance());
                break;
            case R.id.dsloaisan:
                reFragment(DSLoaiSanFragment.newInstance());
                break;
            case R.id.dsdichvu:
                reFragment(DSDichVuFragment.newInstance());
                break;
            case R.id.dstinhtrangsan:
                reFragment(DanhSachTinhTrangfragment.newInstance());
                break;
            case R.id.khunggio:
                reFragment(KhungGioFragment.newInstance());
                break;
            case R.id.trangthai:
                reFragment(TrangThaihdFragment.newInstance());
                break;

            case R.id.mDoanhThu:
                reFragment(ThongKeFragment.newInstance());
                break;

            case R.id.hoadon:
                reFragment(HoaDonFragment.newInstance());
                break;

            case R.id.baocao:
                reFragment(BaoCaoFragment.newInstance());
                break;

            case R.id.tt:
                reFragment(TrangThaiFragment.newInstance());
                break;

            case R.id.timkiem:
                reFragment(TimKiemFragment.newInstance());
                break;

            case R.id.thongtin:
                reFragment(ThongTinFragment.newInstance());
                break;



            case R.id.mDoiMatKhau:
                reFragment(DoiMKFragment.newInstance());
                break;

            case R.id.dangxuat:
                showDialogLogout();
                break;
        }drr.closeDrawer(navL);
        return true;
    }
    // hiển thị một số chứcc năng cho admin
//    String permission = spinner.getSelectedItem().toString();
//        if (permission.equalsIgnoreCase("ADMIN")){
//        Menu menu = navL.getMenu();
//        menu.findItem(R.id.mDoanhThu).setVisible(false);
//        menu.findItem(R.id.mTop10).setVisible(false);
//        menu.findItem(R.id.mtaotaikhoanthuthu).setVisible(false);
//    }

    private void showDialogLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhAdmin.this);
        builder.setTitle("Thông báo!");
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ManHinhAdmin.this, ManHinhLogin.class));
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void reFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_ly_mhAdmin, fragment);
        transaction.commit();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            if (drr.isDrawerOpen(navL)) {
                drr.closeDrawer(navL);
            } else finish();
        }
        return true;
    }



}