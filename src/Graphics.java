import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.geom.*;

@SuppressWarnings("serial")
public class Graphics extends JFrame {

    public Graphics(){
        this.setSize(500, 500);
        this.setTitle("Obstacle Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new DrawStuff(), BorderLayout.CENTER); // what to add to frame
        this.setVisible(true);
    }

    private class DrawStuff extends JComponent{
        public void paint(java.awt.Graphics g){
            Graphics2D graph2 = (Graphics2D) g;
            graph2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            Shape drawLine = new Line2D.Float(20, 90, 55, 250);
            graph2.setPaint(Color.BLACK);
            graph2.draw(drawLine);
        }
    }

}
