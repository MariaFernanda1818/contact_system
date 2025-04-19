package co.uni.contact.management.system.service;

import co.uni.contact.management.system.dto.ContactDTO;

import java.util.List;

public interface IContactService {

    ContactDTO saveContact(ContactDTO contact);

    List<ContactDTO> fetchAllContacts();

    ContactDTO fetchContactById(Long id);

    boolean deleteContact(Long id);

    ContactDTO updateContact(Long id, ContactDTO contact);

}
