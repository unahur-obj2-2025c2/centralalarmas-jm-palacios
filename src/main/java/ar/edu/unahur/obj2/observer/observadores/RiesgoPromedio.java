package ar.edu.unahur.obj2.observer.observadores;

import java.util.List;

import ar.edu.unahur.obj2.observer.Alerta;

public class RiesgoPromedio implements IComportamiento{

    @Override
    public Double obtenerCriterio(List<Alerta> alertas) {
        return alertas.stream().mapToInt(a->a.getNivel()).average().orElse(0);
    }

}
