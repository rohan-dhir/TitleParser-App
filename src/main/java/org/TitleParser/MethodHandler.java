package org.TitleParser;

/*
 * All primary methods for searching, parsing and outputting final result
 */

import javax.swing.JOptionPane;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

 public class MethodHandler {

    static JSONParser parser;
	
    //Format of title follows {brand, product, model}
	static String brand, product, model;
		
	MethodHandler() {
		parser = new JSONParser();
	}

    public static void searchDatabase() {
        try {
            //Specify the location of the JSON File
            Object obj = parser.parse(new FileReader(
                    "GitHub/TitleParser-App/data.json"));
            
            JSONObject jsonObj = (JSONObject) obj;
            
            JSONObject productList = (JSONObject) jsonObj.get("Products");
            JSONArray productsArr = (JSONArray) productList.get("");
            
            //Match brand name in title with JSON object
            for(int i = 0; i < AppFrame.prodTitleArr.length; i++)  {
                for(int j = 0; j < productList.size(); j++) {
                    productsArr = (JSONArray) productList.get(AppFrame.prodTitleArr[i]);
                    if(productsArr != null) {
                        brand = AppFrame.prodTitleArr[i];
                        System.out.println("Brand Name is " + brand);
                        break;
                    }
                }
            }
            //If a brand match was not detected, search product description instead
            if (brand == null) {
                searchDescription();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void searchDescription() {
        //Searches product description.
    }
 }
