package com.example.casper.Experiment2024.Activity;
import java.util.List;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.example.casper.Experiment2024.data.Book;
import com.example.casper.Experiment2024.adapter.BookAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;
import com.example.casper.Experiment2024.R;
import com.example.casper.Experiment2024.data.BookRepository;

public class Hello1880Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<Book> books;
    private ActivityResultLauncher<Intent> addEditLauncher;
    private static final String FILE_NAME = "books_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello1880);
        recyclerView = findViewById(R.id.recycler_view);
        books = loadBooks();
        bookAdapter = new BookAdapter(books, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookAdapter);
        registerForContextMenu(recyclerView);

        addEditLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Book book = (Book) result.getData().getSerializableExtra("book");
                        if (result.getData().hasExtra("position")) {
                            int position = result.getData().getIntExtra("position", -1);
                            if (position >= 0 && position <= books.size()) {
                                books.set(position, book);
                                bookAdapter.notifyItemChanged(position);
                            }
                        } else {
                            books.add(book);
                            bookAdapter.notifyItemInserted(books.size() - 1);
                        }
                    }
                });
    }
    // 保存书籍数据
    private void saveBooks() {
        BookRepository.saveBooks(books, this); // 使用BookRepository的saveBooks方法
    }

    // 加载书籍数据
    private List<Book> loadBooks() {
        return BookRepository.loadBooks(this); // 使用BookRepository的loadBooks方法
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
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
        Intent intent = new Intent(this, BookDetailsActivity.class);
        if (position != null) {
            intent.putExtra("book", books.get(position));
            intent.putExtra("position", position);
        }
        addEditLauncher.launch(intent);
    }

    private void showDeleteConfirmationDialog(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("确认删除")
                .setMessage("您确定要删除这本书吗？")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    books.remove(position);
                    bookAdapter.notifyItemRemoved(position);
                    bookAdapter.notifyItemRangeChanged(position, books.size() - position);
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    public List<Book> getListBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("创新工程实践", R.drawable.book_no_name));
        books.add(new Book("软件项目管理案例教程（第3版）", R.drawable.book_2));
        books.add(new Book("信息安全数学基础（第2版）", R.drawable.book_1));
        return books;
    }
}