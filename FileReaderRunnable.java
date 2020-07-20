package tech.codingclub.wordTopFrequencyAnalyser;

import tech.codingclub.utility.TaskManager;
import tech.codingclub.wordTopFrequencyAnalyser.Utility.FileTaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FileReaderRunnable implements Runnable {

    String filePath;

    public FileReaderRunnable(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        Scanner sc = null;
        ArrayList<String> arrayList = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<String,Integer>();

        File file = new File(filePath);
        try{
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                String str[] = sc.nextLine().split(" ");
                for(String s: str){
                    s = s.toLowerCase();
                    arrayList.add(s);
                }
            }
            for(String str:arrayList) {
                if (map.containsKey(str)) {
                    int count = map.get(str);
                    map.put(str, count + 1);
                } else {
                    map.put(str, 1);
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileTaskManager taskManager = new FileTaskManager(map);
        taskManager.printMap();
    }

    public static void main(String[] args) {
        String filePath = "/home/rudradevmishra/IdeaProjects/CodingClubBackend/data/practice/file/IndianNationalAnthem.txt";
        FileReaderRunnable runnable1 = new FileReaderRunnable(filePath);
        Thread thread1 = new Thread(runnable1);
        thread1.start();
    }
}
