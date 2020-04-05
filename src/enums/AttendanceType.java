package enums;

public interface AttendanceType {

    AttendanceType PRESENT = State.PRESENT;
    AttendanceType ABSENT = State.ABSENT;

    enum State implements AttendanceType {
        PRESENT("Aanwezig"),
        ABSENT("Afwezig");

        private final String name;

        State(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    enum Absent implements AttendanceType {
        ILL("Ziek"),
        DENTIST("Tandarts"),
        DOCTOR("Doktor"),
        FUNERAL("Begravenis"),
        WEDDING("Trouwerij"),
        ENTOMBMENT("Mummificatie"),
        OTHER("Anders");

        private final String name;

        Absent(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
