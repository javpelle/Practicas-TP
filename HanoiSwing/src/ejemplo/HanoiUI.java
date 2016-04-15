package ejemplo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Paints a Towers Of Hanoi puzzle with arbitrary towers
 */
public class HanoiUI extends JPanel {

    private HanoiModel model;

    public void setModel(HanoiModel model) {
        this.model = model;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        if (model == null) {
            return;
        }

        float margin = 10;
        float stackWidth = getWidth() / model.getStackCount();
        float diskHeight = Math.min(1.5f*margin, (getHeight() - 3*margin) / model.getDiskCount());
        float diskWidthDelta = (stackWidth - margin*5) / model.getDiskCount();
        for (int i=0; i<model.getStackCount(); i++) {
            paintStack(g2, model.getStack(i), stackWidth*i, stackWidth, getHeight(),
                    diskHeight, diskWidthDelta, margin);
        }
    }

    private void paintStack(Graphics2D g2, Iterable<Integer> disks,
                            float x0, float w, float h,
                            float dh, float dwd, float m) {

    	// draw pole and base
        float centerX = x0 + w/2f;
        Shape pole = new Rectangle2D.Float(
                centerX - m/2f,
                m,
                m,
                h - 2*m);
        g2.setColor(Color.red);
        g2.fill(pole);
        g2.setColor(Color.black);
        g2.draw(pole);
        Shape base = new Rectangle2D.Float(
                x0 + m,
                h - 2*m,
                w - 2*m,
                m);
        g2.setColor(Color.red);
        g2.fill(base);
        g2.setColor(Color.black);
        g2.draw(base);

        // draw disks
        float centerY = h - 2*m - dh/2;
        Rectangle2D diskRect = new Rectangle2D.Float();
        for (int disk : disks) {
            diskRect.setFrameFromCenter(
            		centerX, 
            		centerY,
                    centerX + (dwd*disk + 3*m)/2, 
                    centerY + dh/2);
            g2.setPaint(new GradientPaint(
            		(float)diskRect.getMinX(), (float)diskRect.getCenterY(), 
            		Color.cyan,
            		(float)diskRect.getMaxX(), (float)diskRect.getCenterY(), 
            		Color.blue));
            g2.fill(diskRect);
            g2.setColor(Color.black);
            g2.draw(diskRect);
            // and also label
            g2.drawString("" + disk, centerX - m/2, centerY + m/2);
            centerY -= dh;
        }
    }
}
