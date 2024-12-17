package com.example.casper.Experiment2024.data;

import android.content.Context; // 添加导入语句
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static final String FILE_NAME = "books_data";

    public static void saveBooks(List<Book> books, Context context) {
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(books);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Book> loadBooks(Context context) {
        try (FileInputStream fis = context.openFileInput(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<Book>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // 如果读取失败，返回空列表
        }
    }
}