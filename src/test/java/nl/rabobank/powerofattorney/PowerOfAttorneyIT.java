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

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void allPowerOfAttorneys() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/power-of-attorneys"))
                .andExpect(status().isOk())
                .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        String expected = Files.read(allPoa.getFile(), Charset.defaultCharset());

        assertEquals(mapper.readTree(expected), mapper.readTree(actual));
    }
}
