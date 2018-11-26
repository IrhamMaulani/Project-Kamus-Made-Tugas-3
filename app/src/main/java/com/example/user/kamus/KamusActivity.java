package com.example.user.kamus;

import android.content.Intent;
import android.database.SQLException;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.kamus.adapter.KamusAdapter;
import com.example.user.kamus.db.DatabaseContract;
import com.example.user.kamus.db.KamusHelper;
import com.example.user.kamus.model.KamusModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class KamusActivity extends AppCompatActivity {

    KamusAdapter kamusAdapter;
    KamusHelper kamusHelper;
    String stringCari = "";
    ArrayList<KamusModel> list = new ArrayList<>();
    AppPreference appPreference ;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(R.string.english_indo);
        kamusAdapter = new KamusAdapter(this);
        kamusHelper = new KamusHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(kamusAdapter);
        getData();
    }

    public void getData(){
        appPreference =  new AppPreference(KamusActivity.this);


        try {
            kamusHelper.open();
        if (stringCari.isEmpty()){
          list = kamusHelper.getAllData(appPreference.getModeBahasa());
        }else{
            list = kamusHelper.getDataByName(stringCari,appPreference.getModeBahasa());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        appPreference =  new AppPreference(KamusActivity.this);

        switch (item.getItemId()){
            case R.id.action_english_to_indo:
                appPreference.setModeBahasa("table_eng_ind");
                getSupportActionBar().setTitle(R.string.english_indo);
                stringCari = "";
                getData();
                break;
            case R.id.action_indo_to_english:
                //TODO
                appPreference.setModeBahasa("table_ind_eng");
                getSupportActionBar().setTitle(R.string.indo_english);
                stringCari = "";
                getData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
