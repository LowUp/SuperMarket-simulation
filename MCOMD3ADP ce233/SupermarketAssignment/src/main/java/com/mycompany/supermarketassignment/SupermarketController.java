/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.supermarketassignment;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class SupermarketController{
      
    private SupermarketModel model;
    private SupermarketView view;
    
    // <editor-fold defaultstate="collapsed" desc="SupermarketController constructor">  
    
    public SupermarketController(SupermarketModel model, SupermarketView view)
    {
        //Pass values by reference
     this.model = model;
     this.view = view;
    
    }
    
    // </editor-fold>
 
    
   // <editor-fold defaultstate="collapsed" desc="SupermarketController Methods">  
    
    
  
    public void StartSimulation() 
    {
       SimulationStart();
        
       if(CustomersInStore())
       {
        
        CreationOfCustomers();
                 
        EnterInTheStore();
        
        StartShopping();
            
        RemoveSpecificCustomers();  
        
        ShowProductSelectedByCustomers();
        
        ShowRemovedCustomers();
        
        TillsOpening();
        
        Report();
        
        SimulationEnd();
       }
       else
       {SimulationEnd();}
        
        
}
          
    
    private void CreationOfCustomers() {view.CreationOfCustomers();  model.RunCustomerThread();}
    
    private void EnterInTheStore() { view.customersEnter(model.GetCustomers());}
    
    private void StartShopping() { view.customersStartShopping(); model.RunShoppingThread();}
    
    private void ShowProductSelectedByCustomers() {view.customersChoices(model.GetCustomers(), model.GetAllProductSelected(), model.GetAllAisleVisited());}
    
    private void RemoveSpecificCustomers(){ model.RemoveSpecificCustomers();}
    
    private void ShowRemovedCustomers() {view.CustomersRemoved(model.CustomerRemovedName());}
    
    private void TillsOpening() {view.TillsOpening();model.TillNo1();}
    
    private void Report() {view.ShowReport(model.GetNumberOfCustomers(), model.GetBag(), model.GetOverallPrice());}
    
    private Boolean CustomersInStore()
    {
        boolean flag = false;
        
        if(model.GetNumberOfCustomersInInteger()> 0)
        {
        flag = true;
        }
        
        return flag;
    }
    
    private void SimulationEnd() {view.SimulationEnd();}
    
    private void SimulationStart() {view.SimulationStart();}
    
   
    
    // </editor-fold>
}
