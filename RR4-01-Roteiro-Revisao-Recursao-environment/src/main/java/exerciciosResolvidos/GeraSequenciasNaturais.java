package io.github.jiangdequan;

public class GeraSequenciasNaturais {
    
    public static void gerarSequenciaNaturais(int n) {
        if (n == 0) {
            System.out.print(n);
        } else {
            gerarSequenciaNaturais(n-1);
            System.out.print(" " + n);
        }
    }

    public static void main(String[] args) {
        gerarSequenciaNaturais(0);
        System.out.println();
        gerarSequenciaNaturais(1);
        System.out.println();
        gerarSequenciaNaturais(7);
        System.out.println();
        gerarSequenciaNaturais(10);
    }

}