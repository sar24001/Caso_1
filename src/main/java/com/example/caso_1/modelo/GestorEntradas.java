package com.example.caso_1.modelo;

public class GestorEntradas {
    private int entradasDisponibles = 20;
    private int reservasConfirmadas = 0;
    private int solicitudesRechazadas = 0;
    private final int MAX_RECHAZOS = 10;

    // Métodos sincronizados para garantizar exclusión mutua
    public synchronized boolean procesarSolicitud(String nombreAgente) {
        if (entradasDisponibles > 0) {
            entradasDisponibles--;
            reservasConfirmadas++;
            System.out.printf("[%s] -> Reserva Confirmada. Disponibles: %d, Confirmadas: %d%n",
                    nombreAgente, entradasDisponibles, reservasConfirmadas);
            return true;
        } else if (solicitudesRechazadas < MAX_RECHAZOS) {
            solicitudesRechazadas++;
            System.out.printf("[%s] -> Solicitud rechazada. Rechazadas totales: %d%n",
                    nombreAgente, solicitudesRechazadas);
            return false;
        }
        return false;
    }

    public synchronized boolean quedanAccionesPendientes() {
        return entradasDisponibles > 0 || solicitudesRechazadas < MAX_RECHAZOS;
    }

    public synchronized void reiniciar() {
        this.entradasDisponibles = 20;
        this.reservasConfirmadas = 0;
        this.solicitudesRechazadas = 0;
    }

    // Getters sincronizados para lecturas seguras
    public synchronized int getEntradasDisponibles() { return entradasDisponibles; }
    public synchronized int getReservasConfirmadas() { return reservasConfirmadas; }
    public synchronized int getSolicitudesRechazadas() { return solicitudesRechazadas; }
}
