package br.com.codemathsz.job_management.modules.company.controllers;

import br.com.codemathsz.job_management.modules.company.dto.CreateJobDTO;
import br.com.codemathsz.job_management.utils.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {

        var createJobDTO = CreateJobDTO.builder()
        .benefits("BENEFITS_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();


        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(createJobDTO))
            .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "@Javagass_233$324f.net.io"))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
        System.out.println(result);
    }
}
