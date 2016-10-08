package com.pulselive.leaguetable;

import java.util.ArrayList;
import java.util.List;

import com.pulselive.leaguetable.domain.LeagueTableEntry;
import com.pulselive.leaguetable.domain.Match;
import com.pulselive.leaguetable.service.LeagueTable;

/**
 * Main
 *
 */
public class LeagueTableMain 
{
    public static void main( String[] args )
    {
    	Match match1 = new Match("Manchester United F.C", "Chelsea F.C", 3, 2);
        Match match2 = new Match("Arsenel F.C", "Chelsea F.C", 4, 5);
        Match match3 = new Match("WestHam United F.C", "Everton", 2, 2);
        Match match4 = new Match("WestHam United F.C", "MiddlesBrough", 5, 1);
        Match match5 = new Match("WestHam United F.C", "Liverpool F.C", 3, 2);
        Match match6 = new Match("Arsenel F.C", "Chelsea F.C", 6, 5);
        Match match7 = new Match("Manchester United F.C", "Newcastle United F.C", 1, 2);
    	
        
        List<Match> matches = new ArrayList<Match>();
        matches.add(match1);
        matches.add(match2);
        matches.add(match3);
        matches.add(match4);
        matches.add(match5);
        matches.add(match6);
        matches.add(match7);
        
        
        LeagueTable leagueTable = new LeagueTable(matches);
        List<LeagueTableEntry> entries = leagueTable.getTableEntries();
        entries.forEach(t -> System.out.println(t.getTeamName()+ "  Won: "+t.getWon()  + " Draw: "+t.getDrawn()   + "  "
        		+ "  Lost: "+t.getLost()  + "  Played: "+t.getPlayed() + "  Goal differences: "+t.getGoalDifference()  + "  "
        				+ "  Against: "+t.getGoalsAgainst()   + "  GoalFor: "+t.getGoalsFor()  + "  Points: "+t.getPoints()));
        		 
    }
}
