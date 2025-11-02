package ar.edu.unahur.obj2.observer.sujeto;

import ar.edu.unahur.obj2.observer.Alerta;
import ar.edu.unahur.obj2.observer.observadores.IEntidad;

public interface IMonitoreo {
    void agreggarEntidad(IEntidad unaEntidad);
    void eleminarEntidad(IEntidad unaEntidad);
    void notificar(Alerta alerta);
}
