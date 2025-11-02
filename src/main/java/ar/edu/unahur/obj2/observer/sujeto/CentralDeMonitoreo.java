package ar.edu.unahur.obj2.observer.sujeto;

 
import java.util.Map; 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import ar.edu.unahur.obj2.observer.Alerta;
import ar.edu.unahur.obj2.observer.excepciones.NivelIncorrectoDeAlertaException;
import ar.edu.unahur.obj2.observer.observadores.IEntidad;

public class CentralDeMonitoreo implements IMonitoreo{
    private Set<IEntidad> entidades = new HashSet<>();
    private Map<Alerta,Integer> registroDeAlertas = new HashMap<>();

    public CentralDeMonitoreo(){}
    
    public void emitirAlerta(String tipo, Integer nivel){
        if(nivel < 1 || nivel > 10){
            throw new NivelIncorrectoDeAlertaException("Nivel de alerta incorrecto");
        }
        Alerta alerta = new Alerta(tipo, nivel);
        registroDeAlertas.put(alerta, entidades.size());
        notificar(alerta);
    }
    public Integer cantidadNotificionesEnviadas(){         
        return registroDeAlertas.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public void agreggarEntidad(IEntidad unaEntidad) {
        entidades.add(unaEntidad);
    }

    @Override
    public void eleminarEntidad(IEntidad unaEntidad) {
        entidades.remove(unaEntidad);
    }

    @Override
    public void notificar(Alerta alerta) {
        entidades.forEach(entidad->entidad.actualizar(alerta));
    }


}
