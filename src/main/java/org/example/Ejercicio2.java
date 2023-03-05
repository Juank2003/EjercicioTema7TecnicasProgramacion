package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio2 {

    private static int contadorClientes = 0;

    private int numeroCliente;
    private String nombre;

    public Ejercicio2(String nombre) {
        this.numeroCliente = ++contadorClientes;
        this.nombre = nombre;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public static void main(String[] args) {
        ArrayList<Ejercicio2> clientes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("-- Menú --");
            System.out.println("1 - Mostrar clientes");
            System.out.println("2 - Crear un cliente");
            System.out.println("3 - Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("-- Clientes --");
                    for (Ejercicio2 cliente : clientes) {
                        System.out.printf("Cliente n.°%d [%s]%n", cliente.getNumeroCliente(), cliente.getNombre());
                    }
                    break;
                case 2:
                    System.out.println("¿Nombre?");
                    scanner.nextLine();
                    String nombre = scanner.nextLine();
                    clientes.add(new Ejercicio2(nombre));
                    System.out.printf("Cliente n.°%d [%s] añadido%n", contadorClientes, nombre);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }
}