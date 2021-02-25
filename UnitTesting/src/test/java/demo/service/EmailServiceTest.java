package demo.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    private EmailService emailService;
    private Order order;

    EmailServiceTest(){
        emailService = new EmailService();
    }
    @Test
    void shouldBeInstanceOfEmailService(){
        assertThat(emailService, instanceOf(EmailService.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Runtime exception", "Runtime", "exception"})
    public void whenExceptionThrown_thenAssertionSucceeds(String expectedMessage){
        Exception exception = assertThrows(RuntimeException.class, () ->{
           emailService.sendEmail((demo.domain.Order) order);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void isEmailSent(){
        String emailStatus = "Email sent successfully";
        assertTrue(emailService.sendEmail((demo.domain.Order) order,emailStatus));
    }

}