package pi.node;

/**
 * Classe Node da árvore binária
 * 
 * @author João P. Amoêdo
 *
 */
public class Node {

	private Node direita;

	private Node esquerda;

	private Node pai = null;
	
	private Cor cor;

	private Integer elemento;

	private int altura;

	private int fat;

	/**
	 * Construtor do Node, recebe um inteiro
	 * 
	 * @param elemento
	 */
	public Node(int elemento) {
		this.elemento = elemento;
		this.direita = null;
		this.esquerda = null;
		this.cor = Cor.VERMELHO;
	}

	public Node(Integer elemento, Cor cor) {
		this.elemento = elemento;
		this.direita = null;
		this.esquerda = null;
		this.cor = cor;
	}

	/**
	 * Retorna o Node a direita
	 * 
	 * @return direita
	 */

	public Node getDireita() {
		return this.direita;
	}

	/**
	 * Retorna o Node a esquerda
	 * 
	 * @return esquerda
	 */
	public Node getEsquerda() {
		return this.esquerda;
	}

	/**
	 * Retorna o elemento do node
	 * 
	 * @return elemento
	 */
	public int getElemento() {
		return this.elemento;
	}

	/**
	 * Recebe como parâmetro um Node e atribui a sua direita
	 * 
	 * @param direita
	 */
	public void setDireita(Node direita) {
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

	public int getAltura() {
		return this.altura;
	}

	/**
	 * Recebe como parâmetro um Node e atribui a sua esquerda
	 * 
	 * @param esquerda
	 */
	public void setEsquerda(Node esquerda) {
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
		if (direita != null && esquerda != null)
			return 2;
		else if (direita != null && esquerda == null)
			return 1;
		else if (direita == null && esquerda != null)
			return -1;
		else
			return 0;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	@Override
	public String toString() {
		return this.elemento + "";
	}

}
