package pi.arvore;

import java.util.Stack;

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
		aux = null;
		Node<T> auxPai = null;
		boolean balanceia = false;
		while (!p1.empty()) {
			Node<T> pop = p1.pop();
			if (balanceia) {
				System.out.println("Tem que balancear");
				if (auxPai.equals(pop)) {
					Node<T> balanceado = verificaTipoBalanceamento(pop, aux, pop.getPai());
					if (pop == this.raiz) {
						this.raiz = balanceado;
					} else {
						Node<T> paiPop = pop.getPai();
						if (paiPop.getDireita().equals(pop))
							paiPop.setDireita(balanceado);
						else
							paiPop.setEsquerda(balanceado);
					}
					p1.pop();
					aux = null;
					auxPai = null;
					balanceia = false;
				}
			} else if (!pop.equals(this.raiz) && !pop.equals(auxPai)) {
				if (verificaCor(pop)) {
					aux = pop;
					auxPai = aux.getPai();
					balanceia = true;
				}
			}
		}
	}

	public boolean verificaCor(Node<T> raiz) {
		Node<T> tio = this.getTio(raiz);
		if (raiz.getCor() == Cor.PRETO)
			return false;
		else if (raiz.getCor() == Cor.VERMELHO
				&& (raiz.getEsquerda().getCor() == Cor.VERMELHO || raiz.getDireita().getCor() == Cor.VERMELHO)) {
			if (tio.getCor() == Cor.VERMELHO) {
				tio.setCor(Cor.PRETO);
				raiz.setCor(Cor.PRETO);
				return false;
			} else {
				// tem que balancear
				return true;
			}
		} else
			return false;
	}

	private Node<T> verificaTipoBalanceamento(Node<T> raiz, Node<T> filho, Node<T> pai) {
		Node<T> rotacao = null;
		if (raiz.getEsquerda() == filho) {
			if (filho.getEsquerda().getCor() == Cor.VERMELHO) {
				rotacao = RSD(raiz);

			} else {
				Node<T> rse = RSE(raiz.getEsquerda());

				rotacao = RDD(raiz);
			}
		} else {
			if (filho.getDireita().getCor() == Cor.VERMELHO) {
				rotacao = RSE(raiz);

			} else {
				Node<T> rsd = RSD(raiz.getDireita());
				rotacao = RDE(raiz);
			}
		}

		rotacao.setCor(Cor.PRETO);
		rotacao.getEsquerda().setCor(Cor.VERMELHO);
		rotacao.getDireita().setCor(Cor.VERMELHO);
		rotacao.setPai(pai);
		raiz.setPai(rotacao);
		return rotacao;

		// Caso 3a rotação direita simples

//			if (raiz.getEsquerda().getCor() == Cor.VERMELHO) {
//				return RSD(raiz);
//			} else {
//				return RDD(raiz);
//			
//		} else {
//			if (raiz.getDireita().getCor() == Cor.VERMELHO) {
//				return RSE(raiz);
//			}
//			return RDE(raiz);
//		}

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
