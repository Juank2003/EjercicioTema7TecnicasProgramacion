package org.example;

import java.util.Random;

public class Ejercicio1 {

        private int caras;
        private Integer ultimaCara;

        public Ejercicio1() {
            this.caras = 6;
            this.ultimaCara = null;
        }

        public Ejercicio1(int caras) {
            this.caras = caras;
            this.ultimaCara = null;
        }

        public int tirar() {
            Random rand = new Random();
            this.ultimaCara = rand.nextInt(this.caras) + 1;
            return this.ultimaCara;
        }

        public int getCaras() {
            return this.caras;
        }

        public Integer getUltimaCara() {
            return this.ultimaCara;
        }

        public void setCaras(int caras) {
            this.caras = caras;
            this.ultimaCara = null;
        }


    public static void main(String[] args) {
        Ejercicio1 dado1 = new Ejercicio1();
        Ejercicio1 dado2 = new Ejercicio1(10);
        Ejercicio1 dado3 = new Ejercicio1(12);

        int suma = 0;
        while (suma < 20) {
            suma = dado1.tirar() + dado2.tirar() + dado3.tirar();
            System.out.printf("Dado 1: %d, Dado 2: %d, Dado 3: %d, Suma: %d%n", dado1.getUltimaCara(), dado2.getUltimaCara(), dado3.getUltimaCara(), suma);
        }

        System.out.println("¡La suma es mayor o igual a 20!");
    }
}
