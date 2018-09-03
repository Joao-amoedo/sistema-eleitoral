package pi.arvore;
import pi.node.Node;


public abstract class ArvoreBalanceada extends ArvoreBinariaDeBusca{
	
	public ArvoreBalanceada(int elemento) {
		super(elemento);
	}

	protected abstract Node RSE(Node raiz);

	protected abstract Node RSD(Node raiz);
	
	protected abstract Node RDE(Node raiz);
	
	protected abstract Node RDD(Node raiz);
	
}
