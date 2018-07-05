/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocr_3;

/**
 *
 * @author Hazel Cavite
 */


import java.awt.*;  
class test extends Frame{  
  test(){  
    Button b=new Button("click me");  
    b.setBounds(30,100,80,30); // setting button position  
    add(b);             //adding button into frame  
    setSize(300,300);//frame size 300 width and 300 height  
    setLayout(null);//no layout manager  
    setVisible(true);//now frame will be visible, by default not visible  
  }  
  public static void main(String args[]){  
    test f=new test();  
  }
}


