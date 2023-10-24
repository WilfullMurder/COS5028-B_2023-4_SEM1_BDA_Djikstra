package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DjikstraTest {
    Djikstra djikstra;
    Vertex Arad = new Vertex("Arad", 0, 0);
    Vertex zerind = new Vertex("Zerind", 1, 1);
    @BeforeEach
    void setUp() {
        djikstra = new Djikstra();
        djikstra.addVertex(Arad);
    }
    @Test
    void hasVertexNames(){
        assertNotNull(djikstra.getVertexNames());
    }
    @Test
    void testAdd(){
        assertEquals(1, djikstra.getVertexNames().size());
        assertThrows(IllegalArgumentException.class, () -> {
            djikstra.addVertex(Arad);
        });
    }
    @Test
    void testGetVertices(){
        assertNotNull(djikstra.getVertices());
    }
    @Test
    void testGetVertex(){
        assertEquals(Arad, djikstra.getVertex("Arad"));

        Vertex tmp = new Vertex("Zerind", 1, 1);
        assertNotEquals(tmp, djikstra.getVertex("Zerind"));
    }
    @Test
    void testAddEdge(){

        assertThrows(IllegalArgumentException.class, ()->{
            djikstra.addEdge(zerind.getName(), Arad.getName(), 75d);
        });
        assertThrows(IllegalArgumentException.class, ()->{
            djikstra.addEdge(Arad.getName(), zerind.getName(), 75d);
        });

        djikstra.addVertex(zerind);
        djikstra.addEdge(Arad.getName(), zerind.getName(), 75d);
        assertEquals(75d, Arad.getAdjacent().get(0).getWeight());
        assertEquals(zerind.getName(), Arad.getAdjacent().get(0).getTarget().getName());
    }
    @Test
    void testAddUndirectedEdge(){
        djikstra.addVertex(zerind);
        djikstra.addUndirectedEdge(Arad.getName(), zerind.getName(), 75d);
        assertEquals(Arad.getAdjacent().get(0).getTarget(), zerind);
        assertEquals(zerind.getAdjacent().get(0).getTarget(), Arad);
    }
    @Test
    void testComputeEuclideanDistance(){
        djikstra.addVertex(zerind);
        double expected = Math.sqrt(Math.pow(zerind.getX()-Arad.getX(), 2) + Math.pow(zerind.getY() - Arad.getY(), 2));
        assertEquals(expected, djikstra.computeEuclideanDistance(zerind, Arad));
    }
    @Test
    void testGetPath(){
        djikstra.addVertex(zerind);
        djikstra.addEdge(Arad.getName(), zerind.getName(), 75d);
        Vertex oradea = new Vertex("Oradea", 2, 2);
        djikstra.addVertex(oradea);
        djikstra.addEdge(zerind.getName(), oradea.getName(), 71d);
        List<Edge> expected = Arrays.asList(Arad.getAdjacent().get(0), zerind.getAdjacent().get(0));
        List<Edge> actual = djikstra.getPath(Arad, oradea);
        assertEquals(expected, actual);
    }
}
