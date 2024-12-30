/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ColorModels;

/**
 *
 * @author tony
 */
public class YIQ extends ColorM {

    public YIQ(double r, double g, double b) {
        super();
        double r1 = r ;
        double g1 = g ;
        double b1 = b ;

        c0 = 0.299 * r1 + 0.587 * g1 + 0.114 * b1;
        c1 = 0.596 * r1 - 0.275 * g1 - 0.321 * b1;
        c2 = 0.212 * r1 - 0.528 * g1 + 0.311 * b1;

    }

    /**
     *
     * @param c0
     * @param c1
     * @param c2
     * @return
     */
    public static RGB toRGB(double c0, double c1, double c2) {
        int r = (int) (c0 + 0.956 * c1 + 0.621 * c2);
        int g = (int) (c0 - 0.272 * c1 - 0.647 * c2);
        int b = (int) (c0 - 1.106 * c1 + 1.703 * c2);
        return new RGB(r, g, b);
    }

}
