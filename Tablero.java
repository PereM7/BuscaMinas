package Principi.Reptes.BuscaMinas.BuscaMinas;

public class Tablero {
    
    private int altura;
    private int llargari;
    private Casella[][] tauler;
    private int nombreMines = 1;

    public Tablero (int altura, int llargari, int nombreMines) {
        this.altura = altura;
        this.llargari = llargari;
        this.tauler = new Casella[altura][llargari];
        
        if (verificarNombreMines(nombreMines)) {
            this.nombreMines = nombreMines;
        } else {
            System.out.println("Nombre de mines major a caselles disponibles, default 1");
        }
    }

    public void iniciarTauler() {
        rellenarArray();
        colocarMinesAleatoris();
        calcularMinesVoltantCasella();
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
            int filaAleatori = (int) (altura * Math.random());
            int columnaAleatori = (int) (llargari * Math.random());

            if (!tauler[filaAleatori][columnaAleatori].getEsMina()) {
                tauler[filaAleatori][columnaAleatori].setEsMina(true);
                contadorMines--;
            }
        }
    }

    private boolean estaDinsTablero (int fila, int columna) {
        if ( (fila < 0 || fila >= altura) || (columna < 0 || columna >= llargari)) {
            return false;
        }
        return true;
    }

    private void calcularMinesVoltantCasella () {
        for (int fila = 0; fila < tauler.length; fila++) {
            for (int columna = 0; columna < tauler[fila].length; columna++) {

                if (!tauler[fila][columna].getEsMina()) {
                    int contador = 0;
                    for (int x = fila - 1; x <= fila + 1; x++) {
                        for (int y = columna - 1; y <= columna + 1; y++) {

                            if (estaDinsTablero(x, y)) {
                                if (tauler[x][y].getEsMina()) {
                                    contador++;
                                }
                            }
                        }
                    }
                    tauler[fila][columna].setMinesVoltant(contador);
                }
            }
        }
    }

    private char[][] arrayCaractersTablero () {
        char[][] array = new char[altura][llargari];
        char bandera = 'P';
        char tapat = '-';
        char buit = ' ';

        for (int fila = 0; fila < array.length; fila++) {
            for (int columna = 0; columna < array[fila].length; columna++) {

                if (tauler[fila][columna].getMinesVoltant() == 0 && !tauler[fila][columna].getEsMina()) {
                    array[fila][columna] = buit;
                }
                else if (tauler[fila][columna].getMinesVoltant() != 0) {
                    array[fila][columna] = (char) (tauler[fila][columna].getMinesVoltant() + '0');
                }
                else if (tauler[fila][columna].getEstaTapada()) {
                    array[fila][columna] = tapat;
                }
                else if (tauler[fila][columna].getTeBandera()) {
                    array[fila][columna] = bandera;
                }
            }
        }

        return array;
    }

    public void imprimirTablero () {
        char[][] array = arrayCaractersTablero();

        for (int fila = 0; fila < array.length; fila++) {
            for (int columna = 0; columna < array[fila].length; columna++) {

                System.out.println("[" + array[fila][columna] + "]");
            }
            System.out.println();
        }
    }

    //imprimir tablero perdre (mines)

    //jugar (inserir bandera, seleccionar casella, imprimir...)
}


/*
    // Mina = *, Bandera = P, Tapat = -, Buit = " "
    [1][1][1][2][*][1]
    [1][*][2][2][*][2]
    [ ][-][-][-][-][-]
    [ ][-][-][ ][-][-]
*/

