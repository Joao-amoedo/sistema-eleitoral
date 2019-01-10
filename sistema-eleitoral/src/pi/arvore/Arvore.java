package pi.arvore;

import pi.node.Node;

public interface Arvore {
	/**
	 * Metodo p�blico que adiciona elemento na �rvore, caso o elemeto seja repetido,
	 * retorna false
	 * 
	 * @return adiciona
	 * @param elemento
	 */
	public boolean add(Integer elemento);

	/**
	 * Metodo p�blico de remo��o
	 * 
	 * @return removido
	 * @param elemento
	 */
	public boolean remove(Integer elemento);
	
	/**
	 * Retorna a raiz da �rvore
	 * @return raiz
	 */
	public Node getRaiz();

}
