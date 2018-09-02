package pi.arvore;

import pi.node.Node;

public interface Arvore {
	
	public boolean add(Integer elemento);
	
	public boolean remove(Integer elemento);
	
	public Node getRaiz();
	
	public int getAltura();
	
}
