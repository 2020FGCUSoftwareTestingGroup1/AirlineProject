package shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsTest {

    @Test
    @DisplayName("Returns true for a name that doesn't have a space or hyphen")
    void returnsTrueForANameWithNoHyphenOrSpace() {
        assertTrue(Validation.isValidName("John"));
    }

    @Test
    @DisplayName("Returns false for a name that doesn't have letters.")
    void returnsFalseForNoLetters() {
        assertFalse(Validation.isValidName(""));
        assertFalse(Validation.isValidName("1"));
        assertFalse(Validation.isValidName("!"));
    }

    @Test
    @DisplayName("Returns false for names with letters and numbers.")
    void returnsFalseForLettersAndNumbers() {
        assertFalse(Validation.isValidName("John1"));
        assertFalse(Validation.isValidName("1John"));
        assertFalse(Validation.isValidName("John 1"));
        assertFalse(Validation.isValidName("John Doe1"));
        assertFalse(Validation.isValidName("1John Doe"));
    }

    @Test
    @DisplayName("Returns true for a name with a hyphen or space in the middle.")
    void returnsTrueForNameWithHyphenOrSpace() {
        assertTrue(Validation.isValidName("John Doe"));
        assertTrue(Validation.isValidName("John-Doe"));
    }
}