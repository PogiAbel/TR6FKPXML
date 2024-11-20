package hu.domparse.tr6fkp;

import java.io.File;
import java.io.FileWriter;
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
        
        
        NodeList iskolak = root.getElementsByTagName("iskola");
        NodeList dolgozok = root.getElementsByTagName("dolgozo");
        NodeList igazgatok = root.getElementsByTagName("igazgato");
        NodeList tanarok = root.getElementsByTagName("tanar");
        NodeList oktatasok = root.getElementsByTagName("oktat");
        NodeList tanulok = root.getElementsByTagName("tanulo");

        String out = "";

        for (int i = 0; i < iskolak.getLength(); i++)
        {
            Node nNode = iskolak.item(i);
                        out += "Aktuális elem: " + nNode.getNodeName() + "\n";

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String id = element.getAttribute("iskola_id");
                String tanulokSzama = element.getAttribute("tanulok_szama");

                Node node1 = element.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();
                Node node2 = element.getElementsByTagName("cim").item(0);
                String cim = node2.getTextContent();

                                                                                
                out += "Iskola ID: " + id + "\n";
                out += "Tanulók száma: " + tanulokSzama + "\n";
                out += "Név: " + nev + "\n";
                out += "Cím: " + cim + "\n\n";
            }
        }

        for (int i = 0; i < dolgozok.getLength(); i++)
        {
            Node nNode = dolgozok.item(i);
                        out += "Aktuális elem: " + nNode.getNodeName() + "\n";

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

                                                                                
                out += "Dolgozo ID: " + id + "\n";
                out += "Iskola idegen kulcs: " + fid + "\n";
                out += nev + "\n";
                out += "Fizetés: " + fizetes + "\n";
                out += "Beosztás: " + beosztas + "\n\n";
            }
        }

        for (int i = 0; i < igazgatok.getLength(); i++)
        {
            Node nNode = igazgatok.item(i);
            
            out += "Aktuális elem: " + nNode.getNodeName() + "\n";

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

                                                                                                
                out += "Igazgató ID: " + id + "\n";
                out += "Iskola idegen kulcs: " + fid + "\n";
                out += nev + "\n";
                out += "Fizetés: " + fizetes + "\n";
                out += telefonszamok + "\n";
            }
        }

        for (int i = 0; i < tanarok.getLength(); i++)
        {
            Node nNode = tanarok.item(i);
            
            out += "Aktuális elem: " + nNode.getNodeName() + "\n";

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

                                                                                                                
                out += "Igazgató ID: " + id + "\n";
                out += nev + "\n";
                out += "Fizetés: " + fizetes + "\n";
                out += telefonszamok;
                out += "Diákok száma: " + diakok_szama + "\n";
                out += szakok + "\n";
            }
        }

        for (int i = 0; i < oktatasok.getLength(); i++) {
            Node nNode = oktatasok.item(i);
            
            out += "Aktuális elem: " + nNode.getNodeName() + "\n";

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String id = element.getAttribute("iskola_fid");
                String fid = element.getAttribute("tanar_fid");
                NodeList osztalyok = element.getElementsByTagName("osztaly");

                                                                
                out += "Iskola idegen kulcs: " + id + "\n";
                out += "Tanár idegen kulcs: " + fid + "\n";
                out += getListString(osztalyok, "Osztály: ") + "\n";
            }
        }

        for (int i = 0; i < tanulok.getLength(); i++) {
            Node nNode = tanulok.item(i);
            
            out += "Aktuális elem: " + nNode.getNodeName() + "\n";

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String tanuloId = element.getAttribute("tanulo_id");
                String iskolaFid = element.getAttribute("iskola_fid");
                String tanarFid = element.getAttribute("tanar_fid");

                Node node1 = element.getElementsByTagName("nev").item(0);
                String nev = getName(node1);
                Node node2 = element.getElementsByTagName("osztaly").item(0);
                String osztaly = node2.getTextContent();

                                                                                                
                out += "Tanuló ID: " + tanuloId + "\n";
                out += "Iskola idegen kulcs: " + iskolaFid + "\n";
                out += "Tanár idegen kulcs: " + tanarFid + "\n";
                out += nev + "\n";
                out += "Osztály: " + osztaly + "\n\n";
            }
        }

        out = out.substring(0, out.length()-2);
        System.out.println(out);

        File file = new File("./XMLTaskTR6FKP/DOMParseTR6FKP/outputTR6FKP.txt");
        FileWriter writer = new FileWriter(file);
        writer.write(out);
        writer.close();
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
