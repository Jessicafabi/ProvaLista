/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exemplo.lista;

/**
 *
 * @author Semec_2
 */
   public class Celula {

  private Celula proxima;

  private Object elemento;

  public Celula(Celula proxima, Object elemento) {
    this.proxima = proxima;
    this.elemento = elemento;
  }

  public Celula(Object elemento) {
    this.elemento = elemento;
  }

  public void setProxima(Celula proxima) {
    this.proxima = proxima;
  }

  public Celula getProxima() {
    return proxima;
  }
  
  public Object getElemento() {
    return elemento;
  }
}

