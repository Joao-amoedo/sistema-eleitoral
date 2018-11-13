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
		Node<T> aux = raiz;
		boolean add = false;
		p1.push(aux);
		while (!add) {
			if (aux.getElemento() < novo.getElemento()) {
				if (aux.getDireita() == pivo) {
					aux.setDireita(novo);
					novo.setPai(aux);
					add = true;
				} else {
					aux = aux.getDireita();
					p1.push(aux);
				}
			} else if (aux.getElemento() > novo.getElemento()) {
				if (aux.getEsquerda() == pivo) {
					aux.setEsquerda(novo);
					novo.setPai(aux);
					add = true;
				} else {
					aux = aux.getEsquerda();
					p1.push(aux);
				}
			} else {
				throw new ElementoRepetidoException();
			}
		}
		boolean balanceia = false;
		aux = null;
		while (!p1.empty()) {
			Node<T> pop = p1.pop();
			if (balanceia) {
				if (aux == pop) {
					pop = verificaTipoBalanceamento(pop);
					aux = null;
					balanceia = false;
				}
			}
			if (pop != this.raiz) {
				balanceia = verificaCor(pop);
				if (balanceia)
					aux = pop.getPai();
			}
		}
	}

	public boolean verificaCor(Node<T> raiz) {
		Node<T> tio = getTio(raiz);
		Cor corRaiz = raiz.getCor();
		Cor corDir = raiz.getDireita().getCor();
		Cor corEsq = raiz.getEsquerda().getCor();
		Cor corTio = tio.getCor();

		if (corRaiz == Cor.VERMELHO) {
			if (corDir == Cor.VERMELHO) {
				if (corTio == Cor.VERMELHO) {
					tio.setCor(Cor.PRETO);
					raiz.setCor(Cor.PRETO);
				} else
					return true;
			} else if (corEsq == Cor.VERMELHO) {
				if (corTio == Cor.VERMELHO) {
					tio.setCor(Cor.PRETO);
					raiz.setCor(Cor.PRETO);
				} else {
					System.out.println("foi esse o caso");
					return true;
				}
			}
		} else {
			// Faz nada
		}
		return false;
	}

	private Node<T> verificaTipoBalanceamento(Node<T> raiz) {
		Node<T> pai = raiz.getPai();
		// Caso 3a rotação direita simples
		
			if (raiz.getEsquerda().getCor() == Cor.VERMELHO) {
				return RSD(raiz);
			} else {
				return RDD(raiz);
			
		} else {
			if (raiz.getDireita().getCor() == Cor.VERMELHO) {
				return RSE(raiz);
			}
			return RDE(raiz);
		}

	}

	private Node<T> getTio(Node<T> raiz) {
		Node<T> pai = raiz.getPai();
		if (pai.getDireita() == raiz)
			return pai.getEsquerda();
		else
			return pai.getDireita();
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
