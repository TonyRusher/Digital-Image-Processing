/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import ColorModels.CMY;
import ColorModels.ColorM;
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
public class ImageData {

    protected double data[][][];
    protected int height, width;
    public int format;
    public int currentChannel;
    
    public ImageData(int mtx[][]) {
        height = mtx.length;
        width = mtx[0].length;

        data = new double[3][height][width];

        for (int i = 0; i < mtx.length; i++) {
            for (int j = 0; j < mtx[0].length; j++) {
                data[0][i][j] = mtx[i][j] >> 16 & 0xFF;
                data[1][i][j] = mtx[i][j] >> 8 & 0xFF;
                data[2][i][j] = mtx[i][j] & 0xFF;
            }
        }
        format = Formats.rgb;
    }
    
    public ImageData(double mtx[][][], int format) {
        data = mtx;
        height = mtx[0].length;
        width = mtx[0][0].length;
        this.format = format;
        
    }
    public ImageData(ImageData image) {
       
        this.currentChannel = image.getCurrChannel();
        this.format = image.getFormat();
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.data = new double[3][height][width];
        double aux[][][] = image.getData();
        for(int i = 0; i < height; i ++){
            for(int j = 0; j < width; j++){
                for(int k = 0; k < 3; k++){
                    this.data[k][i][j] = aux[k][i][j];
                }
            }
        }
        
        
    }
    public double getPixel(int i, int j){
        return ((int)data[0][i][j] <<8)| ((int)data[1][i][j]<<8) | (int)data[2][i][j] ;
    }

    public ImageData toGrayScale() {
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int gray = (int) ((data[0][i][j] + data[1][i][j] + data[2][i][j]) / 3.0);
                mtx[i][j] = gray << 16 | gray << 8 | gray ;
            }
        }
        ImageData grayScaleImage = new ImageData(mtx);
        grayScaleImage.format = Formats.gray;
        
        return new ImageDataByChannels(grayScaleImage, 0);
    }
    public int[][] getArrayInt(){
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mtx[i][j] = (int) data[0][i][j];
            }
        }
        return mtx;
    }
    public int[][] getArray(){
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mtx[i][j] = (int) data[0][i][j] << 16 | (int) data[1][i][j] << 8 | (int) data[2][i][j];
            }
        }
        return mtx;
    }
    public Image getImage() {
        ImageReader ir = new ImageReader(getArray());
        return ir.getImage();
    }
    public int[][] getChannel(int channel){
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = (int) data[channel][i][j];
                if(value < 0) value += 256;
                mtx[i][j] = value << (16 - channel * 8);
            }
        }
        return mtx;
    }
    public int[][] getChannelInt(int channel){
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = (int) data[channel][i][j];
                if(value < 0) value += 256;
                mtx[i][j] = value;
            }
        }
        return mtx;
    }

    public Image getImage(int channel) {
        ImageReader ir = new ImageReader(getChannel(channel));
        return ir.getImage();
    }

    public Image getImageGray(int channel) {
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = (int) data[channel][i][j];
                if(value < 0) value += 256;
//                if(value < 0) value = 0;
//                if(value > 255) value = 255;
//                mtx[i][j] = new Color(value, value, value).getRGB();
                mtx[i][j] = value   << 16 | value << 8 | value;
            }
        }
        ImageReader ir = new ImageReader(mtx);
        return ir.getImage();
    }
    public Image getImageGray() {
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = (int) data[0][i][j];
                if(value < 0) value += 256;
//                if(value < 0) value = 0;
//                if(value > 255) value = 255;
//                mtx[i][j] = new Color(value, value, value).getRGB();
                mtx[i][j] = value   << 16 | value << 8 | value;
            }
        }
        ImageReader ir = new ImageReader(mtx);
        return ir.getImage();
    }
    
    public Image getImageCMY(int channel) {
        int mtx[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = switch(channel){
                    case 0 -> new Color(0, (int) data[1][i][j], (int) data[2][i][j]);
                    case 1 -> new Color((int) data[0][i][j], 0, (int) data[2][i][j]);
                    case 2 -> new Color((int) data[0][i][j], (int) data[1][i][j], 0);
                    default -> new Color (0,0,0); 
                };

                mtx[i][j] = color.getRGB();
            }
        }
        ImageReader ir = new ImageReader(mtx);
        return ir.getImage();
    }
    
    public ImageData rgb2format(int format){
        if(this.format != Formats.rgb) {
            return null;
        }
        
        double ansMtx[][][] = new double[3][height][width];
        for(int i = 0; i < height; i ++){
            for(int j = 0; j < width; j++){
                RGB rgb = new RGB((int)data[0][i][j],(int)data[1][i][j], (int)data[2][i][j] );
                ColorM ans = rgb.rgb2format(format);
                ansMtx[0][i][j] = ans.getC0();
                ansMtx[1][i][j] = ans.getC1();
                ansMtx[2][i][j] = ans.getC2();
//                System.out.println("" + ansMtx[0][i][j] + ", " + + ansMtx[1][i][j] + ", " + ansMtx[2][i][j] + ", ");
            }
        }
        return new ImageData(ansMtx, format);
    }
    
    public ImageData format2rgb(){
        if(this.format == Formats.rgb) return null;
        double ansMtx[][][] = new double[3][height][width];
        for(int i = 0; i < height; i ++){
            for(int j = 0; j < width; j++){
                double c0 = data[0][i][j];
                double c1 = data[1][i][j];
                double c2 = data[2][i][j];

                RGB ans = switch(format){
                    case Formats.cmy -> CMY.toRGB(c0,c1,c2);
                    case Formats.yiq -> YIQ.toRGB(c0,c1,c2);
                    case Formats.hsi -> HSI.toRGB(c0,c1,c2);
                    case Formats.hsv -> HSV.toRGB(c0,c1,c2);
                    case Formats.lab -> LAB.toRGB(c0,c1,c2);
                    default -> null;
                };
                ansMtx[0][i][j] = ans.getC0();
                ansMtx[1][i][j] = ans.getC1();
                ansMtx[2][i][j] = ans.getC2();
            }
        }
        return new ImageData(ansMtx, Formats.rgb);
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getCurrChannel() {
        return currentChannel;
    }

    public void setCurrChannel(int currChannel) {
        this.currentChannel = currChannel;
    }

    public double[][][] getData() {
        return data;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getFormat() {
        return format;
    }

    @Override
    public String toString() {
        System.out.println("PIXELES : ==============");
        for(int i = 0; i < 5; i ++){
            for(int j = 0; j < 5; j++){
                System.out.println("[" + data[0][i][j] + ", " + + data[1][i][j] + ", " + data[2][i][j] + "], ");
            }
        }
        System.out.println("==============");

        return "ImageData{" + '}';
    }
    
    
}
