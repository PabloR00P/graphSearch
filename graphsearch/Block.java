package graphsearch;


public class Block {
    private int x;
    private int y;
    private int count;

    public Block(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
    
    public Block(int [] n){
        this.x= n[0];
        this.y= n[1];
        this.count= n[2];
    }
    
    public int[] getArray(){
        int [] array = {this.x,this.y,this.count};
        return array;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    
    
    
}
