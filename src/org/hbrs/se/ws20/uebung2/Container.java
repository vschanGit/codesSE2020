package org.hbrs.se.ws20.uebung2;
import java.util.ArrayList;
import java.util.Arrays;

public class Container {
    private ArrayList<Member> container;

    public Container(){
        container = new ArrayList<>();
    }

    public Container(Member[] memberArray){
        this.container = new ArrayList<>(Arrays.asList(memberArray));
    }

    public void addMember (Member member) throws ContainerException{
        if (container.contains(member))throw new ContainerException("Das Member-Objekt mit der ID [" + member.getID() + "] ist bereits vorhanden!");
        container.add(member);
    }

    public String deleteMember(Integer id){
        for(Member i: container){
            if (i.getID().equals(id)){
                container.remove(i);
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

    public void dump(){
        for(Member i: container){
            System.out.println(i.toString());
        }
    }

    public int size(){
        return container.size();
    }
}
