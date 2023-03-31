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
import thinhnh.fpoly.myapp.csdl.DTO.San;
import thinhnh.fpoly.myapp.csdl.DTO.Tinhtrang;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;
import thinhnh.fpoly.myapp.interfaces.InteLoadData;

public class Adapter_TinhTrang extends BaseAdapter {
    ArrayList<Tinhtrang> list_TinhTrang = new ArrayList<>();
    ArrayList<San> listSan = new ArrayList<>();
    Context context;
    InteLoadData inteLoadData;
    ArrayList<HashMap<String,Object>> listHm ;
    public Adapter_TinhTrang(Context context, InteLoadData inteLoadData) {
        this.context = context;
        this.inteLoadData = inteLoadData;
    }
    public void setdata_tinhtrang(ArrayList<Tinhtrang> list){
        this.list_TinhTrang = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        if (list_TinhTrang != null) {
            return list_TinhTrang.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tinhtrang tinhtrang = list_TinhTrang.get(position);
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder =new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView  = inflater.inflate(R.layout.items_tinhtrang,null);
            viewHolder.itemTensan = (TextView) convertView.findViewById(R.id.item_tensan_tinhtrang);
            viewHolder.itemtinhtrang = (TextView) convertView.findViewById(R.id.item_tinhtrang);
            viewHolder.itemhoatdong = (TextView) convertView.findViewById(R.id.item_hoatdong_tinhtrang);
            viewHolder.itemPtImg_tinhtrang = (ImageView) convertView.findViewById(R.id.item_pt_img_tinhtrang);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
       viewHolder.itemTensan.setText(tinhtrang.getSan());
        viewHolder.itemtinhtrang.setText(tinhtrang.getTinhtrang());
        viewHolder.itemhoatdong.setText(tinhtrang.getHoatdong());

        ViewHolder finalview = viewHolder;
        viewHolder.itemPtImg_tinhtrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,finalview.itemPtImg_tinhtrang);
                popupMenu.getMenuInflater().inflate(R.menu.menu_for_icon,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menusua:
                                Dialog dialog = new Dialog(context);
                                dialog.setContentView(R.layout.dialog_tinhtrang_edit);

                                TextInputEditText tinhtranga = (TextInputEditText) dialog.findViewById(R.id.edit_tinhtrang_tinhtrang);
                                TextInputEditText hoatdong = (TextInputEditText) dialog.findViewById(R.id.edit_hoatdong_tinhtrang);
                                Spinner spinnertinhtrang = (Spinner) dialog.findViewById(R.id.spnsanedit_tinhtrang);
                                Button btnedit = (Button) dialog.findViewById(R.id.btnedit_tinhtrang);
                                Button btnhuy = (Button) dialog.findViewById(R.id.btnHuyEdit_tinhtrang);



                                tinhtranga.setText(tinhtrang.getTinhtrang());
                                hoatdong.setText(tinhtrang.getHoatdong());
                                SimpleAdapter simpleAdapter = new SimpleAdapter(dialog.getContext(),
                                        getdssan(), android.R.layout.simple_list_item_1,
                                        new String[]{"tensan"},new int[]{android.R.id.text1});

                                spinnertinhtrang.setAdapter(simpleAdapter);


                                btnedit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        tinhtrang.setTinhtrang(tinhtranga.getText().toString());
                                        tinhtrang.setHoatdong(hoatdong.getText().toString());
                                        HashMap<String,Object> hs = (HashMap<String, Object>)
                                                spinnertinhtrang.getSelectedItem();
                                        int masan = (int) hs.get("masan");
                                        String tensan = (String) hs.get("tensan");
                                        tinhtrang.setSan(tensan);

                                        DataBaSe.getInstance(context).dao_tinhtrang().updateTinhtrang(tinhtrang);
                                        dialog.dismiss();
                                        inteLoadData.loadData();

                                    }
                                });

                                btnhuy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
                                break;
                            case R.id.menuxoa:
                                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                                builder.setTitle("DELETE");
                                builder.setMessage("Do you want delete ?");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    DataBaSe.getInstance(context).dao_tinhtrang().deleteTinhTrang(tinhtrang);
                                    inteLoadData.loadData();
                                        Toast.makeText((context), "Đã xóa", Toast.LENGTH_SHORT).show();
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
        return convertView;
    }
    public class ViewHolder{
        TextView itemTensan;
        TextView itemtinhtrang;
        TextView itemhoatdong;
        ImageView itemPtImg_tinhtrang;
    }
    private ArrayList<HashMap<String,Object>> getdssan(){
        listSan = (ArrayList<San>) DataBaSe.getInstance(context.getApplicationContext()).dao_san().getAllSan();

        ArrayList<HashMap<String,Object>> listhm = new ArrayList<>();
        for(San san : listSan){
            HashMap<String , Object> hs = new HashMap<>();
            hs.put("masan",san.getId_san());
            hs.put("tensan",san.getTensan());

            listhm.add(hs);

        }
        return listhm;
    }
}
