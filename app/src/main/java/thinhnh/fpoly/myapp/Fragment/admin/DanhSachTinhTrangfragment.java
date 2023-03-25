package thinhnh.fpoly.myapp.Fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import thinhnh.fpoly.myapp.R;


public class DanhSachTinhTrangfragment extends Fragment {


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
}