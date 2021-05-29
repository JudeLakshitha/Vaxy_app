package com.vaxy.vaxyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.vaxy.vaxyapp.helper.AppProgressDialog;

public class MainActivity extends AppCompatActivity {

    private NavigationView topNavigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private View navHeader;
    private WebView webView;
    private String Url ="https://vaxy.co.uk/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        topNavigationView = findViewById(R.id.navView);
        navHeader = topNavigationView.getHeaderView(0);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);


        startWebView(Url);


        topNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if (item.getItemId() == R.id.naviProducts) {
                    Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.naviBlog) {
                    Intent intent = new Intent(getApplicationContext(), BlogActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.naviWaxing) {
                    Intent intent = new Intent(getApplicationContext(), WaxActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.naviWishList) {
                    Intent intent = new Intent(getApplicationContext(), WishListActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.naviAboutUs) {
                    Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.naviContactUs) {
                    Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.naviMenWaxing) {
                    Intent intent = new Intent(getApplicationContext(), MenWaxingActivity.class);
                    startActivity(intent);
                }

                return false;
            }
        });

    }

    private void startWebView(String url) {

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        AppProgressDialog.showProgressDialog(this, "Loading page...", false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                AppProgressDialog.hideProgressDialog();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(MainActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });
        webView.loadUrl(url);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}