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

    public String getPersonnummer(){
        return this.Personnummer;
    }
}