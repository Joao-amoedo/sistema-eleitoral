package pi.arvore;

import pi.model.Elemento;
import pi.node.Node;

public interface Arvore <T extends Elemento>{
	/**
	 * Metodo público que adiciona elemento na árvore, caso o elemeto seja repetido,
	 * retorna false
	 * 
	 * @return adiciona
	 * @param elemento
	 */
	public boolean add(T elemento);

	/**
	 * Metodo público de remoção
	 * 
	 * @return removido
	 * @param elemento
	 */
	public boolean remove(long elemento);
	
	/**
	 * Retorna a raiz da árvore
	 * @return raiz
	 */
	public Node<T> getRaiz();
	
	
}
