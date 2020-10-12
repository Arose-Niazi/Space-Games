package Player;

import MISC.DatabaseErrors;
import Menu.MySQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TopPlayers {
    public String xInRow()
    {
        try {
            Statement stmt= MySQLConnection.getConnection().createStatement();
            ResultSet rs=stmt.executeQuery("SELECT ID FROM `Stats` WHERE 1 ORDER BY (Stats.xInRowWins - Stats.xInRowLost) DESC LIMIT 1");
            if (rs.next())
            {
                int id= rs.getInt("ID");
                stmt= MySQLConnection.getConnection().createStatement();
                rs=stmt.executeQuery("SELECT Username FROM `Accounts` WHERE ID="+id);
                rs.next();
            }
            return rs.getString("Username");
        }
        catch(SQLException e)
        {
            new DatabaseErrors(null,e.toString());
            e.printStackTrace();
            return "Error";
        }
    }

    public String blackJack()
    {
        try {
            Statement stmt= MySQLConnection.getConnection().createStatement();
            ResultSet rs=stmt.executeQuery("SELECT ID FROM `Stats` WHERE 1 ORDER BY ( `blackJackWins` + `blackJackBJ` - `blackJackLost` ) DESC LIMIT 1");
            if (rs.next())
            {
                int id= rs.getInt("ID");
                stmt= MySQLConnection.getConnection().createStatement();
                rs=stmt.executeQuery("SELECT Username FROM `Accounts` WHERE ID="+id);
                rs.next();
            }
            return rs.getString("Username");
        }
        catch(SQLException e)
        {
            new DatabaseErrors(null,e.toString());
            e.printStackTrace();
            return "Error";
        }
    }

    public String brickBreaker()
    {
        try {
            Statement stmt= MySQLConnection.getConnection().createStatement();
            ResultSet rs=stmt.executeQuery("SELECT ID FROM `Stats` WHERE 1 ORDER BY (  `brickBreakerWins` - `brickBreakerLost` ) DESC LIMIT 1");
            if (rs.next())
            {
                int id= rs.getInt("ID");
                stmt= MySQLConnection.getConnection().createStatement();
                rs=stmt.executeQuery("SELECT Username FROM `Accounts` WHERE ID="+id);
                rs.next();
            }
            return rs.getString("Username");
        }
        catch(SQLException e)
        {
            new DatabaseErrors(null,e.toString());
            e.printStackTrace();
            return "Error";
        }
    }

    public String tickCross()
    {
        try {
            Statement stmt= MySQLConnection.getConnection().createStatement();
            ResultSet rs=stmt.executeQuery("SELECT ID FROM `Stats` WHERE 1 ORDER BY ( `tickCrossWins` - `tickCrossLost`) DESC LIMIT 1");
            if (rs.next())
            {
                int id= rs.getInt("ID");
                stmt= MySQLConnection.getConnection().createStatement();
                rs=stmt.executeQuery("SELECT Username FROM `Accounts` WHERE ID="+id);
                rs.next();
            }
            return rs.getString("Username");
        }
        catch(SQLException e)
        {
            new DatabaseErrors(null,e.toString());
            e.printStackTrace();
            return "Error";
        }
    }

    public String g2048()
    {
        try {
            Statement stmt= MySQLConnection.getConnection().createStatement();
            ResultSet rs=stmt.executeQuery("SELECT ID FROM `Stats` WHERE 1 ORDER BY `g2048HighestPoints` DESC LIMIT 1");
            if (rs.next())
            {
                int id= rs.getInt("ID");
                stmt= MySQLConnection.getConnection().createStatement();
                rs=stmt.executeQuery("SELECT Username FROM `Accounts` WHERE ID="+id);
                rs.next();
            }
            return rs.getString("Username");
        }
        catch(SQLException e)
        {
            new DatabaseErrors(null,e.toString());
            e.printStackTrace();
            return "Error";
        }
    }
}
