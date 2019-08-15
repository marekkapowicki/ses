package pl.marekk.ses.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.mail.SimpleMailMessage;

@AllArgsConstructor(staticName = "of")
@Getter(value = AccessLevel.PACKAGE)
public class Email {

  private final String to;
  private final String from;
  private final String subject;
  private final String text;


  SimpleMailMessage toMessage() {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setFrom(from);
    message.setSubject(subject);
    message.setText(text);
    return message;
  }

  public void send(EmailSender sender) {
    sender.send(this.toMessage());
  }

}
