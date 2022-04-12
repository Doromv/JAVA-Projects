import com.doromv.pojo.Books;
import com.doromv.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Doromv
 * @create 2022-03-04-19:04
 */
public class MyTest {
    @Test
    public void Test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = context.getBean("BookServiceImpl", BookService.class);
        List<Books> list = bookServiceImpl.queryAllBook();
        for (Books books : list) {
            System.out.println(books);
        }
    }
}
