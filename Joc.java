package Principi.Reptes.BuscaMinas.BuscaMinas;

public class Joc { 

    public static void main (String [] args) {
        Tablero joc = new Tablero(8, 8, 8);

        joc.iniciarTauler();
        joc.jugar();
    }
} 
