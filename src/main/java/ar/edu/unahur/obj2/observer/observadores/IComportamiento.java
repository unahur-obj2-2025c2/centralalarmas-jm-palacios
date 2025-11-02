package ar.edu.unahur.obj2.observer.observadores;

import java.util.List;

import ar.edu.unahur.obj2.observer.Alerta;

public interface IComportamiento {  
    Double obtenerCriterio(List<Alerta> alertas);

}
