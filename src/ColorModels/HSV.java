/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ColorModels;

/**
 *
 * @author tony
 */
public class HSV extends ColorM {

 

    public HSV(double r, double g, double b) {
        double r1 = r / 255.0;
        double g1 = g / 255.0;
        double b1 = b / 255.0;
        double min = Math.min(r1, Math.min(g1, b1));
        double max = Math.max(r1, Math.max(g1, b1));
        
		double v = max ;
		double s;
		if (max == 0) {
			s = 0;
		} else {
			s = (max - min) / max;
		}
		double h;
		if(g >= b){
			h = Math.acos((r1 - 0.5 * g1 - 0.5 * b1) / Math.sqrt((r1 - g1) * (r1 - g1) + (r1 - b1) * (g1 - b1) + 0.0000001));
		}else if(b > g){
			h = 2 * Math.PI - Math.acos((r1 - 0.5 * g1 - 0.5 * b1) / Math.sqrt((r1 - g1) * (r1 - g1) + (r1 - b1) * (g1 - b1) + 0.0000001));
		}else{
			h = 0;
		}
		
		// h = Math.toDegrees(h);
		// if (s == 0) {
		// 	h = 0;
		// }
		// if (h < 0) {
		// 	h += 360;
		// }
		
		this.c0 = h * 255.0 / (2 * Math.PI);
		this.c1 = s * 255.0;
		this.c2 = v * 255.0;
		
		
		
    }


     public static RGB toRGB(double c0, double c1, double c2) {
        double r, g, b;
		double h = c0 * 360.0 / 255.0;
		double s = c1 / 255.0;
		double v = c2 / 255.0;
		
        double M = v*255.0;
		double m = M*(1-s);
		double z = (M-m)*(1-Math.abs((h/60)%2-1));
		
		if(h >= 0 && h < 60){
			r = M;
			g = z+m;
			b = m;
		}else if(h >= 60 && h < 120){
			r = z+m;
			g = M;
			b = m;
		}else if(h >= 120 && h < 180){
			r = m;
			g = M;
			b = z+m;
		}else if(h >= 180 && h < 240){
			r = m;
			g = z+m;
			b = M;
		}else if(h >= 240 && h < 300){
			r = z+m;
			g = m;
			b = M;
		}else{
			r = M;
			g = m;
			b = z+m;
		}
		r = Math.min(255, Math.max(0, r));
		g = Math.min(255, Math.max(0, g));
		b = Math.min(255, Math.max(0, b));
		return new RGB(r, g, b);
    }
}
