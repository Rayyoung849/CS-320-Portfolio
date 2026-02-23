package contactservice;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Raynaldo Young
 * Course: CS-320 Software Test Automation & QA
 * Assignment: Module Three Milestone – Contact Service
 *
 * Purpose:
 * Stores and manages Contact objects in memory (no database).
 * Provides add, delete, and update operations using contactId as the key.
 *
 * Key Design Notes:
 * - Uniqueness is enforced at the service layer by rejecting duplicate IDs.
 * - Field validation is delegated to Contact setters to avoid duplicated rules.
 * - Missing IDs are treated as errors to make defects visible in unit tests.
 */


/*
 * Not final to allow future extension of service behavior
 * without modifying core contact logic.
 */
public class ContactService {

    // In-memory storage keyed by contactId for fast lookup.
    private final Map<String, Contact> contacts = new HashMap<>();

    /**
     * Adds a valid Contact to the service.
     * This method enforces service-level rules: non-null input and unique ID.
     */
    public void addContact(Contact contact) {

        // Reject a null reference to avoid NullPointerExceptions later.
        if (contact == null) {
            throw new IllegalArgumentException("contact cannot be null");
        }

        // Extract the ID used as the map key.
        String contactId = contact.getContactId();

        // Reject duplicates so an existing record cannot be overwritten.
        if (contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("contactId must be unique");
        }

        // Store the contact using its ID as the key.
        contacts.put(contactId, contact);
    }

    /**
     * Deletes a contact by its contactId.
     * If the ID does not exist, the method throws to avoid silent failures.
     */
    public void deleteContact(String contactId) {

        // The ID is required to perform a delete operation.
        if (contactId == null) {
            throw new IllegalArgumentException("contactId cannot be null");
        }

        // remove returns null when the key is not present.
        Contact removed = contacts.remove(contactId);

        // Treat unknown IDs as errors so tests can catch incorrect calls.
        if (removed == null) {
            throw new IllegalArgumentException("contactId not found");
        }
    }

    /**
     * Updates firstName for a specific contact ID.
     * Validation happens inside Contact.setFirstName().
     */
    public void updateFirstName(String contactId, String firstName) {

        // Resolve the contact or fail if the ID is unknown.
        Contact contact = getRequiredContact(contactId);

        // Delegate validation and assignment to the Contact model.
        contact.setFirstName(firstName);
    }

    /**
     * Updates lastName for a specific contact ID.
     * Validation happens inside Contact.setLastName().
     */
    public void updateLastName(String contactId, String lastName) {

        // Resolve the contact or fail if the ID is unknown.
        Contact contact = getRequiredContact(contactId);

        // Delegate validation and assignment to the Contact model.
        contact.setLastName(lastName);
    }

    /**
     * Updates phone (Number) for a specific contact ID.
     * Validation happens inside Contact.setPhone().
     */
    public void updateNumber(String contactId, String phone) {

        // Resolve the contact or fail if the ID is unknown.
        Contact contact = getRequiredContact(contactId);

        // Delegate validation and assignment to the Contact model.
        contact.setPhone(phone);
    }

    /**
     * Updates address for a specific contact ID.
     * Validation happens inside Contact.setAddress().
     */
    public void updateAddress(String contactId, String address) {

        // Resolve the contact or fail if the ID is unknown.
        Contact contact = getRequiredContact(contactId);

        // Delegate validation and assignment to the Contact model.
        contact.setAddress(address);
    }

    /**
     * Locates a Contact by ID and guarantees it exists.
     * Centralizing this logic keeps error behavior consistent across operations.
     */
    private Contact getRequiredContact(String contactId) {

        // Null IDs are rejected early to keep error causes obvious.
        if (contactId == null) {
            throw new IllegalArgumentException("contactId cannot be null");
        }

        // Lookup by key is O(1) on average with HashMap.
        Contact contact = contacts.get(contactId);

        // Reject unknown IDs to prevent updates from silently doing nothing.
        if (contact == null) {
            throw new IllegalArgumentException("contactId not found");
        }

        // Return the existing contact for update operations.
        return contact;
    }

    /**
     * Returns the number of stored contacts.
     * This is used by unit tests to verify add and delete behavior.
     */
    public int size() {
        return contacts.size();
    }
}

