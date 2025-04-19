package co.uni.contact.management.system.service.Impl;

import co.uni.contact.management.system.dto.ContactDTO;
import co.uni.contact.management.system.entity.ContactEntity;
import co.uni.contact.management.system.repository.ContactRepository;
import co.uni.contact.management.system.service.IContactService;
import co.uni.contact.management.system.utils.mappers.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService implements IContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ContactDTO saveContact(ContactDTO contact) {
        ContactEntity entity = contactMapper.dtoToEntity(contact);

        if (contact.getPassword() != null && !contact.getPassword().isBlank()) {
            String hashedPassword = passwordEncoder.encode(contact.getPassword());
            entity.setHashPassword(hashedPassword);
        } else {
            throw new RuntimeException("Password is required to create a contact");
        }

        ContactEntity savedEntity = contactRepository.save(entity);
        ContactDTO savedDTO = contactMapper.entityToDto(savedEntity);
        savedDTO.setHashPassword(savedEntity.getHashPassword());
        return savedDTO;
    }

    @Override
    public List<ContactDTO> fetchAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(contactMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO fetchContactById(Long id) {
        Optional<ContactEntity> contactOpt = contactRepository.findById(id);
        return contactOpt.map(contactMapper::entityToDto)
                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + id));
    }

    @Override
    public boolean deleteContact(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ContactDTO updateContact(Long contactId, ContactDTO contact) {
        Optional<ContactEntity> contactOpt = contactRepository.findById(contactId);
        if (contactOpt.isPresent()) {
            ContactEntity existingContact = contactOpt.get();
            existingContact.setFirstName(contact.getFirstName());
            existingContact.setLastName(contact.getLastName());
            existingContact.setEmailAddress(contact.getEmailAddress());
            existingContact.setPhone(contact.getPhone());
            existingContact.setAddress(contact.getAddress());
            existingContact.setBirthDate(contact.getBirthDate());


            ContactEntity updatedEntity = contactRepository.save(existingContact);
            return contactMapper.entityToDto(updatedEntity);
        } else {
            throw new RuntimeException("Contact not found with ID: " + contactId);
        }
    }
}
