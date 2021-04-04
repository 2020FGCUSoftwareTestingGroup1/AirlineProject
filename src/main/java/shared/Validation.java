package shared;

import java.util.regex.Pattern;

public class Validation {

    /**
     * Returns true if a given name matches the acceptable regular expression. The name must consist of a string of
     * letters, followed by an optional hyphen or space, then another string of letters.
     * @param name A name that should be checked to be valid.
     * @return True if the given name is valid within the system.
     */
    public static boolean isValidName(String name) {
        var namePattern = Pattern.compile("^[a-zA-Z]+([- ])?[a-zA-Z]+$");
        return !name.isBlank() && namePattern.matcher(name).matches();
    }
}
