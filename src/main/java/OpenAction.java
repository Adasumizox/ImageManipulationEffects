import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
/**
 * Simple Action that opens file using JavaFileChooser
 * Action seperate functionality and state from components
 * Action is an action listener that provides not only action-event handling
 * We use it to change state of ImageJComponent
 * We usually bind actions to buttons and menu items
 * @version 0.1.0
 */
class OpenAction extends AbstractAction {
    private Image image;
    private ImageJComponent component;

    /**
     * method that will set image to image that user choose
     * @param image
     */
    private void setImage(Image image) {
        this.image = image;
    }

    /**
     * method that will update our Image to ImageJComponent object that we will provide.
     * @param component
     */
    private void update(ImageJComponent component) {
        component.setImage(image);
    }

    /**
     * This simple constructor for class OpenAction
     * @param comp This is object of ImageJComponent that stores image
     * @param text Used for storing the String name for the action, used for a menu or button.
     * @param desc Used for storing a short String description for the action, used for tooltip text
     * @param mnemonic key that is used when we only use keyboard not mouse/ using this can make operating gui faster/ shorcuts
     */
    OpenAction(ImageJComponent comp, String text, String desc, Integer mnemonic) {
        super(text);
        this.component = comp;
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }
    /**
     * Method inherited from java.awt.event.ActionListener
     * It invokes when an action occurs e.g
     * It will choose a image file [JPG, PNG] from user and set it in our component
     * @param e A semantic event which indicates that a component-defined action occurred
     */
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Open image");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter jpeg = new FileNameExtensionFilter("JPEG", "jpeg", "jpg");
        FileNameExtensionFilter png = new FileNameExtensionFilter("PNG", "png");
        jfc.addChoosableFileFilter(jpeg);
        jfc.addChoosableFileFilter(png);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                setImage(ImageIO.read(jfc.getSelectedFile()));
                update(this.component);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
