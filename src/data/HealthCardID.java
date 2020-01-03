package data;

final public class HealthCardID {


    private final String personalID;
    private static String regex = ".*";

    public HealthCardID(String code) throws Exception {
        if (code == null) { throw new NullPointerException("A value class must not be null!"); }
        if (!code.matches(regex)) { throw new Exception("Code does not match inteded class"); }
        // TODO: Make an exception splicitally for this!! Also test
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
}