package pi.arvore;

import java.util.Stack;

import pi.arvore.exception.ElementoRepetidoException;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Elemento;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.UF;
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

	public boolean add(long elemento) {
		Candidato cand1 = new Candidato("1", Partido.PT, TipoCandidato.PRESIDENCIAVEL);
		Candidato cand2 = new Candidato("4", Partido.PT, TipoCandidato.REGIONAL);
		Candidato cand3 = new Candidato("3", Partido.PT, TipoCandidato.FEDERAL);
		return add(new Eleitor(UF.MA, elemento, 0, cand1, cand2, cand3));
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
		boolean add = false;

		do {
			if (raiz.getElemento() == novo.getElemento()) {
				throw new ElementoRepetidoException();
			} else if (raiz.getElemento() < novo.getElemento()) {
				if (raiz.getDireita().equals(pivo)) {
					raiz.setDireita(novo);
					novo.setPai(raiz);
					add = true;
				} else
					raiz = raiz.getDireita();
			} else {
				if (raiz.getEsquerda().equals(pivo)) {
					raiz.setEsquerda(novo);
					novo.setPai(raiz);
					add = true;
				} else
					raiz = raiz.getEsquerda();
			}
		} while (!add);
		verificaCor(novo.getPai(), novo.getElemento());
	}

	public void verificaCor(Node<T> raiz, long x) {
		boolean balanceia = false;
		do {
			balanceia = false;
			if (raiz.getCor() == Cor.VERMELHO
					&& (raiz.getDireita().getCor() == Cor.VERMELHO || raiz.getEsquerda().getCor() == Cor.VERMELHO)) {
				Node<T> tio = getTio(raiz);
				/**
				 * Caso a raiz e o tio sejam vermelhos, recolori os dois para pretos
				 */
				if (tio.getCor() == Cor.VERMELHO) {
					raiz.setCor(Cor.PRETO);
					tio.setCor(Cor.PRETO);
					/**
					 * Recolorindo a cor do pai da raiz para vermelho caso não seja a raiz
					 */
					if (!raiz.getPai().equals(this.raiz)) {
						raiz.getPai().setCor(Cor.VERMELHO);
						raiz = raiz.getPai();
						balanceia = true;
					}
					/**
					 * Caso a cor do tio seja preta, deve-se rebalancear
					 */
				} else {
					Node<T> pai = raiz.getPai();
					Node<T> avo = pai.getPai();
					/**
					 * Caso o pai seja a raiz
					 */
					if (pai.equals(this.raiz)) {
						this.raiz = verificaTipoBalanceamento(pai, raiz, avo);
						/**
						 * Caso o pai não seja a raiz
						 */
					} else if (avo.getEsquerda().equals(pai)) {
						avo.setEsquerda(verificaTipoBalanceamento(pai, raiz, avo));
					} else {
						avo.setDireita(verificaTipoBalanceamento(pai, raiz, avo));
					}
				}
			} else {
				/**
				 * Caso o node em questão seja vermelho e o pai também, sobe um nível e repete o
				 * loop
				 */
				if (raiz.getCor() == Cor.VERMELHO && raiz.getPai().getCor() == Cor.VERMELHO) {
					raiz = raiz.getPai();
					balanceia = true;
				}
			}
		} while (balanceia);
	}

	/**
	 * Balanceamento simples a direita
	 * 
	 * @return rsd
	 * @param raiz
	 */
	protected Node<T> RSD(Node<T> raiz) {
		Node<T> pai = raiz.getPai();
		Node<T> aux = raiz.getEsquerda();
		Node<T> auxDir = aux.getDireita();
		raiz.setEsquerda(auxDir);
		auxDir.setPai(raiz);
		aux.setDireita(raiz);
		raiz.setPai(aux);
		aux.setPai(pai);
		return aux;
	}

	/**
	 * Balanceamento simples a esquerda
	 * 
	 * @return rse
	 * @param raiz
	 */

	protected Node<T> RSE(Node<T> raiz) {
		Node<T> pai = raiz.getPai();
		Node<T> aux = raiz.getDireita();
		Node<T> auxEsq = aux.getEsquerda();
		raiz.setDireita(auxEsq);
		auxEsq.setPai(raiz);
		aux.setEsquerda(raiz);
		raiz.setPai(aux);
		aux.setPai(pai);
		return aux;
	}

	protected Node<T> RDD(Node<T> raiz) {
		raiz.setEsquerda(RSE(raiz.getEsquerda()));
		return RSD(raiz);
	}

	protected Node<T> RDE(Node<T> raiz) {
		raiz.setDireita(RSD(raiz.getDireita()));
		return RSE(raiz);
	}

	private Node<T> verificaTipoBalanceamento(Node<T> raiz, Node<T> filho, Node<T> pai) {

		Node<T> rotacao = null;
		if (raiz.getEsquerda() == filho) {
			/**
			 * RSD
			 */
			if (filho.getEsquerda().getCor() == Cor.VERMELHO) {
				rotacao = RSD(raiz);
			}
			/**
			 * RDD
			 */
			else {

				rotacao = RDD(raiz);
			}
		} else {
			/**
			 * RSE
			 */
			if (filho.getDireita().getCor() == Cor.VERMELHO) {
				rotacao = RSE(raiz);

			}
			/**
			 * RDE
			 */
			else {
				rotacao = RDE(raiz);
			}
		}

		rotacao.setCor(Cor.PRETO);
		if (!rotacao.getEsquerda().equals(pivo))
			rotacao.getEsquerda().setCor(Cor.VERMELHO);
		if (!rotacao.getDireita().equals(pivo))
			rotacao.getDireita().setCor(Cor.VERMELHO);
		rotacao.setPai(pai);
		raiz.setPai(rotacao);
		return rotacao;
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
