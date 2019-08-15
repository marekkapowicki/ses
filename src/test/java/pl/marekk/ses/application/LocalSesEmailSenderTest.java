package pl.marekk.ses.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.VerifyEmailIdentityRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.localstack.LocalStackContainer;
import pl.marekk.ses.domain.EmailAssembler;
import pl.marekk.ses.domain.EmailSender;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LocalSesConfiguration.class, EmailConfiguration.class})
public class LocalSesEmailSenderTest {

  public static final String SENDER = "email@example.com";
  @Autowired
  private EmailSender emailSender;

  @Autowired
  LocalStackContainer localStackContainer;

  @SpyBean
  private AmazonSimpleEmailService ses;

  @Before
  public void setUp() throws Exception {
    ses.verifyEmailIdentity(new VerifyEmailIdentityRequest()
        .withEmailAddress(SENDER));
  }

  @Test
  public void sendMessage() {
    //given
    SimpleMailMessage message = EmailAssembler
        .sample("marek.kapowicki@gmail.com", "hey")
        .sender(SENDER)
        .toSimpleMailMessage();
    //when
    emailSender.send(message);

    //then
    verify(ses)
        .sendEmail(any());

  }

  @After
  public void tearDown() throws Exception {
    localStackContainer.stop();
  }

}