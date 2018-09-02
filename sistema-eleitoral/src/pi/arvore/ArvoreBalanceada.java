package pi.arvore;
import pi.node.Node;


public abstract class ArvoreBalanceada extends ArvoreBinariaDeBusca{
	
	public ArvoreBalanceada(int elemento) {
		super(elemento);
	}

	protected abstract Node RSE();

	protected abstract Node RSD();
	
	protected abstract Node RDE();
	
	protected abstract Node RDD();
	
}
