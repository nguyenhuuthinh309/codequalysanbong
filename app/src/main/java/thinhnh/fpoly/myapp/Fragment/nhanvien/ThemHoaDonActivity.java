package thinhnh.fpoly.myapp.Fragment.nhanvien;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

import thinhnh.fpoly.myapp.R;
import thinhnh.fpoly.myapp.adapter.AdapterListView_HoaDon;
import thinhnh.fpoly.myapp.csdl.DTO.HoaDon;
import thinhnh.fpoly.myapp.csdl.DTO.KhungGio;
import thinhnh.fpoly.myapp.csdl.DTO.San;
import thinhnh.fpoly.myapp.csdl.DTO.TrangThaiHoaDon;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;

public class ThemHoaDonActivity extends AppCompatActivity {


    HoaDon hd;
     TextInputEditText tenkh;
     Spinner spnkhunggio;
     TextView giakhunggio;
     Spinner spntensan;
     TextView giasan1;
     EditText edsoluongbong;
     TextView tvgianbong;
     EditText edsoluongnuoc;
     TextView tvgianuoc;
     EditText edsoluongao;
     TextView tvgiaao;
     Button tongtienbutton;
     TextView texttongtien;
     Spinner spntrangthai;
     Button btnAddhdd;
     Button btnHuyAddhdd;
     ImageView timkiemhoadon;

    ArrayList<San> listsan = new ArrayList<>();
    ArrayList<KhungGio> listkhunggio = new ArrayList<>();
    ArrayList<TrangThaiHoaDon> listtthd = new ArrayList<>();

    ArrayList<HoaDon> listhoadon = new ArrayList<>();

    AdapterListView_HoaDon adapterListView_hoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don);



        tenkh = (TextInputEditText) findViewById(R.id.tenkh);
        spnkhunggio = (Spinner) findViewById(R.id.spnkhunggio);
        timkiemhoadon = (ImageView) findViewById(R.id.timkiemhoadon);
        spntensan = (Spinner) findViewById(R.id.spntensan);
        giasan1 = (TextView) findViewById(R.id.giasan1);
        edsoluongbong = (EditText) findViewById(R.id.edsoluongbong);
        tvgianbong = (TextView) findViewById(R.id.tvgianbong);
        edsoluongnuoc = (EditText) findViewById(R.id.edsoluongnuoc);
        tvgianuoc = (TextView) findViewById(R.id.tvgianuoc);
        edsoluongao = (EditText) findViewById(R.id.edsoluongao);
        tvgiaao = (TextView) findViewById(R.id.tvgiaao);
        tongtienbutton = (Button) findViewById(R.id.tongtien);
        texttongtien = (TextView) findViewById(R.id.texttongtien);
        spntrangthai = (Spinner) findViewById(R.id.spntrangthai);
        btnAddhdd = (Button) findViewById(R.id.btnAddhdd);
        btnHuyAddhdd = (Button) findViewById(R.id.btnHuyAddhdd);


        AdapterListView_HoaDon adapterListView_hoaDon;



        SimpleAdapter simpleAdapter1 = new SimpleAdapter(this, getDSSan(), android.R.layout.simple_list_item_1, new String[]{"tensan"}, new int[]{android.R.id.text1});
        spntensan.setAdapter(simpleAdapter1);

        SimpleAdapter simpleAdapter2 = new SimpleAdapter(this, getDSKhungGio(), android.R.layout.simple_list_item_1, new String[]{"khunggio"}, new int[]{android.R.id.text1});
        spnkhunggio.setAdapter(simpleAdapter2);


        SimpleAdapter simpleAdapter4 = new SimpleAdapter(this, getDSTTHD(), android.R.layout.simple_list_item_1, new String[]{"tentthd"}, new int[]{android.R.id.text1});
        spntrangthai.setAdapter(simpleAdapter4);

        timkiemhoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> hs2 = (HashMap<String, Object>) spnkhunggio.getSelectedItem();
                int makg = (int) hs2.get("makhunggio");

                String khunggio = (String) hs2.get("khunggio");
            if (makg ==1) {

                Toast.makeText(ThemHoaDonActivity.this, "abdddc", Toast.LENGTH_SHORT).show();
            }

            }
        });


        tongtienbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ThemHoaDonActivity.this, "thinh test", Toast.LENGTH_SHORT).show();
                String edsoluonggao = edsoluongao.getText().toString();
                int so11 = Integer.parseInt(edsoluonggao);
                String edsoluonggbong = edsoluongbong.getText().toString();
                int so22 = Integer.parseInt(edsoluonggbong);
                String edsoluonggnuoc = edsoluongnuoc.getText().toString();
                int so33 = Integer.parseInt(edsoluonggnuoc);

                String giaao = String.valueOf(5);
                int so1 = Integer.parseInt(giaao);
                String giabong = String.valueOf(5);
                int so2 = Integer.parseInt(giabong);
                String gianuoc = String.valueOf(10);
                int so3 = Integer.parseInt(gianuoc);

                int tich = so1 *so11;
                int tich2 = so2 *so22;
                int tich3 = so3 *so33;

                HashMap<String,Object> hs1 = (HashMap<String, Object>) spntensan.getSelectedItem();
                int masan = (int) hs1.get("masan");
                String tensan = (String) hs1.get("tensan");
                String giasan = (String) hs1.get("giasan");
                giasan1.setText(giasan);
                int giasan1 = Integer.parseInt(giasan);

            int    tongtientatca1 = tich + tich2 + tich3 + giasan1;
                texttongtien.setText(String.valueOf(tongtientatca1));


            }
        });

        btnAddhdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenkh1 = tenkh.getText().toString();

                String edsoluonggao = edsoluongao.getText().toString();
                int so11 = Integer.parseInt(edsoluonggao);
                String edsoluonggbong = edsoluongbong.getText().toString();
                int so22 = Integer.parseInt(edsoluonggbong);
                String edsoluonggnuoc = edsoluongnuoc.getText().toString();
                int so33 = Integer.parseInt(edsoluonggnuoc);



                String giaao = String.valueOf(5);
                int so1 = Integer.parseInt(giaao);
                String giabong = String.valueOf(5);
                int so2 = Integer.parseInt(giabong);
                String gianuoc = String.valueOf(10);
                int so3 = Integer.parseInt(gianuoc);



                int tich = so1 *so11;
                int tich2 = so2 *so22;
                int tich3 = so3 *so33;





                HashMap<String,Object> hs1 = (HashMap<String, Object>) spntensan.getSelectedItem();
                int masan = (int) hs1.get("masan");

                String tensan = (String) hs1.get("tensan");
                String giasan = (String) hs1.get("giasan");
                giasan1.setText(giasan);
                int giasan1 = Integer.parseInt(giasan);

                int    tongtientatca1 = tich + tich2 + tich3 + giasan1;
                texttongtien.setText(String.valueOf(tongtientatca1));



                HashMap<String,Object> hs2 = (HashMap<String, Object>) spnkhunggio.getSelectedItem();
                int makg = (int) hs2.get("makhunggio");
                String khunggio = (String) hs2.get("khunggio");

                HashMap<String,Object> hs4 = (HashMap<String, Object>) spntrangthai.getSelectedItem();
                int matthd1 = (int) hs4.get("matthd");
                String tentthd1 = (String) hs4.get("tentthd");

                //set thuộc tính HV
                hd = new HoaDon(tenkh1,masan,tensan,giasan,makg,khunggio,matthd1,tentthd1,tich,tich2,tich3,tongtientatca1);
                //Add hv vào database
                DataBaSe.getInstance(getApplicationContext()).dao_hoadon().insertHOADON(hd);
                //View list hv lên màn hìn
                // Khởi tạo Fragment
           finish();
            }
        });
        btnHuyAddhdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           finish();
            }
        });



    }



    private ArrayList<HashMap<String, Object>> getDSSan() {

        listsan = (ArrayList<San>) DataBaSe.getInstance(this).dao_san().getAllSan();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (San san : listsan) {
            HashMap<String, Object> hs1 = new HashMap<>();
            hs1.put("masan", san.getId_san());
            hs1.put("tensan", san.getTensan());
            hs1.put("giasan", san.getGiasan());
            listHM.add(hs1);
        }
        return  listHM;

    }

    private ArrayList<HashMap<String, Object>> getDSKhungGio() {

        listkhunggio = (ArrayList<KhungGio>) DataBaSe.getInstance(this).dao_khunggio().getAllkhunggio();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (KhungGio khungGio : listkhunggio) {
            HashMap<String, Object> hs2 = new HashMap<>();
            hs2.put("makhunggio", khungGio.getId_khunggio());
            hs2.put("khunggio",khungGio.getKhunggio());
            listHM.add(hs2);
        }
        return  listHM;

    }



    private ArrayList<HashMap<String, Object>> getDSTTHD() {

        listtthd = (ArrayList<TrangThaiHoaDon>) DataBaSe.getInstance(this).dao_tthd().getAllTTHD();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (TrangThaiHoaDon trangThaiHoaDon : listtthd) {
            HashMap<String, Object> hs4 = new HashMap<>();
            hs4.put("matthd", trangThaiHoaDon.getId_trangthaihd());
            hs4.put("tentthd",trangThaiHoaDon.getTentrangthai());

            listHM.add(hs4);
        }
        return  listHM;

    }



    @Override
    protected void onResume() {
        super.onResume();
    }

}