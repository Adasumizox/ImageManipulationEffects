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
    void testBlurForWidthChange() {
        Assertions.assertEquals(bi.getWidth(), p.blur(bi).getWidth());
    }

    @Test
    void testBlurForHeightChange() {
        Assertions.assertEquals(bi.getHeight(), p.blur(bi).getHeight());
    }

    @Test
    void testBlurForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.blur(bi).getRGB(0, 0));
    }

    @Test
    void testMedianFilterForWidthChange() {
        Assertions.assertEquals(bi.getWidth(), p.medianFilter(bi).getWidth());
    }

    @Test
    void testMedianFilterForHeightChange() {
        Assertions.assertEquals(bi.getHeight(), p.medianFilter(bi).getHeight());
    }

    @Test
    void testMedianFilterForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.medianFilter(bi).getRGB(0, 0));
    }

    @Test
    void testSharpenFilterForWidthChange() {
        Assertions.assertEquals(bi.getWidth(), p.sharpenFilter(bi).getWidth());
    }

    @Test
    void testSharpenFilterForHeightChange() {
        Assertions.assertEquals(bi.getHeight(), p.sharpenFilter(bi).getHeight());
    }

    @Test
    void testSharpenFilterForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.sharpenFilter(bi).getRGB(0, 0));
    }

    @Test
    void testGrayScaleForWidthChange() {
        Assertions.assertEquals(bi.getWidth(), p.grayScale(bi).getWidth());
    }

    @Test
    void testGrayScaleForHeightChange() {
        Assertions.assertEquals(bi.getHeight(), p.grayScale(bi).getHeight());
    }

    // It's one of tests that don't work in 3x3 px frames but works on every pixel
    @Test
    void testGrayScaleForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.grayScale(bi).getRGB(0, 0));
    }

    @Test
    void testSobelFilterForWidthChange() {
        Assertions.assertEquals(bi.getWidth(), p.sobelFilter(bi).getWidth());
    }

    @Test
    void testSobelFilterForHeightChange() {
        Assertions.assertEquals(bi.getHeight(), p.sobelFilter(bi).getHeight());
    }

    @Test
    void testSobelFilterForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.sobelFilter(bi).getRGB(0, 0));
    }

    @Test
    void testBinarizationThresholdFilterForWidthChange() {
        Assertions.assertEquals(bi.getWidth(), p.binarizationThreshold(bi, 255/2).getWidth());
    }

    @Test
    void testBinarizationThresholdForHeightChange() {
        Assertions.assertEquals(bi.getHeight(), p.binarizationThreshold(bi, 255/2).getHeight());
    }

    @Test
    void testBinarizationThresholdForDataChangeOnlyBelow3px() {
        Assertions.assertNotEquals(bi.getRGB(0, 0), p.binarizationThreshold(bi, 255/2).getRGB(0, 0));
    }


}
