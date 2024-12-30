/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Clase que filtra una imagen por medio del siguiente algoritmo:<br>
 * <br>1. Se toma una mascara de tamanio impar de algun tipo
 * <br>2. Se realiza una sumatoria cada una de la inversa de los elementos de 
 * la mascara
 * <br>3. El valor Inferior armonico se obtiene del cociente del numero de
 * elementos de la mascara y la sumatoria del punto anterior, dicho valor,
 * reemplaza al pixel que quedo centrado en la posicion (x, y).
 *
 * @author Saul De La O Torres
 * @version 1.0 22 de Julio de 2003
 */
public class FiltroInferiorArmonico extends FiltroNoLineal {

    /**
     * El constructor solo recibe el tipo de mascara y el tamanio de la misma.
     * 
     * @param tipoMascara tipo de mascara
     * @param numElem el numero de elementos
     */
    public FiltroInferiorArmonico(int tipoMascara, int numElem) {
        super(tipoMascara, numElem);
    }

    /**
     * Realiza el filtrado de imagen por el valor inferior armonico del pixel
     * encontrado en la mascara.
     * 
     * @param imagen la imagen a filtrar
     * @param alto el alto de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     * 
     * @return devuelve la imagen filtrada
     */
    public int[][] filtrarImagenPorInferiorArmonico(int[][] imagen, int alto,
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
                    imgFiltrada[y][x] = obtenerInferiorArmonico();
                }
            }
        } catch (NegativeArraySizeException e) {
            System.out.println(" Error alto o ancho o ambos negativos"
                    + " en filtrarImagenPorInferiorArmonico() " + e);
        } catch (NullPointerException e) {
            System.out.println(" Error en filtrarImagenPorInferiorArmonico() "
                    + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" Error en filtrarImagenPorInferiorArmonico() "
                    + e);
        }
        return imgFiltrada;
    }

    /**
     * Obtiene el cociente de los elementos elevados entre la sumatoria del
     * inverso de los elementos de la mascara seleccionada.
     * 
     * @return devuelve el cociente
     */
    public int obtenerInferiorArmonico() {
        double suma = 0.0;
        double cociente;
        for (int n = 0; n < this.elementosMascara; n++) {
            suma += DUNO / this.mascara[n];
        }
        cociente = elementosMascara / suma;
        int iCociente = (int) cociente;
        if (cociente - (double) iCociente > MEDIO) {
            cociente = Math.ceil(cociente);
        } else {
            cociente = Math.floor(cociente);
        }
        iCociente = (int) cociente;
        return iCociente;
    }
}