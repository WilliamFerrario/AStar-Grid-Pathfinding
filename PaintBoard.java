import java.awt.*;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PaintBoard extends JPanel{

    //Grid Instantiation
    public Grid grid = new Grid();
    Timer timer;

    /////////MOUSE/////////
    public int mouseX;
    public int mouseY;
    public static int mouseCellX;
    public static int mouseCellY;
    public static boolean mouseDown = false;
    public static boolean mouseUp = true;
    boolean establish = false;
    
    
    //Colors
    Color mutedRed = new Color(192, 76, 83); 
    Color mutedGreen = new Color(61, 154, 61);
    Color mutedGrey = new Color(154, 154, 154);
    
    //Cell Dimensions
    int cellSize = 30;
    int cellPadding = 3;
    

    public void paintComponent(Graphics g){

        //Button Label Font
        Font font = new Font("SansSerif", Font.PLAIN, 25);
        g.setFont(font);

        //Button Colors
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 1020, 810);

        //Execute button
        g.setColor(mutedGrey);
        g.fillRect(15, 15, 212, 84);
        if(grid.executeHovered){
            g.setColor(Color.gray);  
        }
        if(grid.executePressed){
            g.setColor(Color.green); 
        }
        g.fillRect(15, 15, 212, 84);

        //Text
        g.setColor(Color.WHITE); 
        g.drawString("Execute", 80, 65);

        //Reset button
        g.setColor(mutedGrey);
        g.fillRect(15, 105, 212, 84);
        if(grid.resetHovered){
            g.setColor(Color.gray); 
            g.fillRect(15, 105, 212, 84);
        }

        //Text
        g.setColor(Color.WHITE);
        g.drawString("Reset", 90, 155);

        //Start button
        g.setColor(mutedGrey);
        g.fillRect(15, 195, 212, 84);
        if(grid.startHovered){
            g.setColor(Color.gray);
            g.fillRect(15, 195, 212, 84);
        }
        if(grid.startPressed){
            g.setColor(Color.yellow);
            g.fillRect(15, 195, 212, 84);
            g.setColor(Color.gray);
        }

        //Text
        g.setColor(Color.WHITE);
        g.drawString("Start", 90, 245);

        //Goal button
        g.setColor(mutedGrey);
        g.fillRect(15, 285, 212, 84);
        if(grid.goalHovered){
            g.setColor(Color.gray); 
            g.fillRect(15, 285, 212, 84);
        } 
        if(grid.goalPressed){
            g.setColor(mutedGreen);
            g.fillRect(15, 285, 212, 84);
            g.setColor(Color.gray);
        }

        //Text
        g.setColor(Color.WHITE); 
        g.drawString("Goal", 90, 335);
        
        //Draw Cells
        for (int i = 0; i < 25; i++){
            for (int j = 0; j < 25; j++){
                g.setColor(mutedGrey);

                if(grid.gameBoard[i][j] == 0){
                    g.setColor(mutedGrey);
                    if((i == grid.cellHoveredX && j == grid.cellHoveredY) && !grid.goalPressed && !grid.startPressed){
                        g.setColor(mutedRed);
                    }
                }
                
                if(grid.gameBoard[i][j] == 1){
                    g.setColor(mutedRed);
                    if((i == grid.cellHoveredX && j == grid.cellHoveredY) && !grid.goalPressed && !grid.startPressed){
                        g.setColor(mutedGrey);
                    }
                }

                if(grid.gameBoard[i][j] == 2 || (i == grid.cellHoveredX && j == grid.cellHoveredY && grid.startPressed)){
                    g.setColor(Color.orange);
                }

                if(grid.gameBoard[i][j] == 3 || (i == grid.cellHoveredX && j == grid.cellHoveredY && grid.goalPressed)){
                    g.setColor(mutedGreen);
                }

                if(grid.neighborList.contains(grid.nodes[i][j]) && grid.finalPath.isEmpty()){
                    g.setColor(Color.magenta);
                }

                if(grid.nodes[i][j].isVisited && grid.gameBoard[i][j] != 2 && grid.gameBoard[i][j] != 3){
                    g.setColor(Color.pink);
                }

                if (grid.finalPath.contains(grid.nodes[i][j]) && grid.gameBoard[i][j] != 2 && grid.gameBoard[i][j] != 3) {
                    g.setColor(Color.blue);
                }

                g.fillRect(241 + (cellPadding + i * cellSize), 12 + cellPadding + j * cellSize, cellSize - 2 * cellPadding,  cellSize - 2 * cellPadding);
            
            }
        }
    }
}


// for two destinations...
// if val of <1 ... recall alg
//from first initial goal to other set goal