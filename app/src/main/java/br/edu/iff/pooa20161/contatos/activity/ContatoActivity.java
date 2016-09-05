package br.edu.iff.pooa20161.contatos.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.api.GoogleApiClient;

import br.edu.iff.pooa20161.contatos.R;
import br.edu.iff.pooa20161.contatos.helper.DBHelper;

public class ContatoActivity extends AppCompatActivity {

    EditText nome, email, telefone;
    Button btsalvar;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
        btsalvar = (Button) findViewById(R.id.btSalvarContato);


        btsalvar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
            }
        });


    }

    public void salvar() {

        nome = (EditText) findViewById(R.id.etNomeContato);
        email = (EditText) findViewById(R.id.etEmailContato);
        telefone = (EditText) findViewById(R.id.etTelefoneContato);
        DBHelper helper = new DBHelper(getContext());
        helper.createContacto(nome.getText( ).toString( ), email.getText( ).toString( ),
                telefone.getText( ).toString( ));

    }

    @Override
    public void onStart() {
        super.onStart( );


    }

    @Override
    public void onStop() {
        super.onStop( );


    }
    private Context getContext(){
        return this;
    }
}
