
package br.com.reserva.upe.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tipoReserva")
@SessionScoped
public class TipoReservaBean {
    
    private String tipo;
  

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
