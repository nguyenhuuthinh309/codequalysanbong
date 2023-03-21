package thinhnh.fpoly.myapp.Fragment.nhanvien;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

import thinhnh.fpoly.myapp.R;
import thinhnh.fpoly.myapp.adapter.AdapterListView_HoaDon;
import thinhnh.fpoly.myapp.adapter.AdapterListView_San;
import thinhnh.fpoly.myapp.csdl.DTO.DichVu;
import thinhnh.fpoly.myapp.csdl.DTO.HoaDon;
import thinhnh.fpoly.myapp.csdl.DTO.KhungGio;
import thinhnh.fpoly.myapp.csdl.DTO.LoaiSan;
import thinhnh.fpoly.myapp.csdl.DTO.NhanVien;
import thinhnh.fpoly.myapp.csdl.DTO.San;
import thinhnh.fpoly.myapp.csdl.DTO.TrangThaiHoaDon;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;

public class HoaDonFragment extends Fragment {

     ListView lisCs;
     FloatingActionButton floatCs;
    HoaDon hd;


     TextInputEditText tenkh;
     Spinner spnloaisan;
     Spinner spntensan;
     Spinner spnkhunggio;
     Spinner spndichvu;
     Spinner spntrangthai;
     TextView tongtien;
     Button themhd;
     Button huyhd;

    ArrayList<HoaDon> listhoadon = new ArrayList<>();
    ArrayList<LoaiSan> listloaisan = new ArrayList<>();
    ArrayList<San> listsan = new ArrayList<>();
    ArrayList<KhungGio> listkhunggio = new ArrayList<>();
    ArrayList<DichVu> listdichvu = new ArrayList<>();
    ArrayList<TrangThaiHoaDon> listtthd = new ArrayList<>();
    AdapterListView_HoaDon adapterListView_hoaDon;



    public HoaDonFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HoaDonFragment newInstance() {
        HoaDonFragment fragment = new HoaDonFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hoa_don, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        lisCs = (ListView) view.findViewById(R.id.lis_cs);
        floatCs = (FloatingActionButton) view.findViewById(R.id.float_cs);
        loadData();
        floatCs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_add_hoadon);
                tenkh = (TextInputEditText) dialog.findViewById(R.id.tenkh);
                spnloaisan = (Spinner) dialog.findViewById(R.id.spnloaisan);
                spntensan = (Spinner) dialog.findViewById(R.id.spntensan);
                spnkhunggio = (Spinner) dialog.findViewById(R.id.spnkhunggio);
                spndichvu = (Spinner) dialog.findViewById(R.id.spndichvu);
                spntrangthai = (Spinner) dialog.findViewById(R.id.spntrangthai);
                tongtien = (TextView) dialog.findViewById(R.id.tongtien);
                themhd = (Button) dialog.findViewById(R.id.btnAddhdd);
                huyhd = (Button) dialog.findViewById(R.id.btnHuyAddhdd);

                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), getDSLoaiSan(), android.R.layout.simple_list_item_1, new String[]{"tenloai"}, new int[]{android.R.id.text1});
                spnloaisan.setAdapter(simpleAdapter);

                SimpleAdapter simpleAdapter1 = new SimpleAdapter(getContext(), getDSSan(), android.R.layout.simple_list_item_1, new String[]{"tensan"}, new int[]{android.R.id.text1});
                spntensan.setAdapter(simpleAdapter1);

                SimpleAdapter simpleAdapter2 = new SimpleAdapter(getContext(), getDSKhungGio(), android.R.layout.simple_list_item_1, new String[]{"khunggio"}, new int[]{android.R.id.text1});
                spnkhunggio.setAdapter(simpleAdapter2);

                SimpleAdapter simpleAdapter3 = new SimpleAdapter(getContext(), getDSDichVu(), android.R.layout.simple_list_item_1, new String[]{"tendichvu"}, new int[]{android.R.id.text1});
                spndichvu.setAdapter(simpleAdapter3);

                SimpleAdapter simpleAdapter4 = new SimpleAdapter(getContext(), getDSTTHD(), android.R.layout.simple_list_item_1, new String[]{"tentthd"}, new int[]{android.R.id.text1});
                spntrangthai.setAdapter(simpleAdapter4);

                themhd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (validate()){}
                        String tenkh1 = tenkh.getText().toString();
                        String tongtienn = tongtien.getText().toString();

                        HashMap<String,Object> hs = (HashMap<String, Object>) spnloaisan.getSelectedItem();
                        int maloai = (int) hs.get("maloai");
                        String tenloai = (String) hs.get("tenloai");

                        HashMap<String,Object> hs1 = (HashMap<String, Object>) spntensan.getSelectedItem();
                        int masan = (int) hs1.get("masan");
                        String tensan = (String) hs1.get("tensan");
                        String giasan = (String) hs1.get("giasan");


                        HashMap<String,Object> hs2 = (HashMap<String, Object>) spnkhunggio.getSelectedItem();
                        int makg = (int) hs2.get("makhunggio");
                        String khunggio = (String) hs2.get("khunggio");

                        HashMap<String,Object> hs3 = (HashMap<String, Object>) spndichvu.getSelectedItem();
                        int madv = (int) hs3.get("madichvu");
                        String tendv = (String) hs3.get("tendichvu");
                        String giadv = (String) hs3.get("giadichvu");


                        HashMap<String,Object> hs4 = (HashMap<String, Object>) spntrangthai.getSelectedItem();
                        int matthd1 = (int) hs4.get("matthd");
                        String tentthd1 = (String) hs4.get("tentthd");








                        //set thuộc tính HV
                        hd = new HoaDon(tenkh1,maloai,tenloai,masan,tensan,giasan,makg,khunggio,madv,tendv,giadv,matthd1,tentthd1,tongtienn);
                        //Add hv vào database
                        DataBaSe.getInstance(getActivity()).dao_hoadon().insertHOADON(hd);
                        //View list hv lên màn hình
                        loadData();
                        Log.d("zzz", "onViewCreated: " + listhoadon.size());
                        dialog.dismiss();

                    }
                });
                huyhd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
    }


    public void loadData() {
        listhoadon = (ArrayList<HoaDon>) DataBaSe.getInstance(getActivity()).dao_hoadon().getAllHOADON();
        adapterListView_hoaDon = new AdapterListView_HoaDon(getActivity(),this::loadData);
        adapterListView_hoaDon.setdata(listhoadon);
        lisCs.setAdapter(adapterListView_hoaDon);
    }


    private ArrayList<HashMap<String, Object>> getDSLoaiSan() {

        listloaisan = (ArrayList<LoaiSan>) DataBaSe.getInstance(getActivity()).dao_loaisan().getAllLoaiSan();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (LoaiSan loai : listloaisan) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loai.getId_loaisan());
            hs.put("tenloai", loai.getTenloai());
            listHM.add(hs);
        }
        return  listHM;

    }

    private ArrayList<HashMap<String, Object>> getDSSan() {

        listsan = (ArrayList<San>) DataBaSe.getInstance(getActivity()).dao_san().getAllSan();
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

        listkhunggio = (ArrayList<KhungGio>) DataBaSe.getInstance(getActivity()).dao_khunggio().getAllkhunggio();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (KhungGio khungGio : listkhunggio) {
            HashMap<String, Object> hs2 = new HashMap<>();
            hs2.put("makhunggio", khungGio.getId_khunggio());
            hs2.put("khunggio",khungGio.getKhunggio());
            listHM.add(hs2);
        }
        return  listHM;

    }

    private ArrayList<HashMap<String, Object>> getDSDichVu() {

        listdichvu = (ArrayList<DichVu>) DataBaSe.getInstance(getActivity()).dao_dv().getAllDV();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (DichVu dichVu : listdichvu) {
            HashMap<String, Object> hs3 = new HashMap<>();
            hs3.put("madichvu", dichVu.getId_dv());
            hs3.put("tendichvu",dichVu.getTendv());
            hs3.put("giadichvu",dichVu.getGiadv());
            listHM.add(hs3);
        }
        return  listHM;

    }

    private ArrayList<HashMap<String, Object>> getDSTTHD() {

        listtthd = (ArrayList<TrangThaiHoaDon>) DataBaSe.getInstance(getActivity()).dao_tthd().getAllTTHD();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (TrangThaiHoaDon trangThaiHoaDon : listtthd) {
            HashMap<String, Object> hs4 = new HashMap<>();
            hs4.put("matthd", trangThaiHoaDon.getId_trangthaihd());
            hs4.put("tentthd",trangThaiHoaDon.getTentrangthai());

            listHM.add(hs4);
        }
        return  listHM;

    }
}