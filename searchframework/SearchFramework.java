package searchframework;

import graphsearch.Block;
import java.util.ArrayList;
import graphsearch.InterfaceSolver;

public abstract class SearchFramework implements InterfaceSolver {
    public Block initialState;
    public Block finalState;
    public int[][] matrix;
    public int number;
   
    
    public SearchFramework(int[][] matrix, Block initialState,Block finalState,int number) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.matrix = matrix;
        this.number = number;
    }
   
    @Override
    public boolean goalTest(Block estado){
        try{
            if(matrix[estado.getX()-1][estado.getY()]==2){
            return true;
            }
            else if(matrix[estado.getX()][estado.getY()-1]==2){
                return true;
            }
            else if(matrix[estado.getX()][estado.getY()+1]==2){
                return true;
            }
            else if(matrix[estado.getX()+1][estado.getY()]==2){
                return true;
            }
            return false;
        }
        catch(Exception e){
            return false;
        }
    }
    
    @Override
    public ArrayList<String> actions(Block block){
        ArrayList<String> possibleMoves = new ArrayList();
         if(block.getY()-1 >= 0 && matrix[block.getX()][block.getY()-1] != 0){
             possibleMoves.add("left");
        }
 
        if(block.getX()-1 >= 0 && matrix[block.getX()-1][block.getY()] != 0){
            possibleMoves.add("up");
        }
        
       
        if(block.getY()+1 <= matrix.length && matrix[block.getX()][block.getY()+1] != 0){
            possibleMoves.add("right");
        }
        
        if(block.getX()+1 <= matrix.length && matrix[block.getX()+1][block.getY()] != 0){
            possibleMoves.add("down");
        }
        
        return possibleMoves;
    }
    
    @Override
    public Block result(Block estado, String action){
        switch(action){
            case "up":
                return new Block(estado.getX()-1,estado.getY(),estado.getCount());
            case "left":
               return new Block(estado.getX(),estado.getY()-1,estado.getCount()); 
            case "right":
               return new Block(estado.getX(),estado.getY()+1,estado.getCount());
            case "down":
               return new Block(estado.getX()+1,estado.getY(),estado.getCount());
            default:
                return null;     
        }     
    }
    
    @Override
    public int stepCost(Block estado, String action, Block estado2){
        return 0;
    }
       
    @Override
    public int pathCost(ArrayList<Block> lista, ArrayList<String> actions){
        return 0;
    }
    
    @Override
    public ArrayList<Block> graphSearch() {
        return null;
    }
}
