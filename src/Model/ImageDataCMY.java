/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author tony
 */
public class ImageDataCMY extends ImageData{
    
    private double currentData[][];
    private int currentChannel;
    
    public ImageDataCMY(double [][][]data, int currentChannel, int format){
        super(data, format);
        this.currentChannel = currentChannel;
        currentData = new double[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                currentData[i][j] = data[currentChannel][i][j];   
            }
        }
    }
    public ImageDataCMY(ImageData image, int currentChannel){
        super(image);
        this.currentChannel = currentChannel;
        currentData = new double[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                currentData[i][j] = data[currentChannel][i][j];   
            }
        }
    }
    
    public ImageDataCMY(ImageData image, int currentChannel, double [][]newData ){
        super(image);
        this.currentChannel = currentChannel;
        this.currentData = newData;
        for(int i = 0; i < height; i++){
            System.arraycopy(currentData[i], 0, data[currentChannel][i], 0, width);
        }
    }
    
    public ImageDataCMY(double [][][]data, int currentChannel, int format, double [][]newData){
        super(data, format);
        this.currentChannel = currentChannel;
        this.currentData = newData;
        for(int i = 0; i < height; i++){
            System.arraycopy(currentData[i], 0, data[currentChannel][i], 0, width);
        }
    }
    
    @Override
    public int[][] getArray(){
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = (int)currentData[i][j];
                if(value < 0) value += 256;
                
                Color color = switch(currentChannel){
                    case 0 -> new Color(0, (int) data[1][i][j], (int) data[2][i][j]);
                    case 1 -> new Color((int) data[0][i][j], 0, (int) data[2][i][j]);
                    case 2 -> new Color((int) data[0][i][j], (int) data[1][i][j], 0);
                    default -> new Color (0,0,0); 
                };
                 mtx[i][j] = color.getRGB();
            }
        }
        return mtx;
    }
    @Override
    public Image getImage(){
        ImageReader ir = new ImageReader(getArray());
        return ir.getImage();
    }
    
}
