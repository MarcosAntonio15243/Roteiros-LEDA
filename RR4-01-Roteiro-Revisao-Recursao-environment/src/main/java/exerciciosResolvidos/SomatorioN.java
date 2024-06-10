package io.github.jiangdequan;

public class SomatorioN {
    
    public static int Somatorio(int n) {
        if (n == 1) {
            return n;
        } else {
            return n + Somatorio(n-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(Somatorio(1));
        System.out.println(Somatorio(3));
        System.out.println(Somatorio(10));
    }

}