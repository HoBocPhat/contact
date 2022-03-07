import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Contact> contacts;
    private static Scanner scanner = new Scanner(System.in);
    private static int id = 0;
    public static void main(String[] args) {
        contacts = new ArrayList<>();
        System.out.println("Hello");
        showInitialOption ();


    }
    private static void showInitialOption() {
        System.out.println("1. Manage contacts"
                + "\n2. Messages"
                + "\n3. Quit");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                manageContact();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;
        }
    }

    private static void manageContact(){
        System.out.println("1. Show all contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search for a contact");
        System.out.println("4. Delete a contact");
        System.out.println("5. Go back to previous menu");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                showAllContacts();
                break;
            case 2:
                addContact();
                break;
            case 3:
                searchContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOption();
                break;
        }
    }

    private static void showAllContacts() {
        if (contacts.size() > 0){
            for (Contact c: contacts) {
                c.getDetail();
                System.out.println("************");
            }

        } else {
            System.out.println("You don't have any contact");

        }
        showInitialOption();

    }

    private static void addContact() {
        System.out.println("Add a new contact");
        System.out.println("\t Add name");
        String name = scanner.next();
        System.out.println("\t Add number");
        String number = scanner.next();
        System.out.println("\t Add email");
        String email = scanner.next();

        if(name.equals("") || number.equals("") || email.equals("")) {
            System.out.println("Please fill in all information");
            addContact();
        } else {
            boolean doesExist = false;
            for (Contact c: contacts)
            {
                if (c.getName().equals(name) || c.getEmail().equals(email) || c.getNumber().equals(number))
                {
                    doesExist = true;
                    System.out.println("The contact already exist");
                    addContact();
                }
            }
            if (!doesExist){
                Contact contact = new Contact(name,number,email);
                contacts.add(contact);
                System.out.println("Contact added successfully");
            }
        }
        showInitialOption();
    }

    private static void searchContact() {
        System.out.println("Enter name of contact");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter the name");
            searchContact();
        }
        else {
            boolean exist = false;
            for (Contact c: contacts) {
                if (c.getName().equals(name)){
                    exist = true;
                    c.getDetail();
                }

            }
            if(!exist){
                System.out.println("There is no contact exist");
            }
        }
        showInitialOption();
    }

    private static void deleteContact() {
        System.out.println("Enter name of contact");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter the name");
            deleteContact();
        }
        else {
            boolean exist = false;
            for (Contact c: contacts) {
                if (c.getName().equals(name)){
                    exist = true;
                    contacts.remove(c);
                }

            }
            if(!exist){
                System.out.println("There is no contact exist");
            }
        }
        showInitialOption();
    }

    private static void manageMessages() {
        System.out.println("1. See the list of all messages");
        System.out.println("2. Send a new message");
        System.out.println("3. Go bach to previous menu");
        int option = scanner.nextInt();
        switch (option){
            case 1:
                showAllMessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOption();
                break;
        }
    }

    private static void showAllMessages() {
        ArrayList <Message> allMessages = new ArrayList<>();
        for (Contact c: contacts) {
            allMessages.addAll(c.getMessages());
        }
        if (allMessages.size() > 0){
            for (Message m: allMessages) {
                m.getDetail();
                System.out.println("************");
            }
        }
        else {
            System.out.println("You don't have any message");
        }
        showInitialOption();
    }

    private static void sendNewMessage() {
        System.out.println("Who you want to send?");
        String name = scanner.next();
        if (name.equals("")) {
            System.out.println("Please enter a name");
            searchContact();
        } else {
            boolean doesExist = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                }
            }
            if (doesExist) {
                System.out.println("What do you want to send?");
                String text = scanner.next();
                if (text.equals("")) {
                    System.out.println("Please enter the message");
                    sendNewMessage();
                } else {
                    id++;
                    Message message = new Message(text, name, id);
                    for (Contact c : contacts) {
                        ArrayList<Message> newMassages = c.getMessages();
                        newMassages.add(message);
                        c.setMessages(newMassages);
                        System.out.println("Message was sent successfully");
                    }
                }

            } else {
                System.out.println("There is no contact");
            }
        }
        showInitialOption();
    }
}
