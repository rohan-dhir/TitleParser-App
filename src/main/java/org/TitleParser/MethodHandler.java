package org.TitleParser;

/*
 * All primary methods for searching, parsing and outputting final result
 */

import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MethodHandler {

	static JSONParser parser;
	static Map <Integer, String> titleMap = new HashMap<>();

	//Format of title follows {brand, product, model}
	static String brand, product, model;

	MethodHandler() {
		parser = new JSONParser();
	}


	//Searches through JSON file for matching title
	public static void searchDatabase() {

		//Add title entered by user to HashMap
		for (int i = 0; i < AppFrame.prodTitleArr.length; i++) {
			titleMap.put(i, AppFrame.prodTitleArr[i]);
		}

			try {
				//Specify the location of the JSON File
				Object obj = parser.parse(new FileReader(
						"GitHub/TitleParser-App/data.json"));

				JSONObject jsonObj = (JSONObject) obj;

				JSONObject productList = (JSONObject) jsonObj.get("Products");
				JSONArray productsArr = (JSONArray) productList.get("");

				//Match brand name in title with JSON object
				brand = searchArrAgainstDatabase(productsArr, productList, "");


				//Find matching product name within the brand
				if(!brand.equals("")) {

					System.out.println("Brand Name is " + brand);

					productsArr = (JSONArray) productList.get(brand);
					product = searchArrAgainstDatabase(productsArr, productList, brand);
				}

				//Match model with product name
				 if(!product.equals("")) {
					System.out.println("Product Name is " + product);

					JSONObject modelList = (JSONObject) jsonObj.get("Models");
					JSONArray modelArr = (JSONArray) modelList.get(product);

					model = searchArrAgainstDatabase(modelArr, modelList, product);
				}

				 suggestTitle(brand, product, model);



			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	//Searches database for values matching the entered title
	static String searchArrAgainstDatabase(JSONArray arr, JSONObject listObj, String listName) {
		String val;
		arr = (JSONArray) listObj.get(listName);
		Object obj;

		//If the name of the list is currently unknown, find a match
		if (arr == null) {
			for (int i = 0; i < titleMap.size(); i++) {
				obj = listObj.get(titleMap.get(i));

				if (obj != null) {
					val = titleMap.get(i);
					return val;
				}
			}

		//If we know the name of the list, search within it
		} else {
			for (int i = 0; i < titleMap.size(); i++) {

				obj = titleMap.get(i);

				if(arr.contains(obj)) {
					val = (String) obj;
					return val;
				}
			}
		}

		//Search description if no results are found in the title
		return searchDescr(arr, listObj, listName);
	}

	//Searches description for matching words if not found in JSON file
	static String searchDescr(JSONArray arr, JSONObject listObj, String listName) {
		String val;
		Object obj;

		if (arr == null) {
			for (int i = 0; i < AppFrame.descrArr.length; i++) {
				System.out.println("Test");
				obj = listObj.get(AppFrame.descrArr[i]);

				if (obj != null) {
					val = titleMap.get(i);
					return val;
				}
			}
		} else {
			for (int i = 0; i < AppFrame.descrArr.length; i++) {

				if(arr.get(i).equals(AppFrame.descrArr[i])) {
					val = AppFrame.descrArr[i];
					return val;
				}
			}
		}

		//Return empty string if no results are found
		return "";

	}

	//Display the final output
	static void suggestTitle(String brand, String product, String model) {
		  JOptionPane.showMessageDialog(
				  null,
				  "The parsed title is: " + brand + " " + product + " " + model
				  );
	  }
}
