package domtr6fkp1105;

import java.io.IOException;
import java.util.Arrays;
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

public class DomQueryTR6FKP1 {

    public static void main(String []args) throws ParserConfigurationException, SAXException, IOException, Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse("./TR6FKP_orarend.xml");

        // taskA(doc);
        taskB(doc);
        // taskC(doc);

    }

    public static void taskA(Document doc) {
        NodeList kurzusok = doc.getElementsByTagName("ora");
        String[] kurzusokList = new String[kurzusok.getLength()];
        for(int i = 0; i < kurzusok.getLength(); i++){
            Node kurzus = kurzusok.item(i);
            Element element = (Element) kurzus;

            String kurzusNev = element.getElementsByTagName("targy").item(0).getTextContent();

            kurzusokList[i] = kurzusNev;
        }

        System.out.println(Arrays.toString(kurzusokList));
    }

    public static void taskB(Document doc) throws Exception {
        Node root = doc.getDocumentElement();
        NodeList childs = root.getChildNodes();

        Node first = root;

        for(int i = 0; i < childs.getLength(); i++){
            Node child = childs.item(i);
            if(child.getNodeType() == Node.ELEMENT_NODE){
                first = child;
                break;
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(first);

        StreamResult console = new StreamResult(System.out);
        // StreamResult file = new StreamResult("./orarendModifyTR6FKP.xml");

        transf.transform(source, console);
        // transf.transform(source, file);
    }

    public static void taskC(Document doc) {

        NodeList oktatok = doc.getElementsByTagName("oktato");
        String[] nevek = new String[oktatok.getLength()]; 
        for (int i = 0; i < oktatok.getLength(); i++) {
            nevek[i] = oktatok.item(i).getTextContent();
        }

        System.out.println(Arrays.toString(nevek));
    }
    
}
