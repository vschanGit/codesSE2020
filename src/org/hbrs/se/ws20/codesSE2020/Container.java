package org.hbrs.se.ws20.codesSE2020;

import org.hbrs.se.ws20.codesSE2020.persistence.PersistenceStrategy;
import org.hbrs.se.ws20.codesSE2020.persistence.PersistenceStrategyStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Container implements Serializable {
    private List<Member> memberList;

    private static Container myContainer = new Container();
    private PersistenceStrategy<Member> persistenceStrategy;

    private Container() {
        memberList = new ArrayList<>();
        persistenceStrategy = new PersistenceStrategyStream<>();
    }

    public static Container getInstance() {
        return myContainer;
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy){
        this.persistenceStrategy = persistenceStrategy;
    }

    public static void refresh() {
        myContainer = new Container();
    }

    public void addMember(Member member) throws ContainerException {
        if (memberList.contains(member))
            throw new ContainerException("Das Member-Objekt mit der ID [" + member.getID() + "] ist bereits vorhanden!");
        memberList.add(member);
    }

    public String deleteMember(Integer id) {
        for (Member i : memberList) {
            if (i.getID().equals(id)) {
                memberList.remove(i);
                return "Member: " + id + " wurde erfolgreich entfernt!";
            }
        }
        return "Fehler! Member: " + id + " nicht vorhanden!";

        /*Der Fehler wird hier im normalen Anwendungscode behandelt
        und nicht in der Ausnahmebehandlung. Außerdem erfolgt die Fehlermeldung
        über den normalen System Output und nicht über den StackTrace, wodurch nicht
        unbedingt ersichtlich ist, wann und wo der Fehler ausgelöst wurde.
         */
    }

    public List<Member> getCurrentList(){
        return memberList;
    }

    public int size() {
        return memberList.size();
    }

    public void store() throws PersistenceException, ContainerException {
        if(persistenceStrategy == null){
            throw new ContainerException("PersistenceStrategy not set");
        }

        persistenceStrategy.save(memberList);
    }

    public void load() throws PersistenceException, ContainerException {
        if(persistenceStrategy == null){
            throw new ContainerException("PersistenceStrategy not set");
        }

        persistenceStrategy.openConnection();
        memberList = persistenceStrategy.load();
        persistenceStrategy.closeConnection();
    }
}
