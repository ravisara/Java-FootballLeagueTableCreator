import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class FootballLeagueTableTest {

    public List<Match> getGroupDlistOfMatchesEuro2020() {

        // Actual data from UEFA Euro England matches
        Match match1 = new Match("England", "Croatia", 1, 0);
        Match match2 = new Match("England", "Scotland", 0, 0);
        Match match3 = new Match("Czech Republic", "England", 0, 1);

        // Croatia matches (excluding the above matches)
        Match match4 = new Match("Croatia", "Czech Republic", 1, 1);
        Match match5 = new Match("Croatia", "Scotland", 3, 1);

        // Czech Republic matches (excluding the above matches)
        Match match6 = new Match("Scotland", "Czech Republic", 0, 2);

        return new ArrayList<>(Arrays.asList(match1, match2, match3, match4, match5, match6));

    }

    public List<Match> getGroupFlistOfMatchesEuro2020() {

        // Actual data from UEFA Euro
        Match match1 = new Match("France", "Germany", 1, 0);
        Match match2 = new Match("Hungary", "France", 1, 1);
        Match match3 = new Match("Portugal", "France", 2, 2);
        Match match4 = new Match("Portugal", "Germany", 2, 4);
        Match match5 = new Match("Germany", "Hungary", 2, 2);
        Match match6 = new Match("Hungary", "Portugal", 0, 3);

        return new ArrayList<>(Arrays.asList(match1, match2, match3, match4, match5, match6));

    }

    @Test
    public void givenAllCorrespondingStatsOfAllTeamsAreEqual_whenLeagueTableIsSorted_thenListIsSortedAlphabeticallyByTeamName_variation1() {

        // Expected result
        FootballLeagueTableEntry lte1 = new FootballLeagueTableEntry("Croatia", 3, 0, 3, 0, 3, 3, 0, 3);
        FootballLeagueTableEntry lte2 = new FootballLeagueTableEntry("Czech Republic", 3, 0, 3, 0, 3, 3, 0, 3);
        FootballLeagueTableEntry lte3 = new FootballLeagueTableEntry("England", 3, 0, 3, 0, 3, 3, 0, 3);
        FootballLeagueTableEntry lte4 = new FootballLeagueTableEntry("Scotland", 3, 0, 3, 0, 3, 3, 0, 3);
        List<FootballLeagueTableEntry> expectedResult = Arrays.asList(lte1, lte2, lte3, lte4);

        // Actual result
        Match match1Hypothetical = new Match("England", "Croatia", 1, 1);
        Match match2Hypothetical = new Match("England", "Scotland", 1, 1);
        Match match3Hypothetical = new Match("Czech Republic", "England", 1, 1);
        Match match4Hypothetical = new Match("Croatia", "Czech Republic", 1, 1);
        Match match5Hypothetical = new Match("Croatia", "Scotland", 1, 1);
        Match match6Hypothetical = new Match("Scotland", "Czech Republic", 1, 1);
        List<Match> listOfMatches = new ArrayList<>(Arrays.asList(match1Hypothetical, match2Hypothetical, match3Hypothetical, match4Hypothetical, match5Hypothetical, match6Hypothetical));
        FootballLeagueTable lt = new FootballLeagueTable(listOfMatches);
        List<FootballLeagueTableEntry> actualSortedLeaguetableEntries = lt.getTableEntries();

        assertEquals(expectedResult, actualSortedLeaguetableEntries);

    }

    @Test
    public void givenAllCorrespondingStatsOfAllTeamsAreEqual_whenLeagueTableIsSorted_thenListIsSortedAlphabeticallyByTeamName_variation2() {

        // Expected result
        FootballLeagueTableEntry lte1 = new FootballLeagueTableEntry("EnglandA", 3, 0, 3, 0, 3, 3, 0, 3);
        FootballLeagueTableEntry lte2 = new FootballLeagueTableEntry("EnglandB", 3, 0, 3, 0, 3, 3, 0, 3);
        FootballLeagueTableEntry lte3 = new FootballLeagueTableEntry("EnglandC", 3, 0, 3, 0, 3, 3, 0, 3);
        FootballLeagueTableEntry lte4 = new FootballLeagueTableEntry("EnglandD", 3, 0, 3, 0, 3, 3, 0, 3);
        List<FootballLeagueTableEntry> expectedResult = Arrays.asList(lte1, lte2, lte3, lte4);

        // Actual result
        Match match1Hypothetical = new Match("EnglandA", "EnglandB", 1, 1);
        Match match2Hypothetical = new Match("EnglandA", "EnglandC", 1, 1);
        Match match3Hypothetical = new Match("EnglandD", "EnglandA", 1, 1);
        Match match4Hypothetical = new Match("EnglandB", "EnglandD", 1, 1);
        Match match5Hypothetical = new Match("EnglandB", "EnglandC", 1, 1);
        Match match6Hypothetical = new Match("EnglandC", "EnglandD", 1, 1);
        List<Match> listOfMatches = new ArrayList<>(Arrays.asList(match1Hypothetical, match2Hypothetical, match3Hypothetical, match4Hypothetical, match5Hypothetical, match6Hypothetical));
        FootballLeagueTable lt = new FootballLeagueTable(listOfMatches);
        List<FootballLeagueTableEntry> actualSortedLeaguetableEntries = lt.getTableEntries();

        // test
        assertEquals(expectedResult, actualSortedLeaguetableEntries);

    }


    @org.junit.Test
    public void givenEuro2020GroupDActualMatchesData_whenTheTopTeamnameOfTheSortedLeagueTableIsFound_thenItIsEngland() {

        FootballLeagueTable groupLeagueTableEuro2020 = new FootballLeagueTable(getGroupDlistOfMatchesEuro2020());
        assertEquals(groupLeagueTableEuro2020.getTableEntries().get(0).getTeamName(), "England");

    }

    @org.junit.Test
    public void givenEuro2020GroupDActualMatchesData_whenTheBottomTeamnameOfTheSortedLeagueTableIsFound_thenItIsScotland() {

        // expected
        FootballLeagueTable groupLeagueTableEuro2020 = new FootballLeagueTable(getGroupDlistOfMatchesEuro2020());
        String expected = groupLeagueTableEuro2020.getTableEntries().get(3).getTeamName();

        // actual
        String actual = "Scotland";

        assertEquals(expected, actual );

    }

    @Test
    public void givenEuro2020GroupDActualMatchesData_whenLeagueTableIsCalculatedAndResultsAreSorted_thenEnglandAreOnTop() {

        // expected
        FootballLeagueTableEntry expectedResult = new FootballLeagueTableEntry("England", 3, 2, 1, 0, 2, 0, 2, 7);

        // actual
        List<Match> matches = getGroupDlistOfMatchesEuro2020();
        FootballLeagueTable groupDleagueTableEuro2020 = new FootballLeagueTable(matches);
        List<FootballLeagueTableEntry> sortedLeagueTableEntries = groupDleagueTableEuro2020.getTableEntries();

        assertEquals(expectedResult, sortedLeagueTableEntries.get(0));

    }

    @Test
    public void givenEuro2020GroupDActualMatchesData_whenLeagueTableIsCalculatedAndResultsAreSorted_thenIsIdenticalWithActualTableFromUEFA() {

        // expected
        List<FootballLeagueTableEntry> expected= Arrays.asList(
                new FootballLeagueTableEntry("France", 3, 1, 2, 0,  4, 3, 1, 5),
                new FootballLeagueTableEntry("Portugal", 3, 1, 1, 1, 7, 6, 1, 4),
                new FootballLeagueTableEntry("Germany", 3, 1, 1, 1, 6, 5, 1, 4),
                new FootballLeagueTableEntry("Hungary", 3, 0, 2, 1, 3, 6, -3, 2));

        // actual
        List<Match> matches = getGroupFlistOfMatchesEuro2020();
        FootballLeagueTable groupFleagueTableEuro2020 = new FootballLeagueTable(matches);
        List<FootballLeagueTableEntry> actual = groupFleagueTableEuro2020.getTableEntries();

        assertEquals(expected, actual);

    }

    @Test
    public void givenEuro2020GroupDActualMatchesData_whenSortOrderIsByAscendingAlphabetically_thenTheResultIsAsExpected() {

        // expected
        List<FootballLeagueTableEntry> expected= Arrays.asList(
                new FootballLeagueTableEntry("France", 3, 1, 2, 0,  4, 3, 1, 5),
                new FootballLeagueTableEntry("Germany", 3, 1, 1, 1, 6, 5, 1, 4),
                new FootballLeagueTableEntry("Hungary", 3, 0, 2, 1, 3, 6, -3, 2),
                new FootballLeagueTableEntry("Portugal", 3, 1, 1, 1, 7, 6, 1, 4));

        // actual
        List<Match> matches = getGroupFlistOfMatchesEuro2020();
        FootballLeagueTable groupFleagueTableEuro2020 = new FootballLeagueTable(matches);

        LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteriaToUse = new LinkedHashMap<Helper.SortBy, Helper.SortOrder>();
        sortCriteriaToUse.put(Helper.SortBy.TEAM_NAME, Helper.SortOrder.ASCENDING);
        List<FootballLeagueTableEntry> actual = groupFleagueTableEuro2020.getTableEntries(3,1, sortCriteriaToUse);

        assertEquals(expected, actual);

    }

    @Test
    public void givenEuro2020GroupDActualMatchesData_whenSortOrderIsByDescendingAlphabetically_thenTheResultIsAsExpected() {

        // expected
        List<FootballLeagueTableEntry> expected= Arrays.asList(
                new FootballLeagueTableEntry("Portugal", 3, 1, 1, 1, 7, 6, 1, 4),
                new FootballLeagueTableEntry("Hungary", 3, 0, 2, 1, 3, 6, -3, 2),
                new FootballLeagueTableEntry("Germany", 3, 1, 1, 1, 6, 5, 1, 4),
                new FootballLeagueTableEntry("France", 3, 1, 2, 0,  4, 3, 1, 5));

        // actual
        List<Match> matches = getGroupFlistOfMatchesEuro2020();
        FootballLeagueTable groupFleagueTableEuro2020 = new FootballLeagueTable(matches);
        LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteriaToUse = new LinkedHashMap<Helper.SortBy, Helper.SortOrder>();
        sortCriteriaToUse.put(Helper.SortBy.TEAM_NAME, Helper.SortOrder.DESCENDING);
        List<FootballLeagueTableEntry> actual = groupFleagueTableEuro2020.getTableEntries(3,1, sortCriteriaToUse);

        assertEquals(expected, actual);

    }

    @Test
    public void givenEuro2020GroupDActualMatchesData_whenSortOrderIsByGoalsForAscending_thenTheResultIsAsExpected() {

        // expected
        List<FootballLeagueTableEntry> expected= Arrays.asList(
                new FootballLeagueTableEntry("Hungary", 3, 0, 2, 1, 3, 6, -3, 2),
                new FootballLeagueTableEntry("France", 3, 1, 2, 0,  4, 3, 1, 5),
                new FootballLeagueTableEntry("Germany", 3, 1, 1, 1, 6, 5, 1, 4),
                new FootballLeagueTableEntry("Portugal", 3, 1, 1, 1, 7, 6, 1, 4));

        // actual
        List<Match> matches = getGroupFlistOfMatchesEuro2020();
        FootballLeagueTable groupFleagueTableEuro2020 = new FootballLeagueTable(matches);
        LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteriaToUse = new LinkedHashMap<Helper.SortBy, Helper.SortOrder>();
        sortCriteriaToUse.put(Helper.SortBy.GOALS_FOR, Helper.SortOrder.ASCENDING);
        List<FootballLeagueTableEntry> actual = groupFleagueTableEuro2020.getTableEntries(3,1, sortCriteriaToUse);

        assertEquals(expected, actual);

    }

    @Test
    public void givenEuro2020GroupDActualMatchesData_whenSortOrderIsByGoalsDifferenceDescendingAndTeamnameAscendingAplphabatically_thenTheResultIsAsExpected() {

        // expected
        List<FootballLeagueTableEntry> expected= Arrays.asList(
                new FootballLeagueTableEntry("France", 3, 1, 2, 0,  4, 3, 1, 5),
                new FootballLeagueTableEntry("Germany", 3, 1, 1, 1, 6, 5, 1, 4),
                new FootballLeagueTableEntry("Portugal", 3, 1, 1, 1, 7, 6, 1, 4),
                new FootballLeagueTableEntry("Hungary", 3, 0, 2, 1, 3, 6, -3, 2));

        // actual
        List<Match> matches = getGroupFlistOfMatchesEuro2020();
        FootballLeagueTable groupFleagueTableEuro2020 = new FootballLeagueTable(matches);
        LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteriaToUse = new LinkedHashMap<Helper.SortBy, Helper.SortOrder>();
        sortCriteriaToUse.put(Helper.SortBy.GOAL_DIFFERENCE, Helper.SortOrder.DESCENDING);
        sortCriteriaToUse.put(Helper.SortBy.TEAM_NAME, Helper.SortOrder.ASCENDING);

        List<FootballLeagueTableEntry> actual = groupFleagueTableEuro2020.getTableEntries(3,1, sortCriteriaToUse);

        assertEquals(expected, actual);

    }

    @Test
    public void givenEuro2020GroupDActualMatchesData_whenSortOrderIsByGoalsDifferenceDescendingAndTeamnameDesendingAplphabatically_thenTheResultIsAsExpected() {

        // expected
        List<FootballLeagueTableEntry> expected= Arrays.asList(
                new FootballLeagueTableEntry("Portugal", 3, 1, 1, 1, 7, 6, 1, 4),
                new FootballLeagueTableEntry("Germany", 3, 1, 1, 1, 6, 5, 1, 4),
                new FootballLeagueTableEntry("France", 3, 1, 2, 0,  4, 3, 1, 5),
                new FootballLeagueTableEntry("Hungary", 3, 0, 2, 1, 3, 6, -3, 2));

        // actual
        List<Match> matches = getGroupFlistOfMatchesEuro2020();
        FootballLeagueTable groupFleagueTableEuro2020 = new FootballLeagueTable(matches);
        LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteriaToUse = new LinkedHashMap<Helper.SortBy, Helper.SortOrder>();
        sortCriteriaToUse.put(Helper.SortBy.GOAL_DIFFERENCE, Helper.SortOrder.DESCENDING);
        sortCriteriaToUse.put(Helper.SortBy.TEAM_NAME, Helper.SortOrder.DESCENDING);
        List<FootballLeagueTableEntry> actual = groupFleagueTableEuro2020.getTableEntries(3,1, sortCriteriaToUse);

        assertEquals(expected, actual);

    }

    @Test
    public void givenEuro2020GroupDActualMatchesData_whenSortOrderIsByGoalsDifferenceAscendingAndTeamnameDescendingAlphabatically_thenTheResultIsAsExpected() {

        // expected
        List<FootballLeagueTableEntry> expected= Arrays.asList(
                new FootballLeagueTableEntry("Hungary", 3, 0, 2, 1, 3, 6, -3, 2),
                new FootballLeagueTableEntry("Portugal", 3, 1, 1, 1, 7, 6, 1, 4),
                new FootballLeagueTableEntry("Germany", 3, 1, 1, 1, 6, 5, 1, 4),
                new FootballLeagueTableEntry("France", 3, 1, 2, 0,  4, 3, 1, 5));

        // actual
        List<Match> matches = getGroupFlistOfMatchesEuro2020();
        FootballLeagueTable groupFleagueTableEuro2020 = new FootballLeagueTable(matches);
        LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteriaToUse = new LinkedHashMap<Helper.SortBy, Helper.SortOrder>();
        sortCriteriaToUse.put(Helper.SortBy.GOAL_DIFFERENCE, Helper.SortOrder.ASCENDING);
        sortCriteriaToUse.put(Helper.SortBy.TEAM_NAME, Helper.SortOrder.DESCENDING);
        List<FootballLeagueTableEntry> actual = groupFleagueTableEuro2020.getTableEntries(3,1, sortCriteriaToUse);

        assertEquals(expected, actual);

    }

    @Test
    public void givenEuro2020GroupDActualMatchesData_whenSortOrderIsByGoalsDifferenceAscendingAndTeamnameAscendingAlphabatically_thenTheResultIsAsExpected() {

        // expected
        List<FootballLeagueTableEntry> expected= Arrays.asList(
                new FootballLeagueTableEntry("Hungary", 3, 0, 2, 1, 3, 6, -3, 2),
                new FootballLeagueTableEntry("France", 3, 1, 2, 0,  4, 3, 1, 5),
                new FootballLeagueTableEntry("Germany", 3, 1, 1, 1, 6, 5, 1, 4),
                new FootballLeagueTableEntry("Portugal", 3, 1, 1, 1, 7, 6, 1, 4));

        // actual
        List<Match> matches = getGroupFlistOfMatchesEuro2020();
        FootballLeagueTable groupFleagueTableEuro2020 = new FootballLeagueTable(matches);
        LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteriaToUse = new LinkedHashMap<Helper.SortBy, Helper.SortOrder>();
        sortCriteriaToUse.put(Helper.SortBy.GOAL_DIFFERENCE, Helper.SortOrder.ASCENDING);
        sortCriteriaToUse.put(Helper.SortBy.TEAM_NAME, Helper.SortOrder.ASCENDING);
        List<FootballLeagueTableEntry> actual = groupFleagueTableEuro2020.getTableEntries(3,1, sortCriteriaToUse);

        assertEquals(expected, actual);

    }

    @Test
    public void givenEuro2020GroupDActualMatchesData_whenSortOrderIsByGoalsForDesendingGoalsLostDescendingAndGoalDifferenceDescending_thenTheResultIsAsExpected() {

        // expected
        List<FootballLeagueTableEntry> expected= Arrays.asList(
                new FootballLeagueTableEntry("Portugal", 3, 1, 1, 1, 7, 6, 1, 4),
                new FootballLeagueTableEntry("Germany", 3, 1, 1, 1, 6, 5, 1, 4),
                new FootballLeagueTableEntry("France", 3, 1, 2, 0,  4, 3, 1, 5),
                new FootballLeagueTableEntry("Hungary", 3, 0, 2, 1, 3, 6, -3, 2));

        // actual
        List<Match> matches = getGroupFlistOfMatchesEuro2020();
        FootballLeagueTable groupFleagueTableEuro2020 = new FootballLeagueTable(matches);
        LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteriaToUse = new LinkedHashMap<Helper.SortBy, Helper.SortOrder>();
        sortCriteriaToUse.put(Helper.SortBy.GOALS_FOR, Helper.SortOrder.DESCENDING);
        sortCriteriaToUse.put(Helper.SortBy.LOST, Helper.SortOrder.DESCENDING);
        sortCriteriaToUse.put(Helper.SortBy.GOAL_DIFFERENCE, Helper.SortOrder.DESCENDING);
        List<FootballLeagueTableEntry> actual = groupFleagueTableEuro2020.getTableEntries(3,1, sortCriteriaToUse);

        assertEquals(expected, actual);

    }



}