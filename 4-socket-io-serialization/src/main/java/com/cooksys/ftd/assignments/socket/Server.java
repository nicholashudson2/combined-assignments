package com.cooksys.ftd.assignments.socket;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Server extends Utils {

	/**
	 * Reads a {@link Student} object from the given file path
	 *
	 * @param studentFilePath
	 *            the file path from which to read the student config file
	 * @param jaxb
	 *            the JAXB context to use during unmarshalling
	 * @return a {@link Student} object unmarshalled from the given file path
	 * @throws JAXBException
	 */
	public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException {
		File file = new File(studentFilePath);
		Unmarshaller unmarshaller = jaxb.createUnmarshaller();
		Student unmarshalledStudent = (Student) unmarshaller.unmarshal(file);
		return unmarshalledStudent;
	}

	/**
	 * The server should load a
	 * {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
	 * <project-root>/config/config.xml path, using the "port" property of the
	 * embedded {@link com.cooksys.ftd.assignments.socket.model.LocalConfig}
	 * object to create a server socket that listens for connections on the
	 * configured port.
	 *
	 * Upon receiving a connection, the server should unmarshal a
	 * {@link Student} object from a file location specified by the config's
	 * "studentFilePath" property. It should then re-marshal the object to xml
	 * over the socket's output stream, sending the object to the client.
	 *
	 * Following this transaction, the server may shut down or listen for more
	 * connections.
	 * 
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static void main(String[] args) {
		
		// Declaring variables.
		String configFilePath = "C://Users/ftd-18/code/combined-assignments/4-socket-io-serialization/config/config.xml"; 
		JAXBContext context;
		Config importConfig;
		ServerSocket socket;
		Socket clientSocket;

		try {
			context = Utils.createJAXBContext(); // Create context
			importConfig = Utils.loadConfig(configFilePath, context); // Load Config object
			socket = new ServerSocket(importConfig.getLocal().getPort()); // Instantiate ServerSocket on local port
			clientSocket = socket.accept(); // Instantiate socket for incoming client connection.
			
			// Creates marshaller, marshalls student object to xml file, sends to client via output stream.
			context.createMarshaller().marshal(loadStudent(importConfig.getStudentFilePath(), context), clientSocket.getOutputStream());
			
			// Closing sockets
			socket.close();
			clientSocket.close();
		} catch (IOException e) { // Exception catching.
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
