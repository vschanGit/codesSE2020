package org.hbrs.se.ws20.uebung2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    Container testContainer = new Container();
    DummyMember testMember1 = new DummyMember(1);
    DummyMember testMember2 = new DummyMember(2);
    DummyMember testMember3 = new DummyMember(3);
    Container testContainer2 = new Container(new DummyMember[]{testMember1, testMember2, testMember3});

    @BeforeEach
    void refreshContainer() {
        testContainer = new Container();
    }

    @Test
    void addMember() throws ContainerException {
        assertEquals(0, testContainer.size(), "ERROR: expected Container size: [0], actual Container size: [" + testContainer.size() + "]");
        testContainer.addMember(testMember1);
        assertEquals(1, testContainer.size(), "ERROR: expected Container size: [1], actual Container size: [" + testContainer.size() + "]");
        testContainer.addMember(testMember2);
        assertEquals(2, testContainer.size(), "ERROR: expected Container size: [2], actual Container size: [" + testContainer.size() + "]");
        testContainer.addMember(testMember3);
        assertEquals(3, testContainer.size(), "ERROR: expected Container size: [3], actual Container size: [" + testContainer.size() + "]");
        assertThrows(ContainerException.class,() -> testContainer.addMember(testMember1));
    }

    @Test
    void deleteMember() throws ContainerException {
        testContainer.addMember(testMember1);
        testContainer.addMember(testMember2);
        testContainer.addMember(testMember3);
        assertEquals(3, testContainer.size(),"ERROR: expected Container size: [3], actual Container size: [" + testContainer.size() + "]");
        testContainer.deleteMember(3);
        assertEquals(2, testContainer.size(),"ERROR: expected Container size: [2], actual Container size: [" + testContainer.size() + "]");
        assertEquals("Fehler! Member: " + testMember3.getID() + " nicht vorhanden!", testContainer.deleteMember(3));
        testContainer.deleteMember(2);
        assertEquals(1, testContainer.size(),"ERROR: expected Container size: [1], actual Container size: [" + testContainer.size() + "]");
        testContainer.deleteMember(1);
        assertEquals(0, testContainer.size(),"ERROR: expected Container size: [0], actual Container size: [" + testContainer.size() + "]");
    }

    @Test
    void size() {
        assertEquals(3, testContainer2.size());
    }
}