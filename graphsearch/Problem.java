package graphsearch;
import java.util.ArrayList;
import java.util.Map;

public class Problem {

    private int[][] matrix;
    private int nex;
    private int[] inicio;
    private int[] end;
    private int n;
    private boolean[][] visited;  
    private boolean done = false;
    private ArrayList<Block> listaCasillas;
      
    public Problem(int n, int matrix[][],ArrayList<Block> c){  
        this.n=n;
        this.matrix= matrix;
        this.listaCasillas=c;
        Draw.setXscale(0, n+2);
        Draw.setYscale(0, n+2);
        this.end=getFinish();
        init();
    }
    public int[] getFinish(){
        for(int i=0; i<matrix.length;i++){
            for(int j=0; j<matrix.length;j++){
                if(matrix[i][j]==2){
                    int[] finish = {i,j,0};
                    return finish;
                }
            }
        }
        return null;
    }
  
    private void init() {
       // aqui se inicializan las celdas fronterizas como ya fueron visitadas
        visited = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            visited[x][0] = true;
            visited[x][n+1] = true;
        }
        for (int y = 0; y < n+2; y++) {
            visited[0][y] = true;
            visited[n+1][y] = true;
        }
      
    }
    

    private int counter=0;
    private Block temp;
   
    public void solve() {
        for (int x = 1; x <= n; x++)
            for (int y = 1; y <= n; y++)
                visited[x][y] = false;
        done = false;
    }

 
    public void draw() {
        Draw.setPenColor(Draw.BLACK);
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= n; x++) {
                try{
                    if(matrix[y][x]==0){
                       Draw.setPenColor(Draw.BLACK);
                       Draw. filledRectangle(x+0.5, n-(y-1)+0.5, 0.5,0.5);
                       Draw.show();
                    }
                    if(matrix[y][x]==2){
                       Draw.setPenColor(Draw.GREEN);
                       Draw. filledRectangle(x+0.5, n-(y-1)+0.5, 0.5,0.5);
                       Draw.show();
                    }
                    if(matrix[y][x]==3){
                       Draw.setPenColor(Draw.RED);
                       Draw. filledRectangle(x+0.5, n-(y-1)+0.5, 0.5,0.5);
                       Draw.show();
                    }    
                }catch(Exception e){}        
            }
        }
        Draw.show();
        Draw.pause(1000);      
    }
    
    public int[][] resolve(){
        Draw.enableDoubleBuffering();
        draw();
        solve();
        revisarCasillas();
        return matrix;
    }
    
      public ArrayList<Block> revisarCasillas(){
          
        ArrayList<Integer> listNums= new ArrayList();
        ArrayList<Block> caminoFinal = new ArrayList();
        Block current = listaCasillas.get(listaCasillas.size()-1);
        if(listaCasillas.get(0).getCount()==9){
            int c= 0;
            for(Block casi: listaCasillas){
                if(c<=listaCasillas.size()-1){
                  
                        current=casi;
                        Draw.setPenColor(Draw.BLUE);
                        Draw.filledRectangle(casi.getX() + 0.5, n-(casi.getY()) + 0.5, 0.5,0.5);
                        Draw.pause(30);
                        Draw.show();  
                    }
                         
                   c++;
                
                     
            }
        }
        else{
            listaCasillas.remove(listaCasillas.size()-1);

            for(int var=listaCasillas.size()-1;var>0;var--){
 
                if(esAnexo(current,listaCasillas.get(var))) {
                            current= listaCasillas.get(var);
                            caminoFinal.add(current);
                            listNums.add(current.getCount());
                            Draw.setPenColor(Draw.GREEN);
                            Draw.filledRectangle(current.getY() + 0.5, n-(current.getX()-1) + 0.5, 0.5,0.5);
                            Draw.pause(30);
                            Draw.show();
                } 

            }

        
        }   
        return caminoFinal;
        
    }
    public boolean esAnexo(Block casilla, Block casilla2){
        if(casilla.getX()==casilla2.getX()+1
           && casilla.getY() == casilla2.getY()){
            return true;
        }
        
        if(casilla.getX()==casilla2.getX()-1
           && casilla.getY() == casilla2.getY()){
            return true;
        }
        
        if(casilla.getX()==casilla2.getX()
           && casilla.getY() == casilla2.getY()+1){
            return true;
        }
        
        if(casilla.getX()==casilla2.getX()
           && casilla.getY() == casilla2.getY()-1){
            return true;
        }
            
        // SI ES EL INICIO
         if(casilla.getX()==casilla2.getX()
           &&casilla.getY() == casilla2.getY()){
            return true;
        }
        
        return false;
    }
    

     public int distance(int[]b){
        return (int)(Math.pow(this.end[0] - b[0], 2) + Math.pow(this.end[1] - b[1], 2)); // Pythagorean theorem
    }
    
    public int[][] getMatrix() {
        return matrix;
    }

    
}