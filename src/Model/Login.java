package Model;

public class Login {
    private String username;
    private int password;

    public Login() {
        //
    }

    public Login(String name, int password) {
        this.username = name;
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
