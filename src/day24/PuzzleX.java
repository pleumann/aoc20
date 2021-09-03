package day24;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Lobby Layout parts 1 and 2 plus a simple GUI to show the actual hex grid.
 */
public class PuzzleX extends Puzzle2 {

    /**
     * The panel we use for painting.
     */
    JPanel panel;

    /**
     * Our main frame (aka window).
     */
    JFrame frame;

    /**
     * Sets up our GUI. Nothing special here except an anonymous subclass of
     * JPanel with overridden paint() method that delegates to our paintGrid()
     * method.
     */
    void initGui(String s) {
        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                paintGrid(g);
            }
        };
        panel.setDoubleBuffered(true);
        panel.setPreferredSize(new Dimension(800, 800));
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame = new JFrame("AoC 2020.24 [" + s + "]");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocation(d.width - frame.getWidth(), d.height - frame.getHeight());
        frame.setVisible(true);
    }
    
    @Override
    void setup(String s) {
        super.setup(s);

        panel.repaint(); // Sneak in a repaint after each setup string.

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // Ignored
        }
    }
    
    @Override
    void day() {
        super.day();

        panel.repaint(); // Sneak in a repaint after each day performed.

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // Ignored
        }
    }
    
    /**
     * Paints the whole hex grid based on the tile information. The math is a
     * bit hacky, but we're only interested in this single case, so we avoid
     * overengineering.
     */
    public void paintGrid(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        
        double scale1 = panel.getWidth() / (125.0 * 10);
        double scale2 = panel.getHeight() / (150.0 * 8);

        g2.scale(scale1, scale2);
        
        int fix = 0;
        for (int y = -75; y < 76; y++) {
            fix = fix + 5; // Compensate for hex-in-2D-grid, 0.5 tiles per line
            for (int x = -150; x < 150; x++) {
                paintHex(g, (x + 100) * 10 - fix, (y + 75) * 8, tiles.contains(key(x, y)));
            }
        }
        
        panel.invalidate();
    }
    
    /**
     * Paints a single hex tile with the given coordinates and color. Again, all
     * hardcoded to a fixed size/resolution.
     */
    void paintHex(Graphics g, int x, int y, boolean black) {
        int[] xs = new int[] { x, x + 4, x + 4, x, x - 4, x - 4 };
        int[] ys = new int[] { y + 4, y + 2, y - 2, y - 4, y - 2, y + 2 };

        g.setColor(black ? Color.BLACK : Color.WHITE);
        g.fillPolygon(xs, ys, xs.length);
    }
    
    public static void main(String[] args) {
        PuzzleX p = new PuzzleX();
        p.initGui(args[0]);
        p.process(args[0]);
    }
    
}
