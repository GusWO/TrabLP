package br.com.acme.model;

public class Article extends Publication {

    private int idArticle;
    private int FK_idUser;
    private String author;
    private String doi;
    private String journal;

    public Article(String title, short year, String doi) {

        super(title, year);
        this.doi = doi;

    }
    
    public Article(int idArticle, int FK_idUser, String title, short year, String author, String doi, String journal){
        super(title, year);
        
        this.idArticle = idArticle;
        this.FK_idUser = FK_idUser;
        this.doi = doi;
        this.journal = journal;
        
        setAuthor(author);
    }
    
    public Article(String title, short year, String author, String doi, String journal){
        super(title, year);
        this.author = author;
        this.doi = doi;
        this.journal = journal;
        
        setAuthor(author);
    }

    public Article() {}
  

    public int getIdArticle() {
        return idArticle;
    }


    public int getFK_idUser() {
        return FK_idUser;
    }

    public void setFK_idUser(int FK_idUser) {
        this.FK_idUser = FK_idUser;
    }

    

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    
   
    
}
