package pl.marekk.ses.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import pl.marekk.ses.domain.Email;
import pl.marekk.ses.domain.EmailFactory;
import pl.marekk.ses.domain.EmailRepresentation;

public class EmailFactoryTest {

  private EmailFactory emailFactory = DefaultEmailFactory.of(() -> "from@gmail.com");

  @Test
  public void createEmail() {
    //when
    Email email = emailFactory.create("marek.kapowicki@gmail.com", "sampleSubject", "sampleText");
    //then
    EmailRepresentation representation = EmailRepresentation.from(email);
    assertThat(representation.from()).isEqualTo("from@gmail.com");
    assertThat(representation.to()).isEqualTo("marek.kapowicki@gmail.com");
    assertThat(representation.subject()).isEqualTo("sampleSubject");
    assertThat(representation.text()).isEqualTo("sampleText");


  }
}