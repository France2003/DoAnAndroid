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

import java.util.List;

public class AdapterTacGia extends ArrayAdapter<Author> {
    private Context context;
    public AdapterTacGia(Context context, List<Author>authors) {
        super(context, 0, authors);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Author author = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_tacgia, parent, false);
        }

        TextView TGTitle = convertView.findViewById(R.id.titleAuthor);
        TextView TGYear = convertView.findViewById(R.id.yearAuthor);
//        TextView TGInformation = convertView.findViewById(R.id.informationAuthor);
        ImageButton imageButton = convertView.findViewById(R.id.imageMenuTG);
        //
        TGTitle.setText(author.getTitleAuthor());
        TGYear.setText(String.valueOf(author.getYearAuthor()));
//        TGInformation.setText(author.getInformationAuthor());
        //
      imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.tacgia_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if(id == R.id.updateAuthor){
                            Intent intent = new Intent(context, UpdateTacGia.class);
                            intent.putExtra("TitleAuthor", author.getTitleAuthor());
                            intent.putExtra("YearAuthor", author.getYearAuthor());
                            intent.putExtra("InformationAuthor", author.getInformationAuthor());
                            context.startActivity(intent);
                            return true;
                        }
                        else if(id == R.id.deleteAuthor){
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("Thông tin thể loại");
                            builder.setMessage("Bạn có muốn thực hiện hành động với thể loại này?\n\n" +
                                    "Tên tác giả: " + author.getTitleAuthor() + "\n" +
                                    "Năm sinh: " + author.getYearAuthor());
                            // Nút Xác nhận
                            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Thêm logic xử lý nút Xóa
                                    remove(author); // Xóa sách khỏi danh sách
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
                        else if(id == R.id.informationAuthor){
                            Intent intent = new Intent(context, InformationTacGia.class);
                            context.startActivity(intent);
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
