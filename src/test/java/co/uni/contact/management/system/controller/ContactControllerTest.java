package co.uni.contact.management.system.controller;

import co.uni.contact.management.system.dto.ContactDTO;
import co.uni.contact.management.system.service.Impl.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactService contactService;

    @Test
    void testSaveContact() {
        ContactDTO contactDTO = new ContactDTO();
        when(contactService.saveContact(contactDTO)).thenReturn(contactDTO);
        ContactDTO result = contactController.saveContact(contactDTO);
        assertNotNull(result);
        assertEquals(contactDTO, result);
        verify(contactService, times(1)).saveContact(contactDTO);
    }

    @Test
    void testFetchAllContacts() {
        ContactDTO contact1 = new ContactDTO();
        ContactDTO contact2 = new ContactDTO();
        List<ContactDTO> contactList = Arrays.asList(contact1, contact2);
        when(contactService.fetchAllContacts()).thenReturn(contactList);
        List<ContactDTO> result = contactController.fetchAllContacts();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(contactService, times(1)).fetchAllContacts();
    }

    @Test
    void testFetchContactById() {
        Long id = 1L;
        ContactDTO contactDTO = new ContactDTO();
        when(contactService.fetchContactById(id)).thenReturn(contactDTO);

        ResponseEntity<ContactDTO> response = contactController.fetchContactById(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(contactDTO, response.getBody());
        verify(contactService, times(1)).fetchContactById(id);
    }

    @Test
    void testDeleteContact() {
        Long id = 1L;
        when(contactService.deleteContact(id)).thenReturn(true);

        ResponseEntity<Map<String, Boolean>> response = contactController.deleteContact(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().get("Contact deleted successfully"));
        verify(contactService, times(1)).deleteContact(id);
    }

    @Test
    void testUpdateContact() {
        Long id = 1L;
        ContactDTO contactDTO = new ContactDTO();
        when(contactService.updateContact(id, contactDTO)).thenReturn(contactDTO);

        ResponseEntity<ContactDTO> response = contactController.updateContact(id, contactDTO);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(contactDTO, response.getBody());
        verify(contactService, times(1)).updateContact(id, contactDTO);
    }
}
