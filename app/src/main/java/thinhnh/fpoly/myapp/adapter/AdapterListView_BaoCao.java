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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import thinhnh.fpoly.myapp.R;
import thinhnh.fpoly.myapp.csdl.DTO.BaoCao;
import thinhnh.fpoly.myapp.csdl.DTO.HoaDon;
import thinhnh.fpoly.myapp.csdl.DTO.KhungGio;
import thinhnh.fpoly.myapp.csdl.DTO.LoaiSan;
import thinhnh.fpoly.myapp.csdl.DTO.San;
import thinhnh.fpoly.myapp.csdl.DTO.TrangThaiHoaDon;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;
import thinhnh.fpoly.myapp.interfaces.InteLoadData;

public class AdapterListView_BaoCao extends BaseAdapter {

    ArrayList<BaoCao> list = new ArrayList<>();

    Context context;
    InteLoadData intels;
    ArrayList<San> listSan = new ArrayList<>();

    public AdapterListView_BaoCao(Context context, InteLoadData intels) {
        this.context = context;
        this.intels = intels;
    }

    public void setdata(ArrayList<BaoCao> list) {
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
        BaoCao hd = list.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_baocaoo, null);

            viewHolder.mota = (TextView) view.findViewById(R.id.mota);



            viewHolder.itemHdtensan = (TextView)  view.findViewById(R.id.item_bctensan);

            viewHolder.itemPtImgtd = (ImageView)  view.findViewById(R.id.item_pt_imgtd);


            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.itemHdtensan.setText("Sân:"+hd.getTensan());
        viewHolder.mota.setText("Mô Tả:" +hd.getMota());
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
                                dialogEdit.setContentView(R.layout.dialog_bc_edit);


                                Spinner    spntensanedit = (Spinner) dialogEdit.findViewById(R.id.spntensanedit);
                                EditText   motaeditt = dialogEdit.findViewById(R.id.motaedit);
                                SimpleAdapter simpleAdapter1 = new SimpleAdapter(dialogEdit.getContext(), getdssan(),
                                        android.R.layout.simple_list_item_1, new String[]{"tensan"},
                                        new int[]{android.R.id.text1} );
                                spntensanedit.setAdapter(simpleAdapter1);


                                Button btneditcs = (Button) dialogEdit.findViewById(R.id.btneditcs);
                                Button  btnHuyEditcs = (Button) dialogEdit.findViewById(R.id.btnHuyEditcs);



                                btneditcs.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        HashMap<String,Object> hs1 = (HashMap<String, Object>) spntensanedit.getSelectedItem();

                                        String tensan = (String) hs1.get("tensan");
                                        String giasan = (String) hs1.get("giasan");

                                        hd.setTensan(tensan);
                                        hd.setMota(motaeditt.getText().toString());




                                        DataBaSe.getInstance(context).dao_baoCao().updataBC(hd);
                                        intels.loadData();
                                        Toast.makeText(context, "Đã sửa thành công!!!", Toast.LENGTH_SHORT).show();
                                        dialogEdit.dismiss();
                                    }
                                });
                                btnHuyEditcs.setOnClickListener(new View.OnClickListener() {
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
                                        DataBaSe.getInstance(context).dao_baoCao().deleteBc(hd);
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
         TextView itemHdtensan;
         TextView mota;
         ImageView itemPtImgtd;








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



}
