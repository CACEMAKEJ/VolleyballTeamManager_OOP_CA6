package VolleyballManager;

import DAOs.MySqlPlayerDao;
import DAOs.PlayerDaoInterface;
import DTOs.Player;
import Exceptions.DaoException;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        PlayerDaoInterface IUserDao = new MySqlPlayerDao();  //"IUserDao" -> "I" stands for for
        HashSet<Integer> playerIDs = new HashSet<Integer>();
        Scanner keyboard = new Scanner(System.in);
        try
        {
            System.out.println("Choose an option:");
            System.out.println("1 - List all players");
            System.out.println("2 - Find player by ID");
            System.out.println("3 - Delete player by ID");
            System.out.println("4 - Insert a new player");



            System.out.println("\nCall findAllPLayers()");
            List<Player> players = IUserDao.findAllPlayers();     // call a method in the DAO

            if( players.isEmpty() )
                System.out.println("No players found.");
            else {
                for (Player player : players){
                    playerIDs.add(player.getId());
                    System.out.println("Player: " + player.toString());
                }

            }

            Player player1 = IUserDao.findPlayerById(1);
            if(player1 != null) System.out.println(player1);
            else
                System.out.println("user with id 1 not found");

            IUserDao.deletePlayerById(3);

            System.out.println("----After new player added---");
            Player player2 = new Player("Martin", "Macicha", "2002-01-22", "Opposite Hitter", 4);
            IUserDao.addPlayer();
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
