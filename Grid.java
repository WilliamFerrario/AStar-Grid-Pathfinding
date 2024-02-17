import java.util.*;

public class Grid {

    // Initialize Rows / Cols
    public int rows = 100;
    public int cols = 100;

    // Frame Dimensions
    int frameWidth = 1020;
    int frameHeight = 810;

    // Margins and Button Area
    int buttonAreaWidth = 212; // width of button area
    int leftMargin = buttonAreaWidth + 15; // width to start drawing squares
    int verticalMargin = 12;
    int rightMargin = 15;

    //Selector Values
    public boolean sizeSelectorHovered = false;
    public int sizeSelectorValue = 110;

    public boolean searchSpeedHovered = false;
    public int searchSpeedValue = 10;

    public boolean pathSpeedHovered = false;
    public int pathSpeedValue = 20;

    // Calculate Available Grid Area
    int availableWidth = frameWidth - leftMargin - rightMargin;
    int availableHeight = frameHeight - 2 * verticalMargin; // Ensure equal top and bottom margins

    // Calculate Cell Size, Grid Dimensions, and Offsets
    int cellSize = Math.min(availableWidth / cols, availableHeight / rows);
    int gridWidth = cellSize * cols;
    int gridHeight = cellSize * rows;
    int xOffset = leftMargin + (availableWidth - gridWidth) / 2;
    int yOffset = ((availableHeight - gridHeight) / 2) - 7;

    // Initialize Cell Padding
    int cellPadding = cellSize / 6;

    // Algo Playback Speed (in ms)
    public int searchSpeed = 10;
    public int pathSpeed = 20;

    // Initial buttons set to false for game state
    public Node[][] nodes;
    public Node currentNode;
    public Node startNode;
    public Node endNode;

    // Lists for BFS
    public LinkedList<Node> path = new LinkedList<Node>();
    public LinkedList<Node> finalPath = new LinkedList<Node>();
    public LinkedList<Node> neighborList = new LinkedList<Node>();

    // List for Dijkstra
    PriorityQueue<Node> dijkstraPQ = new PriorityQueue<>();

    // Lists for A*
    public PriorityQueue<Node> oSet = new PriorityQueue<>();
    public Set<Node> cSet = new HashSet<>();

    // Initial flags for painting and setting grid state
    public boolean executePressed = false;
    public boolean executeHovered = false;
    public boolean startPressed = false;
    public boolean startHovered = false;
    public boolean goalPressed = false;
    public boolean goalHovered = false;
    public boolean resetHovered = false;

    //Algo Buttons
    public boolean BFSPressed = false;
    public boolean BFSHovered = false;

    public boolean DPressed = false;
    public boolean DHovered = false;

    public boolean AstarPressed = false;
    public boolean AstarHovered = false;
    
    public int cellHoveredX;
    public int cellHoveredY;

    // Creating game board array of 0
    // 0 representing free space, 1 = walls
    public int[][] gameBoard = new int[rows][cols];

    public int[][] getBoard() {
        return gameBoard;
    }

    public int getBoardVal(int x, int y) {
        return gameBoard[x][y];
    }

    public void setBoard(int x, int y, int type) {
        gameBoard[x][y] = type;
    }

    public void updateBoard(int x, int y) {
        if (x == -1 || y == -1)
            return;

        if (startPressed || goalPressed) {
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

    public void execute() {
        if (executePressed) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
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

            if(BFSPressed){
                System.out.println("BFS Starting");
                startBFS();
            }
            if(DPressed){
                System.out.println("DIJKSTRA Starting");
                startDijkstra();
            }
            if(AstarPressed){
                System.out.println("ASTAR Starting");
                startAStar();
            }
        }
    }

    public void updateDimensions(int dimensions) {

        //update with dimensions to ensure
        this.sizeSelectorValue = dimensions;
        
        // Map sliderPosition to rows and cols
        this.rows = sizeSelectorValue;
        this.cols = sizeSelectorValue;

        // Margins and Button Area
        this.buttonAreaWidth = 212; // width of button area
        this.leftMargin = buttonAreaWidth + 15; // width to start drawing squares
        this.verticalMargin = 12;
        this.rightMargin = 15;

        // this.searchSpeed = searchSpeed / 5;
        // this.pathSpeed = pathSpeed / 5;


        // Calculate Available Grid Area
        this.availableWidth = frameWidth - leftMargin - rightMargin;
        this.availableHeight = frameHeight - 2 * verticalMargin; // Ensure equal top and bottom margins

        //Calculate Cell Size, Grid Dimensions, and Offsets
        this.cellSize = Math.min(availableWidth / cols, availableHeight / rows);
        this.gridWidth = cellSize * cols;
        this.gridHeight = cellSize * rows;
        this.xOffset = leftMargin + (availableWidth - gridWidth) / 2;
        this.yOffset = ((availableHeight - gridHeight) / 2) - 7;

        System.out.println(this.searchSpeed);
        System.out.println(this.pathSpeed);
        //Update arrays to match dimensions
        resizeGrid();


        restartBoard(); // Reset grid with new dimensions
    }

    public void setSearchSpeed(int speed) {
        this.searchSpeed = 46 - (speed / 5);
        System.out.println(this.searchSpeed);
        
    }
    
    public void setPathSpeed(int speed) {
        this.pathSpeed = 46 - (speed / 5);
        System.out.println(this.pathSpeed);
    }

    private void resizeGrid() {
        // Create new game board and nodes arrays with updated dimensions
        int[][] newGameBoard = new int[rows][cols];
        Node[][] newNodes = new Node[rows][cols];

        // Initialize newGameBoard with 0 (free space)
        for (int i = 0; i < rows; i++) {
            Arrays.fill(newGameBoard[i], 0); // Fill each row with 0
        }

        // Populate newNodes with fresh Node instances
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newNodes[i][j] = new Node(i, j, false, false, false, false);
                // Set gCost to Integer.MAX_VALUE as in restartBoard method
                newNodes[i][j].gCost = Integer.MAX_VALUE;
            }
        }

        // Replace old arrays with newly created ones
        gameBoard = newGameBoard;
        nodes = newNodes;
    }

    public void restartBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gameBoard[i][j] = 0;
                nodes[i][j] = new Node(i, j, false, false, false, false);
                nodes[i][j].gCost = Integer.MAX_VALUE;
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

    // Empty Node instantiation
    public Grid() {
        nodes = new Node[rows][cols];
        // Initialize the grid with empty nodes
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nodes[i][j] = new Node(i, j);
            }
        }
    }

    private void backtrackPath(Node goalNode) {
        finalPath.clear();
        Node current = goalNode;
        while (current != null) {
            finalPath.addFirst(current);
            current = current.parent;

            try {
                Thread.sleep(pathSpeed); // sleep for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Get Neighbors from current node
    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        neighborList.clear();

        int x = node.getX();
        int y = node.getY();

        if (x + 1 < rows && !nodes[x + 1][y].isWall()) {
            neighbors.add(nodes[x + 1][y]);
            neighborList.add(nodes[x + 1][y]);
        }

        if (x - 1 >= 0 && !nodes[x - 1][y].isWall()) {
            neighbors.add(nodes[x - 1][y]);
            neighborList.add(nodes[x - 1][y]);
        }

        if (y + 1 < cols && !nodes[x][y + 1].isWall()) {
            neighbors.add(nodes[x][y + 1]);
            neighborList.add(nodes[x][y + 1]);
        }

        if (y - 1 >= 0 && !nodes[x][y - 1].isWall()) {
            neighbors.add(nodes[x][y - 1]);
            neighborList.add(nodes[x][y - 1]);
        }

        return neighbors;
    }

    // BFS Method HERE
    public void BFS() {

        while (!path.isEmpty()) {
            currentNode = path.poll();

            if (currentNode.isGoal()) {
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
                Thread.sleep(searchSpeed); // sleep
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Dijkstra's Algorithm
    public void dijkstra() {

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.gCost));

        //Resetting nodes
        
        if (startNode != null) {
            startNode.gCost = 0;
            pq.add(startNode);

            while (!pq.isEmpty()) {
                Node currentNode = pq.poll();

                if (currentNode.isFinish) {
                    backtrackPath(currentNode);
                    return;
                }

                List<Node> neighbors = getNeighbors(currentNode);
                for (Node neighbor : neighbors) {
                    if (!neighbor.isVisited()) {
                        int newDistance = currentNode.gCost + 1; // Change in future for weighted edges
                        if (newDistance < neighbor.gCost) {
                            neighbor.gCost = newDistance;
                            neighbor.parent = currentNode;
                            pq.add(neighbor);
                        }
                    }
                }

                // Update outside PQ
                dijkstraPQ = pq;

                // Set Current to true
                currentNode.isVisited = true;

                try {
                    Thread.sleep(searchSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Calculate Manhattan Distance for A*
    public int calculateManhattanDistance(Node nodeA, Node nodeB) {
        return Math.abs(nodeA.x - nodeB.x) + Math.abs(nodeA.y - nodeB.y);
    }

    // AStar Algorithm
    public void aStar() {

        // Sets for current execution
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
                if (closedSet.contains(neighbor) || neighbor.isWall())
                    continue;

                int tentativeGCost = currentNode.gCost + 1; // Assume cost is 1 for all edges
                if (tentativeGCost < neighbor.gCost) {
                    neighbor.parent = currentNode;
                    neighbor.gCost = tentativeGCost;
                    neighbor.hCost = calculateManhattanDistance(neighbor, endNode);

                    openSet.remove(neighbor);
                    openSet.add(neighbor);
                }
            }

            // Update outside sets
            oSet = openSet;
            cSet = closedSet;

            try {
                Thread.sleep(searchSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO -- no individual threads per algo

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

// Potentially add Diagonal Movement instead of Axial