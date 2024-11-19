package com.example.doanandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private Context context;
    private List<Book> bookList;

    // Constructor
    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_sach, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.titleTextView.setText(book.getTitle());
        holder.authorTextView.setText(book.getAuthor());

        // Xử lý sự kiện nhấn vào icon menu
        holder.menuIcon.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(context, holder.menuIcon);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.book_menu, popup.getMenu()); // Sử dụng file menu bạn đã tạo

            popup.setOnMenuItemClickListener(item -> handleMenuItemClick(item, position));
            popup.show();
        });
    }

    private boolean handleMenuItemClick(MenuItem item, int position) {
        int itemId = item.getItemId();
        if (itemId == R.id.update) {
            updateBook(position);
            return true;
        } else if (itemId == R.id.delete) {
            deleteBook(position);
            return true;
        } else if (itemId == R.id.information) {
            showBookInformation(position);
            return true;
        } else {
            return false;
        }
    }

    // Các phương thức riêng lẻ để xử lý các hành động
    private void updateBook(int position) {
        // Xử lý logic cập nhật sách tại vị trí `position`
        // Ví dụ: mở một activity để cập nhật sách
    }

    private void deleteBook(int position) {
        // Xóa sách khỏi danh sách
        bookList.remove(position);
        notifyItemRemoved(position);
    }

    private void showBookInformation(int position) {
        // Hiển thị thông tin chi tiết về sách
        // Ví dụ: mở một dialog để hiển thị chi tiết sách
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    // ViewHolder class cho từng item trong RecyclerView
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, authorTextView;
        ImageView menuIcon;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
//            titleTextView = itemView.findViewById(R.id.book_title1);
//            authorTextView = itemView.findViewById(R.id.book_author1);
//            menuIcon = itemView.findViewById(R.id.imageButton1);
//            titleTextView = itemView.findViewById(R.id.book_title2);
//            authorTextView = itemView.findViewById(R.id.book_author2);
//            menuIcon = itemView.findViewById(R.id.imageButton1);
//            titleTextView = itemView.findViewById(R.id.book_title3);
//            authorTextView = itemView.findViewById(R.id.book_author3);
//            menuIcon = itemView.findViewById(R.id.imageButton1);
        }
    }
}
