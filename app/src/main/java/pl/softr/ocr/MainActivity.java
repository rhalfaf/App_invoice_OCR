package pl.softr.ocr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.widget.Button;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;

import pl.softr.ocr.database.AppDatabase;
import pl.softr.ocr.database.entity.Seller;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavController navController;
    private NavigationView navigationView;
    private NavHostFragment navHostFragment;


    private static int CAMERA_REQUEST_CODE = 101;

    private Button captureImage;
    private Button captureImage2;
    private File outputDirectory;
    private Uri photoURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        NavHostFragment f = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_content);
        if (f != null) {
            navController = f.getNavController();
        }
        navigationView = findViewById(R.id.navigationView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show and Manage the Drawer and Back Icon
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);


        // Handle Navigation item clicks
        // This works with no further action on your part if the menu and destination id’s match.
        NavigationUI.setupWithNavController(navigationView, navController);

        AppDatabase db = AppDatabase.getInstance(this);
        AppDatabase.writeDatabaseExecutor.execute(() -> {
            db.sellerDAO().addSeller(new Seller());
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Allows NavigationUI to support proper up navigation or the drawer layout
        // drawer menu, depending on the situation.
        return NavigationUI.navigateUp(navController, drawerLayout);

    }
}