

public class Tablero {
    
    private int altura;
    private int llargari;
    private Casella[][] tauler;
    private int nombreMines = 1;

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

    public void iniciarTauler() {
        rellenarArray();
    }

    private boolean verificarNombreMines (int nombreMines) {
        if (nombreMines < (altura * llargari) ) {
            return true;
        }
        return false;
    }

    private void rellenarArray () {
        for (int fila = 0; fila < tauler.length; fila++) {
            for (int columna = 0; columna < tauler[fila].length; columna++) {

                tauler[fila][columna] = new Casella();
            }
        }
    }

    private void colocarMinesAleatoris () {
        int contadorMines = nombreMines;
        while (contadorMines > 0) {
            int filaAleatori = (int) (llargari * Math.random());
            int columnaAleatori = (int) (altura * Math.random());

            if (!tauler[filaAleatori][columnaAleatori].getEsMina()) {
                tauler[filaAleatori][columnaAleatori].setEsMina(true);
                contadorMines--;
            }
        }
    }

    private void calcularMinesVoltantCasella () {
        
        for (int contadorCas = 8; contadorCas > 0; contadorCas--) {
            
        }
    }
}
