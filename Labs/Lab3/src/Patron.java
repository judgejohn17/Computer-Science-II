/**
 * Created by johnjudge on 2/17/15.
 */

public class Patron {

    String name;
    int coolLvl;
    boolean regular;

    /**
     * Constructor for Patron
     * @param n: name of the patron
     * @param c: the coolness factor of the patron
     * @param r: boolean indication whether the patron is a regular
     */
    public Patron(String n, int c, boolean r){
        this.name = n;
        this.coolLvl = c;
        this.regular = r;
    }

    public double getPriority(){
        return coolLvl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getCoolLvl() {
        return coolLvl;
    }

    public void setCoolLvl(int coolLvl) {
        this.coolLvl = coolLvl;
    }

    public boolean isRegular() {
        return regular;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }
}
