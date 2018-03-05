package graphsearch;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T>{

    private List<Tuple<T, Integer>> elements = new ArrayList<>();

    public int count(){
        return elements.size();
    }

    /**
     * Adds an element to the queue with a given priority.
     */
    public void enqueue(T item, int priority){
        elements.add(new Tuple<>(item, priority));
    }

    /**
     * Pops and returns the element with the lowest priority
     * in the queue.
     */
    public T dequeue(){
        int bestIndex = 0; // Assume the first element is the smallest

        for (int i = 0; i < count(); i++) {
            if (elements.get(i).y < elements.get(bestIndex).y) // Compare priorities
                bestIndex = i;
        }

        T bestItem = elements.get(bestIndex).x;
        elements.remove(bestIndex);
        return bestItem;
    }
}