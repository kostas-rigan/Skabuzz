package gr.aueb.dmst.jabuzzz.entities;

/**
 * Class Category is responsible for which categories the player will play.
 * @version 1.1 27/12/2021
 */
public class Category {
    /**
     * categoryName is the identifier of each Category object.
     */
    private String categoryName;
    /**
     * isSelected contains information about being selected in the game,
     * true if it is selected and false otherwise.
     */
    private boolean isSelected;

    /*
     * Class constructor initialising categoryName field.
     */
    public Category(String catName) {
        this.categoryName = catName;
    }
    
    /**
     * @return category identifier.
     */
    public String getCategoryName() {
        return this.categoryName;
    }

    /**
     * Sets the value for isSelected to true if players select this category
     * and false if it is not selected.
     * @param selected true or false, depending on whether it is selected or not.
     */
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }
    
    /**
     * Returns the value of isSelected field, which informs about the selection state
     * of this category.
     * @return true if selected or false if not selected
     */
    public boolean getSelected() {
        return this.isSelected;
    }
}
