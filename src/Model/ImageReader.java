/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import ColorModels.ColorM;
// import javax.swing.JFrame;
// import java.awt.image.MemoryImageSource;

/**
 *
 * @author tony
 */
public class ImageReader {

    public BufferedImage bufferedImage;
    int height, width;
    String fileName;
    String path;
    int[][] pixels;

    public ImageReader(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
        readBuffered();
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        setPixelsFromBuffered();
    }

    public ImageReader(ImageReader currentImage) {
        this.path = currentImage.path;
        this.fileName = currentImage.fileName;
        this.bufferedImage = currentImage.bufferedImage;
        this.pixels = currentImage.pixels;
        this.height = currentImage.height;
        this.width = currentImage.width;
    }

     public ImageReader(BufferedImage bufferedImage) {
        this.path = "null";
        this.fileName = "NuevaImagen";
        this.bufferedImage = bufferedImage;
        this.pixels = null;
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
        setPixelsFromBuffered();
        
    }
    public ImageReader(int[][] mtx) {
        fileName = "newImage.jpg";
        path = "";
        createBufferedImage(mtx);
    }

    public void createBufferedImage(int[][] mtx) {
        height = mtx.length;
        width = mtx[0].length;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                bufferedImage.setRGB(j, i, mtx[i][j]);
            }
        }
    }

    private void readBuffered() {
        bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new FileInputStream(new File(path )));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void setPixelsFromBuffered() {
        pixels = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = bufferedImage.getRGB(j, i);
            }
        }
    }
    

    public void writeImage(String path, String fileName, String newName) {
        try {
            ImageIO.write(bufferedImage, "jpg", new File(path + fileName + newName));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public Image getImage() {
        // Image image = null;
        // JFrame frame = new JFrame();
        // image = frame.createImage(new MemoryImageSource(width, height, Convert2DTo1D(this.mtx) , 0, width));
        // return image;
        return bufferedImage.getScaledInstance(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.SCALE_DEFAULT);
    }

    public int[] Convert2DTo1D(int[][] mtx) {
        int[] array = new int[height * width];
        int k = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[k++] = mtx[i][j];
            }
        }
        return array;
    }

    public ImageReader splitChannels(int channel) {
        return this;
    }

    public int[][] getPixels() {
        return pixels;
    }

    public void setPixels(int[][] pixels) {
        this.pixels = pixels;
    }

}
