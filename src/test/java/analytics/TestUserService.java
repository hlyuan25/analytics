package analytics;

import org.apache.log4j.Logger;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
  
import com.alibaba.fastjson.JSON;
import com.citi.analytics.entity.User;
import com.citi.analytics.service.UserService;  

  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {  
        "classpath:spring.xml",  
        "classpath:spring-hibernate.xml"  
})  
public class TestUserService {  
    private static final Logger LOGGER = Logger.getLogger(TestUserService.class);  
    @Autowired  
    UserService userManager;  
      
    @Test  
    public void save() {  
        User user = new User();  
        user.setUserName("fengwusan");  
        user.setPassword("123456");  
        
        Integer id = userManager.save(user);  
        JSON.toJSONString(id);  
    }  
}  
