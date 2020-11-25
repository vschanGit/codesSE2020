package org.hbrs.se.ws20.codesSE2020;

import org.hbrs.se.ws20.codesSE2020.persistence.PersistenceStrategyMongoDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContainerTest {

    private static final DummyMember testMember1 = new DummyMember(1);
    private static final DummyMember testMember2 = new DummyMember(2);
    private static final DummyMember testMember3 = new DummyMember(3);

    @BeforeEach
    void refresh() {
        Container.refresh();
    }

    @Test
    void addMember() throws ContainerException {
        Container container = Container.getInstance();

        assertEquals(0, container.size(), "ERROR: expected Container size: [0], actual Container size: [" + container.size() + "]");

        container.addMember(testMember1);
        assertEquals(1, container.size(), "ERROR: expected Container size: [1], actual Container size: [" + container.size() + "]");

        container.addMember(testMember2);
        assertEquals(2, container.size(), "ERROR: expected Container size: [2], actual Container size: [" + container.size() + "]");

        container.addMember(testMember3);
        assertEquals(3, container.size(), "ERROR: expected Container size: [3], actual Container size: [" + container.size() + "]");
    }

    @Test
    void addMemberThrowsContainerExceptionWhenAddingSameMember() throws ContainerException {
        Container container = Container.getInstance();

        container.addMember(testMember1);
        assertThrows(ContainerException.class, () -> container.addMember(testMember1));
    }

    @Test
    void deleteMember() throws ContainerException {
        prepareMembersInContainer();

        Container container = Container.getInstance();
        assertEquals(3, container.size(), "ERROR: expected Container size: [3], actual Container size: [" + container.size() + "]");

        container.deleteMember(2);
        assertEquals(2, container.size(), "ERROR: expected Container size: [2], actual Container size: [" + container.size() + "]");

        container.deleteMember(1);
        assertEquals(1, container.size(), "ERROR: expected Container size: [1], actual Container size: [" + container.size() + "]");
    }

    @Test
    void deleteMemberReturnsErrorWhenMemberNotFound() throws ContainerException {
        prepareMembersInContainer();

        Container container = Container.getInstance();
        container.deleteMember(3);

        String result = container.deleteMember(3);
        assertEquals("Fehler! Member: " + testMember3.getID() + " nicht vorhanden!", result);
    }

    @Test
    void deleteMemberWithEmptyList() {
        Container container = Container.getInstance();

        String result = container.deleteMember(3);
        assertEquals("Fehler! Member: " + testMember3.getID() + " nicht vorhanden!", result);
    }

    @Test
    void storeAndLoad() throws ContainerException, PersistenceException {
        prepareMembersInContainer();

        // store members
        Container container = Container.getInstance();
        container.store();

        // clear list
        Container.refresh();

        // load from filesystem
        container.load();
        assertEquals(3, container.size(), "ERROR: expected Container size: [3], actual Container size: [" + container.size() + "]");
    }

    @Test
    void storeFailsWithNoPersistenceStrategy() throws ContainerException {
        prepareMembersInContainer();

        Container container = Container.getInstance();
        container.setPersistenceStrategy(null);

        assertThrows(ContainerException.class, container::store);
    }

    @Test
    void storeFailsWithMongoDBStrategy() throws ContainerException {
        prepareMembersInContainer();

        Container container = Container.getInstance();
        container.setPersistenceStrategy(new PersistenceStrategyMongoDB<>());

        assertThrows(UnsupportedOperationException.class, container::store);
    }

    @Test
    void loadFailsWithNoPersistenceStrategy() throws ContainerException {
        prepareMembersInContainer();

        Container container = Container.getInstance();
        container.setPersistenceStrategy(null);

        assertThrows(ContainerException.class, container::load);
    }

    @Test
    void loadFailsWithMongoDBStrategy() throws ContainerException {
        prepareMembersInContainer();

        Container container = Container.getInstance();
        container.setPersistenceStrategy(new PersistenceStrategyMongoDB<>());

        assertThrows(UnsupportedOperationException.class, container::load);
    }

    @Test
    void getCurrentList() throws ContainerException {
        Container container = Container.getInstance();
        container.addMember(testMember1);

        List<Member> result = container.getCurrentList();
        assertEquals(Collections.singletonList(testMember1), result);
    }

    private void prepareMembersInContainer() throws ContainerException {
        Container container = Container.getInstance();
        container.addMember(testMember1);
        container.addMember(testMember2);
        container.addMember(testMember3);
    }
}