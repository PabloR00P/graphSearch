
package graphsearch;

public class Location {
    public final int x,y;
    public int cost;
    public Location(int [] w) {
        this.x = w[0];
        this.y = w[1];
        this.cost= w[2];
    }
    
    
     @Override public int hashCode() {
        return 10000 * y + x; //codigo unico
    }

    
     @Override public boolean equals(final Object obj) {
        return this.getClass().equals(obj.getClass()) && hashCode() == obj.hashCode();
    }
}