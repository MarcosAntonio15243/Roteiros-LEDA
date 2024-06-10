package vetor;

import java.util.Arrays;
import java.util.Comparator;

public class TestarVetor {

	public static void main(String[] args) {
		
		// Criando um vetor de tamanho 3
		Vetor<String> vetor = new Vetor<String>(3);
		
		// Checando se o vetor está vazio
		System.out.println(vetor.isVazio());
		
		// Adicionando elementos ao vetor
		vetor.inserir("Elemento 1");
		vetor.inserir("El 2");
		vetor.inserir("Elemento 3");
		
		// Checando se o vetor está cheio
		System.out.println("Esta cheio: " + vetor.isCheio());
		
		// Adicionando um elemento ultrapassando o limite (não adiciona)
		vetor.inserir("Elemento 4");
		// Provando que o elemento não foi adicionado buscando ele no array (retorna null)
		System.out.println("Procurar 'Elemento 4': " + vetor.procurar("Elemento 4"));
		
		// Removendo o terceiro elemento do array
		System.out.println("Procurar 'Elemento 3': " + vetor.procurar("Elemento 3"));
		vetor.remover("Elemento 3");
		System.out.println("Procurar 'Elemento 3': " + vetor.procurar("Elemento 3"));
		
		// Criando e adicionando os comparadores máximo e mínimo
		Comparator comparadorMaximo = new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				if (arg0.length() < arg1.length()) {
					return -1;
				} else if (arg0.length() > arg1.length()) {
					return 1;
				} else {
					return 0;
				}
			}
		};
		vetor.setComparadorMaximo(comparadorMaximo);
		Comparator comparadorMinimo = new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				if (arg0.length() > arg1.length()) {
					return -1;
				} else if (arg0.length() < arg1.length()) {
					return 1;
				} else {
					return 0;
				}
			}
		};
		vetor.setComparadorMinimo(comparadorMinimo);
		
		// Buscando o elemento máximo do vetor
		System.out.println("Máximo: " + vetor.maximo());
		// Buscando o elemento mínimo do vetor
		System.out.println("Mínimo: " + vetor.minimo());
		
		
		// Testando o vetor de Alunos
		Vetor<Aluno> vetorAlunos = new Vetor<Aluno>(3);
		
		// Adicionando alunos ao vetor
		vetorAlunos.inserir(new Aluno("Aluno 1", 60));
		vetorAlunos.inserir(new Aluno("Aluno 2", 30));
		vetorAlunos.inserir(new Aluno("Aluno 3", 100));

		// Adicionando os comparadores externos
		vetorAlunos.setComparadorMaximo(new ElementoMaximo());
		vetorAlunos.setComparadorMinimo(new ElementoMinimo());
		
		// Pegando o aluno com a menor média
		System.out.println("Aluno máximo (maior média): " + vetorAlunos.maximo().getMedia());
		System.out.println("Aluno mínimo (menor média): " + vetorAlunos.minimo().getMedia());
		
	}
}
