package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Cuadricula {

    private final char[][] tabla;
    private final char AGUA = '-';

    private int xBarco;
    private int yBarco;

    public Cuadricula() {
        tabla = new char[10][10];
        for (char[] fila : tabla) {
            Arrays.fill(fila, AGUA);
        }
    }

    public void depositarBarco(int x, int y) {
        xBarco = x;
        yBarco = y;
        tabla[x][y] = AGUA;
    }

    public boolean disparar(int x, int y) {
        if (tabla[x][y] == AGUA) {
            System.out.println("Agua");
            tabla[x][y] = 'O'; // muestra el disparo fallido
        } else if (tabla[x][y] == 'O') {
            tabla[x][y] = 'X';
            return estaHundido();
        } else if (tabla[x][y] == 'X') {
            System.out.println("Ya has disparado aquí antes.");
        }
        return false;
    }

    private boolean estaHundido() {
        for (char[] fila : tabla) {
            for (char c : fila) {
                if (c == 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    public void visualizacion() {
        System.out.print("  ");
        for (int i = 0; i < tabla.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < tabla.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tabla[i].length; j++) {
                System.out.print(tabla[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Cuadricula tablero = new Cuadricula();
        tablero.visualizacion();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca las coordenadas donde desea colocar su barco: ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        tablero.depositarBarco(x, y);
        boolean hundido = false;
        while (!hundido) {
            System.out.println("Introduzca las coordenadas de disparo: ");
            int xDisparo = sc.nextInt();
            int yDisparo = sc.nextInt();
            hundido = tablero.disparar(xDisparo, yDisparo);
            tablero.visualizacion();
        }
        System.out.println("¡Has hundido el barco! ¡Felicidades!");
    }
}