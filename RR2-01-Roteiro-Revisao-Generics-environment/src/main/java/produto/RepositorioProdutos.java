package produto;

public interface RepositorioProdutos<T extends Produto> {
    
    public boolean existe(int codigo);

    public void inserir(T obj);

    public void atualizar(T obj);

    public void remover(int codigo);

    public Produto procurar(int codigo);

}