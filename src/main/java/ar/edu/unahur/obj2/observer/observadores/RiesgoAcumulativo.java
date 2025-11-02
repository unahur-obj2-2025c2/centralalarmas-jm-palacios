package ar.edu.unahur.obj2.observer.observadores;

import java.util.List;

import ar.edu.unahur.obj2.observer.Alerta;

public class RiesgoAcumulativo implements IComportamiento{

    @Override
    public Double obtenerCriterio(List<Alerta> alertas) {
        return alertas.stream().filter(a->a.esCritica()).mapToDouble(a->a.getNivel()).sum();
    }

}
