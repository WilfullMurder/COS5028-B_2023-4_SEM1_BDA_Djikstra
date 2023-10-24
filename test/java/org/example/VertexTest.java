package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.Transient;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {
    Vertex vertex;
    @BeforeEach
    void setUp() {
        vertex = new Vertex("Arad", 0, 0);
    }
    @Test
    void hasName(){
        assertEquals("Arad", vertex.getName());
    }
    @Test
    void testSetXPosition(){
        vertex.setX(1);
        assertEquals(1, vertex.getX());
    }
    @Test
    void testSetYPosition(){
        vertex.setY(1);
        assertEquals(1, vertex.getY());
    }
    @Test
    void hasAdjacentEdges(){
        assertNotNull(vertex.getAdjacent());
    }
    @Test
    void testAddEdge(){
        vertex.add(new Edge(vertex, new Vertex("Zerind", 1, 1), 75));
        assertEquals(1, vertex.getAdjacent().size());
    }
    @Test
    void hasPrevious(){
        assertNull(vertex.getPrev());
    }
    @Test
    void testHashCode(){
        Vertex tmp = new Vertex("Zerind", 0, 0);
        assertNotEquals(vertex.hashCode(), tmp.hashCode());
    }
    @Test
    void testIsNotEqual(){
        Vertex tmp = new Vertex("Zerind", 0, 0);
        assertFalse(vertex.equals(tmp));
    }
    @Test
    void testIsEqual(){
        Vertex tmp = new Vertex("Arad", 0, 0);
        assertTrue(vertex.equals(tmp));
    }
    @Test
    void testToString(){
        assertEquals("Arad (0, 0)", vertex.toString());
    }
    @Test
    void testSetDistance(){
        vertex.setDistance(1.0);
        assertEquals(1.0, vertex.getDistance());
    }
    @Test
    void testSetKnown(){
        vertex.setKnown(true);
        assertTrue(vertex.isKnown());
    }

}