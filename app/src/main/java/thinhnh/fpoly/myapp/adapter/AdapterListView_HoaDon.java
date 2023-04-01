package thinhnh.fpoly.myapp.adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import thinhnh.fpoly.myapp.Fragment.nhanvien.ThemHoaDonActivity;
import thinhnh.fpoly.myapp.R;
import thinhnh.fpoly.myapp.csdl.DTO.HoaDon;
import thinhnh.fpoly.myapp.csdl.DTO.KhungGio;
import thinhnh.fpoly.myapp.csdl.DTO.San;
import thinhnh.fpoly.myapp.csdl.DTO.TrangThaiHoaDon;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;
import thinhnh.fpoly.myapp.interfaces.InteLoadData;

public class AdapterListView_HoaDon extends BaseAdapter {
    ArrayList<HoaDon> list = new ArrayList<>();
    ArrayList<San> listSan = new ArrayList<>();
    ArrayList<KhungGio> listkhunggio = new ArrayList<>();
    ArrayList<TrangThaiHoaDon> listtthd = new ArrayList<>();
    Context context;
    InteLoadData intels;

    ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
    EditText itemngaythue;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    int myear,mmonth,mday;
    public AdapterListView_HoaDon(Context context, InteLoadData intels) {
        this.context = context;
        this.intels = intels;
    }

    public void setdata(ArrayList<HoaDon> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HoaDon hd = list.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_hoadon, null);


            viewHolder.itemTenkh = (TextView)  view.findViewById(R.id.item_tenkh);

            viewHolder.itemHdtensan = (TextView)  view.findViewById(R.id.item_hdtensan);
            viewHolder.itemHdkhunggio = (TextView)  view.findViewById(R.id.item_hdkhunggio);
            viewHolder.itemHdnuoc = (TextView)  view.findViewById(R.id.item_hdnuoc);
            viewHolder.itemHdbong = (TextView)  view.findViewById(R.id.item_hdbong);
            viewHolder.itemHdao = (TextView)  view.findViewById(R.id.item_hdao);
            viewHolder.itemHdtrangthai = (TextView)  view.findViewById(R.id.item_hdtrangthai);
            viewHolder.itemHdtongtien = (TextView)  view.findViewById(R.id.item_hdtongtien);
            viewHolder.itemPtImgtd = (ImageView)  view.findViewById(R.id.item_pt_imgtd);
            viewHolder.itemngaythue =(TextView) view.findViewById(R.id.item_ngaythue);

            viewHolder.itemPtImgtd = (ImageView) view.findViewById(R.id.item_pt_imgtd);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.itemTenkh.setText("Khách Hàng:"+hd.getTenkh());
        viewHolder.itemHdtensan.setText("Sân:"+hd.getTensan());
        viewHolder.itemHdkhunggio.setText("Khung Giờ:"+hd.getKhunggio());
        viewHolder.itemngaythue.setText("Ngày thuê"+ hd.getNgaythue());
        viewHolder.itemHdnuoc.setText("Nước:"+hd.getNuoc());
        viewHolder.itemHdbong.setText("Bóng :"+hd.getBong());
        viewHolder.itemHdao.setText("Áo:"+hd.getAo());
        viewHolder.itemHdtrangthai.setText("Trạng Thái:"+hd.getTentrangthai());
        viewHolder.itemHdtongtien.setText("Tổng Tiền:"+hd.getTongtien());
        ViewHolder finalViewHolder = viewHolder;
//        viewHolder.ngaythuebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar calendar = Calendar.getInstance();
//                myear = calendar.get(Calendar.YEAR);
//                mmonth= calendar.get(Calendar.MONTH);
//                mday = calendar.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog dialog = new DatePickerDialog(context.getApplicationContext(),0,mdatetungay,myear,mmonth,mday);
//                dialog.show();
//            }
//        });
        viewHolder.itemPtImgtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(context, finalViewHolder.itemPtImgtd);
                popupMenu.getMenuInflater().inflate(R.menu.menu_for_icon,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()){
                            case R.id.menusua:
                                Dialog dialogEdit = new Dialog(context);
                                dialogEdit.setContentView(R.layout.dialog_hoadon_edit);




                                TextInputEditText     tenkhedit = (TextInputEditText) dialogEdit.findViewById(R.id.tenkhedit);
                                Spinner  spnkhunggioedit = (Spinner) dialogEdit.findViewById(R.id.spnkhunggioedit);
                                TextView      giakhunggio = (TextView) dialogEdit.findViewById(R.id.giakhunggio);
                                Spinner    spntensanedit = (Spinner) dialogEdit.findViewById(R.id.spntensanedit);
                                TextView  giasan1edit = (TextView) dialogEdit.findViewById(R.id.giasan1edit);
                                EditText  edsoluongbongedit = (EditText) dialogEdit.findViewById(R.id.edsoluongbongedit);
                                TextView  tvgianbongedit = (TextView) dialogEdit.findViewById(R.id.tvgianbongedit);
                                EditText  edsoluongnuocedit = (EditText) dialogEdit.findViewById(R.id.edsoluongnuocedit);
                                TextView    tvgianuocedit = (TextView) dialogEdit.findViewById(R.id.tvgianuocedit);
                                EditText     edsoluongaoedit = (EditText) dialogEdit.findViewById(R.id.edsoluongaoedit);
                                TextView   tvgiaaoedit = (TextView) dialogEdit.findViewById(R.id.tvgiaaoedit);
                                Button  tongtien = (Button) dialogEdit.findViewById(R.id.tongtien);
                                TextView  texttongtienedit = (TextView) dialogEdit.findViewById(R.id.texttongtienedit);
                                Spinner   spntrangthaiedit = (Spinner) dialogEdit.findViewById(R.id.spntrangthaiedit);
                                Button   btnAddhddedit = (Button) dialogEdit.findViewById(R.id.btnAddhddedit);
                                Button    btnHuyAddhddedit = (Button) dialogEdit.findViewById(R.id.btnHuyAddhddedit);
                                EditText ngaythue = (EditText) dialogEdit.findViewById(R.id.edtngaythueedit);
                                ImageView btnngaythue = (ImageView) dialogEdit.findViewById(R.id.imgngayedit);
                                DatePickerDialog.OnDateSetListener mdatetungay = new DatePickerDialog.OnDateSetListener() {
                                    ViewHolder vir = null;
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                        myear = i;
                                        mmonth = i1;
                                        mday = i2;
                                        GregorianCalendar c = new GregorianCalendar(myear,mmonth,mday);
                                        ngaythue.setText(simpleDateFormat.format(c.getTime()));
                                    }
                                };
                                btnngaythue.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Calendar calendar = Calendar.getInstance();
                                        myear = calendar.get(Calendar.YEAR);
                                        mmonth= calendar.get(Calendar.MONTH);
                                        mday = calendar.get(Calendar.DAY_OF_MONTH);
                                        DatePickerDialog dialog = new DatePickerDialog(dialogEdit.getContext()
                                                ,0,mdatetungay,myear,mmonth,mday);
                                        dialog.show();
                                    }
                                });
                                tongtien.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String edsoluonggao = edsoluongaoedit.getText().toString();
                                        int so11 = Integer.parseInt(edsoluonggao);
                                        String edsoluonggbong = edsoluongbongedit.getText().toString();
                                        int so22 = Integer.parseInt(edsoluonggbong);
                                        String edsoluonggnuoc = edsoluongnuocedit.getText().toString();
                                        int so33 = Integer.parseInt(edsoluonggnuoc);
                                        String giaao = String.valueOf(5);
                                        int so1 = Integer.parseInt(giaao);
                                        String giabong = String.valueOf(5);
                                        int so2 = Integer.parseInt(giabong);
                                        String gianuoc = String.valueOf(10);
                                        int so3 = Integer.parseInt(gianuoc);
                                        int tich = so1 *so11;
                                        int tich2 = so2 *so22;
                                        int tich3 = so3 *so33;

                                        HashMap<String,Object> hs1 = (HashMap<String, Object>) spntensanedit.getSelectedItem();

                                        String tensan = (String) hs1.get("tensan");
                                        String giasan = (String) hs1.get("giasan");
                                        giasan1edit.setText(giasan);
                                        int gia = Integer.parseInt(giasan);
                                        int    tongtientatca1 = tich + tich2 + tich3 + gia;
                                        texttongtienedit.setText(String.valueOf(tongtientatca1));
                                    }
                                });
                                SimpleAdapter simpleAdapter1 = new SimpleAdapter(dialogEdit.getContext(), getdssan(),
                                        android.R.layout.simple_list_item_1, new String[]{"tensan"},
                                        new int[]{android.R.id.text1} );
                                spntensanedit.setAdapter(simpleAdapter1);


                                SimpleAdapter simpleAdapter2 = new SimpleAdapter(dialogEdit.getContext(), getdskhunggio(),
                                        android.R.layout.simple_list_item_1, new String[]{"tenkhunggio"},
                                        new int[]{android.R.id.text1} );
                                spnkhunggioedit.setAdapter(simpleAdapter2);


                                SimpleAdapter simpleAdapter4 = new SimpleAdapter(dialogEdit.getContext(), getdstthd(),
                                        android.R.layout.simple_list_item_1, new String[]{"tentthd"},
                                        new int[]{android.R.id.text1} );
                                spntrangthaiedit.setAdapter(simpleAdapter4);

                                 tenkhedit.setText(hd.getTenkh());
                                 giasan1edit.setText(hd.getGiasan());
                                 edsoluongaoedit.setText(String.valueOf(hd.getAo()));
                                 edsoluongbongedit.setText(String.valueOf(hd.getBong()));
                                 edsoluongnuocedit.setText(String.valueOf(hd.getNuoc()));
                                 texttongtienedit.setText(String.valueOf(hd.getTongtien()));
                                 ngaythue.setText(hd.getNgaythue());


                                btnAddhddedit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                        hd.setTenkh(tenkhedit.getText().toString());
                                        hd.setNgaythue(ngaythue.getText().toString());
                                        hd.setGiasan(giasan1edit.getText().toString());
                                        hd.setNuoc(Integer.parseInt(edsoluongnuocedit.getText().toString()));
                                        hd.setAo(Integer.parseInt(edsoluongaoedit.getText().toString()));
                                        hd.setBong(Integer.parseInt(edsoluongbongedit.getText().toString()));




                                        String edsoluonggao = edsoluongaoedit.getText().toString();
                                        int so11 = Integer.parseInt(edsoluonggao);
                                        String edsoluonggbong = edsoluongbongedit.getText().toString();
                                        int so22 = Integer.parseInt(edsoluonggbong);
                                        String edsoluonggnuoc = edsoluongnuocedit.getText().toString();
                                        int so33 = Integer.parseInt(edsoluonggnuoc);
                                        String giaao = String.valueOf(5);
                                        int so1 = Integer.parseInt(giaao);
                                        String giabong = String.valueOf(5);
                                        int so2 = Integer.parseInt(giabong);
                                        String gianuoc = String.valueOf(10);
                                        int so3 = Integer.parseInt(gianuoc);
                                        int tich = so1 *so11;
                                        int tich2 = so2 *so22;
                                        int tich3 = so3 *so33;

                                        HashMap<String,Object> hs1 = (HashMap<String, Object>) spntensanedit.getSelectedItem();

                                        String tensan = (String) hs1.get("tensan");
                                        String giasan = (String) hs1.get("giasan");

                                        hd.setTensan(tensan);
                                        giasan1edit.setText(giasan);
                                        int gia = Integer.parseInt(giasan);
                                        int    tongtientatca1 = tich + tich2 + tich3 + gia;
                                         texttongtienedit.setText(String.valueOf(tongtientatca1));
                                        hd.setTongtien(Integer.parseInt(texttongtienedit.getText().toString()));


                                        HashMap<String,Object> hs2 = (HashMap<String, Object>) spnkhunggioedit.getSelectedItem();
                                        String khunggio = (String) hs2.get("tenkhunggio");
                                        hd.setKhunggio(khunggio);

                                        HashMap<String,Object> hs4 = (HashMap<String, Object>) spntrangthaiedit.getSelectedItem();
                                        String tentthd = (String) hs4.get("tentthd");
                                        hd.setTentrangthai(tentthd);

                                        DataBaSe.getInstance(context).dao_hoadon().updataHOADON(hd);
                                        intels.loadData();
                                        Toast.makeText(context, "Đã sửa thành công!!!", Toast.LENGTH_SHORT).show();
                                        dialogEdit.dismiss();
                                    }
                                });
                                btnHuyAddhddedit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialogEdit.dismiss();
                                    }
                                });
                                dialogEdit.show();
                                Toast.makeText(context, " sửa", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menuxoa:
                                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                                builder.setTitle("DELETE");
                                builder.setMessage("Do you want delete ?");
                                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        DataBaSe.getInstance(context).dao_hoadon().deleteHOADON(hd);
                                        Toast.makeText((context), "Đã xóa", Toast.LENGTH_SHORT).show();
                                        intels.loadData();
                                        Toast.makeText(context, " xóa", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNegativeButton("NO",null);
                                builder.show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        return view;

    }
    public class ViewHolder {
        private TextView itemTenkh;
        private TextView itemHdkhunggio;
        private TextView itemHdtensan;
        private TextView itemHdnuoc;
        private TextView itemHdbong;
        private TextView itemHdao;
        private TextView itemHdtrangthai;
        private TextView itemHdtongtien;
        private ImageView itemPtImgtd;
        private TextView itemngaythue;
        private Button ngaythuebtn;






    }


    private ArrayList<HashMap<String,Object>> getdssan(){
        listSan = (ArrayList<San>) DataBaSe.getInstance(context.getApplicationContext()).dao_san().getAllSan();

        ArrayList<HashMap<String,Object>> listhm = new ArrayList<>();
        for(San san : listSan){
            HashMap<String , Object> hs = new HashMap<>();
            hs.put("masan",san.getId_san());
            hs.put("tensan",san.getTensan());
            hs.put("giasan",san.getGiasan());

            listhm.add(hs);

        }
        return listhm;
    }

    private ArrayList<HashMap<String,Object>> getdskhunggio(){
        listkhunggio = (ArrayList<KhungGio>) DataBaSe.getInstance(context.getApplicationContext()).dao_khunggio().getAllkhunggio();

        ArrayList<HashMap<String,Object>> listhmkg = new ArrayList<>();
        for(KhungGio san1 : listkhunggio){
            HashMap<String , Object> hs = new HashMap<>();
            hs.put("makhunggio",san1.getId_khunggio());
            hs.put("tenkhunggio",san1.getKhunggio());

            listhmkg.add(hs);

        }
        return listhmkg;
    }
    private ArrayList<HashMap<String,Object>> getdstthd(){
        listtthd = (ArrayList<TrangThaiHoaDon>) DataBaSe.getInstance(context.getApplicationContext()).dao_tthd().getAllTTHD();

        ArrayList<HashMap<String,Object>> listhmtthd = new ArrayList<>();
        for(TrangThaiHoaDon hoadon  : listtthd){
            HashMap<String , Object> hs = new HashMap<>();
            hs.put("mathhd",hoadon.getId_trangthaihd());
            hs.put("tentthd",hoadon.getTentrangthai());

            listhmtthd.add(hs);

        }
        return listhmtthd;
    }
}
