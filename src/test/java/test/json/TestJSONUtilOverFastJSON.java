package test.json;

import com.cn.common.RandomModule;
import com.cn.common.implement.RandomModuleImpl;
import com.cn.json.JSONUtilOverFastJSON;
import org.apache.log4j.Logger;
import org.junit.Test;
import test.TestBase;
import test.json.entity.Contact;
import test.json.entity.Student;

import java.text.ParseException;
import java.util.ArrayList;

import static com.cn.common.RandomModule.mystring_china;

/**
 * Created by jupiter on 15-12-22.
 */
public class TestJSONUtilOverFastJSON extends TestBase {
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(TestJSONUtilOverFastJSON.class);

    /**
     * 随机数模块
     */
    private RandomModule random = new RandomModuleImpl();

    /**
     * 获得实体类
     *
     * @return
     * @throws ParseException
     */
    private Student buildStudents() throws ParseException {
        Student student = new Student();
        student.setStudentId(random.getUUID());
        student.setName(random.getRStr(mystring_china, 2));
        student.setBathday(random.getRandomDate("2000:1:1", "2010:1:1", "yyyy:MM:dd"));

        Contact contact = new Contact();
        contact.city = random.getRStr(RandomModule.myint_all, 4);
        contact.phone = random.getRStr(RandomModule.myint_09, 11);

        student.setContact(contact);

        return student;
    }

    @Test
    public void testObjectToJSON() throws ParseException {
        for (int i = 0; i < 1000; ++i) {
            String result = JSONUtilOverFastJSON.ObjectToJSON(this.buildStudents());
            logger.debug(result);
        }
    }

    @Test
    public void testListToJSON() throws ParseException {
        ArrayList<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 2; ++i) {
            list.add(buildStudents());
        }
        String result = JSONUtilOverFastJSON.ObjectToJSON(list);
        logger.debug(result);
    }

    @Test
    public void testJSONToObject() {
        String jsonString = "[{\"bathday\":998588357093,\"contact\":{\"city\":\"RC8H\",\"phone\":\"66320337950\"},\"name\":\"郎连\",\"studentId\":\"59cc8d9022794be882cb5acdc095c66d\"},{\"bathday\":1017450178910,\"contact\":{\"city\":\"hd3g\",\"phone\":\"89130271567\"},\"name\":\"路符\",\"studentId\":\"9fa3e5f9a1ea407eb6464a96618d9026\"}]";
        ArrayList<Student> list = (ArrayList<Student>) JSONUtilOverFastJSON.JSONToArray(jsonString, Student.class);
        for (Student student : list) {
            logger.debug(student.studentId);
        }
    }

}
