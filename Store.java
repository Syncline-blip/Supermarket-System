import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Store
{
    // instance variables 
    private FileReader file;
    private ArrayList<Product> listOfproduct;
    


    /**
     * 1) Constructor for objects of class Store
     * 2) Initialise instance variables
     * 2) Get the list of line from fileReader class. Create anonymous Product type of objects, then add them into listOfproduct.
     */
    
    public Store(String fileName)
    {
       //TO DO 14 
       file = new FileReader(fileName);
       listOfproduct = new ArrayList<>();
       for(int i = 0; i < file.totalLines(); i++)
       {
           Product products = new Product(file.getLines().get(i));
           listOfproduct.add(products);
       }

    }

    /**
     * 1)Find the most expensive product of a category.
     * @param e.g. "veg"
     * 2)Return the product id of the product.
     */
    public String expensiveItem(String prod_category)
    {
        //TO DO 15
        ArrayList<Double> tempArray = new ArrayList<>();
        
        double expensiveProduct = 0.0;
        String productID = "";
        boolean exists = false;
        for(Product products : listOfproduct)
        {
            if(products.getCategory().equals(prod_category))
            {
                tempArray.add(products.getPrice());
                expensiveProduct = Collections.max(tempArray);
                if(products.getPrice() == expensiveProduct)
                {
                    productID = products.getProductID();
                }
                exists = true;
            }

        }
        if(exists == false)
        {
            throw new InvalidParameterException("Category does not exist");
        } 
        return productID;
    }

    /**
     * 1) Find the most expensive product of a category from a particular store.
     * 2) Return the product id and price of a product. Return the value in a single line as a string using string concatenation. 
     * Example output: P10982,21.0
     */

    public String expensiveItemStore(String prod_category, String store_id) throws InvalidParameterException
    {
        //TO DO 16
        ArrayList<Double> tempArray = new ArrayList<>();
        double expensiveStoreProduct = 0.0;
        String productDetails = "";
        boolean exists = false;

        for(Product products : listOfproduct)
        {
            if(products.getCategory().equals(prod_category) && products.getStoreID().equals(store_id))
            {
                tempArray.add(products.getPrice());
                expensiveStoreProduct = Collections.max(tempArray);
                if(products.getPrice() == expensiveStoreProduct)
                {
                    productDetails = products.getProductID() + "," + products.getPrice(); 
                }
                exists = true;

            }
            

        }
        if(exists == false)
        {
            throw new InvalidParameterException("Category or Store is not in the given records.");
        }
     
        
        return productDetails;
    }

    /**
     * 1) Find the list of product and price which price is between "min" and "max". 
     * @param e.g min = 2, and max =10. 
     * 2) Return the list in an accumulated string using string concatenation.
     * 3) The list should be a String type, each product details seperate by a new line using "\n".  
     * 4) Throw an exception for a negative argument and "min" must not be greater than "max" value.
     * 
     * An example of returning list:
     * P10082,4.71
     * P10032,3.54
     */

    public String findProduct(int min, int max) 
    {
        //TO DO 17
        String productDetails = "";
     
        if((min < 0 || max < 0))
        {
            throw new IndexOutOfBoundsException("Error: Value range cannot be less than 0");
        }
        else if(min > max || max < min)
        {
            throw new IndexOutOfBoundsException("Error: min value must not be greater than max value");
        }
        else
        {
            for(Product products : listOfproduct)
            {
                if(products.getPrice() >= min && products.getPrice() <= max)
                {
                    productDetails += products.getProductID() + "," + products.getPrice() + "\n";
                }
            }
        }
        return productDetails;
    } 

    /**
     * Use the prod_id to check the price of that product. Throw an exception if no product matches the product id.
     * @param e.g "P10982"
     */
    public double checkPrice(String prod_id)
    {
        // TODO 18
        double productPrice = 0.0;
        boolean exists = false;
  
        for(Product products : listOfproduct)
        {

            if( products != null && products.getProductID().equals(prod_id))
            {
                
                productPrice = products.getPrice();
                exists = true;
                
            }
            else if(exists == false)
            {
                throw new InvalidParameterException("Product not found");
            }
        }
                
     
        return productPrice;
    }

    /**
     * 1) Find the list of products in a store using a store ID. Return the product id and price using an ArrayList of String type
     * 2) Throw an exception if no store matches the store id.
     * @param e.g store_id = "S0198"
     * Example output: 
     * P10082,4.71
     * P10032,3.54
     */

    public ArrayList<String> getproductbyStoreID(String store_id){
        //TO DO 19
        
        ArrayList<String> productsByStore = new ArrayList<>();
        boolean exists = false; 


        for(Product products : listOfproduct)
        {
            if( products != null &&products.getStoreID().equals(store_id))
            {
                
                productsByStore.add(products.getProductID() + "," + products.getPrice());
                exists = true;
                
            }
            else if(exists == false)
            {
                throw new InvalidParameterException("Store not found");
            }
        }
        return productsByStore;
        

    }

    /**
     * 1) Get the average cost of an item in an item category.
     * @param e.g "veg"
     * 2) Throw an exception in calculating the average that a value can not be divided by zero.
     * 3) Return the value with two decimal places. For Example: from 19.887 to 19.89
     */

    public double averageCost(String prod_category){
        //TO DO 20
        int counter = 0;
        double averagePrice = 0.0;
        double categoryPrice = 0.0;
        boolean exists = false;
    
        for(Product products : listOfproduct)
        {
            if(products.getCategory().equals(prod_category))
            {
                counter += 1;
                categoryPrice += products.getPrice();
                averagePrice = categoryPrice/counter;
                exists = true;
            }
            else if(exists == false)
            {
                throw new IndexOutOfBoundsException("Cannot divide by 0, the category was not found");
            }
        }
        averagePrice = (double)Math.round(averagePrice*100.0)/100.0;
        return averagePrice; 
    }

}
