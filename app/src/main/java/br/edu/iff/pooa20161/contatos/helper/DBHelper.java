package br.edu.iff.pooa20161.contatos.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa20161.contatos.models.Contato;

/**
 * Created by lglmoura on 03/09/16.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "ContatosAulaDB";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "contatos";
    public static final String ID ="_id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String TELEFONE = "telefone";

    private String[] allColumns = {DBHelper.ID,DBHelper.NOME, DBHelper.EMAIL,
            DBHelper.TELEFONE};
    private SQLiteDatabase database;

    private static final String DATABASE_CREATE = "create table "+TABLE_NAME+
            "(" + ID+"  integer primary key autoincrement, "+
            NOME +" text not null, " + EMAIL + " text not null, " + TELEFONE + " text not null);";

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
    public Contato createContacto(String nome, String email, String telefone)

    {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.NOME, nome);
        values.put(DBHelper.EMAIL,email);
        values.put(DBHelper.TELEFONE,telefone);

        long insertId = database.insert(DBHelper.TABLE_NAME, null, values);
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, DBHelper.ID +
                "=" + insertId,  null,null, null, null);
        cursor.moveToFirst();
        return cursorToContato(cursor);

    }

    private Contato cursorToContato(Cursor cursor)
    {


        Contato contato = new Contato(cursor.getLong(0),cursor.getString(1),  cursor.getString(2),
                cursor.getString(3));

        return   contato;


    }

    public List<Contato> getContatos()
    {
        Cursor cursor = database.rawQuery("select * from contatos", null);
        ArrayList<Contato> contatos = new ArrayList<Contato>();
        cursor.moveToFirst();
        while(!cursor.moveToNext())
        {

            Contato atual = new Contato(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            contatos.add(atual);


        }


        return contatos;
    }

    public Contato getContato(int idContato){

        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, DBHelper.ID +" = " + idContato,
                null,null,
                null,
                null);

        cursor.moveToFirst();

        return   cursorToContato(cursor);
    }
}
