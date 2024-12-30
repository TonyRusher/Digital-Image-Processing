/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ColorModels;

import Model.Formats;

/**
 *
 * @author tony
 */
public class RGB extends ColorM {

    public RGB(double r, double g, double b) {
        c0 = r;
        c1 = g;
        c2 = b;
    }

    public ColorM rgb2format(int format) {
        return switch (format) {
            case Formats.cmy -> new CMY(c0, c1, c2);
            case Formats.yiq -> new YIQ(c0, c1, c2);
            case Formats.hsi -> new HSI(c0, c1, c2);
            case Formats.hsv -> new HSV(c0, c1, c2);
            case Formats.lab -> new LAB(c0, c1, c2);
            default -> null;
        };
    }


}
