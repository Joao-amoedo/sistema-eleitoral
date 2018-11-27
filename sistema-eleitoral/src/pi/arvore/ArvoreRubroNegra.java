package pi.arvore;

import java.util.Stack;

import pi.arvore.exception.NotFoundElementException;
import pi.arvore.exception.RepeatedElementException;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Elemento;
import pi.model.Partido;
import pi.model.TipoCandidato;
import pi.model.UF;
import pi.node.Cor;
import pi.node.Node;

public class ArvoreRubroNegra<T extends Elemento> extends ArvoreBalanceada<T> {

	public Node<T> pivo = new Node<T>(null, Cor.NEGRO);

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
			raiz.setCor(Cor.NEGRO);
		} else {
			try {
				add(this.raiz, null, novo);
				if (raiz == novo)
					raiz.setCor(Cor.NEGRO);
				return true;
			} catch (RepeatedElementException ex) {
				return false;
			}
		}
		return true;
	}

	private void add(Node<T> raiz, Node<T> pai, Node<T> novo) {
		boolean add = false;

		do {
			if (raiz.getElemento() == novo.getElemento()) {
				throw new RepeatedElementException();
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
			if (raiz.getCor() == Cor.RUBRO
					&& (raiz.getDireita().getCor() == Cor.RUBRO || raiz.getEsquerda().getCor() == Cor.RUBRO)) {
				Node<T> tio = getTio(raiz);
				/**
				 * Caso a raiz e o tio sejam vermelhos, recolori os dois para pretos
				 */
				if (tio.getCor() == Cor.RUBRO) {
					raiz.setCor(Cor.NEGRO);
					tio.setCor(Cor.NEGRO);
					/**
					 * Recolorindo a cor do pai da raiz para vermelho caso não seja a raiz
					 */
					if (!raiz.getPai().equals(this.raiz)) {
						raiz.getPai().setCor(Cor.RUBRO);
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
				if (raiz.getCor() == Cor.RUBRO && raiz.getPai().getCor() == Cor.RUBRO) {
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
			if (filho.getEsquerda().getCor() == Cor.RUBRO) {
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
			if (filho.getDireita().getCor() == Cor.RUBRO) {
				rotacao = RSE(raiz);

			}
			/**
			 * RDE
			 */
			else {
				rotacao = RDE(raiz);
			}
		}

		rotacao.setCor(Cor.NEGRO);
		if (!rotacao.getEsquerda().equals(pivo))
			rotacao.getEsquerda().setCor(Cor.RUBRO);
		if (!rotacao.getDireita().equals(pivo))
			rotacao.getDireita().setCor(Cor.RUBRO);
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

//	public boolean remove(long elemento) {
//
//		try {
//			raiz = remove(raiz, elemento);
//			return true;
//		} catch (NotFoundElementException ex) {
//			System.out.println(ex.getMessage());
//			return false;
//		}
//	}

	public boolean remove(long elemento) {
		
		Node<T> aux = this.raiz;

		do {
			/**
			 * Caso o Node não esteja na árvore
			 */
			if (aux.equals(null) || aux.equals(pivo))
				return false;
			/**
			 * Qunado encontra o Node que deve ser removido
			 */
			else if (aux.getElemento() == elemento) {
//				System.out.println("Achei o elemento! : " + raiz.getElemento());
				Node<T> remove = verificaTipoRemocao(aux);
				if (this.raiz == aux) {
					this.raiz = remove;
					aux.setPai(null);
					definePai(aux, remove);
				} else {
					Node<T> pai = aux.getPai();
					definePai(aux, remove);

				}
				if(remove !=(null))
					balanceiaRemove(aux, remove);
				return true;
			}
			/**
			 * Caso o elemento do Node seja menor do que o elemento
			 */
			else if (aux.getElemento() < elemento)
				aux = aux.getDireita();
			/**
			 * Caso o elemento do Node seja maior do que o elemento
			 */
			else if (aux.getElemento() > elemento)
				aux = aux.getEsquerda();
		} while (true);
	}

	private void balanceiaRemove(Node<T> removido, Node<T> sucessor) {
		Cor rCor = removido.getCor();
		Cor sCor = sucessor.getCor();
		Cor negro = Cor.NEGRO;
		Cor rubro = Cor.RUBRO;
		/**
		 * Caso 1 - Removido = Rubro, Sucessor = Rubro; Faz nada
		 */
		if (rCor.equals(rubro) && sCor.equals(rubro)) {
		}
		/**
		 * Caso 2 - Removido = Preto, Sucessor = Vermelho; Pinte o Sucessor para Preto
		 */
		else if (rCor.equals(negro) && sCor.equals(rubro)) {
			sucessor.setCor(negro);
		}
		/**
		 * Caso 3 - Removido = Preto, Sucessor = Preto;
		 */
		else if (rCor.equals(negro) && sCor.equals(negro)) {
			verificaCorIrmao(removido);
		}

	}

	private boolean verificaCorIrmao(Node<T> removido) {
		Node<T> pai = removido.getPai();

		if (pai.getEsquerda().equals(removido)) {
			if (pai.equals(null) || pai.getDireita().getCor().equals(Cor.NEGRO))
				remocao3Caso1(removido, pai.getDireita());
			else
				remocao3Caso2(removido, pai.getDireita());
		} else {
			if (pai.getEsquerda().getCor().equals(Cor.NEGRO))
				remocao3Caso1(removido, pai.getEsquerda());
			else
				remocao3Caso2(removido, pai.getEsquerda());
		}

		return false;

	}

	private void remocao3Caso2(Node<T> sucessor, Node<T> irmao) {
		Node<T> pai = sucessor.getPai();
		Cor corPai = pai.getCor();
		Cor esqCor = sucessor.getEsquerda().getCor();
		Cor dirCor = sucessor.getDireita().getCor();
		Cor corIrmao = irmao.getCor();
		Cor sCor = sucessor.getCor();
		Cor negro = Cor.NEGRO;
		Cor rubro = Cor.RUBRO;

		/**
		 * Caso a, se todo mundo for negro
		 */
		if (sCor.equals(negro) && esqCor.equals(negro) && dirCor.equals(negro) && corIrmao.equals(negro)) {

			if (irmao.getDireita().getCor().equals(rubro)) {
				Node<T> rse = RSE(pai);
				sucessor.getPai().setCor(negro);
				irmao.setCor(corPai);
				irmao.getDireita().setCor(negro);
			} else if (irmao.getEsquerda().getCor().equals(rubro)) {
				Node<T> rse = RSE(irmao);
				rse.setCor(negro);
				irmao.setCor(rubro);
			} else if (corPai.equals(negro))
				irmao.setCor(rubro);
			else if (corPai.equals(rubro)) {

				irmao.setCor(rubro);
				pai.setCor(negro);

			}
		}
	}

	private void remocao3Caso1(Node<T> sucessor, Node<T> irmao) {
		Node<T> pai = sucessor.getPai();
		Node<T> rse = RSE(pai);

		if (pai.getEsquerda().equals(sucessor)) {
			pai.setEsquerda(rse);
		} else {
			pai.setDireita(rse);
		}
		rse.setPai(pai);
		sucessor.setPai(rse);
		sucessor.getDireita().setPai(sucessor);
		irmao.setCor(Cor.NEGRO);
		rse.setCor(Cor.NEGRO);
		pai.setCor(Cor.RUBRO);
		this.raiz.setCor(Cor.NEGRO);

	}

	/**
	 * Redefine o parentesco da árvore após a remoção
	 * 
	 * @param raiz
	 * @param remove
	 */
	private void definePai(Node<T> raiz, Node<T> remove) {
		int filhos = raiz.getQuantidadeDeFilhos();
		if (filhos == 1)
			raiz.getDireita().setPai(remove);
		else if (filhos == -1)
			raiz.getEsquerda().setPai(remove);
		else if (filhos == 2) {
			raiz.getDireita().setPai(remove);
			raiz.getEsquerda().setPai(remove);
		}
		if (raiz.getPai() != null) {
			Node<T> pai = raiz.getPai();
			if (pai.getEsquerda().equals(raiz)) {
				pai.setEsquerda(remove);
			} else {
				pai.setDireita(remove);
			}
		}
	}
}
