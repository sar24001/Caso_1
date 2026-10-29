package com.example.caso_1.threads;

import com.example.caso_1.modelo.GestorEntradas;

public class Agente implements Runnable {
    private final String nombre;
    private final GestorEntradas gestor;

    public Agente(String nombre, GestorEntradas gestor) {
        this.nombre = nombre;
        this.gestor = gestor;
    }

    @Override
    public void run() {
        while (gestor.quedanAccionesPendientes()) {
            gestor.procesarSolicitud(nombre);
            try {
                // Simulación de tiempo de procesamiento aleatorio
                Thread.sleep((long) (Math.random() * 50));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
