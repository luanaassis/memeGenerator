package br.ifmg.edu.bsi.progmovel.shareimage1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

/**
 * Cria um meme com um texto e uma imagem de fundo.
 *
 * Você pode controlar o texto, a cor do texto e a imagem de fundo.
 */
public class MemeCreator {
    private String textoInferior;
    private String textoSuperior;
    private int corTexto;
    private float tamanhoFonte;
    private Bitmap fundo;
    private DisplayMetrics displayMetrics;
    private Bitmap meme;
    private boolean dirty; // se true, significa que o meme precisa ser recriado.

    public MemeCreator(String textoInferior,String textoSuperior,int corTexto, float tamanhoFonte, Bitmap fundo, DisplayMetrics displayMetrics) {
        this.textoInferior = textoInferior;
        this.textoSuperior = textoSuperior;
        this.corTexto = corTexto;
        this.tamanhoFonte = tamanhoFonte;
        this.fundo = fundo;
        this.displayMetrics = displayMetrics;
        this.meme = criarImagem();
        this.dirty = false;
    }

    public String getTextoInferior() {
        return textoInferior;
    }

    public void setTextoInferior(String textoInferior) {
        this.textoInferior = textoInferior;
        dirty = true;
    }

    public String getTextoSuperior() {
        return textoSuperior;
    }

    public void setTextoSuperior(String textoSuperior) {
        this.textoSuperior = textoSuperior;
        dirty = true;
    }

    public int getCorTexto() {
        return corTexto;
    }

    public void setCorTexto(int corTexto) {
        this.corTexto = corTexto;
        dirty = true;
    }

    public float getTamanhoFonte() {
        return tamanhoFonte;
    }

    public void setTamanhoFonte(float tamanhoFonte) {
        this.tamanhoFonte = tamanhoFonte;
        dirty = true;
    }

    public Bitmap getFundo() {
        return fundo;
    }

    public void setFundo(Bitmap fundo) {
        this.fundo = fundo;
        dirty = true;
    }

    public void rotacionarFundo(float graus) {
        Matrix matrix = new Matrix();
        matrix.postRotate(graus);
        fundo = Bitmap.createBitmap(fundo, 0, 0, fundo.getWidth(), fundo.getHeight(), matrix, true);
        dirty = true;
    }

    public Bitmap getImagem() {
        if (dirty) {
            meme = criarImagem();
            dirty = false;
        }
        return meme;
    }
    protected Bitmap criarImagem() {
        float heightFactor = (float) fundo.getHeight() / fundo.getWidth();
        int width = displayMetrics.widthPixels;
        int height = (int) (width * heightFactor);
        // nao deixa a imagem ocupar mais que 60% da altura da tela.
        if (height > displayMetrics.heightPixels * 0.6) {
            height = (int) (displayMetrics.heightPixels * 0.6);
            width = (int) (height * (1 / heightFactor));
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();

        Bitmap scaledFundo = Bitmap.createScaledBitmap(fundo, width, height, true);
        canvas.drawBitmap(scaledFundo, 0, 0, new Paint());

        paint.setColor(corTexto);
        paint.setAntiAlias(true);
        paint.setTextSize(tamanhoFonte);
        paint.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
        paint.setTextAlign(Paint.Align.CENTER);
        // desenhar texto em cima
        canvas.drawText(textoSuperior, (width / 2.f), (height * 0.15f), paint);

        // desenhar texto embaixo
        canvas.drawText(textoInferior, (width / 2.f), (height * 0.9f), paint);
        return bitmap;
    }
}
