package com.example.doanandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdapterTheLoai extends ArrayAdapter<Genre> {
    private Context context;

    public AdapterTheLoai(Context context, List<Genre> genres) {
        super(context, 0, genres);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Genre genre = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_theloai, parent, false);
        }

        TextView TLTitle = convertView.findViewById(R.id.TLTitle);
        TextView TLSoluong = convertView.findViewById(R.id.TLSoluong);
        ImageButton imageButton = convertView.findViewById(R.id.imageMenuTL);

        TLTitle.setText(genre.getTitleTL());
        TLSoluong.setText(String.valueOf(genre.getQuanlityTL()));
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo một PopupMenu
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                // Thêm các item từ file menu
                popupMenu.getMenuInflater().inflate(R.menu.theloai_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if(id == R.id.updateTL){
                            Intent intent = new Intent(context, UpdateTheLoai.class);
                            intent.putExtra("TitleGenre", genre.getTitleTL());
                            intent.putExtra("SoLuongBook", genre.getQuanlityTL());
                            context.startActivity(intent);
                            return true;
                        }
                        else if(id == R.id.deleteTL){
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("Thông tin thể loại");
                            builder.setMessage("Bạn có muốn thực hiện hành động với thể loại này?\n\n" +
                                    "Thể loại: " + genre.getTitleTL() + "\n" +
                                    "Số lượng: " + genre.getQuanlityTL());

                            // Nút Xác nhận
                            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Thêm logic xử lý nút Xóa
                                    remove(genre); // Xóa sách khỏi danh sách
                                    notifyDataSetChanged(); // Cập nhật Adapter
                                }
                            });

                            // Nút Hủy
                            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.create().show();
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        return convertView;
    }
}
