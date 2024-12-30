/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ColorModels;

/**
 *
 * @author tony
 */
public class LAB extends ColorM {

    int l, a, b;

    public LAB(double r, double g, double b) {
        super();
        double r1 = r / 255.0;
        double g1 = g / 255.0;
        double b1 = b / 255.0;

        double x = 0.4124564 * r1 + 0.3575761 * g1 + 0.1804375 * b1;
        double y = 0.2126729 * r1 + 0.7151522 * g1 + 0.0721750 * b1;
        double z = 0.0193339 * r1 + 0.1191920 * g1 + 0.9503041 * b1;

        double l = 0.3897 * x + 0.6891 * y + 0.0782 * z;
        double m = -0.2298 * x + 1.1834 * y + 0.0464 * z;
        double s = 0.0000 * x + 0.0000 * y + 1.0000 * z;

        double L = Math.log(l);
        double M = Math.log(m);
        double S = Math.log(s);

        double ll = 1.0 / Math.sqrt(3) * (L + M + S);
        double aa = 1.0 / Math.sqrt(6) * (L + M - 2 * S);
        double bb = 1.0 / Math.sqrt(2) * (L - M);

        this.c0 = ll * 255.0;
        this.c1 = aa * 255.0;
        this.c2 = bb * 255.0;
//        System.out.println("" + this.c0 + ", " + this.c1 + ", " + this.c2);
    }

    public static RGB toRGB(double c0, double c1, double c2) {
        double r, g, b;
        double ll = c0 / 255.0;
        double aa = c1 / 255.0;
        double bb = c2 / 255.0;

        double L = 1.0 / Math.sqrt(3) * ll + 1.0 / Math.sqrt(6) * aa + 1.0 / Math.sqrt(2) * bb;
        double M = 1.0 / Math.sqrt(3) * ll + 1.0 / Math.sqrt(6) * aa - 1.0 / Math.sqrt(2) * bb;
        double S = 1.0 / Math.sqrt(3) * ll - 2.0 / Math.sqrt(6) * aa;

        double l = Math.exp(L);
        double m = Math.exp(M);
        double s = Math.exp(S);

        double x = 1.924226435787072 * l - 1.004792312595365 * m + 0.037651404030618 * s;
        double y = 0.350316762094548 * l + 0.726481193931655 * m - 0.065384422026223 * s;
        double z = 0.016793235107528 * l - 0.808675766563924 * m + 1.192405644732491 * s;

        r = 3.2404542 * x - 1.5371385 * y - 0.4985314 * z;
        g = -0.9692660 * x + 1.8760108 * y + 0.0415560 * z;
        b = 0.0556434 * x - 0.2040259 * y + 1.0572252 * z;

        r = Math.min(255, Math.max(0, r * 255));
        g = Math.min(255, Math.max(0, g * 255));
        b = Math.min(255, Math.max(0, b * 255));

        return new RGB(r, g, b);

    }

}
