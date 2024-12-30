/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Clase para filtrar una imagen por medio de la estadistica de la media
 * aritmetica.
 *
 * @author Saul De La O Torres
 * @version 1.0 20 de Septiembre de 2003
 */
public class FiltroMediaAritmetica extends FiltroNoLineal {

    /**
     * El Constructor solo recibe el tipo de mascara a emplear en el filtro.
     *
     * @param tipoMascara el tipo de mascara
     * @param elem el numero de elementos de la mascara
     */
    public FiltroMediaAritmetica(int tipoMascara, int elem) {
        super(tipoMascara, elem);
    }

    /**
     * Realiza el filtrado de imagen por medio de la media aritmetica.
     *
     * @param imagen la image que se filtra
     * @param alto el alto de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     *
     * @return devuelve la imagen filtrada
     */
    public int[][] filtrarImagenMediaAritmetica(int[][] imagen, int alto,
            int ancho) {
        int[][] imgFiltrada=null;
        try {
            imgFiltrada=new int[alto][ancho];
            for(int y=0; y<alto; y++) {
                if(imgFiltrada[y]==null) {
                    imgFiltrada[y]=new int[ancho];
                }
                for(int x=0; x<ancho; x++) {
                    tomarElementos(imagen, x, y, alto, ancho);
                    imgFiltrada[y][x]=obtenerMedia();
                }
            }
        } catch(NegativeArraySizeException e) {
            System.out.println(" Error alto o ancho o ambos negativos"
                    +" en filtrarImagenMediaAritmetica() "+e);
        } catch(NullPointerException e) {
            System.out.println(" Error en filtrarImagenMediaAritmetica() "
                    +e);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(" Error en filtrarImagenMediaAritmetica() "
                    +e);
        }
        return imgFiltrada;
    }

    /**
     * Obtiene la media aritmetica de la mascara seleccionada.
     *
     * @return devuelve la media
     */
    public int obtenerMedia() {
        double media=0.0;
        for(int n=0; n<this.elementosMascara; n++) {
            media+=this.mascara[n];
            }
        media/=(double) this.elementosMascara;
        int iMedia=(int) media;
        if(media-(double) iMedia>MEDIO) {
            media=Math.ceil(media);
            } 
        else {
            media=Math.floor(media);
            }
        iMedia=(int) media;
        return iMedia;
    }
}
