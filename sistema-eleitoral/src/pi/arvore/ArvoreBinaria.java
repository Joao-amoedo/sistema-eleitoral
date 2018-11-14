package pi.arvore;

import java.util.ArrayList;
import java.util.List;

import pi.DAO.DAOCandidato;
import pi.DAO.DAOEleitor;
import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Elemento;
import pi.node.Cor;
import pi.node.Node;

public class ArvoreBinaria<T extends Elemento> implements Arvore {

	public Node<T> raiz;

	/**
	 * True para caso vá gravar no arquivo False para caso não vá gravar no arquivo
	 * 
	 * @param elemento
	 * @param grava
	 */
	public ArvoreBinaria(T elemento, boolean grava) {
		raiz = new Node<T>(elemento, Cor.PRETO);
		if (grava)
			this.gravaArquivo(raiz);
	}

	public ArvoreBinaria(T elemento) {
		raiz = new Node<T>(elemento, Cor.PRETO);
	}

	public ArvoreBinaria() {
		this.raiz = null;
	}

	protected void gravaArquivo(Node<T> novo) {
		T conteudo = novo.getConteudo();
		if (conteudo instanceof Eleitor) {
			try {
				DAOEleitor.gravarArquivoUnico(conteudo.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				DAOCandidato.gravarArquivo(conteudo.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean add(Elemento elemento, boolean grava) {
		Node novo = new Node(elemento);
		boolean add = add(elemento);
		if (add && grava)
			gravaArquivo(novo);
		if (add)
			return true;
		else
			return false;
	}

	@Override
	public boolean add(Elemento elemento) {
		Node<T> novo = new Node<T>((T) elemento);
		try {
			raiz = add(raiz, novo);
			return true;
		} catch (IllegalArgumentException ex) {
			return false;
		}
	}

	public boolean add(Elemento elemento, boolean bol, boolean bol2) {
		Node<T> novo = new Node<T>((T) elemento);
		if (this.raiz == null) {
			raiz = novo;
			return true;
		}
		else {
			Node<T> aux = raiz;
			while (true) {
				if (novo.getElemento() < aux.getElemento()) {
					if (aux.getEsquerda() != null)
						aux = aux.getEsquerda();
					else{
						aux.setEsquerda(novo);
						return true;
					}
				}else if(novo.getElemento() > aux.getElemento()) {
					if(aux.getDireita() != null)
						aux = aux.getDireita();
					else {
						aux.setDireita(novo);
						return true;
					}
				}else {
					return false;
				}
			}
		}
	}

	protected Node<T> add(Node<T> raiz, Node<T> novo) {
		if (raiz == null)
			return novo;
		else if (raiz.getElemento() == novo.getElemento())
			throw new IllegalArgumentException("Elemento repetido");
		else if (raiz.getElemento() < novo.getElemento())
			raiz.setDireita(add(raiz.getDireita(), novo));
		else
			raiz.setEsquerda(add(raiz.getEsquerda(), novo));
		return raiz;
	}

	public boolean remove(long elemento) {
		try {
			raiz = remove(raiz, elemento);
			return true;
		} catch (IllegalArgumentException ex) {
			return false;
		}
	}

	protected Node<T> remove(Node<T> raiz, long elemento) {
		if (raiz.getElemento() == elemento)
			return verificaTipoRemocao(raiz);
		else if (raiz.getElemento() < elemento) {
			if (raiz.getDireita() != null)
				raiz.setDireita(remove(raiz.getDireita(), elemento));
			else
				throw new IllegalArgumentException("Elemento não encontrado");
		} else {
			if (raiz.getEsquerda() != null)
				raiz.setEsquerda(remove(raiz.getEsquerda(), elemento));
			else
				throw new IllegalArgumentException("Elemento não encontrado");
		}
		return raiz;
	}

	protected Node<T> verificaTipoRemocao(Node<T> raiz) {
		int qtd = raiz.getQuantidadeDeFilhos();
		if (qtd == 0)
			return null;
		else if (qtd == 1)
			return remocaoSimplesDireita(raiz);
		else if (qtd == -1)
			return remocaoSimplesEsquerda(raiz);
		else {
			return remocaoComposta(raiz);
		}
	}

	/**
	 * Caso o Node possua dois filhos
	 * 
	 * @param raiz
	 * @return novo
	 */
	private Node<T> remocaoComposta(Node<T> raiz) {
		Node<T> aux;
		aux = maiorEsquerda(raiz.getEsquerda());
		aux.setDireita(raiz.getDireita());
		if (raiz.getEsquerda() != aux)
			aux.setEsquerda(raiz.getEsquerda());
		return aux;
	}

	/**
	 * Remoção simples, retorna o elemento a esquerda
	 * 
	 * @param raiz
	 * @return novo
	 */
	private Node<T> remocaoSimplesEsquerda(Node<T> remove) {
		return remove.getEsquerda();
	}

	/**
	 * Remoção simples, retorna o elemento a direita
	 * 
	 * @param remove
	 * @return novo
	 */
	private Node<T> remocaoSimplesDireita(Node<T> remove) {
		return remove.getDireita();
	}

	/**
	 * Retorna o maior elemento da sub árvore à esquerda
	 * 
	 * @param raiz
	 * @return maiorEsquerda
	 */
	private Node<T> maiorEsquerda(Node<T> raiz) {

		Node<T> remover = raiz;

		if (raiz.getDireita() != null)
			if (raiz.getDireita().getConteudo() != null)
				remover = maiorEsquerda(raiz.getDireita());

		if (raiz.getDireita() == remover) {
			raiz.setDireita(remover.getEsquerda());
		}

		return remover;
	}

	/**
	 * Retorna a raiz da árvore
	 * 
	 * @return raiz
	 */
	@Override
	public Node<T> getRaiz() {
		return raiz;
	}

	/**
	 * Percorre toda a árvore mostrando em preOrdem
	 */
	public void preOrdem() {
		if (raiz == null)
			System.out.println("Árvore vazia");
		else
			preOrdem(raiz);
	}

	/**
	 * Percorre toda a árvore mostrando em emOrdem
	 */
	public void emOrdem() {
		if (raiz == null)
			System.out.println("Árvore vazia");
		else
			emOrdem(raiz);
	}

	/**
	 * Percorre toda a árvore mostrando em posOrdem
	 */
	public void posOrdem() {
		if (raiz == null)
			System.out.println("Árvore vazia");
		else
			posOrdem(raiz);
	}

	/**
	 * Metodo recursivo para percorrer toda a árvore
	 * 
	 * @param raiz
	 */
	private void preOrdem(Node<T> raiz) {
		if (raiz == null || raiz.getConteudo() == null)
			return;
		System.out.println(raiz);
		preOrdem(raiz.getEsquerda());
		preOrdem(raiz.getDireita());

	}

	/**
	 * Metodo recursivo para percorrer toda a árvore
	 * 
	 * @param raiz
	 */
	private void emOrdem(Node<T> raiz) {
		if (raiz == null || raiz.getConteudo() == null)
			return;
		emOrdem(raiz.getEsquerda());
		System.out.println(raiz.getElemento());
		emOrdem(raiz.getDireita());

	}

	/**
	 * Metodo recursivo para percorrer toda a árvore
	 * 
	 * @param raiz
	 */
	private void posOrdem(Node<T> raiz) {
		if (raiz == null || raiz.getConteudo() == null)
			return;
		preOrdem(raiz.getEsquerda());
		preOrdem(raiz.getDireita());
		System.out.println(raiz);

	}

	public List<T> toList() {
		List<T> lista = new ArrayList<T>();
		return toList(lista, this.raiz);
	}

	private List<T> toList(List<T> lista, Node<T> raiz) {
		if (raiz.getEsquerda() != null && raiz.getEsquerda().getConteudo() != null)
			toList(lista, raiz.getEsquerda());

		lista.add(raiz.getConteudo());

		if (raiz.getDireita() != null && raiz.getDireita().getConteudo() != null)
			toList(lista, raiz.getDireita());
		return lista;
	}

}
