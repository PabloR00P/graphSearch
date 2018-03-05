package graphsearch;

import searchframework.SearchFramework;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Astar extends SearchFramework {
    
    private Map<Location, Location> cameFrom;
    private int nex;
    private ArrayList<Block> finalArray;
        
    public Astar(int[][] matrix, Block initialState, Block finalState, int number) {
        super(matrix, initialState, finalState, number);  
        this.finalArray= new ArrayList<>();
    }

    @Override
    public ArrayList<Block> graphSearch(){  
        cameFrom = new HashMap<>();
        HashMap<Location, Integer> costSoFar = new HashMap<>();
        Location goal = new Location(finalState.getArray());

        PriorityQueue<int[]> frontier = new PriorityQueue<>();
        frontier.enqueue(this.initialState.getArray(), 0);

        cameFrom.put(new Location(this.initialState.getArray()),new Location(this.initialState.getArray()));
        costSoFar.put(new Location(this.initialState.getArray()), 0);

        ArrayList<Location> locs= new ArrayList();

        while (frontier.count()>0){

            int [] array = frontier.dequeue();
            Location current = new Location(array);
            
            this.finalArray.add(new Block(array[1],array[0]-1,9));
           
            try{
                if(array[0]-1 >= 0 && matrix[array[0]-1][array[1]] != 0){

                    if (matrix[array[0]-1][array[1]] == 2){
                       
                        return finalArray;
                    }
                    else{
                        matrix[array[0]-1][array[1]] = 0;
                        int [] temp = {array[0]-1, array[1],array[2]+1};
                        locs.add(new Location(temp));
                    }
                }
            }catch(Exception e){}

                try{
                    if(array[1]-1 >= 0 && matrix[array[0]][array[1]-1] != 0){

                    if (matrix[array[0]][array[1]-1] == 2){
                       
                        return finalArray;
                    }
                    else{
                        matrix[array[0]][array[1]-1] = 0;
                        int [] temp = {array[0], array[1]-1,array[2]+1};
                        locs.add(new Location(temp));
                    }
                }
                }catch(Exception e){}

                 try{
                    if(array[1]+1 <= matrix.length && matrix[array[0]][array[1]+1] != 0){

                    if (matrix[array[0]][array[1]+1] == 2){
                        
                        return finalArray;
                    }
                    else{
                        matrix[array[0]][array[1]+1] = 0;
                        int [] temp = {array[0], array[1]+1, array[2]+1};
                        locs.add(new Location(temp));           
                    }
                }

                }catch(Exception e){/*donothing*/}

                try{
                    if(array[0]+1 <= matrix.length && matrix[array[0]+1][array[1]] != 0){

                    if (matrix[array[0]+1][array[1]] == 2){
                       
                        return finalArray;
                    }
                    else{
                        matrix[array[0]+1][array[1]] = 0;
                        int [] temp = {array[0]+1, array[1], array[2]+1};
                        locs.add(new Location(temp));         
                    }
                 }
                }catch(Exception e){}

            for (Location next : locs){
                //aqui se calcula el costo del vecino
                int newCost = costSoFar.get(current) + next.cost;

                //aqui se agrega el siguiente si no lo visitó o si la ruta actual es más corta que la siguiente
                if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)){
                    costSoFar.put(next, newCost);
                    int priority = heuristic1(next, goal);
                    int [] nextLocation= {next.x,next.y,next.cost};
                    nex= next.cost;
                    frontier.enqueue(nextLocation,priority);
                    cameFrom.put(next, current);
                }
            }         
        }
        
        
        return finalArray;
    }

    // euclidean distance
    public static int heuristic1(Location a, Location b){
        return (int)(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
    
    // manhattan distance
    public static int heuristic2(Location a, Location b){
        return (int)Math.abs(b.x-a.x) + Math.abs(b.y-a.y);
    }
    
    
    
    
}
