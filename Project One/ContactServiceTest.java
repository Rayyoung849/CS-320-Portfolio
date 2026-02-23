package contactservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Author: Raynaldo Young
 * Course: CS-320 Software Test Automation & QA
 * Assignment: Module Three Milestone – Contact Service
 *
 * Purpose:
 * Unit tests for ContactService.
 * Verifies add, delete, and update behavior using contactId as the reference key.
 *
 * Test Approach:
 * - Validate service-level rules such as unique IDs and missing ID handling.
 * - Avoid re-testing every field rule here since ContactTest covers model validation.
 */


/*
 * Package-private test class focused on service-level behavior verification.
 */
class ContactServiceTest {

    /**
     * Confirms a contact can be added and stored in memory.
     * Verification uses size() so the internal Map is not exposed.
     */
    @Test
    void testAddContact() {

        // Create a new service instance for an isolated test.
        ContactService service = new ContactService();

        // Build a valid contact.
        Contact contact = new Contact(
                "AZ901",
                "Leo",
                "Ika",
                "8084456621",
                "14 Ala Moana Blvd"
        );

        // Add the contact to the service.
        service.addContact(contact);

        // Confirm one record is stored.
        assertEquals(1, service.size());
    }

    /**
     * Confirms duplicate contact IDs are rejected.
     * This protects the uniqueness requirement and prevents overwriting.
     */
    @Test
    void testDuplicateContactId() {

        // Create a new service instance for an isolated test.
        ContactService service = new ContactService();

        // Add the first contact using a specific ID.
        Contact first = new Contact(
                "DD777",
                "Kai",
                "Noe",
                "8087712233",
                "55 Kapolei Pkwy"
        );

        // Build another contact that reuses the same ID.
        Contact duplicate = new Contact(
                "DD777",
                "Miko",
                "Lane",
                "8086611199",
                "88 Nimitz Hwy"
        );

        // Store the first contact.
        service.addContact(first);

        // The second add must fail because the ID is already in use.
        assertThrows(IllegalArgumentException.class, () ->
                service.addContact(duplicate)
        );

        // Confirm the original record is still the only stored record.
        assertEquals(1, service.size());
    }

    /**
     * Confirms a contact can be deleted by contactId.
     * After deletion, the service should no longer store the record.
     */
    @Test
    void testDeleteContact() {

        // Create a new service instance for an isolated test.
        ContactService service = new ContactService();

        // Add a contact that will be deleted.
        Contact contact = new Contact(
                "RM442",
                "Aiko",
                "Sora",
                "8083321144",
                "6 Kalihi St"
        );

        // Store the contact, then delete it by ID.
        service.addContact(contact);
        service.deleteContact("RM442");

        // Confirm storage is now empty.
        assertEquals(0, service.size());
    }

    /**
     * Confirms deleting an unknown contactId is treated as an error.
     * This prevents silent failures that hide incorrect IDs.
     */
    @Test
    void testDeleteMissingContact() {

        // Create a new service instance for an isolated test.
        ContactService service = new ContactService();

        // Attempt to delete an ID that was never stored.
        assertThrows(IllegalArgumentException.class, () ->
                service.deleteContact("NOPE999")
        );
    }

    /**
     * Confirms updates work by:
     * - locating the correct Contact by contactId
     * - delegating field validation to the Contact setters
     * - persisting the updated values in the same Contact object
     */
    @Test
    void testUpdateFields() {

        // Create a new service instance for an isolated test.
        ContactService service = new ContactService();

        // Add a known contact that will be updated.
        Contact contact = new Contact(
                "UP123",
                "Rin",
                "Koa",
                "8089987766",
                "21 King St"
        );

        // Store the contact so update calls have a valid target.
        service.addContact(contact);

        // Update each allowed field through the service.
        service.updateFirstName("UP123", "Maui");
        service.updateLastName("UP123", "Zen");
        service.updateNumber("UP123", "8081112233");
        service.updateAddress("UP123", "9 Ward Ave");

        // Verify the Contact object reflects all updates.
        assertEquals("Maui", contact.getFirstName());
        assertEquals("Zen", contact.getLastName());
        assertEquals("8081112233", contact.getPhone());
        assertEquals("9 Ward Ave", contact.getAddress());
    }

    /**
     * Confirms update operations fail when the contactId is not found.
     * This prevents accidental creation or silent no-op updates.
     */
    @Test
    void testUpdateMissingContact() {

        // Create a new service instance for an isolated test.
        ContactService service = new ContactService();

        // Attempt to update a record that does not exist.
        assertThrows(IllegalArgumentException.class, () ->
                service.updateFirstName("BAD999", "Test")
        );
    }
}
