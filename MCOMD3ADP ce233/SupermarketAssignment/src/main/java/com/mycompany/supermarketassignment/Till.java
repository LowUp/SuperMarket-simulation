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
//import java.util.Queue;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Till extends Thread{
    
    private List<String> CustomerNames = CustomerClass.listOfCustomers;
    private List<List<String>> ProductSelected = ShoppingClass.listOfAllProductSelected;
    private final int CustomerNumber = SupermarketModel.NumberOfCustomer;   
    private final List<String> customerRemoved = ShoppingClass.CustomerRemoved;
    
    
    private final Queue<String> customerQueue;
    private int QueueSize;
    private String queueName;
    
    public static Queue<Integer> ProductSelectedIndex = new LinkedList<Integer>();
    private Queue<Integer> customerIndex = new LinkedList<Integer>();
    public static Queue<Integer> priceIndex = new LinkedList<Integer>();
    
   
    
    
  // <editor-fold defaultstate="collapsed" desc=" Till Contructor"> 
    
    public Till(int QueueSize, String queueName, Queue<String> sharedCustomerQueue)
    {
    this.QueueSize = QueueSize;
    this.queueName = queueName;
    this.customerQueue = sharedCustomerQueue;
    }
   
    //   </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Till methods"> 
    @Override
    public void run()
    {
       System.out.println("--------------------------------<till "+ queueName +" open>---------------------------------");
       
       for(int y = 0 ; y < CustomerNumber - customerRemoved.size(); y++ )
       {
       
         try
         {
            AddToQueue(customerQueue,CustomerNames);
         } 
         catch (InterruptedException ex)
         {
            ex.printStackTrace();
         }
        
       }
       
       System.out.println("-------------------------------------<till "+ queueName +" closes>--------------------------------------");
       
    }
    
    public void RunTillThread()
    {
        start();
    }
    
      
    private void AddToQueue(Queue<String> customerQueue , List<String> CustomerNames) throws InterruptedException
    {
        synchronized (customerQueue)
        {
         while(FullQueue())
         {               
         System.out.println("\nQueue is full " + Thread.currentThread().getName() + " is waiting for cashier to serve customers, size: " + customerQueue.size()); customerQueue.wait();
         
         }
               
        if(FullBasket())
         {
            
             System.out.println("\n");
             TimeToEnterInQueue(0, 100);
             
             customerQueue.add(CustomerNames.get(customerIndex.peek()));     
             
             System.out.println(CustomerNames.get(customerIndex.poll()) + " Joins Queue " + queueName);  
             System.out.println("Queue "+queueName+" "+ customerQueue); 
                     
             customerQueue.notifyAll();
     
         }
        else{}
 
        }
        
        
    }
    

    ////////////////////
    // Check if the queue is full.
    ////////////////////
    private boolean FullQueue()
    {
        if(customerQueue.size() >= QueueSize) {return true;}
        else {return false;}
           
    }
    
    //////////////////////
    // Check if the customer basket is not empty
    ////////////////////// 
    private boolean FullBasket()
    {
        boolean flag = false;
     for(int y = 0 ; y < CustomerNumber ; y++ ) 
     {
     if(ProductSelected.get(y).isEmpty() == false)
     {flag = true; ProductSelectedIndex.add(y); customerIndex.add(y); priceIndex.add(y);}
         
      }
     return flag;
    }
    
    ///////////////////
    // Takes 2 seconds for a customer to join a queue
    ///////////////////
    private void TimeToEnterInQueue(int TimeMili, int TimeNano)
    {    
        try {
            Thread.sleep(TimeMili, TimeNano);
        } catch (InterruptedException ex) {
            Logger.getLogger(Till.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
        
//   </editor-fold>    
}
