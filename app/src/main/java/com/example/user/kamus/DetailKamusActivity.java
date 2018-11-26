package com.example.user.kamus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.user.kamus.model.KamusModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailKamusActivity extends AppCompatActivity {
    public static  String EXTRA_KAMUS = "extra_kamus";
    @BindView(R.id.text_kalimat)
    TextView textKalimat;
    @BindView(R.id.text_arti)
    TextView textArti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kamus);
        ButterKnife.bind(this);

        KamusModel kamusModel = getIntent().getParcelableExtra(EXTRA_KAMUS);

        textKalimat.setText(kamusModel.getKalimat());
        textArti.setText(kamusModel.getArti());

    }
}
