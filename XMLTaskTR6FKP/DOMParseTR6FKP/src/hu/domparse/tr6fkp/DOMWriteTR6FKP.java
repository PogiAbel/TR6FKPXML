package hu.domparse.tr6fkp;

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


public class DOMWriteTR6FKP
{
    public static void main(String []args) throws ParserConfigurationException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();
        doc.setXmlStandalone(true);
         
        Element root = doc.createElementNS(null, "TR6FKP_Iskolak");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xsi:noNamespaceSchemaLocation", "XMLSchemaTR6FKP.xsd");

        doc.appendChild(root);

        root.appendChild(createSchool(doc, "i1", "111", "Minta Iskola 1", "Minta cím 1"));
        root.appendChild(createSchool(doc, "i2", "222", "Minta Iskola 2", "Minta cím 2"));
        root.appendChild(createSchool(doc, "i3", "333", "Minta Iskola 3", "Minta cím 3"));
        
        root.appendChild(createWorker(doc, "d1", "i1", "Minta", "János", "10000", "Mosogató"));
        root.appendChild(createWorker(doc, "d2", "i2", "Minta", "Péter", "12000", "Szakács"));
        root.appendChild(createWorker(doc, "d3", "i3", "Minta", "Ákos", "14000", "Gondnok"));

        root.appendChild(createPrincipal(doc, "i1", "i1", "Gazdag", "István", "180000",createArray("+36/70 123-1234","+36/70 123-1234")));
        root.appendChild(createPrincipal(doc, "i2", "i2", "Gazdag", "Kornél", "200000",createArray("+36/70 111-1234","+36/70 112-1234")));
        root.appendChild(createPrincipal(doc, "i3", "i3", "Gazdag", "Sándor", "280000",createArray("+36/70 003-1234","+36/70 023-1234","+36/70 000-1234")));

        root.appendChild(createTeacher(doc, "t1", "Szegény", "Éva", "50000", createArray("+36/20 001-0002"), "0", createArray("Kémia","Angol")));
        root.appendChild(createTeacher(doc, "t2", "Szegény", "Irén", "45000", createArray("+36/20 111-0002"), "30", createArray("Magyar","Matek")));
        root.appendChild(createTeacher(doc, "t3", "Szegény", "Mária", "30000", createArray("+36/20 001-1122"), "60", createArray("Tesi")));

        root.appendChild(createOktat(doc, "i1", "t1", createArray()));
        root.appendChild(createOktat(doc, "i2", "t2", createArray("10c")));
        root.appendChild(createOktat(doc, "i1", "t3", createArray("11c")));
        root.appendChild(createOktat(doc, "i2", "t3", createArray("9a")));

        root.appendChild(createStudent(doc, "t1", "i1", "t3", "Sima", "Áron", "10c"));
        root.appendChild(createStudent(doc, "t2", "i2", "t3", "Sima", "Anna", "11c"));
        root.appendChild(createStudent(doc, "t3", "i3", "t2", "Sima", "Eszter", "9a"));
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transf.setOutputProperty(OutputKeys.STANDALONE, "yes");

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult("./XMLTR6FKP1.xml");

        transf.transform(source, console);
        transf.transform(source, file);
    }

    private static Node createSchool(Document doc, String iskola_id, String tanulok_szama, String nev, String cim){
        Element iskola = doc.createElement("iskola");

        iskola.setAttribute("iskola_id", iskola_id);
        iskola.setAttribute("tanulok_szama", tanulok_szama);

        iskola.appendChild(createTextElement(doc, "nev", nev));
        iskola.appendChild(createTextElement(doc, "cim", cim));

        return iskola;
    }

    private static Node createWorker(Document doc, String dolgozo_id, String iskola_fid, String vnev, String knev, String fizetes, String beosztas){
        Element dolgozo = doc.createElement("dolgozo");

        dolgozo.setAttribute("dolgozo_id", dolgozo_id);
        dolgozo.setAttribute("iskola_fid", iskola_fid);

        dolgozo.appendChild(createNameElement(doc, vnev, knev));
        dolgozo.appendChild(createTextElement(doc, "fizetes", fizetes));
        dolgozo.appendChild(createTextElement(doc, "beosztas", beosztas));

        return dolgozo;
    }

    private static Node createPrincipal(Document doc, String igazgato_id, String iskola_fid, String vnev, String knev, String fizetes, String[] telefonszamok){
        Element igazgato = doc.createElement("igazgato");

        igazgato.setAttribute("igazgato_id", igazgato_id);
        igazgato.setAttribute("iskola_fid", iskola_fid);

        igazgato.appendChild(createNameElement(doc, vnev, knev));
        igazgato.appendChild(createTextElement(doc, "fizetes", fizetes));

        appendArray(doc, igazgato, "telefonszam", telefonszamok);

        return igazgato;
    }

    private static Node createTeacher(Document doc, String tanar_id, String vnev, String knev, String fizetes, String[] telefonszamok, String diakok_szama, String[] szakok){
        Element tanar = doc.createElement("tanar");

        tanar.setAttribute("tanar_id", tanar_id);

        tanar.appendChild(createNameElement(doc, vnev, knev));
        tanar.appendChild(createTextElement(doc, "fizetes", fizetes));
        appendArray(doc, tanar, "telefonszam", telefonszamok);
        tanar.appendChild(createTextElement(doc, "diakok_szama", diakok_szama));
        appendArray(doc, tanar, "szak", szakok);

        return tanar;
    }

    private static Node createOktat(Document doc, String iskola_fid, String tanar_fid, String[] osztalyok){
        Element oktat = doc.createElement("oktat");

        oktat.setAttribute("iskola_fid", iskola_fid);
        oktat.setAttribute("tanar_fid", tanar_fid);

        appendArray(doc, oktat, "osztaly", osztalyok);

        return oktat;
    }

    private static Node createStudent(Document doc, String tanulo_id, String iskola_fid, String tanar_fid, String vnev, String knev, String osztaly){
        Element tanulo = doc.createElement("tanulo");

        tanulo.setAttribute("tanulo_id", tanulo_id);
        tanulo.setAttribute("iskola_fid", iskola_fid);
        tanulo.setAttribute("tanar_fid", tanar_fid);

        tanulo.appendChild(createNameElement(doc, vnev, knev));
        tanulo.appendChild(createTextElement(doc, "osztaly", osztaly));

        return tanulo;
    }

    private static void appendArray(Document doc, Element parent,String name, String[] array){
        for (String element : array) {
            parent.appendChild(createTextElement(doc, name, element));
        }
    }

    private static Node createNameElement(Document doc, String vnev, String knev){
        Element nev = doc.createElement("nev");
        nev.appendChild(createTextElement(doc, "vezeteknev", vnev));
        nev.appendChild(createTextElement(doc, "keresztnev", knev));

        return nev;
    }

    private static Node createTextElement(Document doc, String name, String value){
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private static String[] createArray(String... args){
        return args;
    }
};
