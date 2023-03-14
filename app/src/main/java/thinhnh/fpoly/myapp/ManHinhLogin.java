package thinhnh.fpoly.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import thinhnh.fpoly.myapp.csdl.DTO.NhanVien;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;

public class ManHinhLogin extends AppCompatActivity {

    private Button btn_dangNhap;
    CheckBox ckluuMK;
    private TextInputEditText edUser_login,edPass_login;
    private Spinner spinner;
    ArrayList<NhanVien> listCheckNV;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_login);

        spinner = findViewById(R.id.spin_mhlogin_vaitro);
        btn_dangNhap = findViewById(R.id.btn_mhlogin_dangnhap);
        edUser_login = findViewById(R.id.txt_edt_mhlogin_username);
        edPass_login = findViewById(R.id.txt_edt_mhlogin_pass);
        ckluuMK=findViewById(R.id.chk_luumk);
        // Lấy mật khẩu
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        edUser_login.setText(sharedPreferences.getString("user_name",""));
        edPass_login.setText(sharedPreferences.getString("pass",""));
        ckluuMK.setChecked(sharedPreferences.getBoolean("ck",false));
        //set item cho spiner vai trò
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Vai_tro, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        int pos = adapter.getPosition(sharedPreferences.getString("tk",""));
        spinner.setSelection(pos);


        btn_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    String permission = spinner.getSelectedItem().toString();
                    Intent intent = new Intent(ManHinhLogin.this, ManHinhAdmin.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("value", permission);
                    intent.putExtras(bundle);
                    if(permission.equalsIgnoreCase("ADMIN")){
                        if(edUser_login.getText().toString().trim().equalsIgnoreCase("1") && edPass_login.getText().toString().trim().equalsIgnoreCase("1")){
                            if (ckluuMK.isChecked()){
                                editor.putString("user_name",edUser_login.getText().toString());
                                editor.putString("pass",edPass_login.getText().toString());
                                editor.putString("tk",permission);
                                editor.putBoolean("ck",true);
                                editor.commit();
                            }else {
                                editor.remove("user_name");
                                editor.remove("pass");
                                editor.remove("tk");
                                editor.remove("ck");
                                editor.commit();
                            }
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(ManHinhLogin.this, "login thất bại!!!", Toast.LENGTH_SHORT).show();
                        }



                    }
                    if(permission.equals("Nhân Viên")){
                        String user = edUser_login.getText().toString();
                        String pass = edPass_login.getText().toString();
                        listCheckNV = new ArrayList<>();
                        listCheckNV = (ArrayList<NhanVien>) DataBaSe.getInstance(ManHinhLogin.this).dao_nv().CheckLogin(user, pass);
                        if(listCheckNV.size() == 1){
                            int id_nv = listCheckNV.get(0).getId_NV();
                            String tk_nv = listCheckNV.get(0).getTk_NV();
                            String ten_nv = listCheckNV.get(0).getTen_NV();
                            String sdt = listCheckNV.get(0).getSdt_NV();
                            String cccd = listCheckNV.get(0).getCccd_NV();
                            bundle.putInt("idnv",id_nv);
                            bundle.putString("tknv", tk_nv);
                            bundle.putString("tennv", ten_nv);
                            bundle.putString("sdtnv",sdt);
                            bundle.putString("cccdnv", cccd);
                            intent.putExtras(bundle);
                            Toast.makeText(ManHinhLogin.this, "Đăng nhập thành công nhân viên", Toast.LENGTH_SHORT).show();
                            if (ckluuMK.isChecked()){
                                editor.putString("user_name",edUser_login.getText().toString());
                                editor.putString("pass",edPass_login.getText().toString());
                                editor.putString("tk",permission);
                                editor.putBoolean("ck",true);
                                editor.commit();
                            }else {
                                editor.remove("user_name");
                                editor.remove("pass");
                                editor.remove("tk");
                                editor.remove("ck");
                                editor.commit();
                            }
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(ManHinhLogin.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    Log.d("zzz", "onClick: " + permission);

            }
        });
    }
}