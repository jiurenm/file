import com.google.common.base.Stopwatch;
import file.Read;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * file
 *
 * @author Administrator
 * @date 2019/3/1 22:13
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please input filePath");
        String filePath = in.next();
        System.out.println("Please input databaseName");
        String databaseName = in.next();
        System.out.println("Please input collectionName");
        String collectionName = in.next();

        Stopwatch stopwatch = Stopwatch.createStarted();
        Read.read(filePath,filePath.length());
        Read.writeIntoMongo(databaseName, collectionName);
        long nanos = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("Spend: " + nanos + "ms");
    }
}
