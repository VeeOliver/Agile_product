package se.hkr.app;

public class User {
    private static User single_instance = null;

    public String Personnummer;
    public String email;
    public String Name;

    private User(String Personnummer, String email, String Name) {
            this.Personnummer = Personnummer;
            this.email = email;
            this.Name = Name;
    }

    public static User getInstance(String Personnummer, String email, String Name)
    {
        if (single_instance == null)
            single_instance = new User(Personnummer ,email, Name);

        return single_instance;
    }

    public static User getInstance() {
        return single_instance;
    }

    public String getPersonnummer() {
        return Personnummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPersonnummer(String personnummer) {
        this.Personnummer = personnummer;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public static void resetUserInstance() {
        User.single_instance = null;
    }

}