package com.pulselive.leaguetable.domain;

public class LeagueTableEntry
{
	private String teamName;
	private int played;
	private int won;
	private int drawn;
	private int lost;
	private int goalsFor;
	private int goalsAgainst;
	private int goalDifference;
	private int points;

	public LeagueTableEntry( String teamName, int played, int won, int drawn, int lost,
			int goalsFor, int goalsAgainst, int goalDifference, int points )
	{
		this.teamName = teamName;
		this.played = played;
		this.won = won;
		this.drawn = drawn;
		this.lost = lost;
		this.goalsFor = goalsFor;
		this.goalsAgainst = goalsAgainst;
		this.goalDifference = goalDifference;
		this.points = points;
	}

	public String getTeamName() {
		return teamName;
	}

	public int getPlayed() {
		return played;
	}

	public int getWon() {
		return won;
	}

	public int getDrawn() {
		return drawn;
	}

	public int getLost() {
		return lost;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public int getGoalDifference() {
		return goalDifference;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeagueTableEntry other = (LeagueTableEntry) obj;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		return true;
	}
	
	
}
