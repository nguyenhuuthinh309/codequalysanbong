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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import thinhnh.fpoly.myapp.R;
import thinhnh.fpoly.myapp.csdl.DTO.DichVu;
import thinhnh.fpoly.myapp.csdl.DTO.LoaiSan;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;
import thinhnh.fpoly.myapp.interfaces.InteLoadData;

public class AdapterListView_DichVu extends BaseAdapter {
    ArrayList<DichVu> list = new ArrayList<>();
    Context context;
    InteLoadData intels;

    public AdapterListView_DichVu(Context context, InteLoadData intels) {
        this.context = context;
        this.intels = intels;
    }

    public void setdata(ArrayList<DichVu> list) {
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
        DichVu dv = list.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_dv, null);

            viewHolder.itemTendv = (TextView) view.findViewById(R.id.item_tendv);
            viewHolder.itemGiaDV = (TextView) view.findViewById(R.id.item_giaDV);
            viewHolder.itemPtImgtd = (ImageView) view.findViewById(R.id.item_pt_imgtd);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.itemTendv.setText(dv.getTendv());
        viewHolder.itemGiaDV.setText(dv.getGiadv());
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
                                dialogEdit.setContentView(R.layout.dialog_dv_edit);





                                TextInputEditText  tendvedit = (TextInputEditText) dialogEdit.findViewById(R.id.tendvedit);
                                TextInputEditText   giadvedit = (TextInputEditText) dialogEdit.findViewById(R.id.giadvedit);
                                Button  btneditls = (Button) dialogEdit.findViewById(R.id.btneditls);
                                Button  btnHuyEditls = (Button) dialogEdit.findViewById(R.id.btnHuyEditls);



                                tendvedit.setText(dv.getTendv());
                                giadvedit.setText(dv.getGiadv());

                                btneditls.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
              dv.setTendv(tendvedit.getText().toString());
              dv.setGiadv(giadvedit.getText().toString());


                                        DataBaSe.getInstance(context).dao_dv().updataDV(dv);
                                        intels.loadData();
                                        Toast.makeText(context, "Đã sửa thành công!!!", Toast.LENGTH_SHORT).show();
                                        dialogEdit.dismiss();
                                    }
                                });
                                btnHuyEditls.setOnClickListener(new View.OnClickListener() {
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
                                        DataBaSe.getInstance(context).dao_dv().deleteDV(dv);
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



         TextView itemTendv;
         TextView itemGiaDV;
         ImageView itemPtImgtd;











    }
}
