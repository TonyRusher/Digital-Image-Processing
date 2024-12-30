/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Clase que filtra una imagen por medio del siguiente algoritmo:<br>
 * <br>1. Se toma una mascara de tamanio impar de algun tipo
 * <br>2. Se recibe un numero entero P
 * <br>3. El numero anterior indica que dos sumatorias que se obtienen de la
 * misma mascara, para una, los elementos se elevan a la potencia P+1 y para la
 * otra sumatoria se elevan los elementos a la P.
 * <br>4. Con las dos sumatorias se obtiene un cociente el cual reemplaza al
 * pixel que quedo centrado en la posicion (x, y).
 *
 * @author Saul De La O Torres
 * @version 1.0 22 de Julio de 2003
 */
public class FiltroInferiorContraArmonico extends FiltroNoLineal {

    /**
     * El constructor solo recibe el tipo de mascara y el tamanio de la misma.
     *
     * @param tipoMascara el tipo de mascara
     * @param numElem el tamanio de la mascara
     */
    public FiltroInferiorContraArmonico(int tipoMascara, int numElem) {
        super(tipoMascara, numElem);
    }

    /**
     * Realiza el filtrado de imagen por medio del minimo valor del pixel
     * encontrado en la mascara.
     *
     * @param imagen la imagen a filtrar
     * @param alto el ancho en pixeles de la imagen
     * @param ancho el ancho en pixeles de la imagen
     * @param P el parametro de comparacion para obtener la media
     *
     * @return devuelve la imagen filtrada
     */
    public int[][] filtrarImagenPorContraArmonico(int[][] imagen, int alto,
            int ancho, double P) {
        int[][] imgFiltrada=null;
        try {
            imgFiltrada=new int[alto][ancho];
            for(int y=0; y<alto; y++) {
                if(imgFiltrada[y]==null) {
                    imgFiltrada[y]=new int[ancho];
                    }
                for(int x=0; x<ancho; x++) {
                    tomarElementos(imagen, x, y, alto, ancho);
                    imgFiltrada[y][x]=obtenerContraArmonico(P);
                    }
                }
        } catch(NegativeArraySizeException e) {
            System.out.println(" Error alto o ancho o ambos negativos"
                    +" en filtrarImagenPorContraArmonico() "+e);
        } catch(NullPointerException e) {
            System.out.println(" Error en filtrarImagenPorContraArmonico() "
                    +e);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(" Error en filtrarImagenPorContraArmonico() "
                    +e);
        }
        return imgFiltrada;
    }

    /**
     * Obtiene la media de dos sumatorias de la mascara seleccionada.
     *
     * @param P el parametro de busqueda
     *
     * @return devuelve la media
     */
    public int obtenerContraArmonico(double P) {
        double suma1=0.0;
        double suma2=0.0;
        double cociente;
        for(int n=0; n<this.elementosMascara; n++) {
            suma1+=Math.pow(this.mascara[n], P+1);
            suma2+=Math.pow(this.mascara[n], P);
            }
        cociente=suma1/suma2;
        int iCociente=(int) cociente;
        if(cociente-(double) iCociente>MEDIO) {
            cociente=Math.ceil(cociente);
            } 
        else {
            cociente=Math.floor(cociente);
            }
        iCociente=(int) cociente;
        return iCociente;
    }
}
