package com.addresbook;

import com.addresbook.DAO.ContactDaoSQL;
import com.addresbook.MODEL.Requests.ContactAddRequestModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressBookApplication.class, args);

		ContactDaoSQL dao = new ContactDaoSQL();

	}

}
