/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Clase para filtrar una imagen por medio de la estadistica de la mediana.
 *
 * @author Saul De La O Torres
 * @version 1.0 20 de Septiembre de 2003
 */
public class FiltroMediana extends FiltroNoLineal {

    /**
     * Constante entera de dos
     */
    private static final int DOS=2;
    /**
     * Crea el filtro de la mediana
     */
    public FiltroMediana() {
        super();
    }

    /**
     * El Constructor solo recibe el tipo de mascara a emplear en el filtro.
     *
     * @param tipoMascara el tipo de mascara
     * @param elem el numero de elementos de la mascara
     */
    public FiltroMediana(int tipoMascara, int elem) {
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
    public int[][] filtrarImagenPorMediana(int[][] imagen, int alto,
            int ancho) {
        int[][] imgFiltrada=null;
//        try {
            imgFiltrada=new int[alto][ancho];
            for(int y=0; y<alto; y++) {
                if(imgFiltrada[y]==null) {
                    imgFiltrada[y]=new int[ancho];
                }
                for(int x=0; x<ancho; x++) {
                    tomarElementos(imagen, x, y, alto, ancho);
                    imgFiltrada[y][x]=encontrarMediana();
                }
            }
//        } catch(NegativeArraySizeException e) {
//            System.out.println(" Error alto o ancho o ambos negativos"
//                    +" en filtrarImagenPorMediana() "+e);
//        } catch(NullPointerException e) {
//            System.out.println(" Error en filtrarImagenPorMediana() "+e);
//        } catch(ArrayIndexOutOfBoundsException e) {
//            System.out.println(" Error en filtrarImagenPorMediana() "+e);
//        }
        return imgFiltrada;
    }

    /**
     * Realiza el filtrado de imagen por medio de la media aritmetica.
     *
     * @param vectorD la image que se filtra
     * @param ini el inicio del filtrado del vector
     * @param tam tamanio de la mascara
     * @param fin el fin del filtrado del vector
     *
     * @return devuelve la imagen filtrada
     */
    public int[] filtrarImagenPorMediana(double[] vectorD, int ini, int tam,
            int fin) {
        int[] vector=new int[vectorD.length];
        for(int y=ini; y<fin; y++) {
            vector[y]=(int) vectorD[y];
        }
        int[] vectorFiltrado=null;
        try {
            vectorFiltrado=new int[vectorD.length];
            int[] mask=new int[tam];
            for(int x=ini; x<fin; x++) {
                for(int k=0; k<tam; k++) {
                    if((x+k>=fin)) {
                        mask[k]=vector[k];
                        }
                    else {
                        mask[k]=vector[x+k];
                        }
                    }
                vectorFiltrado[x]=encontrarMediana(mask);
                // DEBUG
                //System.out.print(" "+vectorFiltrado[x]);
                }
        } catch(NegativeArraySizeException e) {
            System.out.println(" Error alto o ancho o ambos negativos"
                    +" en filtrarImagenPorMediana() "+e);
        } catch(NullPointerException e) {
            System.out.println(" Error en filtrarImagenPorMediana() "+e);
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println(" Error en filtrarImagenPorMediana() "+e);
        }
        return vectorFiltrado;
    }
    
    /**
     * Realiza el filtrado de imagen por medio de la media aritmetica.
     *
     * @param vectorD la image que se filtra
     * @param ini el inicio del filtrado del vector
     * @param tam tamanio de la mascara
     * @param fin el fin del filtrado del vector
     *
     * @return devuelve la imagen filtrada
     */
    public double[] filtrarImagenPorMedianaD(double[] vectorD, int ini, int tam,
            int fin) {
        int[] vector=new int[vectorD.length];
        for(int y=ini; y<fin; y++) {
            vector[y]=(int) vectorD[y];
        }
        double[] vectorFiltrado=null;
        try {
            vectorFiltrado=new double[vectorD.length];
            int[] mask=new int[tam];
            for(int x=ini; x<fin; x++) {
                for(int k=0; k<tam; k++) {
                    if((x+k>=fin)) {
                        mask[k]=vector[k];
                        }
                    else {
                        mask[k]=vector[x+k];
                        }
                    }
                vectorFiltrado[x]=encontrarMediana(mask);
                // DEBUG
                //System.out.print(" "+vectorFiltrado[x]);
                }
        } catch(NegativeArraySizeException e) {
            System.out.println(" Error alto o ancho o ambos negativos"
                    +" en filtrarImagenPorMediana() "+e);
        } catch(NullPointerException e) {
            System.out.println(" Error en filtrarImagenPorMediana() "+e);
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println(" Error en filtrarImagenPorMediana() "+e);
        }
        return vectorFiltrado;
    }


    /**
     * Obtiene la media aritmetica de la mascara seleccionada.
     *
     * @return devuelve la mediana
     */
    public int encontrarMediana() {
        int elementoMediana=0;
        try {
            mascara=ordenarPorBurbuja(mascara);
            int mitad=mascara.length/DOS;
            elementoMediana=mascara[mitad];
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(" Error mitad negativo en encontrarMediana()"
                    +" o en ordenaPorBurbuja() "+e);
        }
        return elementoMediana;
    }

    /**
     * Obtiene la media aritmetica de la mascara seleccionada.
     *
     * @param mask la mascara del filtro
     * @return devuelve la mediana
     */
    public int encontrarMediana(int[] mask) {
        // DEBUG
        //imprimirVector(mask, "\nmascara desordenada");
        int elementoMediana=0;
        try {
            mask=ordenarPorBurbuja(mask);
            // DEBUG
            //imprimirVector(mask, "\nmascara ordenada");
            int mitad=mask.length/DOS;
            elementoMediana=mask[mitad];
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(" Error mitad negativo en encontrarMediana()"
                    +" o en ordenaPorBurbuja() "+e);
        }
        return elementoMediana;
    }

    // DEBUG
    /**
     * Imprime el vector
     * @param vector el vector a imprimir
     * @param mensaje el mensaje a mostrar
     */
    public void imprimirVector(int[] vector, String mensaje) {
        System.out.println(mensaje);
        for(int i=0, k=1; i<vector.length; i++, k++) {
            System.out.print(" "+vector[i]);
            if(k%16==0) {
                System.out.println();
                }
            }
        System.out.println();
    }
}
