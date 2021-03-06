package pi.arvore;

import java.util.Stack;

import pi.arvore.exception.FoundElementException;
import pi.model.Elemento;
import pi.node.Node;

public class ArvoreBinariaDeBusca<T extends Elemento> extends ArvoreBinaria<T> {

	public ArvoreBinariaDeBusca(T elemento) {
		super(elemento);
	}

	public ArvoreBinariaDeBusca() {
		super();
	}

	public T buscaBinaria(long elemento) {
		if(this.raiz == null)
			return null;
		return buscaBinaria(elemento, this.raiz);

	}

	private T buscaBinaria(long elemento, Node<T> raiz) {

		if (raiz.getElemento() > elemento)
			if (raiz.getEsquerda() != null && raiz.getEsquerda().getConteudo() != null)
				return buscaBinaria(elemento, raiz.getEsquerda());
			else
				return null;
		else if (raiz.getElemento() < elemento)
			if (raiz.getDireita() != null && raiz.getDireita().getConteudo() != null)
				return buscaBinaria(elemento, raiz.getDireita());
			else
				return null;
		else {
			return raiz.getConteudo();
		}

	}

	public T buscaProfundidade(long elemento) {
		if(this.raiz == null)
			return null;
		try {
			buscaProfundidade(elemento, this.raiz);
			return null;
		} catch (FoundElementException e) {
			Node<T> node = (Node<T>) e.getNode();
			return node.getConteudo();
		}
	}

	private void buscaProfundidade(long elemento, Node<T> raiz) {
		if (raiz.getElemento() == elemento)
			throw new FoundElementException(raiz);
		if (raiz.getEsquerda() != null && raiz.getEsquerda().getConteudo() != null)
			buscaProfundidade(elemento, raiz.getEsquerda());

		if (raiz.getDireita() != null && raiz.getDireita().getConteudo() != null)
			buscaProfundidade(elemento, raiz.getDireita());
	}

	public T buscaLargura(long elemento) {
		if(this.raiz == null)
			return null;
		Stack<Node<T>> p1 = new Stack<Node<T>>();
		Stack<Node<T>> p2 = new Stack<Node<T>>();
		p1.push(this.raiz);
		while (true) {
			while (!p1.empty()) {
				Node<T> aux = p1.pop();
//				System.out.println(aux.getElemento());
				if (aux.getElemento() == elemento)
					return aux.getConteudo();

				if (aux.getEsquerda() != null && aux.getEsquerda().getConteudo() != null)
					p2.push(aux.getEsquerda());

				if (aux.getDireita() != null && aux.getDireita().getConteudo() != null)
					p2.push(aux.getDireita());

			}
			while (!p2.empty())
				p1.push(p2.pop());
			if (p1.empty())
				return null;
		}

	}

}
