package VolleyballManager;

import DTOs.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerListContainer {

    public final List<Player> playerList;

    public PlayerListContainer()
    {
        this.playerList = new ArrayList<>();
    }

    public void add(Player player)
    {
        this.playerList.add(player);
    }



    public List<Player> filterBy(IFilter_Player filter)
    {
        List<Player> returnList = new ArrayList<Player>();
        for(Player p : this.playerList)
        {
            if(filter.matches(p))
                returnList.add(p);
        }

        return returnList;
    }


}
