import java.awt.*;
import javax.swing.JPanel;

public class PaintBoard extends JPanel {

    // Grid Instantiation
    public Grid grid = new Grid();

    // Colors
    Color mutedRed = new Color(192, 76, 83);
    Color mutedGreen = new Color(61, 154, 61);
    Color mutedGrey = new Color(154, 154, 154);

    Color fgDark = new Color(32, 33, 36);
    Color bgDark = new Color(18, 19, 20);
    Color hoveredDark = new Color(55, 55, 55);

    public void paintComponent(Graphics g) {

        // Button Label Font
        Font font = new Font("SansSerif", Font.PLAIN, 25);
        g.setFont(font);

        // Button Colors
        g.setColor(bgDark);
        g.fillRect(0, 0, 1020, 810);

        // Execute button
        g.setColor(fgDark);
        g.fillRect(15, 15, 212, 84);
        if (grid.executeHovered) {
            g.setColor(hoveredDark);
        }
        if (grid.executePressed) {
            g.setColor(Color.green);
        }
        g.fillRect(15, 15, 212, 84);

        // Text
        g.setColor(Color.WHITE);
        g.drawString("Execute", 80, 65);

        // Reset button
        g.setColor(fgDark);
        g.fillRect(15, 105, 212, 84);
        if (grid.resetHovered) {
            g.setColor(hoveredDark);
            g.fillRect(15, 105, 212, 84);
        }

        // Text
        g.setColor(Color.WHITE);
        g.drawString("Reset", 90, 155);

        // Start button
        g.setColor(fgDark);
        g.fillRect(15, 195, 212, 84);
        if (grid.startHovered) {
            g.setColor(hoveredDark);
            g.fillRect(15, 195, 212, 84);
        }
        if (grid.startPressed) {
            g.setColor(Color.yellow);
            g.fillRect(15, 195, 212, 84);
            g.setColor(hoveredDark);
        }

        // Text
        g.setColor(Color.WHITE);
        g.drawString("Start", 90, 245);

        // Goal button
        g.setColor(fgDark);
        g.fillRect(15, 285, 212, 84);
        if (grid.goalHovered) {
            g.setColor(hoveredDark);
            g.fillRect(15, 285, 212, 84);
        }
        if (grid.goalPressed) {
            g.setColor(mutedGreen);
            g.fillRect(15, 285, 212, 84);
            g.setColor(hoveredDark);
        }

        // Text
        g.setColor(Color.WHITE);
        g.drawString("Goal", 90, 335);

        // Draw Cells
        for (int i = 0; i < grid.cols; i++) {
            for (int j = 0; j < grid.rows; j++) {
                g.setColor(fgDark);

                // Paint non-selected cells as normal, unless hovered
                if (grid.gameBoard[i][j] == 0) {
                    g.setColor(fgDark);
                    if ((i == grid.cellHoveredX && j == grid.cellHoveredY) && !grid.goalPressed && !grid.startPressed) {
                        g.setColor(mutedRed);
                    }
                }

                // Paint wall cells as a wall, darker if hovered
                if (grid.gameBoard[i][j] == 1) {
                    g.setColor(mutedRed);
                    if ((i == grid.cellHoveredX && j == grid.cellHoveredY) && !grid.goalPressed && !grid.startPressed) {
                        // g.setColor(fgDark);
                    }
                }

                // Paint Start State if 2, if button is selected paint that square before
                // placement
                if (grid.gameBoard[i][j] == 2
                        || (i == grid.cellHoveredX && j == grid.cellHoveredY && grid.startPressed)) {
                    g.setColor(Color.orange);
                }

                // Paint Goal State if 3, if button is selected paint that square before
                // placement
                if (grid.gameBoard[i][j] == 3
                        || (i == grid.cellHoveredX && j == grid.cellHoveredY && grid.goalPressed)) {
                    g.setColor(mutedGreen);
                }

                // Paint currently searched neighbor square
                if (grid.neighborList.contains(grid.nodes[i][j]) && grid.finalPath.isEmpty()) {
                    g.setColor(Color.magenta);
                }

                // Paint previously searched spots -- FOR BFS
                if (grid.nodes[i][j].isVisited && grid.gameBoard[i][j] != 2 && grid.gameBoard[i][j] != 3) {
                    g.setColor(Color.pink);
                }

                // Paint current open set search -- FOR A*
                if (grid.oSet.contains(grid.nodes[i][j]) && grid.gameBoard[i][j] != 2 && grid.gameBoard[i][j] != 3) {
                    g.setColor(Color.magenta);
                }

                // Paint previously searched close set search -- FOR A*
                if (grid.cSet.contains(grid.nodes[i][j]) && grid.gameBoard[i][j] != 2 && grid.gameBoard[i][j] != 3) {
                    g.setColor(Color.pink);
                }

                // Paint final path
                if (grid.finalPath.contains(grid.nodes[i][j]) && grid.gameBoard[i][j] != 2
                        && grid.gameBoard[i][j] != 3) {
                    g.setColor(Color.blue);
                }

                g.fillRect(grid.xOffset + (grid.cellPadding + i * grid.cellSize),
                        grid.yOffset + grid.cellPadding + j * grid.cellSize, grid.cellSize - 2 * grid.cellPadding,
                        grid.cellSize - 2 * grid.cellPadding);

            }
        }
    }
}

// for two destinations...
// if val of <1 ... recall alg
// from first initial goal to other set goal