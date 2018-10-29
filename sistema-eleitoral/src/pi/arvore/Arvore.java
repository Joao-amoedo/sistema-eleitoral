package pi.arvore;

import pi.model.Elemento;
import pi.node.Node;

public interface Arvore <T extends Elemento>{
	/**
	 * Metodo p�blico que adiciona elemento na �rvore, caso o elemeto seja repetido,
	 * retorna false
	 * 
	 * @return adiciona
	 * @param elemento
	 */
	public boolean add(T elemento);

	/**
	 * Metodo p�blico de remo��o
	 * 
	 * @return removido
	 * @param elemento
	 */
	public boolean remove(long elemento);
	
	/**
	 * Retorna a raiz da �rvore
	 * @return raiz
	 */
	public Node<T> getRaiz();
	
	
}
