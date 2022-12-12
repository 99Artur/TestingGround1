import java.util.*;
public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);
    public int getIntInput() {
        int choice = 0;
        while (choice == 0) {
            try {
                choice = reader.nextInt();
                reader.nextLine();
            } catch (Exception e) {
                System.out.println("PLEASE ENTER A NUMBER OMFG!!!");
                reader.nextLine();
            }
        }
        return choice;
    }
    public void printClubOptions() {
        System.out.println("""
                1) Club Mercury
                2) Club Neptune
                3) Club Jupiter
                4) Multi Clubs""");

    }

    public int getChoice() {
        int choice;
        System.out.println("""
                WELCOME TO OZONE FITNESS CENTER
                ===============================
                1) Add Member
                2) Remove Member
                3) Display Member Information
                Please select an option (or Enter -1 quit)""");
        choice = getIntInput();
        return choice;
    }

    public String addMembers(LinkedList<Member> m) {
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> calc;

        System.out.print("Enter a name: ");
        name = reader.nextLine();
        printClubOptions();

        System.out.print("Enter ID club: ");
        club = getIntInput();

        while (club < 1 || club > 4) {
            System.out.println("Entered an incorrect find, try again: ");
            club = getIntInput();
        }
        if (m.size() > 0) {
            memberID = m.getLast().getMemberID() + 1;
        } else memberID = 1;

        if (club != 4) {
            calc = (i) -> {
                if (i == 1) {
                    return 900;
                } else if (i == 2) {
                    return 950;
                } else if (i == 3) {
                    return 1000;
                } else {
                    return -1;
                }
            };

            fees = calc.calculateFees(club);

            mbr = new SingleClubMember('S', memberID, name, fees, club);
            m.add(mbr);
            mem = mbr.toString();

            System.out.println("Single Club Member added");
        } else {
            calc = (i) -> {
                if (i == 4)
                    return 1200;
                else
                    return -1;
            };
            fees = calc.calculateFees(club);
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("Multi Club Member added");
        }
        return mem;
    }

    public void removeMember(LinkedList<Member> m) {

        int memberID;

        System.out.print("Enter ID member: ");
        memberID = getIntInput();

        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getMemberID() == memberID) {
                m.remove(i);
                System.out.println("Member with ID " + memberID + " has deleted");
                return;
            }
        }
        System.out.println("Member with ID " + memberID + " is not found");
    }

    public void printMemberInfo(LinkedList<Member> m) {

        String[] memberInfo;
        int memberID;

        System.out.print("Enter ID member: ");
        memberID = getIntInput();

        for (Member member : m) {
            if (member.getMemberID() == memberID) {
                memberInfo = member.toString().split(", ");
                System.out.println(Arrays.toString(memberInfo));
                return;
            }
        }
        System.out.println("Member ID not found");
    }
}
