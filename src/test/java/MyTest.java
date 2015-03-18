/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.db.entities.Doctor;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jevprentice
 */
public class MyTest {

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
//    @Test
    public void hello() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Doctor readValue = mapper.readValue("{\"medID\":\"MID123\",\"name\":\"John\",\"surname\":\"Doe\"}", Doctor.class);
        System.out.println("readValue: " + readValue);
        if (readValue == null){
            System.out.println("its null");
        } else {
            System.out.println("name " + readValue.getName());
                System.out.println("its not null");
        }
        assert(true);
    }
}
