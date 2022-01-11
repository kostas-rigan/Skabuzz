package gr.aueb.dmst.jabuzzz.entities;

/**
 * Representation of Team concept.
 * 
 * @version 1.0 17/11/2021
 */
public class Team {
    /** teamName is the name that describes a created Team. */
    private String teamName;

    /**
     * Class constructor specifying team's name.
     *
     * @param name team's specified name
     */
    public Team(final String name) {
        super();
        this.teamName = name;
    }

    /**
     * Class constructor with default value.
     */
    public Team() {
        this.teamName = "";
    }

    /**
     *
     * @return team's current name
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Specifies new team name.
     *
     * @param name team's specified name
     */
    public void setTeamName(final String name) {
        this.teamName = name;
    }

    /**
     * Represents a Team object by returning its name.
     * 
     * @return name that defines a team
     */
    @Override
    public String toString() {
        return this.getTeamName();
    }
}
