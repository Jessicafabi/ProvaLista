/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercicios.caelum;


//Implemente a estrutura Célula através de uma classe no pacote br.com.caelum.ed.listasligadas.
package br.com.caelum.ed.listasligadas;

public class Celula {
  private Celula proxima;

  private Celula anterior;
  
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
  
  public void setAnterior(Celula anterior) {
    this.anterior = anterior;
  }
  
  public Celula getAnterior() {
    return anterior;
  }
  
  public Object getElemento() {
    return elemento;
  }
}
//Implemente a classe ListaLigada com o "esqueleto" das operações. Utilize o pacote br.com.caelum.ed.listasligadas.
package br.com.caelum.ed.listasligadas;

public class ListaLigada {

  private Celula primeira;

  private Celula ultima;
  
  private int totalDeElementos;

  public void adiciona(Object elemento) {
  }

  public void adiciona(int posicao, Object elemento) {
  }

  public Object pega(int posicao) {
    return null;
  }

  public void remove(int posicao) {
  }

  public int tamanho() {
    return 0;
  }

  public boolean contem(Object o) {
    return false;
  }

  public void adicionaNoComeco(Object elemento) {
  }

  public void removeDoComeco() {
  }

  public void removeDoFim() {
  }
}
//Implemente todos os testes feitos na seção Teste. Coloque as classes no pacote br.com.caelum.ed.listasligadas.
//Para testar as operações precisamos reescrever o método toString() para devolver os elementos da Lista em uma String.
public String toString() {

  // Verificando se a Lista está vazia
  if(this.totalDeElementos == 0){
    return "[]";
  }
  
  StringBuilder builder = new StringBuilder("[");
  Celula atual = primeira;
  
  // Percorrendo até o penúltimo elemento.
  for (int i = 0; i < this.totalDeElementos - 1; i++) {
    builder.append(atual.getElemento());
    builder.append(", ");
    atual = atual.getProxima();
  }
  
  // último elemento
  builder.append(atual.getElemento());
  builder.append("]");
  
  return builder.toString();
}

//Implemente todos métodos para a Lista Ligada. A cada método implementado não esqueça de executar o teste apropriado.
//Adiciona no começo.
public void adicionaNoComeco(Object elemento) {
  if(this.totalDeElementos == 0){
    Celula nova = new Celula(elemento);
    this.primeira = nova;
    this.ultima = nova;
  } else {
    Celula nova = new Celula(this.primeira, elemento);
    this.primeira.setAnterior(nova);
    this.primeira = nova;
  }
  this.totalDeElementos++;
}

//Adiciona no fim.
public void adiciona(Object elemento) {
  if (this.totalDeElementos == 0) {
    this.adicionaNoComeco(elemento);
  } else {
    Celula nova = new Celula(elemento);
    this.ultima.setProxima(nova);
    nova.setAnterior(this.ultima);
    this.ultima = nova;
    this.totalDeElementos++;
  }
}

//Adiciona em qualquer posição. Esta operação depende do método pegaCelula(int) que por sua vez depende do método posicaoOcupada(int).
private boolean posicaoOcupada(int posicao){
  return posicao >= 0 && posicao < this.totalDeElementos;
}
    
private Celula pegaCelula(int posicao) {
  if(!this.posicaoOcupada(posicao)){
    throw new IllegalArgumentException("Posição não existe");
  }

  Celula atual = primeira;
  for (int i = 0; i < posicao; i++) {
    atual = atual.getProxima();
  }
  return atual;
}
public void adiciona(int posicao, Object elemento) {
  if(posicao == 0){ // No começo.
    this.adicionaNoComeco(elemento);
  } else if(posicao == this.totalDeElementos){ // No fim.
    this.adiciona(elemento);
  } else {
    Celula anterior = this.pegaCelula(posicao - 1);
    Celula proxima = anterior.getProxima();
    Celula nova = new Celula(anterior.getProxima(), elemento);
    nova.setAnterior(anterior);
    anterior.setProxima(nova);
    proxima.setAnterior(nova);
    this.totalDeElementos++;
  }
}
//Remove do começo.
public void removeDoComeco() {
  if (!this.posicaoOcupada(0)) {
    throw new IllegalArgumentException("Posição não existe");
  }
    
  this.primeira = this.primeira.getProxima();
  this.totalDeElementos--;
  
  if (this.totalDeElementos == 0) {
    this.ultima = null;
  }
}
//Remove do fim.
public void removeDoFim() {
  if (!this.posicaoOcupada(this.totalDeElementos - 1)) {
    throw new IllegalArgumentException("Posição não existe");
  }
  if (this.totalDeElementos == 1) {
    this.removeDoComeco();
  } else {
    Celula penultima = this.ultima.getAnterior();
    penultima.setProxima(null);
    this.ultima = penultima;
    this.totalDeElementos--;
  }
}
//Remove de qualquer posição.
public void remove(int posicao) {
  if (!this.posicaoOcupada(posicao)) {
    throw new IllegalArgumentException("Posição não existe");
  }
    
  if (posicao == 0) {
    this.removeDoComeco();
  } else if (posicao == this.totalDeElementos - 1) {
    this.removeDoFim();
  } else {
    Celula anterior = this.pegaCelula(posicao - 1);
    Celula atual = anterior.getProxima();
    Celula proxima = atual.getProxima();
    
    anterior.setProxima(proxima);
    proxima.setAnterior(anterior);
    
    this.totalDeElementos--;
  }
}
//Informar o tamanho.
public int tamanho() {
  return this.totalDeElementos;
}
//Pega elemento por posição.
public Object pega(int posicao) {
  return this.pegaCelula(posicao).getElemento();
}
//Verifica se um elemento pertence a Lista.
public boolean contem(Object elemento) {
  Celula atual = this.primeira;
    
  while (atual != null) {
    if (atual.getElemento().equals(elemento)) {
      return true;
    }
    atual = atual.getProxima();
  }
  return false;
}
//Vamos comparar o consumo de tempo entre o Vetor e a Lista Ligada para saber em quais situações um é melhor que o outro.
public class TestePerformance {
  public static void main(String[] args) {

    ArrayList<String> vetor = new ArrayList<String>();
    LinkedList<String> lista = new LinkedList<String>();
    int numeroDeElementos = 40000;

    // ADICIONADO NO COMEÇO
    
    long inicio = System.currentTimeMillis();

    for (int i = 0; i < numeroDeElementos; i++) {
      vetor.add(0, "" + i);
    }

    long fim = System.currentTimeMillis();
    System.out.println("Vetor adiciona no começo: " + (fim - inicio)
        / 1000.0);

    inicio = System.currentTimeMillis();

    for (int i = 0; i < numeroDeElementos; i++) {
      lista.add(0, "" + i);
    }

    fim = System.currentTimeMillis();
    System.out.println("Lista Ligada adiciona no começo: " + 
      (fim - inicio) / 1000.0);


    // PERCORRENDO
    inicio = System.currentTimeMillis();

    for (int i = 0; i < numeroDeElementos; i++) {
      vetor.get(i);
    }

    fim = System.currentTimeMillis();
    System.out
      .println("Vetor percorrendo: " 
      + (fim - inicio) / 1000.0);

    inicio = System.currentTimeMillis();

    for (int i = 0; i < numeroDeElementos; i++) {
      lista.get(i);
    }

    fim = System.currentTimeMillis();
    System.out.
      println("Lista Ligada percorrendo: " 
      + (fim - inicio) / 1000.0);
    
    
    // REMOVENDO DO COMEÇO
    inicio = System.currentTimeMillis();

    for (int i = 0; i < numeroDeElementos; i++) {
      vetor.remove(0);
    }
    
    fim = System.currentTimeMillis();
    System.out
      .println("Vetor remove do começo: " 
      + (fim - inicio) / 1000.0);

    inicio = System.currentTimeMillis();

    for (int i = 0; i < numeroDeElementos; i++) {
      lista.remove(0);
    }

    fim = System.currentTimeMillis();
    System.out.println("Lista Ligada remove do começo: " 
      + (fim - inicio) / 1000.0);
    
  }
}
