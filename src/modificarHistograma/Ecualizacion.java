/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modificarHistograma;

import Model.Histogram;
import Model.ImageData;
import Model.ImageDataByChannels;

/**
 *
 * @author tony
 */
public class Ecualizacion {

    private ImageData imagen;
    private Histogram histogram;
    private double[] histo;
    private double[] dp;
    private double[] lut;
    private int[] lutAns;
    private double maxx = 0;
    private double minn = 255;

    public Ecualizacion(ImageData imagen, int fmin, int fmax) {
        this.imagen = imagen;
        lut = new double[256];
        histogram = new Histogram(imagen.getArrayInt());
        histo = histogram.getHistogram();
        dp = histogram.getAcumulativeProbabilityHistogram();
        maxx = fmax;
        minn = fmin;
        minn += 0.00001;
        maxx -= 0.001;     
    }

    public void ecAcumulative() {
        
        for (int i = 0; i < 256; i++) {
            lut[i] = dp[i] * 255;
        }
    }

    public void ecUniform() {
        for (int i = 0; i < 256; i++) {
            lut[i] = (maxx - minn) * dp[i] - minn;
        }
    }

    public void ecExponencial(double alpha) {
        for (int i = 0; i < 256; i++) {
            lut[i] = minn - (1 / alpha) * Math.log(1 - dp[i]);
        }
    }

    public void ecRayleigh(double alpha) {
        for (int i = 0; i < 256; i++) {
            lut[i] = minn + Math.sqrt(2) * alpha * Math.sqrt(-Math.log(1 - dp[i]));
        }
    }

    public void ecHiperbolic(double pot) {
        for (int i = 0; i < 256; i++) {
            lut[i] = Math.pow((Math.pow(maxx, 1 / pot) - Math.pow(minn, 1 / pot)) * dp[i] + Math.pow(minn, (1 / pot)), pot);
        }
    }

    public void ecLogaritmic() {
        for (int i = 0; i < 256; i++) {
            lut[i] = minn * Math.pow((maxx / minn), dp[i]);
        }
    }

    public void calculateLut() {
        lutAns = new int[256];
        for (int i = 0; i < 256; i++) {
            lutAns[i] = (int) lut[i];
            if(lutAns[i] > 255) lutAns[i] = 255;
            if(lutAns[i] < 0) lutAns[i] = 0;
        }
    }
	
    public ImageData applyLut() {
        double [][] imgAns = new double[imagen.getHeight()][imagen.getWidth()];
        calculateLut();
        for (int i = 0; i < imagen.getHeight(); i++) {
                for (int j = 0; j < imagen.getWidth(); j++) {
                        imgAns[i][j] = lutAns[(int)imagen.getPixel(i, j)];
                }
        }
        return new ImageDataByChannels(imagen, imagen.currentChannel, imgAns);
    }

    public ImageData getImagen() {
            return imagen;
    }

    public void setImagen(ImageDataByChannels imagen) {
            this.imagen = imagen;
    }

    public Histogram getHistogram() {
            return histogram;
    }

    public void setHistogram(Histogram histogram) {
            this.histogram = histogram;
    }

    public double[] getHisto() {
            return histo;
    }

    public void setHisto(double[] histo) {
            this.histo = histo;
    }

    public double[] getDp() {
            return dp;
    }

    public void setDp(double[] dp) {
            this.dp = dp;
    }

    public double[] getLut() {
            return lut;
    }

    public void setLut(double[] lut) {
            this.lut = lut;
    }

    public int[] getLutAns() {
            return lutAns;
    }

    public void setLutAns(int[] lutAns) {
            this.lutAns = lutAns;
    }

    public double getMaxx() {
            return maxx;
    }

    public void setMaxx(double maxx) {
            this.maxx = maxx;
    }

    public double getMinn() {
            return minn;
    }

    public void setMinn(double minn) {
            this.minn = minn;
    }

//	public static int[] ecualizarHistograma(int[] histograma, int totalPixeles){
//		int[] nuevoHistograma = new int[256];
//		int[] nuevoHistogramaAcumulado = new int[256];
//		int[] nuevoHistogramaEcualizado = new int[256];
//		
//		// Calculamos el histograma acumulado
//		nuevoHistogramaAcumulado[0] = histograma[0];
//		for(int i = 1; i < 256; i++){
//			nuevoHistogramaAcumulado[i] = nuevoHistogramaAcumulado[i-1] + histograma[i];
//		}
//		
//		// Calculamos el histograma ecualizado
//		for(int i = 0; i < 256; i++){
//			nuevoHistogramaEcualizado[i] = (int) Math.round((255.0 / totalPixeles) * nuevoHistogramaAcumulado[i]);
//		}
//		
//		return nuevoHistogramaEcualizado;
//	}
//	
//	public static int[] ecualizarImagen(int[] imagen, int[] histogramaEcualizado){
//		int[] nuevaImagen = new int[imagen.length];
//		
//		for(int i = 0; i < imagen.length; i++){
//			nuevaImagen[i] = histogramaEcualizado[imagen[i]];
//		}
//		
//		return nuevaImagen;
//	}
//	
//	public static int[] ecualizarImagen(int[] imagen, int[] histograma, int totalPixeles){
//		int[] histogramaEcualizado = ecualizarHistograma(histograma, totalPixeles);
//		return ecualizarImagen(imagen, histogramaEcualizado);
//	}
//	
//	public static int[] ecualizarImagen(int[] imagen, int totalPixeles){
//		int[] histograma = Histograma.calcularHistograma(imagen);
//		return ecualizarImagen(imagen, histograma, totalPixeles);
//	}
//	
//	public static int[] ecualizarImagen(int[] imagen){
//		int totalPixeles = imagen.length;
//		return ecualizarImagen(imagen, totalPixeles);
//	}
//	
//	public static int[] ecualizarImagen(int[] imagen, int[] histograma){
//		int totalPixeles = 0;
//		for(int i = 0; i < histograma.length; i++){
//			totalPixeles += histograma[i];
//		}
//		return ecualizarImagen(imagen, histograma, totalPixeles);
//	}
}
