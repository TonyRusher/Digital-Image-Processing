/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OperateImages;

/**
 *
 * @author tony
 */
public class LutTecnique {
    private LookUpTable lut;

    public LutTecnique() {
        lut = new LookUpTable();
    }

    public int[][] aplicarTecnica(int selector, int[][] imagen, int invertido) {
        int alto = imagen.length;
        int ancho = imagen[0].length;
        int[][] fprima = new int[alto][ancho];
        int[] transformacion;
        switch (selector) {
            case 1:
                lut.transformarIdentidad();
                break;
            case 2:
                lut.transformarNegativo();
                break;
            case 3:
                lut.transformarConstraste(0.5, 10);
                break;
            case 4:
                lut.transformarCorreccionGamma(0.7);
                break;
            case 5:
                double media = 0.0;
                for (int yy = 0; yy < alto; yy++) {
                    for (int xx = 0; xx < ancho; xx++) {
                        media += imagen[yy][xx];
                    }
                }
                media /= (alto * ancho);
                lut.transformarConstraste(0.4, media, true);
                break;
            case 6:
                lut.transformarEscalar(45);
                break;
            case 7:
                lut.transformarParabola();
                break;
            case 8:
                lut.transformarUmbral1();
        }
        transformacion = lut.getLutInt();

        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                fprima[y][x] = transformacion[imagen[y][x]];
            }
        }
        return fprima;
    }
}
