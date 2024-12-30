/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FiltrosLineales;

/**
 *
 * @author tony
 */
public class Convolution {
    
    public static double[][] applyConvolution(int[][] image, double[][] mask, boolean normalizar) {
        int imageHeight = image.length;
        int imageWidth = image[0].length;
        int maskHeight = mask.length;
        int maskWidth = mask[0].length;
        
        // Initialize the output array with the same size as the input image
        double[][] output = new double[imageHeight][imageWidth];
        
        // Calculate the padding size
        int padHeight = maskHeight / 2;
        int padWidth = maskWidth / 2;
        
        // Iterate over each pixel in the image
        for (int i = padHeight; i < imageHeight - padHeight; i++) {
            for (int j = padWidth; j < imageWidth - padWidth; j++) {
                double sum = 0.0;
                
                // Apply the convolution operation
                for (int k = 0; k < maskHeight; k++) {
                    for (int l = 0; l < maskWidth; l++) {
                        int imageX = i + k - padHeight;
                        int imageY = j + l - padWidth;
                        sum += image[imageX][imageY] * mask[k][l];
                    }
                }
                
                // Store the result in the output array
                if(!normalizar){
                    if(sum > 255) sum = 255;
                    if(sum < 0) sum = 0;
                }
               
                output[i][j] = sum;
            }
        }
        
        // Normalize the output values to the range 0 to 255
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        
        // Find the minimum and maximum values in the output
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                if (output[i][j] < min) {
                    min = output[i][j];
                }
                if (output[i][j] > max) {
                    max = output[i][j];
                }
            }
        }
        
        // Normalize the output values
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                output[i][j] = 255 * (output[i][j] - min) / (max - min);
            }
        }
        
        return output;
    }
}
