package com.example.caso_1.service;

import com.example.caso_1.modelo.GestorEntradas;
import com.example.caso_1.threads.Agente;

import java.util.concurrent.atomic.AtomicBoolean;

public class Simulador {
    private final GestorEntradas gestor = new GestorEntradas();
    private final AtomicBoolean enEjecucion = new AtomicBoolean(false);

    public void iniciarSimulacion() {
        // Garantiza que no se inicien agentes adicionales durante una ejecución
        if (!enEjecucion.compareAndSet(false, true)) {
            System.out.println("[ADVERTENCIA] Ya hay una simulación en curso. Solicitud ignorada.");
            return;
        }

        System.out.println("\n=== Iniciando Simulacion ===");
        gestor.reiniciar();

        // Crear 3 agentes independientes
        Thread a1 = new Thread(new Agente("Agente-1", gestor));
        Thread a2 = new Thread(new Agente("Agente-2", gestor));
        Thread a3 = new Thread(new Agente("Agente-3", gestor));

        a1.start();
        a2.start();
        a3.start();

        // Hilo en segundo plano para monitorear la finalización sin congelar la terminal
        new Thread(() -> {
            try {
                a1.join();
                a2.join();
                a3.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                enEjecucion.set(false);
                validarPruebasDeAceptacion();
            }
        }).start();
    }

    private void validarPruebasDeAceptacion() {
        int disp = gestor.getEntradasDisponibles();
        int conf = gestor.getReservasConfirmadas();
        int rech = gestor.getSolicitudesRechazadas();

        System.out.println("=== Fin de la simulacion ===");
        System.out.println("Entradas disponibles final: " + disp );
        System.out.println("Reservas confirmadas: " + conf );
        System.out.println("Solicitudes rechazadas: " + rech );
    }

    public boolean isEnEjecucion() {
        return enEjecucion.get();
    }
}