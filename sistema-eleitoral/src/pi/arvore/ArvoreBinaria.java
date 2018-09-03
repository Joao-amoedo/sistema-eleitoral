package pi.arvore;

import pi.node.Cor;
import pi.node.Node;

public class ArvoreBinaria implements Arvore {

	private Node raiz;

	public ArvoreBinaria(int elemento) {
		raiz = new Node(elemento, Cor.PRETO);
	}

	@Override
	public boolean add(Integer elemento) {
		Node novo = new Node(elemento);
		if (raiz == null) {
			raiz = novo;
			return true;
		} else
			return add(raiz, novo);
	}

	/**
	 * Método privado sobrecarregado para adicionar elemento na Árvore de forma
	 * recursivo
	 * 
	 * @param raiz
	 * @param novo
	 * @return adiciona
	 */
	private boolean add(Node raiz, Node novo) {
		Node aux = raiz;
		if (aux.getElemento() < novo.getElemento())
			return addDireita(aux, novo);
		else if (aux.getElemento() > novo.getElemento()) {
			return addEsquerda(aux, novo);
		} else
			return false;
	}

	/**
	 * Adiciona no elemento direito da árvore
	 * 
	 * @param aux
	 * @param novo
	 * @return adiciona
	 */
	private boolean addDireita(Node aux, Node novo) {
		if (aux.getElemento() == novo.getElemento())
			return false;
		else if (aux.getDireita() != null)
			return add(aux.getDireita(), novo);
		else {
			aux.setDireita(novo);
			return true;
		}
	}

	/**
	 * Adiciona no elemento esquerdo da árvore
	 * 
	 * @param aux
	 * @param novo
	 * @return adiciona
	 */
	private boolean addEsquerda(Node aux, Node novo) {
		if (aux.getElemento() == novo.getElemento())
			return false;
		else if (aux.getEsquerda() != null)
			return add(aux.getEsquerda(), novo);
		else {
			aux.setEsquerda(novo);
			return true;
		}
	}

	@Override
	public boolean remove(Integer elemento) {
		if (this.raiz.getElemento() == elemento) {
			raiz = verificaTipoRemocao(raiz);
			return true;
		} else {
			remove(raiz, elemento);
		}
		return false;
	}

	private boolean remove(Node raiz, Integer elemento) {
		if (raiz.getElemento() < elemento) {
			Node direita = raiz.getDireita();
			if (direita == null)
				return false;
			else if (direita.getElemento() != elemento)
				return remove(direita, elemento);
			else {
				raiz.setDireita(verificaTipoRemocao(direita));
				return true;
			}
		} else {
			Node esquerda = raiz.getEsquerda();
			if (esquerda == null)
				return false;
			else if (esquerda.getElemento() != elemento)
				return remove(esquerda, elemento);
			else {
				raiz.setEsquerda(verificaTipoRemocao(esquerda));
				return true;
			}

		}
	}

	/**
	 * Metodo para verificar o tipo de remoção e executa-la
	 * 
	 * @param raiz
	 * @return novo
	 */
	private Node verificaTipoRemocao(Node raiz) {
		int qtd = raiz.getQuantidadeDeFilhos();
		Node aux = raiz;
		if (qtd == 0)
			return null;
		else if (qtd == 1)
			return remocaoSimplesDireita(raiz);
		else if (qtd == -1)
			return remocaoSimplesEsquerda(raiz);
		else {
			return remocaoComposta(raiz);
		}
	}

	/**
	 * Caso o Node possua dois filhos
	 * 
	 * @param raiz
	 * @return novo
	 */
	private Node remocaoComposta(Node raiz) {
		Node aux;
		aux = maiorEsquerda(raiz.getEsquerda());
		aux.setDireita(raiz.getDireita());
		if (raiz.getEsquerda() != aux)
			aux.setEsquerda(raiz.getEsquerda());
		return aux;
	}

	/**
	 * Remoção simples, retorna o elemento a esquerda
	 * 
	 * @param raiz
	 * @return novo
	 */
	private Node remocaoSimplesEsquerda(Node remove) {
		return remove.getEsquerda();
	}

	/**
	 * Remoção simples, retorna o elemento a direita
	 * 
	 * @param remove
	 * @return novo
	 */
	private Node remocaoSimplesDireita(Node remove) {
		return remove.getDireita();
	}

	/**
	 * Retorna o maior elemento da sub árvore à esquerda
	 * 
	 * @param raiz
	 * @return maiorEsquerda
	 */
	private Node maiorEsquerda(Node raiz) {

		Node remover = raiz;

		if (raiz.getDireita() != null)
			remover = maiorEsquerda(raiz.getDireita());

		if (raiz.getDireita() == remover) {
			raiz.setDireita(remover.getEsquerda());
		}

		return remover;
	}

	/**
	 * Retorna a raiz da árvore
	 * 
	 * @return raiz
	 */
	@Override
	public Node getRaiz() {
		return raiz;
	}

	/**
	 * Percorre toda a árvore mostrando em preOrdem
	 */
	public void preOrdem() {
		if (raiz == null)
			System.out.println("Árvore vazia");
		else
			preOrdem(raiz);
	}
	/**
	 * Percorre toda a árvore mostrando em emOrdem
	 */
	public void emOrdem() {
		if (raiz == null)
			System.out.println("Árvore vazia");
		else
			emOrdem(raiz);
	}
	/**
	 * Percorre toda a árvore mostrando em posOrdem
	 */
	public void posOrdem() {
		if (raiz == null)
			System.out.println("Árvore vazia");
		else
			posOrdem(raiz);
	}

	/**
	 * Metodo recursivo para percorrer toda a árvore
	 * @param raiz
	 */
	private void preOrdem(Node raiz) {
		if (raiz == null)
			return;
		System.out.println(raiz);
		preOrdem(raiz.getEsquerda());
		preOrdem(raiz.getDireita());

	}
	/**
	 * Metodo recursivo para percorrer toda a árvore
	 * @param raiz
	 */
	private void emOrdem(Node raiz) {
		if (raiz == null)
			return;
		preOrdem(raiz.getEsquerda());
		System.out.println(raiz);
		preOrdem(raiz.getDireita());

	}
	/**
	 * Metodo recursivo para percorrer toda a árvore
	 * @param raiz
	 */
	private void posOrdem(Node raiz) {
		if (raiz == null)
			return;
		preOrdem(raiz.getEsquerda());
		preOrdem(raiz.getDireita());
		System.out.println(raiz);

	}

}
