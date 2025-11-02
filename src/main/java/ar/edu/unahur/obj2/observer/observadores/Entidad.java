package ar.edu.unahur.obj2.observer.observadores;

import java.util.ArrayList;
 
import java.util.List;

import ar.edu.unahur.obj2.observer.Alerta;

public class Entidad implements IEntidad{
    private final String nombre;
    private List<Alerta> alertas = new ArrayList<>();
    private IComportamiento comportamiento = new RiegoCritico();
    
    public Entidad(String nombre) {
        this.nombre = nombre;         
    }    

    public String getNombre() {
        return nombre;
    }    

    public Alerta alertaDeMAyorNivel() {
        Integer nivelMaximo = alertas.stream().mapToInt(a->a.getNivel()).max().orElse(0);
        return alertas.stream().filter(a->a.getNivel()==nivelMaximo).findFirst().orElseThrow();
    }

    public IComportamiento getComportamiento() {
        return comportamiento;
    }

    public Double riesgo(){
        return alertas.isEmpty() ? 0.00 : comportamiento.obtenerCriterio(alertas);
    }

    @Override
    public void actualizar(Alerta alerta) {
        alertas.add(alerta);
    }

    public void setComportamiento(IComportamiento comportamiento) {
        this.comportamiento = comportamiento;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }
    
}
