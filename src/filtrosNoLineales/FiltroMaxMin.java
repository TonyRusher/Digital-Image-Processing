/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Clase que filtra una imagen por medio del siguiente algoritmo:<br>
 * 1. Se toma una mascara de tamaio impar de algun tipo. <br>
 * 2. Se busca el maximo valor del pixel encontrado en la mascara. <br>
 * 3. Se busca el minimo valor del pixel encontrado en la mascara. <br>
 * 4. Se restan el pixel con el maximo y el pixel menos el minimo, se
 * comparan y si la primera resta es menor que la segunda, se toma el maximo
 * para sustituir al pixel en la coordenada (x, y), en caso contrario, se toma
 * el minimo y dicho valor, reemplaza al pixel que quedo centrado en la posicion (x, y).
 *
 * @author Saul De La O Torres
 * @version 1.0 03 de Agosto de 2003
 */
public class FiltroMaxMin extends FiltroNoLineal {

    /**
     * El constructor solo recibe el tipo de mascara y el tamanio de la misma.
     * 
     * @param tipoMascara el tipo de mascara
     * @param numElem el numero de elementos de la mascara
     */
    public FiltroMaxMin(int tipoMascara, int numElem) {
        super(tipoMascara, numElem);
    }

    /**
     * Realiza el filtrado de imagen por el valor del punto medio del pixel
     * encontrado en la mascara.
     * 
     * @param imagen la imagen a filtrar
     * @param alto el alto de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     * 
     * @return devuelve la imagen filtrara
     */
    public int[][] filtrarImagenConMaxMin(int[][] imagen, int alto, int ancho) {
        int[][] imgFiltrada = null;
        try {
            imgFiltrada = new int[alto][ancho];
            for (int y = 0; y < alto; y++) {
                if (imgFiltrada[y] == null) {
                    imgFiltrada[y] = new int[ancho];
                }
                for (int x = 0; x < ancho; x++) {
                    tomarElementos(imagen, x, y, alto, ancho);
                    imgFiltrada[y][x] = obtenerMaxMin(imagen[y][x]);
                }
            }
        } catch (NegativeArraySizeException e) {
            System.out.println(" Error alto o ancho o ambos negativos"
                    + " en filtrarImagenConMaxMin() " + e);
        } catch (NullPointerException e) {
            System.out.println(" Error en filtrarImagenConMaxMin() " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" Error en filtrarImagenConMaxMin() " + e);
        }
        return imgFiltrada;
    }

    /**
     * Obtiene el punto medio del valor maximo y minimo encontrados en los
     * elementos de la mascara seleccionada.
     * 
     * @param fxy funcion xy
     * 
     * @return devuelve el punto medio
     */
    public int obtenerMaxMin(int fxy) {
        int iPuntoMaxMin;
        int fmax = obtenerMaximo();
        int fmin = obtenerMinimo();
        if ((fmax - fxy) <= (fxy - fmin)) {
            iPuntoMaxMin = fmax;
        } else {
            iPuntoMaxMin = fmin;
        }
        return iPuntoMaxMin;
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
     * @return devuelve elminimo valor de la mascara
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