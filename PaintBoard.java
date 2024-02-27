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

        // Exe Text
        g.setColor(Color.WHITE);
        g.drawString("Execute", 80, 65);

        // Reset button
        g.setColor(fgDark);
        g.fillRect(15, 105, 212, 84);
        if (grid.resetHovered) {
            g.setColor(hoveredDark);
            g.fillRect(15, 105, 212, 84);
        }

        // Reset Text
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

        // Start Text
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

        // Goal Text
        g.setColor(Color.WHITE);
        g.drawString("Goal", 90, 335);

        // Small Button Font
        Font smallFont = new Font("SansSerif", Font.PLAIN, 12);
        g.setFont(smallFont);

        // BFS button
        g.setColor(fgDark);
        g.fillRect(15, 375, 65, 60);
        if (grid.BFSHovered) {
            g.setColor(hoveredDark);
            g.fillRect(15, 375, 65, 60);
        }
        if (grid.BFSPressed) {
            g.setColor(mutedGreen);
            g.fillRect(15, 375, 65, 60);
            g.setColor(hoveredDark);
        }

        // BFS button text
        g.setColor(Color.WHITE); // Set text color
        g.drawString("BFS", 15 + (65 / 2) - (g.getFontMetrics().stringWidth("BFS") / 2), 375 + (60 / 2) + (g.getFontMetrics().getAscent() / 2) - 3);

        // DIJKSTRA button
        g.setColor(fgDark);
        g.fillRect(89, 375, 65, 60);
        if (grid.DHovered) {
            g.setColor(hoveredDark);
            g.fillRect(89, 375, 65, 60);
        }
        if (grid.DPressed) {
            g.setColor(mutedGreen);
            g.fillRect(89, 375, 65, 60);
            g.setColor(hoveredDark);
        }

        // Dijkstra button text
        g.setColor(Color.WHITE); // Set text color
        g.drawString("Dijkstra's", 89 + (65 / 2) - (g.getFontMetrics().stringWidth("Dijkstra's") / 2), 375 + (60 / 2) + (g.getFontMetrics().getAscent() / 2) - 3);

        // Astar button
        g.setColor(fgDark);
        g.fillRect(162, 375, 65, 60);
        if (grid.AstarHovered) {
            g.setColor(hoveredDark);
            g.fillRect(162, 375, 65, 60);
        }
        if (grid.AstarPressed) {
            g.setColor(mutedGreen);
            g.fillRect(162, 375, 65, 60);
            g.setColor(hoveredDark);
        }

        // Astar button text
        g.setColor(Color.WHITE); // Set text color
        g.drawString("A*", 162 + (65 / 2) - (g.getFontMetrics().stringWidth("A*") / 2), 375 + (60 / 2) + (g.getFontMetrics().getAscent() / 2) - 3);

        //Size Selector
        g.setColor(fgDark);
        g.fillRect(15, 442, 212, 30);

        //Size Selector handle
        g.setColor(mutedGrey);
        g.fillRect(grid.sizeSelectorValue, 442, 10, 30);
        if (grid.sizeSelectorHovered) {
            g.setColor(hoveredDark);
            g.fillRect(grid.sizeSelectorValue, 442, 10, 30);
        }

        // Size selector text
        g.setColor(Color.WHITE); // Set text color
        String sizeText = "Grid Size: " + grid.sizeSelectorValue; // Get actual value of size selector
        g.drawString(sizeText, 15 + (212 / 2) - (g.getFontMetrics().stringWidth(sizeText) / 2), 460);

        //Search Speed Selector
        g.setColor(fgDark);
        g.fillRect(15, 480, 212, 30);

        //Search speed handle
        g.setColor(mutedGrey);
        g.fillRect(grid.searchSpeedValue, 480, 10, 30);
        if (grid.searchSpeedHovered) {
            g.setColor(hoveredDark);
            g.fillRect(grid.searchSpeedValue, 480, 10, 30);
        }

        // Search text
        g.setColor(Color.WHITE); // Set text color
        String searchSpeedText = "Search Speed: " + grid.searchSpeedValue; // Get actual value of search speed selector
        g.drawString(searchSpeedText, 15 + (212 / 2) - (g.getFontMetrics().stringWidth(searchSpeedText) / 2), 498);

        //Path Speed Selector
        g.setColor(fgDark);
        g.fillRect(15, 522, 212, 30);

        //Path speed handle
        g.setColor(mutedGrey);
        g.fillRect(grid.pathSpeedValue, 522, 10, 30);
        if (grid.pathSpeedHovered) {
            g.setColor(hoveredDark);
            g.fillRect(grid.pathSpeedValue, 522, 10, 30);
        }

        // Path text
        g.setColor(Color.WHITE); // Set text color
        String pathSpeedText = "Path Speed: " + grid.pathSpeedValue; // Get actual value of path speed selector
        g.drawString(pathSpeedText, 15 + (212 / 2) - (g.getFontMetrics().stringWidth(pathSpeedText) / 2), 540);


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

                // Paint general color for cell
                g.fillRect(grid.xOffset + (grid.cellPadding + i * grid.cellSize),
                        grid.yOffset + grid.cellPadding + j * grid.cellSize, grid.cellSize - 2 * grid.cellPadding,
                        grid.cellSize - 2 * grid.cellPadding);

            }
        }
    }
}

// TODO for two destinations...
// if val of <1 ... recall alg
// from first initial goal to other set goal