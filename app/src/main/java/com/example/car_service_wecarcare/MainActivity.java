package com.example.car_service_wecarcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
//import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainApAdapter  mainApAdapter;
   // TextView appHome;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.aprv);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new CustomLinearLayoutManager(this));

        FirebaseRecyclerOptions<MainApModel> options =
                new FirebaseRecyclerOptions.Builder<MainApModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://carserviceapp-fb926-default-rtdb.firebaseio.com/")
                                .getReference().child("appoinment"), MainApModel.class)
                                .build();
        mainApAdapter = new MainApAdapter(options);
        recyclerView.setAdapter(mainApAdapter);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,addApActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainApAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainApAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //search
        getMenuInflater().inflate(R.menu.searchap,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void txtSearch(String str)
    {
        //search by vehicle name
        FirebaseRecyclerOptions<MainApModel> options =
                new FirebaseRecyclerOptions.Builder<MainApModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://carserviceapp-fb926-default-rtdb.firebaseio.com/")
                                .getReference().child("appoinment").orderByChild("vehicleName").startAt(str).endAt(str+"~"), MainApModel.class)
                        .build();

        mainApAdapter = new MainApAdapter(options);
        mainApAdapter.startListening();
        recyclerView.setAdapter(mainApAdapter);
    }


}