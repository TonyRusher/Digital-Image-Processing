/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Clase que filtra una imagen por medio del siguiente algoritmo:<br>
 * 1. Se toma una mascara de tamanio impar de algun tipo. <br>
 * 2. Se eleva cada uno de los elementos de la mascara a la inversa del total de
 * ellos y se realiza el producto, se obtiene el valor que reemplaza al pixel
 * que quedo centrado en la posicion (x, y).<br>
 *
 * @author Saul De La O Torres
 * @version 1.0 22 de Julio de 2003
 */
public class FiltroInferiorGeometrico extends FiltroNoLineal {

    /**
     * El constructor solo recibe el tipo de mascara y el tamanio de la misma.
     *
     * @param tipoMascara el tipo de mascara
     * @param numElem el numero de elementos de la mascara
     */
    public FiltroInferiorGeometrico(int tipoMascara, int numElem) {
        super(tipoMascara, numElem);
    }

    /**
     * Realiza el filtrado de imagen por el valor inferior geometrico del pixel
     * encontrado en la mascara.
     *
     * @param imagen la imagen a ser filtrada
     * @param alto la altura en pixeles de la imagen
     * @param ancho el ancho de la imagen en pixeles
     *
     * @return devuelve la imagen filtrada
     */
    public int[][] filtrarImagenPorInferiorGeometrico(int[][] imagen,
            int alto, int ancho) {
        int[][] imgFiltrada=null;
        try {
            imgFiltrada=new int[alto][ancho];
            for(int y=0; y<alto; y++) {
                if(imgFiltrada[y]==null) {
                    imgFiltrada[y]=new int[ancho];
                    }
                for(int x=0; x<ancho; x++) {
                    tomarElementos(imagen, x, y, alto, ancho);
                    imgFiltrada[y][x]=obtenerInferiorGeometrico();
                    }
                }
        } catch(NegativeArraySizeException e) {
            System.out.println(" Error alto o ancho o ambos negativos"
                    +" en filtrarImagenPorInferiorGeometrico() "
                    +e);
        } catch(NullPointerException e) {
            System.out.println(" Error en filtrarImagenPorInferiorGeometrico()"
                    +" "+e);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(" Error en filtrarImagenPorInferiorGeometrico()"
                    +" "+e);
        }
        return imgFiltrada;
    }

    /**
     * Obtiene el producto de los elementos elevados a la inversa del total de
     * elementos de la mascara seleccionada.
     *
     * @return devuelve el punto medio del filtro inferior geometrico
     */
    public int obtenerInferiorGeometrico() {
        double producto=DUNO;
        for(int n=0; n<this.elementosMascara; n++) {
            producto*=Math.pow(this.mascara[n],
                    (double) (DUNO/(double) elementosMascara));
            }
        int iProducto=(int)producto;
        if(producto-(double)iProducto>MEDIO) {
            producto=Math.ceil(producto);
            } 
        else {
            producto=Math.floor(producto);
            }
        iProducto=(int)producto;
        return iProducto;
    }
}
