package com.addresbook.CONTROLLER;

import com.addresbook.DAO.ContactDAO;
import com.addresbook.DAO.ContactDaoSQL;
import com.addresbook.MODEL.Requests.ContactAddRequestModel;
import com.addresbook.service.CsvService;
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
    @CrossOrigin()
    @PostMapping("/contacts")
    public void addContact(@RequestBody ContactAddRequestModel contact) {
        for (ContactAddRequestModel cont : contacts) {
            if (contacts.contains(cont)) {
                System.out.println("This contact already exists.");
            }
        }
        cd.addContact(contact);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/contacts/{contactName}")
    public void deleteContact(@PathVariable("contactName") String name) {

        cd.deleteContact(name);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PatchMapping("/contacts/{name}")
    public void updateContact(@RequestBody ContactAddRequestModel contactInfo,
                              @PathVariable("name") String name) {
        for (ContactAddRequestModel cont : contacts) {
            if (!contacts.contains(cont)) {
                System.out.println("This contact do not exists.");
            }
        }
        cd.updateContact(contactInfo, name);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/contacts/{name}")

    public ContactAddRequestModel getContact(@PathVariable("name") String contactName) {
        return cd.getContact(contactName);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/contacts/search")

    public List<ContactAddRequestModel> searchContactsByName(@RequestParam String contactName) {
        return cd.allContactSearchByName(contactName);
    }

    @Autowired
    private CsvService csvService;

//    @RequestMapping("/")
//    public String index(){
//        return "index";
//    }

    @RequestMapping("/download/contact.csv")
    public void downloadCsvFile(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=contact.csv");
        csvService.downloadCvsFile(response.getWriter(), contacts);
    }
}
