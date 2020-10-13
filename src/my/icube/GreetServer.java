// import java.net.*;
// import java.io.*;

// public class GreetServer {
// 	private ServerSocket serverSocket;
// 	private Socket clientSocket;
// 	private PrintWriter out;
// 	private BufferedReader in;

// 	public void start(int port){
// 		serverSocket = new ServerSocket(port);
// 		clientSocket = serverSocket.accept();
// 		out = new PrintWriter(clientSocket.getOutputStream(), true);
// 		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
// 		String greeting = in.readLine();
// 			if ("Hello server".equals(greeting)) {
// 				System.out.println("Hello Client");
// 			}
// 			else
// 			{
// 				System.out.println("Who dis?");
// 			}
// 	}

// 	public void stop(){
// 		in.close();
// 		out.close();
// 		clientSocket.close();
// 		serverSocket.close();
// 	}

// 	public static void main(String[] args){
// 		GreetServer server = new GreetServer();
// 		server.start(6666);
// 	}
// }
package my.icube;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */
public class GreetServer {
    
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    private static void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public GreetServer() throws IOException, ClassNotFoundException{
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject("Message Received:  "+message);
            //close resources
            ois.close();
            oos.close();
            socket.close();
            //terminate the server if client sends exit request
            if(message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        initComponents();
        
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static class setVisible {

        public setVisible(boolean b) {
        }
    }
    
}