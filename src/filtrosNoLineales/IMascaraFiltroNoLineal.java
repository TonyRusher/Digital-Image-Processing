/**
 * Paquete al que pertenece la clase.
 */
package filtrosNoLineales;

/**
 * Interface utilizada para almacenar los tipos de mascara que se puede utilizar
 * para el Filtrado no lineal de la imagen:
 * <br>1. Fila
 * <br>2. Columna
 * <br>3. Diamante
 * <br>4. Cruz
 * <br>5. Equis
 * <br>6. Cuadrado
 *
 * @author Saul De La O Torres.
 * @version 1.0 21 Septiembre de 2003.
 */
public interface IMascaraFiltroNoLineal {

    /**
     * Tipo de mascara <b>FILA</b>.
     */
    int FILA = 1;
    /**
     * Tipo de mascara <b>COLUMNA</b>.
     */
    int COLUMNA = 2;
    /**
     * Tipo de mascara <b>DIAMANTE</b>.
     */
    int DIAMANTE = 3;
    /**
     * Tipo de mascara <b>CRUZ</b>.
     */
    int CRUZ = 4;
    /**
     * Tipo de mascara <b>EQUIS</b>.
     */
    int EQUIS = 5;
    /**
     * Tipo de mascara <b>CUADRADO</b>.
     */
    int CUADRADO = 6;
}