package domtr6fkp1105;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryTR6FKP {

    public static void main(String []args) throws ParserConfigurationException, SAXException, IOException, Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse("./hallgato.xml");

        NodeList hallgatok = doc.getElementsByTagName("hallgato");

        for(int i = 0; i < hallgatok.getLength(); i++){
            System.out.println("Aktuális elem:\nhallgato");
            Node hallgato = hallgatok.item(i);
            Element element = (Element) hallgato;
            System.out.println(element.getElementsByTagName("vezeteknev").item(0).getTextContent() + "\n");
        }
    }
    
}
