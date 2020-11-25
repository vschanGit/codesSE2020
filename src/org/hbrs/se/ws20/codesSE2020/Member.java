package org.hbrs.se.ws20.codesSE2020;

import java.io.Serializable;

public interface Member extends Serializable {

    // ID ist über einen Konstruktor einer abgeleiteten Klasse
    // explizit außerhalb der Container-Klasse zu belegen
    // --> Primärschlüssel zur Unterscheidung aller Member-Objekte
    Integer getID();

    String toString();

}
