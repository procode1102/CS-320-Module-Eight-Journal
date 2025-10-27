package com.grandstrand.contact;

import org.junit.jupiter.api.*;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {
    ContactService svc;

    @BeforeEach void setup(){ svc = new ContactService(); }

    @Test void addAndGetAndSize() {
        Contact c = new Contact("C1","A","B","1234567890","addr");
        svc.add(c);
        assertEquals(1, svc.size());
        assertTrue(svc.get("C1").isPresent());
    }

    @Test void addDuplicateFails() {
        Contact c1 = new Contact("C1","A","B","1234567890","addr");
        svc.add(c1);
        assertThrows(IllegalArgumentException.class, () -> svc.add(c1));
    }

    @Test void deleteById() {
        svc.add(new Contact("C1","A","B","1234567890","addr"));
        assertTrue(svc.delete("C1"));
        assertFalse(svc.delete("C1"));
        assertEquals(0, svc.size());
    }

    @Test void updateFields() {
        svc.add(new Contact("C1","A","B","1234567890","addr"));
        svc.updateFirstName("C1","First");
        svc.updateLastName("C1","Last");
        svc.updatePhone("C1","0123456789");
        svc.updateAddress("C1","New");
        Contact c = svc.get("C1").orElseThrow();
        assertEquals("First", c.getFirstName());
        assertEquals("Last", c.getLastName());
        assertEquals("0123456789", c.getPhone());
        assertEquals("New", c.getAddress());
    }

    @Test void updateMissingContactThrows() {
        assertThrows(NoSuchElementException.class, () -> svc.updateAddress("NOPE","X"));
    }
}

