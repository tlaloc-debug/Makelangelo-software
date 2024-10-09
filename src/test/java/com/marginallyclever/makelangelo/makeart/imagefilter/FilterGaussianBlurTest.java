package com.marginallyclever.makelangelo.makeart.imagefilter;

import com.marginallyclever.makelangelo.makeart.TransformedImage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FilterGaussianBlurTest {

    private TransformedImage img;
    private FilterGaussianBlur filterGaussianBlur;
    private BufferedImage bufferedImage;

    @BeforeEach
    public void setUp() throws IOException {
        // Arrange
        bufferedImage = ImageIO.read(new File("src/test/resources/mandrill.png"));
        img = new TransformedImage(bufferedImage);
        filterGaussianBlur = new FilterGaussianBlur(img, 5);
    }

    @Test
    public void testGaussianBlurFilter() throws IOException {
        // Act
        TransformedImage result = filterGaussianBlur.filter();

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(bufferedImage.getWidth(), result.getSourceImage().getWidth(), "Filtered image width should match input width");
        assertEquals(bufferedImage.getHeight(), result.getSourceImage().getHeight(), "Filtered image height should match input height");

        // Check that the filter has actually changed the image
        boolean imageChanged = false;
        for (int x = 0; x < bufferedImage.getWidth() && !imageChanged; x++) {
            for (int y = 0; y < bufferedImage.getHeight() && !imageChanged; y++) {
                if (bufferedImage.getRGB(x, y) != result.getSourceImage().getRGB(x, y)) {
                    imageChanged = true;
                }
            }
        }
        assertTrue(imageChanged, "The filter should have changed at least one pixel in the image");
    }

    @Test
    public void testGetGaussianBlurFilter() {
        // Act
        ConvolveOp horizontalFilter = filterGaussianBlur.getGaussianBlurFilter(true);
        ConvolveOp verticalFilter = filterGaussianBlur.getGaussianBlurFilter(false);

        // Assert
        assertNotNull(horizontalFilter, "Horizontal Gaussian blur filter should not be null");
        assertNotNull(verticalFilter, "Vertical Gaussian blur filter should not be null");
    }
}

