/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userInterface;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author tony
 */
public class ImagePanel extends JPanel{
    private Image image;
    public int nada;
    public ImagePanel(Image image){
        super();
        this.image = image;
        this.setSize(image.getWidth(null), image.getHeight(null));
        
    }
    
    public void addImage(Image image){
        this.image = image;
        repaint();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, 0, 0, this);
        }
    }
}
