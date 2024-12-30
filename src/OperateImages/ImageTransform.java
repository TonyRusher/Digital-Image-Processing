/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OperateImages;

import Model.ImageData;
import Model.ImageDataByChannels;

/**
 *
 * @author tony
 */
public class ImageTransform {
    private ImageData image1;
    private int []lut;
    private boolean inverso;
    
    public ImageTransform(ImageData image1, int[] lut, boolean inverso) {
        this.image1 = image1;
        this.lut = lut;
        this.inverso = inverso;
    }
    
    public ImageData getImage(){
        int height = image1.getHeight();
        int width = image1.getWidth();
        double mtx[][] = new double[height][width];
        int data[][] = image1.getArrayInt();
        
        for(int i = 0 ; i < height; i ++){
            for(int j =0; j < width; j++){
                mtx[i][j] = (inverso)? 255 - lut[data[i][j]]:lut[data[i][j]] ;
            }
        }
        return new ImageDataByChannels(image1, image1.getCurrChannel(), mtx);
    }
        
    
}
