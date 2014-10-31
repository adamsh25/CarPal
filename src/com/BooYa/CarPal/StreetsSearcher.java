package com.BooYa.CarPal;

import android.util.JsonReader;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Rony on 31/10/2014.
 */
public class StreetsSearcher {
    public static ArrayList<Street> GetStreets(InputStream file) {
        ArrayList<Street> result = new ArrayList<Street>();
        try {
            JsonFactory f = new MappingJsonFactory();
            JsonParser jp = f.createParser(file);

            JsonToken current;
            current = jp.nextToken();

            if (current != JsonToken.START_ARRAY) {
                System.out.println("Error: root should be array: quiting.");
                return null;
            }

            while (jp.nextToken() != JsonToken.END_ARRAY) {
                current = jp.nextToken();

                if (current != JsonToken.FIELD_NAME) {
                    return null;
                }

                Street curr = new Street();

                while (current != JsonToken.END_OBJECT) {

                    String fieldName = jp.getCurrentName();
                    // move from field name to field value
                    current = jp.nextToken();

                    if (fieldName == "house_num") {
                        curr.setHouse_num(jp.readValueAs(Integer.class));
                    } else if (fieldName == "street_code") {
                        curr.setStreet_code(jp.readValueAs(Integer.class));
                    } else if (fieldName == "street_name") {
                        curr.setStreet_name(jp.readValueAs(String.class));
                    } else {
                        curr.setStreet_name_eng(jp.readValueAs(String.class));
                    }

                    current = jp.nextToken();
                }

                result.add(curr);
            }

        } catch (IOException e) {
        }
        finally {
            return result;
        }
    }
}
