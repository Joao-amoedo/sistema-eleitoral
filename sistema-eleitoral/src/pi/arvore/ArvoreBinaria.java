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
	 * Metodo privado
	 * 
	 * @param raiz
	 * @param novo
	 * @return
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
		return false;
	}

	@Override
	public Node getRaiz() {
		return raiz;
	}

	@Override
	public int getAltura() {
		return 0;
	}

}
