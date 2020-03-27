package com.adasumizox.processing;

import com.adasumizox.processing.Processing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.image.BufferedImage;

public class TestProcessing {
    Processing p;
    BufferedImage bi;

    @BeforeEach
    public void testSetup() {
        p = new Processing();
        bi = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
    }

    @Test
    void testBlurForDimensionChange() {
        Assertions.assertEquals(bi.getWidth(), p.blur(bi).getWidth());
        Assertions.assertEquals(bi.getHeight(), p.blur(bi).getHeight());
    }

    @Test
    void testMedianFilterForDimensionChange() {
        Assertions.assertEquals(bi.getWidth(), p.medianFilter(bi).getWidth());
        Assertions.assertEquals(bi.getHeight(), p.medianFilter(bi).getHeight());
    }

    @Test
    void testSharpenFilterForDimensionChange() {
        Assertions.assertEquals(bi.getWidth(), p.sharpenFilter(bi).getWidth());
        Assertions.assertEquals(bi.getHeight(), p.sharpenFilter(bi).getHeight());
    }

    @Test
    void testGrayScaleForDimensionChange() {
        Assertions.assertEquals(bi.getWidth(), p.grayScale(bi).getWidth());
        Assertions.assertEquals(bi.getHeight(), p.grayScale(bi).getHeight());
    }

    @Test
    void testSobelFilterForDimensionChange() {
        Assertions.assertEquals(bi.getWidth(), p.sobelFilter(bi).getWidth());
        Assertions.assertEquals(bi.getHeight(), p.sobelFilter(bi).getHeight());
    }

    @Test
    void testBinarizationThresholdFilterForDimensionChange() {
        Assertions.assertEquals(bi.getWidth(), p.binarizationThreshold(bi, 255/2).getWidth());
        Assertions.assertEquals(bi.getHeight(), p.binarizationThreshold(bi, 255/2).getHeight());
    }

    @Test
    void testImageDilatationForDimensionChange() {
        Assertions.assertEquals(bi.getWidth(), p.imageDilatation(bi).getWidth());
        Assertions.assertEquals(bi.getHeight(), p.imageDilatation(bi).getHeight());
    }

    @Test
    void testImageErosionForDimensionChange() {
        Assertions.assertEquals(bi.getWidth(), p.imageErosion(bi).getWidth());
        Assertions.assertEquals(bi.getHeight(), p.imageErosion(bi).getHeight());
    }

    @Test
    void testBlurForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.blur(bi).getRGB(0, 0));
    }

    @Test
    void testMedianFilterForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.medianFilter(bi).getRGB(0, 0));
    }

    @Test
    void testSharpenFilterForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.sharpenFilter(bi).getRGB(0, 0));
    }

    // It's one of tests that don't work in 3x3 px frames but works on every pixel
    @Test
    void testGrayScaleForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.grayScale(bi).getRGB(0, 0));
    }

    @Test
    void testSobelFilterForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.sobelFilter(bi).getRGB(0, 0));
    }

    @Test
    void testBinarizationThresholdForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.binarizationThreshold(bi, 255/2).getRGB(0, 0));
    }

    @Test
    void testImageDilatationForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.imageDilatation(bi).getRGB(0,0));
    }

    @Test
    void testImageErosionForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0,0), p.imageDilatation(bi).getRGB(0, 0));
    }


}
