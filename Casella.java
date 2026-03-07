package Principi.Reptes.BuscaMinas.BuscaMinas;

public class Casella {
    
    private boolean esMina = false;
    private boolean estaTapada = true;
    private boolean teBandera = false;
    private int minesVoltant = 0;

    public Casella () { }

    public void setEsMina (boolean esMina) {
        this.esMina = esMina;
    }
    public boolean getEsMina() {
        return esMina;
    }

    public void setEstaTapada (boolean estaTapada) {
        this.estaTapada = estaTapada;
    }
    public boolean getEstaTapada() {
        return estaTapada;
    }

    public void setTeBandera (boolean teBandera) {
        this.teBandera = teBandera;
    }
    public boolean getTeBandera() {
        return teBandera;
    }

    public void setMinesVoltant (int num) {
        this.minesVoltant = num;
    }
    public int getMinesVoltant() {
        return minesVoltant;
    }

}
