package domtr6fkp1022;

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


public class DomReadTR6FKP
{
    public static void main(String []args) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("./TR6FKP_1022/hallgato.xml"));
        org.w3c.dom.Element root = doc.getDocumentElement();
        System.out.println("Gyökér elem: " + root.getNodeName() + "\n");

        NodeList childNodes = root.getElementsByTagName("hallgato");

        for (int i = 0; i < childNodes.getLength(); i++)
        {
            Node nNode = childNodes.item(i);
            System.out.println("Aktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String id = element.getAttribute("id");

                Node node1 = element.getElementsByTagName("vezeteknev").item(0);
                String vezeteknev = node1.getTextContent();
                Node node2 = element.getElementsByTagName("keresztnev").item(0);
                String keresztnev = node2.getTextContent();
                Node node3 = element.getElementsByTagName("foglalkozas").item(0);
                String foglalkozas = node3.getTextContent();

                System.out.println("Hallgato ID: " + id);
                System.out.println("Vezetéknév: " + vezeteknev);
                System.out.println("Keresztnév: " + keresztnev);
                System.out.println("Foglalkozás: " + foglalkozas + "\n");
            }
        }
    }
};