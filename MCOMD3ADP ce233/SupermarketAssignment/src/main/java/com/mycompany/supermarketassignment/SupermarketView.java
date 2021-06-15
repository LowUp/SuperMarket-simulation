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
import java.util.Date;

public class SupermarketView extends Thread{
    
 // <editor-fold defaultstate="collapsed" desc="SupermarketView methods">   
    public void SimulationStart()
    {
        System.out.println("Simulation starts \n");
    }
    
    public void CreationOfCustomers()
    {
    System.out.println("Customers are being created... \n");     
    }
       
    public void customersEnter(List<String> customers)
    {
         System.out.println("Customers start to enter in the supermarket \n");
        for(int i = 0 ; customers.size() > i ; i++ ) 
     {     
          slice();
          System.out.println(dateFormat() + " - " +customers.get(i) + " enters in the store");        
     }
    }
   
    public void customersStartShopping()
    {
    System.out.println("\n Customers pick a basket and start their shopping... \n");
    
    }
    
    public void customersChoices(List<String> customers, List<List<String>> productsSelected, List<List<String>> Aisle)
    {
        System.out.println("\n");
     System.out.println("Customers have finished their shopping \n");
     slice();
        for(int i = 0 ; customers.size() > i ; i++ ) 
     {     
          slice();
          System.out.println(customers.get(i) + " has chosen " + productsSelected.get(i) + " at aisle(s): " + Aisle.get(i));        
     }
    }
    
    public void CustomersRemoved(List<String> customersRemoved)
    {
        System.out.println("\n");
        
        for(int i = 0 ; customersRemoved.size() > i ; i++ ) 
     {     
          slice();
          System.out.println(customersRemoved.get(i) + " picked no item and left the store");        
     }
    
    }
    
    public void TillsOpening()
    {
        System.out.println("\n");
        System.out.println("Tills start opening:");
    }
    
    public void ShowReport(String NumberOfCustomers, List<String> Bag, String OverallPrice)
    {
        System.out.println("\n");
        System.out.println("------------------------------------<Report>-----------------------------------");
        System.out.println("Total number of customers:" + NumberOfCustomers);
        System.out.println("Supermarket gain: £" + OverallPrice);
        slice();
        System.out.println("\nCustomers bag content:");
        for(int y = 0; y < Bag.size(); y++)
        {
            slice();
            System.out.println(Bag.get(y));
        }
        
    
    }
    
    public void SimulationEnd()
    {
        System.out.println("\n");
        System.out.println("End of the Simulation");
    }
            
    
    /*public int CustomerSet()
    {
    Scanner sc= new Scanner(System.in);
    System.out.println("How many customer would you like in the supermarket?");
    int input= sc.nextInt();
    
    return input;
    
    }*/
    
    //////////////
    // Customized date format
    /////////////
    private String dateFormat()
    {
    Date time = new Date();
        
    String format = String.valueOf(time.getHours()) + "h:" +String.valueOf(time.getMinutes()) + "min:" + String.valueOf(time.getSeconds() + "s");
    return format;
    }
    
    ///////////////////////
    /// Time slice
    ///////////////////////
     private void slice() {

        try {

            Thread.sleep(0, 100);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }
}
     
     
       // </editor-fold>
}
