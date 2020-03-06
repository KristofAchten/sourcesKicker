package gui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class GUIToolkit {

    public static void showErrorFrame(@NotNull final String title, @NotNull final String content) {
        final JFrame errorFrame = new JFrame(title);
        final JLabel errorLabel = new JLabel(content);

        errorLabel.setPreferredSize(new Dimension(300, 70));
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setVerticalAlignment(SwingConstants.CENTER);

        errorFrame.add(errorLabel);

        showFrame(errorFrame);
    }

    static void showFrame(@NotNull final JFrame frame) {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @NotNull
    static JPanel getLogoAsPanel() {
        JPanel logoPanel = new JPanel();

        ImageIcon icon = new ImageIcon("src/gui/assets/kp_logo.png");
        JLabel label = new JLabel();

        label.setIcon(icon);
        logoPanel.add(label);
        logoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return logoPanel;
    }
}
