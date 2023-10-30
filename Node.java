public class Node {
    public int x, y; //Node coords on the grid
    public boolean isWall; //Check if node is wall or not
    public boolean isStart; //Check if node is wall or not
    public boolean isFinish; //Check if node is wall or not
    public int gCost, hCost; //For A* -- gCost from start -- hCost heuristic from end
    public int fCost; //Total cost -- G + H
    public Node parent; //Stores path from start node to current node
    public boolean isVisited;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
        this.isWall = false;
        this.isStart = false;
        this.isFinish = false;
        this.isVisited = false;
    }

    public Node(int x, int y, boolean isWall, boolean isStart, boolean isFinish, boolean isVisited){
        this.x = x;
        this.y = y;
        this.isWall = isWall;
        this.isStart = isStart;
        this.isFinish = isFinish;
        this.isVisited = isVisited;
    }

    public void printNode(){
        if(isWall){
            System.out.print("w ");
        }
        else if(isStart){
            System.out.print("s ");
        }
        else if(isFinish){
            System.out.print("f ");
        }
        else{
            System.out.print("- ");
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Node getParent(){
        return parent;
    }

    public boolean isWall(){
        return isWall;
    }

    public boolean isVisited(){
        return isVisited;
    }

    public void visit(){
        isVisited = true;
    }

    public boolean isGoal(){
        return isFinish;
    }

    //Calculate fCost
    public void calculateFCost(){
        this.fCost = this.gCost + this.hCost;
    }
}
