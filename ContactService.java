package com.grandstrand.contact;

import java.util.*;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    public Contact add(Contact c) {
        Objects.requireNonNull(c, "contact");
        String id = c.getContactId();
        if (contacts.containsKey(id)) throw new IllegalArgumentException("duplicate contactId");
        contacts.put(id, c);
        return c;
    }

    public boolean delete(String contactId) {
        return contacts.remove(contactId) != null;
    }

    public Optional<Contact> get(String contactId) {
        return Optional.ofNullable(contacts.get(contactId));
    }

    // Updatable fields
    public void updateFirstName(String id, String first) { getRequired(id).setFirstName(first); }
    public void updateLastName(String id, String last)   { getRequired(id).setLastName(last); }
    public void updatePhone(String id, String phone)     { getRequired(id).setPhone(phone); }
    public void updateAddress(String id, String address) { getRequired(id).setAddress(address); }

    private Contact getRequired(String id) {
        Contact c = contacts.get(id);
        if (c == null) throw new NoSuchElementException("contact not found: " + id);
        return c;
    }

    // for testing/coverage
    public int size() { return contacts.size(); }
}

