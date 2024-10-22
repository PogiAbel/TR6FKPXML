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


public class DomRead1TR6FKP
{
    public static void main(String []args) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File("./TR6FKP_1001/TR6FKP_orarend.xml"));
        Element root = doc.getDocumentElement();
        
        System.out.println("Gyökér elem: " + root.getNodeName() + "\n");

        NodeList childNodes = root.getElementsByTagName("ora");

        for (int i = 0; i < childNodes.getLength(); i++)
        {
            Node nNode = childNodes.item(i);
            System.out.println("Aktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String id = element.getAttribute("id");
                String tipus = element.getAttribute("tipus");


                Node node1 = element.getElementsByTagName("targy").item(0);
                String targy = node1.getTextContent();

                Node node2 = element.getElementsByTagName("idopont").item(0);
                Element idopont = (Element) node2;
                Node idopont1 = idopont.getElementsByTagName("nap").item(0);
                String nap = idopont1.getTextContent();
                Node idopont2 = idopont.getElementsByTagName("tol").item(0);
                String tol = idopont2.getTextContent();
                Node idopont3 = idopont.getElementsByTagName("ig").item(0);
                String ig = idopont3.getTextContent();

                Node node3 = element.getElementsByTagName("helyszin").item(0);
                String helyszin = node3.getTextContent();

                Node node4 = element.getElementsByTagName("oktato").item(0);
                String oktato = node4.getTextContent();

                Node node5 = element.getElementsByTagName("szak").item(0);
                String szak = node5.getTextContent();

                System.out.println("Ora ID: " + id);
                System.out.println("Ora tipus: " + tipus);
                System.out.println("Targy: " + targy);
                System.out.println("Idopont Nap: " + nap);
                System.out.println("Idopont Tol: " + tol);
                System.out.println("Idopont Ig: " + ig);
                System.out.println("Helyszin: " + helyszin);
                System.out.println("Oktato: " + oktato);
                System.out.println("Szak: " + szak + "\n");
            }
        }
    }
};