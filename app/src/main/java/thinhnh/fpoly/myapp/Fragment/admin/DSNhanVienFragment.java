package thinhnh.fpoly.myapp.Fragment.admin;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import thinhnh.fpoly.myapp.R;
import thinhnh.fpoly.myapp.adapter.AdapterListView_NV;
import thinhnh.fpoly.myapp.csdl.DTO.NhanVien;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;


public class DSNhanVienFragment extends Fragment {

    ArrayList<NhanVien> lissonv = new ArrayList<>();
    NhanVien nv;
    private ListView lisCs;
    private FloatingActionButton floatCs;
    ArrayList<NhanVien> list = new ArrayList<>();
    AdapterListView_NV adapterListView_nv;
    TextView tvsnv;
     TextInputEditText tkAdd,loaitk;
     TextInputEditText mkAdd;
     TextInputEditText tenAdd;
     TextInputEditText sdtAdd;
     TextInputEditText cccdAdd;
     Button btnAddNV;
     Button btnHuyAddNv;

    ImageView imageView;



    public DSNhanVienFragment() {
        // Required empty public constructor
    }


    public static DSNhanVienFragment newInstance() {
        DSNhanVienFragment fragment = new DSNhanVienFragment();

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
        return inflater.inflate(R.layout.fragment_d_s_nhan_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lisCs = (ListView) view.findViewById(R.id.lis_cs);
        floatCs = (FloatingActionButton) view.findViewById(R.id.float_cs);
        imageView=new ImageView(getActivity());
        tvsnv = view.findViewById(R.id.sonv);
        lisCs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_nhanvien_chitiet);
                nv = list.get(position);
                TextView tennv = (TextView) dialog.findViewById(R.id.tennvchitiet);
                TextView matkhau = (TextView) dialog.findViewById(R.id.matkhaunvchitiet);
                TextView hoten = (TextView) dialog.findViewById(R.id.hotennvchitiet);
                TextView sdt = (TextView) dialog.findViewById(R.id.sdtnvchitiet);
                tennv.setText(nv.getTk_NV());
               matkhau.setText(nv.getMk_NV());
               hoten.setText(nv.getTen_NV());
               sdt.setText(nv.getSdt_NV());
                dialog.show();


            }
        });
    imageView.setImageResource(R.drawable.avtnv);
        loadData();
        sonv();
        floatCs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_add_nv);
                tkAdd = (TextInputEditText) dialog.findViewById(R.id.tk_add);
                mkAdd = (TextInputEditText)  dialog.findViewById(R.id.mk_add);
                tenAdd = (TextInputEditText)  dialog.findViewById(R.id.ten_add);
                sdtAdd = (TextInputEditText)  dialog.findViewById(R.id.sdt_add);

                btnAddNV = (Button)  dialog.findViewById(R.id.btnAddNV);
                btnHuyAddNv = (Button) dialog.findViewById(R.id.btnHuyAddNv);
                btnAddNV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (validate()){}
                            String tk = tkAdd.getText().toString();
                            String mk = mkAdd.getText().toString();
                            String ten = tenAdd.getText().toString();
                            String sdt = sdtAdd.getText().toString();



                            //set thuộc tính HV
                            nv = new NhanVien(tk,mk,ten,sdt,Image_to_bye(imageView) );
                            //Add hv vào database
                            DataBaSe.getInstance(getActivity()).dao_nv().insertNV(nv);
                            //View list hv lên màn hình
                            loadData();
                            sonv();
                            Log.d("zzz", "onViewCreated: " + list.size());
                            dialog.dismiss();

                    }
                });
                btnHuyAddNv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
        tvsnv.setText("Số Lượng : "+sonv());

    }

    public void loadData() {
        list = (ArrayList<NhanVien>) DataBaSe.getInstance(getActivity()).dao_nv().getAllNV();
        adapterListView_nv = new AdapterListView_NV(getActivity(),this::loadData);
        adapterListView_nv.setdata(list);
        lisCs.setAdapter(adapterListView_nv);

    }
    public byte[] Image_to_bye(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }
    public int sonv(){
        int x = 0;
        lissonv =(ArrayList<NhanVien>) DataBaSe.getInstance(getActivity()).dao_nv().getAllNV();
        for (int i = 1;i<lissonv.toArray().length;i++){

            x=i+1;
        }
        return x;
    }
}