package ar.edu.unahur.obj2.observer.observadores;

import java.util.List;

import ar.edu.unahur.obj2.observer.Alerta;

public class RiegoCritico implements IComportamiento{

    @Override
    public Double obtenerCriterio(List<Alerta> alertas) {
        return alertas.getLast().esCritica() ? 10.0 : alertas.getLast().getNivel();
    }

}
