package com.cooksys.ftd.assignments.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.cooksys.ftd.assignments.socket.model.RemoteConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client {

	/**
     * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
     * a {@link Server} listening on the given host and port.
     *
     * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
     * over the socket as xml, and should unmarshal that object before printing its details to the console.
     * @throws JAXBException 
	 * @throws IOException 
	 * @throws UnknownHostException 
     */
    public static void main(String[] args) {
    	JAXBContext context;
		try {
			context = Utils.createJAXBContext();
			// Loads remote config data from config.xml file.
			RemoteConfig remoteConfig = Utils.loadConfig("config/config.xml", context).getRemote();
			InputStream input;
			
			try(Socket socket = new Socket(remoteConfig.getHost(), remoteConfig.getPort())) {
				input = socket.getInputStream();
				System.out.println("Connected. Awaiting file...");
				// Creates unmarshaller and unmarshals student object from student.xml file.
				Student unmarshalledStudent = (Student)context.createUnmarshaller().unmarshal(input);
				System.out.println("File received...");
				System.out.println(unmarshalledStudent.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
    	
    }
}
