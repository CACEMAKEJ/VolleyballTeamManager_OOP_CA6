package VolleyballManager;

import DAOs.MySqlPlayerDao;
import DAOs.PlayerDaoInterface;
import DTOs.Player;
import Exceptions.DaoException;

import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        PlayerDaoInterface IUserDao = new MySqlPlayerDao();  //"IUserDao" -> "I" stands for for

        try
        {
            System.out.println("\nCall findAllPLayers()");
            List<Player> players = IUserDao.findAllPlayers();     // call a method in the DAO

            if( players.isEmpty() )
                System.out.println("No players found.");
            else {
                for (Player player : players)
                    System.out.println("Player: " + player.toString());
            }

            Player player1 = IUserDao.findPlayerById(1);
            if(player1 != null) System.out.println(player1);
            else
                System.out.println("user with id 1 not found");

            IUserDao.deletePlayerById(3);

            System.out.println("----After new player added---");
            Player player2 = new Player("Martin", "Macicha", "2002-01-22", "Opposite Hitter", 4);
            IUserDao.addPlayer(player2);
            players = IUserDao.findAllPlayers();

            if( players.isEmpty() )
                System.out.println("No players found.");
            else {
                for (Player player : players)
                    System.out.println("Player: " + player.toString());
            }



        }
        catch(DaoException e )
        {
            e.printStackTrace();
        }
    }
}
