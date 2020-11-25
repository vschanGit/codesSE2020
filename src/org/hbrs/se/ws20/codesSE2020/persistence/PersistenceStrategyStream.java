package org.hbrs.se.ws20.codesSE2020.persistence;

import org.hbrs.se.ws20.codesSE2020.PersistenceException;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {

    private ObjectInputStream objectInputStream;

    @Override
    public void openConnection() throws PersistenceException {
        try {
            File file = getFile();
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "IOException in PersistenceStrategyStream.openConnection");
        }
    }

    @Override
    public void closeConnection() throws PersistenceException {
        try {
            objectInputStream.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionClosingFailure, "IOException in PersistenceStrategyStream.closeConnection");
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(getFile()));
            objectOutputStream.writeObject(member);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.SaveFailure, "IOException in PersistenceStrategyStream.save");
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public List<Member> load() throws PersistenceException {
        try {
            Object input = objectInputStream.readObject();
            if (input instanceof List<?>) {
                return (List<Member>) input;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "IOException in PersistenceStrategyStream.load");
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "ClassNotFoundException in PersistenceStrategyStream.load");
        }

        return null;
    }

    private File getFile() {
        return new File("container.dat");
    }
}
