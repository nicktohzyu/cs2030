import java.util.*;

class Case{

    Set<Contact> contacts = new HashSet<Contact>();

    int id;

    Case(int id){

        this.id = id;

    }

}

class ImportedCase extends Case{

    String countryOfOrigin;

    ImportedCase(int id, String countryOfOrigin){

        super(id);

        this.countryOfOrigin = countryOfOrigin;

    }

}

class LocalCase extends Case{

}

class Contact extends Case{

    enum ContactType { CASUAL, CLOSE, FAMILY}

    ContactType contactType;

}

class Cluster{

    Set<Case> cases= new HashSet<Case>();

    String clusterName;

    Cluster(String clusterName){

        this.clusterName = clusterName;

    }

    void addCase(Case c){

        cases.add(c);

    }

}