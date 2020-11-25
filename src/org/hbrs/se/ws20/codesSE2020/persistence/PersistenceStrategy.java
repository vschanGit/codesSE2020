package org.hbrs.se.ws20.codesSE2020.persistence;

import org.hbrs.se.ws20.codesSE2020.PersistenceException;

import java.util.List;

/**
 * Interface for defining basic methods for a persistence mechanism
 * Each concrete algorithm (i.e. strategy) must implement this method
 * This interface corresponds to the abstract strategy w.r.t. to the
 * Strategy Design Pattern (GoF).
 *
 * @param <E>
 */
public interface PersistenceStrategy<E> {
    void openConnection() throws PersistenceException;

    void closeConnection() throws PersistenceException;

    void save(List<E> member) throws PersistenceException;

    List<E> load() throws PersistenceException;
}
