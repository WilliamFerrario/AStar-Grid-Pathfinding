import java.util.*;

public class Grid{

    //Initial buttons set to false for game state
    public Node[][] nodes;
    public Node currentNode;
    public LinkedList<Node> path = new LinkedList<Node>();
    public LinkedList<Node> finalPath = new LinkedList<Node>();
    public LinkedList<Node> neighborList = new LinkedList<Node>();
    public Node startNode;
    public Node endNode;
    
    //Initial flags for painting and setting grid state
    public boolean executePressed = false;
    public boolean executeHovered = false;
    public boolean startPressed = false;
    public boolean startHovered = false;
    public boolean goalPressed = false;
    public boolean goalHovered = false;
    public boolean resetHovered = false;
    public int cellHoveredX;
    public int cellHoveredY;

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
                            startNode = nodes[i][j];
                            break;
                        case 3:
                            nodes[i][j] = new Node(i, j, false, false, true, false);
                            endNode = nodes[i][j];
                            break;
                    }
                }    
            }

            executePressed = false;
            //startBFS();
            //startDijkstra();
            startAStar();
        }
    }

    public void restartBoard(){
        for (int i = 0; i < 25; i++){
            for (int j = 0; j < 25; j++){
                gameBoard[i][j] = 0;
                nodes[i][j] = new Node(i, j, false, false, false, false);
                nodes[i][j].distance = Integer.MAX_VALUE;
            }
        }
        path.clear();
        finalPath.clear();
        neighborList.clear();
        startNode = null;
        endNode = null;
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

    //Calculate Manhattan Distance for A*
    public int calculateManhattanDistance(Node nodeA, Node nodeB) {
        return Math.abs(nodeA.x - nodeB.x) + Math.abs(nodeA.y - nodeB.y);
    }

    //Get Neighbors from current node
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

    //Dijkstra's Algorithm
    public void dijkstra(){
        PriorityQueue <Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));

        if (startNode != null){
            startNode.distance = 0;
            pq.add(startNode);

            while (!pq.isEmpty()){
                Node currentNode = pq.poll();

                if(currentNode.isFinish){
                    backtrackPath(currentNode);
                    return;
                }

                List<Node> neighbors = getNeighbors(currentNode);
                for (Node neighbor : neighbors) {
                    if (!neighbor.isVisited()) {
                        int newDistance = currentNode.distance + 1; //Change in future for weighted edges
                        if (newDistance < neighbor.distance){
                            neighbor.distance = newDistance;
                            neighbor.parent = currentNode;
                            pq.add(neighbor);
                        }
                    }
                }

                currentNode.isVisited = true;

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //AStar Algorithm
    public void aStar() {
        System.out.println("ASTAR");
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.getFCost()));
        Set<Node> closedSet = new HashSet<>();
    
        startNode.gCost = 0;
        startNode.hCost = calculateManhattanDistance(startNode, endNode);
        openSet.add(startNode);
    
        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            
            if (currentNode.equals(endNode)) {
                backtrackPath(currentNode);
                return;
            }
    
            closedSet.add(currentNode);
    
            for (Node neighbor : getNeighbors(currentNode)) {
                if (closedSet.contains(neighbor) || neighbor.isWall()) continue;
                
                int tentativeGCost = currentNode.gCost + 1; // Assume cost is 1 for all edges
                if (tentativeGCost < neighbor.gCost) {
                    neighbor.parent = currentNode;
                    neighbor.gCost = tentativeGCost;
                    neighbor.hCost = calculateManhattanDistance(neighbor, endNode);
                    
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
    
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startBFS() {
        new Thread(this::BFS).start();
    }

    public void startDijkstra() {
        new Thread(this::dijkstra).start();
    }

    public void startAStar() {
        new Thread(this::aStar).start();
    }
}

//Potentially add Diagonal Movement instead of Axial