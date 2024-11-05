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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyTR6FKP1 {
    public static void main(String []args) throws ParserConfigurationException, SAXException, IOException, Exception
    {
        taskA();
        taskB();
    }

    public static void taskA() throws ParserConfigurationException, SAXException, IOException, Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse("./TR6FKP_orarend.xml");
        doc.normalize();

        Node ora = doc.getElementsByTagName("ora").item(0);

        Node oraado = doc.createElement("oraado");
        oraado.setTextContent("Dr. Bednarik László");

        ora.appendChild(oraado);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult("./orarendModifyTR6FKP.xml");

        transf.transform(source, console);
        transf.transform(source, file);
    }

    public static void taskB() throws ParserConfigurationException, SAXException, IOException, Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse("./TR6FKP_orarend.xml");
        doc.normalize();

        NodeList oraList = doc.getElementsByTagName("ora");
        for (int i = 0; i < oraList.getLength(); i++){
            Node ora = oraList.item(i);
            Element element = (Element) ora;
            if (element.getAttribute("tipus").equals("gyakorlat")){
                element.setAttribute("tipus", "eloadas");
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);

        transf.transform(source, console);
    }

    public static void changeTextValue(Node node, String tag, String value){
        Element element = (Element) node;
        element.getElementsByTagName(tag).item(0).setTextContent(value);
    }
}
