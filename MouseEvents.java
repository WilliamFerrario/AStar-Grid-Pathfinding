import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class MouseEvents implements MouseInputListener{
    private Grid grid;

    public MouseEvents(Grid grid){
        this.grid = grid;
    }

    public int mouseX;
    public int mouseY;
    public int mouseCellX;
    public int mouseCellY;
    public boolean mouseDown;
    public boolean mouseUp;


    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        
        mouseCellX = inCellX(mouseX, mouseY);
        mouseCellY = inCellY(mouseX, mouseY);

        if(mouseCellX != -1 || mouseCellY != -1){
            grid.updateBoard(mouseCellX, mouseCellY);
        }

        //System.out.println(mouseCellX + " " + mouseCellY);

        //execute button
        if(mouseX >= 15 && mouseX < 228 && mouseY >= 15 && mouseY <  98){
            grid.executePressed = !grid.executePressed; // Toggle the value
            grid.startPressed = false;
            grid.goalPressed = false;

            if (grid.executePressed){
                grid.execute();
            }
        }

        //reset button
        if(mouseX >= 15 && mouseX < 228 && mouseY >= 105 && mouseY <  188){
            grid.restartBoard();
            grid.executePressed = false;
            grid.startPressed = false;
            grid.goalPressed = false;
        }

        //start button
        if(mouseX >= 15 && mouseX < 228 && mouseY >= 195 && mouseY <  278){
            grid.startPressed = !grid.startPressed; // Toggle the value
            if (grid.startPressed) {  // If start is now true, set goal to false
                grid.goalPressed = false;
            }
            grid.executePressed = false;
        }

        //goal button
        if(mouseX >= 15 && mouseX < 228 && mouseY >= 285 && mouseY <  368){
            grid.goalPressed = !grid.goalPressed; // Toggle the value
            if (grid.goalPressed) {  // If goal is now true, set start to false
                grid.startPressed = false;
            }
            grid.executePressed = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        mouseCellX = inCellX(mouseX, mouseY);
        mouseCellY = inCellY(mouseX, mouseY);

        //execute button
        if(mouseX >= 15 && mouseX < 228 && mouseY >= 15 && mouseY <  98 && !grid.executePressed){
            grid.executeHovered = true;
        }
        else{
            grid.executeHovered = false;
        }

        //reset button
        if(mouseX >= 15 && mouseX < 228 && mouseY >= 105 && mouseY <  188){
            grid.resetHovered = true;
        }
        else{
            grid.resetHovered = false;
        }

        //start button
        if(mouseX >= 15 && mouseX < 228 && mouseY >= 195 && mouseY <  278 && !grid.startPressed){
            grid.startHovered = true;
        }
        else{
            grid.startHovered = false;
        }

        //goal button
        if(mouseX >= 15 && mouseX < 228 && mouseY >= 285 && mouseY <  368 && !grid.goalPressed){
            grid.goalHovered = true;
        }
        else{
            grid.goalHovered = false;
        }

        //Cell hovering
        grid.cellHoveredX = mouseCellX; 
        grid.cellHoveredY = mouseCellY; 

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
        mouseUp = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
        mouseUp = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        mouseCellX = inCellX(mouseX, mouseY);
        mouseCellY = inCellY(mouseX, mouseY);
        int val = grid.getBoardVal(mouseCellX, mouseCellY);

        if(mouseDown) {
            grid.updateBoard(mouseCellX, mouseCellY);
            mouseX = e.getX();
            mouseY = e.getY();

            mouseCellX = inCellX(mouseX, mouseY);
            mouseCellY = inCellY(mouseX, mouseY);
            
            //Drag to change values of cells opposite of initial click
            if(val != grid.getBoardVal(mouseCellX, mouseCellY)){
                grid.updateBoard(mouseCellX, mouseCellY);
            }
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

    //Get X Cell Mouse is occupying
    public int inCellX(int x, int y) {
        int cellSize = 30; // size of one cell w borders and padding
        int xOffset = 241; // x grid offset
    
        int col = (x - xOffset) / cellSize;
        if (col >= 0 && col < 25) {
            return col;
        } else {
            return -1;
        }
    }

    //Get X Cell Mouse is occupying
    public int inCellY(int x, int y) {
        int cellSize = 30; // size of one cell w borders and padding
        int yOffset = 12;  // y grid offset
    
        int row = ((y - yOffset) / cellSize);
        if (row >= 0 && row < 25) {
            return row;
        } 
        else {
            return -1;
        }
    }
}
