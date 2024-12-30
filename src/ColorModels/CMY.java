/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ColorModels;

/**
 *
 * @author tony
 */
public class CMY extends ColorM {

   
    public CMY(double r, double g, double b) {
        super();
        c0 = 255 - r;
        c1 = 255 - g;
        c2 = 255 - b;

    }

  
    public static RGB toRGB(double c0, double c1, double c2) {
        double r = 255 - c0;
        double g = 255 - c1;
        double b = 255 - c2;
        return new RGB(r, g, b);
    }

  
}
