package com.example.foodapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapter.CategoryAdapter;
import com.example.foodapp.Domain.CategoryDomain;
import com.example.foodapp.R;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout dashboard, foods, drinks, beverages, cart;
    private RecyclerView.Adapter catAdapter, bestDealsAdapter;
    private RecyclerView recyclerViewCat, recyclerViewBestDeal;

    private void initRecyclerviewCat() {
        ArrayList<CategoryDomain> items = new ArrayList<>();
        items.add(new CategoryDomain("cat1", "Vegetable"));
        items.add(new CategoryDomain("cat2", "Fruits"));
        items.add(new CategoryDomain("cat3", "Dairy"));
        items.add(new CategoryDomain("cat4", "Drinks"));
        items.add(new CategoryDomain("cat5", "Grain"));

        recyclerViewCat = findViewById(R.id.catView);
        recyclerViewCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        catAdapter = new CategoryAdapter(items);
        recyclerViewCat.setAdapter(catAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);

        drawerLayout = findViewById(R.id.drawerlayout);
        menu = findViewById(R.id.imageView8); // Ensure this ID is correctly set
        dashboard = findViewById(R.id.dashboard);
        foods = findViewById(R.id.Food);
        drinks = findViewById(R.id.drinks);
        beverages = findViewById(R.id.beverages);
        cart = findViewById(R.id.cart);
        initRecyclerviewCat();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer(drawerLayout);
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

        foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(Dashboard.this, Foods.class);
            }
        });

        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(Dashboard.this, Drinks.class);
            }
        });

        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(Dashboard.this, Beverages.class);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(Dashboard.this, Cart.class);
            }
        });
        initLocation();
    }
    private void initLocation() {
        String[] items=new String[]{"LosAngles, USA", "NewYork, USA"};

        final Spinner locationSpin= findViewById(R.id.spinner);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpin.setAdapter(adapter);

    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class<?> secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
}
