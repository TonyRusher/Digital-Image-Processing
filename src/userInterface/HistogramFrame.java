/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userInterface;

import Model.Histogram;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author tony
 */
public class HistogramFrame  extends JFrame{
    private HistogramPanel panel;
    private static final String []types = {"","histograma", "histo acum", "histo proba", "histo proba acum"};
    public HistogramFrame(Histogram histograma, Color color, int which, String fileName) {
        super(fileName + types[which]);
        panel = new HistogramPanel(histograma, color, which);
        Container contenedor = this.getContentPane();
        contenedor.setLayout(new BorderLayout());
        contenedor.add(panel, BorderLayout.CENTER);
        this.setSize(400, 210);
        this.setVisible(true);
    }

}
