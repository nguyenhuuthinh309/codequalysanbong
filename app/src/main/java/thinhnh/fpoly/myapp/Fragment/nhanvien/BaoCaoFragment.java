package thinhnh.fpoly.myapp.Fragment.nhanvien;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import thinhnh.fpoly.myapp.R;

public class BaoCaoFragment extends Fragment {


    public BaoCaoFragment() {
        // Required empty public constructor
    }


    public static BaoCaoFragment newInstance() {
        BaoCaoFragment fragment = new BaoCaoFragment();

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
        return inflater.inflate(R.layout.fragment_bao_cao, container, false);
    }
}