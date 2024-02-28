# AStar-Grid-Pathfinding
An updated and remastered version of my previous search algorithm program. Includes ability to set end and start states, walls, and choose between BFS, Djikstra's, and AStar search algorithms.

User can place walls by clicking or dragging with mouse, walls are not passible by the algorithm's search state. The user can set start and 'goal' states for the algorithm by selecting the respective button and clicking a cell. 

The 'Grid Size' selector allows users to change the size of the grid in multiples of 10, up to a size of 220 x 220. If changing grid size, this should be done before placing any walls; as changing the grid size will clear any previously placed cell states, due to resizing the grid arrays, nodes available and queues.

'Search Speed' displays the speed at which the algorithm's fronteir seaches. 
'Path Speed' traces back the most efficient path taken from the start to end state.
Both Search and Path speeds can be updated during runtime.

Before executing the algorithm to begin searching, one must press the algorithm they would like to use. In the initial gamestate, A* is initially selected.