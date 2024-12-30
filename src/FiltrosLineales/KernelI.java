/**
 * Paquete al que pertenece la clase.
 */
package FiltrosLineales;

/**
 * Clase que almacena valores de mascara para convolucionar tipo entero
 * 
 * @author sdelaot
 */
public class KernelI {
    /**
     * Los valores que almacena el kernel
     */
    private int [][] valores;
    /**
     * Crea un kernel de algun tamanio
     * 
     * @param tam el tamanio debe ser impar mayor o igual a tres
     */
    public KernelI(int tam) {
        this(new int[tam][tam]);
    }
    /**
     * Crea un kernel con todos sus valores
     * 
     * @param valores los valores
     */
    public KernelI(int [][] valores) {
        this.valores = valores;
    }
    /**
     * Pone los valores del kernel
     * 
     * @param valores todos los valores
     */
    public void setKernel(int [][] valores) {
        this.valores = valores;
    }
    /**
     * Devuelve un valor del kernel
     * 
     * @param x la coordenada en x en el kernel
     * @param y la coordenada en y en el kernel
     * @return devuelve el valor en esa coordenada
     */
    public int getValor(int x, int y) {
        if(y>=0 && y<valores.length) {
            if(x>=0 && x<valores[0].length) {
                return valores[y][x];
                }
            }
        return Integer.MAX_VALUE;
    }
    /**
     * Pone o cambia un valor del kernel
     * 
     * @param x la coordenada en x en el kernel
     * @param y la coordenada en y en el kernel
     * @param valor el nuevo valor que se va a cambiar
     */
    public void setValor(int x, int y, int valor) {
        if(y>=0 && y<valores.length) {
            if(x>=0 && x<valores[0].length) {
                valores[y][x] = valor;
                return;
                }
            }
        System.out.println("");
    }
    /**
     * Devuelve los valores del kernel
     * 
     * @return devuelve valores
     */
    public int[][] getValores() {
        return valores;
    }
    
    /**
     * Devuelve el kernel
     * 
     * @return devuelve el kernel para sacarlo a pantalla 
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int y=0; y<valores.length; y++) {
            for(int x=0; x<valores[0].length; x++) { 
                builder.append(valores[y][x]).append(" ");
                }
            builder.append("\n");
            }
        return builder.toString();
    }
}
