package yugeriya.example.quixote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> Title=new ArrayList<>();
    static ArrayList<String> description=new ArrayList<>();
    static ArrayList<Uri> images=new ArrayList<>();

    static NotesAdapter notesAdapter;
    static ListView listView;

    static SharedPreferences sharedPreferences;


    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView=findViewById(R.id.list_notes);
        sharedPreferences=getSharedPreferences("shared_prefs",MODE_PRIVATE);


        if(Title==null){
            Title.add("Title");
            description.add("Description");
            images.add(Uri.EMPTY);
        }
        notesAdapter=new NotesAdapter(getApplicationContext(),Title,description,images);
        listView.setAdapter(notesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent update=new Intent(getApplicationContext(),add_notes.class);
                update.putExtra("Index",i);
                startActivity(update);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int itemToDelete=i;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete")
                        .setMessage("Delete note")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                description.remove(itemToDelete);
                                Title.remove(itemToDelete);
                                images.remove(itemToDelete);
                                notesAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("NO",null)
                        .show();
                return true;
            }
        });

        //Navigation View Setup
        String email_s = sharedPreferences.getString("Email", "Email");
        String username_s = sharedPreferences.getString("Name", "USERNAME");

        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Navigation drawer item click listener
                switch (item.getItemId()) {
                    case R.id.add_new:
                        Intent update=new Intent(getApplicationContext(),add_notes.class);
                        update.putExtra("Index",-1);
                        startActivity(update);
                        break;
                    case R.id.l_s:
                        Intent log_page=new Intent(getApplicationContext(),login_signup.class);
                        startActivity(log_page);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        Toolbar toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar.setTitle("QUIXOTE");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        TextView name_nav = headerView.findViewById(R.id.header_textView);
        TextView email_nav = headerView.findViewById(R.id.header_email);
        ImageButton image = headerView.findViewById(R.id.header_imageView);

        email_nav.setText(email_s);
        name_nav.setText(username_s);
        image.setImageResource(R.drawable.back);

    }

}