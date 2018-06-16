package org.gestion;

import java.util.Date;

import org.gestion.dao.*;
import org.gestion.modele.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AddEmployeeDemo {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AddEmployeeDemo.class, args);

		PersonRepository personRepo = context.getBean(PersonRepository.class);
		ClientRepository clientRepo = context.getBean(ClientRepository.class);
		DoctorRepository doctorRepo = context.getBean(DoctorRepository.class);
		
		Client client = new Client("John", "Doe", "John.doe@gmail.com","img");
		Doctor doctor = new Doctor("Ryad", "Ali-Bey", "ryad.ali-bey@gmail.com","img", 95000);
		
		clientRepo.save(client);
		doctorRepo.save(doctor);

		// Page<Etudiant> etudiants = etRepo.findAll(new PageRequest(0, 5));
		// Page<Etudiant> etudiants = etRepo.chercherEtudiants("%m%", new
		// PageRequest(0,5));
		// etudiants.forEach(e->System.out.println(e.getNom()));

	}

}
