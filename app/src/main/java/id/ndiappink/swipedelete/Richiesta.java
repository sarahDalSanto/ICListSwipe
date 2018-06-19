package id.ndiappink.swipedelete;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.widget.ImageView;

public class Richiesta {


    private String matricola, nomeEcognome, giornoInizio,  oraInizio,  giornoFine,  oraFine, tipo, testo;


    public Richiesta(){

    }


    public Richiesta( String matricola, String nomeEcognome, String giornoInizio, String oraInizio, String giornoFine, String oraFine, String tipo, String testo) {
        this.matricola = matricola;
        this.nomeEcognome = nomeEcognome;
        this.giornoInizio = giornoInizio;
        this.oraInizio = oraInizio;
        this.giornoFine = giornoFine;
        this.oraFine = oraFine;
        this.testo = testo;
        this.tipo = tipo;

    }



    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getNomeEcognome() {
        return nomeEcognome;
    }

    public void setNomeEcognome(String nomeEcognome) {
        this.nomeEcognome = nomeEcognome;
    }

    public String getGiornoInizio() {
        return giornoInizio;
    }

    public void setGiornoInizio(String giornoInizio) {
        this.giornoInizio = giornoInizio;
    }

    public String getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(String oraInizio) {
        this.oraInizio = oraInizio;
    }

    public String getGiornoFine() {
        return giornoFine;
    }

    public void setGiornoFine(String giornoFine) {
        this.giornoFine = giornoFine;
    }

    public String getOraFine() {
        return oraFine;
    }

    public void setOraFine(String oraFine) {
        this.oraFine = oraFine;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
