/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Clase que se utiliza para filtrar un Buffer de imagen por medio de maximo
 * valor encontrado en una mascara conformada por los pixeles de la imagen.
 *
 * @author Saul De La O Torres
 * @version 1.0 22 de Julio de 2003
 */
public class FiltroDelMaximo extends FiltroNoLineal {
    /**
     * El Constructor solo recibe el tipo de mascara a emplear en el filtro.
     * 
     * @param tipoMascara el tipo de mascara y el elemento
     * @param elem el elemento
     */
    public FiltroDelMaximo(int tipoMascara, int elem) {
        super(tipoMascara, elem);
    }
    /**
     * Realiza el filtrado de imagen por medio del minimo valor encontrado en 
     * la mascara que se obtiene de la imagen.
     * 
     * @param imagen la imagen a filtrar
     * @param alto el alto en pixeles de la imagen
     * @param ancho el ancho en pixeles de la imagen
     * 
     * @return devuelve la imagen filtrada
     */
    public int[][] filtrarImagenPorMaximo(int[][] imagen, int alto,
            int ancho) {
        int[][] imgFiltrada = null;
        try {
            imgFiltrada = new int[alto][ancho];
            for(int y = 0; y < alto; y++) {
                if(imgFiltrada[y] == null) {
                    imgFiltrada[y] = new int[ancho];
                    }
                for(int x = 0; x < ancho; x++) {
                    tomarElementos(imagen, x, y, alto, ancho);
                    imgFiltrada[y][x] = obtenerMaximo();
                    }
                }
        } catch (NegativeArraySizeException e) {
            System.out.println(" Error alto o ancho o ambos negativos"
                    + " en filtraImagenPorMaximo() " + e);
        } catch (NullPointerException e) {
            System.out.println(" Error en filtraImagenPorMaximo() " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" Error en filtraImagenPorMaximo() " + e);
        }
        return imgFiltrada;
    }

    /**
     * Obtiene el valor maximo de la mascara seleccionada y lo devuelve.
     * 
     * @return devuelve el maximo buscado
     */
    public int obtenerMaximo() {
        int max = MAX;
        for(int n = 0; n < this.elementosMascara; n++) {
            if (mascara[n] > max) {
                max = mascara[n];
                }
            }
        return max;
    }
}