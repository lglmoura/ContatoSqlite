package br.edu.iff.pooa20161.contatos.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lglmoura on 03/09/16.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "contatosDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contatos";
    private static final String ID ="_id";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String TELEFONE = "telefone";
    private static final String FOTO = "foto";
    private static final String DATABASE_CREATE = "create table "+TABLE_NAME+
            "(" + ID+"  integer primary key autoincrement, "+
            NOME +" text not null, " + EMAIL + " text not null, " + TELEFONE + " text not null, "+
            FOTO + "BLOB);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(DATABASE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVers, int newVers) {

        Log.w(DBHelper.class.getName(),"Altualizando banco de dados");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);

    }
}
