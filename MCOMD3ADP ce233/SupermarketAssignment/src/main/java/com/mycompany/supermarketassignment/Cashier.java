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

public class Cashier extends Thread{
    
    private final Queue<String> customerQueue;
    private String employeeName;
    
    private List<List<String>> ProductSelected = ShoppingClass.listOfAllProductSelected;
    private Queue<Integer> Index = Till.ProductSelectedIndex;
    private Queue<Integer> priceIndex = Till.priceIndex;
    
    private final List<String> customerRemoved = ShoppingClass.CustomerRemoved;  
     private final int CustomerNumber = SupermarketModel.NumberOfCustomer;  
    private final List<String> customerPrice = ShoppingClass.customerTotalPrice;
     
    private List<String> ItemsInBag = new ArrayList<>();
    
   
    
    //<editor-fold defaultstate="collapsed" desc="Cashier constructor">
    public Cashier(Queue<String> sharedcustomerQueue, String employeeName)
    {
        this.customerQueue = sharedcustomerQueue;
        this.employeeName = employeeName;
    }
//</editor-fold>
    
    @Override
    public void run()
    {
     System.out.println("--------------------------<Employee No "+ employeeName+" is ready>--------------------------");
        
        for(int y = 0; y < CustomerNumber - customerRemoved.size(); y++)
        { 
            
         try
         {
            GetServed(customerQueue, Index, ProductSelected);
         } catch (InterruptedException ex)
         {
            ex.printStackTrace();
         }
         
        }
    
   System.out.println("--------------------------<Employee No "+ employeeName+" has done his >--------------------------");
    }
    
    public void RunCashierThread()
    {
        start();
    }
    
    // Not sure about that
    /*public void WaitThread()
    {   try {
    start();
    customerQueue.wait();
    } catch (InterruptedException ex) {
    Logger.getLogger(Till.class.getName()).log(Level.SEVERE, null, ex);
    }
    }*/
    
    private void GetServed(Queue<String> Queue, Queue<Integer> Index, List<List<String>> customerProduct) throws InterruptedException
    {
        synchronized (Queue)
        {
        while(Queue.isEmpty())
         {       
               System.out.println("\nEmployee is waiting for more customers to enter in the queue " + Thread.currentThread().getName() + " Queue size: " + Queue.size()); Queue.wait();
               
         }
        
        
        System.out.println("\n");
        
        TimeToGetServed(0, 200);        
        Queue<String> productsToBeScanned = new LinkedList<>();
        
        int TempProductsSize = customerProduct.get(Index.peek()).size();
        String AllProductsScanned = "";
        
        for(int y = 0 ; y < TempProductsSize ; y++ )
        {
            String TempProduct = customerProduct.get(Index.peek()).get(y);
            
            TimeToScanProduct(0, 10);
            
            productsToBeScanned.add(TempProduct);
            
            System.out.println("Employee "+employeeName+" scans: " + productsToBeScanned.peek() + " from: " + Queue.peek());
            
             
              AllProductsScanned += productsToBeScanned.peek() + ", ";
            productsToBeScanned.poll();
        }
            DuePrice(this.priceIndex, this.customerPrice);
            PlaceItemsToBag(AllProductsScanned, Queue);   
            TimeToScanProduct(0, 100);
            LeaveQueue(Queue, Index,customerProduct);
            
            
            Queue.notifyAll();
            
        }
    }
    
    private void LeaveQueue(Queue<String> Queue, Queue<Integer> Index, List<List<String>> customerProduct)
    {   
        List<String> removeList = customerProduct.get(Index.peek());
        
         boolean ItemsRemoved = customerProduct.get(Index.peek()).removeAll(removeList);
     
        Index.poll();
        if(ItemsRemoved){System.out.println(Queue.poll() + " Leave the store ");}
        
    }
    
    private void PlaceItemsToBag(String ProductScanned, Queue<String> Queue)
    {
        System.out.println("\n"+Queue.peek()+" places his items to his bag");
       ItemsInBag.add(Queue.peek()+" bought: "+"["+ProductScanned+"]");
        
    }
    
    private void DuePrice(Queue<Integer> priceIndex, List<String> CustomerPrice)
    {
        System.out.println("\n price due: £" + CustomerPrice.get(priceIndex.poll()));
        
    }
    
    /////////////////
    // Return items bought by customers.
    /////////////////
    public List<String> GetItemsBought()
    { return ItemsInBag;}
    
    ///////////////////
    // Takes 3 seconds for a customer to get served
    ///////////////////
    private void TimeToGetServed(int TimeMili, int TimeNano)
    {
        try {
            Thread.sleep(TimeMili, TimeNano);
        } catch (InterruptedException ex) {
            Logger.getLogger(Till.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////
    // Takes 1 second to scan a product.
    ////////////////////
    private void TimeToScanProduct(int TimeMili, int TimeNano)
    {
    try {
                Thread.sleep(TimeMili, TimeNano);
            } catch (InterruptedException ex) {
                Logger.getLogger(Till.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    
    
    
}
