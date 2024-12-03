package SaxTR6FKP1203;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.helpers.DefaultHandler;

public class XsdTR6FKP {
    public static void main(String[] args) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("TR6FKP_1203/SaxTR6FKP/TR6FKP_kurzusfelvetel.xsd"));

            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            saxFactory.setSchema(schema);
            SAXParser saxParser = saxFactory.newSAXParser();
            saxParser.parse("TR6FKP_1203/SaxTR6FKP/TR6FKP_kurzusfelvetel.xml", new SaxHandler());        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class SaxHandler extends DefaultHandler{
        private static Boolean valid = true;

        @Override
        public void error(org.xml.sax.SAXParseException e) {
            valid = false;
            // System.out.println("Error: " + e.getMessage());
        }

        @Override
        public void endDocument() {
            if (valid){
                System.out.println("XSD Validation successful!");
            } else {
                System.out.println("XSD Validation failed!");
            }
        }
    }
}
