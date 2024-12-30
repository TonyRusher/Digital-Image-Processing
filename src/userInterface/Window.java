/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userInterface;

import Model.ImageData;
import java.awt.Image;
import javax.swing.JInternalFrame;

/**
 *
 * @author tony
 */
public class Window extends JInternalFrame {
    private Image image2show;
    private ImageData id;
    String fileName;
    public int idWindow;
    public Window(ImageData id, String fileName){
        super(fileName, true, true, true, true);
        this.fileName = fileName;
        this.id = id;
        this.image2show = id.getImage();
        
    }
    

    public Image getImage2show() {
        return image2show;
    }

    public void setImage2show(Image image2show) {
        this.image2show = image2show;
    }

    public ImageData getId() {
        return id;
    }

    public void setId(ImageData id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Window{" + "image2show=" + image2show + ", id=" + id + ", fileName=" + fileName + '}';
    }
    
}
