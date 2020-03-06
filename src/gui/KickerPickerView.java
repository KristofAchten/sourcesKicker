package gui;

import domain.KickerPickerLogic;
import domain.KickerPickerModel;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class KickerPickerView {

    private static final String EMPTY = "...";
    private static final String TITLE = "Pick-A-Kicker";
    private static final int DEFAULT_WIDTH = 20;
    private static final int DEFAULT_HEIGHT = 10;
    private static final Pattern NEWLINE = Pattern.compile("\\r?\\n");
    private static final Pattern SPACE = Pattern.compile(" ");

    private final JLabel pos1 = new JLabel(EMPTY);
    private final JLabel pos2 = new JLabel(EMPTY);
    private final JLabel pos3 = new JLabel(EMPTY);
    private final JLabel pos4 = new JLabel(EMPTY);
    private final JFrame mainFrame = new JFrame(TITLE);
    private final JTextArea players = new JTextArea(DEFAULT_HEIGHT, DEFAULT_HEIGHT);

    public KickerPickerView() {
        this.mainFrame.add(createMainPanel());
    }

    public void show() {
        GUIToolkit.showFrame(mainFrame);
    }

    @NotNull
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new MigLayout("center"));

        // 1. Logo
        mainPanel.add(GUIToolkit.getLogoAsPanel(), "newline");

        // 2. Player panel
        mainPanel.add(createPlyPanel(), "newline, center");
        mainPanel.add(new JSeparator(), "newline, grow, push");

        // 3. Result panel
        mainPanel.add(createResultPanel(), "newline, center");

        // 4. Generate button
        final JButton button = new JButton();
        button.setAction(new AbstractAction("Generate") {
            @Override
            public void actionPerformed(ActionEvent e) {
                KickerPickerLogic.fillInPositions(new KickerPickerModel(getPlayers(), getPositions()));
            }
        });
        button.setPreferredSize(new Dimension(70, 30));
        mainPanel.add(button, "newline, center");

        return mainPanel;
    }


    @NotNull
    private List<String> getPlayers() {
        return Arrays.stream(NEWLINE.split(this.players.getText()))
                .filter(KickerPickerLogic::hasUsefulContent)
                .collect(Collectors.toList());
    }

    @NotNull
    private List<JLabel> getPositions() {
        return Arrays.asList(pos1, pos2, pos3, pos4);
    }



    @NotNull
    private JPanel createPlyPanel() {
        final JPanel plyPanel = new JPanel(new MigLayout("center"));

        final JLabel spelers = new JLabel("SPELERS");
        final JLabel info = new JLabel("(1 naam per regel) ");
        final JScrollPane invulveld = new JScrollPane(players);

        spelers.setFont(spelers.getFont().deriveFont(Font.BOLD));
        info.setFont(info.getFont().deriveFont(Font.ITALIC, 10f));

        plyPanel.add(spelers, "center, newline");
        plyPanel.add(info, "center, newline");
        plyPanel.add(invulveld, "newline");

        players.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return plyPanel;
    }

    @NotNull
    private JPanel createResultPanel() {
        JPanel posPanel = new JPanel(new MigLayout("left"));

        posPanel.add(new JLabel("Rood aanvallen:"), "newline");
        posPanel.add(pos1);

        posPanel.add(new JLabel("Groen aanvallen:"));
        posPanel.add(pos2);

        posPanel.add(new JLabel("Rood verdedigen:"), "newline");
        posPanel.add(pos3);

        posPanel.add(new JLabel("Groen verdedigen:"));
        posPanel.add(pos4);
        return posPanel;
    }
}
