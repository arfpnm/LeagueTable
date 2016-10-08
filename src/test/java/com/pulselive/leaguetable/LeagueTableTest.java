package com.pulselive.leaguetable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.pulselive.leaguetable.domain.LeagueTableEntry;
import com.pulselive.leaguetable.domain.Match;
import com.pulselive.leaguetable.service.LeagueTable;

/**
 * Unit test for league table
 * The unit tests are based on the mock data populated in the init() method. 
 * Any data changes will effect the junit results.
 */
public class LeagueTableTest 

{

	List<LeagueTableEntry> entries=null;

	@Before
	public void init(){
		//Mock Data
		Match match1 = new Match("Manchester United F.C", "Chelsea F.C", 3, 2);
		Match match2 = new Match("Arsenel F.C", "Chelsea F.C", 4, 5);
		Match match3 = new Match("WestHam United F.C", "Everton", 2, 2);
		Match match4 = new Match("WestHam United F.C", "MiddlesBrough", 5, 1);
		Match match5 = new Match("WestHam United F.C", "Liverpool F.C", 3, 2);
		Match match6 = new Match("Arsenel F.C", "ston Villa F.C", 6, 5);
		Match match7 = new Match("Manchester United F.C", "Newcastle United F.C", 1, 2);
		Match match8 = new Match("Chelsea F.C", "MiddlesBrough", 2, 4);
		Match match9 = new Match("Aston Villa F.C", "Everton", 2, 8);
		Match match10 = new Match("WestHam United F.C", "Aston Villa F.C", 3, 7);


		List<Match> matches = new ArrayList<Match>();
		matches.add(match1);
		matches.add(match2);
		matches.add(match3);
		matches.add(match4);
		matches.add(match5);
		matches.add(match6);
		matches.add(match7);
		matches.add(match8);
		matches.add(match9);
		matches.add(match10);


		LeagueTable leagueTable = new LeagueTable(matches);
		entries = leagueTable.getTableEntries();
	}


	@Test
	public void testTotalNumberOfMatchesPlayed() {
		assertEquals(10, entries.size());
	}

	/** Sorted based on the Points, Goal Differences, Goals For and the Team Name **/
	/** The Highest points will the first record **/

	@Test
	public void testShowHighestPoints() {
		assertEquals(entries.get(0).getPoints(), 2);
	}

	@Test
	public void testMaximumNumberOfMatchesATeamHasWon() {
		IntSummaryStatistics summaryStatistics = entries.stream()
				.mapToInt(LeagueTableEntry::getWon).summaryStatistics();
		assertEquals(2, summaryStatistics.getMax());
	}

	@Test
	public void testMaximumNumberOfMatchesATeamHasPlayed() {
		IntSummaryStatistics summaryStatistics = entries.stream()
				.mapToInt(LeagueTableEntry::getPlayed).summaryStatistics();
		assertEquals(4, summaryStatistics.getMax());
	}

	@Test
	public void testMaximumNumberOfMatchesATeamHasLost() {
		IntSummaryStatistics summaryStatistics = entries.stream()
				.mapToInt(LeagueTableEntry::getLost).summaryStatistics();
		assertEquals(2, summaryStatistics.getMax());
	}

	@Test
	public void testShowHighestPointsTeamName() {
		LeagueTableEntry teamWithHihestPoints = entries.stream()
				.min((p1, p2) -> Integer.compare(p2.getWon(), p1.getWon()))
				.get();
		assertThat(teamWithHihestPoints.getTeamName(), is("WestHam United F.C"));
	}

	@Test
	public void testShowLowestPointsTeamName() {
		LeagueTableEntry teamWithLowestPoints = entries.stream()
				.min((p1, p2) -> Integer.compare(p1.getWon(), p2.getWon()))
				.get();
		assertThat(teamWithLowestPoints.getTeamName(), is("Liverpool F.C"));
	}

	@Test
	public void testShowTeamWhichHasDrawMatches() {
		List<LeagueTableEntry> drawnTeams = entries.stream().filter(t -> t.getDrawn() > 0).collect(Collectors.toList());
		
		assertThat(drawnTeams.get(0).getTeamName(), is("WestHam United F.C"));
		assertThat(drawnTeams.get(1).getTeamName(), is("Everton"));
	}

	@Test
	public void testPassingZeroyMatches() {
		LeagueTable leagueTable = new LeagueTable(new ArrayList<Match>());
		entries = leagueTable.getTableEntries();
		assertEquals(entries == null, true);
	}

	@Test
	public void testPassingNullMatches() {
		LeagueTable leagueTable = new LeagueTable(null);
		entries = leagueTable.getTableEntries();
		assertEquals(entries == null, true);
	}


	/** Display the league table *
	@Test
	public void displayLeagueTable(){
		entries.forEach(t -> System.out.println(t.getTeamName()+ "  Won: "+t.getWon()  + " Draw: "+t.getDrawn()   + "  "
				+ "  Lost: "+t.getLost()  + "  Played: "+t.getPlayed() + "  Goal differences: "+t.getGoalDifference()  + "  "
				+ "  Against: "+t.getGoalsAgainst()   + "  GoalFor: "+t.getGoalsFor()  + "  Points: "+t.getPoints()));
	}
	 **/

}
