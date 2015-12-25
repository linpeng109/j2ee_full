package test.json;

import com.cn.common.RandomModule;
import com.cn.common.implement.RandomModuleImpl;
import com.cn.json.JSONUtilOverJackson;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.junit.Test;
import test.TestBase;
import test.json.entity.Contact;
import test.json.entity.Student;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


import static com.cn.common.RandomModule.mystring_china;

/**
 * Created by jupiter on 15-12-21.
 */
public class TestJSONUtilOverJackson extends TestBase {
    private static Logger logger = Logger.getLogger(TestJSONUtilOverJackson.class);

    private RandomModule random = new RandomModuleImpl();

    private Student getStudent() throws ParseException {
        Student student = new Student();
        student.studentId = random.getUUID();
        student.name = random.getRStr(mystring_china, 2);
        student.bathday = random.getRandomDate("2000:1:1", "2010:1:1", "yyyy:MM:dd");
        Contact contact = new Contact();
        contact.city = "BeiJing";
        contact.phone = "13601024590";

        student.contact = contact;

        return student;

    }

    @Test
    public void testPOJO2JSON() throws ParseException, IOException, JSONException {
        ArrayList<String> jsons=new ArrayList<String>();
        for (int i=0;i<1000;++i){
            Student student = this.getStudent();
            String jsonString = JSONUtilOverJackson.ObjectToJSON(student);
            jsons.add(i,jsonString);
            logger.debug(jsonString);
        }
    }

    @Test
    public void testJSON2JAVA() throws IOException {
        String jsonString = "{\"studentId\":\"987654321\",\"bathday\":1110048023803,\"name\":\"洪柯\",\"contact\":{\"city\":\"BeiJing\",\"phone\":null}}";
        Student student = (Student) JSONUtilOverJackson.JSONToObject(jsonString, Student.class);
        String result = String.format("StudentId=[%s];bathday=[%s],name=[%s]", student.studentId, student.bathday, student.name);
        logger.debug(result);
    }
}
