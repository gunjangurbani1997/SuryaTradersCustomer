package com.example.suryatraderscustomer.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.tokenmanager.TokenManager;
import com.example.suryatraderscustomer.viewmodels.CartViewModel;
import com.example.suryatraderscustomer.viewmodels.OrderViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class CategoryOneActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;

    private DrawerLayout drawerLayout;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    CartViewModel cartViewModel;
    OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_category_one);
        drawerLayout=findViewById(R.id.layout_drawer);
        navigationView=findViewById(R.id.nav_view);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        appBarConfiguration=new AppBarConfiguration.Builder(new int[] {R.id.firstCategoryFragment, R.id.cartFragment,R.id.allOrderDetailsFragment,R.id.changePasswordFragment,R.id.aboutFragment,R.id.feedbackFormFragment,R.id.logoutFragment}).setDrawerLayout(drawerLayout).build();

        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       /* getMenuInflater().inflate(R.menu.menu,menu);
        return true;*/
            return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onSupportNavigateUp() {

        return NavigationUI.navigateUp(navController,appBarConfiguration);

    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.firstCategoryFragment:
            case R.id.allOrderDetailsFragment:
            case R.id.aboutFragment:
            case R.id.changePasswordFragment:
            case R.id.feedbackFormFragment:
            case R.id.cartFragment:
                if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);

            case R.id.logoutFragment:
                if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                TokenManager tokenManager=new TokenManager(this);
                tokenManager.logoutUser();
                Log.d("CategoryOneActivity","Logged out");
        }

        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}