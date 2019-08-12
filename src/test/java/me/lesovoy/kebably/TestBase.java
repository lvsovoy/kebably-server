package me.lesovoy.kebably;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@WebAppConfiguration
abstract public class TestBase {
    public static final String TEST_RESOURCES = "src/test/resources/";
    public ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected MockMvc MVC;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        MVC = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
