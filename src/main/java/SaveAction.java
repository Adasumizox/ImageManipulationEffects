import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

class SaveAction extends AbstractAction {

    private ImageJComponent component;

    SaveAction(ImageJComponent comp, String text, String desc, Integer mnemonic) {
        super(text);
        this.component = comp;
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }

    private Image getComponentImage(ImageJComponent comp) {
        return comp.getImage();
    }

    private String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Save image");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter jpeg = new FileNameExtensionFilter("JPEG", "jpeg", "jpg");
        FileNameExtensionFilter png = new FileNameExtensionFilter("PNG", "png");
        jfc.addChoosableFileFilter(jpeg);
        jfc.addChoosableFileFilter(png);



        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File file = new File(jfc.getSelectedFile().getAbsolutePath());
                String extension = getFileExtension(jfc.getSelectedFile().getAbsolutePath()).substring(1);
                BufferedImage image = (BufferedImage) getComponentImage(this.component);
                ImageIO.write(image, extension, file);
            } catch(IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}
