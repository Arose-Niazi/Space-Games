package Player;

import MISC.DatabaseErrors;
import Menu.MySQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatsSaveLoad {

    private Player player;

    public StatsSaveLoad(Player player) {
        this.player = player;
    }

    public void loadStats()
    {
        try {
            Statement stmt= MySQLConnection.getConnection().createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM Stats WHERE ID="+player.getID()+" LIMIT 1");
            while (rs.next())
            {
                int i=1;
                player.setxInRowPlayed(rs.getInt(++i));
                player.setxInRowWins(rs.getInt(++i));
                player.setxInRowLost(rs.getInt(++i));
                player.setxInRowTurns(rs.getInt(++i));

                player.setBlackJackPlayed(rs.getInt(++i));
                player.setBlackJackWins(rs.getInt(++i));
                player.setBlackJackLost(rs.getInt(++i));
                player.setBlackJackBJ(rs.getInt(++i));
                player.setBlackJackTurns(rs.getInt(++i));
                player.setBlackJackHits(rs.getInt(++i));
                player.setBlackJackPoints(rs.getInt(++i));

                player.setBrickBreakerPlayed(rs.getInt(++i));
                player.setBrickBreakerWins(rs.getInt(++i));
                player.setBrickBreakerLost(rs.getInt(++i));
                player.setBrickBreakerPoints(rs.getInt(++i));

                player.setTickCrossPlayed(rs.getInt(++i));
                player.setTickCrossWins(rs.getInt(++i));
                player.setTickCrossLost(rs.getInt(++i));

                player.setG2048Played(rs.getInt(++i));
                player.setG2048Wins(rs.getInt(++i));
                player.setG2048Lost(rs.getInt(++i));
                player.setG2048Points(rs.getInt(++i));
                player.setG2048HighestPoints(rs.getInt(++i));
            }
        }
        catch(SQLException e)
        {
            new DatabaseErrors(null,e.toString());
            e.printStackTrace();
            return;
        }
    }

    public void saveStats()
    {
        if(player == null) return;
        try {
            Statement stmt= MySQLConnection.getConnection().createStatement();
            stmt.executeUpdate("UPDATE `Stats` SET " +
                    "`xInRowPlayed`="+player.getxInRowPlayed() +
                    ",`xInRowWins`="+player.getxInRowWins() +
                    ",`xInRowLost`="+player.getxInRowLost() +
                    ",`xInRowTurns`="+player.getxInRowTurns() +
                    " WHERE ID="+player.getID());
            stmt= MySQLConnection.getConnection().createStatement();
            stmt.executeUpdate("UPDATE `Stats` SET " +
                    "`blackJackPlayed`="+player.getBlackJackPlayed() +
                    ",`blackJackWins`="+player.getBlackJackWins() +
                    ",`blackJackLost`="+player.getBlackJackLost() +
                    ",`blackJackBJ`="+player.getBlackJackBJ() +
                    ",`blackJackTurns`="+player.getBlackJackTurns() +
                    ",`blackJackHits`="+player.getBlackJackHits() +
                    ",`blackJackPoints`="+player.getBlackJackPoints() +
                    " WHERE ID="+player.getID());
            stmt= MySQLConnection.getConnection().createStatement();
            stmt.executeUpdate("UPDATE `Stats` SET " +
                    "`brickBreakerPlayed`="+player.getBrickBreakerPlayed() +
                    ",`brickBreakerWins`="+player.getBrickBreakerWins() +
                    ",`brickBreakerLost`="+player.getBrickBreakerLost() +
                    ",`brickBreakerPoints`="+player.getBrickBreakerPoints() +
                    " WHERE ID="+player.getID());
            stmt= MySQLConnection.getConnection().createStatement();
            stmt.executeUpdate("UPDATE `Stats` SET " +
                    "`tickCrossPlayed`="+player.getTickCrossPlayed() +
                    ",`tickCrossWins`="+player.getTickCrossWins() +
                    ",`tickCrossLost`="+player.getTickCrossLost() +
                    " WHERE ID="+player.getID());
            stmt= MySQLConnection.getConnection().createStatement();
            stmt.executeUpdate("UPDATE `Stats` SET " +
                    "`g2048Played`="+player.getG2048Played() +
                    ",`g2048Wins`="+player.getG2048Wins() +
                    ",`g2048Lost`="+player.getG2048Lost() +
                    ",`g2048Points`="+player.getG2048Points() +
                    ",`g2048HighestPoints`="+player.getG2048HighestPoints()  +
                    " WHERE ID="+player.getID());
            stmt= MySQLConnection.getConnection().createStatement();
            stmt.executeUpdate("UPDATE `Accounts` SET " +
                    "`LastSeen`=NOW()"+
                    " WHERE ID="+player.getID());
        }
        catch(SQLException e)
        {
            new DatabaseErrors(null,e.toString());
            e.printStackTrace();
        }
    }
}
