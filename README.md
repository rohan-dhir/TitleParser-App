# Title Parsing Application

This is a Java Maven application that parses a given title of a product based on known titles within a database or based on the product description.

## Overview

The purpose of this project is to provide more elegant product titles for E-commerce websites such as Amazon by suggesting a simpler title whilst omitting unnecessary details that may belong in the description rather than a title. This can improve reability and ease of use when searching for specific products. 

This project utilizes json-simple in order to read and write to JSON files, which act as our database for the purpose of this application. In order to parse a title, we first search our database for known titles and products and try to match the words given by the user to parse the title. If we cannot find a match in our database, we then move on to the given description match the number of occurences of the given words in the title with the description. We then assume that these words are the title and add them to our databse.

## Challenges

This application is in no way a 'one size fits all' solution, however it is an attempt to demonstrate how one may approach the problem of creating a 'smart' title solution by creating a knowledge base of known information of different products and matching the title provided by the user in order to parse it. 

## Further Improvment 

There are numerous special use cases where this application would not be able to parse correctly. Improving upon the database, one may add additional variables such as 'product categories' to allow for more complex products which contain numerous specifications that may need to mentioned in the title, such as laptops.

