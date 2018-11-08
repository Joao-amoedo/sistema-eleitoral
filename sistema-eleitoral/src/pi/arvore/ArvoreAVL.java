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
		if (verificaFat(add.getFat()))
			return add;
		else
			return verificaBalanceamento(add);
	}

	public Node<T> verificaBalanceamento(Node<T> add) {
		int fat = add.getFat();
		System.out.println(add.getFat());
		if (fat < -1) {
			if (add.getDireita().getFat() < 0)
				return RSE(add);
			else
				return RDE(add);
		} else {
			if(add.getEsquerda().getFat() < 0)
			return RSD(add);
			else
				return RDE(add);
		}
	}

	private boolean verificaFat(int fat) {
		if (fat > 1 || fat < 1)
			return false;
		else
			return true;
	}

}
