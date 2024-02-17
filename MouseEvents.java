import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class MouseEvents implements MouseInputListener {
    private Grid grid;

    // Mouse events to communicate with grid
    public MouseEvents(Grid grid) {
        this.grid = grid;
    }

    // Variable instantiation
    public int mouseX;
    public int mouseY;
    public int mouseCellX;
    public int mouseCellY;
    public int size;
    public int searchSpeed;
    public int pathSpeed;
    public boolean mouseDown;
    public boolean mouseUp;
    public int mod;

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mouseCellX = inCellX(mouseX, mouseY);
        mouseCellY = inCellY(mouseX, mouseY);

        // Update Cell on grid
        if (mouseCellX != -1 || mouseCellY != -1) {
            grid.updateBoard(mouseCellX, mouseCellY);
        }

        // execute button press
        if (mouseX >= 15 && mouseX < 228 && mouseY >= 15 && mouseY < 98) {
            grid.executePressed = !grid.executePressed; // Toggle the value
            grid.startPressed = false;
            grid.goalPressed = false;

            if (grid.executePressed) {
                grid.execute();
            }
        }

        // reset button press
        if (mouseX >= 15 && mouseX < 228 && mouseY >= 105 && mouseY < 188) {
            grid.restartBoard();
            grid.executePressed = false;
            grid.startPressed = false;
            grid.goalPressed = false;
        }

        // start button press
        if (mouseX >= 15 && mouseX < 228 && mouseY >= 195 && mouseY < 278) {
            grid.startPressed = !grid.startPressed; // Toggle the value
            if (grid.startPressed) { // If start is now true, set goal to false
                grid.goalPressed = false;
            }
            grid.executePressed = false;
        }

        // goal button press
        if (mouseX >= 15 && mouseX < 228 && mouseY >= 285 && mouseY < 368) {
            grid.goalPressed = !grid.goalPressed; // Toggle the value
            if (grid.goalPressed) { // If goal is now true, set start to false
                grid.startPressed = false;
            }
            grid.executePressed = false;
        }

        // BFS button press
        if (mouseX >= 15 && mouseX < 15 + 65 && mouseY >= 375 && mouseY < 375 + 60) {
            grid.BFSPressed = !grid.BFSPressed; // Toggle the value
            grid.DPressed = false; // Reset others
            grid.AstarPressed = false;
        }

        // Dijkstra button press
        if (mouseX >= 89 && mouseX < 89 + 65 && mouseY >= 375 && mouseY < 375 + 60) {
            grid.DPressed = !grid.DPressed; // Toggle the value 
            grid.BFSPressed = false; // Reset others
            grid.AstarPressed = false;
        }

        // A* button press
        if (mouseX >= 162 && mouseX < 162 + 65 && mouseY >= 375 && mouseY < 375 + 60) {
            grid.AstarPressed = !grid.AstarPressed; // Toggle the value
            grid.BFSPressed = false; // Reset others
            grid.DPressed = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mouseCellX = inCellX(mouseX, mouseY);
        mouseCellY = inCellY(mouseX, mouseY);

        // execute button
        if (mouseX >= 15 && mouseX < 228 && mouseY >= 15 && mouseY < 98 && !grid.executePressed) {
            grid.executeHovered = true;
        } else {
            grid.executeHovered = false;
        }

        // reset button
        if (mouseX >= 15 && mouseX < 228 && mouseY >= 105 && mouseY < 188) {
            grid.resetHovered = true;
        } else {
            grid.resetHovered = false;
        }

        // start button
        if (mouseX >= 15 && mouseX < 228 && mouseY >= 195 && mouseY < 278 && !grid.startPressed) {
            grid.startHovered = true;
        } else {
            grid.startHovered = false;
        }

        // goal button
        if (mouseX >= 15 && mouseX < 228 && mouseY >= 285 && mouseY < 368 && !grid.goalPressed) {
            grid.goalHovered = true;
        } else {
            grid.goalHovered = false;
        }

        // BFS button 
        if (mouseX >= 15 && mouseX < 15 + 65 && mouseY >= 375 && mouseY < 375 + 60) {
            grid.BFSHovered = true;
        } else {
            grid.BFSHovered = false;
        }

        // Dijkstra button 
        if (mouseX >= 89 && mouseX < 89 + 65 && mouseY >= 375 && mouseY < 375 + 60) {
            grid.DHovered = true;
        } else {
            grid.DHovered = false;
        }

        // A* button 
        if (mouseX >= 162 && mouseX < 162 + 65 && mouseY >= 375 && mouseY < 375 + 60) {
            grid.AstarHovered = true;
        } else {
            grid.AstarHovered = false;
        }

        // Size Selector Hover
        if (mouseX >= 15 && mouseX <= 227 && mouseY >= 442 && mouseY <= 472) {
            grid.sizeSelectorHovered = true;

        } else {
            grid.sizeSelectorHovered = false;
        }

        // Search Selector Hover
        if (mouseX >= 15 && mouseX <= 227 && mouseY >= 480 && mouseY <= 510) {
            grid.searchSpeedHovered = true;

        } else {
            grid.searchSpeedHovered = false;
        }

        // Path Selector Hover
        if (mouseX >= 15 && mouseX <= 227 && mouseY >= 522 && mouseY <= 552) {
            grid.pathSpeedHovered = true;

        } else {
            grid.pathSpeedHovered = false;
        }

        // Cell hovering
        grid.cellHoveredX = mouseCellX;
        grid.cellHoveredY = mouseCellY;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseUp = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        try{

            if (mouseDown && (mouseCellX != -1 && mouseCellY != -1)) {
                //make sure cell selection is in a valid spot to prevent IndexOOB

                int val = grid.getBoardVal(mouseCellX, mouseCellY);
                grid.updateBoard(mouseCellX, mouseCellY);
                mouseX = e.getX();
                mouseY = e.getY();
                mouseCellX = inCellX(mouseX, mouseY);
                mouseCellY = inCellY(mouseX, mouseY);

                // Drag to change values of cells opposite of initial click
                if (val != grid.getBoardVal(mouseCellX, mouseCellY)) {
                    grid.updateBoard(mouseCellX, mouseCellY);
                }
            }
        }

        catch (ArrayIndexOutOfBoundsException exception){
            // System.out.println("mouseX" + mouseCellX);
            // System.out.println("mouseY" + mouseCellY);
        }

        //System.out.println(mouseX);

        // Size Selector Mouse Events
        if (grid.sizeSelectorHovered && mouseDown) {

            mouseX = e.getX();
            mouseY = e.getY();

            //10 - 220 px for mouse limits
            mod = mouseX % 10;
            grid.sizeSelectorValue = mouseX - mod;
            grid.sizeSelectorValue = Math.max(grid.sizeSelectorValue, 10);
            grid.sizeSelectorValue = Math.min(grid.sizeSelectorValue, 226);
            
            //Update
            //grid.updateDimensions(grid.sizeSelectorValue, grid.searchSpeedValue, grid.pathSpeedValue);
            grid.updateDimensions(grid.sizeSelectorValue);
        }

        // Search Selector Mouse Events
        if (grid.searchSpeedHovered && mouseDown) {

            mouseX = e.getX();
            mouseY = e.getY();

            //mouse limits
            grid.searchSpeedValue = mouseX;
            grid.searchSpeedValue = Math.max(grid.searchSpeedValue, 10);
            grid.searchSpeedValue = Math.min(grid.searchSpeedValue, 226);
            
            //Update
            //grid.updateDimensions(grid.sizeSelectorValue, grid.searchSpeedValue, grid.pathSpeedValue);
            grid.setSearchSpeed(grid.searchSpeedValue);
        }

        // Path Selector Mouse Events
        if (grid.pathSpeedHovered && mouseDown) {

            mouseX = e.getX();
            mouseY = e.getY();

            //mouse limits
            grid.pathSpeedValue = mouseX;
            grid.pathSpeedValue = Math.max(grid.pathSpeedValue, 10);
            grid.pathSpeedValue = Math.min(grid.pathSpeedValue, 226);
            
            //Update
            //grid.updateDimensions(grid.sizeSelectorValue, grid.searchSpeedValue, grid.pathSpeedValue);
            grid.setPathSpeed(grid.pathSpeedValue);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    // Get X Cell Mouse is occupying
    public int inCellX(int x, int y) {
        if (x >= grid.xOffset + grid.cellPadding) {
            int col = (x - grid.xOffset) / grid.cellSize;
            // System.out.println((x - grid.xOffset - grid.cellPadding));
            if (col >= 0 && col < grid.cols) {
                return col;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    // Get Y Cell Mouse is occupying
    public int inCellY(int x, int y) {
        if (y >= grid.yOffset + grid.cellPadding) {
            int row = (y - grid.yOffset) / grid.cellSize;
            if (row >= 0 && row < grid.rows) {
                return row;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
