import java.util.*;

public class FootballLeagueTable {

    private final List<Match> mMatchesTheLeagueTableIsFor;

    public FootballLeagueTable(final List<Match> matches) {
        mMatchesTheLeagueTableIsFor = matches;
    }

    /**
     * Get the ordered list of league table entries for this league table.
     *
     * @return
     */
    public List<FootballLeagueTableEntry> getTableEntries() {

        LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteriaToUse = new LinkedHashMap<Helper.SortBy, Helper.SortOrder>();
        sortCriteriaToUse.put(Helper.SortBy.POINTS, Helper.SortOrder.DESCENDING);
        sortCriteriaToUse.put(Helper.SortBy.GOAL_DIFFERENCE, Helper.SortOrder.DESCENDING);
        sortCriteriaToUse.put(Helper.SortBy.GOALS_FOR, Helper.SortOrder.DESCENDING);
        sortCriteriaToUse.put(Helper.SortBy.TEAM_NAME, Helper.SortOrder.ASCENDING);

        return getTableEntries(3, 1, sortCriteriaToUse);

    }

    // created to make it extensible and also to facilitate better testing.
    public List<FootballLeagueTableEntry> getTableEntries(int pointsAwinIsWorth, int pointsAdrawIsWorth, LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteria) {

        ArrayList<FootballLeagueTableEntry> unsortedLeagueTableEntriesList = calculateLeagueTableEntriesListAndReturnUnsorted(pointsAwinIsWorth, pointsAdrawIsWorth);
        unsortedLeagueTableEntriesList.sort(getComparatorWithSortCriteriaApplied(sortCriteria));

        ArrayList<FootballLeagueTableEntry> sortedLeagueTableEntriesList = unsortedLeagueTableEntriesList; // variable is really not needed but for clarity as the list is now sorted (otherwise it would say unsorted...)
        return sortedLeagueTableEntriesList;

    }


    private Set<String> getAllTeamNames() {

        Set<String> teamNames = new HashSet<>();
        for (Match match : mMatchesTheLeagueTableIsFor) {
            teamNames.add(match.getHomeTeam());
            teamNames.add(match.getAwayTeam());
        }
        return teamNames;

    }

    private ArrayList<FootballLeagueTableEntry> calculateLeagueTableEntriesListAndReturnUnsorted(int pointsAwinIsWorth, int pointsAdrawIsWorth) {

        // these variables will temporarily store values until all the calculations are done and the League table entry is created.
        String lteTeamName = "";
        int ltePlayed; // lte stands for League Table Entry
        int lteWon;
        int lteDrawn;
        int lteLost;
        int lteGoalsFor; // total number of goals scored by the team
        int lteGoalsAgainst; // total number of goals conceded by the team
        int lteGoalDifference; // the difference between total goals scored by the team and the total goals conceded
        int ltePoints;

        Set<String> teamNames = getAllTeamNames();

        ArrayList<FootballLeagueTableEntry> unsortedLeagueTableEntriesList = new ArrayList<>();

        for (String teamName : teamNames) {

            lteTeamName = teamName;
            ltePlayed = 0; // lte stands for League Table Entry
            lteWon = 0;
            lteDrawn = 0;
            lteLost = 0;
            lteGoalsFor = 0;
            lteGoalsAgainst = 0;

            for (Match match : mMatchesTheLeagueTableIsFor) {

                if (teamName.equals(match.getHomeTeam())) { // team we are interested in is playing as the home team

                    ltePlayed++;
                    if (match.getHomeScore() > match.getAwayScore()) {
                        lteWon++;
                    } else if (match.getHomeScore() == match.getAwayScore()) { // this condition is placed here so that there is some systematical order in the if else conditions. I.e. first greater than, then equal and lastly less than
                        lteDrawn++;
                    } else { // home score is less than away score
                        lteLost++;
                    }
                    lteGoalsFor = lteGoalsFor + match.getHomeScore();
                    lteGoalsAgainst = lteGoalsAgainst + match.getAwayScore();

                } else if (teamName.equals(match.getAwayTeam())) { // team we are interested in is playing as the away team

                    ltePlayed++;
                    if (match.getAwayScore() > match.getHomeScore()) {
                        lteWon++;
                    } else if (match.getAwayScore() == match.getHomeScore()) {
                        lteDrawn++;
                    } else { // away score is less than home score
                        lteLost++;
                    }
                    lteGoalsFor = lteGoalsFor + match.getAwayScore();
                    lteGoalsAgainst = lteGoalsAgainst + match.getHomeScore();

                }

            }

            lteGoalDifference = lteGoalsFor - lteGoalsAgainst;
            ltePoints = (lteWon * pointsAwinIsWorth) + (lteDrawn * pointsAdrawIsWorth);
            FootballLeagueTableEntry footballLeagueTableEntry = new FootballLeagueTableEntry(lteTeamName, ltePlayed, lteWon, lteDrawn, lteLost, lteGoalsFor, lteGoalsAgainst, lteGoalDifference, ltePoints);
            unsortedLeagueTableEntriesList.add(footballLeagueTableEntry);

        }

        return unsortedLeagueTableEntriesList;

    }

    // This can be used get a more advanced Comparator which has several chained Comparators
    private Comparator getComparatorWithSortCriteriaApplied(LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteria) { // A LinkedHashMap has been used as the order of the sort criteria are important.

        int countOfComparators = 0;
        Comparator theChainedComparator = null;

        // Could not use the Java8 Lambda syntax as it was tricky to get the outer variables to mutate from inside a lambda.
        for (Map.Entry<Helper.SortBy, Helper.SortOrder> entry : sortCriteria.entrySet()) {

            Helper.SortBy sortByKey = entry.getKey();
            Helper.SortOrder sortOrderValue = entry.getValue();

            switch (sortByKey) {
                case TEAM_NAME:
                    if (countOfComparators == 0) { // This means it is the first comparator. So, .comparing is used instead of thenComparing. Similarly for the other cases.
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getTeamName, String.CASE_INSENSITIVE_ORDER).reversed(); // as we do not want to take capitalization into account.
                        } else {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getTeamName, String.CASE_INSENSITIVE_ORDER);
                        }
                    } else {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getTeamName, String.CASE_INSENSITIVE_ORDER).reversed());
                        } else {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getTeamName, String.CASE_INSENSITIVE_ORDER));
                        }
                    }
                    break;
                case PLAYED:
                    if (countOfComparators == 0) {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getPlayed).reversed();
                        } else {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getPlayed);
                        }
                    } else {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getPlayed).reversed());
                        } else {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getPlayed));
                        }
                    }
                    break;
                case WON:
                    if (countOfComparators == 0) {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getWon).reversed();
                        } else {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getWon);
                        }
                    } else {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getWon).reversed());
                        } else {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getWon));
                        }
                    }
                    break;
                case DRAWN:
                    if (countOfComparators == 0) {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getDrawn).reversed();
                        } else {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getDrawn);
                        }
                    } else {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getDrawn).reversed());
                        } else {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getDrawn));
                        }
                    }
                    break;
                case LOST:
                    if (countOfComparators == 0) {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getLost).reversed();
                        } else {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getLost);
                        }
                    } else {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getLost).reversed());
                        } else {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getLost));
                        }
                    }
                    break;
                case GOALS_FOR:
                    if (countOfComparators == 0) {
                        if(sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getGoalsFor).reversed();
                        } else {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getGoalsFor);
                        }
                    } else {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparingInt(FootballLeagueTableEntry::getGoalsFor).reversed());
                        } else {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getGoalsFor));
                        }
                    }
                    break;
                case GOALS_AGAINST:
                    if (countOfComparators == 0) {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getGoalsAgainst).reversed();
                        } else {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getGoalsAgainst);
                        }
                    } else {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getGoalsAgainst).reversed());
                        } else {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getGoalsAgainst));
                        }
                    }
                    break;
                case GOAL_DIFFERENCE:
                    if (countOfComparators == 0) {
                        if(sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getGoalDifference).reversed();
                        } else {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getGoalDifference);
                        }
                    } else {
                        if (sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getGoalDifference).reversed());
                        } else {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getGoalDifference));
                        }
                    }
                    break;
                case POINTS:
                    if (countOfComparators == 0) {
                        if(sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getPoints).reversed();
                        } else {
                            theChainedComparator = Comparator.comparing(FootballLeagueTableEntry::getPoints);
                        }
                    } else {
                        if(sortOrderValue.equals(Helper.SortOrder.DESCENDING)) {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getPoints).reversed());
                        } else {
                            theChainedComparator = theChainedComparator.thenComparing(Comparator.comparing(FootballLeagueTableEntry::getPoints));
                        }
                    }
                    break;
            }

            countOfComparators++;

        }

        return theChainedComparator;

    }

}
