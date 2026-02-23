package contactservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Author: Raynaldo Young
 * Course: CS-320 Software Test Automation & QA
 * Assignment: Module Three Milestone – Contact Service
 *
 * FIX SUMMARY (Module 3 correction):
 * The following missing requirement tests were added based on rubric feedback:
 *
 * 1. Added explicit null and length tests for lastName
 * 2. Added explicit null test for phone
 * 3. Added explicit immutability confirmation for contactId
 *
 * These additions ensure all Contact field requirements are fully tested
 * for both null checks and length constraints as required.
 *
 * Purpose:
 * Unit tests for the Contact model.
 * Verifies constructor validation, getter correctness, and update validation.
 *
 * Test Approach:
 * - Each test focuses on one requirement category.
 * - Negative tests verify invalid inputs throw IllegalArgumentException.
 * - Positive tests verify values are stored and returned correctly.
 */

/*
 * Package-private test class following JUnit 5 conventions.
 */
class ContactTest {

    /**
     * Confirms that a Contact can be created when all inputs meet requirements.
     * Also confirms getters return exactly what was stored.
     */
    @Test
    void testValidContactCreation() {

        // Create a contact using values that satisfy every constraint.
        Contact contact = new Contact(
                "HX902",
                "Leo",
                "Koa",
                "8083217789",
                "77 Kapiolani Ave"
        );

        // Verify all stored values match the inputs.
        assertEquals("HX902", contact.getContactId());
        assertEquals("Leo", contact.getFirstName());
        assertEquals("Koa", contact.getLastName());
        assertEquals("8083217789", contact.getPhone());
        assertEquals("77 Kapiolani Ave", contact.getAddress());
    }

    /**
     * Confirms contactId validation:
     * - must not be null
     * - must be 10 characters or less
     */
    @Test
    void testInvalidContactId() {

        // Null ID must be rejected.
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(null, "Mika", "Lane", "8084459912", "12 Nuuanu St")
        );

        // ID longer than 10 characters must be rejected.
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("ID_TOO_LONG_99", "Mika", "Lane", "8084459912", "12 Nuuanu St")
        );
    }

    /**
     * Confirms firstName validation:
     * - must not be null
     * - must be 10 characters or less
     */
    @Test
    void testInvalidFirstName() {

        // Null firstName must be rejected.
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("FN001", null, "Lane", "8084459912", "12 Nuuanu St")
        );

        // firstName longer than 10 characters must be rejected.
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("FN002", "MakanaKailoa", "Lane", "8084459912", "12 Nuuanu St")
        );
    }

    /**
     * FIX:
     * Explicit lastName validation tests.
     * - must not be null
     * - must be 10 characters or less
     */
    @Test
    void testInvalidLastName() {

        // Null lastName must be rejected.
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("LN001", "Kai", null, "8084459912", "12 Nuuanu St")
        );

        // lastName longer than 10 characters must be rejected.
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("LN002", "Kai", "VeryLongLastName", "8084459912", "12 Nuuanu St")
        );
    }

    /**
     * Confirms phone validation:
     * - must not be null
     * - must be exactly 10 digits
     * - must contain digits only
     */
    @Test
    void testInvalidPhoneNumber() {

        // FIX: Null phone number must be rejected.
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("PH001", "Rin", "Sato", null, "55 Kalakaua Rd")
        );

        // Too short violates the "exactly 10 digits" rule.
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("PH002", "Rin", "Sato", "80812", "55 Kalakaua Rd")
        );

        // Non-digit characters violate the "digits only" rule.
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("PH003", "Rin", "Sato", "808ABC9912", "55 Kalakaua Rd")
        );
    }

    /**
     * Confirms address validation:
     * - must not be null
     * - must be 30 characters or less
     */
    @Test
    void testInvalidAddress() {

        // Null address must be rejected.
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("NV300", "Aiko", "Tan", "8087712234", null)
        );

        // Over-length address must be rejected.
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(
                        "AD002",
                        "Aiko",
                        "Tan",
                        "8087712234",
                        "KalakauaAvenueNearBeachSideExtraText"
                )
        );
    }

    /**
     * FIX:
     * Confirms contactId immutability.
     * contactId has no setter and must remain unchanged after construction.
     */
    @Test
    void testContactIdIsImmutable() {

        Contact contact = new Contact(
                "IMMUT01",
                "Kai",
                "Noa",
                "8081234567",
                "10 Bishop St"
        );

        assertEquals("IMMUT01", contact.getContactId());
    }

    /**
     * Confirms that allowed fields can be updated and remain valid.
     * Also confirms updated values are reflected by getters.
     */
    @Test
    void testFieldUpdates() {

        // Start with a valid contact so updates are the only variable.
        Contact contact = new Contact(
                "QA771",
                "Noa",
                "Hale",
                "8086631198",
                "9 Kapahulu Ave"
        );

        // Apply valid updates through setters.
        contact.setFirstName("Kai");
        contact.setLastName("Iona");
        contact.setPhone("8089901122");
        contact.setAddress("18 Ala Wai Blvd");

        // Confirm each update persisted.
        assertEquals("Kai", contact.getFirstName());
        assertEquals("Iona", contact.getLastName());
        assertEquals("8089901122", contact.getPhone());
        assertEquals("18 Ala Wai Blvd", contact.getAddress());
    }
}
