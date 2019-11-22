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
            //Search product description if brand name not found.
            if (brand == null) {
                searchDescription();
                return;
            }

            //Find matching product name within the brand
				productsArr = (JSONArray) productList.get(brand);
				
				for(int i = 0; i < AppFrame.prodTitleArr.length; i++) {
					for(int j = 0; j < productsArr.size(); j++) {
						if(productsArr.get(j).equals(AppFrame.prodTitleArr[i]))
						{
							product = AppFrame.prodTitleArr[i];
							System.out.println("Product Name is " + product);
						}
					}
				}
				
				//Search product description if product not found.
				if(product == null) {
					searchDescription();
					return;
				}
				
				JSONObject modelList = (JSONObject) jsonObj.get("Models");
				JSONArray modelArr = (JSONArray) modelList.get(product);
				
				for(int i = 0; i < AppFrame.prodTitleArr.length; i++) {
					for (int j = 0; j < modelArr.size(); j++) {
						if(modelArr.get(j).equals(AppFrame.prodTitleArr[i])) {
							model = AppFrame.prodTitleArr[i];
							System.out.println("Model is "+ model);
						}
					}
				}
				
				//Search product description if model not found.
				if (model == null) {
					searchDescription();
					return;
				}
				
                suggestTitle(brand, product, model);
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void searchDescription() {
        //Searches product description.
    }
    
    //Display the final output
	static void suggestTitle(String brand, String product, String model) {
        JOptionPane.showMessageDialog(
                null, 
                "The parsed title is: " + brand + " " + product + " " + model
                );
    }
 }
