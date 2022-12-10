package br.edu.unijui.xml;

import br.com.acme.model.AcademicLibraryData;
import br.com.acme.model.Article;
import br.com.acme.model.Book;
import br.com.acme.model.User;
import br.com.acme.model.logic.LogController;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLCache {

    public static final XMLCache instance = new XMLCache();

    private AcademicLibraryData data;
    private Document doc;
    
    static final String CACHE_FILE_PATH = "./cache.xml";

    private XMLCache() {
        data = new AcademicLibraryData();
    }

    public void serialize() {
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = doc.createElement("AcademicLibrary");
            doc.appendChild(root);

            appendOfflineMode(root);
            appendArticles(root);
            appendBooks(root);

            ManipuladorXML.writeXmlFile(doc, "./cache.xml");
        } catch (Exception ex) {
            Logger.getLogger(XMLCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deserialize() {

        Document doc = loadXMLCacheOrNull();
        if (doc == null) return;

        ArrayList<Book> books = loadListFromCache(doc, Book.class);
        ArrayList<Article> articles = loadListFromCache(doc, Article.class);
        boolean isOfflineModeOn = loadOfflineModeFromCache(doc);
        
        data.books = books;
        data.articles = articles;
        data.isOfflineMode = isOfflineModeOn;
    }
    
    public AcademicLibraryData getCachedData() {
        return data.copy();
    }

    public void setOfflineMode(boolean isOfflineMode) {
        data.isOfflineMode = isOfflineMode;
    }

    public void setArticles(ArrayList<Article> articles) {
        data.articles = articles;
    }

    public void setBooks(ArrayList<Book> books) {
        data.books = books;
    }

    public void setUsers(ArrayList<User> users) {
        data.users = users;
    }

    private XMLCache appendOfflineMode(Element root) {
        Element offlineModeElement = doc.createElement("OfflineMode");
        offlineModeElement.setTextContent(data.isOfflineMode ? "true" : "false");
        root.appendChild(offlineModeElement);

        return instance;
    }

    private void appendAny(Element root, List toAppend, Class classOfObjectToAppend) {
        if (toAppend == null) {
            return;
        }

        Field[] fields = classOfObjectToAppend.getDeclaredFields();
        Field[] baseFields = classOfObjectToAppend.getSuperclass().getDeclaredFields();

        Field[] allFields = new Field[fields.length + baseFields.length];
        System.arraycopy(fields, 0, allFields, 0, fields.length);
        System.arraycopy(baseFields, 0, allFields, fields.length, baseFields.length);

        String className = classOfObjectToAppend.getSimpleName();
        Element articlesElement = doc.createElement("List");
        articlesElement.setAttribute("type", className);

        for (Object objToAppend : toAppend) {
            Element articleElement = doc.createElement(className);

            for (Field field : allFields) {
                field.setAccessible(true);
                try {
                    Object obj = field.get(objToAppend);
                    articleElement.setAttribute(field.getName(), String.valueOf(obj));
                } catch (Exception any) {
                    LogController.writeLog(String.format("Exception in {0}", (Object) any.getStackTrace()));
                }
            }

            articlesElement.appendChild(articleElement);
        }

        root.appendChild(articlesElement);
    }

    private void appendArticles(Element root) {
        appendAny(root, data.articles, Article.class);
    }

    private void appendBooks(Element root) {
        appendAny(root, data.books, Book.class);
    }

    private Document loadXMLCacheOrNull() {
        try {

            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            
            File cacheFile = new File(CACHE_FILE_PATH);
            return builder.parse(cacheFile);
            
        } catch (IOException | SAXException | ParserConfigurationException ex) {
            System.out.println(ex);
            return null;
        }
    }

    private ArrayList loadListFromCache(Document doc, Class typeOfList) {
        ArrayList elements = new ArrayList();

        System.out.println("nome da classe -> " + typeOfList.getSimpleName());

        Field[] fields = typeOfList.getDeclaredFields();
        Field[] baseFields = typeOfList.getSuperclass().getDeclaredFields();

        Field[] allFields = new Field[fields.length + baseFields.length];
        System.arraycopy(fields, 0, allFields, 0, fields.length);
        System.arraycopy(baseFields, 0, allFields, fields.length, baseFields.length);

        NodeList list = doc.getElementsByTagName(typeOfList.getSimpleName());

        Constructor typeConstructor = null;

        // TODO: só um trycatch
        try {
            typeConstructor = typeOfList.getConstructor();
        } catch (Exception ex) {
            Logger.getLogger(XMLCache.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < list.getLength(); ++i) {
            Node node = list.item(i);
            try {
                Object element = typeConstructor.newInstance();
                NamedNodeMap attributes = node.getAttributes();

                for (Field field : allFields) {
                    field.setAccessible(true);
                    Class fieldType = field.getType();

                    String nodeValue = attributes.getNamedItem(field.getName()).getNodeValue();
                    Object convertedTypedValue = castHelper(nodeValue, fieldType);

                    field.set(element, convertedTypedValue);
                }

               
               elements.add(element);
               
            } catch (Exception ex) {
                Logger.getLogger(XMLCache.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return elements;
    }

    private Object castHelper(String input, Class fieldClass) {

        if (fieldClass.isAssignableFrom(short.class)) {
            return Short.parseShort(input);
        }
        if (fieldClass.isAssignableFrom(int.class)) {
            return Integer.parseInt(input);
        }

        return input;
    }
    
    private boolean loadOfflineModeFromCache(Document doc) {
        NodeList elementsByTagName = doc.getElementsByTagName("OfflineMode");
        
        Node offlineModeNode = elementsByTagName.item(0);
        
        boolean isOfflineModeOn = Boolean.parseBoolean(offlineModeNode.getTextContent());
        
        return isOfflineModeOn;
    }
}
