package com.adasumizox.gui;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestGui {
    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() {
        Gui frame = GuiActionRunner.execute(() -> new Gui());
        window = new FrameFixture(frame);
        window.show();
    }

    @Test
    public void shouldBeAbleToMaximize() {
        window.maximize();
    }

    @Test
    public void shouldBeAbleToRevertMaximize() {
        window.maximize();
        window.normalize();
    }

    @Test
    public void shouldBeAbleToMinimize() {
        window.iconify();
    }

    @Test
    public void shouldBeAbleToRevertMinimize() {
        window.iconify();
        window.deiconify();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

}
