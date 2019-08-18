import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Gui {
    public static void main(String... args) {
        final String[] IMPLEMENTED_EFFECTS = {"Original","Grayscale","Blurred","Binarization", "MedianFilter", "SharpenFilter", "SobelFilter", "Dilatation", "Erosion"};

        JFrame frame = new JFrame("Image manipulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        ImageJComponent imageComponent = new ImageJComponent(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        Action openAction = new OpenAction(imageComponent ,"Open", "This is a file open menu option", KeyEvent.VK_O);
        JMenuItem menuItemOpen = new JMenuItem(openAction);
        Action saveAction = new SaveAction(imageComponent, "Save", "This is a file save menu option", KeyEvent.VK_S);
        JMenuItem menuItemSave = new JMenuItem(saveAction);
        fileMenu.add(menuItemOpen);
        fileMenu.add(menuItemSave);

        JPanel footer = new JPanel();
        JLabel label = new JLabel("Choose effect");
        JComboBox<String> effects = new JComboBox<>(IMPLEMENTED_EFFECTS);
        effects.addItemListener(new ItemChangeListener(imageComponent));
        Action applyAction = new ApplyAction(imageComponent, "Apply", "This is button for saving effects", KeyEvent.VK_A);
        JButton applyBtn = new JButton(applyAction);
        footer.add(label);
        footer.add(effects);
        footer.add(applyBtn);

        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, imageComponent);
        frame.getContentPane().add(BorderLayout.SOUTH, footer);
        frame.setVisible(true);
    }
}
