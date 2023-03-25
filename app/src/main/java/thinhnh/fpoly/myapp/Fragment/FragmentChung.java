package thinhnh.fpoly.myapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import thinhnh.fpoly.myapp.R;
import thinhnh.fpoly.myapp.adapter.slideAdapter;
import thinhnh.fpoly.myapp.model.model_slideImage;

public class FragmentChung extends Fragment {


     ImageView imgMhcLogo;
     ViewPager ViewPagerSlide;
     CircleIndicator cirIn;

    ArrayList<model_slideImage> model_slideImages;
    slideAdapter mAdapter;
    ViewPager mViewPager;
    CircleIndicator mCircleIndicator;
    Timer mTimer;
    public FragmentChung() {
        // Required empty public constructor
    }


    public static FragmentChung newInstance() {
        FragmentChung fragment = new FragmentChung();

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
        return inflater.inflate(R.layout.fragment_chung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mViewPager = (ViewPager) view.findViewById(R.id.ViewPagerSlide);
        mCircleIndicator = (CircleIndicator) view.findViewById(R.id.cirIn);

        //tạo slide hình ảnh cho màn hình chào
        model_slideImages=new ArrayList<>();
        dataSlide();
        mAdapter=new slideAdapter(getActivity(),model_slideImages);
        mViewPager.setAdapter(mAdapter);
        mCircleIndicator.setViewPager(mViewPager);
        mAdapter.registerDataSetObserver(mCircleIndicator.getDataSetObserver());
        if (mTimer==null){
            mTimer=new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int current = mViewPager.getCurrentItem();
                        int index = model_slideImages.size()-1;
                        if (current<index){
                            current++;
                            mViewPager.setCurrentItem(current);
                        }else {
                            mViewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },1000,3000);
    }

    private void dataSlide() {
        model_slideImages.add(new model_slideImage(R.drawable.img_banner1,"Bạn Muốn làm Gì","Biến tập gym thành một cách sống","Không chỉ là một buổi luyện tập mà còn là trải nghiệm"));
        model_slideImages.add(new model_slideImage(R.drawable.img_banner2,"Poly Gym App","Rèn Luyện Bản Thân","Đừng để ngoại hình định nghĩa con người bạn. Bạn không béo. Bạn có mỡ thôi. MỠ không định nghĩa bạn là ai cả"));
        model_slideImages.add(new model_slideImage(R.drawable.img_banner3,"Poly Gym","Quản Lý Cho Poly Gym","Đừng ngại thất bại, đó là con đường dẫn đến thành công"));
    }
}