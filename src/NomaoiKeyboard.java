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
        System.out.println(ch);
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
        SwingUtilities.invokeLater(new NomaoiKeyboard(model));
    }
}
