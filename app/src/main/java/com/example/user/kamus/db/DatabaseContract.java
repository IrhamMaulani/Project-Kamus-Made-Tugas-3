package com.example.user.kamus.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_IND_ENG = "table_ind_eng";
    static String TABLE_ENG_IND = "table_eng_ind";

    static final class KamusColumns implements BaseColumns {

        // Kolom Kalimat
        static String KALIMAT = "kalimat";
        // Kolom Arti
        static String ARTI = "arti";

    }
}
