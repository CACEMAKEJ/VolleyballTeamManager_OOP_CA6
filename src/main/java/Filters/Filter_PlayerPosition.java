package Filters;

import DTOs.Player;
import VolleyballManager.IFilter_Player;

public class Filter_PlayerPosition implements IFilter_Player {
    private final String position;

    public Filter_PlayerPosition(String position) {
        this.position = position;
    }

    public boolean matches(Player player) {
        return player.getPosition().equalsIgnoreCase(position);
    }
}
