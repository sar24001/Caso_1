package com.example.caso_1;

import com.example.caso_1.service.Simulador;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Simulador simulador = new Simulador();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sistema de reserva de entradas");
        System.out.println("Escribe 'iniciar' o 'salir' dependiendo de la accion que quieras completar.");

        while (true) {
            System.out.print("\nComando > ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("salir")) {
                break;
            } else if (input.equals("iniciar")) {
                simulador.iniciarSimulacion();
            } else {
                System.out.println("Comando no reconocido. Usa 'iniciar' o 'salir'.");
            }
        }
        scanner.close();
    }
}
