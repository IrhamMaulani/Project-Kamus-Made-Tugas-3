package com.example.user.kamus.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.user.kamus.model.KamusModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.user.kamus.db.DatabaseContract.KamusColumns.ARTI;
import static com.example.user.kamus.db.DatabaseContract.KamusColumns.KALIMAT;
import static com.example.user.kamus.db.DatabaseContract.TABLE_IND_ENG;

public class KamusHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public KamusHelper(Context context){
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<KamusModel> getDataByName(String nama){
        String result = "";
        Cursor cursor = database.query(TABLE_IND_ENG,null,KALIMAT+" LIKE ?",new String[]{nama},null,null,_ID + " ASC",null);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount()>0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setKalimat(cursor.getString(cursor.getColumnIndexOrThrow(KALIMAT)));
                kamusModel.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public ArrayList<KamusModel> getAllData(){
        Cursor cursor = database.query(TABLE_IND_ENG,null,null,null,null,null,_ID+ " ASC",null);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount()>0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setKalimat(cursor.getString(cursor.getColumnIndexOrThrow(KALIMAT)));
                kamusModel.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayList.add(kamusModel);
                cursor.moveToNext();


            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(KamusModel mahasiswaModel){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(KALIMAT, mahasiswaModel.getKalimat());
        initialValues.put(ARTI, mahasiswaModel.getArti());
        return database.insert(TABLE_IND_ENG, null, initialValues);
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    public void insertTransaction(KamusModel kamusModel){
        String sql = "INSERT INTO "+TABLE_IND_ENG+" ("+KALIMAT+", "+ARTI
                +") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusModel.getKalimat());
        stmt.bindString(2, kamusModel.getArti());
        stmt.execute();
        stmt.clearBindings();

    }

    public int update(KamusModel kamusModel){
        ContentValues args = new ContentValues();
        args.put(KALIMAT, kamusModel.getKalimat());
        args.put(ARTI, kamusModel.getArti());
        return database.update(TABLE_IND_ENG, args, _ID + "= '" + kamusModel.getId() + "'", null);
    }


    public int delete(int id){
        return database.delete(TABLE_IND_ENG, _ID + " = '"+id+"'", null);
    }

}
