package pl.marekk.ses.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;
import pl.marekk.ses.SesApplication;
import pl.marekk.ses.domain.EmailAssembler;
import pl.marekk.ses.domain.EmailSender;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SesApplication.class)
@IfProfileValue(name = "spring.profiles.active", value = "prod")
public class ProductionSesEmailSenderTest {

  @Autowired
  private EmailSender emailSender;

  @Test
  public void sendMessage() {
    //given
    SimpleMailMessage message = EmailAssembler.sample("marek.kapowicki@gmail.com", "hey")
        .toSimpleMailMessage();
    //when
    emailSender.send(message);

  }
}