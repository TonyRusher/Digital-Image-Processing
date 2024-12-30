/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userInterface;

import Model.Histogram;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author tony
 */
public class HistogramPanel extends JPanel {

    private Histogram histogram;
    private Color color;
    private int type;

    public HistogramPanel(Histogram histogram, Color color, int type) {
        this.histogram = histogram;
        this.color = color;
        this.type = type;
        this.setOpaque(true);
        this.setSize(350, 200);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g); //pinta el fondo
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        
//        g2d.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f));
        //Pone la imagen en su tamanio normal.
        
        if (histogram != null) {
            drawHistogram(g, type);
            drawPlane(g);
        }
//        this.setBackground(Color.PINK);
    }

    private void drawPlane(Graphics g) {
        g.setColor(Color.BLACK);
        // linea horizontal X de los bins
        g.drawLine(46, 140, 360, 140);
        // linea vertical Y de pi
        g.drawLine(48, 16, 48, 144);
        g.drawLine(46, 20, 54, 20);
        g.drawLine(46, 80, 54, 80);

        // dibuja el histograma
    }

    private void drawHistogram(Graphics g, int which) {
        // iniciamos el maximo
        double maxPi = -1.0;
        // tomamos L
        int L = Histogram.sz;
        // tomamos pi
        double[] array;
        array = switch (which) {
            case 1 -> histogram.getHistogram();
            case 2 -> histogram.getAcumulativeHistogram();
            case 3 -> histogram.getProbabilityHistogram();
            case 4 -> histogram.getAcumulativeProbabilityHistogram();
            default -> new double[L];
        };

        // buscamos a maxPi
        for (int i = 0; i < L; i++) {
            maxPi = Math.max(maxPi, array[i]);
        }

        g.drawString("" + redondear(maxPi), 5, 25);
        g.drawString("" + redondear(maxPi / 2.0), 5, 85);
        g.drawString("0", 5, 145);
        // dibujamos los valores de X
        for (int n = 0; n < L; n++) {
            if (n % 16 == 0 && n != 0) {
                g.drawLine(50 + n, 141, 50 + n, 144);
            }
            if (n % 32 == 0) {
                g.drawLine(50 + n, 141, 50 + n, 148);
                g.drawString("" + n, 45 + n, 160);
            }
            if (n == 255) {
                g.drawLine(50 + n, 141, 50 + n, 148);
                g.drawString("" + n, 45 + n, 160);
            }
        }
        // dibujamos los valores en Y

        // dibujamos el histograma
        g.setColor(color);
        for (int n = 0; n < L; n++) {
            double valor = (array[n] / maxPi) * 120;
//            System.out.println("valor = " + valor);
            g.drawLine(50 + n, 140 - (int) valor, 50 + n, 140);

        }
    }

    private double redondear(double valor) {
        valor *= 1000.0;
        valor = Math.round(valor);
        valor /= 1000.0;
        return valor;
    }
}
