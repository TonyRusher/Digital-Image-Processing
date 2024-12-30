/**
 * Paquete al que pertenece la clase.
 */
package Ruidos;

/**
 * Esta clase genera ruido de Sal y Pimienta y lo adiciona a la imagen con el
 * fin de degradarla.
 *
 * @author Saul De La O Torres
 * @version 1.0 14 de Julio de 2003
 */
public class RuidoDeSalyPimienta extends Ruido {

    /**
     * Probabilidad de que salga ruido de sal o de pimienta en la imagen.
     */
    private double probabilidad;
    /**
     * Si la probabilidad esta en cierto rango, entonces el pixel en la posicion
     * (x, y), se cambia por ruido de sal o pimienta.
     */
    private boolean cambio;

    /**
     * El Constructor recibe la altura y el ancho de la imagen a degradar con
     * ruido.
     *
     * @param alto la altura de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public RuidoDeSalyPimienta(int alto, int ancho) {
        super(alto, ancho);
        probabilidad = 0.5;
    }

    /**
     * Adiciona ruido De Sal y Pimienta a la imagen original, recibe el buffer
     * de la imagen en dos dimensiones y la probabilidad del ruido, regresa una
     * copia de la imagen degradada (tambien en dos dimensiones).
     *
     * @param imagen la imagen que se degrada
     * @param prob la probabilidad de aparicion de sal o pimienta del ruido
     *
     * @return devuelve la imagen degradada en dos dimensiones
     */
    public int[][] adicionarRuidoDeSalyPimienta(int[][] imagen, double prob) {
        probabilidad = prob;
        double DATA;
        int col;

        DATA = (int) (probabilidad * 32768 / 2);
        final double DATA2 = DATA + 16384.0;
        final double DATA1 = 16384.0 - DATA;

        int [][] imagenConRuido2D = this.getImagenConRuido2D();
        int alto = this.getAlto();
        int ancho = this.getAncho();
        double dRuido = 0.0;

        cambio = false;
        for (int y = 0; y < alto; y++) {
            if (imagenConRuido2D[y] != null) {
                imagenConRuido2D[y] = new int[ancho];
            }
            for (int x = 0; x < ancho; x++) {
                col = imagen[y][x];
                DATA = Math.random() * 32768;
                if (DATA > DATA1 && DATA < DATA2) {
                    if (DATA >= 16384 && DATA < DATA2) {
                        dRuido = 0.0;
                    }
                    if (DATA >= DATA1 && DATA < 16384) {
                        dRuido = 255.0;
                    }
                    cambio = true;
                } else {
                    cambio = false;
                }
                if (cambio == true) {
                    col = (int) (dRuido);
                }
                imagenConRuido2D[y][x] = col;
            }
        }
        return imagenConRuido2D;
    }

    /**
     * Adiciona ruido de Sal y Pimienta a la imagen original, recibe el buffer
     * de la imagen en dos dimensiones y la probabilidad del ruido, regresa una
     * copia de la imagen degradada (en una dimension).
     *
     * @param imagen la imagen que se degrada
     * @param prob la probabilidad de aparicion de sal o pimienta del ruido
     *
     * @return devuelve la imagen degradada en una dimension
     */
    public int[] adicionarRuidoDeSalyPimienta2DA1D(int[][] imagen,
            double prob) {
        probabilidad = prob;
        int col;
        int [] imagenConRuido1D = this.getImagenConRuido1D();
        int alto = this.getAlto();
        int ancho = this.getAncho();
        double dRuido = 0.0;

        double DATA = probabilidad * 32768.0 / 2; // p=0.2 DATA = 3276.8
        final double DATA2 = DATA + 16384.0;       // DATA2 = 19660.8
        final double DATA1 = 16384.0 - DATA;       // DATA1 = 13107.2	 
        int index = 0;
        cambio = false;
        for (int y = alto - 1; y >= 0; y--) {
            for (int x = 0; x < ancho; x++) {
                col = imagen[y][x];
                DATA = Math.random() * 32768;
                if (DATA > DATA1 && DATA < DATA2) {
                    if (DATA >= 16384.0 && DATA < DATA2) {
                        dRuido = 0.0;
                    }
                    if (DATA >= DATA1 && DATA < 16384.0) {
                        dRuido = 255.0;
                    }
                    cambio = true;
                } else {
                    cambio = false;
                }
                if (cambio == true) {
                    col = (int) (dRuido);
                }
                imagenConRuido1D[index] = col;
                index++;
            }
        }
        return imagenConRuido1D;
    }
}
