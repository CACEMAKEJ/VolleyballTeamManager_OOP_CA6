package DAOs;

import DTOs.Player;
import Exceptions.DaoException;
import VolleyballManager.PlayerListContainer;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySqlPlayerDao extends MySqlDao implements PlayerDaoInterface{
    @Override
    public PlayerListContainer findAllPlayers() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        PlayerListContainer playerListContainer = new PlayerListContainer();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM players";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("player_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birthdate = resultSet.getString("birthdate");
                String position = resultSet.getString("position");
                int team_id = resultSet.getInt("team_id");
                Player p = new Player(id, firstName, lastName, birthdate, position, team_id);
                playerListContainer.add(p);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllPlayerResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return playerListContainer;     // may be empty
    }

    @Override
    public Player findPlayerById(int searchId) throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Player p = null;

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM players WHERE player_id = ?";
            ps = connection.prepareStatement(query);
            ps.setString( 1, String.valueOf(searchId));


            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("player_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birthdate = resultSet.getString("birthdate");
                String position = resultSet.getString("position");
                int team_id = resultSet.getInt("team_id");
                p = new Player(id, firstName, lastName, birthdate, position, team_id);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findPlayerById() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return p;     // may be empty
    }

    public void deletePlayerById(int deleteId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = this.getConnection();

            String query = "DELETE FROM players WHERE player_id = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString( 1, String.valueOf(deleteId));

            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            throw new DaoException("deleteUser() " + e.getMessage());
        } finally
        {
            try
            {
                System.out.println("User sucessfully deleted!!");
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findUserByLastName() " + e.getMessage());
            }
        }
    }

    public void addPlayer() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            Scanner keyboard = new Scanner(System.in);
            connection = this.getConnection();
            String query = "INSERT INTO players (first_name, last_name, birthdate, position, team_id) VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            System.out.print("Please enter player first name:");
            preparedStatement.setString( 1, keyboard.nextLine());
            System.out.print("\nPlease enter player last name:");
            preparedStatement.setString( 2, keyboard.nextLine());
            System.out.print("\nPlease enter player birth date (YYYY-MM-DD):");
            preparedStatement.setString( 3, keyboard.nextLine());
            System.out.print("\nPlease enter player position:");
            preparedStatement.setString( 4, keyboard.nextLine());
            System.out.print("\nPlease enter player team id:");
            preparedStatement.setInt( 5, keyboard.nextInt());



            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            throw new DaoException("addUser() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findUserByLastName() " + e.getMessage());
            }
        }
    }

    public String findAllPlayersJson() throws DaoException {
        Gson gsonParser = new Gson();
        PlayerListContainer fetchedData = findAllPlayers();
        String jsonData = gsonParser.toJson(fetchedData.playerList);
        return jsonData;
    }


    public String findPlayerByIdJson(int inputId) throws DaoException{
        Gson gsonParser = new Gson();
        Player fetchedData = findPlayerById(inputId);
        String jsonData = gsonParser.toJson(fetchedData);
        return jsonData;
    }


    @Override
    public List<Player> filterPlayersByPosition(String input) throws DaoException {
        return null;
    }


}




