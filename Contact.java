package com.grandstrand.contact;

import java.util.Objects;

public final class Contact {

    private final String contactId; // required, unique, <= 10, not updatable
    private String firstName;       // required, <= 10
    private String lastName;        // required, <= 10
    private String phone;           // required, exactly 10 digits
    private String address;         // required, <= 30

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        this.contactId = validateId(contactId);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    private static String validateId(String id) {
        if (id == null || id.length() > 10) {
            throw new IllegalArgumentException("contactId null or length > 10");
        }
        return id;
    }

    private static String requireMaxLen(String v, int max, String field) {
        if (v == null || v.length() > max) {
            throw new IllegalArgumentException(field + " null or length > " + max);
        }
        return v;
    }

    private static String requirePhone10(String p) {
        if (p == null || p.length() != 10 || !p.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("phone must be exactly 10 digits");
        }
        return p;
    }

    public String getContactId() { return contactId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public void setFirstName(String firstName) { this.firstName = requireMaxLen(firstName, 10, "firstName"); }
    public void setLastName(String lastName)   { this.lastName = requireMaxLen(lastName, 10, "lastName"); }
    public void setPhone(String phone)         { this.phone = requirePhone10(phone); }
    public void setAddress(String address)     { this.address = requireMaxLen(address, 30, "address"); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        return contactId.equals(((Contact)o).contactId);
    }
    @Override public int hashCode() { return Objects.hash(contactId); }
}
