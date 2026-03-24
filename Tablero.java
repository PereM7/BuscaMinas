package Principi.Reptes.BuscaMinas.BuscaMinas;

import java.util.Scanner;

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

                if (tauler[fila][columna].getTeBandera()) {
                    array[fila][columna] = bandera;
                }
                else if (tauler[fila][columna].getEstaTapada()) {
                    array[fila][columna] = tapat;
                }
                else if (tauler[fila][columna].getMinesVoltant() != 0) {
                    array[fila][columna] = (char) (tauler[fila][columna].getMinesVoltant() + '0');
                }
                else if (tauler[fila][columna].getMinesVoltant() == 0 && !tauler[fila][columna].getEsMina()) {
                    array[fila][columna] = buit;
                }
            }
        }

        return array;
    }

    private void imprimirTablero () {
        char[][] array = arrayCaractersTablero();

        for (int fila = 0; fila < array.length; fila++) {
            for (int columna = 0; columna < array[fila].length; columna++) {

                System.out.print("[" + array[fila][columna] + "]");
            }
            System.out.println();
        }
    }

    private void imprimirTableroPerdre () {
        for (int fila = 0; fila < tauler.length; fila++) {
            for (int columna = 0; columna < tauler[fila].length; columna++) {

                if (tauler[fila][columna].getEsMina()) {
                    System.out.print("[*]");
                }else{
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    private int llegirColumnaSeleccionada () {
        Scanner sn = new Scanner(System.in);
        int columna = 0;
        do {
            System.out.println("Introdueix la columna (0-" + (llargari - 1) + "): ");
            columna = sn.nextInt();

            if (columna < 0 || columna >= llargari) {
                System.out.println("Incorrecte, no és un valor entre 0 i " + (llargari - 1));
            }
        } while (columna < 0 || columna >= llargari);

        return columna;
    }

    private int llegirFilaSeleccionada () {
        Scanner sn = new Scanner(System.in);

        int fila = 0;
        do {
            System.out.println("Introdueix la fila (0-" + (altura - 1) + "): ");
            fila = sn.nextInt();

            if (fila < 0 || fila >= altura) {
                System.out.println("Incorrecte, no és un valor entre 0 i " + (altura - 1));
            }
        } while (fila < 0 || fila >= altura);

        return fila;
    }

    private boolean posarBandera () {
        Scanner sn = new Scanner(System.in);
        String llegir = "";

        do {
            System.out.println("Posar bandera (escriu P per posar o deixar buit per passar): ");
            llegir = sn.nextLine().toLowerCase();

            if (!llegir.equals("p") && !llegir.isEmpty()) {
                System.out.println("Incorrecte, no és P o buit");
            }
        } while (!llegir.equals("p") && !llegir.isEmpty());

        return llegir.equals("p");
    }

    private boolean haGuanyat () {
        int contador = 0;

        for (int fila = 0; fila < tauler.length; fila++) {
            for (int columna = 0; columna < tauler[fila].length; columna++) {

                if (tauler[fila][columna].getEsMina() && tauler[fila][columna].getTeBandera()) {
                    contador++;
                }
            }
        }
        return contador == nombreMines;
    }

    public void iniciarTauler() {
        rellenarArray();
        colocarMinesAleatoris();
        calcularMinesVoltantCasella();
    }

    public void jugar () {
        boolean bandera;
        int fila = 0;
        int columna = 0;

        while (true) {
            imprimirTablero();
            bandera = posarBandera();
            fila = llegirFilaSeleccionada();
            columna = llegirColumnaSeleccionada();

            if (bandera && tauler[fila][columna].getEstaTapada()) {
                tauler[fila][columna].setTeBandera(true);
            } else {
                if (tauler[fila][columna].getEsMina()) {
                    System.out.println("Has perdut.");
                    imprimirTableroPerdre();
                    break;
                }else {
                    tauler[fila][columna].setEstaTapada(false);
                }
                if (haGuanyat()) {
                    System.out.println("Has guanyat!!!!");
                    break;
                }
            }
        }

    }
}


/*
    // Mina = *, Bandera = P, Tapat = -, Buit = " "
    [1][1][1][2][*][1]
    [1][*][2][2][*][2]
    [ ][-][-][P][-][-]
    [ ][-][-][ ][-][-]


                if (tauler[fila][columna].getTeBandera()) {
                    array[fila][columna] = bandera;
                }
                else if (tauler[fila][columna].getEstaTapada()) {
                    array[fila][columna] = tapat;
                }
                else if (tauler[fila][columna].getMinesVoltant() != 0) {
                    array[fila][columna] = (char) (tauler[fila][columna].getMinesVoltant() + '0');
                }
                else if (tauler[fila][columna].getMinesVoltant() == 0 && !tauler[fila][columna].getEsMina()) {
                    array[fila][columna] = buit;
                }


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
*/

