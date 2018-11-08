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

	private void verificaCor(Node<T> raiz) {

		Cor raizCor = raiz.getCor();

		// Primeiro Caso
		if (raizCor == Cor.PRETO) {
			// Faz nada
		} else if (raizCor == Cor.VERMELHO) {
			Node<T> tio = getTio(raiz);
			// Segundo caso
			// Muda a cor do node do tio e da raiz em quetão para preto
			if (tio.getCor() == Cor.VERMELHO) {
				raiz.setCor(Cor.PRETO);
				tio.setCor(Cor.PRETO);
				// Terceiro caso.. rotações..
			} else {
				Node<T> node = verificaTipoBalanceamento(raiz);
			}
		}
	}

	private Node<T> verificaTipoBalanceamento(Node<T> raiz) {
		if (raiz == raiz.getPai().getEsquerda()) {
			if (raiz.getEsquerda().getCor() == Cor.VERMELHO)
				RSD(raiz);
			else
				RDD(raiz);
		}
		else {
			if(raiz.getDireita().getCor() == Cor.VERMELHO)
				RSE(raiz);
			else
				RDE(raiz);
		}
		return null;
	}

	private Node<T> getTio(Node<T> raiz) {
		Node<T> pai = raiz.getPai();
		if (pai.getDireita() == raiz)
			return pai.getEsquerda();
		else
			return pai.getDireita();
	}

	private Node<T> add(Node<T> raiz, Node<T> pai, Node<T> novo) {
		if (raiz == null || raiz == pivo) {
			novo.setPai(pai);
			if (this.raiz == null)
				novo.setCor(Cor.PRETO);
			return novo;
		} else if (raiz.getElemento() < novo.getElemento()) {
			raiz.setDireita(add(raiz.getDireita(), raiz, novo));
		} else if (raiz.getElemento() > novo.getElemento()) {
			raiz.setEsquerda(add(raiz.getEsquerda(), raiz, novo));
		} else {
			throw new IllegalArgumentException("Não pode haver elementos repetidos!");
		}
		verificaCor(raiz);
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
