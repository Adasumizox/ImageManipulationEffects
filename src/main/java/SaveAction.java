import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.io.File;

class SaveAction extends AbstractAction {

    SaveAction(String text, String desc, Integer mnemonic) {
        super(text);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
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
            // TODO: Write saving
        }
    }
}
