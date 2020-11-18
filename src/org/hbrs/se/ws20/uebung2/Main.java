package org.hbrs.se.ws20.uebung2;

public class Main {
    public static void main (String[] args){
        DummyMember testMember1 = new DummyMember(1);
        DummyMember testMember2 = new DummyMember(2);
        DummyMember testMember3 = new DummyMember(3);
        Container testContainer2 = new Container(new DummyMember[]{testMember1, testMember2, testMember3});
        testContainer2.dump();
    }
}
