package pl.marekk.ses.domain;

import org.springframework.mail.SimpleMailMessage;

@FunctionalInterface
public interface EmailSender {

  void send(SimpleMailMessage message);
}
