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
//import java.math.BigDecimal;
//import java.util.concurrent.atomic.*;

public class ShoppingClass extends Thread{
  
     
    private final String[][] products = {{"Eggs", "Margarine", "Uncooked ground beef", "cornmeal"}, {"Frankfurters", "pasta", "Bread", "Apples"},
                                         {"Prescription drugs", "Canned vegetables", "Pet food", "sugar"}, {"Roasted coffee", "Potatoes", "Bakery products", "Candy"}, 
                                         {"Bacon", "whole chicken", "cookies", "milk"}};
    
    private final double[][] prices = {{1.05, 0.80, 5.00, 2.05}, {5.05, 1.50, 1.00, 1.05},
                                         {2.30, 1.20, 1.10, 0.90}, {1.20, 0.30, 1.30, 0.90}, 
                                         {1.30, 6.20, 2.30, 1.80}};
    
   
    
    private final int CustomerNumber = SupermarketModel.NumberOfCustomer;
    
    double OverallPrice;
    
    private int MaxBasketLoad;
      
    public static List<List<String>> listOfAllProductSelected = new ArrayList<>();
    
    public static List<String> customerTotalPrice = new ArrayList<>();
    
    //public static volatile AtomicReference<List<String>> listOfAllProductSelected = new AtomicReference<List<String>>();
    
    private List<List<String>> listOfAllAisleVisited = new ArrayList<>();
    
    public static List<String> CustomerRemoved = new ArrayList<>();
    
     // <editor-fold defaultstate="collapsed" desc="ShoppingClass contructor">   
    
    public ShoppingClass(int MaxBasketLoad)
    {
        this.MaxBasketLoad = MaxBasketLoad;
    }
   //   </editor-fold>
    
   // <editor-fold defaultstate="collapsed" desc="ShoppingClass methods">   
    @Override
    public void run() 
    {

      ShopperSelectionAlgorithm();
      
    }
    
    public void RunShoppingThread()
    {
        start();
    }
    
    
    public List<List<String>> GetListOfAllProductSelected()
    {return this.listOfAllProductSelected;}
    
    public List<List<String>> GetAisleVisited()
    {
        return this.listOfAllAisleVisited;
    }
    
     ///////////////
    // Algorithm that enables customers to select products. Currently, customers can have 10 products maximum
    ////////////////
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
           
         TempCustomerSelection.add(TempproductSelected);  
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
    
    
    ////////////////////
    // Searches for customers who did not take any item and store it in a list
    ////////////////////
    public void GetRidOfSpecificCustomers(List<String> customerName, List<List<String>> customerProduct)
    {
          for(int y = 0 ; y < CustomerNumber ; y++ ) 
     {
         
             if(customerProduct.get(y).isEmpty() == true)
             {
            
                 CustomerRemoved.add(customerName.get(y));
             
             }
             else{}
         
     }  
    
    }
    
    
    public List<String> GetCustomerRemoved()
    {return CustomerRemoved;}
    
    public String GetOverallPrice()
    { 
        double OverallPrice = Math.floor(this.OverallPrice * 100) / 100;
        String finalprice = Double.toString(OverallPrice);
        return finalprice;
    }
    
    ////////////////
    //  pause the thread for a certain amount of time
    ////////////////
    private void TimeToPlaceItemInBasket(int TimeMili, int TimeNano)
    {
        try {
           Thread.sleep(TimeMili, TimeNano);
       } catch (InterruptedException ex) {
           Logger.getLogger(ShoppingClass.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
   //   </editor-fold> 
}
