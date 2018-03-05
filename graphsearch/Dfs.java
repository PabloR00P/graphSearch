package graphsearch;

import searchframework.SearchFramework;
import java.util.ArrayList;

public class Dfs extends SearchFramework{
    private ArrayList<Block> finalArray;
    private boolean done = false;
    private int counter=0;
    private int x;
    private int y;
    private Block temp;

    public Dfs(int[][] matrix, Block initialState, Block finalState, int number) {
        super(matrix, initialState, finalState, number);  
        this.finalArray= new ArrayList();
    }

    @Override
    public ArrayList<Block> graphSearch(){
                
        ArrayList<int[]> queue = new ArrayList<> ();
        boolean first = true;
        queue.add(initialState.getArray());
        

        while (!queue.isEmpty()){
            
            
            int [] array = queue.get(queue.size()-1);
            Block state= new Block(array);   
            if(first){
                finalArray.add(state);
                first=false;
            }
            
            try{
                ArrayList<String> possibles = actions(state);
                
                if(!possibles.isEmpty()){
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
                            int [] ar= {newState.getX(),newState.getY(),distance(newState)};         
                            queue.add(ar);
                            finalArray.add(newState); 
                            break;
                            
                        }
                          else if(move.equals("down")){
                            newState = result(state,"down");
                            matrix[newState.getX()][newState.getY()] = 0;
                            int [] ar= {newState.getX(),newState.getY(),distance(newState)};                           
                            queue.add(ar);
                            finalArray.add(newState);    
                            break;
                        }
                        else if(move.equals("left")){
                            newState = result(state,"left");
                            matrix[newState.getX()][newState.getY()] = 0;
                            int [] ar= {newState.getX(),newState.getY(),distance(newState)};           
                            queue.add(ar);
                            finalArray.add(newState); 
                            break;
                        }
                        
                       
                         else if(move.equals("right")){
                            newState = result(state,"right");
                            matrix[newState.getX()][newState.getY()] = 0;
                            int [] ar= {newState.getX(),newState.getY(),distance(newState)};      
                             queue.add(ar);
                             finalArray.add(newState); 
                             break;
                        }
                       
                         
                        }             
                    }
                }
                else{
                    queue.remove(queue.size()-1);
                }
              
                 
                }catch(Exception e){
                    queue.remove(queue.size()-1);
                    e.printStackTrace();
                }               
            }       
        return finalArray;

    }
    
    // euclidean distance
    public int distance(Block a){
        return (int)(Math.abs(a.getX()-initialState.getX()) + Math.abs( a.getY() - initialState.getY())); // Pythagorean theorem
    }       
}
