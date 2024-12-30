/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Clase general que almacena los datos de todos los filtros no lineales
 * utilizados para procesar imagen.
 *
 * @author Saul De La O Torres
 * @version 1.0 22 de Julio de 2003
 */
public class FiltroNoLineal implements IMascaraFiltroNoLineal { 

    /**
     * Tipo de mascara: 1. Fila.<br>
     * 2. Columna.<br>
     * 3. Diamante.<br>
     * 4. Cruz.<br>
     * 5. Equis.<br>
     * 6. Cuadrado.<br>
     */
    protected int tipoMascara;
    /**
     * Buffer para almacenar los elementos de la imagen a los cuales se les
     * obtendra la media areitmetica para filtrar la imagen.
     */
    protected int[] mascara;
    /**
     * Numero de elementos que contiene la mascara de filtrado.
     */
    protected int elementosMascara;
    /**
     * Mitad de uno double.
     */
    protected static double MEDIO=0.5;
    /**
     * Inicio del Minimo
     */
    protected static final int MIN=5000;
    /**
     * Inicio del Maximo
     */
    protected static final int MAX=-5000;
    /**
     * Cosntante uno double
     */
    protected static final double DUNO=1.0;
    /**
     * Crea el objeto del fitro no lineal
     */
    public FiltroNoLineal() {

    }

    /**
     * El Constructor recibe el tipo de mascara a utilizar, asigna memoria a la
     * mascara de acuerdo al tipo de ella utilizado.
     *
     * @param tipoMascara el tipo de mascara
     */
    public FiltroNoLineal(int tipoMascara) {
        this.tipoMascara=tipoMascara;
        elementosMascara=1;
        switch(tipoMascara) {
            case FILA:
            case COLUMNA:
                elementosMascara=5;
                break;
            case CRUZ:
            case EQUIS:
                elementosMascara=9;
                break;
            case DIAMANTE:
                elementosMascara=13;
                break;
            case CUADRADO:
                elementosMascara=25;
                break;
            default:
                break;
        }
        try {
            mascara=new int[elementosMascara];
        } catch(NegativeArraySizeException e) {
            System.out.println(" Error elementosMascara negativo"
                    +" en FiltroNoLineal() "+e);
        }
    }

    /**
     * Constructor para definir un filtro no lineal con tipo de mascara y numero
     * de elementos.
     *
     * @param tipoMascara el tipo de mascara
     * @param nElem el numero de elementos de la mascara
     */
    public FiltroNoLineal(int tipoMascara, int nElem) {
        this.tipoMascara=tipoMascara;
        elementosMascara=nElem;
        try {
            mascara=new int[elementosMascara];
        } catch(NegativeArraySizeException e) {
            System.out.println(" Error elementosMascara negativo"
                    +" en FiltroNoLineal() "+e);
        }
    }

    /**
     * Toma los elementos o pixeles de la imagen, siempre asigna una mascara
     * temporal cuadrada, recibe la posicion en (x, y) del pixel central y luego
     * se vacia en la mascara correspondiente seleccionada.
     *
     * @param img la imagen de donde se toman los elementos
     * @param x la coordenada en X
     * @param y la coordenada en Y
     * @param alto el alto de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public void tomarElementos(int[][] img, int x, int y, int alto, int ancho) {
        int[][] mskTmp;
        if(tipoMascara==CUADRADO) {
            int elementoSqrt=(int) Math.sqrt(elementosMascara);
            mskTmp=asignarImagen(img, x, y, elementoSqrt, alto, ancho);
        } else {
            mskTmp=asignarImagen(img, x, y, 5, alto, ancho);
        }
        switch(tipoMascara) {
            case FILA:
                asignarMascaraFila(mskTmp, x, y, 5, 5);
                break;
            case COLUMNA:
                asignarMascaraColumna(mskTmp, x, y, 5, 5);
                break;
            case DIAMANTE:
                asignarMascaraDiamante(mskTmp, x, y, 5, 5);
                break;
            case CRUZ:
                asignarMascaraCruz(mskTmp, x, y, 5, 5);
                break;
            case EQUIS:
                asignarMascaraEquis(mskTmp, x, y, 5, 5);
                break;
            case CUADRADO:
                int elementoSqrt=(int) Math.sqrt(elementosMascara);
                //System.out.println( " elem : " + elementoSqrt );
                asignarMascaraCuadrada(
                        mskTmp, x, y, elementoSqrt, elementoSqrt);
                break;
        }
    }

    /**
     * Toma un pedazo de la imagen del tamanio de la mascara, segun donde vaya
     * moviendose la mascara, es de donde se toma la imagen:
     *
     * @param matriz la matriz a tomar
     * @param x la coordenada en X en la imagen
     * @param y la coordenada en Y en la imagen
     * @param tam el tamanio de la mascara
     * @param al la altura de la imagen en pixeles
     * @param an el ancho de la imagen en pixeles
     *
     * @return devuelve el pedaso de imagen
     *
     * @throws ArrayIndexOutOfBoundsException por si se sale de limites
     */
    public int[][] asignarImagen(int[][] matriz, int x, int y, int tam,
            int al, int an) throws ArrayIndexOutOfBoundsException {
        int ct=0;
        int dif=tam/2;
        int[] subImg=new int[tam*tam];
        int[][] imgTmp=new int[tam][tam];
        for(int n=-dif; n<=dif; n++) {
            for(int m=-dif; m<=dif; m++) {
                if((y+n)<0&&(x+m)<0) {
                    subImg[ct]=matriz[al+n][an+m];
                    } 
                else 
                if((y+n)<0&&x+m<an) {
                    subImg[ct]=matriz[al+n][x+m];
                    } 
                else 
                if((x+m)<0&&y+n<al) {
                    subImg[ct]=matriz[y+n][an+m];
                    } 
                else 
                if(x+m==an&&y+n==al) {
                    subImg[ct]=matriz[0][0];
                    } 
                else 
                if(x+m>=an) {
                    subImg[ct]=matriz[y][m];
                    } 
                else 
                if(y+n>=al) {
                    subImg[ct]=matriz[n][x];
                    } 
                else {
                    subImg[ct]=matriz[y+n][x+m];
                    }
                ct++;
                }
            }
        ct=0;
        for(int n=0; n<tam; n++) {
            if(imgTmp[n]==null) {
                imgTmp[n]=new int[tam];
                }
            for(int m=0; m<tam; m++) {
                imgTmp[n][m]=subImg[ct++];
                }
            }
        // DEBUG
//        System.out.println();
//        for(int n=0; n<tam; n++) {
//            for(int m=0; m<tam; m++) {
//                if(imgTmp[n][m]>=0 && imgTmp[n][m]<10) {
//                    System.out.print( "   " + imgTmp[n][m] );
//                    }
//                if( imgTmp[n][m]<100) {
//                    System.out.print( "  " + imgTmp[n][m] );
//                    }
//                else {
//                    System.out.print( " " + imgTmp[n][m] );
//                    }
//                }
//            System.out.println();
//            }
        return imgTmp;
    }

    /**
     * Asigna la mascara de tipo <b>fila</b> para realizar el filtrado:<br>
     * XXXXX
     *
     * @param img la imagen a procesar
     * @param x la coordenada en x
     * @param y la coordenada en y
     * @param alto el alto de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public void asignarMascaraFila(int[][] img, int x, int y, int alto,
            int ancho) {
        int ct=0;
        for(int n=0; n<alto; n++) {
            for(int m=0; m<ancho; m++) {
                if(n==2) {
                    mascara[ct++]=img[n][m];
                    }
                }
            }
    }

    /**
     * Asigna la mascara de tipo <b>columna</b> para realizar el filtrado:<br>
     *
     * X<br>
     * X<br>
     * X<br>
     * X<br>
     * X<br>
     *
     * @param img la imagen a procesar
     * @param x la coordenada en x
     * @param y la coordenada en y
     * @param alto el alto de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public void asignarMascaraColumna(int[][] img, int x, int y, int alto,
            int ancho) {
        int ct=0;
        for(int n=0; n<alto; n++) {
            for(int m=0; m<ancho; m++) {
                if(m==2) {
                    mascara[ct++]=img[n][m];
                    }
                }
            }
    }

    /**
     * Asigna la mascara de tipo <b>cruz</b> para realizar el filtrado:<br>
     *
     * ___X___<br>
     * ___X___<br>
     * XXXXX<br>
     * ___X___<br>
     * ___X___<br>
     *
     * @param img la imagen a procesar
     * @param x la coordenada en x
     * @param y la coordenada en y
     * @param alto el alto de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public void asignarMascaraCruz(int[][] img, int x, int y, int alto,
            int ancho) {
        int ct=0;
        for(int n=0; n<alto; n++) {
            for(int m=0; m<ancho; m++) {
                if(m==2) {
                    mascara[ct++]=img[n][m];
                    } 
                else 
                if(n==2&&m==2) {
                    mascara[ct++]=img[n][m];
                    } 
                else 
                if(n==2) {
                    mascara[ct++]=img[n][m];
                    }
                }
            }
    }

    /**
     * Asigna la mascara de tipo <b>diamante</b> para realizar el filtrado:<br>
     *
     * ___X___<br>
     * __XXX__<br>
     * XXXXX<br>
     * __XXX__<br>
     * ___X___<br>
     *
     * @param img la imagen a procesar
     * @param x la coordenada en x
     * @param y la coordenada en y
     * @param alto el alto de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public void asignarMascaraDiamante(int[][] img, int x, int y, int alto,
            int ancho) {
        int ct=0;
        for(int n=0; n<alto; n++) {
            for(int m=0; m<ancho; m++) {
                if(n==0&&m==2) {
                    mascara[ct++]=img[n][m];
                    } 
                else 
                if(n==1&&(m>=1&&m<=ancho-2)) {
                    mascara[ct++]=img[n][m];
                    } 
                else 
                if(n==2) {
                    mascara[ct++]=img[n][m];
                    } 
                else 
                if(n==3&&(m>=1&&m<=ancho-2)) {
                    mascara[ct++]=img[n][m];
                    } 
                else 
                if(n==4&&m==2) {
                    mascara[ct++]=img[n][m];
                    }
                }
            }
    }

    /**
     * Asigna la mascara de tipo <b>equis</b> para realizar el filtrado:<br>
     *
     * X___X<br>
     * _X_X_<br>
     * __X__<br>
     * _X_X_<br>
     * X___X<br>
     *
     * @param img la imagen a procesar
     * @param x la coordenada en x
     * @param y la coordenada en y
     * @param alto el alto de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public void asignarMascaraEquis(int[][] img, int x, int y, int alto,
            int ancho) {
        int ct=0;
        for(int n=0; n<alto; n++) {
            for(int m=0; m<ancho; m++) {
                if(n==0&&(m==0||m==ancho-1)) {
                    mascara[ct++]=img[n][m];
                    } 
                else 
                if(n==1&&(m==1||m==ancho-2)) {
                    mascara[ct++]=img[n][m];
                    } 
                else 
                if(n==2&&m==2) {
                    mascara[ct++]=img[n][m];
                    } 
                else 
                if(n==3&&(m==1||m==ancho-2)) {
                    mascara[ct++]=img[n][m];
                    } 
                else 
                if(n==4&&(m==0||m==ancho-1)) {
                    mascara[ct++]=img[n][m];
                    }
                }
            }
    }

    /**
     * Asigna la mascara de tipo <b>cuadrada</b> para realizar el filtrado:<br>
     *
     * XXXXX<br>
     * XXXXX<br>
     * XXXXX<br>
     * XXXXX<br>
     * XXXXX<br>
     *
     * @param img la imagen a procesar
     * @param x la coordenada en x
     * @param y la coordenada en y
     * @param alto el ancho de la imagen en pixeles
     * @param ancho el alto de la imagen en pixeles
     */
    public void asignarMascaraCuadrada(int[][] img, int x, int y, int alto,
            int ancho) {
        int ct=0;
        for(int n=0; n<alto; n++) {
            for(int m=0; m<ancho; m++) {
                mascara[ct]=img[n][m];
                ct++;
                }
            }
    }

    /**
     * Algoritmo de la burbuja para ordenar los elementos, recibe el arreglo
     * desordenado y devuelve el arreglo ordenado.
     *
     * @param arr el vector a ordenar
     *
     * @return devuelve el vector ordenado
     *
     * @throws ArrayIndexOutOfBoundsException por si se sale de limites
     */
    public int[] ordenarPorBurbuja(int[] arr){
//            throws ArrayIndexOutOfBoundsException {
        boolean intercambio=false;
        for(int pasadas=0; pasadas<arr.length; pasadas++) {
            intercambio=false;
            for(int n=0; n<arr.length-pasadas-1; n++) {
                if(arr[n]>arr[n+1]) {
                    int temporal=arr[n];
                    arr[n]=arr[n+1];
                    arr[n+1]=temporal;
                    intercambio=true;
                    }
                }
            if(intercambio==false) {
                break;
                }
            }
        return arr;
    }
    /**
     * Devuelve la mascara
     * 
     * @return devuelve el vector de vecinos en la mascara
     */
    public int[] getMascara() {
        return mascara;
    }
    /**
     * Pone los elementos de la mascara
     * 
     * @param elementosMascara el numero de elementos de la mascara
     */
    public void setElementosMascara(int elementosMascara) {
        this.elementosMascara = elementosMascara;
        try {
            mascara=new int[elementosMascara];
        } catch(NegativeArraySizeException e) {
            System.out.println(" Error elementosMascara negativo"
                    +" en FiltroNoLineal() "+e);
        }
    }
    /**
     * Pone el nuevo tipo de mascara
     * 
     * @param tipoMascara la seleccion del nuevo tipo de mascara
     */
    public void setTipoMascara(int tipoMascara) {
        this.tipoMascara = tipoMascara;
    }
}
