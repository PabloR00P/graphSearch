package graphsearch;

public interface WeightedGraph {
    
    public int cost(Location location); //Aqui obtenemos el costo de cada posicion
    public Iterable<Location> neighbors(Location id);
    
}