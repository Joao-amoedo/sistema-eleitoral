package pi.arvore;
import pi.node.Node;


public interface ArvoreDeBusca extends Arvore{
	
	public Node buscaBinaria(Integer elemento);
	
	public Node buscaProfundidade(Integer elemento);
	
	public Node buscaLargura(Integer elemento);
	
}
