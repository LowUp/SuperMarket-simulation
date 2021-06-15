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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerClass extends Thread{
    
    public static List<String> listOfCustomers = new ArrayList<String>();
   
   private final int CustomerNumber;
   
  
     
   // <editor-fold defaultstate="collapsed" desc="CustomerClass Contructor"> 
   
   public CustomerClass(int CustomerNumber)
   {
        this.CustomerNumber = CustomerNumber;
   }
   
   //   </editor-fold>  
   
   // <editor-fold defaultstate="collapsed" desc="CustomerClass methods"> 
   
    @Override
    public void run() {

      CustomerGenerator(CustomerNumber);

    }
   
    public void runCustomer()
    {
    start();
 
    }
    

    
     ///////////////
   // Method to retrieve customers 
   ///////////////
   public List<String> GetCustomerNames()
    {return this.listOfCustomers;} 
   
   
        ///////////////
        // Creates customers
        //////////////
        private void CustomerGenerator(int numberOfCustomer)
    {
   String[] CustomerNames = new String[]{"John", "George", "Oliver", "Harry", "Noah", "Jack", "Jacob", "Leo", "Oscar", "Charlie", "Muhammad", "Olivia", "Amelia", "Isla", "Ava", "Emily", "Isabella", "Mia", "Poppy", "Ella", "Lily"};
    
    
    for(int i = 1 ; i <= numberOfCustomer ; i++ ) 
     {
         int RandomNumber = (int)(Math.random() * CustomerNames.length);
         String Names = CustomerNames[RandomNumber];
       
         TimeToCreateCustomer(0, 1);
    
    this.listOfCustomers.add(Names + " No " + i);
    
    
     
     }   
        
    }
        
        
        ///////////////
        // pause the thread for a certain amount of time
        ///////////////
      private void TimeToCreateCustomer(int TimeMili, int TimeNano)
      {
        try {
           Thread.sleep(TimeMili, TimeNano);
       } catch (InterruptedException ex) {
           Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
       }
      }
    
        
  //   </editor-fold>   
        
  }
    



