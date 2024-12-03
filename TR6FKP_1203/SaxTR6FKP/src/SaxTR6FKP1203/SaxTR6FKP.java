package SaxTR6FKP1203;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SaxTR6FKP {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SaxHandler handler = new SaxHandler();
            saxParser.parse("TR6FKP_1203/SaxTR6FKP/TR6FKP_kurzusfelvetel.xml", handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class SaxHandler extends DefaultHandler {
        private int indent = 0;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            indent();
            System.out.print(qName + " start");
            attributes(attributes);
            System.out.print("\n");
            indent++;
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            indent--;
            indent();
            System.out.print(qName + " end\n");
        }

        @Override
        public void characters(char ch[], int start, int length) {
            String s = new String(ch, start, length).trim();
            if (!s.isEmpty()) {
                indent();
                System.out.print(s + "\n");
            }
        }

        private void indent() {
            for (int i = 0; i < indent; i++) {
                System.out.print("  ");
            }
        }

        private void attributes(Attributes attributes) {
            if (attributes != null) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    System.out.print(" " + attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\"");
                }
            }
        }
    }
}
