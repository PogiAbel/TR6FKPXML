package tr6fkp1112;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;


public class JSONValidateTR6FKP {
    public static void main(String[] args) {
        try {
            String content = Files.readString(Paths.get("./TR6FKP_1112/JSONParseTR6FKP/orarendTR6FKP.json"));
            String rawSchemaString = Files.readString(Paths.get("./TR6FKP_1112/JSONParseTR6FKP/orarendTR6FKPSchema.json"));
            JSONObject jsonObject = new JSONObject(content);
            JSONObject rawSchema = new JSONObject(rawSchemaString);
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(jsonObject);
            System.out.println("JSON is valid");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}