package hu.domparse.tr6fkp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryTR6FKP {
    
        public static void main(String []args) throws ParserConfigurationException, SAXException, IOException, Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse("./XMLTaskTR6FKP/DOMParseTR6FKP/XMLTR6FKP.xml");

        query1(doc);
        System.out.println();
        query2(doc);
        System.out.println();
        query3(doc, "t3");
        System.out.println();
        query4(doc, "i1");
    }

    // 4 query

    public static void query1(Document doc) {
        // Iskolák nevei és az igazgatói

        NodeList iskolak = doc.getElementsByTagName("iskola");

        for (int i = 0; i < iskolak.getLength(); i++) {
            Node iskola = iskolak.item(i);

            if (iskola.getNodeType() == Node.ELEMENT_NODE) {
                Element iskolaElem = (Element) iskola;

                String iskolaNev = iskolaElem.getElementsByTagName("nev").item(0).getTextContent();
                String iskolaId = iskolaElem.getAttribute("iskola_id");
                Node igazgato = matchId(doc, "igazgato", "iskola_fid", iskolaId);
                String igazgatoNev = getFullName(igazgato);

                System.out.println("Iskola neve: " + iskolaNev + ", igazgató: " + igazgatoNev);
            }
        }
    }

    public static void query2(Document doc) {
        // Egy tanár neve és az általa tanított tantárgyak
        NodeList tanarok = doc.getElementsByTagName("tanar");
        for (int i = 0; i < tanarok.getLength(); i++) {
            Node tanar = tanarok.item(i);
            Element element = (Element) tanar;

            NodeList tantargyak = element.getElementsByTagName("szak");
            String[] szakok = new String[tantargyak.getLength()];

            for (int j = 0; j < tantargyak.getLength(); j++) {
                szakok[j] = tantargyak.item(j).getTextContent();
            }

            System.out.println("Tanár neve: " + getFullName(tanar) + ", tantárgyak: " + String.join(", ", szakok));
        }
    }

    public static void query3(Document doc, String teacherId) {
        // Egy tanárhoz tartozó diákok nevei
        Node tanar = matchId(doc, "tanar", "tanar_id", teacherId);
        Element tanarElem = (Element) tanar;
        if(tanar == null) {
            System.out.println("Nincs ilyen tanár");
            return;
        }

        List<Node> diakok = matchIdArray(doc, "tanulo", "tanar_fid", tanarElem.getAttribute("tanar_id"));
        System.out.println("Tanár neve: " + getFullName(tanar));
        for (Node diak : diakok) {
            System.out.println("Diák neve: " + getFullName(diak));
        }
        
    }

    public static void query4(Document doc, String schoolId) {
        // Egy iskolában lévő összes ember neve és foglalkozása
        Node iskola = matchId(doc, "iskola", "iskola_id", schoolId);
        if(iskola == null) {
            System.out.println("Nincs ilyen iskola");
            return;
        }

        Node igazgato = matchId(doc, "igazgato", "iskola_fid", schoolId);
        List<Node> tanarok = getTeachers(doc, schoolId);
        List<Node> diakok = matchIdArray(doc, "tanulo", "iskola_fid", schoolId);
        List<Node> dolgozok = matchIdArray(doc, "dolgozo", "iskola_fid", schoolId);

        System.out.println("Iskola neve: " + ((Element) iskola).getElementsByTagName("nev").item(0).getTextContent());
        System.out.println("Igazgató neve: " + getFullName(igazgato));
        printList(tanarok, "Tanár neve: ");
        printList(diakok, "Diák neve: ");
        printList(dolgozok, "Dolgozó neve: ");
    }

    // Segédfüggvények

    // Azonosító alapján keresés
    public static Node matchId(Document doc,String tagName, String idName, String id){
        NodeList nodes = doc.getElementsByTagName(tagName);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                if (elem.getAttribute(idName).equals(id)) {
                    return node;
                }
            }
        }
        return null;
    }

    // Azonosító alapján lista keresés
    public static List<Node> matchIdArray(Document doc,String tagName, String idName, String id){
        NodeList nodes = doc.getElementsByTagName(tagName);
        List<Node> resultList = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                if (elem.getAttribute(idName).equals(id)) {
                    resultList.add(node);
                }
            }
        }
        return resultList;
    }

    // Egy iskolában tanító tanárok lekérdezése
    public static List<Node> getTeachers(Document doc, String schoolId) {
        List<Node> oktat = matchIdArray(doc, "oktat", "iskola_fid", schoolId);
        List<Node> tanarok = new ArrayList<>();
        for (Node node : oktat) {
            tanarok.add(matchId(doc, "tanar", "tanar_id", ((Element) node).getAttribute("tanar_fid")));
        }

        return tanarok;
    }

    // Teljes név lekérdezése
    public static String getFullName(Node node) {
        Element elem = (Element) node;
        return elem.getElementsByTagName("vezeteknev").item(0).getTextContent() + " " + elem.getElementsByTagName("keresztnev").item(0).getTextContent();
    }

    // Lista kiíratása
    public static void printList(List<Node> nodes, String title) {
        for (Node node : nodes) {
            System.out.println(title + getFullName(node));
        }
    }

}
