package produto;

import java.util.ArrayList;

/**
 * Classe que representa um repositório de produtos usando ArrayList como
 * estrutura sobrejacente. Alguns métodos (atualizar, remover e procurar) ou
 * executam com sucesso ou retornam um erro. Para o caso desde exercício, o erro
 * será representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 *
 * @author Adalberto
 */
public class RepositorioProdutoArrayList implements RepositorioProdutos<Produto> {

	/**
	 * A estrutura onde os produtos sao mantidos. Voce nao precisa se preocupar
	 * por enquanto com o uso de generics em ArrayList.
	 */
	private ArrayList<Produto> produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoArrayList(int size) {
		super();
		this.produtos = new ArrayList<Produto>();
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		Produto produto = new Produto(codigo, null, 0, null);
		return this.produtos.indexOf(produto);
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	@Override
	public boolean existe(int codigo) {
		return this.produtos.contains(new Produto(codigo, null, 0, null));
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	@Override
	public void inserir(Produto produto) {
		this.produtos.add(produto);
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	@Override
	public void atualizar(Produto produto) {
		if (!this.produtos.contains(produto)) {
			throw new RuntimeException("Produto Inexistente!");
		} else {
			this.produtos.remove(produto);
			this.produtos.add(produto);
		}
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	@Override
	public void remover(int codigo) {
		Produto produto = new Produto(codigo, null, 0, null);
		if (!this.produtos.contains(produto)) {
			throw new RuntimeException("Produto Inexistente!");
		} else {
			this.produtos.remove(produto);
		}
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	@Override
	public Produto procurar(int codigo) {
		int index = this.procurarIndice(codigo);
		Produto produto = new Produto(codigo, null, 0, null);
		return produto = index < 0 ? null : this.produtos.get(index);
	}

}
