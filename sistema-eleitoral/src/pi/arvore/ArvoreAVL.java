package pi.arvore;

import pi.model.Elemento;
import pi.node.Node;

public class ArvoreAVL<T extends Elemento> extends ArvoreBalanceada<T> {

	public ArvoreAVL(T elemento) {
		super(elemento);

	}

	public ArvoreAVL() {
	}

	protected Node<T> add(Node<T> raiz, Node<T> novo) {
		Node<T> add = super.add(raiz, novo);

		add.setAlturaEFat();
		verificaFat(add.getFat());
//		return add;

		return verificaFatRetorno(add);
	}

	private Node<T> verificaFatRetorno(Node<T> add) {
		if (verificaFat(add.getFat()))
			return verificaBalanceamento(add);
		else
			return add;
	}

	public Node<T> verificaBalanceamento(Node<T> add) {
		int fat = add.getFat();
		Node<T> retorno;
		if (fat < -1) {
			if (add.getDireita().getFat() > 0)
				retorno = RDE(add);
			else
				retorno = RSE(add);
		} else {
			if (add.getEsquerda().getFat() < 0)
				retorno = RDD(add);
			else {
				retorno = RSD(add);
			}
		}
		setFatFilhosRetorno(retorno);
		return verificaFatRetorno(retorno);
	}

	private void setFatFilhosRetorno(Node<T> retorno) {
		int filhos = retorno.getQuantidadeDeFilhos();
		if (filhos == -1)
			retorno.getEsquerda().setAlturaEFat();
		else if (filhos == 1)
			retorno.getDireita().setAlturaEFat();
		else if (filhos == 2) {
			retorno.getDireita().setAlturaEFat();
			retorno.getEsquerda().setAlturaEFat();
		}
		retorno.setAlturaEFat();
	}

	private boolean verificaFat(int fat) {
		if (fat > 1 || fat < -1) {
			return true;
		} else
			return false;
	}

	protected Node<T> remove(Node<T> raiz, long elemento) {
		Node<T> remover = super.remove(raiz, elemento);
		remover.setAlturaEFat();
		if (verificaFat(remover.getFat())) {
			return verificaBalanceamento(remover);
		} else {
			return remover;
		}
	}
}
