package contactservice;

/**
 * Author: Raynaldo Young
 * Course: CS-320 Software Test Automation & QA
 * Assignment: Module Three Milestone – Contact Service
 *
 * Purpose:
 * Represents a single contact record and enforces all field-level
 * validation rules required by the project specifications.
 *
 * Key Design Notes:
 * - contactId is immutable to preserve identity consistency.
 * - All validation is handled inside this class.
 * - Update methods re-apply validation to prevent invalid state changes.
 */


/*
 * Marked final to prevent inheritance and protect validation rules
 * and identity consistency of Contact objects.
 */
public final class Contact {

    // Centralized constants define all validation limits in one place.
    // This avoids magic numbers scattered throughout the class.
    private static final int MAX_ID_LEN = 10;
    private static final int MAX_NAME_LEN = 10;
    private static final int MAX_ADDRESS_LEN = 30;
    private static final int PHONE_LEN = 10;

    // contactId uniquely identifies the contact.
    // Marked final so it cannot be changed after construction.
    private final String contactId;

    // Mutable fields that are allowed to change through setters.
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /**
     * Constructs a Contact object in a guaranteed valid state.
     *
     * All inputs are validated before assignment so that an invalid
     * Contact instance can never exist in memory.
     */
    public Contact(String contactId, String firstName, String lastName,
                   String phone, String address) {

        // Validate and assign the immutable contact ID.
        this.contactId = requireNonNullAndMaxLen("contactId", contactId, MAX_ID_LEN);

        // Validate and assign required name fields.
        this.firstName = requireNonNullAndMaxLen("firstName", firstName, MAX_NAME_LEN);
        this.lastName = requireNonNullAndMaxLen("lastName", lastName, MAX_NAME_LEN);

        // Validate and assign phone number using strict digit rules.
        this.phone = requirePhoneTenDigits(phone);

        // Validate and assign address field.
        this.address = requireNonNullAndMaxLen("address", address, MAX_ADDRESS_LEN);
    }

    // --------------------
    // Getter methods
    // --------------------

    // Returns the immutable contact ID.
    public String getContactId() {
        return contactId;
    }

    // Returns the current first name.
    public String getFirstName() {
        return firstName;
    }

    // Returns the current last name.
    public String getLastName() {
        return lastName;
    }

    // Returns the current phone number.
    public String getPhone() {
        return phone;
    }

    // Returns the current address.
    public String getAddress() {
        return address;
    }

    // --------------------
    // Update methods
    // --------------------

    /**
     * Updates the first name.
     * Validation is re-applied to ensure consistency after creation.
     */
    public void setFirstName(String firstName) {
        this.firstName = requireNonNullAndMaxLen("firstName", firstName, MAX_NAME_LEN);
    }

    /**
     * Updates the last name.
     * Validation mirrors constructor rules.
     */
    public void setLastName(String lastName) {
        this.lastName = requireNonNullAndMaxLen("lastName", lastName, MAX_NAME_LEN);
    }

    /**
     * Updates the phone number.
     * Must always remain exactly 10 digits.
     */
    public void setPhone(String phone) {
        this.phone = requirePhoneTenDigits(phone);
    }

    /**
     * Updates the address.
     * Length and null constraints are enforced again here.
     */
    public void setAddress(String address) {
        this.address = requireNonNullAndMaxLen("address", address, MAX_ADDRESS_LEN);
    }

    // --------------------
    // Validation helpers
    // --------------------

    /**
     * Validates that a required string field is not null and does not
     * exceed the specified maximum length.
     *
     * Centralizing this logic avoids duplicated checks and keeps
     * validation rules consistent across fields.
     */
    private static String requireNonNullAndMaxLen(String field,
                                                  String value,
                                                  int maxLen) {
        if (value == null) {
            throw new IllegalArgumentException(field + " cannot be null");
        }
        if (value.length() > maxLen) {
            throw new IllegalArgumentException(field + " must be <= " + maxLen + " characters");
        }
        return value;
    }

    /**
     * Validates that a phone number contains exactly 10 numeric digits.
     *
     * Explicit character checks are used instead of regex to keep
     * the logic readable and easy to debug.
     */
    private static String requirePhoneTenDigits(String value) {
        if (value == null) {
            throw new IllegalArgumentException("phone cannot be null");
        }
        if (value.length() != PHONE_LEN) {
            throw new IllegalArgumentException("phone must be exactly " + PHONE_LEN + " digits");
        }

        // Each character must be a digit between '0' and '9'.
        for (int i = 0; i < PHONE_LEN; i++) {
            char ch = value.charAt(i);
            if (ch < '0' || ch > '9') {
                throw new IllegalArgumentException("phone must contain digits only");
            }
        }
        return value;
    }
}
