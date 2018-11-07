package pi.arvore;

import pi.model.Elemento;
import pi.node.Cor;
import pi.node.Node;

public class ArvoreRubroNegra<T extends Elemento> extends ArvoreBalanceada<T> {

	public ArvoreRubroNegra(T elemento) {
		super(elemento);
	}

	public ArvoreRubroNegra() {
		super();
	}

	public Node<T> pivo = new Node<T>(null, Cor.PRETO);

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
		if (pai.getDireita() == raiz)
			return pai.getEsquerda().getCor();
		else
			return pai.getDireita().getCor();
	}

	private void verificaBalanceamento(Node<T> raiz) {

		if (raiz.getCor() == raiz.getEsquerda().getCor()) {
			Cor corTio = verificaCorTio(raiz);
		} else if (raiz.getCor() == raiz.getDireita().getCor()) {
			Cor verificaCorTio = verificaCorTio(raiz);
		}

	}

	private Node<T> add(Node<T> raiz, Node<T> pai, Node<T> novo) {
		if (raiz == null || raiz == pivo) {
			novo.setPai(pai);
			return novo;
		} else if (raiz.getElemento() < novo.getElemento()) {
			raiz.setDireita(add(raiz.getDireita(), raiz, novo));
		} else if (raiz.getElemento() > novo.getElemento()) {
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
			System.out.println(ex.getMessage());
			return false;
		}
	}

	protected Node<T> remove(Node<T> raiz, Node<T> pai, long elemento) {
		if (raiz == null)
			throw new IllegalArgumentException("Elemento não encontrado");
		else if (raiz.getElemento() == elemento) {
			Node<T> remove = verificaTipoRemocao(raiz);
			int filhos = raiz.getQuantidadeDeFilhos();
			definePai(raiz, filhos, remove);
			if (remove != null) {
				remove.setPai(pai);
			}

			return remove;
		} else if (raiz.getElemento() < elemento) {
			raiz.setDireita(remove(raiz.getDireita(), elemento));
		} else {
			raiz.setEsquerda(remove(raiz.getEsquerda(), elemento));
		}
		return raiz;
	}

	private void definePai(Node<T> raiz, int filhos, Node<T> remove) {
		if (filhos == 1)
			raiz.getDireita().setPai(remove);
		else if (filhos == -1)
			raiz.getEsquerda().setPai(remove);
		else if (filhos == 2) {
			raiz.getDireita().setPai(remove);
			raiz.getEsquerda().setPai(remove);
		}
	}

}
