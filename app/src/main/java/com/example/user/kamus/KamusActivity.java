package com.example.user.kamus;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.kamus.adapter.KamusAdapter;
import com.example.user.kamus.db.KamusHelper;
import com.example.user.kamus.model.KamusModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class KamusActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    KamusAdapter kamusAdapter;
    KamusHelper kamusHelper;
    String stringCari = "";
    ArrayList<KamusModel> list = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus);
        ButterKnife.bind(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        kamusAdapter = new KamusAdapter(this);
        kamusHelper = new KamusHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(kamusAdapter);

        getData();

    }

    public void getData(){
        try {
            kamusHelper.open();
        if (stringCari.isEmpty()){
          list = kamusHelper.getAllData();

           // kamusAdapter.addItem(list);
        }else{
            list = kamusHelper.getDataByName(stringCari);
            //kamusAdapter.addItem(list);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            kamusHelper.close();
        }
        kamusAdapter.addItem(list);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedPresident(list.get(position));
            }
        });

    }

    @OnTextChanged(R.id.edit_cari_kalimat)
    void onTextChanged(CharSequence kalimat, int start, int count, int after) {
        stringCari = kalimat.toString();
        getData();
    }

    private void showSelectedPresident(KamusModel kamusModel){

        kamusModel.setId(kamusModel.getId());
        kamusModel.setKalimat(kamusModel.getKalimat());
        kamusModel.setArti(kamusModel.getArti());

        Intent moveWithObjectIntent = new Intent(KamusActivity.this, DetailKamusActivity.class);
        moveWithObjectIntent.putExtra(DetailKamusActivity.EXTRA_KAMUS, kamusModel);
        startActivity(moveWithObjectIntent);

    }


}
