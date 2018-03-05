package graphsearch;

import searchframework.SearchFramework;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends SearchFramework{
    private ArrayList<Block> finalArray;

    public BFS(int[][] matrix, Block initialState, Block finalState, int number) {
        super(matrix, initialState, finalState, number);  
        this.finalArray= new ArrayList();
    }
 
    @Override
    public ArrayList<Block> graphSearch(){
        Queue <int []> queue = new LinkedList <> ();
        
        queue.add(initialState.getArray());

        while (queue.peek() != null){
            int [] array = queue.remove();
            Block state= new Block(array);            
                
            try{
                ArrayList<String> possibles = actions(state);
                
                for(String move: possibles){
                    
                    if (goalTest(state)){
                        finalArray.add(new Block(array[0]-1,array[1],array[2]+1));
                        return finalArray;
                    }
                    else{
                        Block newState;
                        if(move.equals("up")){
                            newState = result(state,"up");
                            matrix[newState.getX()][newState.getY()] = 0;
                            int [] ar= {newState.getX(),newState.getY(),newState.getCount()+1};         
                            queue.add(ar);
                            
                        }
                        else if(move.equals("left")){
                            newState = result(state,"left");
                            matrix[newState.getX()][newState.getY()] = 0;
                            int [] ar= {newState.getX(),newState.getY(),newState.getCount()+1};           
                            queue.add(ar);
                        }
                         else if(move.equals("right")){
                            newState = result(state,"right");
                            matrix[newState.getX()][newState.getY()] = 0;
                            int [] ar= {newState.getX(),newState.getY(),newState.getCount()+1};      
                             queue.add(ar);
                        }
                        else if(move.equals("down")){
                            newState = result(state,"down");
                            matrix[newState.getX()][newState.getY()] = 0;
                            int [] ar= {newState.getX(),newState.getY(),newState.getCount()+1};                           
                             queue.add(ar);
                        }
                        else{
                            newState=null;
                        }
                                            
                        finalArray.add(newState);       
                        }             
                    }
                 
                }catch(Exception e){
                }               
            }       
        return null;
    }   
}
