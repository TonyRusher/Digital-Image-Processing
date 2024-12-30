/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ColorModels;

/**
 *
 * @author tony
 */
public class HSI extends ColorM {

    public HSI(double r, double g, double b) {
        super();
        double r1 = r / 255.0;
        double g1 = g / 255.0;
        double b1 = b / 255.0;

        double tetha = Math.acos(0.5 * ((r1 - g1) + (r1 - b1)) / Math.sqrt((r1 - g1) * (r1 - g1) + (r1 - b1) * (g1 - b1) + 0.0000001));
        double H = b1 <= g1 ? tetha : 2 * Math.PI - tetha;

        double S = 1 - 3 * Math.min(r1, Math.min(g1, b1)) / (r1 + g1 + b1 + 0.0000001);
        double I = (r1 + g1 + b1) / 3;
        // System.out.println("" + H + ", " + S + ", " + I);
		
		this.c0 = H * 255.0 / (2 * Math.PI);
		this.c1 = S * 255.0;
		this.c2 = I * 255.0;
        
    }

    public static RGB toRGB(double c0, double c1, double c2) {
        double r = 0, g = 0, b = 0;
        double H = c0 * 2 * Math.PI / 255.0;
        double S = c1 / 255.0;
        double I = c2 / 255.0;
        if (H >= 0 && H < 2 * Math.PI / 3) {
            b = I * (1 - S);
            r = I * (1 + S * Math.cos(H) / Math.cos(Math.PI / 3 - H));
            g = 3 * I - (r + b);
        } else if (H >= 2 * Math.PI / 3 && H < 4 * Math.PI / 3) {
            H = H - 2 * Math.PI / 3;
            r = I * (1 - S);
            g = I * (1 + S * Math.cos(H) / Math.cos(Math.PI / 3 - H));
            b = 3 * I - (r + g);
        } else {
            H = H - 4 * Math.PI / 3;
            g = I * (1 - S);
            b = I * (1 + S * Math.cos(H) / Math.cos(Math.PI / 3 - H));
            r = 3 * I - (g + b);
        }
        r = Math.min(255, Math.max(0, r * 255));
        g = Math.min(255, Math.max(0, g * 255));
        b = Math.min(255, Math.max(0, b * 255));

        return new RGB(r, g, b);
    }
}
