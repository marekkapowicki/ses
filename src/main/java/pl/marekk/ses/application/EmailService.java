package pl.marekk.ses.application;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.marekk.ses.domain.Email;
import pl.marekk.ses.domain.EmailFactory;
import pl.marekk.ses.domain.EmailSender;

@AllArgsConstructor(access = AccessLevel.PACKAGE, staticName = "of")
public class EmailService {

  private final EmailFactory emailFactory;
  private final EmailSender emailSender;

  public void send(SendEmailCommand command) {
    Email email = emailFactory.create(command.to(), command.subject(), command.text());
    email.send(emailSender);
  }
}
