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

    @Test
    public void shouldBeAbleToApplyEffect() {
        window.button("applyBtn").click();
    }

    @Test
    public void shouldBeAbleToChooseAllEffects() {
        window.comboBox("effectsCombo").selectItem(0);
        window.comboBox("effectsCombo").selectItem(1);
        window.comboBox("effectsCombo").selectItem(2);
        window.comboBox("effectsCombo").selectItem(3);
        window.comboBox("effectsCombo").selectItem(4);
        window.comboBox("effectsCombo").selectItem(5);
        window.comboBox("effectsCombo").selectItem(6);
        window.comboBox("effectsCombo").selectItem(7);
        window.comboBox("effectsCombo").selectItem(8);
    }

    @Test
    public void shouldBeAbleToChooseAndApplyAllEffects() {
        window.comboBox("effectsCombo").selectItem(0);
        window.button("applyBtn").click();
        window.comboBox("effectsCombo").selectItem(1);
        window.button("applyBtn").click();
        window.comboBox("effectsCombo").selectItem(2);
        window.button("applyBtn").click();
        window.comboBox("effectsCombo").selectItem(3);
        window.button("applyBtn").click();
        window.comboBox("effectsCombo").selectItem(4);
        window.button("applyBtn").click();
        window.comboBox("effectsCombo").selectItem(5);
        window.button("applyBtn").click();
        window.comboBox("effectsCombo").selectItem(6);
        window.button("applyBtn").click();
        window.comboBox("effectsCombo").selectItem(7);
        window.button("applyBtn").click();
        window.comboBox("effectsCombo").selectItem(8);
        window.button("applyBtn").click();
    }

    @Test
    public void shouldBeAbleToOpenMenu() {
        window.menuItem("fileMenu").click();
    }

    @Test
    public void shouldBeAbleToChooseLoadFile() {
        window.menuItem("fileMenu").click();
        window.menuItem("menuItemOpen").click();
    }

    @Test
    public void shouldBeAbleToChooseSaveFile() {
        window.menuItem("fileMenu").click();
        window.menuItem("menuItemSave").click();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

}
