package org.TitleParser;

/**
* This application will accept an entered product title
* The title will be parsed based on known information or product description
* The format for all titles will follow {Brand, Product Name, Model Name/Number}
* New brands, products and model names will be stored for easier parsing in the future
*/

public class App extends AppFrame
{
	public static void main( String[] args )
    {
    	AppFrame newWindow = new AppFrame();
    	newWindow.createWindow();
    }
}
