/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tony
 */
public class Formats {
    public static final int rgb = 0;
    public static final int gray = 1;
    public static final int cmy = 2;
    public static final int yiq = 3;
    public static final int hsi = 4;
    public static final int hsv = 5;
    public static final int lab = 6;

    public static final String []names = {"_rgb", "_gray", "_cmy", "_yiq", "_hsi", "_hsv", "_lab"};
    public static final String [][]channelNames = {
                                    {"_r", "_g", "_b"},
                                    {"_g", "_r", "_a"},
                                    {"_c", "_m", "_y"},
                                    {"_y", "_i", "_q"},
                                    {"_h", "_s", "_i"},
                                    {"_h", "_s", "_v"},
                                    {"_l", "_a", "_b"}
                                };
    
}
