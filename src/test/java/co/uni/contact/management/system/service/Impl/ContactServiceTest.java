package co.uni.contact.management.system.service.Impl;

import co.uni.contact.management.system.dto.ContactDTO;
import co.uni.contact.management.system.entity.ContactEntity;
import co.uni.contact.management.system.repository.ContactRepository;
import co.uni.contact.management.system.utils.mappers.ContactMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    @Spy
    private ContactMapper contactMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test void fetchContactByIdExitoso() {
        Long id = 1L;
        ContactEntity entity = new ContactEntity();
        ContactDTO contactDTO = new ContactDTO();
        when(contactRepository.findById(id)).thenReturn(Optional.of(entity));
        when(contactMapper.entityToDto(entity)).thenReturn(contactDTO);
        ContactDTO result = contactService.fetchContactById(id);
        assertNotNull(result);
        verify(contactRepository, times(1)).findById(id);
        verify(contactMapper, times(1)).entityToDto(entity);
    }


    @Test
    void fetchAllContactsFallido() {
        when(contactRepository.findAll()).thenThrow(new RuntimeException("Error simulado"));
        assertThrows(RuntimeException.class, () -> contactService.fetchAllContacts());
        verify(contactRepository, times(1)).findAll();
    }

    @Test
    void fetchContactByIdFallido() {
        Long id = 1L;
        when(contactRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> contactService.fetchContactById(id));
        assertEquals("Contact not found with ID: " + id, exception.getMessage());
        verify(contactRepository, times(1)).findById(id);
    }

    @Test
    void deleteContactExitoso() {

        Long id = 1L;
        when(contactRepository.existsById(id)).thenReturn(true);


        boolean deleted = contactService.deleteContact(id);

        assertTrue(deleted);
        verify(contactRepository, times(1)).existsById(id);
        verify(contactRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteContactFallido() {

        Long id = 1L;
        when(contactRepository.existsById(id)).thenReturn(false);

        boolean deleted = contactService.deleteContact(id);

        assertFalse(deleted);
        verify(contactRepository, times(1)).existsById(id);
        verify(contactRepository, never()).deleteById(id);
    }

    @Test
    void updateContactExitoso() {
        Long id = 1L;
        ContactEntity entity = new ContactEntity();
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName("Sofia");
        contactDTO.setLastName("Salgado");

        when(contactRepository.findById(id)).thenReturn(Optional.of(entity));
        when(contactRepository.save(any(ContactEntity.class))).thenReturn(entity);
        when(contactMapper.entityToDto(any())).thenReturn(new ContactDTO());

        ContactDTO updatedContact = contactService.updateContact(id, contactDTO);

        assertNotNull(updatedContact);
        verify(contactRepository, times(1)).findById(id);
        verify(contactRepository, times(1)).save(any(ContactEntity.class));
        verify(contactMapper, times(1)).entityToDto(any());
    }


    @Test
    void updateContactFallido() {
        Long id = 1L;
        ContactDTO contactDTO = new ContactDTO();
        when(contactRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> contactService.updateContact(id, contactDTO));
        assertEquals("Contact not found with ID: " + id, exception.getMessage());
        verify(contactRepository, times(1)).findById(id);
        verify(contactRepository, never()).save(any(ContactEntity.class));
    }
}
