package org.example;

import java.util.LinkedList;
import java.util.List;

public class Vertex {
    private String name;
    private int x, y;
    private boolean known;
    private double distance;
    private List<Edge> adjacent;
    private Vertex prev;

    public Vertex(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
        adjacent = new LinkedList<>();
        prev = null;
        known = false;
        distance = 0.0;
    }

    public String getName() {
        return name;
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

    public List<Edge> getAdjacent() {
        return adjacent;
    }


    public void add(Edge adjacentEdge) {
        adjacent.add(adjacentEdge);
    }

    public Vertex getPrev() {
        return prev;
    }

    /**
     * Assume vertices have unique names
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Vertex)){
            return false;
        }

        Vertex objVertex = (Vertex) obj;

        return name.equals(objVertex.getName()) &&
                x == objVertex.getX() && y == objVertex.getY();
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %s)", name, x, y);
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }
}
