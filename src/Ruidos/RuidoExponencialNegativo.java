/**
 * Paquete al que pertenece la clase.
 */
package Ruidos;

/**
 * Esta clase genera ruido Exponencial Negativo y lo adiciona a la imagen con el
 * fin de degradarla.
 *
 * @author Saul De La O Torres
 * @version 1.0 14 de Julio de 2003
 */
public class RuidoExponencialNegativo extends Ruido {

    /**
     * El Constructor recibe la altura y el ancho de la imagen a degradar con
     * ruido.
     *
     * @param alto la altura de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public RuidoExponencialNegativo(int alto, int ancho) {
        super(alto, ancho);
    }

    /**
     * Adiciona ruido Exponencial Negativo a la imagen original, recibe el
     * buffer de la imagen en dos dimensiones, la varianza y la media aritmetica
     * del ruido, regresa una copia de la imagen degradada (tambien en dos
     * dimensiones).
     *
     * @param imagen la imagen que se degrada
     * @param var la varianza del ruido
     * @param med la media del ruido
     *
     * @return devuelve la imagen degradada en dos dimensiones
     */
    public int[][] adicionarRuidoExponencialNegativoAImagen(int[][] imagen,
            double var, double med) {
        double Rx;
        double Ry;
        int col;
        this.setVarianza(var);
        this.setMedia(med);
        double A = Math.sqrt(var) / 2.0;

        int [][] imagenConRuido2D = this.getImagenConRuido2D();
        int alto = this.getAlto();
        int ancho = this.getAncho();
        
        for (int y = 0; y < alto; y++) {
            if (imagenConRuido2D[y] != null) {
                imagenConRuido2D[y] = new int[alto];
            }
            for (int x = 0; x < ancho; x++) {
                col = imagen[y][x];
                double dRuido = 
                        Math.sqrt(-2 * A * Math.log(1.0 - Math.random()));
                double theta = Math.random() * getDosPI();
                Rx = dRuido * Math.cos(theta);
                Ry = dRuido + getMedia();
                dRuido = Rx * Rx + Ry * Ry;
                col += (dRuido + 0.5);
                if (col > 255) {
                    col = 255;
                }
                imagenConRuido2D[y][x] = col;
            }
        }
        return imagenConRuido2D;
    }

    /**
     * Adiciona ruido ruido Exponencial Negativo a la imagen original, recibe el
     * buffer de la imagen en dos dimensiones, la varianza y la media aritmetica
     * del ruido, regresa una copia de la imagen degradada (en una dimension).
     *
     * @param imagen la imagen que se degrada
     * @param var la varianza del ruido
     * @param med la media del ruido
     *
     * @return devuelve la imagen degradada en una dimension
     */
    public int[] adicionarRuidoExponencialNegativoAImagen2DA1D(int[][] imagen,
            double var, double med) {
        double Rx;
        double Ry;
        int col;
        int [] imagenConRuido1D = this.getImagenConRuido1D();
        int alto = this.getAlto();
        int ancho = this.getAncho();
        this.setVarianza(var);
        this.setMedia(med);
        double A = Math.sqrt(var) / 2.0;
        int index = 0;
        for (int y = alto - 1; y >= 0; y--) {
            for (int x = 0; x < ancho; x++) {
                col = imagen[y][x];
                double dRuido = 
                        Math.sqrt(-2 * A * Math.log(1.0 - Math.random()));
                double theta = Math.random() * getDosPI();
                Rx = dRuido * Math.cos(theta);
                Ry = dRuido + getMedia();
                dRuido = Rx * Rx + Ry * Ry;
                col += (dRuido + 0.5);
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
