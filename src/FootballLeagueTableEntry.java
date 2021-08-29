import java.util.Objects;

public class FootballLeagueTableEntry
{
    private String mTeamName; // 4th by team name in alphabetical order
    private int mPlayed;
    private int mWon;
    private int mDrawn;
    private int mLost;
    private int mGoalsFor; // 3rd sort by goals descending
    private int mGoalsAgainst;
    private int mGoalDifference; // 2nd sort by goal difference descending
    private int mPoints; // 1st sort by descending

    public FootballLeagueTableEntry(final String teamName, final int played, final int won, final int drawn, final int lost,
                                    final int goalsFor, final int goalsAgainst, final int goalDifference, final int points )
    {
        this.mTeamName = teamName;
        this.mPlayed = played;
        this.mWon = won;
        this.mDrawn = drawn;
        this.mLost = lost;
        this.mGoalsFor = goalsFor;
        this.mGoalsAgainst = goalsAgainst;
        this.mGoalDifference = goalDifference;
        this.mPoints = points;
    }

    public String getTeamName()
    {
        return mTeamName;
    }

    public void setTeamName( String teamName )
    {
        this.mTeamName = teamName;
    }

    public int getPlayed()
    {
        return mPlayed;
    }

    public void setPlayed( int played )
    {
        this.mPlayed = played;
    }

    public int getWon()
    {
        return mWon;
    }

    public void setWon( int won )
    {
        this.mWon = won;
    }

    public int getDrawn()
    {
        return mDrawn;
    }

    public void setDrawn( int drawn )
    {
        this.mDrawn = drawn;
    }

    public int getLost()
    {
        return mLost;
    }

    public void setLost( int lost )
    {
        this.mLost = lost;
    }

    public int getGoalsFor()
    {
        return mGoalsFor;
    }

    public void setGoalsFor( int goalsFor )
    {
        this.mGoalsFor = goalsFor;
    }

    public int getGoalsAgainst()
    {
        return mGoalsAgainst;
    }

    public void setGoalsAgainst( int goalsAgainst )
    {
        this.mGoalsAgainst = goalsAgainst;
    }

    public int getGoalDifference()
    {
        return mGoalDifference;
    }

    public void setGoalDifference( int goalDifference )
    {
        this.mGoalDifference = goalDifference;
    }

    public int getPoints()
    {
        return mPoints;
    }

    public void setPoints( int points )
    {
        this.mPoints = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballLeagueTableEntry that = (FootballLeagueTableEntry) o;
        return mPlayed == that.mPlayed && mWon == that.mWon && mDrawn == that.mDrawn && mLost == that.mLost && mGoalsFor == that.mGoalsFor && mGoalsAgainst == that.mGoalsAgainst && mGoalDifference == that.mGoalDifference && mPoints == that.mPoints && mTeamName.equals(that.mTeamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mTeamName, mPlayed, mWon, mDrawn, mLost, mGoalsFor, mGoalsAgainst, mGoalDifference, mPoints);
    }

}
