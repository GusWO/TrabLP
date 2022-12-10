package br.com.acme.model;

public class User {

    private int idUser;
    private String login;
    private String password;
    private String email;
    private int publications_limit;

    public User() {}
    
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    
    public User(int id, String login, String password, String email) {
        this.idUser = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }
    
    public User(String login, String password, String email, int publications_limit) {
        this(login, password, email);
        this.publications_limit = publications_limit;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }   
    
    public int getIdUser() {
        return idUser;
    }
    
    public int getPublications_limit() {
        return publications_limit;
    }

    public void setPublications_limit(int publications_limit) {
        this.publications_limit = publications_limit;
    }
}
