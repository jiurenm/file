package file;

import com.google.common.base.Splitter;
import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Multiset;
import mongo.MongoUtil;
import org.bson.Document;

import java.io.File;
import java.util.*;

/**
 * file
 *
 * @author Administrator
 * @date 2019/3/1 22:15
 */
public class Read {

    private final static Multiset<Document> DOCUMENTS = ConcurrentHashMultiset.create();
    public static void read(final String filePath, int fileLength) {
        final File file = new File(filePath);
        if (!file.isDirectory()) {
            System.out.println("path=" + file.getAbsolutePath());
            String path = file.getAbsolutePath().substring(fileLength);
            DOCUMENTS.add(new Document().append("fileName",file.getName()).append("filePath",path.replace("\\","/")));
        } else {
            String[] fileList = file.list();
            for(String s:fileList) {
                File readFile = new File(filePath + "\\" + s);
                String d = file.getPath().substring(fileLength);
                if (!readFile.isDirectory()) {
                    List<String> list2 = Splitter.on("\\").omitEmptyStrings().trimResults().splitToList(d);
                    System.out.println("path=" + readFile.getAbsolutePath());
                    DOCUMENTS.add(new Document().append("fileName", readFile.getName()).append("filePath", readFile.getAbsolutePath().substring(fileLength).replace("\\", "/"))
                            .append("type", list2));
                } else {
                    read(filePath + "\\" + s, fileLength);
                }
            }
        }
    }

    public static void writeIntoMongo(String databaseName, String collectionName) {
        System.out.println("Write in Mongodb");
        MongoUtil.write(new ArrayList<Document>(DOCUMENTS), databaseName, collectionName);
        System.out.println("Total: " + DOCUMENTS.size() + " documents");
    }
}
