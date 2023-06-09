package VolleyballManager;

import DAOs.MySqlPlayerDao;
import DAOs.PlayerDaoInterface;
import DTOs.Player;
import Exceptions.DaoException;
import Filters.Filter_PlayerPosition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static PlayerDaoInterface IUserDao = new MySqlPlayerDao();  //"IUserDao" -> "I" stands for for
    public static PlayerListContainer playerListContainer = new PlayerListContainer();
    public static HashSet<Integer> playerIDs = new HashSet<Integer>();

    public static void main(String[] args)
    {

        try
        {

            Scanner keyboard = new Scanner(System.in);

            System.out.println("Choose an option:");
            System.out.println("1 - List all players");
            System.out.println("2 - Find player by ID");
            System.out.println("3 - Delete player by ID");
            System.out.println("4 - Insert a new player");
            System.out.println("5 - Find player using position filter");
            System.out.println("6 - List all players JSON");
            System.out.println("7 - Find player by ID JSON");


            int menuChoice = keyboard.nextInt();
            int input;
            String jsonString;
            switch(menuChoice){
                case 1:
                    findAllPlayers();
                case 2:
                    input=keyboard.nextInt();
                    Player player1 = IUserDao.findPlayerById(input);
                    if(player1 != null) System.out.println(player1);
                    else
                        System.out.println("user with id 1 not found");;break;
                case 3:
                    input=keyboard.nextInt();
                    IUserDao.deletePlayerById(input);
                    ;break;
                case 4:
                    Player player2 = new Player("Martin", "Macicha", "2002-01-22", "Opposite Hitter", 4);
                    IUserDao.addPlayer();
                    playerListContainer = IUserDao.findAllPlayers();

                    if( playerListContainer.playerList.isEmpty() )
                        System.out.println("No players found.");
                    else {
                        for (Player player : playerListContainer.playerList)
                            System.out.println("Player: " + player.toString());
                    }
                    break;
                case 5:
                    System.out.println("Which position would you like to filter by?");
                    System.out.println("1 - Outside Hitter");
                    System.out.println("2 - Middle blocker");
                    System.out.println("3 - Opposite hitter");
                    System.out.println("4 - Libero");
                    System.out.println("5 - Setter");
                    System.out.print("\nChoose an option:");

                    String output = "";
                    switch(choice){
                        case 1: output = "Outside Hitter";break;
                        case 2: output = "Middle blocker";break;
                        case 3: output = "Opposite Hitter";break;
                        case 4: output = "Libero";break;
                        case 5: output = "Setter";break;

                    }
                    List<Player> filteredList = playerListContainer.filterBy(new Filter_PlayerPosition(output));
                    System.out.println(filteredList);
                    break;
                case 6:
                    jsonString = IUserDao.findAllPlayersJson();
                    System.out.println(jsonString);
                    break;
                case 7:
                    System.out.print("Enter player id:");
                    int choice = keyboard.nextInt();
                    jsonString = IUserDao.findPlayerByIdJson(choice);
                    System.out.println(jsonString);
                    break;
            }
        }
        catch(DaoException e )
        {
            e.printStackTrace();
        }
    }

    public static void findAllPlayers() throws DaoException {
        System.out.println("\nCall findAllPLayers()");
        playerListContainer = IUserDao.findAllPlayers();     // call a method in the DAO

        if( playerListContainer.playerList.isEmpty() )
            System.out.println("No players found.");
        else {
            for (Player player : playerListContainer.playerList){
                playerIDs.add(player.getId());
                System.out.println("Player: " + player.toString());
            }

        }
    }

    public List<Player> filterBy(IFilter_Player filter, List<Player> playerList)
    {
        List<Player> returnList = new ArrayList<Player>();
        for(Player p : playerList)
        {
            if(filter.matches(p))
                returnList.add(p);
        }

        return returnList;
    }


}
