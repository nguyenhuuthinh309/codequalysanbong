package thinhnh.fpoly.myapp.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

import thinhnh.fpoly.myapp.R;
import thinhnh.fpoly.myapp.csdl.DTO.DichVu;
import thinhnh.fpoly.myapp.csdl.DTO.HoaDon;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;
import thinhnh.fpoly.myapp.interfaces.InteLoadData;

public class AdapterListView_HoaDon extends BaseAdapter {
    ArrayList<HoaDon> list = new ArrayList<>();
    Context context;
    InteLoadData intels;
    InteLoadData inteCS;
    ArrayList<HashMap<String,Object>> listHM;

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
            viewHolder.itemHdloaisan = (TextView)  view.findViewById(R.id.item_hdloaisan);
            viewHolder.itemHdtensan = (TextView)  view.findViewById(R.id.item_hdtensan);
            viewHolder.itemHdkhunggio = (TextView)  view.findViewById(R.id.item_hdkhunggio);
            viewHolder.itemHddichvu = (TextView)  view.findViewById(R.id.item_hddichvu);
            viewHolder.itemHdtrangthai = (TextView)  view.findViewById(R.id.item_hdtrangthai);
            viewHolder.itemHdtongtien = (TextView)  view.findViewById(R.id.item_hdtongtien);
            viewHolder.itemPtImgtd = (ImageView)  view.findViewById(R.id.item_pt_imgtd);


            viewHolder.itemPtImgtd = (ImageView) view.findViewById(R.id.item_pt_imgtd);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.itemTenkh.setText("Khách Hàng:"+hd.getTenkh());
        viewHolder.itemHdloaisan.setText("Loại:"+hd.getTenloai());
        viewHolder.itemHdtensan.setText("Sân:"+hd.getTensan());
        viewHolder.itemHdkhunggio.setText("Khung Giờ:"+hd.getKhunggio());
        viewHolder.itemHddichvu.setText("Dịch Vụ:"+hd.getTendv());
        viewHolder.itemHdtrangthai.setText("Trạng Thái:"+hd.getTentrangthai());
        viewHolder.itemHdtongtien.setText("Tổng Tiền:"+hd.getTongtien());
        ViewHolder finalViewHolder = viewHolder;
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




                                TextInputEditText  tenkhedit = (TextInputEditText) dialogEdit.findViewById(R.id.tenkhedit);
                                Spinner spnloaisanedit = (Spinner) dialogEdit.findViewById(R.id.spnloaisanedit);
                                Spinner  spntensanedit = (Spinner) dialogEdit.findViewById(R.id.spntensanedit);
                                Spinner  spnkhunggioedit = (Spinner) dialogEdit.findViewById(R.id.spnkhunggioedit);
                                Spinner spndichvuedit = (Spinner) dialogEdit.findViewById(R.id.spndichvuedit);
                                Spinner spntrangthaiedit = (Spinner) dialogEdit.findViewById(R.id.spntrangthaiedit);
                                TextView  tongtienedit = (TextView) dialogEdit.findViewById(R.id.tongtienedit);
                                Button btnAddhddedit = (Button) dialogEdit.findViewById(R.id.btnAddhddedit);
                                Button btnHuyAddhddedit = (Button) dialogEdit.findViewById(R.id.btnHuyAddhddedit);


                                SimpleAdapter simpleAdapter = new SimpleAdapter(context, listHM, android.R.layout.simple_list_item_1, new String[]{"tenloai"}, new int[]{android.R.id.text1} );spnloaisanedit.setAdapter(simpleAdapter);
                                int index = 0;
                                int postion = -1;
                                for (HashMap<String,Object> item: listHM) {if ((int) item.get("maloai") == hd.getId_loaisan()) {postion = index;}index++;}
                                spnloaisanedit.setSelection(postion);


                                SimpleAdapter simpleAdapter1 = new SimpleAdapter(context, listHM, android.R.layout.simple_list_item_1, new String[]{"tensan"}, new int[]{android.R.id.text1} );spntensanedit.setAdapter(simpleAdapter1);
                                SimpleAdapter simpleAdapter2 = new SimpleAdapter(context, listHM, android.R.layout.simple_list_item_1, new String[]{"khunggio"}, new int[]{android.R.id.text1} );spnkhunggioedit.setAdapter(simpleAdapter2);
                                SimpleAdapter simpleAdapter3 = new SimpleAdapter(context, listHM, android.R.layout.simple_list_item_1, new String[]{"tendichvu"}, new int[]{android.R.id.text1} );spndichvuedit.setAdapter(simpleAdapter3);
                                SimpleAdapter simpleAdapter4 = new SimpleAdapter(context, listHM, android.R.layout.simple_list_item_1, new String[]{"tentthd"}, new int[]{android.R.id.text1} );spntrangthaiedit.setAdapter(simpleAdapter4);
                                btnAddhddedit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        HashMap<String,Object> hs = (HashMap<String, Object>) spnloaisanedit.getSelectedItem();int tenloai = (int) hs.get("loaisan");hd.setId_loaisan(tenloai);
                                        HashMap<String,Object> hs1 = (HashMap<String, Object>) spntensanedit.getSelectedItem();int tensan = (int) hs.get("tensan");hd.setId_san(tensan);
                                        HashMap<String,Object> hs2 = (HashMap<String, Object>) spnkhunggioedit.getSelectedItem();int khunggio = (int) hs.get("khunggio");hd.setId_khunggio(khunggio);
                                        HashMap<String,Object> hs3 = (HashMap<String, Object>) spndichvuedit.getSelectedItem();int tendichvu = (int) hs.get("tendichvu");hd.setId_dv(tendichvu);
                                        HashMap<String,Object> hs4 = (HashMap<String, Object>) spntrangthaiedit.getSelectedItem();int tentthd = (int) hs.get("tentthd");hd.setId_trangthaihd(tentthd);

                                        DataBaSe.getInstance(context).dao_hoadon().updataHOADON(hd);
                                        inteCS.loadData();
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










         TextView itemTenkh;
         TextView itemHdloaisan;
         TextView itemHdtensan;
         TextView itemHdkhunggio;
         TextView itemHddichvu;
         TextView itemHdtrangthai;
         TextView itemHdtongtien;
         ImageView itemPtImgtd;





    }
}
