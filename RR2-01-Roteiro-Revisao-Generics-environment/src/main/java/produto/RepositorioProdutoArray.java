package produto;

public class RepositorioProdutoArray<T extends Produto> implements RepositorioProdutos<T> {
    
    /**
	 * A estrutura onde os produtos sao mantidos. Voce nao precisa se preocupar
	 * por enquanto com o uso de generics em ArrayList.
	 */
	private T[] produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	@SuppressWarnings("unchecked")
	public RepositorioProdutoArray(int size) {
		super();
		this.produtos = (T[]) new Object[size];
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
        for (int i = 0; i < this.produtos.length; i++) {
            if (produto.equals(this.produtos[i])) {
                return i;
            }
        }
        return -1;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	@Override
	public boolean existe(int codigo) {
		return procurarIndice(codigo) < 0;
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	@Override
	public void inserir(T produto) {
		this.produtos[++this.index] = produto;
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	@Override
	public void atualizar(T produto) {
		boolean temProduto = false;
        int i = 0;
        while (i < this.produtos.length && !temProduto) {
            if (produto.equals(this.produtos[i])) {
                this.produtos[i] = produto;
                temProduto = true;
            }
            i += 1;
        }
        if (!temProduto) {
            throw new RuntimeException("Produto Inexistente!");
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
		boolean temProduto = false;
        int i = 0;
        while (i < this.produtos.length && !temProduto) {
            if (produto.equals(this.produtos[i])) {
                this.produtos[i] = this.produtos[index--];
                temProduto = true;
            }
            i += 1;
        }
        if (!temProduto) {
            throw new RuntimeException("Produto Inexistente!");
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
	public T procurar(int codigo) {
		T produto = null;
		boolean temProduto = false;
        int i = 0;
        while (i < this.produtos.length && !temProduto) {
            if (produto.equals(this.produtos[i])) {
                produto = this.produtos[i];
                temProduto = true;
            }
            i += 1;
        }
        if (!temProduto) {
            throw new RuntimeException("Produto Inexistente!");
        }
        return produto;
	}

}