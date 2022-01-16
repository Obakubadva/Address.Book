package com.addresbook.DAO;

import com.addresbook.MODEL.Requests.ContactAddRequestModel;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ContactDAO {

    void addContact(ContactAddRequestModel contact);
    void deleteContact(String name);
    void updateContact(ContactAddRequestModel contact, String name);
   ContactAddRequestModel getContact(String name);
   List<ContactAddRequestModel> allContactSearchByName(String name);
    List<ContactAddRequestModel> allContact();
}
