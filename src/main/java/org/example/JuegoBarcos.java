package org.example;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class JuegoBarcos {

    private static final int TAMANIO_TABLERO = 10;
    private static final int NUMERO_BARCOS = 2;
    private static final int LONGITUD_BARCOS = 3;
    private static final char AGUA = '-';
    private static final char BARCO = 'B';
    private static final char DISPARO_AGUA = 'O';
    private static final char DISPARO_BARCO = 'X';

    private final char[][] tableroUsuario;
    private final char[][] tableroComputadora;
    private final Random random;

    public JuegoBarcos() {
        tableroUsuario = new char[TAMANIO_TABLERO][TAMANIO_TABLERO];
        System.out.println("\nTablero usuario");

        tableroComputadora = new char[TAMANIO_TABLERO][TAMANIO_TABLERO];
        random = new Random();

        inicializarTableros();

    }

    private void inicializarTableros() {
        for (char[] fila : tableroUsuario) {
            Arrays.fill(fila, AGUA);
        }

        for (char[] fila : tableroComputadora) {
            Arrays.fill(fila, AGUA);
        }
        colocarBarcos(tableroUsuario);
        colocarBarcos(tableroComputadora);
    }

    private void colocarBarcos(char[][] tablero) {
        int numeroBarcosColocados = 0;
        while (numeroBarcosColocados < NUMERO_BARCOS) {
            int x = random.nextInt(TAMANIO_TABLERO);
            int y = random.nextInt(TAMANIO_TABLERO);
            boolean orientacionHorizontal = random.nextBoolean();
            if (esPosicionValida(tablero, x, y, orientacionHorizontal)) {
                colocarBarco(tablero, x, y, orientacionHorizontal);
                numeroBarcosColocados++;
            }
        }
    }

    private boolean esPosicionValida(char[][] tablero, int x, int y, boolean orientacionHorizontal) {
        if (orientacionHorizontal) {
            for (int i = y; i < y + LONGITUD_BARCOS; i++) {
                if (i >= TAMANIO_TABLERO || tablero[x][i] != AGUA) {
                    return false;
                }
            }
        } else {
            for (int i = x; i < x + LONGITUD_BARCOS; i++) {
                if (i >= TAMANIO_TABLERO || tablero[i][y] != AGUA) {
                    return false;
                }
            }
        }
        return true;
    }

    private void colocarBarco(char[][] tablero, int x, int y, boolean orientacionHorizontal) {
        if (orientacionHorizontal) {
            for (int i = y; i < y + LONGITUD_BARCOS; i++) {
                tablero[x][i] = BARCO;
            }
        } else {
            for (int i = x; i < x + LONGITUD_BARCOS; i++) {
                tablero[i][y] = BARCO;
            }
        }
    }

    public void jugar() {
        Scanner sc = new Scanner(System.in);
        boolean hundidoUsuario = false;
        boolean hundidoComputadora = false;
        while (!hundidoUsuario && !hundidoComputadora) {
            // Turno del usuario
            System.out.println("Turno del usuario:");
            visualizarTablero(tableroUsuario);
            System.out.println("/////////////////////////");
            System.out.println("Turno de la computadora:");
            visualizarTablero(tableroComputadora);
            System.out.println("Introduce la coordenada x:");
            int x = sc.nextInt();
            System.out.println("Introduce la coordenada y:");
            int y = sc.nextInt();
            hundidoUsuario = disparar(tableroComputadora, x, y);
            // Turno de la computadora
            System.out.println("Turno de la computadora:");
            visualizarTablero(tableroUsuario);
            visualizarTablero(tableroComputadora);
            x = random.nextInt(TAMANIO_TABLERO);
            y = random.nextInt(TAMANIO_TABLERO);
            hundidoComputadora = disparar(tableroUsuario, x, y);
        }
        if (hundidoUsuario) {
            System.out.println("Has perdido!");
        } else {
            System.out.println("Has ganado!");
        }
    }

    private boolean disparar(char[][] tablero, int x, int y) {
        if (tablero[x][y] == BARCO) {
            tablero[x][y] = DISPARO_BARCO;
            System.out.println("Has hundido un barco!");
            return comprobarHundido(tablero);
        } else {
            tablero[x][y] = DISPARO_AGUA;
            System.out.println("Has fallado!");
            return false;
        }
    }

    private boolean comprobarHundido(char[][] tablero) {
        for (char[] fila : tablero) {
            for (char celda : fila) {
                if (celda == BARCO) {
                    return false;
                }
            }
        }
        return true;
    }

    private void visualizarTablero(char[][] tablero) {
        for (int i = 0; i < TAMANIO_TABLERO; i++) {
            for (int j = 0; j < TAMANIO_TABLERO; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        JuegoBarcos juego = new JuegoBarcos();
        juego.jugar();
    }

}