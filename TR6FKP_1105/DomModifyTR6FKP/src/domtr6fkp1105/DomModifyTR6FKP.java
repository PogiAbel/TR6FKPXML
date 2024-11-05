package domtr6fkp1105;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DomModifyTR6FKP
{
    public static void main(String []args) throws ParserConfigurationException, SAXException, IOException, Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse("./hallgato.xml");
        doc.normalize();

        Node hallgato = doc.getElementsByTagName("hallgato").item(0);

        changeTextValue(hallgato, "keresztnev", "Géza");
        changeTextValue(hallgato, "vezeteknev", "Magyar");

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);
        // StreamResult file = new StreamResult("./hallgato1.xml");

        transf.transform(source, console);
        // transf.transform(source, file);
    }

    public static void changeTextValue(Node node, String tag, String value){
        Element element = (Element) node;
        element.getElementsByTagName(tag).item(0).setTextContent(value);
    }
}