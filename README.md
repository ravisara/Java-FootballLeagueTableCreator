# Project name:
Football League Table Generator

# Description:
* When all the matches played by different teams in the league phase of a tournament are fed in along with the final scores of each match, the required sort criteria and the points to award for a win and a draw are specified, this project can generate the sorted league table automatically. 
* There is a default simple usage(described in detail in Step 3.1) where the sort criteria, points a win is worth and points a draw is worth are already predefined. In this mode the league table will be generated according to these predefined criteria.
* If more customization is needed with regard to the sort criteria and the way points are allocated, that is also supported(described in detail in Step 3.2). For that, there is an overloaded method with the following signature to facilitate future reuse and extension. This can be used to sort in ascending or descending order of **any** field in the `FootballLeagueTableEntry` class. The sorting is done by using chained Comparators and is quite flexible.

# Installation:
* You can either clone the repository or download 
* This project and the LeageTable class have been developed in a Java 8 environment. Therefore, it is best to be checked in a Java8 environment. Some code may not compile if tested under an older version of the JDK than 8.
* The project has been tested in IntelliJ IDEA Community 2021.2.1 The entire project has been included so that if required the entire project can be loaded.
* Please use the `FootballLeagueTableEntry` class included in this project as it has got two overridden methods with the following signatures which are used in unit testing:
    `public boolean equals(Object o)` and ` public int hashCode()`
* Please make sure that `Helper` class is also used when testing as the `FootballLeagueTable` class depends on it.
* `FootballLeagueTableTest` class can be used to test the `FootballLeagueTable` class to a good extent.

# Usage:

## Step 1
Create the Matches that should be taken into account in the League Table and store them in variables
A match can be created as follows where the team names are followed by the scores of each team in order of the team names: 
```new Match("England", "Croatia", 1, 0);```

E.g. create 6 matches with their end results as follows

// Actual data from UEFA Euro England matches
```Match match1 = new Match("England", "Croatia", 1, 0); // England score is 1 and Croatia score is 0```
```Match match2 = new Match("England", "Scotland", 0, 0);```
```Match match3 = new Match("Czech Republic", "England", 0, 1);```

// Croatia matches (excluding the above matches)
```Match match4 = new Match("Croatia", "Czech Republic", 1, 1);```
```Match match5 = new Match("Croatia", "Scotland", 3, 1);```

// Czech Republic matches (excluding the above matches)
```Match match6 = new Match("Scotland", "Czech Republic", 0, 2);```

// Create a list of matches 
```List<Match> matches = ArrayList<>(Arrays.asList(match1, match2, match3, match4, match5, match6));```

## Step 2

Feed in the matches list to the FootballLeagueTable class and store an instance of it in a variable of type as shown below```FootballLeagueTable```
```FootballLeagueTable groupFleagueTableEuro2020 = new FootballLeagueTable(matches);```

## Step 3
### 3.1) Default usage
By default, if simply getTableEntries() is called on the above object without passing any arguments, a list of FootballLeagueTableEntry objects will be returned with the following criteria applied:

* sort by points descending
* then sort by goal difference descending
* then sort by goals for descending
* then sort by team name ascending

* A win is worth 3 points, and a draw is worth 1 point.

`List<FootballLeagueTableEntry> finalizedLeagueTable = groupFleagueTableEuro2020.getTableEntries() // if the getTableEntries() method is called on the groupFleagueTableEuro2020 object above, it will use the above criteria` 

For customizing the sort by fields and specifying the sort order for each field, please refer the customization section immediately below.

### 3.2) Customization (only if the default sort criteria above and the points for a win and a draw are not enough, and you want to use the full power of customization)
If customization is needed, ignore Step 3.1 above and continue from here.

Create a LinkedHashMap to specify and hold the sort criteria to use. A normal HashMap would not do as the order of the entries matter.
`LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteriaToUse = new LinkedHashMap<Helper.SortBy, Helper.SortOrder>();`

Table can be sorted in the Ascending or descending order by using any combination of the following :TEAM_NAME, PLAYED, WON, DRAWN, LOST, GOALS_FOR, GOALS_AGAINST, GOAL_DIFFERENCE, POINTS.

In the example below, the table is going to be sorted first in descending order of GOALS_FOR, then by LOST descending and finally by GOAL_DIFFERENCE descending. 

`sortCriteriaToUse.put(Helper.SortBy.GOALS_FOR, Helper.SortOrder.DESCENDING);`
`sortCriteriaToUse.put(Helper.SortBy.LOST, Helper.SortOrder.DESCENDING);`
`sortCriteriaToUse.put(Helper.SortBy.GOAL_DIFFERENCE, Helper.SortOrder.DESCENDING);`

The order the sort criteria are entered matters. If the first two lines above are swapped then it will first be sorted by LOST and then by GOALS_FOR.

Then call the `getTableEntries(3,1, sortCriteriaToUse)` method of the `groupFleagueTableEuro2020` object of the `FootballLeagueTable` class created in step 2 above.
`List<FootballLeagueTableEntry> finalizedLeagueTable = groupFleagueTableEuro2020.getTableEntries(3,1, sortCriteriaToUse);`

Note that the signature of the above method is as follows. So, in the example above, a point is worth 3 and a draw is worth 1. But arbitrary numbers can be used instead of those numbers depending on your requirements.
`public List<FootballLeagueTableEntry> getTableEntries(int pointsAwinIsWorth, int pointsAdrawIsWorth, LinkedHashMap<Helper.SortBy, Helper.SortOrder> sortCriteria) `

`FootballLeagueTableTest` class can be used to test the `FootballLeagueTable` class to a good extent. If further guidance is needed, it is best to refer to the unit tests in this class for usage examples.

# Contributing:
No contributions are expected or necessary. If you find any issue with the code, please kindly report it as an issue.

# License: 
MIT license 









