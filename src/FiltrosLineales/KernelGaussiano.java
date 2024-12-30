/**
 * Paquete al que pertenece la clase
 */
package FiltrosLineales;

/**
 * Crea un Kernel de tipo Gaussiano para convolucionar con una imagen.
 *
 * @author Saul De La O Torres
 * @version 1.1 [1999/07/29]
 */
public class KernelGaussiano {
    /**
     * Kernel creado por la clase para una dimension.
     */
    private double [] mascara;
    /**
     * Kernel creado por la clase para dos dimensiones.
     */
    private double [][] mascara2D;
    /**
     * Desviacion estandar para calcular el Kernel Gaussiano.
     */
    private double sigma;
    /**
     * Kernel gaussiano de 3 x 3
     */
    private final KernelD GAUSSION_3x3 = new KernelD( new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.1250, 0.250, 0.1250},
            {0.0625, 0.125, 0.0625}
            });
    /**
     * Kernel gaussiano de 5 x 5
     */
    private final KernelD GAUSSION_5x5 = new KernelD( new double[][] {
            {0.00390625, 0.015625, 0.0234375, 0.015625, 0.00390625},
            {0.01562500, 0.062500, 0.0937500, 0.062500, 0.01562500},
            {0.02343750, 0.093750, 0.1406250, 0.093750, 0.02343750},
            {0.01562500, 0.062500, 0.0937500, 0.062500, 0.01562500},
            {0.00390625, 0.015625, 0.0234375, 0.015625, 0.00390625}
            });
    private KernelD kernel;
    /**
     * Crea un Kernel Gaussiano con desviacion estandar de 1.0.
     */
    public KernelGaussiano() {
        this(1.0);
    }

    /**
     * Creaa un Kernel Gaussian con la desviacion estandar especificada.
     *
     * @param sigma desviacion estandar.
     */
    public KernelGaussiano(double sigma) {
        this.sigma = sigma;
        kernel = null;
    }

    /**
     * Pone el valor de la desviacion estandar.
     *
     * @param sigma el nuevo valor de la desviasion estandar
     */
    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    /**
     * Calcula el tamanio del kernel para una desviacion estandar dada.
     *
     * @param sigma desviacion estandar.
     *
     * @return tamanio del kernel tamanio, en pixeles.
     */
    public int getTamanio(double sigma) {
        int radio = (int) Math.ceil(4.0 * sigma);
        return 2 * radio + 1;
    }

    /**
     * Crea un arreglo de muestras para la mascara de la funcion Gaussiana en
     * dos dimensiones con la desviacion estandar dada.
     *
     * @return arreglo de muestras.
     */
    public double[] calcularKernel() {
        int n = (int) Math.ceil(4.0 * sigma);
        int tamanio = 2 * n + 1;
        mascara = new double[tamanio * tamanio];
        double r, s = 2.0 * sigma * sigma;
        double norm = 0.0;
        int i = 0;
        for (int y = -n; y <= n; ++y) {
            for (int x = -n; x <= n; ++x, ++i) {
                r = Math.sqrt(x * x + y * y);
                mascara[i] = Math.exp(-r * r / s);
                norm += mascara[i];
            }
        }
        for (i = 0; i < tamanio * tamanio; ++i) {
            mascara[i] /= norm;
        }
        return mascara;
    }

    /**
     * Crea un arreglo de muestras para la mascara de la funcion Gaussiana en
     * dos dimensiones con la desviacion estandar dada.
     *
     * @param sigma desviacion estandar.
     * @param K constante de la Gaussiana
     *
     * @return arreglo de muestras.
     */
    public double[][] calcularKernel(double sigma, double K) {
        this.setSigma(sigma);
        int n = (int) Math.ceil(4.0 * sigma);
        int tamanio = 2 * n + 1;
        mascara2D = new double[tamanio][tamanio];

        double r, s = 2.0 * sigma * sigma;
        double norm = 0.0;
        int i = 0;
        for (int y = -n; y <= n; ++y) {
            if (mascara2D[y + n] == null) {
                mascara2D[y + n] = new double[tamanio];
            }
            for (int x = -n; x <= n; ++x) {
                r = Math.sqrt(x * x + y * y);
                mascara2D[y + n][x + n] = K * Math.exp(-r * r / (s * s));
                mascara2D[y + n][x + n] = 
                        normalizarDouble(mascara2D[y + n][x + n]);
                if (mascara2D[y + n][x + n] < 0.001) {
                    mascara2D[y + n][x + n] = 0.0;
                }
                //norm += mascara2D[y+n][x+n];
            }
        }

        //for( int y=-n; y<=n; ++y )
        //	for( int x=-n; x<=n; ++x ) mascara2D[y+n][x+n] /= norm;
        return mascara2D;
    }

    /**
     * Imprime el kernel.
     * 
     * @return devuelve la mascara
     */
    @Override
    public String toString() {
        String mascaraStr = "";
        for (double[] mascara2D1 : mascara2D) {
            for (int m = 0; m < mascara2D1.length; m++) {
                mascaraStr += " " + normalizarDouble(mascara2D1[m]);
            }
            mascaraStr += "\n";
        }
        return mascaraStr;
    }
    /**
     * Normaliza un double
     * 
     * @param d el numero a normalizar
     * 
     * @return devuelve el numero normalizado 
     */
    public double normalizarDouble(double d) {
        d *= 100000.0;
        d = Math.round(d);
        d /= 100000.0;
        return d;
    }
    /**
     * Devuelve el kermel gaussiano
     * @param ksize el tamanio del kernel
     * @return devuelve el kernel
     */
    public KernelD getGaussionKernel(int ksize) {
        assert(ksize % 2 == 1 && ksize >= 3 && ksize <= 101);

        if(ksize == 3) {
            kernel  = GAUSSION_3x3;
            } 
        else 
        if(ksize == 5) {
            kernel = GAUSSION_5x5;
            } 
        else {
            // How to determine sigma : 
            double sigma = 0.3 * ((ksize - 1) * 0.5 - 1) + 0.8;
            kernel = new KernelD(ksize);
            double c = (ksize / 2);
            double sums = 0;
            double [][] valores = new double[ksize][ksize];
            for(int i = 0; i < ksize; i++) {
                for(int j = 0; j < ksize; j++) {
                    double valor = Math.exp(
                                  -(Math.pow(i - c, 2.) + 
                                    Math.pow(j - c, 2)) / 0.5 / 
                                    Math.pow(sigma, 2.));
                    valores[i][j] = valor;
                    sums += valor;
                    }
                }
            for(int i = 0; i < ksize; i++) {
                for (int j = 0; j < ksize; j++) {
                    valores[i][j] /= sums;
                    }
                }
            kernel.setKernel(valores);
            }
        return kernel;
    }
    
    public double [][] calcularKernel(int tam, double sigma) {
        double [][] kernel = new double[tam][tam];
        for(int y=-tam/2, j=0; y<=tam/2; y++, j++) {
            for(int x=-tam/2, i=0; x<=tam/2; x++, i++) {
                double suma = Math.pow(x, 2.0) + Math.pow(y, 2.0);
                double divisor = 2.0 * sigma * sigma;
                double division = suma / divisor;
                System.out.println(division);
                kernel[j][i] = redondear(Math.pow(Math.E, -division));
                }
            }
        return kernel;
    }
    public double redondear(double valor) {
        valor *= 10000.0;
        valor = Math.round(valor);
        valor /= 10000.0;
        return valor;
    }
    /**
     * Creates a GaussianKernel and writes its coefficients to standard output.
     *
     * @param argv los argumentos del main
     */
//    public static void main(String[] argv) {
//        double sigma = 0.7;
//        if (argv.length > 0) {
//            sigma = Float.parseFloat(argv[0]);
//        }
//        KernelGaussiano kernel = new KernelGaussiano(sigma);
//        double[][] kernelGauss = kernel.calcularKernel(sigma, 2.0);
//        System.out.println(kernel);
//        System.out.println(" tamanio del kernel " + kernel.getTamanio(sigma));
//        int tam = 5;
//        double sigmaDos = 4.0;
//        double [][] elKernel = kernel.calcularKernel(tam, sigmaDos);
//        double sumatoria = 0.0;
//        for(int y=0; y<tam; y++) {
//            for(int x=0; x<tam; x++) {
//                System.out.print(" " + elKernel[y][x]);
//                sumatoria += elKernel[y][x];
//                }
//            System.out.println();
//            }
//        double c = 1.0 / sumatoria;
//        System.out.println("s = " + kernel.redondear(sumatoria));
//        System.out.println("c = " + kernel.redondear(c));
//    }
}