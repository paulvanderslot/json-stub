package nl.rabobank.powerofattorney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.internal.Files;

@SpringBootTest
@AutoConfigureMockMvc
public class PowerOfAttorneyIT {

    @Autowired
    private MockMvc mvc;

    @Value("classpath:poa/poa.json")
    private Resource allPoa;
    @Value("classpath:poa/0001.json")
    private Resource onePoa;
    @Value("classpath:accounts/123456789.json")
    private Resource oneAccount;
    @Value("classpath:credit-card/3333.json")
    private Resource oneCreditCard;
    @Value("classpath:debit-card/1111.json")
    private Resource oneDebitCard;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void getAllPowerOfAttorneys() throws Exception {
        assertResultEqualToFile("/power-of-attorneys", allPoa);
    }

    @Test
    void getOnePowerOfAttorney() throws Exception {
        assertResultEqualToFile("/power-of-attorneys/0001", onePoa);
    }

    @Test
    void getOneAccount() throws Exception {
        assertResultEqualToFile("/accounts/123456789", oneAccount);
    }

    @Test
    void getOneCreditCard() throws Exception {
        assertResultEqualToFile("/credit-cards/3333", oneCreditCard);
    }

    @Test
    void getOneDebitCard() throws Exception {
        assertResultEqualToFile("/debit-cards/1111", oneDebitCard);
    }

    private void assertResultEqualToFile(String restUrl, Resource jsonResource) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(restUrl))
                .andExpect(status().isOk())
                .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        String expected = Files.read(jsonResource.getFile(), Charset.defaultCharset());

        assertEquals(mapper.readTree(expected), mapper.readTree(actual));
    }
}
