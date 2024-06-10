package recursao;

public class TestarMetodosRecursivos {
	public static void main(String[] args) {

		MetodosRecursivos mr = new MetodosRecursivos();

		// Calcular soma array
		System.out.println("Calcular soma array:");
		System.out.println(mr.calcularSomaArray(new int[]{2, 5, 8, 3, 12, 34})); // Esperado: 64
		System.out.println();

		// Testando o calcularFatorial()
		System.out.println("Calculando fatorial:");
		mr.calcularFatorial(12); // Esperado: 479001600
		System.out.println();

		// Testando o calcularFibonacci()
		System.out.println("Calculando n-ésimo termo da sequencia de Fibonacci:");
		System.out.println(mr.calcularFibonacci(7)); // Esperado: 13
		System.out.println();

		// Testando o contarNaoNulos()
		System.out.println("Contando elementos não nulos:");
		System.out.println(mr.countNotNull(new Object[]{1, 2, null, null, 5})); // Esperado: 3
		System.out.println();

		// Testando o potenciaDe2()
		System.out.println("Potencias de 2:");
		System.out.println(mr.potenciaDe2(0)); // Esperado: 1
		System.out.println(mr.potenciaDe2(1)); // Esperado: 2
		System.out.println(mr.potenciaDe2(4)); // Esperado: 16
		System.out.println();

		// Testando o progressaoAritmetica()
		System.out.println("Progressão Aritmética:");
		System.out.println(mr.progressaoAritmetica(1, 3, 1)); // Esperado: 1
		System.out.println(mr.progressaoAritmetica(1, 3, 4)); // Esperado: 10
		System.out.println();

		// Testando o progressaoGeometrica()
		System.out.println("Progressão Geométrica:");
		System.out.println(mr.progressaoGeometrica(2, 3, 1)); // Esperado: 2
		System.out.println(mr.progressaoGeometrica(2, 3, 4)); // Esperado: 54
		System.out.println();

	}
}
