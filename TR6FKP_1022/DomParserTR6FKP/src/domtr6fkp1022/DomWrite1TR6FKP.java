package domtr6fkp1022;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class DomWrite1TR6FKP
{
    public static void main(String []args) throws ParserConfigurationException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();
         
        Element root = doc.createElementNS("DOMTR6FKP", "orarend");

        doc.appendChild(root);

        root.appendChild(createUser(doc, "e1", "eloadas", "Adatkezelés XML-ben", "Kedd", "10", "12", "Inf/103", "Dr. Bednarik László", "Programtervező Informatikus"));
        root.appendChild(createUser(doc, "g1", "gyakorlat", "Adatkezelés XML-ben", "Kedd", "12", "14", "Inf/103", "Dr. Bednarik László", "Programtervező Informatikus"));
        root.appendChild(createUser(doc, "g2", "gyakorlat", "Mesterséges intelligencia alapok", "Szerda", "10", "12", "E10", "Fazekas Levente Áron", "Programtervező Informatikus"));
        root.appendChild(createUser(doc, "e2", "eloadas", "Mesterséges intelligencia alapok", "Szerda", "16", "18", "E32", "Kunné Dr. Tamás Judit", "Programtervező Informatikus"));
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult("./TR6FKP_orarend1.xml");

        transf.transform(source, console);
        transf.transform(source, file);
    }

    private static Node createUser(Document doc, String id,String tipus, String targy, String nap, String tol, String ig, String helyszin, String oktato, String szak){
        Element ora = doc.createElement("ora");
        Element idopont = doc.createElement("idopont");

        idopont.appendChild(createUserElement(doc, "nap", nap));
        idopont.appendChild(createUserElement(doc, "tol", tol));
        idopont.appendChild(createUserElement(doc, "ig", ig));

        ora.setAttribute("id", id);
        ora.setAttribute("tipus", tipus);
        ora.appendChild(createUserElement(doc, "targy", targy));
        ora.appendChild(idopont);
        ora.appendChild(createUserElement(doc, "helyszin", helyszin));
        ora.appendChild(createUserElement(doc, "oktato", oktato));
        ora.appendChild(createUserElement(doc, "szak", szak));

        return ora;
    }

    private static Node createUserElement(Document doc, String name, String value){
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
};