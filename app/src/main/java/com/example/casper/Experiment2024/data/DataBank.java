package com.example.casper.Experiment2024.data;

import android.content.Context;
import android.util.Log;

import com.example.casper.Experiment2024.MainActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataBank {
    private final Context context;
    private final String data_file_name="items.data";

    public DataBank(Context context) {
        this.context= context;
    }

    public ArrayList<Item> readItems() {
        ArrayList<Item>  items = new ArrayList<>();
        try {
            FileInputStream fileIn= context.openFileInput(data_file_name);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // 从输入流中读取对象
            items = (ArrayList<Item>) in.readObject();

            // 关闭输入流
            in.close();

            // 关闭文件输入流
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            Log.e("Experiment2024.data",e.toString());
        }
        return items;
    }

    public void saveItems(ArrayList<Item> items) {

        FileOutputStream fileOut = null;
        try {
            fileOut = context.openFileOutput(data_file_name, Context.MODE_PRIVATE);
            // 创建一个对象输出流
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            // 将对象写入输出流
            out.writeObject(items);

            // 关闭输出流
            out.close();

            // 关闭文件输出流
            fileOut.close();
        } catch (IOException e) {
            Log.e("Experiment2024.data",e.toString());
        }
    }
}
