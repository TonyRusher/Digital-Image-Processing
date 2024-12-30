/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Clase que filtra una imagen por medio del siguiente algoritmo:<br>
 * <br>1. Se toma una mascara de tamanio impar de algun tipo
 * <br>2. Se busca el maximo valor del pixel encontrado en la mascara
 * <br>2. Se busca el minimo valor del pixel encontrado en la mascara
 * <br>3. Se ponderan ambos valores y dicho valor, reemplaza al pixel que quedo
 * centrado en la posicion (x, y).
 *
 * @author Saul De La O Torres
 * @version 1.0 22 de Julio de 2003
 */
public class FiltroDelPuntoMedio extends FiltroNoLineal {

    /**
     * Constante DDOS
     */
    private static final double DDOS = 2.0;

    /**
     * El constructor recibe el tipo de mascara y el numero de elementos para la
     * mascara.
     * 
     * @param tipoMascara tipo de mascara del filtro
     * @param numElem numero de elementos del filtro
     */
    public FiltroDelPuntoMedio(int tipoMascara, int numElem) {
        super(tipoMascara, numElem);
    }

    /**
     * Realiza el filtrado de imagen por el valor del punto medio del pixel
     * encontrado en la mascara.
     * 
     * @param imagen la matriz de imagen que se procesa
     * @param alto la altura de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     * 
     * @return devuelve la matriz de imagen filtrada
     */
    public int [][] filtrarImagenDelPuntoMedio(int[][] imagen, int alto,
            int ancho) {
        int[][] imgFiltrada = null;
        try {
            imgFiltrada = new int[alto][ancho];
            for (int y = 0; y < alto; y++) {
                if (imgFiltrada[y] == null) {
                    imgFiltrada[y] = new int[ancho];
                }
                for (int x = 0; x < ancho; x++) {
                    tomarElementos(imagen, x, y, alto, ancho);
                    imgFiltrada[y][x] = obtenerPuntoMedio();
                }
            }
        } catch (NegativeArraySizeException e) {
            System.out.println(" Error alto o ancho o ambos negativos"
                    + " en filtrarImagenDelPuntoMedio() " + e);
        } catch (NullPointerException e) {
            System.out.println(" Error en filtrarImagenDelPuntoMedio() " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" Error en filtrarImagenDelPuntoMedio() " + e);
        }
        return imgFiltrada;
    }

    /**
     * Obtiene el punto medio del valor maximo y minimo encontrados en los
     * elementos de la mascara seleccionada.
     * 
     * @return devuelve el punto medio del filtro
     */
    public int obtenerPuntoMedio() {
        double puntoMedio = 0.0;
        puntoMedio = (obtenerMaximo() + obtenerMinimo()) / DDOS;
        int iPuntoMedio = (int) puntoMedio;
        if (puntoMedio - (double) iPuntoMedio > MEDIO) {
            puntoMedio = Math.ceil(puntoMedio);
        } else {
            puntoMedio = Math.floor(puntoMedio);
        }
        iPuntoMedio = (int) puntoMedio;
        return iPuntoMedio;
    }

    /**
     * Obtiene el valor maximo de la mascara seleccionada y lo devuelve.
     * 
     * @return devuelve el valor maximo de la mascara
     */
    private int obtenerMaximo() {
        int max = MAX;
        for (int n = 0; n < this.elementosMascara; n++) {
            if (mascara[n] > max) {
                max = mascara[n];
            }
        }
        return max;
    }

    /**
     * Obtiene el minimo valor de la mascara y lo devuelve.
     * 
     * @return devuelve el valor minimo de la mascara
     */
    private int obtenerMinimo() {
        int min = MIN;
        for (int n = 0; n < this.elementosMascara; n++) {
            if (mascara[n] < min) {
                min = mascara[n];
            }
        }
        return min;
    }
}