package main.java.lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelbrotSet extends JFrame {

    private final int MAX_ITER = 570;
    private double zoom;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;
    private int width;
    private int height;

    public MandelbrotSet(int size) {
        super("MandelbrotSet Set");
        this.width = size;
        this.height = size * 3 / 5;
        this.zoom = size / 5;
        setBounds(10, 10, size, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void count(int firstDimension, int secondDimension) {
        for (int y = firstDimension; y < secondDimension; y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = zy = 0;
                cX = (x - width / 2) / zoom;
                cY = (y - height / 2) / zoom;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y, iter | (iter << 8));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }
}