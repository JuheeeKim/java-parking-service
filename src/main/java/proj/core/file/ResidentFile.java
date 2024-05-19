package proj.core.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ResidentFile {

    private ArrayList<String> phoneNumList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private int residentNum;

    public ResidentFile() {
        File file = new File("C:\\바탕 화면\\parking\\core\\src\\main\\java\\proj\\core\\res.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            residentNum = Integer.parseInt(br.readLine());

            String line;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(" ");
                phoneNumList.add(str[0]);
                nameList.add(str[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getResidentNum() {
        return residentNum;
    }

    public ArrayList<String> getPhoneNumList() {
        return phoneNumList;
    }

    public ArrayList<String> getNameList() {
        return nameList;
    }
}
