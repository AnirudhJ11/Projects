package myjava;
import java.util.HashMap;
import java.util.Scanner;

public class RFIDStudentSecuritySystem {

    // Inner class to represent a student
    static class Student {
        String name;
        String grade;
        String status;

        Student(String name, String grade) {
            this.name = name;
            this.grade = grade;
            this.status = "outside"; // default status is "outside"
        }
    }

    // HashMap to store the student database (RFID tag as key, Student object as value)
    static HashMap<String, Student> studentsDB = new HashMap<>();

    // Function to check student entry
    public static void studentCheckIn(String rfidTag) {
        if (studentsDB.containsKey(rfidTag)) {
            Student student = studentsDB.get(rfidTag);
            if (student.status.equals("inside")) {
                System.out.println(student.name + " is already inside.");
            } else {
                student.status = "inside";
                System.out.println(student.name + " has checked in successfully.");
            }
        } else {
            System.out.println("Invalid RFID tag.");
        }
    }

    // Function to check student exit
    public static void studentCheckOut(String rfidTag) {
        if (studentsDB.containsKey(rfidTag)) {
            Student student = studentsDB.get(rfidTag);
            if (student.status.equals("outside")) {
                System.out.println(student.name + " is already outside.");
            } else {
                student.status = "outside";
                System.out.println(student.name + " has checked out successfully.");
            }
        } else {
            System.out.println("Invalid RFID tag.");
        }
    }

    // Function to track student attendance (status)
    public static void trackAttendance() {
        System.out.println("\n--- Student Attendance ---");
        for (String rfidTag : studentsDB.keySet()) {
            Student student = studentsDB.get(rfidTag);
            System.out.println("RFID: " + rfidTag + " | Name: " + student.name + " | Status: " + student.status);
        }
        System.out.println("--------------------------\n");
    }

    // Function to control access to restricted areas
    public static void accessControl(String rfidTag, String area) {
        String[] restrictedAreas = {"lab", "library", "admin_office"};
        boolean isRestricted = false;

        // Check if the area is restricted
        for (String restrictedArea : restrictedAreas) {
            if (restrictedArea.equals(area)) {
                isRestricted = true;
                break;
            }
        }

        if (isRestricted) {
            if (studentsDB.containsKey(rfidTag) && studentsDB.get(rfidTag).status.equals("inside")) {
                System.out.println("Access granted to " + area + " for " + studentsDB.get(rfidTag).name + ".");
            } else {
                System.out.println("Access denied. Either the student is not inside or the RFID tag is invalid.");
            }
        } else {
            System.out.println(area + " is not a restricted area.");
        }
    }

    // Main function to simulate the RFID system
    public static void main(String[] args) {
        // Initializing the student database
        studentsDB.put("1001", new Student("Alice", "10"));
        studentsDB.put("1002", new Student("Bob", "11"));
        studentsDB.put("1003", new Student("Charlie", "12"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- RFID-based Student Security System ---");
            System.out.println("1. Student Check-in");
            System.out.println("2. Student Check-out");
            System.out.println("3. Track Attendance");
            System.out.println("4. Access Control");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter RFID tag to check-in: ");
                    String checkInTag = scanner.nextLine();
                    studentCheckIn(checkInTag);
                    break;

                case 2:
                    System.out.print("Enter RFID tag to check-out: ");
                    String checkOutTag = scanner.nextLine();
                    studentCheckOut(checkOutTag);
                    break;

                case 3:
                    trackAttendance();
                    break;

                case 4:
                    System.out.print("Enter RFID tag for access control: ");
                    String accessTag = scanner.nextLine();
                    System.out.print("Enter area name (lab, library, admin_office): ");
                    String area = scanner.nextLine();
                    accessControl(accessTag, area);
                    break;

                case 5:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
