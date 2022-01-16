package com.addresbook.service;

import com.addresbook.MODEL.Requests.ContactAddRequestModel;

import java.io.PrintWriter;
import java.util.List;

public interface CsvService {
    public void downloadCvsFile(PrintWriter printWriter, List<ContactAddRequestModel> contacts);
}
