import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * Simple Action that applies effect on image.
 * Action seperate functionality and state from components
 * Action is an action listener that provides not only action-event handling
 * We use it to change state of ImageJComponent
 * We usually bind actions to buttons and menu items
 * @version 0.1.0
 */
class ApplyAction extends AbstractAction {
    private ImageJComponent component;

    /**
     * This simple constructor for class ApplyAction
     * @param comp This is object of ImageJComponent that stores image
     * @param text Used for storing the String name for the action, used for a menu or button.
     * @param desc Used for storing a short String description for the action, used for tooltip text
     * @param mnemonic key that is used when we only use keyboard not mouse/ using this can make operating gui faster/ shorcuts
     */
    ApplyAction(ImageJComponent comp, String text, String desc, Integer mnemonic) {
        super(text);
        this.component = comp;
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }

    /**
     * Method inherited from java.awt.event.ActionListener
     * It invokes when an action occurs e.g
     * It changes Image in our component to Image with filters
     * @param e A semantic event which indicates that a component-defined action occurred
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        component.setImage(component.getBufferedImage());
    }
}
