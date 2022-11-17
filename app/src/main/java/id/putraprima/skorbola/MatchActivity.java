package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    private TextView tvTxtHome, tvTxtAway, tvScoreHome, tvScoreAway;
    private ImageView ivHomeLogo, ivAwayLogo;
    private Integer iHomeScore, iAwayScore;
    private Button btnAddHome, btnAddHome2, btnAddHome3;
    private Button btnAddAway, btnAddAway2, btnAddAway3;
    private Button btnResult, btnReset;
    private String s_Winner,s_TxtHome, s_TxtAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        getSupportActionBar().hide();

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

        //1.Menampilkan detail match sesuai data dari main activity
        tvTxtHome = findViewById(R.id.txt_home);
        tvTxtAway = findViewById(R.id.txt_away);
        ivHomeLogo = findViewById(R.id.home_logo);
        ivAwayLogo = findViewById(R.id.away_logo);

        s_TxtHome = getIntent().getExtras().getString("moveHomeTeam");
        s_TxtAway = getIntent().getExtras().getString("moveAwayTeam");

        Bundle bundle = getIntent().getExtras();
        tvTxtHome.setText(s_TxtHome);
        tvTxtAway.setText(s_TxtAway);
        ivHomeLogo.setImageURI(Uri.parse(bundle.getString("homeImg")));
        ivAwayLogo.setImageURI(Uri.parse(bundle.getString("awayImg")));;

        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        tvScoreHome = findViewById(R.id.score_home);
        tvScoreAway = findViewById(R.id.score_away);
        btnAddHome = findViewById(R.id.btn_add_home);
        btnAddHome2 = findViewById(R.id.btn_add_home2);
        btnAddHome3 = findViewById(R.id.btn_add_home3);
        btnAddAway = findViewById(R.id.btn_add_away);
        btnAddAway2 = findViewById(R.id.btn_add_away2);
        btnAddAway3 = findViewById(R.id.btn_add_away3);

        iHomeScore = 0;
        iAwayScore = 0;
        tvScoreHome.setText(String.valueOf(iHomeScore));
        tvScoreAway.setText(String.valueOf(iAwayScore));

        btnAddHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iHomeScore += 1;
                tvScoreHome.setText(String.valueOf(iHomeScore));
            }
        });
        btnAddHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iHomeScore += 2;
                tvScoreHome.setText(String.valueOf(iHomeScore));
            }
        });
        btnAddHome3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iHomeScore += 3;
                tvScoreHome.setText(String.valueOf(iHomeScore));
            }
        });

        btnAddAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAwayScore += 1;
                tvScoreAway.setText(String.valueOf(iAwayScore));
            }
        });
        btnAddAway2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAwayScore += 2;
                tvScoreAway.setText(String.valueOf(iAwayScore));
            }
        });
        btnAddAway3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAwayScore += 3;
                tvScoreAway.setText(String.valueOf(iAwayScore));
            }
        });

        //2.1 Menambah Tombol Reset
        btnReset = findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iHomeScore =0 ;
                tvScoreHome.setText(String.valueOf(iHomeScore));

                iAwayScore = 0;
                tvScoreAway.setText(String.valueOf(iAwayScore));

            }
        });

        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
        btnResult = findViewById(R.id.btn_result);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_Winner = "empty";
                if(iHomeScore > iAwayScore){
                    s_Winner = s_TxtHome;
                }
                else if (iHomeScore < iAwayScore){
                    s_Winner = s_TxtAway;
                }
                else {
                    s_Winner = "draw";
                }

                Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
                intent.putExtra("winner", s_Winner);
                startActivity(intent);
            }
        });

    }
}
