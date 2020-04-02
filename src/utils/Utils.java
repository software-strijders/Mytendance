package utils;

import models.user.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Pattern;

public class Utils {

    public static UUID idGenerator() {
        UUID id = UUID.randomUUID();
        return idAlreadyExists(id) ? idGenerator() : id;
    }

    static boolean idAlreadyExists(UUID id) {
        for (User user : User.getRegisteredUsers()) {
            if (user.getUserId() == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(String text) {
        return text.chars().allMatch(Character::isDigit);
    }

    public static String formatClassName(String field, int studyYear, char group) {
        return String.format("%s-V%d%s", field, studyYear, group);
    }

    public static char getCharFromStringByIndex(String string, int index) {
        return string.isEmpty() ? '0' : string.charAt(index);
    }

    public static String capitalize(String string) {
        if (string == null || string.isEmpty())
            return ""; // Nothing to capitalize here

        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    // Author: Jason Buberel
    private static final Pattern EMAIL_ADDR_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isEmailValid(String email) {
        return EMAIL_ADDR_REGEX.matcher(email).find();
    }

    public static String formatDateTime(LocalDateTime date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}
