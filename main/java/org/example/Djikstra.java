package org.example;

import java.util.*;

public class Djikstra {
    private Map<String, Vertex> vertexNames;

    public Djikstra(){
        vertexNames = new HashMap<>();
    }

    public Map<String, Vertex> getVertexNames() {
        return vertexNames;
    }

    public void addVertex(Vertex vertex) {
        if(vertexNames.containsKey(vertex.getName())){
            throw new IllegalArgumentException(
                    String.format("Vertex with name: %s, already exists! Cannot create new vertex with existing name!", vertex.getName()));
        }
        vertexNames.put(vertex.getName(), vertex);
    }

    public Collection<Vertex> getVertices() {
        return vertexNames.values();
    }

    public Vertex getVertex(String key) {
        return vertexNames.get(key);
    }

    public void addEdge(String sourceName, String targetName, Double weight) {
        if(!vertexNames.containsKey(sourceName)){
            throw new IllegalArgumentException(String.format(
                    "%s does not exist! Cannot create edge!", sourceName
            ));
        }
        if(!vertexNames.containsKey(targetName)){
            throw new IllegalArgumentException(String.format(
                    "%s does not exist! Cannot create edge!", targetName
            ));
        }
        Vertex source = vertexNames.get(sourceName);
        Vertex target = vertexNames.get(targetName);
        Edge edge = new Edge(source, target, weight);
        source.add(edge);
    }

    public void addUndirectedEdge(String sourceName, String targetName, double weight) {
        addEdge(sourceName, targetName, weight);
        addEdge(targetName, sourceName, weight);
    }

    public double computeEuclideanDistance(Vertex target, Vertex source) {
        return Math.sqrt(Math.pow(target.getX() - source.getY(), 2) + Math.pow(target.getY() - source.getY(), 2));
    }

    public List<Edge> getPath(Vertex source, Vertex target) {
        execute(source);
        List<Edge> path = new LinkedList<>();
        Vertex end = target;
        while(end != source){
            Vertex previous = end.getPrev();

            if(previous == null){
                return new LinkedList<>();
            }

            for(Edge edge: previous.getAdjacent()){
                if(edge.getTarget() == end){
                    path.add(0, edge);
                }
            }
            end = previous;
        }

        return path;
    }

    private void execute(Vertex source) {
        for (Vertex v: vertexNames.values()){
            v.setDistance(Double.POSITIVE_INFINITY);
        }

        List<Vertex> visited = new LinkedList<>();
        List<Vertex> pending = new LinkedList<>();
        pending.add(source);
        source.setDistance(0);

        while(!pending.isEmpty() && visited.size() < vertexNames.size()){
            Vertex min = findMin(pending);
            visited.add(min);

            for(Edge edge: min.getAdjacent()){
                Vertex target = edge.getTarget();
                if(target.getDistance() == Double.POSITIVE_INFINITY){
                    target.setDistance(min.getDistance() + edge.getWeight());
                    target.setPrev(edge.getSource());
                }
                else if(min.getDistance() + edge.getWeight() < target.getDistance()){
                    target.setDistance(min.getDistance() + edge.getWeight());
                    target.setPrev(min);
                }

                if(!visited.contains(target) && !pending.contains(target)){
                    pending.add(target);
                }
            }
        }
    }

    private Vertex findMin(List<Vertex> pending) {
        double min = pending.get(0).getDistance();
        int minIndex = 0;

        for(int i = 1; i < pending.size(); i++){
            if(pending.get(i).getDistance() < min){
                min = pending.get(i).getDistance();
                minIndex = i;
            }
        }

        return pending.remove(minIndex);
    }

    public static void main(String[] args){
        Djikstra djikstra = new Djikstra();
        Vertex arad = new Vertex("Arad", 0, 0);
        djikstra.addVertex(arad);
        Vertex zerind = new Vertex("Zerind", 0, 1);
        djikstra.addVertex(zerind);
        Vertex oradea = new Vertex("Oradea", 0, 2);
        djikstra.addVertex(oradea);
        Vertex sibiu = new Vertex("Sibiu", 3, 0);
        djikstra.addVertex(sibiu);
        Vertex timisoara = new Vertex("Timisoara", 0, -3);
        djikstra.addVertex(timisoara);
        Vertex lugoj = new Vertex("Lugoj", 2, -4);
        djikstra.addVertex(lugoj);
        Vertex mehadia = new Vertex("Mehadia", 2, -5);
        djikstra.addVertex(mehadia);
        Vertex dobreta = new Vertex("Dobreta", 2, -6);
        djikstra.addVertex(dobreta);
        Vertex rimnicuVilcea = new Vertex("Rimnicu Vilcea", 4, -3);
        djikstra.addVertex(rimnicuVilcea);
        Vertex craiova = new Vertex("Craiova", 4, -6);
        djikstra.addVertex(craiova);
        Vertex fagaras = new Vertex("Fagaras", 5, 0);
        djikstra.addVertex(fagaras);
        Vertex pitesti = new Vertex("Pitesti", 5, -4);
        djikstra.addVertex(pitesti);
        Vertex bucharest = new Vertex("Bucharest", 8, -5);
        djikstra.addVertex(bucharest);
        Vertex giurgiu = new Vertex("Giurgiu", 8, -7);
        djikstra.addVertex(giurgiu);
        Vertex urziceni = new Vertex("Urziceni", 10, -5);
        djikstra.addVertex(urziceni);
        Vertex hirsova = new Vertex("Hirsova", 12, -5);
        djikstra.addVertex(hirsova);
        Vertex eforie = new Vertex("Eforie", 13, -7);
        djikstra.addVertex(eforie);
        Vertex vaslui = new Vertex("Vaslui", 12, 0);
        djikstra.addVertex(vaslui);
        Vertex iasi = new Vertex("Iasi", 11, 1);
        djikstra.addVertex(iasi);
        Vertex neamt = new Vertex("Neamt", 8, 2);
        djikstra.addVertex(neamt);



        djikstra.addEdge(arad.getName(), zerind.getName(), 75d);
        djikstra.addEdge(zerind.getName(), oradea.getName(), 71d);
        djikstra.addEdge(oradea.getName(), sibiu.getName(), 151d);
        djikstra.addEdge(arad.getName(), sibiu.getName(), 140d);
        djikstra.addEdge(arad.getName(), timisoara.getName(), 118d);
        djikstra.addEdge(timisoara.getName(), lugoj.getName(), 111d);
        djikstra.addEdge(lugoj.getName(), mehadia.getName(), 70d);
        djikstra.addEdge(mehadia.getName(), dobreta.getName(), 75d);
        djikstra.addEdge(dobreta.getName(), craiova.getName(), 120d);
        djikstra.addEdge(sibiu.getName(), rimnicuVilcea.getName(), 80d);
        djikstra.addEdge(sibiu.getName(), fagaras.getName(), 99d);
        djikstra.addEdge(rimnicuVilcea.getName(), craiova.getName(), 146d);
        djikstra.addEdge(rimnicuVilcea.getName(), pitesti.getName(), 97d);
        djikstra.addEdge(craiova.getName(), pitesti.getName(), 138d);
        djikstra.addEdge(fagaras.getName(), bucharest.getName(), 211d);
        djikstra.addEdge(pitesti.getName(), bucharest.getName(), 101d);
        djikstra.addEdge(bucharest.getName(), giurgiu.getName(), 90d);
        djikstra.addEdge(bucharest.getName(), urziceni.getName(), 85d);
        djikstra.addEdge(urziceni.getName(), vaslui.getName(), 142d);
        djikstra.addEdge(urziceni.getName(), hirsova.getName(), 98d);
        djikstra.addEdge(vaslui.getName(), iasi.getName(), 92d);
        djikstra.addEdge(iasi.getName(), neamt.getName(), 87d);
        djikstra.addEdge(hirsova.getName(), eforie.getName(), 86d);

        System.out.println(djikstra.getPath(arad, bucharest));
    }


}
