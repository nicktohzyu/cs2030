import java.util.*;

class Case {
    int id;

    Case(int id) {
        this.id = id;
    }
}

class ImportedCase extends Case {
    String countryOfOrigin;

    ImportedCase(int id, String countryOfOrigin) {
        super(id);
        this.countryOfOrigin = countryOfOrigin;
    }
}

class LocalCase extends Case {
    LocalCase(int id) {
        super(id);
    }
}

enum ContactType {CASUAL, CLOSE, FAMILY}

class Contact {
    ContactType contactType;
    Case firstCase, secondCase;

    Contact(Case firstCase, Case secondCase, ContactType contactType) {
        this.firstCase = firstCase;
        this.secondCase = secondCase;
        this.contactType = contactType;
    }
}

class Cluster {
    Set<Case> cases = new HashSet<Case>();
    String clusterName;

    Cluster(String clusterName) {
        this.clusterName = clusterName;
    }

    void addCase(Case c) {
        cases.add(c);
    }
}
