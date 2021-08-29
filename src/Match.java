public class Match
{
    private String mHomeTeam;
    private String mAwayTeam;
    private int mHomeScore;
    private int mAwayScore;

    public Match( final String homeTeam, final String awayTeam, final int homeScore, final int awayScore )
    {
        this.mHomeTeam = homeTeam;
        this.mAwayTeam = awayTeam;
        this.mHomeScore = homeScore;
        this.mAwayScore = awayScore;
    }

    public String getHomeTeam()
    {
        return mHomeTeam;
    }

    public String getAwayTeam()
    {
        return mAwayTeam;
    }

    public int getHomeScore()
    {
        return mHomeScore;
    }

    public int getAwayScore()
    {
        return mAwayScore;
    }

}
