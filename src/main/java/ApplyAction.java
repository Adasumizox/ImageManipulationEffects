import javax.swing.*;
import java.awt.event.ActionEvent;

class ApplyAction extends AbstractAction {
    private ImageJComponent component;

    ApplyAction(ImageJComponent comp, String text, String desc, Integer mnemonic) {
        super(text);
        this.component = comp;
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        component.setImage(component.getBufferedImage());
    }
}
