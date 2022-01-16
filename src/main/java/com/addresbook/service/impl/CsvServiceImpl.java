package com.addresbook.service.impl;

import com.addresbook.MODEL.Requests.ContactAddRequestModel;
import com.addresbook.service.CsvService;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;
@Service
public class CsvServiceImpl implements CsvService {
    @Override
    public void downloadCvsFile(PrintWriter printWriter, List<ContactAddRequestModel> contacts) {
        printWriter.write("Picture, Contact name, Contact address\n");
        for (ContactAddRequestModel contact : contacts){
            printWriter.write(contact.getPicture() + "," + contact.getName() + "," + contact.getAddress()+ "\n");
        }
    }
}
