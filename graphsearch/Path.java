package graphsearch;

import java.util.Map;

public class Path {

    private Map<Location, Location> path;
    private Location startLocation;
    private Location goalLocation = null;

    public Path(final Map<Location, Location> path, Location start) {
        this.path = path;
        this.startLocation = start;

        for (Location key : path.keySet()){
            if (path.get(key) == null){
                goalLocation = key;
            }
        }
    }

    public Location getStartLocation(){
        return startLocation;
    }

    public Location getNext(Location currentLocation){
        return path.get(currentLocation);
    }

    public Location getGoalLocation(){
        return goalLocation;
    }
}