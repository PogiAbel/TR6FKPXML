package tr6fkp1112;

import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



@SuppressWarnings("unchecked")
public class JSONWriteTR6FKP {
    public static void main(String[] args) {
        JSONObject root = new JSONObject();
        JSONArray pa = new JSONArray();
        
        pa.add(createLesson("e1", "eloadas", "Adatkezelés XML-ben", "Kedd", "10", "12", "Inf/103", "Dr. Bednarik László", "Programtervező Informatikus"));
        pa.add(createLesson("g1", "gyakorlat", "Adatkezelés XML-ben", "Kedd", "12", "13", "Inf/103", "Dr. Bednarik László", "Programtervező Informatikus"));
        pa.add(createLesson("g2", "gyakorlat", "Mesterséges intelligencia alapok", "Szerda", "8", "10", "E10", "Fazekas Levente Áron", "Programtervező Informatikus"));
        pa.add(createLesson("e2", "eloadas", "Mesterséges intelligencia alapok", "Szerda", "16", "18", "E32", "Kunné Dr. Tamás Judit", "Programtervező Informatikus"));

        root.put("PA_orarend", pa);

        for (int i = 0; i<pa.size(); i++) {
            printObject((JSONObject)pa.get(i));
        }

        try {
            FileWriter writer = new FileWriter("./TR6FKP_1112/JSONParseTR6FKP/orarend1.json");
            writer.write(indentJson(root.toJSONString()));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static JSONObject createLesson(String id, String type, String subject, String day, String from, String to, String location, String teacher, String major) {
        JSONObject lesson = new JSONObject();
        JSONObject time = new JSONObject();
        time.put("nap", day);
        time.put("tol", from);
        time.put("ig", to);
        lesson.put("id", id);
        lesson.put("tipus", type);
        lesson.put("targy", subject);
        lesson.put("idopont", time);
        lesson.put("helyszin", location);
        lesson.put("oktato", teacher);
        lesson.put("szak", major);
        return lesson;
    }

    static void printObject(JSONObject object){
        System.out.println();
        JSONObject lesson = (JSONObject) object;
        JSONObject time = (JSONObject) lesson.get("idopont");
        System.out.println("Tantárgy: " + lesson.get("targy"));
        System.out.println("Nap: " + time.get("nap"));
        System.out.println("Tól: " + time.get("tol"));
        System.out.println("Ig: " + time.get("ig"));
        System.out.println("Helyszín: " + lesson.get("helyszin"));
        System.out.println("Oktató: " + lesson.get("oktato"));
        System.out.println("Szak: " + lesson.get("szak"));
    }

    static String indentJson(String json){
        String out = "";
        int indent = 0;
        for (int i = 0; i < json.length()-1; i++) {
            out += json.charAt(i);
            if (json.charAt(i) == ',') {
                out += "\n" + " ".repeat(indent>0? indent : 0);
            } else if (json.charAt(i) == '{' || json.charAt(i) == '[') {
                indent++;
                out += "\n" + " ".repeat(indent>0? indent : 0);
            } else if (json.charAt(i) == '}' || json.charAt(i) == ']') {
                indent--;
                if( json.charAt(i+1) != ',') out += "\n" + " ".repeat(indent>0? indent:0); 
            }
        }

        out +=json.charAt(json.length()-1);
        return out;
    }
}