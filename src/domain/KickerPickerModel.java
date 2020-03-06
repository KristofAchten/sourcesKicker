package domain;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class KickerPickerModel {

    private final List<String> leftoverPlayers = new ArrayList<>();
    private final List<String> players;
    private final List<JLabel> positions;


    public KickerPickerModel(@NotNull final List<String> players,
                             @NotNull final List<JLabel> positions) {
        this.players = players;
        this.positions = positions;
    }

    public List<String> getPlayers() {
        return players;
    }

    public List<JLabel> getPositions() {
        return positions;
    }

    public List<String> getLeftoverPlayers() {
        return leftoverPlayers;
    }

}
