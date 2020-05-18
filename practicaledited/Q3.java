import java.util.ArrayList;

enum NatureOfContact {
    CASUAL,
    CLOSE,
    FAMILY
}

interface ICase {

}

interface IContact extends ICase {

}

class Case implements ICase {
    private int id;
    private ArrayList<IContact> contacts;
}

class ImportedCase extends Case {
    private String countryImportedFrom;
}

class Contact implements IContact {
    private NatureOfContact natureOfContact;
}
class Cluster {
    private String name;
    private ArrayList<ICase> cases;
}
