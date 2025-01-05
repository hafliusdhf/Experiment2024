package com.example.casper.Experiment2024.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.casper.Experiment2024.R;
import com.example.casper.Experiment2024.data.Book;
import android.widget.TextView;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class BookListFragment extends Fragment {
    private ArrayList<Book> books;
    private BookAdapter bookAdapter;
    private ActivityResultLauncher<Intent> launcherAdd;
    private ActivityResultLauncher<Intent> launcherUpdate;

    public BookListFragment() {
        // Required empty public constructor
    }

    public static BookListFragment newInstance() {
        return new BookListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_list, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_the_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // Initialize books list
        books = (ArrayList<Book>) getListBooks(); // 获取图书列表
        bookAdapter = new BookAdapter(books);
        recyclerView.setAdapter(bookAdapter);
        registerForContextMenu(recyclerView);
        return rootView;
    }

    private List<Book> getListBooks() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("创新工程实践", R.drawable.book_no_name));
        bookList.add(new Book("软件项目管理案例教程（第3版）", R.drawable.book_2));
        bookList.add(new Book("信息安全数学基础（第2版）", R.drawable.book_1));
        return bookList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcherAdd = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String bookName = data.getStringExtra("book_name");
                            books.add(new Book(bookName, R.drawable.book_1)); // 使用默认封面
                            bookAdapter.notifyItemInserted(books.size() - 1);
                        }
                    }
                });

        launcherUpdate = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            int position = data.getIntExtra("position", -1);
                            String bookName = data.getStringExtra("book_name");
                            if (position != -1 && bookName != null) {
                                books.get(position).setTitle(bookName);
                                bookAdapter.notifyItemChanged(position);
                            }
                        }
                    }
                });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0: // 添加
                Intent intentAdd = new Intent(getContext(), AddBookActivity.class);
                launcherAdd.launch(intentAdd);
                break;
            case 1: // 删除
                new AlertDialog.Builder(getContext())
                        .setTitle("确认删除")
                        .setMessage("您确定要删除这本书吗？")
                        .setPositiveButton("是", (dialog, which) -> {
                            books.remove(item.getOrder());
                            bookAdapter.notifyItemRemoved(item.getOrder());
                        })
                        .setNegativeButton("否", null)
                        .show();
                break;
            case 2: // 修改
                Intent intentUpdate = new Intent(getContext(), UpdateBookActivity.class);
                intentUpdate.putExtra("position", item.getOrder());
                intentUpdate.putExtra("book_name", books.get(item.getOrder()).getTitle());
                launcherUpdate.launch(intentUpdate);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    private static class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
        private final List<Book> books;

        public BookAdapter(List<Book> books) {
            this.books = books;
        }

        @NonNull
        @Override
        public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
            return new BookViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
            Book book = books.get(position);
            holder.textViewName.setText(book.getTitle());
            holder.imageViewCover.setImageResource(book.getCoverResourceId());
        }

        @Override
        public int getItemCount() {
            return books.size();
        }

        static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
            TextView textViewName;
            ImageView imageViewCover;

            public BookViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.text_view_book_name);
                imageViewCover = itemView.findViewById(R.id.image_view_book_cover);
                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("操作");
                menu.add(0, 0, getAdapterPosition(), "添加");
                menu.add(0, 1, getAdapterPosition(), "删除");
                menu.add(0, 2, getAdapterPosition(), "修改");
            }
        }
    }
}