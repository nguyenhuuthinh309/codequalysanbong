package thinhnh.fpoly.myapp.Fragment.nhanvien;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import thinhnh.fpoly.myapp.R;

public class TrangThaiFragment extends Fragment {

    private ListView lisCs;
    private FloatingActionButton floatCs;

    public TrangThaiFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TrangThaiFragment newInstance() {
        TrangThaiFragment fragment = new TrangThaiFragment();

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
        return inflater.inflate(R.layout.fragment_trang_thai, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lisCs = (ListView) view.findViewById(R.id.lv_tinhtrang);
        floatCs = (FloatingActionButton) view.findViewById(R.id.float_cs);
        floatCs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog();
            }
        });

    }
    private void opendialog() {
        final Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_add_tinhtrang);
        dialog.show();
    }


}