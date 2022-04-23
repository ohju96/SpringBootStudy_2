package mongodb.mongo.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MailController {
    private final MailService mailService;

    @GetMapping("/email")
    public String dispMail() {
        return "email";
    }

    @PostMapping("/email")
    public void execMail(MailDto mailDto) {
        mailService.mailSend(mailDto);
    }
}
