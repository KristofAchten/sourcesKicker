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
        final boolean fillUp = leftoverPlayers.isEmpty();

        if (players.size() < 4) {
            GUIToolkit.showErrorFrame("Niet genoeg spelers", "Opgelet: niet genoeg spelers ingevuld!");
            return;
        }

        players.removeAll(leftoverPlayers);
        model.getPositions().forEach(p -> {
            setRandomFromList(p, leftoverPlayers.isEmpty() ? players : leftoverPlayers);
        });

        if (fillUp) {
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
