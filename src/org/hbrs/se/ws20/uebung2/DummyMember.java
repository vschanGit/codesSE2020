package org.hbrs.se.ws20.uebung2;

public class DummyMember implements Member{
    public int ID;

    public DummyMember(int id){
        this.ID = id;
    }

    public Integer getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Member (ID = [" + ID + "])";
    }
}
