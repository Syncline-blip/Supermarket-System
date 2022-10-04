import java.util.ArrayList;


public class Product
{
    // instance variables 
    private String product_id; 
    private double discount;   
    private double price;      
    private String store_id;   
    private String prod_category;

    

    /**
     * Constructor for objects of class Product
     * Initialise instance variables
     * Default Constructor
     */
    public Product(String product_details)
    { 
        // Split the strings by spaces
        String[] separatedString; // stores product_details and splits them
        separatedString = product_details.split(" ");
        product_id = separatedString[0]; 
        price = Double.parseDouble(separatedString[1]);
        discount = Double.parseDouble(separatedString[2]);
        store_id = separatedString[3];
        prod_category = separatedString[4];
        //TO DO 2

    }

    /**
     * Return the product ID
     */
    public String getProductID()
    {
      //TO DO 3  
      return product_id;
    }
    
    /**
     * Return the product price
     */
    
    public double getPrice()
    {
      //TO DO 4
      return price;
    }
    
    /**
     * Return the discount of a product 
     */
    public double getDiscount()
    {
       //TO DO 5 
      return discount;
    }
  
    
   /**
    * Return the category of a product 
    */ 
    public String getCategory()
    {
      //TO DO 6 
      return prod_category;
    }
    
    /**
     * Return the store ID of a product 
     */
    public String getStoreID()
    {
      //TO DO 7 
      return store_id;
    }

    
    // toString() overriden method to show class items as string, for testing purposes
    // Please ignore this.
    @Override
    public String toString()
    {
        return product_id + " " + price + " " + discount + " " + store_id + " " + prod_category;
    }
    
    
    
}
