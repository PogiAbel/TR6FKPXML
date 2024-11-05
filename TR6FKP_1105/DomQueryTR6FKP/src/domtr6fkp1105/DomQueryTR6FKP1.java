package domtr6fkp1105;

import java.io.IOException;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

        taskA(doc);

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
    
}
