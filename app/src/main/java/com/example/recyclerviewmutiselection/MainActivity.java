package com.example.recyclerviewmutiselection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.my_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        CategoryAdapter adapter = new CategoryAdapter();
        adapter.setHasStableIds(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(decoration);

        SelectionTracker<Long> selectionTracker = new SelectionTracker.Builder<Long>(
                "category_lis",
                recyclerView,
                new MyItemKeyProvider(recyclerView),
                new MyItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage())
                .build();

        selectionTracker.addObserver(new SelectionTracker.SelectionObserver<Long>() {
            @Override
            public void onSelectionChanged() {
                super.onSelectionChanged();
                Toast.makeText(MainActivity.this, "Item selected", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setTracker(selectionTracker);
    }
}