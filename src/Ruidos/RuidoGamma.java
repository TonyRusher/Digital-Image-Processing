/**
 * Paquete al que pertenece la clase.
 */
package Ruidos;

/**
 * Esta clase genera ruido Gamma y lo adiciona a la imagen con el fin de
 * degradarla.
 *
 * @author Saul De La O Torres
 * @version 1.0 14 de Julio de 2003
 */
public class RuidoGamma extends Ruido {

    /**
     * Variable de variacion del ruido alfa.
     */
    int alfa;

    /**
     * El constructor recibe la altura y el ancho de la imagen.
     *
     * @param alto la altura de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public RuidoGamma(int alto, int ancho) {
        super(alto, ancho);
        alfa = 1;
    }

    /**
     * Adiciona ruido gamma a la imagen original, recibe el buffer de la imagen
     * en dos dimensiones, el alfa y la varianza del ruido, regresa una copia de
     * la imagen degradada (tambien en dos dimensiones).
     *
     * @param imagen la imagen que se degrada
     * @param alfa para el ruido
     * @param var la varianza del ruido
     *
     * @return devuelve la imagen degradada en dos dimensiones
     */
    public int[][] adicionarRuidoGammaAImagen(int[][] imagen, int alfa,
            double var) {
        this.alfa = alfa;
        this.setVarianza(var);
        int NOISE1;
        double IMAGE1;
        int col;
        double Rx;
        double Ry;
        double dosPI = 2.0 * Math.PI;
        int [][] imagenConRuido2D = this.getImagenConRuido2D();
        int alto = this.getAlto();
        int ancho = this.getAncho();

        double A = Math.sqrt((double) var / (double) alfa) / 2;
        for (int y = 0; y < alto; y++) {
            if (imagenConRuido2D[y] != null) {
                imagenConRuido2D[y] = new int[ancho];
            }
            for (int x = 0; x < ancho; x++) {
                col = imagen[y][x];
                IMAGE1 = 0.0;
                for (int i = 0; i <= alfa; i++) {
                    double dRuido = 
                            Math.sqrt(-2 * A * Math.log(1.0 - Math.random()));
                    double theta = Math.random() * dosPI;
                    Rx = dRuido * Math.cos(theta);
                    Ry = dRuido * Math.sin(theta);
                    dRuido = Rx * Rx + Ry * Ry;
                    IMAGE1 = IMAGE1 + dRuido;
                }
                NOISE1 = (int) (IMAGE1 + 0.5);
                col += NOISE1;
                if (col > 255) {
                    col = 255;
                }
                imagenConRuido2D[y][x] = col;
            }
        }
        return imagenConRuido2D;
    }

    /**
     * Adiciona ruido gamma a la imagen original, recibe el buffer de la imagen
     * en dos dimensiones, el alfa y la varianza del ruido, regresa una copia de
     * la imagen degradada (en una dimension).
     *
     * @param imagen la imagen que se degrada
     * @param alfa para el ruido
     * @param var la varianza del ruido
     *
     * @return devuelve la imagen degradada en una dimension
     */
    public int[] adicionarRuidoGammaAImagen2DA1D(int[][] imagen, int alfa,
            double var) {
        this.alfa = alfa;
        this.setVarianza(var);
        int NOISE1;
        double IMAGE1;
        int col;
        double Rx;
        double Ry;
        int [] imagenConRuido1D = this.getImagenConRuido1D();
        int alto = this.getAlto();
        int ancho = this.getAncho();

        double A = Math.sqrt((double) var / (double) alfa) / 2;
        int index = 0;
        for (int y = alto - 1; y >= 0; y--) {
            for (int x = 0; x < ancho; x++) {
                col = imagen[y][x];
                IMAGE1 = 0.0;
                for (int i = 0; i <= alfa; i++) {
                    double dRuido = 
                            Math.sqrt(-2 * A * Math.log(1.0 - Math.random()));
                    double theta = Math.random() * getDosPI();
                    Rx = dRuido * Math.cos(theta);
                    Ry = dRuido * Math.sin(theta);
                    dRuido = Rx * Rx + Ry * Ry;
                    IMAGE1 = IMAGE1 + dRuido;
                }
                NOISE1 = (int) (IMAGE1 + 0.5);
                col += NOISE1;
                if (col > 255) {
                    col = 255;
                }
                imagenConRuido1D[index] = col;
                index++;
            }
        }
        return imagenConRuido1D;
    }
}
