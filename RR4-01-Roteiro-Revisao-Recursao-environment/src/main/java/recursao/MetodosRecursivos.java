package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		int result = 0;
		result = somarArrayRecursivo(array, array.length - 1);
		return result;
	}

	private int somarArrayRecursivo(int[] array, int index) {
		if (index == 0) {
			return array[index];
		} else {
			return array[index] + somarArrayRecursivo(array, index-1);
		}
	}

	public long calcularFatorial(int n) {
		long result = 1;
		if (n == 0 || n == 1) {
			System.out.println(n + "! = " + result);
			return result;
		} else {
			result = n*calcularFatorial(n-1);
			System.out.println(n + "! = " + result);
			return result;
		}
	}

	public int calcularFibonacci(int n) {
		int result = 1;
		if (n == 1 || n == 2) {
			return result;
		} else {
			return calcularFibonacci(n-1) + calcularFibonacci(n-2);
		}
	}

	public int countNotNull(Object[] array) {
		int result = 0;
		result = contarNaoNulosRecursivo(array, array.length-1);
		return result;
	}

	private int contarNaoNulosRecursivo(Object[] array, int index) {
		if (index == 0) {
			return array[index] != null ? 1 : 0;
		} else {
			return contarNaoNulosRecursivo(array, index-1) + (array[index] != null ? 1 : 0);
		}
	}

	public long potenciaDe2(int expoente) {
		int result = 1;
		if (expoente == 0) {
			return result;
		} else {
			return 2*potenciaDe2(expoente-1);
		}
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = 0;
		if (n == 1) {
			return result + termoInicial;
		} else {
			return progressaoAritmetica(termoInicial, razao, n-1) + razao;
		}
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = 1;
		if (n == 1) {
			return result * termoInicial;
		} else {
			return progressaoGeometrica(termoInicial, razao, n-1) * razao;
		}
	}
	
	
}
