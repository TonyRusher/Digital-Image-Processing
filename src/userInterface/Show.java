/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userInterface;

import Model.Histogram;
import Model.ImageData;
import Model.ImageReader;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author tony
 */
public class Show {
    
    private final app mainFrame;
    private final JFrame currentApp;
    
    public Show(app mainFrame, JFrame currentApp){
        this.mainFrame = mainFrame;
        this.currentApp = currentApp;
    }
    
    public ImagePanel drawImage(ImageData image, ImagePanel imagePanel, JPanel containerPanel) {
        if (imagePanel != null) {
            containerPanel.remove(imagePanel);
        }
        imagePanel = new ImagePanel(image.getImageGray().getScaledInstance(350, 350, Image.SCALE_DEFAULT));
        containerPanel.add(imagePanel);
//        System.out.println("image printed");
        currentApp.repaint();
        return imagePanel;

    }

    public HistogramPanel drawHisto(ImageData image, HistogramPanel histogramPanel, JPanel containerPanel, Color color) {

        Histogram histogram1 = new Histogram(image.getChannelInt(image.currentChannel));
        if (histogramPanel != null) {
            containerPanel.remove(histogramPanel);
        }
        try{
             histogramPanel = new HistogramPanel(histogram1, color, 3);
        }catch(Exception e){
            System.out.println("Error aqui" + e);
        }
       
        containerPanel.add(histogramPanel);
        histogramPanel.setVisible(true);
//        histogramPanel.setSize(300, 300);

        currentApp.repaint();
        return histogramPanel;

    }

   

    public ImageData readImage() {
        JFileChooser fileChooser = new JFileChooser();
        switch (fileChooser.showOpenDialog(currentApp)) {
            case JFileChooser.APPROVE_OPTION:
                String fileName = fileChooser.getSelectedFile().getName();
                String path = fileChooser.getSelectedFile().getAbsolutePath();

                ImageReader ir = new ImageReader(path, fileName);
                ImageData img = new ImageData(ir.getPixels());
                return img.toGrayScale();
        }
        return null;
    }

    public void setUpWindow(Window window) {
        Image img = window.getImage2show().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
        ImagePanel ip = new ImagePanel(img);
        window.add(ip);
        window.setVisible(true);
//        window.setSize(img.getHeight(null), img.getWidth(null));
        window.setSize(350, 350);
        window.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                mainFrame.currentWindow = window;
                window.toFront();
                System.out.println("SELECIONE  " + window.getFileName() + "_ecualizada");
                System.out.println(mainFrame.currentWindow.getId());

            }
        });
        mainFrame.getjDesktopPane1().add(window);
    }
    
    public app getMainFrame() {
        return mainFrame;
    }

    public JFrame getCurrentApp() {
        return currentApp;
    }
}
