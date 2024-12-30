/**
 * Paquete al que pertenece la clase.
 */
package Ruidos;

/**
 * Esta clase genera ruido Coherente (senoidal) y lo adiciona a la imagen con el
 * fin de degradarla.
 *
 * @author Saul De La O Torres
 * @version 1.0 14 de Julio de 2003
 */
public class RuidoCoherente extends Ruido {

    /**
     * Amplitud del ruido Coherente o Senoidal.
     */
    private double amplitud;
    /**
     * Fracunecia sobre el eje del tiempo en direccion X para el ruido Coherente
     * o Senoidal.
     */
    private double frecX;
    /**
     * Fracunecia sobre el eje del tiempo en direccion Y para el ruido Coherente
     * o Senoidal.
     */
    private double frecY;

    /**
     * El Constructor recibe la altura y el ancho de la imagen a degradar con
     * ruido.
     *
     * @param alto la altura de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public RuidoCoherente(int alto, int ancho) {
        super(alto, ancho);
    }

    /**
     * Adiciona ruido Senoidal a la imagen original, recibe el buffer de la
     * imagen en dos dimensiones, la amplitud y las frecuencias en X y Y del
     * ruido, regresa una copia de la imagen degradada (tambien en dos
     * dimensiones).
     *
     * @param imagen la imagen que se degrada
     * @param amp la amplitud del ruido
     * @param fX la frecuencia en X del ruido
     * @param fY la frecuencia en Y del ruido
     *
     * @return devuelve la imagen degradada en dos dimensiones
     */
    public int[][] adicionarRuidoCoherenteAImagen(int[][] imagen, double amp,
            double fX, double fY) {
        int col;
        amplitud = amp;
        frecX = fX;
        frecY = fY;

        int [][] imagenConRuido2D = this.getImagenConRuido2D();
        int alto = this.getAlto();
        int ancho = this.getAncho();

        for (int y = 0; y < alto; y++) {
            if (imagenConRuido2D[y] == null) {
                imagenConRuido2D[y] = new int[ancho];
            }
            for (int x = 0; x < ancho; x++) {
                col = imagen[y][x];
                double dRuido = amplitud * Math.sin(frecX * x + frecY * y);
                col += dRuido;
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
     * Adiciona ruido Senoidal a la imagen original, recibe el buffer de la
     * imagen en dos dimensiones, la amplitud y las frecuencias en X y Y del
     * ruido, regresa una copia de la imagen degradada (en una dimension).
     *
     * @param imagen la imagen que se degrada
     * @param amp la amplitud del ruido
     * @param fX la frecuencia en X del ruido
     * @param fY la frecuencia en Y del ruido
     *
     * @return devuelve la imagen degradada en dos dimensiones
     */
    public int[] adicionarRuidoCoherenteAImagen2DA1D(int[][] imagen,
            double amp, double fX, double fY) {
        int col;
        int [] imagenConRuido1D = this.getImagenConRuido1D();
        int alto = this.getAlto();
        int ancho = this.getAncho();
        amplitud = amp;
        frecX = fX;
        frecY = fY;
        int index = 0;
        for (int y = alto - 1; y >= 0; y--) {
            for (int x = 0; x < ancho; x++) {
                col = imagen[y][x];
                double dRuido = amplitud * Math.sin(frecX * x + frecY * y);
                col += dRuido;
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
