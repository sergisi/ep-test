package data;

import exceptions.data.NotAValidValue;

final public class HealthCardID implements DataInterface<String>{


    private final String personalID;
    private static String regex = "(Jo)|(Tu)";

    //TODO : Change regex so it makes sense. Note that tests will have to change accordingly.
    public HealthCardID(String code) throws NotAValidValue {
        if (code == null) {
            throw new NullPointerException("A value class must not be null.");
        }
        if (!code.matches(regex)) {
            throw new NotAValidValue("Code is not a HealthCardID.");
        }
        this.personalID = code;
    }

    public String getPersonalID() {
        return personalID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthCardID hcardID = (HealthCardID) o;
        return personalID.equals(hcardID.personalID);
    }

    @Override
    public int hashCode() {
        return personalID.hashCode();
    }

    @Override
    public String toString() {
        return "HealthCardID{" + "personal code='" + personalID + '\'' + '}';
    }

    @Override
    public String getValue() {
        return getPersonalID();
    }
}