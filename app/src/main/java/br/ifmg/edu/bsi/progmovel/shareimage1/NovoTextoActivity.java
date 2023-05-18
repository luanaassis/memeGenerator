package br.ifmg.edu.bsi.progmovel.shareimage1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NovoTextoActivity extends AppCompatActivity {
    public static String EXTRA_TEXTO_ATUAL_I = "br.ifmg.edu.bsi.progmovel.shareimage1.texto_atual_inferior";
    public static String EXTRA_NOVO_TEXTO_I= "br.ifmg.edu.bsi.progmovel.shareimage1.novo_texto_inferior";
    public static String EXTRA_TEXTO_ATUAL_S = "br.ifmg.edu.bsi.progmovel.shareimage1.texto_atual_superior";
    public static String EXTRA_NOVO_TEXTO_S= "br.ifmg.edu.bsi.progmovel.shareimage1.novo_texto_superior";
    private EditText etTextoI;
    private EditText etTextoS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_texto);
        etTextoI = findViewById(R.id.etTextoInferior);
        etTextoS = findViewById(R.id.etTextoSuperior);
        Intent intent = getIntent();
        String textoAtualI = intent.getStringExtra(EXTRA_TEXTO_ATUAL_I);
        String textoAtualS = intent.getStringExtra(EXTRA_TEXTO_ATUAL_S);
        etTextoI.setText(textoAtualI);
        etTextoS.setText(textoAtualS);
    }

    public void enviarNovoTexto(View v) {
        String novoTextoI = etTextoI.getText().toString();
        String novoTextoS = etTextoS.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NOVO_TEXTO_I, novoTextoI);
        intent.putExtra(EXTRA_NOVO_TEXTO_S, novoTextoS);
        setResult(RESULT_OK, intent);
        finish();
    }
}