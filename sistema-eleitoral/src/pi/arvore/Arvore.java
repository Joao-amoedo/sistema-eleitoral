package pi.arvore;

import pi.node.Node;

public interface Arvore {
	/**
	 * Metodo público que adiciona elemento na árvore, caso o elemeto seja repetido,
	 * retorna false
	 * 
	 * @return adiciona
	 * @param elemento
	 */
	public boolean add(Integer elemento);

	/**
	 * Metodo público de remoção
	 * 
	 * @return removido
	 * @param elemento
	 */
	public boolean remove(Integer elemento);
	
	/**
	 * Retorna a raiz da árvore
	 * @return raiz
	 */
	public Node getRaiz();

}
