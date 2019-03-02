package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * file
 *
 * @author Administrator
 * @date 2019/3/1 22:15
 */
class Write {
    static void write(String path) throws IOException {
        File file = new File("out.txt");
        FileWriter fw = new FileWriter(file.getAbsolutePath(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(path + "\r\n");
        bw.close();
    }
}
