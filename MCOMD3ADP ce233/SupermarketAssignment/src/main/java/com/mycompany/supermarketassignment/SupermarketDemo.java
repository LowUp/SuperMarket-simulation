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
                                                                                                        
public class SupermarketDemo {
    
    // <editor-fold defaultstate="collapsed" desc=" SupermarketDemo Methods">  
    
     public static void main(String[] args) { 
       new SupermarketDemo(); 
    }
     
     /////////////////////////////// 
     // Private so it can not be used outside of this class
     ///////////////////////////////
    private SupermarketDemo()
     { 
      SupermarketView view = new SupermarketView();   
         
     SupermarketModel data = new SupermarketModel();     
     
     SupermarketController controller = new SupermarketController(data, view); 
     
     controller.StartSimulation();
       
     
     }
    
    
     
    
     // </editor-fold>
}
