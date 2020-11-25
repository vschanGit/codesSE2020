package org.hbrs.se.ws20.codesSE2020;

public class Client {
    public static void main(String[] args) throws ContainerException {
        DummyMember testMember1 = new DummyMember(1);
        DummyMember testMember2 = new DummyMember(2);
        DummyMember testMember3 = new DummyMember(3);

        Container container = Container.getInstance();

        container.addMember(testMember1);
        container.addMember(testMember2);
        container.addMember(testMember3);

        MemberView.dump(container.getCurrentList());
    }
}
