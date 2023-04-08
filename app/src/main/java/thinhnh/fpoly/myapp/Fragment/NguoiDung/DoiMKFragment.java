package thinhnh.fpoly.myapp.Fragment.NguoiDung;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import thinhnh.fpoly.myapp.R;
import thinhnh.fpoly.myapp.csdl.DTO.Admin;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;


public class DoiMKFragment extends Fragment {
     TextInputEditText old_pass;
     TextInputEditText mk_moi,chek_mkmoi;
        Admin admin;
     Button btndoi,btnhuy;

    public DoiMKFragment() {
        // Required empty public constructor
    }


    public static DoiMKFragment newInstance() {
        DoiMKFragment fragment = new DoiMKFragment();

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
        return inflater.inflate(R.layout.fragment_doi_m_k, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        savedInstanceState = getActivity().getIntent().getExtras();
        String userHV = savedInstanceState.getString("userHV");
        old_pass = (TextInputEditText) view.findViewById(R.id.edOldPass_changge);
        mk_moi = (TextInputEditText) view.findViewById(R.id.edNewPass_changge);
        chek_mkmoi = (TextInputEditText) view.findViewById(R.id.edReNewPass_changge);
        btndoi = (Button) view.findViewById(R.id.btnSwapPass_changge);
        btnhuy = (Button) view.findViewById(R.id.btnEndSwapPass_changge);
        TextView a = view.findViewById(R.id.text1);


        btndoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chek_mkmoi.setTextColor(Color.BLACK);
                admin = DataBaSe.getInstance(getActivity()).dao_admin().getAdtheoUser(userHV).get(0);
                    String oldPass = old_pass.getText().toString();
                    String newPass = mk_moi.getText().toString();
                    if (oldPass.equals(admin.getMatkhau())) {
                        admin.setMatkhau(newPass);
                        DataBaSe.getInstance(getActivity()).dao_admin().updataad(admin);
                        Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                          a.setText("dổi thành coong");
                    } else {
                        Toast.makeText(getActivity(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                        a.setText("dổi không thahf công");
                    }


            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}