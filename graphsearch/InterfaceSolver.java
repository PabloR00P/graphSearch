package graphsearch;

import java.util.ArrayList;

public interface InterfaceSolver {
    
    public boolean goalTest(Block estado);
         
    public ArrayList<String> actions(Block casilla);
    
    public Block result(Block estado, String action);
    
    public int stepCost(Block estado, String action, Block estado2);
   
    public int pathCost(ArrayList<Block> lista, ArrayList<String> actions);
    
    public ArrayList<Block> graphSearch();
}
