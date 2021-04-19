# SuperMarket-simulation
This presents my personal attempt on creating a supermarket simulation using java.
This program was implemented using Apache NetBeans IDE 12.1, an installation guide will be present in the appendix.


Functional requirements:

The simulation had to meet a minimal set of functional requirements, which are: 
1. The program must support a small database of 20 products.
2. The program must simulate a shop floor, tills, shoppers, cashiers, shopping baskets, products, and single bags. 
3. The program must simulate different scenarios such number of open tills, the maximum queue size and/or the number of shoppers. 
4. The simulation of supermarket queue must be in processor time. 
5. The program must quantify the flow of customer through the market given the number and the size queues at the tills.
6. The simulation should provide a usable user interface that fulfils all other requirements.

1. To meet the first requirement, the program contains a private 2D string array labelled “products” which contains 20 elements. “products” is located in “ShoppingClass.java”. These elements are considered as products in the program.
code snipped: private final String[][] products = {{"Eggs", "Margarine", "Uncooked ground beef", "cornmeal"}, {"Frankfurters", "pasta", "Bread", "Apples"}, {"Prescription drugs", "Canned vegetables", "Pet food", "sugar"}, {"Roasted coffee", "Potatoes", "Bakery products", "Candy"}, {"Bacon", "whole chicken", "cookies", "milk"}};
The program is made in a way where more element can be added to this array without leading the whole program to crash.
This “products” is used in a method labelled “ShopperSelectionAlgorithm()”.
 	2. For the second requirement, to represent the shopping floor the program simulates aisles where at a specific aisle there is products that cannot be found in other aisles. For example, at aisle 0 there is {"Eggs", "Margarine", "Uncooked ground beef", "cornmeal"} and at aisle 1 there is {"Frankfurters", "pasta", "Bread", "Apples"}. To store the aisles visited by customers, there is a private list of lists of datatype string labelled “listOfAllAisleVisited”. “listOfAllAisleVisited” is used in methods: “GetAisleVisited()”, “ShopperSelectionAlgorithm()”.
code snipped: private List<List<String>> listOfAllAisleVisited = new ArrayList<>();
This attribute represent all aisles where customers have picked a product. “listOfAllAisleVisited” can be located in “ShoppingClass.java”.
To represent the tills, the program has a till class labelled “Till.java”, this “Till.java” class extends from Tread. The purpose of this class is to search which customers are still in the supermarket and adding it in a queue. Customers take 1000 nano seconds to enter the queue. To instantiate an object of this class 3 properties, need to be delivered, the queue size, the queue name and the shared queue.
code snipped:  private Till tillNo1 = new Till(QueueMaxSize, "1", QueueNo1);
To represent the shoppers, the code has a shopper class labelled “CustomerClass.java” which extends from a Thread. The purpose of this class is to create customers and these customers are stored in a public static list of datatype string labelled “listOfCustomers”.
code snipped:  public static List<String> listOfCustomers = new ArrayList<String>();
To represent cashiers, the code has a cashier class labelled “Cashier.java”. The purpose of this class is to make customer that are in a queue to be served. Once in a queue it takes 3000 nano seconds for a customer to be served, after that, the cashier scans all of the customer product with an algorithm labelled “GetServed()”. It takes 500 nanoseconds for a product to be scanned which makes the time to be served of each customer dependent on their basket load. Once served, the basket content of the customer is deleted from the “listOfAllProductSelected” attribute in a method called “LeaveQueue”, code snipped:

private void LeaveQueue(Queue<String> Queue, Queue<Integer> Index, List<List<String>> customerProduct)
    {   
        List<String> removeList = customerProduct.get(Index.peek());
         boolean ItemsRemoved = customerProduct.get(Index.peek()).removeAll(removeList);
        Index.poll();
        if(ItemsRemoved){System.out.println(Queue.poll() + " Leave the store ");}
       
    }
and transferred into “ItemsInBag” in a method called “PlaceItemsToBag”. 
code snipped:
private void PlaceItemsToBag(String ProductScanned, Queue<String> Queue)
    {
       ItemsInBag.add(Queue.peek()+" bought:  "+ProductScanned);
        System.out.println("\n"+Queue.peek()+" places his items to his bag");
    }
To represent the shopping basket, a list of lists of string type attribute is used in the code. This attribute is located in ”ShoppingClass.java” class. 
code snipped: public static List<List<String>> listOfAllProductSelected = new ArrayList<>();
code snipped: private int MaxBasketLoad;
The MaxBasketLoad attribute is defined when an object of the class is instantiated. There is a method in the ”ShoppingClass.java” class called “ShopperSelectionAlgorithm()” that populates the customer baskets by adding a certain number of product in it depending on the MaxBasketLoad number. Customers that did not take any items are considered as they left the store. 
code snipped: 
   private void ShopperSelectionAlgorithm()
   { 
    
  for(int y = 1 ; y <= CustomerNumber; y++ )  
   {
      List<String> TempCustomerSelection = new ArrayList<String>();
      List<String> TempAisleVisited = new ArrayList<String>();
       double TemptotalPrice = 0;
      int RandomNumberForBasketLoad = (int)(Math.random() * MaxBasketLoad);
      
      
    for(int i = 0 ; i < RandomNumberForBasketLoad ; i++ ) 
     {
         int RandomNumberForAisle = (int)(Math.random() * products.length);
         
         int RandomNumberForProduct = (int)(Math.random() * products[RandomNumberForAisle].length);
         
         String TempproductSelected = products[RandomNumberForAisle][RandomNumberForProduct];
         
         String TempAisle = Integer.toString(RandomNumberForAisle);
         TemptotalPrice += prices[RandomNumberForAisle][RandomNumberForProduct];
         TempCustomerSelection.add(TempproductSelected);  z
         TempAisleVisited.add(TempAisle);
       
     } 
        TimeToPlaceItemInBasket(0, 1);
        double totalprice =  Math.floor(TemptotalPrice * 100) / 100;
        OverallPrice += TemptotalPrice;
       
        this.customerTotalPrice.add(Double.toString(totalprice));
        this.listOfAllProductSelected.add(TempCustomerSelection);    
        this.listOfAllAisleVisited.add(TempAisleVisited);  
       
    }          
   }
To represent single bag, the code has a list of string type called “ItemsInBag” located in “Cashier.java”. This collection of variable stores all items bought by customers after their checkout.
code snipped: private List<String> ItemsInBag = new ArrayList<>();

	3. The code simulates different scenarios. 
- It simulates when a customer enters in the shop:
code snipped: 12h:29min:21s - Lily No 1 enters in the store.
-It simulates when a customer is done with his shopping and displays all the items chosen by the customer and all the aisles the items were picked from:
code snipped: Lily No 1 has chosen [Uncooked ground beef, cookies, Canned vegetables, pasta, Prescription drugs] at aisle(s): [0, 4, 2, 1, 2]
-It simulates when an employee open a till and close
code snipped: --------------------------------<till 1 open>---------------------------------
-------------------------------------<till 1 closes>--------------------------------------  				           -It simulates which customer joined a queue and displays a visual of the customers in a queue
code snipped: 
Oliver No 3 Joins Queue 1
Queue 1 [Oliver No 2, Oliver No 3]
-It simulates which employee is scanning product from which customer
code snipped: 
Employee 1 scans: Uncooked ground beef from: Lily No 1
Employee 1 scans: cookies from: Lily No 1
Employee 1 scans: Canned vegetables from: Lily No 1
Employee 1 scans: pasta from: Lily No 1
Employee 1 scans: Prescription drugs from: Lily No 1
-It simulates when a customer leaves the store when they took no product and/or after their proceeded to the checkout.
After proceeding through the checkout:
code snipped: 
Oliver No 3 places his items to his bag
Oliver No 3 Leave the store
Leaving the store without proceeding through the checkout:
code snipped:
Leo No 2 picked no item and left the store
-It simulates when a queue is full and when the employee starts scanning products
code snipped:
Queue is full Thread-2 is waiting , size: 5

 	4. The program runs in nano seconds. It takes 1 nano second for a customer to be created and it takes the same amount of time for a customer to select its product and place it to its basket.
code snipped:
private void TimeToCreateCustomer(int TimeMili, int TimeNano)
      {
        try {
           Thread.sleep(TimeMili, TimeNano);
       } catch (InterruptedException ex) {
           Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
       }
      }
TimeToCreateCustomer(0, 1);
TimeToPlaceItemInBasket(0, 1);

- It takes 100 nano second for a customer to enter in a queue
code snipped: TimeToEnterInQueue(0, 100);

- It takes 200 nano seconds for the customer at the head of the queue to get served. 
code snipped: TimeToEnterInQueue(0, 100);

- To scan on product, the employee takes 10 nano seconds
code snipped: TimeToScanProduct(0, 10);
For a customer to place its items in its bag, it takes 100 nano seconds
code snipped: TimeToScanProduct(0, 100);


 	5. The program displays a report at the end when there are no more customers in the supermarket. It displays the number of customers in the supermarket, all the items they bought, and the total amount of money the supermarket received.
code snipped: 
------------------------------------<Report>-----------------------------------
Total number of customers:4
Supermarket gain: £31.8

Customers bag content:
Jack No 2 bought: [Bacon, pasta, Prescription drugs, Bakery products, Eggs, pasta, ]
Oliver No 3 bought: [Bread, Canned vegetables, cornmeal, Roasted coffee, sugar, whole chicken, Apples, ]
Ava No 4 bought: [Potatoes, Eggs, cookies, cookies, Potatoes, pasta, pasta, ]


End of the Simulation

Improvements:
The reason why a lot of attributes and classes in the code are private is for security, integrity, and bug free purpose. It disables programmers from calling attribute from any class and changing the value of it. Also, it protects methods that are meant to be ran once only to be called in a different class. 
The program includes prices and aisles. This adds more information for the user to examine. 

Limitation:
The program does not let the user to interact at all, for example, it does not let the user choose how much customers they want to simulate in the supermarket or how muck queue they want to use. This is problematic. The number of customers can only be changed in the backend system, in “SupermarketModel.java”. Also, the program only includes a single queue system, which is problematic because the program is supposed to implement a supermarket and usually in a supermarket there is more than one queue.  

