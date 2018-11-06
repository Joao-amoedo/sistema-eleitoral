package pi.node;

import pi.model.Candidato;
import pi.model.Eleitor;
import pi.model.Elemento;

/**
 * Classe Node da árvore binária
 * 
 * @author João P. Amoêdo
 *
 */
@SuppressWarnings("hiding")
public class  Node  <t extends Elemento>{

	private Node<t> direita;

	private Node<t> esquerda;

	private Node<t> pai = null;
	
	private Cor cor;

	private t conteudo;

	private int altura;

	private int fat;

	/**
	 * Construtor do Node, recebe um inteiro
	 * 
	 * @param elemento
	 */
	public Node(t conteudo) {
		this.conteudo = conteudo;
		this.direita = null;
		this.esquerda = null;
		this.cor = Cor.VERMELHO;
	}

	public Node(t conteudo, Cor cor) {
		this.conteudo = conteudo;
		this.direita = null;
		this.esquerda = null;
		this.cor = cor;
	}

	/**
	 * Retorna o Node a direita
	 * 
	 * @return direita
	 */

	public Node<t> getDireita() {
		return this.direita;
	}

	/**
	 * Retorna o Node a esquerda
	 * 
	 * @return esquerda
	 */
	public Node<t> getEsquerda() {
		return this.esquerda;
	}

	/**
	 * Retorna o elemento do node
	 * 
	 * @return elemento
	 */
	@SuppressWarnings("unchecked")
	public long getElemento() {
		return this.conteudo.getElemento();
	}

	/**
	 * Recebe como parâmetro um Node e atribui a sua direita
	 * 
	 * @param direita
	 */
	public void setDireita(Node<t> direita) {
		this.direita = direita;
	}

	/**
	 * Define o fator de balanceament do Node
	 */
	public void setFat() {
		int qtd = this.getQuantidadeDeFilhos();
		if (qtd == 0) {
			fat = 0;
		} else if (qtd == 1) {
			fat = direita.getAltura();
		} else if (qtd == -1) {
			fat = esquerda.getAltura();
		} else {
			fat = direita.getAltura() - esquerda.getAltura();
		}
	}

	/**
	 * Define a altura do Node
	 */

	public void setAltura() {
		int qtd = this.getQuantidadeDeFilhos();
		
		if (qtd == 0)
			altura = 1;
		else if (qtd == 1)
			altura = direita.getAltura() + 1;
		else if (qtd == -1)
			altura = esquerda.getAltura() + 1;
		else {
			if (direita.getAltura() > esquerda.getAltura())
				altura = direita.getAltura() + 1;
			else
				altura = esquerda.getAltura() + 1;
		}

	}
	
	public t getConteudo() {
		return (t) this.conteudo;
	}
	
	public int getAltura() {
		return this.altura;
	}

	/**
	 * Recebe como parâmetro um Node e atribui a sua esquerda
	 * 
	 * @param esquerda
	 */
	public void setEsquerda(Node<t> esquerda) {
		this.esquerda = esquerda;
	}

	/**
	 * Retorna 2 caso os dois filhos sejam diferentes de nulo Retorna 1 caso apenas
	 * o filho da direita seja diferente de nulo Retorna -1 caso apenas o filho da
	 * esquerda seja diferente de nulo Retorna 0 caso os dois filhos sejam nulos
	 * 
	 * @return qtdFilhos
	 */
	public int getQuantidadeDeFilhos() {
		if(direita == null && direita == null) {
			return 0;
		}
		else if(direita != null && esquerda == null) {
			return 1;
		}else if(direita == null && esquerda != null) {
			return -1;
		}else {
			if(direita.getConteudo() == null && esquerda.getConteudo() == null)
				return 0;
			else if(esquerda.getConteudo() == null && direita.getConteudo() != null)
				return 1;
			else if(direita.getConteudo() == null && esquerda.getConteudo() != null)
				return -1;
			else
				return 2;
		}
	}
			

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	@Override
	public String toString() {
		return this.conteudo + "";
	}

	public void setPai(Node<t> pai) {
		this.pai = pai;
		
	}

	public Node<t> getPai() {
		
		return pai;
	}
	
	

}
