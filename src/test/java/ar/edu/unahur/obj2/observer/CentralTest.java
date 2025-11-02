package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
 
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.excepciones.NivelIncorrectoDeAlertaException;
import ar.edu.unahur.obj2.observer.observadores.Entidad;
import ar.edu.unahur.obj2.observer.observadores.RiesgoAcumulativo;
import ar.edu.unahur.obj2.observer.observadores.RiesgoMaritimo;
import ar.edu.unahur.obj2.observer.observadores.RiesgoPromedio;
import ar.edu.unahur.obj2.observer.sujeto.CentralDeMonitoreo;

public class CentralTest {
    private CentralDeMonitoreo central;
    private Entidad e1;
    private Entidad e2;

    @BeforeEach
    void setUp(){
        central = new CentralDeMonitoreo();
        e1 = new Entidad("Hospital");
        e2 = new Entidad("Bombero");
        central.agreggarEntidad(e1);
        central.agreggarEntidad(e2);
        
    }

    @Test
    void dadoElSetUp_alAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo(){
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia",8);

        assertEquals(2, e1.getAlertas().size());
        assertEquals(10.0, e1.riesgo());
        assertEquals(2, e2.getAlertas().size());
        assertEquals(10.0, e2.riesgo());
    }
    @Test
    void dadoElSetUp_alCambiarElComportamientoYAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo(){
        e1.setComportamiento(new RiesgoPromedio());
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia",8);

        assertEquals(2, e1.getAlertas().size());
        assertEquals(7.0, e1.riesgo());
        assertEquals(2, e2.getAlertas().size());
        assertEquals(10.0, e2.riesgo());
    }
    @Test
    void dadoElSetUp_alAgregarAlertasQuitarUnaEntidadYAgregaNuevaAlerta_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo(){
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia",8);
        central.eleminarEntidad(e1);
        central.emitirAlerta("granizo", 7);

        assertEquals(2, e1.getAlertas().size());
        assertEquals(10.0, e1.riesgo());
        assertEquals(3, e2.getAlertas().size());
        assertEquals(7.0, e2.riesgo());
        assertEquals(5, central.cantidadNotificionesEnviadas());
    }
    @Test
    void seEmiteUnaAlertadelNivel11_entoncesLanzaUnaExcepcionCorrespondiente(){
        NivelIncorrectoDeAlertaException ex = assertThrows(NivelIncorrectoDeAlertaException.class, 
        ()-> central.emitirAlerta("ciclon", 11));

        assertEquals("Nivel de alerta incorrecto", ex.getMessage());
    }
    @Test
    void dadoElSetup_alCambiarElComportamientoEnLasEntidadesYAgregarAlertas_SeVerificaCorrectamentelasAlertasRecibidasYElRieso(){
        e1.setComportamiento(new RiesgoMaritimo());
        e2.setComportamiento(new RiesgoAcumulativo());
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia",8);
        central.emitirAlerta("tifon", 9);
        

        assertEquals(3, e1.getAlertas().size());
        assertEquals(9.0, e1.riesgo());
        assertEquals(3, e2.getAlertas().size());
        assertEquals(17.0, e2.riesgo());
    }
    @Test
    void dadoElSeptup_alEliminarEntidadE2YCambiarE1CompotamiertoYAgregarAlertas_SeVerificaCorrectamenteLaALertaMayorYElRiesgo(){
        central.eleminarEntidad(e2);
        e1.setComportamiento(new RiesgoPromedio());
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia",8);
        central.emitirAlerta("tifon", 7);

        assertEquals("lluvia", e1.alertaDeMAyorNivel().getTipo());
        assertEquals(7, e1.riesgo());

    }


}
