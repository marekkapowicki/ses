package pl.marekk.ses.application;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import pl.marekk.ses.domain.EmailFactory;
import pl.marekk.ses.domain.EmailSender;
import pl.marekk.ses.domain.FakeSender;

public class EmailServiceTest {

  private EmailFactory emailFactory = DefaultEmailFactory.of(() -> "from@gmail.com");
  private EmailSender emailSender = new FakeSender();

  private EmailService emailService = EmailService.of(emailFactory, emailSender);

  @Test
  public void sendMessage() {
    //given
    SendEmailCommand sendEmailCommand = SendEmailCommand.builder()
        .subject("someSubject")
        .text("someText")
        .to("mk@gmail.com").build();
    //when
    emailService.send(sendEmailCommand);
    //then
    SimpleMailMessage result = ((FakeSender) emailSender).messageSent();

    //then
    assertThat(result.getSubject()).isEqualTo("someSubject");
    assertThat(result.getFrom()).isEqualTo("from@gmail.com");
    assertThat(result.getTo()).hasSize(1)
        .containsExactly("mk@gmail.com");
    assertThat(result.getText()).isEqualTo("someText");

  }

}