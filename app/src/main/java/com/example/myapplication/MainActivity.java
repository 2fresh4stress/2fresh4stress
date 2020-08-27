package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import android.os.FileUriExposedException;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

public interface OnLongClickListener{
    void OnItemLongClicked(int position);
}

    List<String> items;

    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvItems);

        loadItems();

        ItemsAdapter.OnLongClickListner onLongClickListner = new ItemsAdapter.OnLongClickListner() {
            @Override
            public void onItemLongClicked (int position) {
                //Delete the item from model
                items.remove(position)
                //Notify the adapter
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(),"Item was removed", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };
        final ItemsAdapter itemsAdapter = new ItemsAdapter(items,OnLongClickListner);
        rvItems.setAdapter (itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String todoItem = etItem.getText().toString();
                // Add item to the model
                items.add(todoItem);
                // Notify adapter that an item is inserted
                itemsAdapter.notifyItemInserted(  items.size() - 1);
                etItem.setText("");
                Toast.makeText(getApplicationContext(),"Item was added", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        });
    }

    private File getDataFile (){
        return new File(getFilesDir(),  "data.txt");
    }

    // This function will load items by reading every line of the data file
    private void loadItems () {
        items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset());
    } catch (IOException e) {
        Log.e( "MainActivity", "Error reading items", e);
        items = new ArrayList<>();
    }
    // This function saves items by writing them into the data file
    private void saveItems() {
        FileUtils.writeLines(getDateFile(), items);
    } catch(IOException e) {
        Log.e ("MainActivity", "Error writing Items", e);
    }

}
