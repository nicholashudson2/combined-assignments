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
		config.setStudentFilePath("config/student.xml");
		config.setRemote(remoteConfig);
		config.setLocal(localConfig);
		
		Student student = new Student();
		student.setFirstName("Nicholas");
		student.setLastName("Hudson");
		student.setFavoriteIDE("IntelliJ");
		student.setFavoriteLanguage("Java");
		student.setFavoriteParadigm("Functional");
		
		try {
			JAXBContext context = Utils.createJAXBContext();
			File studentXml = new File("config/student.xml");
			FileWriter studentOutput = new FileWriter(studentXml);
			// Creates marshaller and marshalls student object to xml file.
			Marshaller studentMarshaller = context.createMarshaller();
			studentMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			studentMarshaller.marshal(student, studentOutput);
			studentOutput.close();
			System.out.println("student.xml file created.");
			
			File configXml = new File("config/config.xml");
			FileWriter configOutput = new FileWriter(configXml);
			// Creates marshaller, and marshalls config object to xml file.
			Marshaller configMarshaller = context.createMarshaller();
			configMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			configMarshaller.marshal(config, configOutput);
			configOutput.close();
			System.out.println("config.xml file created.");
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
}
