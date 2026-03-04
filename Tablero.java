

public class Tablero {
    
    private int altura;
    private int llargari;
    private Casella[][] tauler;
    private int nombreMines = 1;
    
    private boolean verificarNombreMines (int nombreMines) {
        if (nombreMines < (altura * llargari) ) {
            return true;
        }
        return false;
    }

    public Tablero (int altura, int llargari, int nombreMines) {
        this.altura = altura;
        this.llargari = llargari;
        this.tauler = new Casella[llargari][altura];
        
        if (verificarNombreMines(nombreMines)) {
            this.nombreMines = nombreMines;
        } else {
            System.out.println("Nombre de mines major a caselles disponibles, default 1");
        } 
    }

    private void colocarMinesAleatoris () {
        int contadorMines = nombreMines;
        while (contadorMines > 0) {
            int filaAleatori = (int) ((llargari + 1) * Math.random());
            int columnaAleatori = (int) ((altura + 1) * Math.random());

            if (!tauler[filaAleatori][columnaAleatori].getEsMina()) {
                tauler[filaAleatori][columnaAleatori].setEsMina(true);
                contadorMines--;
            }
        }
    }

    private void calcularMinesVoltantCasella () {
        
    }
}
