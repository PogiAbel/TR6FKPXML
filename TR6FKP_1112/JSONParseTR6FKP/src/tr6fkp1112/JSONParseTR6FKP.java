package tr6fkp1112;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONParseTR6FKP {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("./orarend.json"));

            JSONObject root = (JSONObject) jsonObject.get("PA_orarend");
            JSONArray pa = (JSONArray) root.get("ora");

            System.out.println("Órarend: Programtervező Informatikus 2024");

            for (int i = 0; i<pa.size(); i++) {
                System.out.println();
                JSONObject lesson = (JSONObject) pa.get(i);
                JSONObject time = (JSONObject) lesson.get("idopont");
                System.out.println("Tantárgy: " + lesson.get("targy"));
                System.out.println("Nap: " + time.get("nap"));
                System.out.println("Tól: " + time.get("tol"));
                System.out.println("Ig: " + time.get("ig"));
                System.out.println("Helyszín: " + lesson.get("helyszin"));
                System.out.println("Oktató: " + lesson.get("oktato"));
                System.out.println("Szak: " + lesson.get("szak"));
            }
            
        } catch (Exception e) {
        }
    }
}
