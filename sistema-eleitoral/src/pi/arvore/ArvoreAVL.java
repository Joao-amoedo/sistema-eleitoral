package pi.arvore;

import pi.model.Elemento;
import pi.node.Node;


public class ArvoreAVL <T extends Elemento >extends ArvoreBalanceada<T>{




	
	public ArvoreAVL(T elemento) {
		super(elemento);

	}
	
	
	
	
	
	public boolean add(Elemento elemento) {
		System.out.println("fui invocado pq sou lindo");
		return super.add(elemento);
	}
	
	
	

	
	

}
