/*
NAME: CLINT JOSEPH S. UBANAN
BSCS 2A

DATA STRUCTURE ACTIVITY - ONLINE RETAIL SYSTEM
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

class OnlineRetailSystem{
   public static Scanner scan = new Scanner(System.in);

   public static void main(String[] args){
      User     user     = new User(0, "", "");
      Customer customer = new Customer(0, "", "", "");
      Admin    admin    = new Admin(0, "", "", "");
      Product  product  = new Product(0, "", 0, 0);
      Order    order    = new Order(0);
      
      int userDisplay, customerChoice, adminChoice;
      
      boolean logout              = false;
      boolean customerDisplayExit = false;
      boolean adminDisplay        = false;
      boolean invManageExit       = false;
      boolean psManageExit        = false;
      
      // USER LOGIN DISPLAY
      System.out.println("--- ONLINE RETAIL SYSTEM ---");
      
      do {
         // USER DISPLAY
         System.out.println("\n1. Customer");
         System.out.println("2. Admin");
         System.out.println("3. Exit");
            
         System.out.print("Enter Here: ");
         userDisplay = scan.nextInt();
         
         // CUSTOMER
         if (userDisplay == 1){
            System.out.println("\n--- CUSTOMER LOGIN ---");
            
            customer.Login();
            customer.getAddress();
            
            do {
               // CUSTOMER DISPLAY
               System.out.println("\n--- CUSTOMER DISPLAY ---");
               System.out.println("1. Place Order");
               System.out.println("2. View Order History");
               System.out.println("3. Exit");
               System.out.print("Enter Here: ");
               customerChoice = scan.nextInt();

               if (customerChoice == 1){
                  customer.placeOrder(); // PLACE ORDER
               }  
               else if (customerChoice == 2){
                  customer.viewOrderHist(); // ORDER HISTORY
               }
               else if (customerChoice == 3){
                  customerDisplayExit = true; // CUSTOMER DISPLAY EXIT
               }
               else{
                  customerDisplayExit = false; // ERROR OUTPUT
                  System.out.println("Please enter a valid number/choice.");
               }
            }while (!customerDisplayExit);
         } 
         // ADMIN
         else if (userDisplay == 2){
            System.out.println("\n--- ADMIN LOGIN ---");
            
            admin.Login();
            
            admin.getDepartment();
            
            do { // ADMIN DISPLAY
               System.out.println("\n--- ADMIN MANAGEMENT ---");
               System.out.println("1. Inventory Management");
               System.out.println("2. Price & Stock Management");
               System.out.println("3. Product Table");
               System.out.println("4. Exit");
               
               System.out.print("Enter Here: ");
               adminChoice = scan.nextInt();
               
               if (adminChoice == 1){ // INVENTORY MANAGEMENT
                  do {
                     System.out.println("\n--- INVENTORY MANAGEMENT ---");
                     System.out.println("1. Add Product");
                     System.out.println("2. Remove Product");
                     System.out.println("3. Exit");
                     
                     System.out.print("Enter Here: ");
                     int invManage = scan.nextInt();
                     
                     if (invManage == 1){ // ADD PRODUCT
                        admin.addProduct();
                     }
                     else if (invManage == 2){ // REMOVE PRODUCT
                        admin.removeProduct();
                     }
                     else if (invManage == 3){ // INVENTORY MANAGEMENT EXIT
                        invManageExit = true;
                     }
                     else{
                        invManageExit = false; // ERROR OUTPUT
                        System.out.println("Please enter a valid number/choice.");
                     }
                  }while (!invManageExit);
               }
               else if (adminChoice == 2){ // PRICE & STOCK MANAGEMENT
                  do {
                     System.out.println("\n--- PRICE & STOCK MANAGEMENT ---");
                     System.out.println("1. Update Price");
                     System.out.println("2. Update Stock");
                     System.out.println("3. Exit");
                     
                     System.out.print("Enter Here: ");
                     int psManage = scan.nextInt();
                     
                     if (psManage == 1){ // UPDATE PRICE
                        product.updatePrice();
                     }
                     else if (psManage == 2){ // UPDATE STOCK
                        product.updateStock();
                     }
                     else if (psManage == 3){ // PRICE & STOCK MANAGEMENT EXIT
                        psManageExit = true;
                     }
                     else{
                        psManageExit = false; // ERROR OUTPUT
                        System.out.println("Please enter a valid number/choice.");
                     }
                  }while (!psManageExit);
               }
               else if (adminChoice == 3){ // PRODUCT TABLE
                  System.out.println("\n--- PRODUCT TABLE ---\n");
                  product.productTable();
               }
               else if (adminChoice == 4){ // ADMIN DISPLAY EXIT
                  adminDisplay = true;
               } 
               else{
                  adminDisplay = false; // ERROR OUTPUT
                  System.out.println("Please enter a valid number/choice.");
               }
            }while (!adminDisplay);
         }         
         else if (userDisplay == 3){ // LOGOUT
            logout = true;
         }
         else{
            logout = false; // ERROR OUTPUT
            System.out.println("Please enter a valid number/choice.");
         }  
             
      }while (!logout);
      
      user.Logout(); // LOGOUT
   }
}

// USER CLASS
class User{
   int    userID;
   String username, email;
   
   public User(int userID, String username, String email){
      this.userID   = userID;
      this.username = username;
      this.email    = email;
   }
   
   // USER LOGIN
   public void Login(){
      System.out.print("Enter User ID:  ");
      userID   = OnlineRetailSystem.scan.nextInt();
      System.out.print("Enter Username: ");
      username = OnlineRetailSystem.scan.next();
      System.out.print("Enter Email:    ");
      email    = OnlineRetailSystem.scan.next();
      
      System.out.println("\nWelcome " + username + "!");
   }
   
   // USER LOGOUT
   public void Logout(){
      System.out.println("\nYou are logged out, Goodbye! ");
   }
}

// CUSTOMER CLASS
class Customer extends User{
   ArrayList<Order> orders;
   String address;

   Product product = new Product(0, "", 0, 0);
   
   public Customer(int userID, String username, String email, String address){
        super(userID, username, email);
        this.address    = address;
        orders          = new ArrayList<>();
    }
       
   // GETS CUSTOMER ADDRESS
   public void getAddress(){
      System.out.print("Enter Address: ");
      address = OnlineRetailSystem.scan.next();
   }
   
   // PLACE ORDER
   public void placeOrder(){
      System.out.println("\n--- PLACE ORDER ---");
      product.productTable();

      int choice;
      Order order = new Order(this.userID); // CREATES A NEW ORDER

      do {
         System.out.print("\nEnter Product ID: ");
         int prodID = OnlineRetailSystem.scan.nextInt();
         System.out.print("Enter Quantity:   ");
         int qty = OnlineRetailSystem.scan.nextInt();
         
         // GETS PRODUCT USING PRODUCT ID, THIS ALSO CHECKS IF THERE IS ENOUGH STOCK ON THE CHOSEN PRODUCT.
         Product orderedProduct = Product.getProductById(prodID);
         if (orderedProduct != null){
            if (qty <= orderedProduct.stockQuantity){
               order.addProductToOrder(orderedProduct, qty);
               System.out.println("\nProduct added to order successfully!\n");
            } 
            else{
               System.out.println("\nNot enough stock available for this product.\n");
            }
         } 
         else{
            System.out.println("\nProduct with ID " + prodID + " not found.\n");
         }

         System.out.print("Add more Product? (1 = Yes, 0 = No): ");
         choice = OnlineRetailSystem.scan.nextInt();

      }while (choice == 1);
      
      // GET CURRENT DATE
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      Date date = new Date();
      order.orderDate = formatter.format(date);
      
      // CALCULATE TOTAL AMOUNT AFTER ADDING PRODUCTS
      order.calculateTotalAmount();
      
      // DISPLAY ORDER DETAILS
      order.displayOrder();

      // ADD THE ORDER TO THE CUSTOMER'S ORDER HISTORY
      orders.add(order);
   }

   // ORDER HISTORY
   public void viewOrderHist(){
      System.out.println("\n--- Order History ---");

      if(orders.isEmpty()){
         System.out.println("No Orders Yet.");
         return; // EXIT THE METHOD IF THERE ARE NO ORDERS.
      }
      
      // ORDER HISTORY DISPLAY
      for(Order order : orders){
         System.out.println("Order ID: " + order.orderID);
         System.out.printf("%-12s%-12s%-20s%-10s\n", "Product ID", "Product Name", "Quantity", "Price");

         for(Product product : order.products){
            System.out.printf("%-12d%-20s%-10d$%-9d\n", product.productID, product.productName, product.quantity, product.price);
         }
         
         // TOTAL AMOUNT OF THE CURRENT ORDER ID
         System.out.println("Total Amount: $" + order.totalAmount);
         System.out.println("------------------------");
      }
   }
}

// ADMIN CLASS
class Admin extends User{
   String department;
   
   Product newProduct = new Product(0, "", 0, 0);
   Order newOrder = new Order(0);
   
   public Admin(int userID, String username, String email, String department){
      super(userID, username, email);
      this.department = department;
   }
   
   // GETS ADMIN DEPARTMENT
   public void getDepartment(){
      System.out.print("Enter Department: ");
      department = OnlineRetailSystem.scan.next();
   }
   
   // ADD PRODUCT
   public void addProduct(){
      boolean addAnother = true;
      
      // DISPLAY CURRENT PRODUCT TABLE
      System.out.println();
      Product.productTable();
      
      do {
         System.out.println("\n--- Add Product ---");

         System.out.print("Enter Product ID:     ");
         int productID = OnlineRetailSystem.scan.nextInt();
         System.out.print("Enter Product Name:   ");
         String productName = OnlineRetailSystem.scan.next();
         System.out.print("Enter Price:          ");
         int price = OnlineRetailSystem.scan.nextInt();
         System.out.print("Enter Stock Quantity: ");
         int stockQuantity = OnlineRetailSystem.scan.nextInt();
         
         Product newProduct = new Product(productID, productName, price, stockQuantity);
         Product.addProduct(newProduct);
         System.out.println("\nProduct added successfully!\n");
         
         // DISPLAY UPDATED PRODUCT TABLE
         Product.productTable();

         System.out.print("\nAdd another product? (1 = Yes, 0 = No): ");
         int choice = OnlineRetailSystem.scan.nextInt();
         addAnother = (choice == 1);
         
      }while (addAnother);
   }

   // REMOVE PRODUCT
   public void removeProduct(){
      boolean removeAnother = true;
      
      // DISPLAY CURRENT PRODUCT TABLE
      System.out.println();
      Product.productTable();
      
      do {
         System.out.println("\n--- Remove Product ---");
         
         System.out.print("Enter Product ID to remove: ");
         int productID = OnlineRetailSystem.scan.nextInt();
         
         Product productToRemove = Product.getProductById(productID);
         if (productToRemove != null){
            Product.productList.remove(productToRemove);
            System.out.println("\nProduct removed successfully!\n");
         } 
         else{
            System.out.println("Product with ID " + productID + " not found.");
         }
         
         // DISPLAY UPDATED PRODUCT TABLE
         Product.productTable();
         
         System.out.print("\nRemove another product? (1 = Yes, 0 = No): ");
         int choice = OnlineRetailSystem.scan.nextInt();
         removeAnother = (choice == 1);
         
      }while (removeAnother);
   }   
}

// PRODUCT CLASS
class Product{
   int productID, price, stockQuantity, quantity;
   String productName;

   static ArrayList<Product> productList = new ArrayList<>();

   public Product(int productID, String productName, int price, int stockQuantity){
      this.productID     = productID;
      this.price         = price;
      this.stockQuantity = stockQuantity;
      this.productName   = productName;
   }
   
   // ADDS THE PRODUCT TO THE LIST
   public static void addProduct(Product product){
      productList.add(product);
   }
   
   // STATIC PRODUCT TABLE
   static{
      productList.add(new Product(101, "Scrub  ", 4, 5));
      productList.add(new Product(102, "Soap   ", 7, 3));
      productList.add(new Product(103, "Shampoo", 8, 2));
   }
   
   // PRODUCT TABLE 
   public static void productTable(){
      System.out.printf("%-12s%-20s%-10s%-18s\n", "Product ID", "Product Name", "Price", "Stock Quantity");

      for (Product product : productList){
         System.out.printf("%-12d%-20s$%-9d%-18d\n", product.productID, product.productName, product.price, product.stockQuantity);
      }
   }
   
   // GETS PRODUCT BY PRODUCT ID
   public static Product getProductById(int productId){
        for (Product product : productList){
            if (product.productID == productId){
                return product;
            }
        }
        return null; // RETURN NULL IF PRODUCT IS NOT FOUND
   }
   
   // UPDATE PRICE
   public void updatePrice(){
      boolean updateAnother = true;
      
      do {
         System.out.println("\n--- Update Price ---");
         
         // DISPLAY CURRENT PRODUCT TABLE
         Product.productTable();
         
         System.out.print("Enter the Product ID you want to update: ");
         int productId = OnlineRetailSystem.scan.nextInt();
         
         Product productToUpdate = Product.getProductById(productId);
         if (productToUpdate != null){
            System.out.println("Current Price:       $" + productToUpdate.price);
            System.out.print("Enter the new price: $");
            int newPrice = OnlineRetailSystem.scan.nextInt();
            productToUpdate.price = newPrice;
            System.out.println("Price updated successfully!");
         } 
         else{
            System.out.println("Product with ID " + productId + " not found.");
         }

         System.out.print("\nUpdate another product? (1 = Yes, 0 = No): ");
         int choice = OnlineRetailSystem.scan.nextInt();
         updateAnother = (choice == 1);
         
      }while (updateAnother);
   }
   
   // UPDATE STOCK
   public void updateStock(){
      boolean updateAnother = true;
      
      do {
         System.out.println("\n--- Update Stock ---");
         
         // DISPLAY CURRENT PRODUCT TABLE
         Product.productTable();
         
         System.out.print("Enter the Product ID you want to update: ");
         int productId = OnlineRetailSystem.scan.nextInt();
         
         Product productToUpdate = Product.getProductById(productId);
         if (productToUpdate != null){
            System.out.println("Current Stock Quantity:       " + productToUpdate.stockQuantity);
            System.out.print("Enter the new stock quantity: ");
            int newStockQuantity = OnlineRetailSystem.scan.nextInt();
            productToUpdate.stockQuantity = newStockQuantity;
            System.out.println("Stock quantity updated successfully!");
         } 
         else{
            System.out.println("Product with ID " + productId + " not found.");
         }
         
         System.out.print("\nUpdate another product? (1 = Yes, 0 = No): ");
         int choice = OnlineRetailSystem.scan.nextInt();
         updateAnother = (choice == 1);
         
      }while (updateAnother);
   }
}

class Order{
   static int orderCounter = 1; // STATIC ORDER ID

   int orderID, customerID, totalAmount;
   String orderDate;
   ArrayList<Product> products = new ArrayList<>();
   
   // ORDER ID
   public Order(int customerID){
      this.orderID    = orderCounter++;
      this.customerID = customerID;
   }

   // CALCULATE THE TOTAL AMOUNT 
   public void calculateTotalAmount(){
      totalAmount = 0;
      for (Product product : products){
         totalAmount += product.price * product.quantity;
      }
   }

   // ADD PRODUCT TO ORDER
   public void addProductToOrder(Product product, int quantity){
      product.stockQuantity -= quantity; // UPDATE STOCK QUANTITY
      product.quantity = quantity; // SET THE QUANTITY OF THE PRODUCT IN ORDER
      products.add(product);
      calculateTotalAmount();
   }

   // DISPLAY ORDER
   public void displayOrder(){
      System.out.println("\nOrder ID:  " + orderID);
      System.out.println("Customer ID: " + customerID);
      System.out.println("Order Date:  " + orderDate);

      System.out.printf("%-12s%-20s%-10s%-10s\n", "Product ID", "Product Name", "Quantity", "Price");
         for (Product product : products){
            System.out.printf("%-12d%-20s%-10d$%-9d\n", product.productID, product.productName, product.quantity, product.price);
      }

      System.out.println("Total Amount: $" + totalAmount);
   }
}
