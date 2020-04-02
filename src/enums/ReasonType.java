package enums;

public enum ReasonType {
    ILL("Ziek"),
    DENTIST("Tandarts"),
    DOCTOR("Doktor"),
    FUNERAL("Begravenis"),
    MARRIAGE("Trouwerij"),
    ENTOMBMENT("Mummificatie"),
    OTHER("Anders");

    private final String typeName;

    ReasonType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return this.typeName;
    }
}
