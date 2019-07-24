package test;

import com.lqh.dev.Account2Application;
import com.lqh.dev.domain.Account;
import com.lqh.dev.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@SpringBootTest(classes = Account2Application.class)
@RunWith(SpringRunner.class)
public class CommonTest {

    @Resource
    private IAccountService accountService;

    @Test
    public void test() {
        Account account = new Account();
        account.setUserId(UUID.randomUUID().toString());
        account.setName("Donald");
        account.setBalance(new BigDecimal(888));
        account.setCreateTime(new Date());
        accountService.save(account);
    }
}
