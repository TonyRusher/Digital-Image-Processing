/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Clase que filtra una imagen por medio del siguiente algoritmo:<br>
 * <br>1. Se toma una mascara de tamanio impar de algun tipo
 * <br>2. Se ordena la mascara de menor a mayor
 * <br>3. Se recibe un numero entero
 * <br>4. El numero anterior indica que no se van a tomar varios valores de la
 * mascara ordenada, partiendo del centro hacia los extremos.
 * <br>5. Con los valores restantes de la mascara se realiza el calculo de la
 * media aritmetica, el valor obtenido, reemplaza al pixel que quedo centrado 
 * en la posicion (x, y).
 *
 * @author Saul De La O Torres
 * @version 1.0 22 de Julio de 2003
 */
public class FiltroAlfaTrimmed extends FiltroNoLineal {
    /**
     * Crea el objeto del filtro
     * 
     * @param tipoMascara el tipo de mascara
     * @param elem el elemento
     */
    public FiltroAlfaTrimmed(int tipoMascara, int elem) {
        super(tipoMascara, elem);
    }

    /**
     * Realiza el filtrado de imagen por medio del minimo valor del pixel
     * encontrado en la mascara.
     * 
     * @param imagen la image que se procesa
     * @param alto la altura de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     * @param P el parametro estadistico del filtro
     * 
     * @return devuelve la matriz de la imagen procesada
     */
    public int [][] filtrarImagenPorAlfaTrimmed(int[][] imagen, int alto,
            int ancho, int P) {
        int[][] imgFiltrada = null;
//        try {
            imgFiltrada = new int[alto][ancho];
            for(int y = 0; y < alto; y++) {
                if(imgFiltrada[y] == null) {
                    imgFiltrada[y] = new int[ancho];
                    }
                for(int x = 0; x < ancho; x++) {
                    tomarElementos(imagen, x, y, alto, ancho);
                    mascara = this.ordenarPorBurbuja(mascara);
                    imgFiltrada[y][x] = obtenerMediaTrimmed(P);
                    }
                }
//        } catch (NegativeArraySizeException e) {
//            System.out.println(" Error alto o ancho o ambos negativos"
//                    + " en filtraImagenPorAlfaTrimmed() " + e);
//        } catch (NullPointerException e) {
//            System.out.println(" Error en filtraImagenPorAlfaTrimmed() " + e);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println(" Error en filtraImagenPorAlfaTrimmed() " + e);
//        }
        return imgFiltrada;
    }

    /**
     * Obtiene la media aritmetica de la mascara seleccionada.
     * 
     * @param P el parametro del filtro
     * 
     * @return devuelve el calculo del filtrado
     */
    public int obtenerMediaTrimmed(int P) {
        double media = 0.0;
        for(int n = 0; n < this.elementosMascara; n++) {
            if(n >= P && n < elementosMascara - P) {
                media += this.mascara[n];
                }
            }
        media /= ((double) this.elementosMascara - (2 * P));
        int iMedia = (int) media;
        if(media - (double) iMedia > MEDIO) {
            media = Math.ceil(media);
            } 
        else {
            media = Math.floor(media);
            }
        iMedia = (int) media;
        return iMedia;
    }
}