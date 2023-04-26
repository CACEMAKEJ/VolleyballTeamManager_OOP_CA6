package DAOs;


import DTOs.Player;
import Exceptions.DaoException;
import VolleyballManager.PlayerListContainer;

import java.util.List;

public interface PlayerDaoInterface
{
    public PlayerListContainer findAllPlayers() throws DaoException;
    public Player findPlayerById(int id) throws DaoException;
    public void deletePlayerById(int id) throws DaoException;
    public void addPlayer() throws DaoException;
    public List<Player> filterPlayersByPosition(String input) throws DaoException;
    public String findAllPlayersJson() throws DaoException;
    public String findPlayerByIdJson(int input) throws DaoException;



}

