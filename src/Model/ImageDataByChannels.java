/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import ColorModels.CMY;
import ColorModels.HSI;
import ColorModels.HSV;
import ColorModels.LAB;
import ColorModels.RGB;
import ColorModels.YIQ;
import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author tony
 */
public class ImageDataByChannels extends ImageData {

    private double currentData[][];

    public ImageDataByChannels(double[][][] data, int currentChannel, int format) {
        super(data, format);
        this.currentChannel = currentChannel;
        currentData = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                currentData[i][j] = data[currentChannel][i][j];
            }
        }
    }

    public ImageDataByChannels(ImageData image, int currentChannel) {
        super(image);
        this.currentChannel = currentChannel;
        currentData = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                currentData[i][j] = data[currentChannel][i][j];
            }
        }
    }

    public ImageDataByChannels(ImageData image, int currentChannel, double[][] newData) {
        super(image);
        this.currentChannel = currentChannel;
        this.currentData = newData;
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++){
                this.data[currentChannel][i][j] = newData[i][j];
            }
        }
    }

    public ImageDataByChannels(double[][][] data, int currentChannel, int format, double[][] newData) {
        super(data, format);
        this.currentChannel = currentChannel;
        this.currentData = newData;
        for (int i = 0; i < height; i++) {
            System.arraycopy(currentData[i], 0, data[currentChannel][i], 0, width);
        }
    }

    @Override
    public int[][] getArrayInt() {
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mtx[i][j] = (int) currentData[i][j];
            }
        }
        return mtx;
    }

    @Override
    public int[][] getArray() {
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mtx[i][j] = (int) currentData[i][j];
            }
        }
        return mtx;
    }

    @Override
    public Image getImage() {
        int mtx[][] = new int[height][width];
        double max = -10000, min = 10000;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double value = currentData[i][j];
                max = Math.max(max, value);
                min = Math.min(min, value);
            }
        }
        double sz = max - min;//sz = 255
        //value = 

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
//                int value;
//                if(sz > 255) 
                int value = (int) (((currentData[i][j] - min) * 255.0) / sz);
//                else value = (int) currentData[i][j];
                if (value > 255 || value < 0) {
                    System.out.println("Value === " + value);
                }
//                int value = (int) mtx[i][j];
//                while(value < 0) value += 256;
//                if(value > 255) value = 255;
                Color color = switch (currentChannel) {
                    case 0 ->
                        format == Formats.cmy ? new Color(0, value, value) : new Color(value, 0, 0);
                    case 1 ->
                        format == Formats.cmy ? new Color(value, 0, value) : new Color(0, value, 0);
                    case 2 ->
                        format == Formats.cmy ? new Color(value, value, 0) : new Color(0, 0, value);
                    default ->
                        new Color(value, value, value);
                };
                if (format == Formats.gray) {
                    color = new Color(value, value, value);
                }
                mtx[i][j] = color.getRGB();
            }
        }
        ImageReader ir = new ImageReader(mtx);
        return ir.getImage();
    }

    @Override
    public Image getImageGray() {
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = (int) data[currentChannel][i][j];
                while (value < 0) {
                    value += 256;
                }
                if (value > 255) {
                    value = 255;
                }
                mtx[i][j] = new Color(value, value, value).getRGB();
            }
        }
        ImageReader ir = new ImageReader(mtx);
        return ir.getImage();
    }
    @Override
    public double getPixel(int i, int j){
        return currentData[i][j];
    }
    public ImageData format2rgb(){
        if(this.format == Formats.rgb) return null;
        double ansMtx[][][] = new double[3][height][width];
        for(int i = 0; i < height; i ++){
            for(int j = 0; j < width; j++){
                double c[] = {data[0][i][j], data[1][i][j], data[2][i][j]};
                c[currentChannel] = currentData[i][j];

                RGB ans = switch(format){
                    case Formats.cmy -> CMY.toRGB(c[0],c[1],c[2]);
                    case Formats.yiq -> YIQ.toRGB(c[0],c[1],c[2]);
                    case Formats.hsi -> HSI.toRGB(c[0],c[1],c[2]);
                    case Formats.hsv -> HSV.toRGB(c[0],c[1],c[2]);
                    case Formats.lab -> LAB.toRGB(c[0],c[1],c[2]);
                    default -> new RGB(255,0,0);
                };
                
                ansMtx[0][i][j] = ans.getC0();
                ansMtx[1][i][j] = ans.getC1();
                ansMtx[2][i][j] = ans.getC2();
            }
        }
        return new ImageData(ansMtx, Formats.rgb);
    }
//    
//    @Override
//    public int[][] getChannel(int channel){
//        int mtx[][] = new int[height][width];
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                mtx[i][j] = ((int) data[channel][i][j]) << (16 - channel * 8);
//            }
//        }
//        return mtx;
//    }
//    @Override
//    public int[][] getChannelInt(int channel){
//        int mtx[][] = new int[height][width];
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                mtx[i][j] = ((int) data[channel][i][j]);
//            }
//        }
//        return mtx;
//    }
}
