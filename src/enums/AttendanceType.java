package enums;

public interface AttendanceType {

    AttendanceType PRESENT = State.PRESENT;
    AttendanceType ABSENT = State.ABSENT;
    String getColor();

    enum State implements AttendanceType {
        PRESENT("Aanwezig", "green"),
        ABSENT("Afwezig", "black");

        private final String name;
        private final String color;

        State(String name, String color) {
            this.name = name;
            this.color = color;
        }

        public String getColor() {
            return this.color;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    enum Absent implements AttendanceType {
        ILL("Ziek", "#ff0000"),
        DENTIST("Tandarts", "#ce0000"),
        DOCTOR("Dokter", "#a80000"),
        FUNERAL("Begravenis", "#870000"),
        WEDDING("Trouwerij", "#560000"),
        ENTOMBMENT("Mummificatie", "#380000"),
        OTHER("Anders", "#1c0000");

        private final String name;
        private final String color;

        Absent(String name, String color) {
            this.name = name;
            this.color = color;
        }

        public String getColor() {
            return this.color;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}