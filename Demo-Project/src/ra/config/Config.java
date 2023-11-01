package ra.config;

import java.io.*;
import java.util.Scanner;

public class Config<T> {
    public static Scanner scanner(){
        return new Scanner(System.in);
    }

    public static final String URL_USERS="users.txt";
    public static final String URL_USER_LOGIN="userLogin.txt";
    public static final String URL_PRODUCT="product.txt";
    public static final String URL_CATEGORY="category.txt";
    public void writeFile(String PATH_FILE, T t) {
        File file = new File(PATH_FILE);
//        FileOutputStream fos = null;
//        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public T readFile(String PATH_FILE) {
        File file = new File(PATH_FILE);
        T t = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            t = (T) ois.readObject();
            if (fis != null) {
                fis.close();
            }
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file rỗng");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return t;
    }
}
