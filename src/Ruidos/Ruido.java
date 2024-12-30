/**
 * Paquete al que pertenece la clase.
 */
package Ruidos;

/**
 * Clase Ruido general que contiene las variables necesarias utilizadas por las
 * diferentes clases de ruido derivados
 *
 * @author Saul De La O Torres
 * @version 1.0 14 de Julio de 2003
 */
public class Ruido {
    /**
     * Variable de tipo double que almacena la varianza del ruido.
     */
    private double varianza;
    /**
     * Variable de tipo double que almacena la media del ruido.
     */
    private double media;
    /**
     * Altura de la imagen.
     */
    private int alto;
    /**
     * Anchura de la imagen.
     */
    private int ancho;
    /**
     * Calcula el producto d2 por PI.
     */
    private final double dosPI;
    /**
     * Buffer de una dimension de la imagen que se va a degradar.
     */
    private int[] imagenConRuido1D;
    /**
     * Buffer de dos dimensiones de la imagen que se va a degradar.
     */
    private int[][] imagenConRuido2D;

    /**
     * El Constructor solo recibe la altura y el ancho de la imagen a la que se
     * degrada con ruido.
     *
     * @param alto la altura de la imagen en pixeles
     * @param ancho el ancho de la imagen en pixeles
     */
    public Ruido(int alto, int ancho) {
        this.alto = alto;
        this.ancho = ancho;
        dosPI = 2.0 * Math.PI;
    }
    /**
     * Devuelve la varianza
     * @return devuelve varianza
     */
    public double getVarianza() {
        return varianza;
    }
    /**
     * Pone la varianza
     * @param varianza la nueva varianza
     */
    public void setVarianza(double varianza) {
        this.varianza = varianza;
    }
    /**
     * Devuelve la media
     * @return devuelve media
     */
    public double getMedia() {
        return media;
    }
    /**
     * Pone la media
     * @param media la nueva media
     */
    public void setMedia(double media) {
        this.media = media;
    }
    /**
     * Devuelve el alto
     * @return devuelve alto
     */
    public int getAlto() {
        return alto;
    }
    /**
     * Pone el alto de la imah=gen
     * @param alto el nuevo alto
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }
    /**
     * Devuelve el ancho
     * @return devuelve ancho
     */
    public int getAncho() {
        return ancho;
    }
    /**
     * Pone el ancho de la imah=gen
     * @param ancho el nuevo alto
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
    /**
     * Devuelve dosPI
     * @return devuelve dosPI
     */
    public double getDosPI() {
        return dosPI;
    }
    /**
     * Devuelve la imagen con ruido
     * @return devuelve imagenConRuido1D
     */
    public int[] getImagenConRuido1D() {
        return imagenConRuido1D = new int[alto * ancho];
    }
    /**
     * Pone la imagen con ruido
     * @param imagenConRuido1D la nueva imagen
     */
    public void setImagenConRuido1D(int[] imagenConRuido1D) {
        this.imagenConRuido1D = imagenConRuido1D;
    }
    /**
     * Devuelve la imagen con ruido
     * @return devuelve imagenConRuido2D
     */
    public int[][] getImagenConRuido2D() {
        return imagenConRuido2D = new int[alto][ancho];
    }
    /**
     * Pone la imagen con ruido
     * @param imagenConRuido2D la nueva imagen
     */
    public void setImagenConRuido2D(int[][] imagenConRuido2D) {
        this.imagenConRuido2D = imagenConRuido2D;
    }
    
}
