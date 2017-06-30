package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by Steve on 2017/7/1.
 */

@Controller
@RequestMapping("/")
public class ContactController
{
    private ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactRepository contactRepo)
    {
        this.contactRepository = contactRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String, Object> model)
    {
        List<Contact> contacts = contactRepository.findAll();
        model.put("contacts", contacts);
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Contact contact)
    {
        contactRepository.save(contact);
        return "redirect:/";
    }
}
