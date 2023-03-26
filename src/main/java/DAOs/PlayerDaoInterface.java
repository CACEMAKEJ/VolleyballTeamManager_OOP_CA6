package DAOs;


import DTOs.Player;
import Exceptions.DaoException;

import java.util.List;

public interface PlayerDaoInterface
{
    public List<Player> findAllPlayers() throws DaoException;
    public Player findPlayerById(int id) throws DaoException;


}

