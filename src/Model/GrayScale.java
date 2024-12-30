/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tony
 */
public class GrayScale extends ImageReader {
    int [][]mtx;
    public GrayScale(String path, String fileName) {
        super(path, fileName);
        convert2GrayScale();
    }
    public GrayScale(ImageReader image){
        super(image);
        convert2GrayScale();
    }
    
    private void convert2GrayScale(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
				int pixel = bufferedImage.getRGB(j, i);
				int c1 = (pixel >> 16) & 0xff;
				int c2 = (pixel >> 8) & 0xff;
				int c3 = pixel & 0xff;
                mtx[i][j] = (int)((c1 + c2 + c3)/ 3);
            }
        }
		createBufferedImage(mtx);
    }
    
}
