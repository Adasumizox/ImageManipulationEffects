import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

class OpenAction extends AbstractAction {
    private Image image;
    private ImageJComponent component;

    private void setImage(Image image) {
        this.image = image;
    }

    private void update(ImageJComponent component) {
        component.setImage(image);
    }

    OpenAction(ImageJComponent comp, String text, String desc, Integer mnemonic) {
        super(text);
        this.component = comp;
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }

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
