package com.example.casper.Experiment2024.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.casper.Experiment2024.Activity.BookDetailsActivity; // 确保导入 BookDetailsActivity
import com.example.casper.Experiment2024.Activity.Hello1880Activity;
import com.example.casper.Experiment2024.R;
import com.example.casper.Experiment2024.adapter.BookAdapter;
import com.example.casper.Experiment2024.data.Book;
import com.example.casper.Experiment2024.data.BookRepository;
import java.util.List;

public class BookListFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<Book> books;
    private static final String FILE_NAME = "books_data";

    public BookListFragment() {
        super(R.layout.fragment_book_list); // 确保布局文件存在
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState); // 修改此处，按照正确的参数要求传入view和savedInstanceState
        recyclerView = view.findViewById(R.id.recycler_view);
        books = loadBooks();
        bookAdapter = new BookAdapter(books, (Hello1880Activity) requireActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(bookAdapter);
        registerForContextMenu(recyclerView);
    }

    private List<Book> loadBooks() {
        return BookRepository.loadBooks(requireContext());
    }

    // 保存书籍数据
    private void saveBooks() {
        BookRepository.saveBooks(books, requireContext()); // 使用 BookRepository 的 saveBooks 方法
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (info == null) return super.onContextItemSelected(item);
        int position = info.position; // 现在可以安全地获取位置了
        if (item.getItemId() == R.id.menu_add) {
            launchAddEditActivity(null);
            return true;
        } else if (item.getItemId() == R.id.menu_edit) {
            launchAddEditActivity(position);
            return true;
        } else if (item.getItemId() == R.id.menu_delete) {
            showDeleteConfirmationDialog(position);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    private void launchAddEditActivity(Integer position) {
        Intent intent = new Intent(requireActivity(), BookDetailsActivity.class);
        if (position != null) {
            intent.putExtra("book", books.get(position));
            intent.putExtra("position", position);
        }
        startActivityForResult(intent, 1); // 使用 startActivityForResult 以获取结果
    }

    private void showDeleteConfirmationDialog(final int position) {
        new AlertDialog.Builder(requireContext())
                .setTitle("确认删除")
                .setMessage("您确定要删除这本书吗？")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    books.remove(position);
                    bookAdapter.notifyItemRemoved(position);
                    bookAdapter.notifyItemRangeChanged(position, books.size() - position);
                    saveBooks(); // 删除后保存数据
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
}