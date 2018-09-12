package br.com.senaijandira.mybooks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.senaijandira.mybooks.model.Livro;

public class MainActivity extends AppCompatActivity {

    LinearLayout  listaLivros;

    public static Livro[] livros;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaLivros = findViewById(R.id.listaLivros);

        //Criar um Cadastros Fake

        livros = new Livro[]{
           new Livro(1,Utils.toByteArray(getResources(),R.drawable.pequeno_principe),
                   "O pequeno principe",getString(R.string.pequeno_principe)),

        new Livro(2,Utils.toByteArray(getResources(),R.drawable.cinquenta_tons_cinza),
                "Ciquenta Tons de Cinza ",getString(R.string.pequeno_principe)),


        new Livro(3,Utils.toByteArray(getResources(),R.drawable.kotlin_android),
                "Kotlin com Android",getString(R.string.pequeno_principe)),



        };

    }
        @Override
        protected void onResume(){

        super.onResume();

        listaLivros.removeAllViews();


        for (Livro l : livros){
            criarLivros(l,listaLivros);
        }
    }






    public void criarLivros(Livro livro, ViewGroup root) {

            View v = LayoutInflater.from(this)
            .inflate(R.layout.livro_layout, root, false);


        ImageView imgLivroCapa = v.findViewById(R.id.imglivroCapa);
        TextView txtLivroTitulo = v.findViewById(R.id.txtLivroTitulo);
        TextView txtLivroDescricao = v.findViewById(R.id.txtLivroDescricao);


        imgLivroCapa.setImageBitmap(Utils.toBitmap(livro.getCapa()));
        txtLivroTitulo.setText(livro.getTitulo());
        txtLivroDescricao.setText(livro.getDescricao());

        // Comando para exibir na tela.
        root.addView(v);
    }
}
