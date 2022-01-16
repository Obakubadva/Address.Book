package com.addresbook.CONTROLLER;

import com.addresbook.DAO.ContactDAO;
import com.addresbook.DAO.ContactDaoSQL;
import com.addresbook.MODEL.Requests.ContactAddRequestModel;
import com.addresbook.service.CsvService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ContactCONTROLLER {

    public static final ContactDAO cd = new ContactDaoSQL();
    static List<ContactAddRequestModel> contacts = cd.allContact();

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/contacts")
    public List<ContactAddRequestModel> getAllContacts() {
        return contacts;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/contacts")
    public void addContact(@RequestBody ContactAddRequestModel contact) {
        cd.addContact(contact);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/contacts/{contactName}")
    public void deleteContact(@PathVariable("contactName") String name) {
        cd.deleteContact(name);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/contacts/{name}")
    public void updateContact(@RequestBody ContactAddRequestModel contactInfo,
                              @PathVariable("name") String name) {
        cd.updateContact(contactInfo, name);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/contacts/{name}")

    public ContactAddRequestModel getContact(@PathVariable("name") String contactName) {
        return cd.getContact(contactName);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/contacts/search")

    public ContactAddRequestModel searchContactsByName(@RequestParam String contactName) {
        for (ContactAddRequestModel contact: contacts) {
            if (contact.getName().equals(contactName)){
                return cd.getContact(contactName);
            }
        }
        return null;
    }

    @Autowired
    private CsvService csvService;

    @RequestMapping("/download/contact.csv")
    public void downloadCsvFile(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=contact.csv");
        csvService.downloadCvsFile(response.getWriter(), contacts);
    }
}
