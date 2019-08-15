package pl.marekk.ses.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailAssembler {

  private String to;
  private String from;
  private String subject;
  private String text;

  public static EmailAssembler sample(String to, String text) {
    return new EmailAssembler(to, "marek.kapowicki@gmail.com", "sns email", text);
  }

  public EmailAssembler sender(String email) {
    this.from = email;
    return this;
  }

  public Email assemble() {
    return Email.of(to, from, subject, text);
  }

  public SimpleMailMessage toSimpleMailMessage() {
    return assemble().toMessage();
  }
}
