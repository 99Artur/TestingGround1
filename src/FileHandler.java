import java.io.*;
import java.util.LinkedList;


public class FileHandler {

    public LinkedList<Member> readFile() {
        LinkedList<Member> members = new LinkedList<>();
        BufferedReader reader;
        Member mem = null;
        String[] split;
        String line;

        try {
            reader = new BufferedReader(new FileReader("members.csv"));
            while ((line = reader.readLine()) != null) {
                split = line.split(", ");
                if (split[0].equals("S")) {
                    mem = new SingleClubMember('S', Integer.parseInt(split[1]),
                            split[2], Double.parseDouble(split[3]),
                            Integer.parseInt(split[4]));
                } else if (split[0].equals("M")) {
                    mem = new MultiClubMember('M', Integer.parseInt(split[1]),
                            split[2], Double.parseDouble(split[3]),
                            Integer.parseInt(split[4]));
                }
                members.add(mem);
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }

    public void appendFile(String mem) {
        BufferedWriter writer;

        try  {
            writer = new BufferedWriter(new FileWriter("members.csv", true));
            writer.write(mem + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void overwriteFile(LinkedList<Member> m) {

        BufferedWriter writer;
        String s;

        try {
            writer = new BufferedWriter(new FileWriter("members.temp", false));
            for (Member member : m) {
                s = member.toString();
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            File fileCsv = new File("members.csv");
            File fileTemp = new File("members.temp");
            fileCsv.delete();
            fileTemp.renameTo(fileCsv);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
