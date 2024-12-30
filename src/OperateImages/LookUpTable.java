/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OperateImages;

/**
 *
 * @author tony
 */
public class LookUpTable {
    private final int L = 256;
    private int[] lutInt;
    private double[] lutDouble;
    

    public LookUpTable() {
        lutInt = new int[L];
        lutDouble = new double[L];
    }

    public void transformarIdentidad() {
        for (int n = 0; n < L; n++) {
            lutInt[n] = n;
        }
    }

    public void transformarNegativo() {
        for (int n = 0; n < L; n++) {
            lutInt[n] = (L - 1) - n;
        }
    }

    public void transformarCorreccionGamma(double gamma) {
        for (int n = 0; n < L; n++) {
            lutInt[n] = (int) Math.pow(n, 1.0 / gamma);
        }
    }

    public void transformarConstraste(double m, int b) {
        for (int n = 0; n < L; n++) {
            lutInt[n] = (int) (m * n) + b;
        }
    }

    public void transformarParabola() {
        int max = L - 1;
        for (int n = 0; n < L; n++) {
            lutInt[n] = (int) (max * Math.pow(n, 2.0));
        }
    }

    public void transformarEscalar(int K) {
        for (int n = 0; n < L; n++) {
            lutInt[n] = n + K;
        }
    }

    /**
     *
     * @param contraste esta entre 0 y 1
     * @param media debe calcularse antes
     * @param incremento true incrementamos contraste, de lo contrario se
     * decrementa
     */
    public void transformarConstraste(double contraste, double media, boolean incremento) {

        for (int n = 0; n < L; n++) {
            lutInt[n] = (int) (contraste * (n + media));
            if (incremento) {
                lutInt[n] += media;
            } else {
                lutInt[n] -= media;
            }
        }
    }

    public void transformarUmbral1() {
        for (int n = 0; n < L; n++) {
            if (n < 128) {
                lutInt[n] = 0;
            } else {
                lutInt[n] = 255;
            }
        }
    }

    public void transformarUmbralN(int n) {
        int colores = 256/(n);
        int paso = 256 /(n+1) ;
        int current = 0;
        for(int i = 0; i < n+1 && i < L; i++){
            for(int j = i * paso; j < (i+1) * paso && j < L; j++){
                lutInt[j] = current;
            }
            current += colores;
        }
        for(int i = 0; i < L;i++){
            System.out.print("" + lutInt[i] + ", ");
        }
        System.out.println("");
    }

    public int[] getLutInt() {
        for(int i = 0; i < 256; i++){
            if(lutInt[i] > 255) lutInt[i] = 255;
            if(lutInt[i] < 0) lutInt[i] = 0;
        }
        return lutInt;
    }

}
