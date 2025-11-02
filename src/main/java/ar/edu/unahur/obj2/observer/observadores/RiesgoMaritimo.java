package ar.edu.unahur.obj2.observer.observadores;

import java.util.List;

import ar.edu.unahur.obj2.observer.Alerta;

public class RiesgoMaritimo implements IComportamiento{
    private List<String> tipo = List.of("maremoto", "tifon","tsunami");

    @Override
    public Double obtenerCriterio(List<Alerta> alertas) {   
        Alerta ultima = alertas.get(alertas.size()-1);      
        return tipo.contains(ultima.getTipo()) ? ultima.getNivel() : 4.0;
    }

}
