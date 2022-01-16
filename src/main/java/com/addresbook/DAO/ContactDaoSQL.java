package com.addresbook.DAO;

import com.addresbook.DB.DatabaseConnection;
import com.addresbook.MODEL.Requests.ContactAddRequestModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactDaoSQL implements ContactDAO{

    private static final Connection conn = DatabaseConnection.getConnection();

    @Override
    public void addContact(ContactAddRequestModel contact) {

        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO contact VALUES (?, ?, ?)");
            st.setString(1, contact.getPicture());
            st.setString(2, contact.getName());
            st.setString(3, contact.getAddress());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteContact(String name) {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM contact WHERE name = ?");

            st.setString(1, name);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContact(ContactAddRequestModel contact, String name) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE contact SET picture = ?, " +
                    "name = ?, " +
                    "address = ? " +
                    "WHERE name = ?");
            st.setString(4, name );
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM contact WHERE name = '" + name + "'");

            if (rs.next()) {

                if (contact.getPicture().isEmpty())
                    st.setString(1, rs.getString(1));
                else
                    st.setString(1, contact.getPicture());
                if (contact.getName().isEmpty())
                    st.setString(2, rs.getString(2));
                else
                    st.setString(2, contact.getName());
                if (contact.getAddress().isEmpty())
                    st.setString(3, rs.getString(3));
                else
                    st.setString(3, contact.getAddress());

            }

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContactAddRequestModel getContact(String name) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("SELECT * FROM contact WHERE name = ?");
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            rs.next();
            ContactAddRequestModel contact = new ContactAddRequestModel(rs.getString(1),
                    rs.getString(2), rs.getString(3));

            return contact;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ContactAddRequestModel> allContactSearchByName(String name) {
        List<ContactAddRequestModel> contacts = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM contact WHERE name LIKE '%" + name + "%'");

            while (rs.next()){
                contacts.add(new ContactAddRequestModel(rs.getString(1),
                rs.getString(2),
                rs.getString(3)));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public List<ContactAddRequestModel> allContact() {
        List<ContactAddRequestModel> contacts = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM contact");

            while (rs.next()){
                contacts.add(new ContactAddRequestModel(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }


}
