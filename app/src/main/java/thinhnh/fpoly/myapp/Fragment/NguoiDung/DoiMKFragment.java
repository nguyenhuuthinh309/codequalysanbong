package thinhnh.fpoly.myapp.Fragment.NguoiDung;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thinhnh.fpoly.myapp.R;


public class DoiMKFragment extends Fragment {


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
}