package hu.domparse.tr6fkp;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyTR6FKP {

    public static void main(String []args) throws ParserConfigurationException, SAXException, IOException, Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse("./XMLTaskTR6FKP/DOMParseTR6FKP/XMLTR6FKP.xml");

        task1(doc, "d1");
        task2(doc, "t1", "i2", createArray("9.A","9.B"));
        task3(doc, "dolgozo", "d1", "200000");
        task4(doc, "igazgato", "i2", "+36/20 111-1111");
    }

    public static void task1(Document doc, String workerId)throws Exception{
        // Dolgozók áthelyezése más iskolába
        Node dolgozo = matchId(doc, "dolgozo", "dolgozo_id", workerId);
        Element dolgozoElem = (Element) dolgozo;
        dolgozoElem.setAttribute("iskola_fid", "i2");
        
        printXML(doc);
    }

    public static void task2(Document doc, String teacherId, String schoolId, String... classString) throws Exception{
        // Tanár hozzáadása iskolához
        Node oktat = doc.createElement("oktat");
        Element oktatElem = (Element) oktat;

        oktatElem.setAttribute("iskola_fid", schoolId);
        oktatElem.setAttribute("tanar_fid", teacherId);

        for(String classStr : classString){
            appendChild(doc,oktat,"osztaly",classStr);
        }

        Node firstOktat = doc.getElementsByTagName("oktat").item(0);

        doc.getDocumentElement().insertBefore(oktat, firstOktat);

        printXML(doc);
    }

    public static void task3(Document doc, String tag, String id, String newValue)throws Exception{
        // Fizetés módosítása

        Node dolgozo = matchId(doc, tag, tag+"_id", id);
        if(dolgozo == null){
            System.out.println("Nincs ilyen azonosítójú dolgozó!");
            return;
        }
        changeTextValue(dolgozo,"fizetes",newValue);

        printXML(doc);
    }
    public static void task4(Document doc,String tag, String id, String number)throws Exception{
        // Telefonszám hozzáadása
        Node dolgozo = matchId(doc, tag, tag+"_id", id);
        if(dolgozo == null){
            System.out.println("Nincs ilyen azonosítójú dolgozó!");
            return;
        }        
        Node telefonszam = doc.createElement("telefonszam");
        telefonszam.setTextContent(number);
        
        NodeList childs = dolgozo.getChildNodes();
        boolean insert = false;
        for (int i = 0; i < childs.getLength(); i++) {
            Node child = childs.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                if (insert) {
                    dolgozo.insertBefore(telefonszam, child);
                    break;
                }
                Element elem = (Element) child;
                if (elem.getTagName().equals("fizetes")) {
                    insert = true;
                }
            }
        }

        printXML(doc);
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

    public static void changeTextValue(Node node, String tag, String value){
        Element element = (Element) node;
        element.getElementsByTagName(tag).item(0).setTextContent(value);
    }

    public static void appendChild(Document doc,Node node, String tag, String value){
        Node child = doc.createElement(tag);
        child.setTextContent(value);
        node.appendChild(child);
    }
    
    public static void printXML(Document doc) throws Exception{
        doc.setXmlStandalone(true);
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", 2);

        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("./XMLTaskTR6FKP/DOMParseTR6FKP/pretty.xsl")));
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.console().writer());

        transformer.transform(source, console);
    }
    
    private static String[] createArray(String... args){
        return args;
    }
}
