package com.example.casper.Experiment2024.fragment;

import android.os.Bundle;
import android.view.View; // 添加导入语句
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

    public BookListFragment() {
        super(R.layout.fragment_book_list); // 确保布局文件存在
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view); // 使用 view 查找 recycler_view
        books = loadBooks();
        bookAdapter = new BookAdapter(books, (Hello1880Activity) requireActivity()); // 确保 BookAdapter 构造函数支持 Activity
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(bookAdapter);
    }

    private List<Book> loadBooks() {
        return BookRepository.loadBooks(requireContext());
    }
}