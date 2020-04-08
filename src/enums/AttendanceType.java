package enums;

public interface AttendanceType {

    AttendanceType PRESENT = State.PRESENT;
    AttendanceType ABSENT = State.ABSENT;
    String getColor();

    enum State implements AttendanceType {
        PRESENT("Aanwezig", "#1dc452"),
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
        ILL("Ziek", "#d32d2a"),
        DENTIST("Tandarts", "#c12b28"),
        DOCTOR("Dokter", "#a82321"),
        FUNERAL("Begravenis", "#961d1b"),
        WEDDING("Trouwerij", "#821716"),
        ENTOMBMENT("Mummificatie", "#701514"),
        OTHER("Anders", "#7c0d0b");

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
