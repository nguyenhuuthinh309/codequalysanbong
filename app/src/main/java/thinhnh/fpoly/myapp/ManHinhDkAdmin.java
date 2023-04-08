package thinhnh.fpoly.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;

import thinhnh.fpoly.myapp.csdl.DAO.DAO_Admin;
import thinhnh.fpoly.myapp.csdl.DTO.Admin;
import thinhnh.fpoly.myapp.csdl.DTO.NhanVien;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;

public class ManHinhDkAdmin extends AppCompatActivity {

     LinearLayout ThongTinDK;
     TextInputEditText dkemail;
     TextInputEditText dkmatkhau;
     TextInputEditText dkrematkhau;
     TextInputEditText dkhoten;
     TextInputEditText dktensanbong;
     TextInputEditText dkkiachi;
     LinearLayout linlayoutNote;
     Button btnMhDkDangky, btnhuydk;
 Admin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dk_admin);

        ThongTinDK = (LinearLayout) findViewById(R.id.ThongTinDK);
        dkemail = (TextInputEditText) findViewById(R.id.dkemail);
        dkmatkhau = (TextInputEditText) findViewById(R.id.dkmatkhau);
        dkrematkhau = (TextInputEditText) findViewById(R.id.dkrematkhau);
        dkhoten = (TextInputEditText) findViewById(R.id.dkhoten);
        dktensanbong = (TextInputEditText) findViewById(R.id.dktensanbong);
        dkkiachi = (TextInputEditText) findViewById(R.id.dkkiachi);
        linlayoutNote = (LinearLayout) findViewById(R.id.linlayout_note);
        btnMhDkDangky = (Button) findViewById(R.id.btn_mhDk_dangky);
        btnhuydk = findViewById(R.id.btn_huy_dangky);


        btnMhDkDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaill = dkemail.getText().toString();
                String matkhauu = dkmatkhau.getText().toString();
                String hotenn = dkhoten.getText().toString();
                String tensann = dktensanbong.getText().toString();
                String diachii = dkkiachi.getText().toString();
                admin = new Admin(emaill,matkhauu,hotenn,tensann,diachii);
                //Add hv vào database
                DataBaSe.getInstance(ManHinhDkAdmin.this).dao_admin().insertad(admin);
            }
        });

        btnhuydk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}