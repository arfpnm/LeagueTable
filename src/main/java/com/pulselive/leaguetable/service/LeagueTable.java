package com.pulselive.leaguetable.service;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.pulselive.leaguetable.domain.LeagueTableEntry;
import com.pulselive.leaguetable.domain.Match;

public class LeagueTable
{
	/**
	 * Create a league table from the supplied list of match results
	 *
	 * @param matches
	 */
	private LeagueTableEntry leagueTableEntry;
	private final Map<String, LeagueTableEntry> leagueTableEntryMap;
	private final List<Match> matches;

	public LeagueTable( final List<Match> matches )
	{
		leagueTableEntryMap = new ConcurrentHashMap<String, LeagueTableEntry>();
		this.matches = matches;
	}

	/**
	 * Get the ordered list of league table entries for this league table.
	 *
	 * @return List<LeagueTableEntry>
	 */

	public List<LeagueTableEntry> getTableEntries() {	
		if(matches == null || matches.isEmpty()) return null;
		matches.forEach(t -> leagueTableEntryMap.put(t.getHomeTeam(), createLeagueForHomeTeams(t)));
		matches.forEach(t -> leagueTableEntryMap.put(t.getAwayTeam(), createLeagueForAwayTeams(t)));
		return leagueTableEntryMap.values().stream().sorted(Comparator.comparing(LeagueTableEntry::getPoints).reversed().
				thenComparing(LeagueTableEntry::getGoalDifference).
				thenComparing(LeagueTableEntry::getGoalsFor).
				thenComparing(LeagueTableEntry::getTeamName)).collect(Collectors.toList());			
	}

	/**
	 * @param Match
	 * @return LeagueTableEntry
	 * Creating League Table for the Home Teams
	 */
	private LeagueTableEntry createLeagueForHomeTeams(Match match){
		int homeTeamWin = 0;
		int homeTeamDraw = 0;
		int homeTeamLost = 0;
		int homeTeamGoalsFor = 0;
		int homeTeamGoalsAgainst = 0;
		int homeTeamGoalsDifference = 0;
		int homeTeamTotalPlayed = 0;

		String homeTeam = match.getHomeTeam();
		int homeTeamScore = match.getHomeScore();
		int awayTeamScore = match.getAwayScore();
		if(leagueTableEntryMap.containsKey(homeTeam)){
				leagueTableEntry = leagueTableEntryMap.get(homeTeam);
			if(leagueTableEntry.getTeamName() != null && leagueTableEntry.getTeamName().equalsIgnoreCase(homeTeam)){
				homeTeamWin = homeTeamScore > awayTeamScore ? (leagueTableEntry.getWon() + 1) : leagueTableEntry.getWon();
				homeTeamDraw = homeTeamScore == awayTeamScore ? (leagueTableEntry.getDrawn() + 1) : leagueTableEntry.getDrawn();
				homeTeamLost = homeTeamScore < awayTeamScore ? (leagueTableEntry.getLost() + 1) : leagueTableEntry.getLost();
				homeTeamGoalsFor = homeTeamGoalsFor + homeTeamScore;
				homeTeamGoalsAgainst = homeTeamGoalsAgainst + awayTeamScore;
				homeTeamGoalsDifference = homeTeamScore - awayTeamScore;
				homeTeamTotalPlayed = homeTeamWin + homeTeamDraw + homeTeamLost;
			}

		}else{
			homeTeamWin = homeTeamScore > awayTeamScore ? (homeTeamWin + 1) : homeTeamWin;
			homeTeamDraw = homeTeamScore == awayTeamScore ? (homeTeamDraw + 1) : homeTeamDraw;
			homeTeamLost = homeTeamScore < awayTeamScore ? (homeTeamLost + 1) : homeTeamLost;
			homeTeamGoalsFor = homeTeamGoalsFor + homeTeamScore;
			homeTeamGoalsAgainst = homeTeamGoalsAgainst + awayTeamScore;
			homeTeamGoalsDifference = homeTeamGoalsDifference + (homeTeamScore - awayTeamScore);
			homeTeamTotalPlayed = homeTeamWin + homeTeamDraw + homeTeamLost;

		}
		return  new LeagueTableEntry(match.getHomeTeam(), 
				homeTeamTotalPlayed, homeTeamWin, homeTeamDraw, homeTeamLost, homeTeamGoalsFor, homeTeamGoalsAgainst, homeTeamGoalsDifference, homeTeamWin);
	}
	/**
	 * @param Match
	 * @return LeagueTableEntry
	 * Creating League Table for the Away Teams
	 */
	private LeagueTableEntry createLeagueForAwayTeams(Match match){
		int awayTeamWin = 0;
		int awayTeamDraw = 0;
		int awayTeamLost = 0;
		int awayTeamGoalsFor = 0;
		int awayTeamGoalsAgainst = 0;
		int awayTeamGoalsDifference = 0;
		int awayTeamTotalPlayed = 0;

		String awayTeam = match.getAwayTeam();
		int homeTeamScore = match.getHomeScore();
		int awayTeamScore = match.getAwayScore();
		if(leagueTableEntryMap.containsKey(awayTeam)){
				leagueTableEntry = leagueTableEntryMap.get(awayTeam);
			if(leagueTableEntry.getTeamName() != null && leagueTableEntry.getTeamName().equalsIgnoreCase(awayTeam)){
				awayTeamWin = awayTeamScore > homeTeamScore ? (leagueTableEntry.getWon() + 1) : leagueTableEntry.getWon();
				awayTeamDraw = awayTeamScore == homeTeamScore ? (leagueTableEntry.getDrawn() + 1) : leagueTableEntry.getDrawn();
				awayTeamLost = awayTeamScore < homeTeamScore ? (leagueTableEntry.getLost() + 1) : leagueTableEntry.getLost();
				awayTeamGoalsFor = awayTeamGoalsFor + awayTeamScore;
				awayTeamGoalsAgainst = awayTeamGoalsAgainst + awayTeamScore;
				awayTeamGoalsDifference = (awayTeamScore - awayTeamScore);
				awayTeamTotalPlayed = awayTeamWin + awayTeamDraw + awayTeamLost;
			}

		}else{
			awayTeamWin = awayTeamScore > homeTeamScore ? (awayTeamWin + 1) : awayTeamWin;
			awayTeamDraw = awayTeamScore == homeTeamScore ? (awayTeamDraw + 1) : awayTeamDraw;
			awayTeamLost = awayTeamScore < homeTeamScore ? (awayTeamLost + 1) : awayTeamLost;
			awayTeamGoalsFor = awayTeamGoalsFor + awayTeamScore;
			awayTeamGoalsAgainst = awayTeamGoalsAgainst + awayTeamScore;
			awayTeamGoalsDifference = awayTeamGoalsDifference + (awayTeamScore - awayTeamScore);
			awayTeamTotalPlayed = awayTeamWin + awayTeamDraw + awayTeamLost;

		}
		return  new LeagueTableEntry(match.getAwayTeam(), 
				awayTeamTotalPlayed, awayTeamWin, awayTeamDraw, awayTeamLost, awayTeamGoalsFor, awayTeamGoalsAgainst, awayTeamGoalsDifference, awayTeamWin);
	}


}
