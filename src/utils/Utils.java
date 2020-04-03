package utils;

import models.user.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Pattern;

public final class Utils {

    /**
     * We should not be able to make an instance of this class
     */
    private Utils() {}

    public static UUID idGenerator() {
        UUID id = UUID.randomUUID();
        return idAlreadyExists(id) ? idGenerator() : id;
    }

    private static boolean idAlreadyExists(UUID id) {
        for (User user : User.getRegisteredUsers()) {
            if (user.getUserId() == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(String text) {
        return text.chars().anyMatch(Character::isDigit);
    }

    private static final Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);

    public static boolean hasSpecial(String text) {
        return pattern.matcher(text).find();
    }

    public static String formatClassName(String field, int studyYear, char group) {
        return String.format("%s-V%d%s", field, studyYear, group);
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
