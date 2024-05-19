package proj.core.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ResidentFileTest {
    ResidentFile residentFile;

    @BeforeEach
    void setUp() {
        residentFile = new ResidentFile();
    }

    @Test
    void readTest(){
        residentFile = new ResidentFile();
        assertThat(residentFile.getResidentNum()).isEqualTo(3);
    }

    @Test
    void arrayTest(){
        System.out.println("Phone Numbers:");
        for (String phoneNumber : residentFile.getPhoneNumList()) {
            System.out.println(phoneNumber);
        }

        System.out.println("Names:");
        for (String name : residentFile.getNameList()) {
            System.out.println(name);
        }
    }
}

