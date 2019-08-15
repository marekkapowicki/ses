package pl.marekk.ses.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import pl.marekk.ses.domain.EmailSender;

@Log4j2
@AllArgsConstructor(access = AccessLevel.PACKAGE, staticName = "of")
public class SesEmailSender implements EmailSender {

  private MailSender mailSender;


  @Override
  public void send(SimpleMailMessage message) {
    log.info("Sending email: {}", () -> message);
    mailSender.send(message);
  }
}
