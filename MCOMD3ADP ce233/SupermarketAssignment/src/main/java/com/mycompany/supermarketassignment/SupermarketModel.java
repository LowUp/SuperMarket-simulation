/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.supermarketassignment;

/**
 *
 * @author User
 */

/*
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
*/
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SupermarketModel{

   public static int NumberOfCustomer = 4;
   private final int QueueMaxSize = 5;
   private final int MaxBasketLoad = 10;
   
   private Queue<String> QueueNo1 = new LinkedList<String>();
    
   private CustomerClass customer = new CustomerClass(NumberOfCustomer);
   private ShoppingClass shopping = new ShoppingClass(MaxBasketLoad);
   
   private Till tillNo1 = new Till(QueueMaxSize, "1", QueueNo1);
   private Cashier employeeNo1 = new Cashier(QueueNo1, "1");
   
   
   
   
   
    // <editor-fold defaultstate="collapsed" desc="Supermarket methods"> 
   
   
   public List<String> GetCustomers() { return customer.GetCustomerNames();}
   
   public List<List<String>> GetAllProductSelected() { return shopping.GetListOfAllProductSelected();}
   
   public List<List<String>> GetAllAisleVisited(){return shopping.GetAisleVisited();}
   
   public void RemoveSpecificCustomers(){shopping.GetRidOfSpecificCustomers(customer.GetCustomerNames(), shopping.GetListOfAllProductSelected());}
   
   public List<String> CustomerRemovedName(){return shopping.GetCustomerRemoved();}
   
   public String GetNumberOfCustomers()
   {  String NumberOfCustomers = Integer.toString(NumberOfCustomer);
   return NumberOfCustomers;
   }
   
   public Integer GetNumberOfCustomersInInteger() { return NumberOfCustomer;}
   
   public List<String> GetBag(){return employeeNo1.GetItemsBought();}
   
   /////////////////////
    // Run the customer thread and waits until the thread is finished
    ///////////////////
   public synchronized void RunCustomerThread()
   {
      
       customer.runCustomer();
       try {
           customer.join();
       } catch (InterruptedException ex) {
           Logger.getLogger(SupermarketModel.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
  
   /////////////////////
    // Run the customer thread and waits until the thread is finished
    ///////////////////
   public void RunShoppingThread()
   {
       shopping.RunShoppingThread();
       try {
           shopping.join();
       } catch (InterruptedException ex) {
           Logger.getLogger(SupermarketModel.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   /////////////////////
    // Run the Till thread and waits until the treads is finished
    ///////////////////
   public void TillNo1()
   {
        tillNo1.RunTillThread();
        employeeNo1.RunCashierThread();    
        
       try {    
           tillNo1.join();
           employeeNo1.join();
       } catch (InterruptedException ex) {
           Logger.getLogger(SupermarketModel.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public String GetOverallPrice() {return shopping.GetOverallPrice();}
   
   
    //   </editor-fold> 
    
    
}
