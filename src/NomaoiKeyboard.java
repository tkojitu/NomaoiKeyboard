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

    public void keyPressed(KeyEvent event) {}
    public void keyReleased(KeyEvent event) {}

    public void keyTyped(KeyEvent event) {
        char ch = event.getKeyChar();
        if (ch == ' ') {
            model.noteOff();
            return;
        }
        int note = charToNote(ch);
        if (note >= 0) {
            model.noteOn(note);
        }
    }

    private int charToNote(char ch) {
        switch (ch) {
        case 'd':
            return 60;
        case 'f':
            return 62;
        case 'g':
            return 64;
        case 'h':
            return 65;
        case 'j':
            return 67;
        case 'k':
            return 69;
        case 'l':
            return 71;
        case ';':
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
