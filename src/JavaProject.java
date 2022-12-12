import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] args) {

        String mem;
        MembershipManagement mm = new MembershipManagement();
        FileHandler fh = new FileHandler();
        LinkedList<Member> members = fh.readFile();
        int choice = mm.getChoice();

        while (choice != -1) {
            if (choice == 1) {
                mem = mm.addMembers(members);
                fh.appendFile(mem);
            } else if (choice == 2) {
                mm.removeMember(members);
                fh.overwriteFile(members);
            } else if (choice == 3) {
                mm.printMemberInfo(members);
            } else {
                System.out.println("Error! Please, try again or ENTER (-1) for ended.");
            }
            choice = mm.getChoice();
        }
    }
}