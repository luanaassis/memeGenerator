package br.ifmg.edu.bsi.progmovel.shareimage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NovoTamanhoActivity extends AppCompatActivity {
    public static String EXTRA_TAMANHO_ATUAL = "br.ifmg.edu.bsi.progmovel.shareimage1.tamanho_atual";
    public static String EXTRA_NOVO_TAMANHO = "br.ifmg.edu.bsi.progmovel.shareimage1.novo_tamanho";

    private EditText etTamanho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_tamanho);
        etTamanho = findViewById(R.id.etTamanho);

        Intent intent = getIntent();
        String tamanhoAtual = intent.getStringExtra(EXTRA_TAMANHO_ATUAL);
        etTamanho.setText(tamanhoAtual);
    }
    public void enviarNovoTamanho(View v) {
        String novoTamanho = etTamanho.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NOVO_TAMANHO, novoTamanho);
        setResult(RESULT_OK, intent);
        finish();
    }
}