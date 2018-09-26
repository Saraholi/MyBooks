package br.com.senaijandira.mybooks;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.senaijandira.mybooks.db.MyBooksDatabase;
import br.com.senaijandira.mybooks.model.Livro;

public class MainActivity extends AppCompatActivity {

    LinearLayout  listaLivros;

    public static Livro[] livros;

    // Variavel de acesso ao Banco
    private MyBooksDatabase myBooksDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Criando Instancias do Banco de Dados.
        myBooksDb = Room.databaseBuilder(getApplicationContext(),
                MyBooksDatabase.class,Utils.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();


        listaLivros = findViewById(R.id.listaLivros);

        //Criar um Cadastros Fake

    livros = new Livro[]{
 /*          new Livro(1,Utils.toByteArray(getResources(),R.drawable.pequeno_principe),
                   "O pequeno principe",getString(R.string.pequeno_principe)),

        new Livro(2,Utils.toByteArray(getResources(),R.drawable.cinquenta_tons_cinza),
                "Ciquenta Tons de Cinza ",getString(R.string.pequeno_principe)),


        new Livro(3,Utils.toByteArray(getResources(),R.drawable.kotlin_android),
                "Kotlin com Android",getString(R.string.pequeno_principe)),


*/

        };

    }






        @Override
        protected void onResume(){
        super.onResume();

        // Aqui faz um SELECT no Banco.
        livros = myBooksDb.daoLivro().selecionarTodos();

        listaLivros.removeAllViews();

        for (Livro l : livros){
            criarLivros(l,listaLivros);
        }
    }

    // Aqui faz o DELETE no Banco
    private void deletaLivro(Livro livro, View v){


        //Remover do Banco de dados
        myBooksDb.daoLivro().deletar(livro);

        // Remover da Tela
        listaLivros.removeView(v);

    }



    public void criarLivros(final Livro livro, ViewGroup root) {

            final View v = LayoutInflater.from(this)
            .inflate(R.layout.livro_layout, root, false);


        ImageView imgLivroCapa = v.findViewById(R.id.imglivroCapa);
        TextView txtLivroTitulo = v.findViewById(R.id.txtLivroTitulo);
        TextView txtLivroDescricao = v.findViewById(R.id.txtLivroDescricao);

        ImageView imgDeleteLivro = v.findViewById(R.id.imgDeleteLivro);
        ImageView imgEditar = v.findViewById(R.id.imgEditar);

        imgDeleteLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deletaLivro(livro,v);

            }
        });



        imgLivroCapa.setImageBitmap(Utils.toBitmap(livro.getCapa()));
        txtLivroTitulo.setText(livro.getTitulo());
        txtLivroDescricao.setText(livro.getDescricao());

        // Comando para exibir na tela.
        root.addView(v);
    }

    public void enviarLivroLidos(View view) {

    }


    public void enviarLivroLer(View view) {

    }

    public void abrirCadastro(View view) {

        startActivity(new Intent(this, CadastroActivity.class));
    }
}
