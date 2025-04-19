package co.uni.contact.management.system.controller;

import co.uni.contact.management.system.dto.ContactDTO;
import co.uni.contact.management.system.service.Impl.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/saveContact")
    public ContactDTO saveContact(@RequestBody ContactDTO contact) {
        return contactService.saveContact(contact);
    }

    @GetMapping("/fetchContacts")
    public List<ContactDTO> fetchAllContacts() {
        return contactService.fetchAllContacts();
    }

    @GetMapping("/fetchUserById/{id}")
    public ResponseEntity<ContactDTO> fetchContactById(@PathVariable("id") Long id) {
        ContactDTO contact = contactService.fetchContactById(id);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/deleteContactBy/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteContact(@PathVariable("id") Long id) {
        boolean deleted = false;
        deleted = contactService.deleteContact(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Contact deleted successfully", deleted = true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateContact/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable("id") Long id, @RequestBody ContactDTO contact) {
        contact = contactService.updateContact(id, contact);
        return ResponseEntity.ok(contact);
    }
}
