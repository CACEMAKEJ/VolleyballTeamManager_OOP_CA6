package DAOs;

import DTOs.Player;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlPlayerDao extends MySqlDao implements PlayerDaoInterface{
    @Override
    public List<Player> findAllPlayers() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Player> playerList = new ArrayList<>();

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
                playerList.add(p);
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
        return playerList;     // may be empty
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
                System.out.println("User sucessfully deleted!");
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


}




