/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exemplo.lista;

public class TesteAdicionaNoFim {
  public static void main(String[] args) {
    ListaLigada lista = new ListaLigada();

    lista.adiciona("Rafael");
    lista.adiciona("Paulo");
    
    System.out.println(lista);
  }
}
//Saída esperada:

//[Rafael, Paulo]

public class TesteAdicionaPorPosicao {
  public static void main(String[] args) {
    ListaLigada lista = new ListaLigada();
    lista.adiciona("Rafael");
    lista.adiciona(0, "Paulo");
    lista.adiciona(1, "Camila");
    
    System.out.println(lista);
  }
}
//Saída esperada:

//[Paulo, Camila, Rafael]
public class TestePegaPorPosicao {
  public static void main(String[] args) {
    ListaLigada lista = new ListaLigada();
    
    lista.adiciona("Rafael");
    lista.adiciona("Paulo");
    
    System.out.println(lista.pega(0));
    System.out.println(lista.pega(1));
  }
}
Saída esperada:

Rafael
Paulo
public class TesteRemovePorPosicao {
  public static void main(String[] args) {
    ListaLigada lista = new ListaLigada();
    
    lista.adiciona("Rafael");
    lista.adiciona("Paulo");
    lista.adiciona("Camila");
    
    lista.remove(1);
    
    System.out.println(lista);
  }
}
Saída esperada:

[Rafael, Camila]
public class TesteTamanho {
  public static void main(String[] args) {
    ListaLigada lista = new ListaLigada();
    
    lista.adiciona("Rafael");
    lista.adiciona("Paulo");
    
    System.out.println(lista.tamanho());
    
    lista.adiciona("Camila");
    
    System.out.println(lista.tamanho());
  }
}
Saída esperada:

2
3
public class TesteContemElemento {
  public static void main(String[] args) {
    ListaLigada lista = new ListaLigada();
    
    lista.adiciona("Rafael");
    lista.adiciona("Paulo");
    
    System.out.println(lista.contem("Rafael"));
    System.out.println(lista.contem("Camila"));
  }
}
Saída esperada:

true
false
public class TesteAdicionaNoComeco {
  public static void main(String[] args) {
    ListaLigada lista = new ListaLigada();
    
    lista.adicionaNoComeco("Rafael");
    lista.adicionaNoComeco("Paulo");
    
    System.out.println(lista);
  }
}
Saída esperada:

[Paulo, Rafael]
public class TesteRemoveDoComeco {
  public static void main(String[] args) {
    ListaLigada lista = new ListaLigada();
    
    lista.adiciona("Rafael");
    lista.adiciona("Paulo");
    
    lista.removeDoComeco();
    
    System.out.println(lista);
  }
}
Saída esperada:

[Paulo]
public class TesteRemoveDoFim {
  public static void main(String[] args) {
    ListaLigada lista = new ListaLigada();
    
    lista.adiciona("Rafael");
    lista.adiciona("Paulo");
    
    lista.removeDoFim();
    
    System.out.println(lista);
  }
}
Saída esperada:

[Rafael]


// Adicionando no começo da lista

public class ListaLigada {

  private Celula primeira;
  
  private Celula ultima;

  private int totalDeElementos;
  
  public void adicionaNoComeco(Object elemento) {
    Celula nova = new Celula(this.primeira, elemento);
    this.primeira = nova;
    
    if(this.totalDeElementos == 0){
      // caso especial da lista vazia
      this.ultima = this.primeira;
    }
    this.totalDeElementos++;
  }
}

// Adicionando no fim da lista

public class ListaLigada {

  private Celula primeira;

  private Celula ultima;

  private int totalDeElementos;

  public void adiciona(Object elemento) {
    if (this.totalDeElementos == 0) {
      this.adicionaNoComeco(elemento);
    } else {
      Celula nova = new Celula(elemento);
      this.ultima.setProxima(nova);
      this.ultima = nova;
      this.totalDeElementos++;
    }
  }
}

//Percorrendo a lista

public String toString() {

  // Verificando se a Lista está vazia
  if(this.totalDeElementos == 0){
    return "[]";
  }
  
  StringBuilder builder = new StringBuilder("[");
  Celula atual = primeira;

  // Percorrendo até o penúltimo elemento.
  for (int i = 0; i < this.totalDeAlunos - 1; i++) {
    builder.append(atual.getElemento());
    builder.append(", ");
    atual = atual.getProxima();
  }

  // último elemento
  builder.append(atual.getElemento());
  builder.append("]");
  
  return builder.toString();
}

// Adicionando em qualquer posicao

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
    Celula nova = new Celula(anterior.getProxima(), elemento);
    anterior.setProxima(nova);
    this.totalDeElementos++;
  }
}

// Removendo do começo 

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

//Então, em vez de fazer uma Lista Ligada simples vamos fazer uma Lista Duplamente Ligada. Ou seja, cada célula aponta para a sua anterior além de apontar para a próxima:

public class Celula {

  ...
  
  private Celula anterior;
  
  ...
  
  public Celula getAnterior() {
    return anterior;
  }
  
  public void setAnterior(Celula anterior) {
    this.anterior = anterior;
  }
}

// Removendo do fim

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

// removendo de qualquer posição

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

// Verificando se o elemento esta na lista

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

// Tamanho da lista

public int tamanho() {
    return this.totalDeElementos;
  }
}


// Lista Duplamente ligada

//Adicionando no começo da lista

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

// Adicionando no fim da lista 

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

// Adicionando em qualquer posição

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

