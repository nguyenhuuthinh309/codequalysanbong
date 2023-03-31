package thinhnh.fpoly.myapp.Fragment.admin;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

import thinhnh.fpoly.myapp.R;
import thinhnh.fpoly.myapp.adapter.Adapter_TinhTrang;
import thinhnh.fpoly.myapp.csdl.DTO.San;
import thinhnh.fpoly.myapp.csdl.DTO.Tinhtrang;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;


public class DanhSachTinhTrangfragment extends Fragment {
    private ListView lvTinhtrang;
    private FloatingActionButton fabthemTinhTrang;
    private Spinner spnsan;
    private TextInputEditText txttinhtrang;
    private TextInputEditText txthoatdong;
    private Button btnadd;
    private Button btnhuy;
    Tinhtrang tinhtrang;
    ArrayList<Tinhtrang> listtinhtrang = new ArrayList<>();
    ArrayList<San> lissan = new ArrayList<>();
    Adapter_TinhTrang adapter_tinhTrang;


    public DanhSachTinhTrangfragment() {
        // Required empty public constructor
    }


    public static DanhSachTinhTrangfragment newInstance() {
        DanhSachTinhTrangfragment fragment = new DanhSachTinhTrangfragment();

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
        return inflater.inflate(R.layout.fragment_tinh_trangfragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvTinhtrang = (ListView) view.findViewById(R.id.lis_tinhtrang);
        fabthemTinhTrang = view.findViewById(R.id.float_tinhtrang);

        fabthemTinhTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_tinhtrangsan);
                txttinhtrang = (TextInputEditText)dialog.findViewById(R.id.tinhtrang);
                txthoatdong= (TextInputEditText) dialog.findViewById(R.id.hoatdong);
                spnsan = (Spinner) dialog.findViewById(R.id.spnsan);
                btnadd = (Button) dialog.findViewById(R.id.btnAddtinhtrang);
                btnhuy = (Button) dialog.findViewById(R.id.btnHuyAddTinhtrang);
                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),getdssan(), android.R.layout.simple_list_item_1,
                        new String[]{"tensan"}, new int[]{android.R.id.text1});
                spnsan.setAdapter(simpleAdapter);
                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tensan1 = txttinhtrang.getText().toString();
                        String vitri1 = txthoatdong.getText().toString();

                        HashMap<String,Object> hs = (HashMap<String, Object>) spnsan.getSelectedItem();
                        String tensan = (String) hs.get("tensan");
                        int masan = (int) hs.get("masan");
                        tinhtrang = new Tinhtrang(tensan1,vitri1,masan,tensan);
                        DataBaSe.getInstance(getActivity()).dao_tinhtrang().insertTinhtrang(tinhtrang);
                        load();

                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        load();
    }
    private ArrayList<HashMap<String,Object>> getdssan(){
        lissan = (ArrayList<San>) DataBaSe.getInstance(getActivity()).dao_san().getAllSan();
        ArrayList<HashMap<String,Object>> listhm = new ArrayList<>();
        for(San san : lissan){
            HashMap<String , Object> hs = new HashMap<>();
            hs.put("tensan",san.getTensan());
            hs.put("masan",san.getId_san());
            listhm.add(hs);

        }
        return listhm;
    }
    public void load(){
        listtinhtrang = (ArrayList<Tinhtrang>) DataBaSe.getInstance(getActivity()).dao_tinhtrang().getAllTT();
        adapter_tinhTrang = new Adapter_TinhTrang(getActivity(),this::load);
        adapter_tinhTrang.setdata_tinhtrang(listtinhtrang);
        lvTinhtrang.setAdapter(adapter_tinhTrang);
    }
}