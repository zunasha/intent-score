package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //Input Home Team
    private EditText etHomeTeam;
    private String s_HomeTeam;

    //Input Away Team
    private EditText etAwayTeam;
    private String s_AwayTeam;

    //Home Team
    private static final String TAG = "asal";
    private ImageView ivHomeLogo;
    private Bitmap bitmap;
    private Uri imgUri;
    private static final int HOME_REQUEST_CODE = 1;

    //Away Team
    private static final String TAG2 = "asal";
    private ImageView ivAwayLogo;
    private Bitmap bitmap2;
    private Uri imgUri2;
    private static final int AWAY_REQUEST_CODE = 2;

    //Next Button
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity

        //1. Validasi Input Home Team
        etHomeTeam = findViewById(R.id.home_team);

        //2. Validasi Input Away Team
        etAwayTeam = findViewById(R.id.away_team);

        //3. Ganti Logo Home Team
        ivHomeLogo = findViewById(R.id.home_logo);
        ivHomeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), HOME_REQUEST_CODE);
            }
        });

        //4. Ganti Logo Away Team
        ivAwayLogo = findViewById(R.id.away_logo);
        ivAwayLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AWAY_REQUEST_CODE);
            }
        });

        //5. Next Button Pindah Ke MatchActivity
        btnNext = findViewById(R.id.btn_team);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dapatkan Data
                s_HomeTeam = etHomeTeam.getText().toString();
                s_AwayTeam = etAwayTeam.getText().toString();

                Intent move = new Intent(MainActivity.this, MatchActivity.class);

                //Set Data
                move.putExtra("moveHomeTeam",s_HomeTeam);
                move.putExtra("moveAwayTeam", s_AwayTeam);
                move.putExtra("homeImg", imgUri.toString());
                move.putExtra("awayImg", imgUri2.toString());

                startActivity(move);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            Log.d(TAG, "Pilih gambar dicancel");
            return;
        }
        else if (requestCode == HOME_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    imgUri = imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
                    ivHomeLogo.setImageBitmap(bitmap);
                }
                catch (IOException error) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
        else if (requestCode == AWAY_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    imgUri2 = imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri2);
                    ivAwayLogo.setImageBitmap(bitmap);
                }
                catch (IOException error) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG2, error.getMessage());
                }
            }
        }
    }



}
