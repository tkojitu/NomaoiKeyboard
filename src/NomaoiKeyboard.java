package nomaoi;

import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.*;

public class NomaoiKeyboard implements KeyListener, Runnable {
    private NomaoiModel model;

    public NomaoiKeyboard(NomaoiModel model) {
        this.model = model;
    }

    public void createAndShowGui() {
        JFrame frame = createFrame();
        frame.setVisible(true);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame("NomaoiKeyboard");
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(createPane());
        frame.pack();
        return frame;
    }

    private JPanel createPane() {
        JPanel pane = new JPanel();
        pane.setPreferredSize(new Dimension(320, 240));
        return pane;
    }

    public void keyPressed(KeyEvent event) {
        int code = event.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            model.noteOff();
            return;
        }
        int note = keyCodeToNote(code);
        if (note >= 0) {
            model.noteOn(note);
        }
    }

    public void keyReleased(KeyEvent event) {}
    public void keyTyped(KeyEvent event) {}

    private int keyCodeToNote(int keyCode) {
        switch (keyCode) {
        case KeyEvent.VK_D:
            return 60;
        case KeyEvent.VK_F:
            return 62;
        case KeyEvent.VK_G:
            return 64;
        case KeyEvent.VK_H:
            return 65;
        case KeyEvent.VK_J:
            return 67;
        case KeyEvent.VK_K:
            return 69;
        case KeyEvent.VK_L:
            return 71;
        case KeyEvent.VK_SEMICOLON:
            return 72;
        default:
            return -1;
        }
    }

    public void run() {
        createAndShowGui();
    }

    public static void main(String[] args) {
        ChannelData data = null;
        if (args.length > 0) {
            data = ChannelData.load(args[0]);
        }
        NomaoiModel model = new NomaoiModel(data);
        model.open();
        SwingUtilities.invokeLater(new NomaoiKeyboard(model));
    }
}
