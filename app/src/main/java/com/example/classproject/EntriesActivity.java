package com.example.classproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.classproject.adapter.StudentEntryAdapter;
import com.example.classproject.adapter.SwipeToDeleteCallback;
import com.example.classproject.db.StudentEntryDBHelper;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classproject.databinding.ActivityEntriesBinding;
;

public class EntriesActivity extends AppCompatActivity {

    private ActivityEntriesBinding binding;
    RecyclerView recyclerView;
    private StudentEntryAdapter entryAdapter;

    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEntriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle("Entries");

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(view -> {
            startActivity(new Intent(EntriesActivity.this, MainActivity.class));
        });

//        display data on the recycler view
        displayData();
//        enable swipe on delete and undo
        enableSwipeToDeleteAndUndo();

    }
    private void  displayData(){
        StudentEntryDBHelper dbHelper = new StudentEntryDBHelper(this, null, null, 1);

//        find the recyclerview by id
        recyclerView = findViewById(R.id.entriesRecyclerView);

//        set this to improve performance
        /*RecyclerView can perform several optimizations if it can know
         in advance that RecyclerView's size is not affected by the adapter contents.*/
        recyclerView.setHasFixedSize(true);

//        create and set the layout manager as linear layout
         layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        Toast.makeText(this,  dbHelper.getEveryone().toString(), Toast.LENGTH_SHORT).show();
        entryAdapter = new StudentEntryAdapter(dbHelper.getEveryone());
        recyclerView.setAdapter(entryAdapter);
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final StudentModel item = entryAdapter.getData().get(position);

                entryAdapter.removeItem(position);



                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", view -> {

                    entryAdapter.restoreItem(item, position);
                    recyclerView.scrollToPosition(position);
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.setBackgroundTint(Color.DKGRAY);
                snackbar.setTextColor(Color.WHITE);
                snackbar.show();
            }
        };
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }
}
