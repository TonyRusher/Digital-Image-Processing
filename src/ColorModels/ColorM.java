package ColorModels;

public class ColorM {

    double c0, c1, c2;

    public ColorM(){
        
    }
    public static RGB toRGB(double c0, double c1, double c2) {
       return new RGB(c0,c1,c2);
    }

    public double getC0() {
        return c0;
    }

    public void setC0(double c0) {
        this.c0 = c0;
    }

    public double getC1() {
        return c1;
    }

    public void setC1(double c1) {
        this.c1 = c1;
    }

    public double getC2() {
        return c2;
    }

    public void setC2(double c2) {
        this.c2 = c2;
    }
}
