package org.hbrs.se.ws20.codesSE2020;

import java.util.List;

public class MemberView {

    public static void dump(List<Member> members) {
        for (Member i : members) {
            System.out.println(i.toString());
        }
    }
}
