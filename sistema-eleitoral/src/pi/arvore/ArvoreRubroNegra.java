package pi.arvore;


import pi.model.Elemento;
import pi.node.Cor;
import pi.node.Node;

public class ArvoreRubroNegra<T extends Elemento> extends ArvoreBalanceada<T> {

	private Node<T> pivo = new Node<T>(null, Cor.PRETO);

	public ArvoreRubroNegra(T elemento) {
		super(elemento);
	}

	public boolean add(Elemento elemento) {

		@SuppressWarnings("unchecked")
		Node<T> novo = new Node<T>((T) elemento);
		novo.setDireita(pivo);
		novo.setEsquerda(pivo);
		try {
			raiz = add(this.raiz, null, novo);
			return true;
		} catch (IllegalArgumentException ex) {
			return false;
		}
	}

	
	
	private Cor verificaCorTio(Node<T> raiz) {
		Node<T> pai = raiz.getPai();
		if(pai.getDireita() == raiz)
			return pai.getEsquerda().getCor();
		else
			return pai.getDireita().getCor();
	}
	
	private void verificaBalanceamento(Node<T> raiz) {
		
		if(raiz.getCor() == raiz.getEsquerda().getCor()) {
			Cor corTio = verificaCorTio(raiz);
		}else if(raiz.getCor() == raiz.getDireita().getCor()) {
			Cor verificaCorTio = verificaCorTio(raiz);
		}
			
		
	}
	
	private Node<T> add(Node<T> raiz, Node<T> pai, Node<T> novo) {
		if (raiz == null) {
			novo.setPai(pai);
			return novo;
		} else if (raiz.getElemento() < novo.getElemento() && raiz.getEsquerda() != pivo) {
			raiz.setDireita(add(raiz.getDireita(), raiz, novo));
		} else if (raiz.getElemento() > novo.getElemento() && raiz.getDireita() != pivo) {
			raiz.setEsquerda(add(raiz.getEsquerda(), raiz, novo));
		} else {
			throw new IllegalArgumentException("Não pode haver elementos repetidos!");
		}

		return raiz;

	}

	public boolean remove(long elemento) {

		try {
			raiz = remove(raiz, null, elemento);
			return true;
		} catch (IllegalArgumentException ex) {
			return false;
		}
	}

	protected Node<T> remove(Node<T> raiz, Node<T> pai, long elemento) {
		if (raiz == null || raiz == pivo)
			throw new IllegalArgumentException("Elemento não encontrado");
		else if (raiz.getElemento() == elemento) {
			Node<T> remove = verificaTipoRemocao(raiz);
			if (remove != null)
				remove.setPai(pai);
			return pai;
		} else if (raiz.getElemento() < elemento) {
			raiz.setDireita(remove(raiz.getDireita(), elemento));
		} else {
			raiz.setEsquerda(remove(raiz.getEsquerda(), elemento));
		}
		return raiz;
	}

}
