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

<<<<<<< HEAD
    public String getPersonnummer(){
        return this.Personnummer;
=======

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
        Personnummer = personnummer;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
>>>>>>> a8539cd249c99ca1cc4e361672bdea4f1f5fa7d9
    }
}