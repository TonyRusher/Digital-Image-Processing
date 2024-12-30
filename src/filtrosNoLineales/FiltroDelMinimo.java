/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Clase que se utiliza para filtrar un Buffer de imagen por medio de minimo
 * valor encontrado en una mascara conformada por los pixeles de la imagen.
 *
 * @author Saul De La O Torres
 * @version 1.0 22 de Julio de 2003
 */
public class FiltroDelMinimo extends FiltroNoLineal {
    /**
     * El Constructor solo recibe el tipo de mascara a emplear en el filtro.
     * 
     * @param tipoMascara tipo de mascara a aplicar
     * @param elem el elemento 
     */
    public FiltroDelMinimo(int tipoMascara, int elem) {
        super(tipoMascara, elem);
    }

    /**
     * Realiza el filtrado de imagen por medio del minimo valor del pixel
     * encontrado en la mascara.
     * 
     * @param imagen la imagen a filtrar
     * @param alto el alto en pixeles de la imagen
     * @param ancho el ancho en pixeles de la imagen
     * 
     * @return devuelve la imagen filtrada
     */
    public int[][] filtrarImagenPorMinimo(int[][] imagen, int alto,
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
                    imgFiltrada[y][x] = obtenerMinimo();
                    }
                }
        } catch (NegativeArraySizeException e) {
            System.out.println(" Error alto o ancho o ambos negativos"
                    + " en filtraImagenPorMinimo() " + e);
        } catch (NullPointerException e) {
            System.out.println(" Error en filtraImagenPorMinimo() " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" Error en filtraImagenPorMinimo() " + e);
        }
        return imgFiltrada;
    }

    /**
     * Obtiene el minimo valor de la mascara y lo devuelve.
     * 
     * @return devuelve el minimo buscado 
     */
    public int obtenerMinimo() {
        int min = MIN;
        for(int n = 0; n < this.elementosMascara; n++) {
            if(mascara[n] < min) {
                min = mascara[n];
                }
            }
        return min;
    }
}