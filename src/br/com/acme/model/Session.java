package br.com.acme.model;

public class Session {
    private static Session instance = null;
    private User loggedUser;
    
    private Session(User user){
        this.loggedUser = user;
    }
    
    public static Session getInstance() {
        return instance;
    }
    
    public static void startSession(User user) {
        instance = new Session(user);
    }
    
    public User getLoggedUser() {
        return loggedUser;
    }
}
