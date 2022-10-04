import java.util.ArrayList;
import java.util.Random;


public class Order
{
    // instance variables s
    private FileReader file;
    private ArrayList<Product> product_list; 
    private String orderID;
    private double total_price;
    private double total_discount; 
    private String invoice_reference;
    private int total_item;

    /**
     * 1) Constructor for objects of class Order
     * 2) Initialise instance variables
     * 3) Provide the input file name and first letter of your first name and last as an order ID
     * e.g ("input.txt" "CP") CP for Collin Peter
     * 4) Get the list of lines from fileReader class. Create anonymous Product type of objects, then add them into product_list. 
     */
    public Order(String fileName, String order_id)
    {
        //TO DO 1
        
        String lines = ""; // Stores the extracted lines form file.
        file = new FileReader(fileName);
        product_list = new ArrayList<Product>();
        orderID = order_id;
        total_price = 0; // Since values are not given during call of constructor, 0 is used as placeholder.
        total_discount = 0;
        invoice_reference = "";
        total_item = 0;
        
        
        // Iterate through every line in file starting from 0 to value of file.totalLines();
        for(int i = 0; i < file.totalLines(); i++)
        {   
            lines = file.getLines().get(i);
            Product products = new Product(lines); // For every line in file, create a new product object, append to list.
            product_list.add(products);
        }

    }


    /**
     * Generate invoice ID using OrderID and random generated value. String concatenation is applicable here. 
     * if orderID = "AN", and random value is 18. 
     * example output: AN18
     */

    public String generateInvoice(){
       //TO DO 8
        Random randomNumberGen = new Random();
        int randomNumber = randomNumberGen.nextInt(100); // Generate a random number up to 100
        invoice_reference = orderID + randomNumber; // Concatenate orderID and randomNumber
        return invoice_reference; 
    }

    /**
     * 1) This method must communicate with Product class
     * 2) The method should be able to place the order using product id and unit. It will take one product item at a time. 
     * 3) Calculate the total payable amount by the customer
     * 4) To place multiple orders method should be able to get called multiple times. The total price and total discount will be accumulated to the field variable total_price total_discount respectively.
     * 5) Return the accumulated total price from this method
     * @param e.g prod_id = "P10982" and unit = 2
     * 6) Return the value with two decimal places. For Example: from 19.887 to 19.89
     */
    public double placeOrder(String prod_id, int unit)
    {
        //TO DO 9
        double multiProductPrice = 0; // price x quantity, example: 21.0 * 2 = 42
        double actualProductPrice = 0; // price of product after discount
        for(Product product : product_list)
        {
            if(product.getProductID().equals(prod_id))
            {
                multiProductPrice = product.getPrice() * unit;
                total_item += unit; 
                actualProductPrice = (multiProductPrice * (100-product.getDiscount())/100);
                total_discount = (multiProductPrice*product.getDiscount()) / 100;
                total_price += (double) Math.round(actualProductPrice*100)/100; // Accumulates every time method is called.    
                                                                                // Round to two decimal places    
            }
           
           
        }
        return total_price;
    }
    /**
     * 1) This method must communicate with Product class
     * 2) Return the accumulated total price after placing the order. 
     * 3) Return the value with two decimal places. For Example: from 19.887 to 19.89
     */
    public double getTotalPrice() 
    { 
        //TO DO 10
        return total_price;
    }

    /**
     * 1) This method must communicate with Product class
     * 2) Get the accumulated total amount of discount that has been applied after placing the order.
     * 3) Return the value with two decimal places. For Example: from 1.113 to 1.11
     */

    public double getTotalDiscount(){
        //TO DO 11
        total_discount = (double) Math.round(total_discount * 100.0)/100.0; // Round to two decimal places
        return total_discount;
    }

    /**
     * Return total number of items ordered by a customer
     */
    public int getTotalItem(){
        //TO DO 12
        return total_item;
    }

    /**
     * print orders in the follwing format. 
     * *******Order Details*******
     * Invoice Number: AN43
     * Total items :2
     * Amount Payable: 36.7
     * Discount Applied: 2.4
     */

    public void printOrder()
    {
       //TO DO 13
       System.out.println("*******Order Details*******\n" +
                          "Invoice Number: " + generateInvoice() + "\n"+ 
                          "Total items: " + getTotalItem() + "\n" +
                          "Amount Payable: " + getTotalPrice() + "\n" +
                          "Discount Applied: " + getTotalDiscount() + "\n");
    }

  
}
