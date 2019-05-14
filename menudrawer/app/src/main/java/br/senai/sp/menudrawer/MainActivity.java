package br.senai.sp.menudrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawar_layout);


        navigationView.setNavigationItemSelectedListener(this);

        //** Criar objeto que exibe o navigation drawer
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.open_drawar,
                        R.string.close_drawar);


        drawerLayout.addDrawerListener(toggle);

        /// *** sincronizar o estado do drawar
        toggle.syncState();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.entrada_veiculo:
                Toast.makeText(this, "Entrada de Veiculo", Toast.LENGTH_LONG).show();

                break;
            case R.id.saida_veiculo:
                Toast.makeText(this, "Saida de Veiculo", Toast.LENGTH_LONG).show();
                break;

            case R.id.cadastrar_mensalista:
                Toast.makeText(this, "Cadastrar Mensalista", Toast.LENGTH_LONG).show();
                break;

            case R.id.receber_mensalista:
                Toast.makeText(this, "Receber Mensalista", Toast.LENGTH_LONG).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
    }else{
        Toast.makeText(this, "Volte Sempre", Toast.LENGTH_LONG).show();
        super.onBackPressed();
    }



    }
}