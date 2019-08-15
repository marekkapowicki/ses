package pl.marekk.ses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;

public class EmailTest {

  @Test
  public void useSender() {
    //given
    Email email = EmailAssembler.sample("marek.kapowicki@gmail.com", "hello").assemble();
    FakeSender sender = new FakeSender();

    //when
    email.send(sender);
    //then
    SimpleMailMessage messageSent = sender.messageSent();
    assertThat(messageSent.getTo())
        .hasSize(1)
        .containsExactly("marek.kapowicki@gmail.com");
    assertThat(messageSent.getText()).isEqualTo("hello");
  }
}