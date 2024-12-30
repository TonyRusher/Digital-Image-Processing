/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tony
 */
public class Histogram {
    public static final int sz = 256;
    private int [][]data;
    private int height, width;
    private double []histogram;
    private double []acumulativeHistogram;
    private double []probabilityHistogram;
    private double []acumulativeProbabilityHistogram;
    private double media, varianza, asimetria, energia, entropia;
    
    public Histogram(int [][]data){
        this.data = data;
        
        histogram = new double[sz];
        acumulativeHistogram = new double[sz];
        
        probabilityHistogram = new double[sz];
        acumulativeProbabilityHistogram = new double[sz];
        
        this.height = data.length;
        this.width = data[0].length;
        init();
    }
    public void init(){
        calculateHistogram();
        calculateAcumulativeHistogram();
        
        calculateProbabilityHistogram();
        calculateAcumulativeProbabilityHistogram();
        
        calcularMedia();
        calcularVarianza();
        calcularAsimetria();
        calcularEnergia();
        calcularEntropia();
        
    }
    
    private void calculateHistogram(){
       for(int i = 0; i < height; i ++){
           for(int j = 0; j < width; j++){
               int pixel = data[i][j];
               histogram[pixel]++;
           }
       }
    }
    private void calculateAcumulativeHistogram(){
        acumulativeHistogram[0] = histogram[0];
        for(int i = 1; i < sz; i++){
            acumulativeHistogram[i] = acumulativeHistogram[i-1] + histogram[i];
        }
    }
    private void calculateProbabilityHistogram (){
        for(int i = 0; i < sz; i++){
            probabilityHistogram[i] = (double)histogram[i] / (double)(height * width);
        }
    }
    private void calculateAcumulativeProbabilityHistogram(){
        acumulativeProbabilityHistogram[0] = probabilityHistogram[0];
        for(int i = 1; i < sz; i++){
            acumulativeProbabilityHistogram[i] = acumulativeProbabilityHistogram[i-1] + probabilityHistogram[i];
        }
    }
    
    
    private void calculateMedia(){
        media = 0.0;
        double sum = 0.0;
        for(int i = 0; i < sz; i++){
            sum += i * probabilityHistogram[i];
        }
        media = sum;
    }
    private void calcularMedia() {
        media = 0.0;
        double sumatoria = 0.0;
        for(int y=0; y<sz; y++) {
            sumatoria += y*probabilityHistogram[y];
            }
        media = sumatoria;
    }
    private void calcularVarianza() {
        varianza = 0.0;
        double sumatoria = 0.0;
        for(int y=0; y<sz; y++) {
            sumatoria += Math.pow((y-media), 2.0)*probabilityHistogram[y];
            }
        varianza = sumatoria;
    }
    private void calcularAsimetria() {
        asimetria = 0.0;
        double sumatoria = 0.0;
        for(int y=0; y<sz; y++) {
            sumatoria += Math.pow((y-media), 3.0)*probabilityHistogram[y];
            }
        asimetria = sumatoria;
    }
    private void calcularEnergia() {
        energia = 0.0;
        double sumatoria = 0.0;
        for(int y=0; y<sz; y++) {
            sumatoria += Math.pow(probabilityHistogram[y], 2.0);
            }
        energia = sumatoria;
    }
    private void calcularEntropia() {
        entropia = 0.0;
        double sumatoria = 0.0;
        for(int y=0; y<sz; y++) {
            sumatoria += probabilityHistogram[y]*logDos(probabilityHistogram[y]);
            }
        entropia = -sumatoria;
    }
    public double logDos(double x) {
        double numeroUno = Math.log(x);
        if(x==0.0) {
            numeroUno = 1;
            }
        double numeroDos = Math.log(2);
        //System.out.println( x + "\t" + numeroUno + "\t" + numeroDos);
        return numeroUno / numeroDos;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("El histograma es: ").append("\n");
        for(int i=0; i<sz; i++) {
            builder.append("histogram[").append(i).append("]=").append(histogram[i]);
            builder.append(" ");
            if(i%8==0 && i!=0) {
                builder.append("\n");
                }
            }
        builder.append("\n\n");
        builder.append("sza probabilidad del histograma: ").append("\n");
        for(int i=0; i<sz; i++) {
            builder.append("probabilityHistogram[").append(i).append("]=").append(probabilityHistogram[i]);
            builder.append(" ");
            if(i%4==0 && i!=0) {
                builder.append("\n");
                }
            }
        builder.append("\n\n");
        builder.append("Alto imagen: ").append(data.length).append("\n");
        builder.append("Ancho imagen: ").append(data[0].length).append("\n");
        builder.append("La media es: ").append(media).append("\n");
        builder.append("La varianza es: ").append(varianza).append("\n");
        double varianzaDos = Math.sqrt(varianza);
        builder.append("La varianza es: ").append(varianzaDos).append("\n");
        builder.append("La asimetria es: ").append(asimetria).append("\n");
        builder.append("La energia es: ").append(energia).append("\n");
        builder.append("La entropia es: ").append(entropia).append("\n");
        builder.append("\n\n");
        builder.append("El histograma acumulado es: ").append("\n");
        for(int i=0; i<sz; i++) {
            builder.append("acumulativeHistogram[").append(i).append("]=").append(acumulativeHistogram[i]);
            builder.append(" ");
            if(i%8==0 && i!=0) {
                builder.append("\n");
                }
            }
        builder.append("\n\n");
        builder.append("La probabilidad de histograma acumulado es: \n");
        for(int i=0; i<sz; i++) {
            builder.append("acumulativeProbabilityHistogram[").append(i).append("]=").append(acumulativeProbabilityHistogram[i]);
            builder.append(" ");
            if(i%4==0 && i!=0) {
                builder.append("\n");
                }
            }
        return builder.toString();
    }

    public double[] getHistogram() {
        return histogram;
    }

    public void setHistogram(double[] histogram) {
        this.histogram = histogram;
    }

    public double[] getAcumulativeHistogram() {
        return acumulativeHistogram;
    }

    public void setAcumulativeHistogram(double[] acumulativeHistogram) {
        this.acumulativeHistogram = acumulativeHistogram;
    }

    public double[] getProbabilityHistogram() {
        return probabilityHistogram;
    }

    public void setProbabilityHistogram(double[] probabilityHistogram) {
        this.probabilityHistogram = probabilityHistogram;
    }

    public double[] getAcumulativeProbabilityHistogram() {
        return acumulativeProbabilityHistogram;
    }

    public void setAcumulativeProbabilityHistogram(double[] acumulativeProbabilityHistogram) {
        this.acumulativeProbabilityHistogram = acumulativeProbabilityHistogram;
    }
   
}
