package br.edu.iff.pooa20161.contatos.adapter;

/**
 * Created by lglmoura on 03/09/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa20161.contatos.helper.DBHelper;
import br.edu.iff.pooa20161.contatos.models.Contato;

public class DBAdapter {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    private String[] allColumns = {DBHelper.ID,DBHelper.NOME, DBHelper.EMAIL,
            DBHelper.TELEFONE, DBHelper.FOTO};

    public DBAdapter(Context context) {
         dbHelper = new DBHelper(context);

    }

    public Contato createContacto(String nome, String email, String telefone,
                                  Bitmap foto)
    {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NOME, nome);
        values.put(DBHelper.EMAIL,email);
        values.put(DBHelper.TELEFONE,telefone);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        foto.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] photo = baos.toByteArray();
        values.put(DBHelper.FOTO, photo);
        long insertId = database.insert(DBHelper.TABLE_NAME, null, values);
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, DBHelper    .ID +
                                "=" + insertId,  null,null, null, null);
        cursor.moveToFirst();
        return cursorToContato(cursor);

    }

    private Contato cursorToContato(Cursor cursor)
    {

        byte[] blob = cursor.getBlob(cursor.getColumnIndex(DBHelper.FOTO));
        Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
        Contato contato = new Contato(cursor.getLong(0),cursor.getString(1),  cursor.getString(2),
                        cursor.getString(3), bmp);

        return   contato;


    }

    public List<Contato> getContatos()
    {
        Cursor cursor = database.rawQuery("select * from contatos", null);
        ArrayList<Contato> contatos = new ArrayList<Contato>();
        cursor.moveToFirst();
        while(!cursor.moveToNext())
        {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex(DBHelper.FOTO));
            Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
            Contato atual = new Contato(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    bmp);
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
