import java.util.ArrayList;

enum NatureOfContact {
    CASUAL,
    CLOSE,
    FAMILY
}

interface IContact {

}

class Case {
    private int id;
    private ArrayList<IContact> contacts;
}

class ImportedCase extends Case {
    private String countryImportedFrom;
}

class Contact implements IContact {
    private Case case1;
    private Case case2;
    private NatureOfContact natureOfContact;
}

class Cluster {
    private String name;
    private ArrayList<Case> listOfCases;
}
