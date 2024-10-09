package com.marginallyclever.makelangelo.makeart.imagefilter;

import com.marginallyclever.makelangelo.makeart.TransformedImage;
import com.marginallyclever.util.PreferencesHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FilterCMYKTest {
    private TransformedImage img;
    private FilterCMYK filterCMYK;

    @BeforeEach
    public void setUp() throws IOException {
        // Arrange
        BufferedImage bufferedImage = ImageIO.read(new File("src/test/resources/mandrill.png"));
        img = new TransformedImage(bufferedImage);
        filterCMYK = new FilterCMYK(img);
    }

    @Test
    public void testFilter() {
        // Act
        TransformedImage result = filterCMYK.filter();

        // Assert
        assertNotNull(result, "The result should not be null");
        assertNotNull(filterCMYK.getC(), "Cyan channel should not be null");
        assertNotNull(filterCMYK.getM(), "Magenta channel should not be null");
        assertNotNull(filterCMYK.getY(), "Yellow channel should not be null");
        assertNotNull(filterCMYK.getK(), "Black channel should not be null");

        assertTrue(filterCMYK.getC().getSourceImage().getWidth() > 0, "Cyan channel image should have width greater than 0");
        assertTrue(filterCMYK.getC().getSourceImage().getHeight() > 0, "Cyan channel image should have height greater than 0");

        assertTrue(filterCMYK.getM().getSourceImage().getWidth() > 0, "Magenta channel image should have width greater than 0");
        assertTrue(filterCMYK.getM().getSourceImage().getHeight() > 0, "Magenta channel image should have height greater than 0");

        assertTrue(filterCMYK.getY().getSourceImage().getWidth() > 0, "Yellow channel image should have width greater than 0");
        assertTrue(filterCMYK.getY().getSourceImage().getHeight() > 0, "Yellow channel image should have height greater than 0");

        assertTrue(filterCMYK.getK().getSourceImage().getWidth() > 0, "Black channel image should have width greater than 0");
        assertTrue(filterCMYK.getK().getSourceImage().getHeight() > 0, "Black channel image should have height greater than 0");
    }

    @Test
    public void testMainMethod() {
        // Arrange
        String[] args = {};

        // Act & Assert
        assertDoesNotThrow(() -> FilterCMYK.main(args),
                "Main method should run without throwing exceptions");
    }

    //@Test
    public void testConversion() throws IOException {
        PreferencesHelper.start();
        final String PATH_NAME = "target/classes/bill-murray";
        final String EXT = "jpg";
        File file = new File(PATH_NAME + "." + EXT);
        assert (file.isFile());
        TransformedImage img = new TransformedImage(ImageIO.read(new FileInputStream(file)));
        FilterCMYK filter = new FilterCMYK(img);
        filter.filter();

        ImageIO.write(filter.getC().getSourceImage(), EXT, new File(PATH_NAME + "C." + EXT));
        ImageIO.write(filter.getM().getSourceImage(), EXT, new File(PATH_NAME + "M." + EXT));
        ImageIO.write(filter.getY().getSourceImage(), EXT, new File(PATH_NAME + "Y." + EXT));
        ImageIO.write(filter.getK().getSourceImage(), EXT, new File(PATH_NAME + "K." + EXT));
    }
}
