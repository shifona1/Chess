/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychess;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author shifona
 * This code has been written keeping in mind the client mainly 
 */
public class Connection 
{
    String host;
    final static int port=6000;
    Socket socket;
    boolean isServer=false;
    OutputStreamWriter osw;
    BufferedReader br;
    MyChess chess;

    public Connection(MyChess chess) throws IOException 
    {
        this.chess=chess;
        JPanel panel = new JPanel();
        panel.add(new JLabel("What will you like to be. \n Please make a selection:"));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Server");
        model.addElement("Client");
        JComboBox comboBox = new JComboBox(model);
        panel.add(comboBox);
        int result = JOptionPane.showConfirmDialog(null, panel, "Player mode", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
         String selected=(String) comboBox.getSelectedItem();
         if(selected.equals("Server")) isServer=true;else isServer=false;
         if(isServer)
         {
             ServerSocket serversocket=new ServerSocket(port);
             System.out.println("Server has started");
             socket=serversocket.accept();
             
         }
         else
         {
             host = JOptionPane.showInputDialog("Enter the name of the server");
             //port = Integer.parseInt(JOptionPane.showInputDialog("Enter the port of the server"));
              InetAddress address = InetAddress.getByName(host);
     
             /** Establish a socket connection */
               socket = new Socket(address, port);
            
             
         }
               System.out.println("Connected : "+connect());
         
        
        
    }
    
    public boolean connect()
{

    try {
      /** Obtain an address object of the server */
     
      /** Instantiate a BufferedOutputStream object */
      BufferedOutputStream bos = new BufferedOutputStream(socket.
          getOutputStream());
      br = new BufferedReader( new InputStreamReader(socket.getInputStream()));
      //socket.setSoTimeout(500);

      /** Instantiate an OutputStreamWriter object with the optional character
       * encoding.
       */
      osw = new OutputStreamWriter(bos, "US-ASCII");

      
      ReceiveMsg();
      return true;
      
        }
    
    catch (Exception g) {
      System.out.println("Exception: " + g);
      
        }
    return false;
   }
 
   void sendMsg(String msg)
   {
       try
  	{
	  	osw.write(msg+"\n");
	  	osw.flush();
   	} catch(IOException e) {
  		System.out.println(e.toString());
  	}
   }
   
   void ReceiveMsg() throws IOException
   {
       new Thread("Received") {

           @Override
           public void run() {
                char r;
                 while(true){

                             String out=null;
                    try {
                        out = br.readLine().trim();
                    } catch (IOException ex) {
                        continue;
                    }
                             if(out.isEmpty())
                                 continue;


                              String[] coords=out.split(" ");
                              int prevx=Integer.parseInt(coords[0]);

                              int prevy=Integer.parseInt(coords[1]);
                              int newx=Integer.parseInt(coords[2]);
                              int newy=Integer.parseInt(coords[3]);
                              chess.move(prevx,prevy,newx,newy);   

                            }
             }  
           
       }.start();
       
      		
        
   }
}    
