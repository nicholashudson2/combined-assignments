package com.cooksys.ftd.assignments.socket.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.cooksys.ftd.assignments.socket.Utils;

public class XMLCreator {

	public static void main(String[] args) {
		LocalConfig localConfig = new LocalConfig();
		localConfig.setPort(4455);
		
		RemoteConfig remoteConfig = new RemoteConfig();
		remoteConfig.setPort(4455);
		remoteConfig.setHost("localhost");
		
		Config config = new Config();
		config.setStudentFilePath("C://Users/ftd-18/code/combined-assignments/4-socket-io-serialization/config/student.xml");
		config.setRemote(remoteConfig);
		config.setLocal(localConfig);
		
		Student student = new Student();
		student.setFirstName("Nicholas");
		student.setLastName("Hudson");
		student.setFavoriteIDE("IntelliJ");
		student.setFavoriteLanguage("Java");
		student.setFavoriteParadigm("Object-Oriented");
		
		try {
			JAXBContext context = Utils.createJAXBContext();
			File studentXml = new File("C://Users/ftd-18/code/combined-assignments/4-socket-io-serialization/config/student.xml");
			FileWriter studentOutput = new FileWriter(studentXml);
			Marshaller studentMarshaller = context.createMarshaller();
			studentMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			studentMarshaller.marshal(student, studentOutput);
			studentOutput.close();
			
			File configXml = new File("C://Users/ftd-18/code/combined-assignments/4-socket-io-serialization/config/config.xml");
			FileWriter configOutput = new FileWriter(configXml);
			Marshaller configMarshaller = context.createMarshaller();
			configMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			configMarshaller.marshal(config, configOutput);
			configOutput.close();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
}
