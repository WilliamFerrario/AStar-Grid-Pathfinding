import java.util.*;

public class Grid{

    //Initial buttons set to false for game state
    public boolean executePressed = false;
    public boolean executeHovered = false;
    public boolean startPressed = false;
    public boolean startHovered = false;
    public boolean goalPressed = false;
    public boolean goalHovered = false;
    public boolean resetHovered = false;
    public int cellHoveredX;
    public int cellHoveredY;
    public Node[][] nodes;
    public Node currentNode;
    public LinkedList<Node> path = new LinkedList<Node>();
    public LinkedList<Node> finalPath = new LinkedList<Node>();
    public LinkedList<Node> neighborList = new LinkedList<Node>();
    

    //Creating 20 x 20 game board arrays
    //0 representing free space, 1 = walls
    public int [][] gameBoard = 
    {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };

    public int [][] getBoard() {
        return gameBoard;
    }

    public int getBoardVal(int x, int y) {
        return gameBoard[x][y];
    }

    public void setBoard(int x, int y, int type){
        gameBoard[x][y] = type;
    }

    public void updateBoard(int x, int y) {
        if(x == -1 || y == -1) return;
    
        if(startPressed || goalPressed) {
            setBoard(x, y, startPressed ? 2 : 3);
            startPressed = false;
            goalPressed = false;
        } else {
            toggleWall(x, y);
        }
    }
    
    private void toggleWall(int x, int y) {
        setBoard(x, y, gameBoard[x][y] == 0 ? 1 : 0);
    }

    public void execute(){
        if (executePressed){
            executePressed = true;
            for (int i = 0; i < 25; i++){
                for (int j = 0; j < 25; j++){
                    switch (gameBoard[i][j]) {
                        case 0:
                            nodes[i][j] = new Node(i, j, false, false, false, false);
                            break;
                        case 1:
                            nodes[i][j] = new Node(i, j, true, false, false, false);
                            break;
                        case 2:
                            nodes[i][j] = new Node(i, j, false, true, false, false);
                            path.add(nodes[i][j]);
                            break;
                        case 3:
                            nodes[i][j] = new Node(i, j, false, false, true, false);
                            break;
                    }
                }    
            }

            executePressed = false;
            startBFS();
        }
    }

    public void restartBoard(){
        for (int i = 0; i < 25; i++){
            for (int j = 0; j < 25; j++){
                gameBoard[i][j] = 0;
                nodes[i][j] = new Node(i, j, false, false, false, false);
            }
        }
        currentNode = null;
        path.clear();
    }

    //Empty Node instantiation
    public Grid() {
        nodes = new Node[25][25];
        // Initialize the grid with empty nodes
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                nodes[i][j] = new Node(i, j);
            }
        }
    }

    //BFS Method HERE
    public void BFS(){

        while(!path.isEmpty()){
            currentNode = path.poll();

            if(currentNode.isGoal()){
                backtrackPath(currentNode);
                break;
            } else {
                currentNode.visit();// Mark the node as searched
            }

            List<Node> neighbors = getNeighbors(currentNode);
            for (Node neighbor : neighbors) {
                if (!neighbor.isVisited() && !path.contains(neighbor)) {
                    neighbor.parent = currentNode;
                    path.add(neighbor);
                }
            }

            try {
                Thread.sleep(10); // sleep for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void backtrackPath(Node goalNode){
        finalPath.clear();
        Node current = goalNode;
        while(current != null){
            finalPath.addFirst(current);
            current = current.parent;

            try {
                Thread.sleep(20); // sleep for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Node> getNeighbors(Node node){
        List<Node> neighbors = new ArrayList<>();
        neighborList.clear();

        int x = node.getX();
        int y = node.getY();

        if(x + 1 < 25 && !nodes[x + 1][y].isWall()){
            neighbors.add(nodes[x + 1][y]);
            neighborList.add(nodes[x + 1][y]);
        }

        if(x - 1 >= 0 && !nodes[x - 1][y].isWall()){
            neighbors.add(nodes[x - 1][y]);
            neighborList.add(nodes[x - 1][y]);
        }

        if(y + 1 < 25 && !nodes[x][y + 1].isWall()){
            neighbors.add(nodes[x][y + 1]);
            neighborList.add(nodes[x][y + 1]);
        }

        if(y - 1 >= 0 && !nodes[x][y - 1].isWall()){
            neighbors.add(nodes[x][y - 1]);
            neighborList.add(nodes[x][y - 1]);
        }
        
        return neighbors;
    }

    public void setNeighborList(LinkedList<Node> neighborList) {
        this.neighborList = neighborList;
    }

    public LinkedList<Node> getNeighborList() {
        return neighborList;
    }

    public void startBFS() {
        new Thread(this::BFS).start();
    }
}

