package graphsearch;

public class Solver {
    public InterfaceSolver getSolver(String method, int [][] matrix, Block c1,Block c2 , int number){
        if(method.equals("bfs")){
            return new BFS(matrix,c1,c2,number);
        }
        else if(method.equals("dfs")){
             return new Dfs(matrix,c1,c2,number);
        
        }
         else if(method.equals("a*")){
             return new Astar(matrix,c1,c2,number);
        
        }
        return null;
    }
    
}
