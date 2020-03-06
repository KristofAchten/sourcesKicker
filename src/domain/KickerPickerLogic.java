package domain;

import gui.GUIToolkit;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class KickerPickerLogic {

    private static final Pattern SPACE = Pattern.compile(" ");

    public static void fillInPositions(@NotNull final KickerPickerModel model) {
        final List<String> players = model.getPlayers();
        final List<String> leftoverPlayers = model.getLeftoverPlayers();
        final List<JLabel> positions = model.getPositions();

        if (players.size() < 4) {
            GUIToolkit.showErrorFrame("Niet genoeg spelers", "Opgelet: niet genoeg spelers ingevuld!");
            return;
        }

        boolean fillerUp = !leftoverPlayers.isEmpty();
        if (fillerUp) {
            players.removeAll(leftoverPlayers);
        }

        for (JLabel l : positions) {
            if (!leftoverPlayers.isEmpty()) {
                setRandomFromList(l, leftoverPlayers);
            } else {
                setRandomFromList(l, players);
            }
        }

        if (!fillerUp) {
            leftoverPlayers.addAll(players);
        }
    }


    private static void setRandomFromList(@NotNull final JLabel l, @NotNull final List<String> players) {
        final Random r = new Random();

        int ri = r.nextInt(players.size());
        l.setText(players.get(ri));
        players.remove(ri);
    }


    public static boolean hasUsefulContent(@NotNull final String string) {
        return !SPACE.matcher(string).replaceAll("").isEmpty();
    }
}
