package com.grandstrand.contact;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void ctorAndGetters_Valid() {
        Contact c = new Contact("ID1","John","Doe","1234567890","123 Main St");
        assertEquals("ID1", c.getContactId());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test void invalidId_nullOrTooLong() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact(null,"A","B","1234567890","addr"));
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("12345678901","A","B","1234567890","addr"));
    }

    @Test void invalidNamesLengths() {
        String eleven = "ABCDEFGHIJK";
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID","A",eleven,"1234567890","addr"));
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID",eleven,"A","1234567890","addr"));
    }

    @Test void invalidPhone() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID","A","B",null,"addr"));
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID","A","B","123", "addr"));
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID","A","B","123456789A", "addr"));
    }

    @Test void invalidAddress() {
        String longAddr = "x".repeat(31);
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID","A","B","1234567890", longAddr));
    }

    @Test void settersValidate() {
        Contact c = new Contact("ID","A","B","1234567890","addr");
        c.setFirstName("First");
        c.setLastName("Last");
        c.setPhone("0987654321");
        c.setAddress("New Address");
        assertEquals("First", c.getFirstName());
        assertEquals("Last", c.getLastName());
        assertEquals("0987654321", c.getPhone());
        assertEquals("New Address", c.getAddress());
    }
}

