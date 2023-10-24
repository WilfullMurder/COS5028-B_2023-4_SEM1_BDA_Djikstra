package org.example;

public class Edge {
    private double weight;
    private Vertex source, target;

    public Edge(Vertex source, Vertex target, double weight){
        this.source = source;
        this.target = target;
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }

    public Vertex getSource() {
        return source;
    }
    public Vertex getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", source.getName(), target.getName());
    }
}
