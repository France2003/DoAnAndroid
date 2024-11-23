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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Adapter extends ArrayAdapter<Book> {
    private Context context;
    private Sach sachFragment;

    public Adapter(Context context, List<Book> books, Sach sachFragment) {
        super(context, 0, books);
        this.context = context; // Lưu lại context
        this.sachFragment = sachFragment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_book, parent, false);
        }
        TextView bookTitle = convertView.findViewById(R.id.bookTitle);
        TextView bookAuthor = convertView.findViewById(R.id.bookAuthor);
        ImageButton imgMenu = convertView.findViewById(R.id.imageMenu);

        bookTitle.setText(book.getTitle());
        bookAuthor.setText(book.getAuthor());
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo một PopupMenu
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                // Thêm các item từ file menu
                popupMenu.getMenuInflater().inflate(R.menu.book_menu, popupMenu.getMenu());
                // Xử lý sự kiện khi click vào từng item
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.update) {
//                            Toast.makeText(getContext(), "Bạn chọn Update", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, UpdateBook.class);
                            // Truyền dữ liệu qua màn hình khác
                            intent.putExtra("sachId", book.getId());
                            context.startActivity(intent); // Chạy Activity mới
                            return true;
                        }
                        else if (id == R.id.delete) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("Thông tin sách");
                            builder.setMessage("Bạn có muốn thực hiện hành động với sách này?\n\n" +
                                    "Tiêu đề: " + book.getTitle() + "\n" +
                                    "Tác giả: " + book.getAuthor());

                            // Nút Xác nhận
                            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dataBase dbHelper = new dataBase(context, "QuanLiSach.db", null, 1);
                                    boolean isDeleted = dbHelper.deleteRecord("Sach", "maSach", book.getId());
                                    if (isDeleted) {
                                        remove(book); // Xóa sách khỏi danh sách
                                        notifyDataSetChanged(); // Cập nhật Adapter
                                        Toast.makeText(context, "Xóa sách thành công", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Xóa sách thất bại", Toast.LENGTH_SHORT).show();
                                    }

                                    if (sachFragment != null) {
                                        sachFragment.loadData();
                                    }
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
                        else if (id == R.id.information) {
                            Intent intent = new Intent(context, Information.class);
                            // Truyền dữ liệu qua màn hình khác
                            intent.putExtra("sachId", book.getId());
                            context.startActivity(intent); // Chạy Activity mới
                            return true;
                        }
                        return false;
                    }
                });
                // Hiển thị menu
                popupMenu.show();
            }
        });
        return convertView;
    }


}
