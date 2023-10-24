package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    Edge edge;
    @BeforeEach
    void setUp() {
        edge = new Edge(new Vertex("Arad", 0, 0), new Vertex("Zerind", 1, 1), 75);
    }
    @Test
    void hasWeight(){
        assertEquals(75.0, edge.getWeight());
    }
    @Test
    void hasSource(){
        assertNotNull(edge.getSource());
    }
    @Test
    void hasTarget(){
        assertNotNull(edge.getTarget());
    }
    @Test
    void testToString(){
        String expected = String.format("%s -> %s", edge.getSource().getName(), edge.getTarget().getName());
        assertEquals(expected, edge.toString());
    }
}