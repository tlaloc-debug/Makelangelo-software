package com.marginallyclever.convenience;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

import java.io.IOException;

class QuadGraphTest {

    private QuadGraph quadGraph;
    private Faker faker;
    private double minX,minY,maxX,maxY;
    @BeforeEach
    public void setUp() throws IOException {
        // Arrange
        faker = new Faker();
        minX = faker.number().randomDouble(4, -100, 0);
        minY = faker.number().randomDouble(4, -100, 0);
        maxX = faker.number().randomDouble(4, 0, 100);
        maxY = faker.number().randomDouble(4, 0, 100);
        quadGraph = new QuadGraph(minX, minY, maxX, maxY);
    }

    @Test
    public void testInsertOnePoint() {
        // Arrange
        double x = minX + (maxX - minX) * faker.number().randomDouble(4, 0, 1);
        double y = minY + (maxY - minY) * faker.number().randomDouble(4, 0, 1);
        Point2D point = new Point2D(x, y);

        // Act
        boolean result = quadGraph.insert(point);

        // Assert
        assertTrue(result, "The point should be inserted successfully");
        assertEquals(1, quadGraph.countPoints(), "The QuadGraph should contain 1 point after insertion");
    }

    @Test
    public void testInsertOverMaxPoints() {
        // Arrange
        for (int i = 0; i < 20; i++) {
            double x = minX + (maxX - minX) * faker.number().randomDouble(4, 0, 1);
            double y = minY + (maxY - minY) * faker.number().randomDouble(4, 0, 1);
            Point2D point = new Point2D(x, y);
            quadGraph.insert(point);
        }

        // Act
        int count = quadGraph.countPoints();

        // Assert
        assertEquals(20, count, "The QuadGraph should contain 2000 points after insertion");
    }

    @Test
    public void testCountPoints() {
        // Arrange
        for (int i = 0; i < 5; i++) {
            double x = minX + (maxX - minX) * faker.number().randomDouble(4, 0, 1);
            double y = minY + (maxY - minY) * faker.number().randomDouble(4, 0, 1);
            Point2D point = new Point2D(x, y);
            quadGraph.insert(point);
        }

        // Act
        int count = quadGraph.countPoints();

        // Assert
        assertEquals(5, count, "The QuadGraph should contain 5 points");
    }

    @Test
    public void testCountPointsChildren() {
        // Arrange
        for (int i = 0; i < 5; i++) {
            double x = minX + (maxX - minX) * faker.number().randomDouble(4, 0, 1);
            double y = minY + (maxY - minY) * faker.number().randomDouble(4, 0, 1);
            Point2D point = new Point2D(x, y);
            quadGraph.insert(point);
        }
        quadGraph.split();

        // Act
        int count = quadGraph.countPoints();

        // Assert
        assertEquals(5, count, "The QuadGraph should contain 5 points");
    }

    @Test
    public void testSearch() {
        // Arrange
        double x = minX + (maxX - minX) * faker.number().randomDouble(4, 0, 1);
        double y = minY + (maxY - minY) * faker.number().randomDouble(4, 0, 1);
        Point2D point = new Point2D(x, y);
        quadGraph.insert(point);

        // Act
        Point2D foundPoint = quadGraph.search(point);

        // Assert
        assertNotNull(foundPoint, "The point should be found");
        assertEquals(point, foundPoint, "The found point should be the same as the inserted point");
    }

    @Test
    public void testSearchChildren() {
        // Arrange
        double x = minX + (maxX - minX) * faker.number().randomDouble(4, 0, 1);
        double y = minY + (maxY - minY) * faker.number().randomDouble(4, 0, 1);
        Point2D point = new Point2D(x, y);
        quadGraph.insert(point);
        quadGraph.split();

        // Act
        Point2D foundPoint = quadGraph.search(point);

        // Assert
        assertNotNull(foundPoint, "The point should be found");
        assertEquals(point, foundPoint, "The found point should be the same as the inserted point");
    }

    @Test
    public void testSearchNotFound() {
        // Arrange
        double x = minX + (maxX - minX) * faker.number().randomDouble(4, 0, 1);
        double y = minY + (maxY - minY) * faker.number().randomDouble(4, 0, 1);
        Point2D point = new Point2D(x, y);

        // Act
        Point2D foundPoint = quadGraph.search(point);

        // Assert
        assertNull(foundPoint, "The point should not be found");
    }

    @Test
    void testSplit() {
        // Act
        quadGraph.split();

        // Assert
        assertNotNull(quadGraph.children, "The QuadGraph should have split into children");
        assertEquals(4, quadGraph.children.length, "The QuadGraph should have exactly four children after splitting");
        assertEquals(0, quadGraph.sites.size(), "The QuadGraph should have no points after splitting");
    }
}
