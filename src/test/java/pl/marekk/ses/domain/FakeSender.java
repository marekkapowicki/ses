package pl.marekk.ses.domain;

import lombok.Getter;
import org.springframework.mail.SimpleMailMessage;

public class FakeSender implements EmailSender {

  @Getter
  private SimpleMailMessage messageSent;

  @Override
  public void send(SimpleMailMessage message) {
    messageSent = message;
  }
}
