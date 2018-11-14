package pi.arvore;

import java.util.Stack;

import pi.arvore.exception.ElementoRepetidoException;
import pi.model.Elemento;
import pi.node.Cor;
import pi.node.Node;

public class ArvoreRubroNegra<T extends Elemento> extends ArvoreBalanceada<T> {

	public Node<T> pivo = new Node<T>(null, Cor.PRETO);

	public ArvoreRubroNegra(T elemento) {
		super(elemento);
	}

	public ArvoreRubroNegra() {
		super();
	}

	public boolean add(Elemento elemento) {

		@SuppressWarnings("unchecked")
		Node<T> novo = new Node<T>((T) elemento);
		novo.setDireita(pivo);
		novo.setEsquerda(pivo);
		if (raiz == null) {
			raiz = novo;
			raiz.setCor(Cor.PRETO);
		} else {
			try {
				add(this.raiz, null, novo);
				if (raiz == novo)
					raiz.setCor(Cor.PRETO);
				return true;
			} catch (ElementoRepetidoException ex) {
				return false;
			}

		}
		return true;
	}

	private void add(Node<T> raiz, Node<T> pai, Node<T> novo) {
		Stack<Node<T>> p1 = new Stack<Node<T>>();
		p1.push(raiz);
		Node<T> aux = raiz;
		boolean add = false;
		while (!add) {
			if (aux == pivo) {
				if (pai.getElemento() < novo.getElemento()) {
					pai.setDireita(novo);
				} else {
					pai.setEsquerda(novo);
				}
				add = true;
				novo.setPai(pai);
			} else if (aux.getElemento() < novo.getElemento()) {
				p1.push(aux);
				pai = aux;
				aux = aux.getDireita();
			} else if (aux.getElemento() > novo.getElemento()) {
				p1.push(aux);
				pai = aux;
				aux = aux.getEsquerda();
			} else
				throw new ElementoRepetidoException();
		}

		Node<T> paiNovo = novo.getPai();
		Node<T> avoNovo = paiNovo.getPai();
		boolean recolori = false;

		do {
//			if (novo.getElemento() == 8) {
//				System.out.println(paiNovo.pai.getElemento() + "  " + paiNovo.pai.getCor());
//				System.out.println(avoNovo.pai.getElemento() + "  " + avoNovo.pai.getCor());
//			}
			recolori = false;
			if (paiNovo.getCor() == Cor.VERMELHO) {
				Node<T> tio = getTio(paiNovo);
				if (tio.getCor() == Cor.VERMELHO) {
					tio.setCor(Cor.PRETO);
					paiNovo.setCor(Cor.PRETO);
					if (!avoNovo.equals(this.raiz)) {
						avoNovo.setCor(Cor.VERMELHO);
						if (avoNovo.pai.getCor().equals(Cor.VERMELHO)) {
							paiNovo = avoNovo.getPai();
							avoNovo = avoNovo.getPai().pai;
							recolori = true;
						}
					}
				} else {
					if (this.raiz == paiNovo.getPai()) {
						this.raiz = verificaTipoBalanceamento(avoNovo, paiNovo, avoNovo.getPai());
					} else {
						if (avoNovo.getPai().getEsquerda().equals(avoNovo)) {
							avoNovo.getPai().setEsquerda(verificaTipoBalanceamento(avoNovo, paiNovo, avoNovo.getPai()));
						} else {
//							if (novo.getElemento() == 8)
//								System.out.println(avoNovo.pai.getElemento());
							avoNovo.getPai().setDireita(verificaTipoBalanceamento(avoNovo, paiNovo, avoNovo.getPai()));
						}
					}
				}
			} else {
			}
		} while (recolori);

//		esvaziaAPilha(p1, null);
	}

	private Node<T> verificaTipoBalanceamento(Node<T> raiz, Node<T> filho, Node<T> pai) {

		Node<T> rotacao = null;
		if (raiz.getEsquerda() == filho) {
			if (filho.getEsquerda().getCor() == Cor.VERMELHO) {
				rotacao = RSD(raiz);

			} else {
				Node<T> rse = RSE(raiz.getEsquerda());
				rse.getEsquerda().setPai(rse);
				rse.setPai(raiz);
				raiz.setEsquerda(rse);
				rotacao = RSD(raiz);
			}
		} else {
			if (filho.getDireita().getCor() == Cor.VERMELHO) {
				rotacao = RSE(raiz);

			} else {
				Node<T> rsd = RSD(raiz.getDireita());
				rsd.getDireita().setPai(rsd);
				rsd.setPai(raiz);
				raiz.setDireita(rsd);
				rotacao = RSE(raiz);
			}
		}

		rotacao.setCor(Cor.PRETO);
		rotacao.getEsquerda().setCor(Cor.VERMELHO);
		rotacao.getDireita().setCor(Cor.VERMELHO);
		rotacao.setPai(pai);
		raiz.setPai(rotacao);
		return rotacao;

	}

	private void esvaziaAPilha(Stack<Node<T>> p1, Node<T> auxFilho) {

	}

	public boolean verificaCor(Node<T> raiz) {
		return false;
	}

	private Node<T> getTio(Node<T> raiz) {
		Node<T> pai = raiz.getPai();
		Node<T> direita = pai.getDireita();
		Node<T> esquerda = pai.getEsquerda();
		if (direita.equals(raiz))
			return esquerda;
		else
			return direita;
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
