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

public class ArvoreBinaria <T extends Elemento> implements Arvore {

	protected Node<T> raiz;

	/**
	 * True para caso vá gravar no arquivo
	 * False para caso não vá gravar no arquivo
	 * @param elemento
	 * @param grava
	 */
	public ArvoreBinaria(T elemento,boolean grava) {
		raiz = new Node<T>(elemento, Cor.PRETO);
		if(grava)
			this.gravaArquivo(raiz);
	}		
	
	public ArvoreBinaria(T elemento) {
		raiz = new Node<T>(elemento, Cor.PRETO);
	}	
	
	
	public ArvoreBinaria() {
		this.raiz = null;
	}

	@Override
	public boolean add(Elemento elemento) {
		Node<T> novo = new Node<T>((T) elemento);
		boolean adicionado = false;
		if (raiz == null) {
			raiz = novo;
			adicionado = true;
		} else {
			adicionado = add(raiz, novo);
		}
//		if (adicionado)
//			gravaArquivo(novo);
		return adicionado;
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

	public void teste() {

		System.out.println(raiz.getConteudo() instanceof Eleitor);
		System.out.println(raiz.getConteudo() instanceof Candidato);
	}

	/**
	 * Método privado sobrecarregado para adicionar elemento na Árvore de forma
	 * recursivo
	 * 
	 * @param raiz
	 * @param novo
	 * @return adiciona
	 */
	protected boolean add(Node<T> raiz, Node<T> novo) {
		Node<T> aux = raiz;
		if (aux.getElemento() < novo.getElemento())
			return addDireita(aux, novo);
		else if (aux.getElemento() > novo.getElemento()) {
			return addEsquerda(aux, novo);
		} else
			return false;
	}

	/**
	 * Adiciona no elemento direito da árvore
	 * 
	 * @param aux
	 * @param novo
	 * @return adiciona
	 */
	protected boolean addDireita(Node<T> aux, Node<T> novo) {
		if (aux.getElemento() == novo.getElemento())
			return false;
		else if (aux.getDireita() != null)
			return add(aux.getDireita(), novo);
		else {
			aux.setDireita(novo);
			return true;
		}
	}

	/**
	 * Adiciona no elemento esquerdo da árvore
	 * 
	 * @param aux
	 * @param novo
	 * @return adiciona
	 */
	private boolean addEsquerda(Node<T> aux, Node<T> novo) {
		if (aux.getElemento() == novo.getElemento())
			return false;
		else if (aux.getEsquerda() != null)
			return add(aux.getEsquerda(), novo);
		else {
			aux.setEsquerda(novo);
			return true;
		}
	}

	@Override
	public boolean remove(Integer elemento) {
		if (this.raiz.getElemento() == elemento) {
			raiz = verificaTipoRemocao(raiz);
			return true;
		} else {
			remove(raiz, elemento);
		}
		return false;
	}

	protected boolean remove(Node<T> raiz, Integer elemento) {
		if (raiz.getElemento() < elemento) {
			Node<T> direita = raiz.getDireita();
			if (direita == null)
				return false;
			else if (direita.getElemento() != elemento)
				return remove(direita, elemento);
			else {
				raiz.setDireita(verificaTipoRemocao(direita));
				return true;
			}
		} else {
			Node<T> esquerda = raiz.getEsquerda();
			if (esquerda == null)
				return false;
			else if (esquerda.getElemento() != elemento)
				return remove(esquerda, elemento);
			else {
				raiz.setEsquerda(verificaTipoRemocao(esquerda));
				return true;
			}

		}
	}

	/**
	 * Metodo para verificar o tipo de remoção e executa-la
	 * 
	 * @param raiz
	 * @return novo
	 */
	private Node<T> verificaTipoRemocao(Node<T> raiz) {
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
		if (raiz == null)
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
		if (raiz == null)
			return;
		preOrdem(raiz.getEsquerda());
		System.out.println(raiz);
		preOrdem(raiz.getDireita());

	}

	/**
	 * Metodo recursivo para percorrer toda a árvore
	 * 
	 * @param raiz
	 */
	private void posOrdem(Node<T> raiz) {
		if (raiz == null)
			return;
		preOrdem(raiz.getEsquerda());
		preOrdem(raiz.getDireita());
		System.out.println(raiz);

	}
	
	public List<T> toList(){
		List<T> lista = new ArrayList<T>();
		return toList(lista,this.raiz);
	}	
	
	private List<T> toList(List<T> lista,Node<T> raiz){
		if(raiz.getEsquerda() != null)
			toList(lista,raiz.getEsquerda());
		
		
		lista.add(raiz.getConteudo());
		
		
		if(raiz.getDireita() != null)
			toList(lista,raiz.getDireita());
		return lista;
	}
	
	
	

}
