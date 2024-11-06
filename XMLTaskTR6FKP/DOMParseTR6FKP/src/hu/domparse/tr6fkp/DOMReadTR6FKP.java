package hu.domparse.tr6fkp;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMReadTR6FKP {
    
    public static void main(String []args) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File("./XMLTaskTR6FKP/DOMParseTR6FKP/XMLTR6FKP.xml"));
        Element root = doc.getDocumentElement();
        
        System.out.println("Gyökér elem: " + root.getNodeName() + "\n");

        NodeList iskolak = root.getElementsByTagName("iskola");
        NodeList dolgozok = root.getElementsByTagName("dolgozo");
        NodeList igazgatok = root.getElementsByTagName("igazgato");
        NodeList tanarok = root.getElementsByTagName("tanar");
        NodeList oktatasok = root.getElementsByTagName("oktat");
        NodeList tanulok = root.getElementsByTagName("tanulo");

        for (int i = 0; i < iskolak.getLength(); i++)
        {
            Node nNode = iskolak.item(i);
            System.out.println("Aktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String id = element.getAttribute("iskola_id");
                String tanulokSzama = element.getAttribute("tanulok_szama");

                Node node1 = element.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();
                Node node2 = element.getElementsByTagName("cim").item(0);
                String cim = node2.getTextContent();

                System.out.println("Iskola ID: " + id);
                System.out.println("Tanulók száma: " + tanulokSzama);
                System.out.println("Név: " + nev);
                System.out.println("Cím: " + cim + "\n");
            }
        }

        for (int i = 0; i < dolgozok.getLength(); i++)
        {
            Node nNode = dolgozok.item(i);
            System.out.println("Aktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String id = element.getAttribute("dolgozo_id");
                String fid = element.getAttribute("iskola_fid");

                Node node1 = element.getElementsByTagName("nev").item(0);
                String nev = getName(node1);
                Node node2 = element.getElementsByTagName("fizetes").item(0);
                String fizetes = node2.getTextContent();
                Node node3 = element.getElementsByTagName("beosztas").item(0);
                String beosztas = node3.getTextContent();

                System.out.println("Dolgozo ID: " + id);
                System.out.println("Iskola idegen kulcs: " + fid);
                System.out.println(nev);
                System.out.println("Fizetés: " + fizetes);
                System.out.println("Beosztás: " + beosztas + "\n");
            }
        }

        for (int i = 0; i < igazgatok.getLength(); i++)
        {
            Node nNode = igazgatok.item(i);
            System.out.println("Aktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String id = element.getAttribute("igazgato_id");
                String fid = element.getAttribute("iskola_fid");

                Node node1 = element.getElementsByTagName("nev").item(0);
                String nev = getName(node1);
                Node node2 = element.getElementsByTagName("fizetes").item(0);
                String fizetes = node2.getTextContent();
                NodeList node3 = element.getElementsByTagName("telefonszam");
                String telefonszamok = getListString(node3, "Telefonszám: ");

                System.out.println("Igazgató ID: " + id);
                System.out.println("Iskola idegen kulcs: " + fid);
                System.out.println(nev);
                System.out.println("Fizetés: " + fizetes);
                System.out.print(telefonszamok);
                System.out.print("\n");
            }
        }

        for (int i = 0; i < tanarok.getLength(); i++)
        {
            Node nNode = tanarok.item(i);
            System.out.println("Aktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String id = element.getAttribute("tanar_id");

                Node node1 = element.getElementsByTagName("nev").item(0);
                String nev = getName(node1);
                Node node2 = element.getElementsByTagName("fizetes").item(0);
                String fizetes = node2.getTextContent();
                NodeList node3 = element.getElementsByTagName("telefonszam");
                String telefonszamok = getListString(node3, "Telefonszám: ");
                Node node4 = element.getElementsByTagName("diakok_szama").item(0);
                String diakok_szama = node4.getTextContent();
                NodeList node5 = element.getElementsByTagName("szak");
                String szakok = getListString(node5, "Szak: ");

                System.out.println("Igazgató ID: " + id);
                System.out.println(nev);
                System.out.println("Fizetés: " + fizetes);
                System.out.print(telefonszamok);
                System.out.println("Diákok száma: " + diakok_szama);
                System.out.print(szakok);
                System.out.print("\n");
            }
        }

        for (int i = 0; i < oktatasok.getLength(); i++) {
            Node nNode = oktatasok.item(i);
            System.out.println("Aktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String id = element.getAttribute("iskola_fid");
                String fid = element.getAttribute("tanar_fid");

                Node node1 = element.getElementsByTagName("osztaly").item(0);
                String osztaly;
                try {
                    osztaly = "Osztály: " + node1.getTextContent() + "\n";
                    
                } catch (Exception e) {
                    osztaly = "";
                }

                System.out.println("Iskola idegen kulcs: " + id);
                System.out.println("Tanár idegen kulcs: " + fid);
                System.out.print(osztaly);
                System.out.print("\n");
            }
        }

        for (int i = 0; i < tanulok.getLength(); i++) {
            Node nNode = tanulok.item(i);
            System.out.println("Aktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String tanuloId = element.getAttribute("tanulo_id");
                String iskolaFid = element.getAttribute("iskola_fid");
                String tanarFid = element.getAttribute("tanar_fid");

                Node node1 = element.getElementsByTagName("nev").item(0);
                String nev = getName(node1);
                Node node2 = element.getElementsByTagName("osztaly").item(0);
                String osztaly = node2.getTextContent();

                System.out.println("Tanuló ID: " + tanuloId);
                System.out.println("Iskola idegen kulcs: " + iskolaFid);
                System.out.println("Tanár idegen kulcs: " + tanarFid);
                System.out.println(nev);
                System.out.println("Osztály: " + osztaly);
                System.out.print("\n");
            }
        }
    }

    static String getName(Node nevNode){
        Element nev = (Element) nevNode;
        Node node1 = nev.getElementsByTagName("keresztnev").item(0);
        String keresztnev = node1.getTextContent();
        Node node2 = nev.getElementsByTagName("vezeteknev").item(0);
        String vezeteknev = node2.getTextContent();
        return "Név vezetéknév: " + vezeteknev + "\nNév keresztnév: " + keresztnev;
    }

    static String getListString(NodeList list, String name){
        String result = "";
        for (int i = 0; i < list.getLength(); i++){
            result += name + list.item(i).getTextContent() + "\n";
        }
        return result;
    }
}
