package thinhnh.fpoly.myapp.Fragment.thongke;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thinhnh.fpoly.myapp.R;


public class ThongKeFragment extends Fragment {



    public ThongKeFragment() {
        // Required empty public constructor
    }


    public static ThongKeFragment newInstance() {
        ThongKeFragment fragment = new ThongKeFragment();

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
        return inflater.inflate(R.layout.fragment_thong_ke__doanh_thu, container, false);
    }
}