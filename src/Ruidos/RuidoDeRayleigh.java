/**
 * Paquete al que pertenece la clase.
 */
package Ruidos;

/**
 * Esta clase genera ruido de Rayleigh y lo adiciona a la imagen con el fin de
 * degradarla.
 *
 * @author Saul De La O Torres
 * @version 1.0 14 de Julio de 2003
 */
public class RuidoDeRayleigh extends Ruido {

    /**
     * El Constructor recibe la altura y el ancho de la imagen a degradar con
     * ruido.
     *
     * @param alto la altura de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public RuidoDeRayleigh(int alto, int ancho) {
        super(alto, ancho);
    }

    /**
     * Adiciona ruido De Rayleigh a la imagen original, recibe el buffer de la
     * imagen en dos dimensiones y la varianza del ruido, regresa una copia de
     * la imagen degradada (tambien en dos dimensiones).
     *
     * @param imagen la imagen que se degrada
     * @param var la varianza del ruido
     *
     * @return devuelve la imagen degradada en dos dimensiones
     */
    public int[][] adicionarRuidoDeRayleigh(int[][] imagen, double var) {
        int col;
        this.setVarianza(var);
        double A = 2.3299 * var;
        int [][] imagenConRuido2D = this.getImagenConRuido2D();
        int alto = this.getAlto();
        int ancho = this.getAncho();

        for (int y = 0; y < alto; y++) {
            if (imagenConRuido2D[y] != null) {
                imagenConRuido2D[y] = new int[ancho];
            }
            for (int x = 0; x < ancho; x++) {
                col = imagen[y][x];
                double dRuido = 
                        Math.sqrt(-2 * A * Math.log(1.0 - Math.random()));
                col += (dRuido + 0.5);
                if (col > 255) {
                    col = 255;
                }
                if (col < 0) {
                    col = 0;
                }
                imagenConRuido2D[y][x] = col;
            }
        }
        return imagenConRuido2D;
    }

    /**
     * Adiciona ruido ruido de Rayleigh a la imagen original, recibe el buffer
     * de la imagen en dos dimensiones y la varianza del ruido, regresa una
     * copia de la imagen degradada (en una dimension).
     *
     * @param imagen la imagen que se degrada
     * @param var la varianza del ruido
     *
     * @return devuelve la imagen degradada en una dimension
     */
    public int[] adicionaRuidoDeRayleigh2DA1D(int[][] imagen, double var) {
        int col;
        int [] imagenConRuido1D = this.getImagenConRuido1D();
        int alto = this.getAlto();
        int ancho = this.getAncho();
        this.setVarianza(var);
        double A = 2.3299 * var;
        int index = 0;
        for (int y = alto - 1; y >= 0; y--) {
            for (int x = 0; x < ancho; x++) {
                col = imagen[y][x];
                double dRuido = 
                        Math.sqrt(-2 * A * Math.log(1.0 - Math.random()));
                col += (dRuido + 0.5);
                if (col > 255) {
                    col = 255;
                }
                if (col < 0) {
                    col = 0;
                }
                imagenConRuido1D[index] = col;
                index++;
            }
        }
        return imagenConRuido1D;
    }
}
