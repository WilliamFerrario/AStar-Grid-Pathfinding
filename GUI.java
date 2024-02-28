import javax.swing.JFrame;

public class GUI extends JFrame {

    public GUI() {

        this.setTitle("Grid-Pathfinding");
        this.setSize(1020, 810);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        // TODO change w and h with resize

        PaintBoard paint = new PaintBoard();
        this.setContentPane(paint);

        MouseEvents mouseEvents = new MouseEvents(paint.grid);
        paint.addMouseListener(mouseEvents);
        paint.addMouseMotionListener(mouseEvents);
        paint.addMouseWheelListener(mouseEvents);

    }
}
