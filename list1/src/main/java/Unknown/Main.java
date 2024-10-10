package Unknown;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

public class Main {

    public static class Person
    {
        Scanner sc  = new Scanner(System.in);

        public String name, email, phone, address, age;
        public Person(String name, String email, String phone, String address, String age)
        {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.age = age;
        }
        public void inputPerson()
        {
            System.out.println("Enter name: ");

            this.name = sc.nextLine();
            System.out.println("Enter email: ");
            this.email = sc.nextLine();
            System.out.println("Enter phone: ");
            this.phone = sc.nextLine();
            System.out.println("Enter address: ");
            this.address = sc.nextLine();
            System.out.println("Enter age: ");
            this.age = sc.nextLine();
        }
    }
    public static class ContactList
    {
        Person p;
        ContactList pNext;
        public ContactList(Person p)
        {
            this.p = p;
            this.pNext = null;
        }
    }
    public static void PrintList(ContactList head)
    {
        ContactList current = head;
        int count = 0;
        while (current != null)
        {
            count++;
            System.out.printf("======================== PERSON %d ========================\n", count);
            System.out.println("Name: " + current.p.name + "\nEmail: " + current.p.email + "\nPhone: " + " " + current.p.phone + "\nAddress: " + " " + current.p.address + "\nAge: " + " " + current.p.age);
            current = current.pNext;
        }
        System.out.println();
    }
    public static ContactList insertAtBeginning(ContactList head, Person p, Document doc) throws ParserConfigurationException, TransformerException
    {
        Element personElement = doc.createElement("person");

        Element nameElement = doc.createElement("name");
        nameElement.appendChild(doc.createTextNode(p.name));
        personElement.appendChild(nameElement);

        Element emailElement = doc.createElement("email");
        emailElement.appendChild(doc.createTextNode(p.email));
        personElement.appendChild(emailElement);

        Element phoneElement = doc.createElement("phone");
        phoneElement.appendChild(doc.createTextNode(p.phone));
        personElement.appendChild(phoneElement);

        Element addressElement = doc.createElement("address");
        addressElement.appendChild(doc.createTextNode(p.address));
        personElement.appendChild(addressElement);

        Element ageElement = doc.createElement("age");
        ageElement.appendChild(doc.createTextNode(p.age));
        personElement.appendChild(ageElement);

        doc.getDocumentElement().appendChild(personElement);

        ContactList newNode = new ContactList(p);
        newNode.pNext = head;
        head  = newNode;

        try(FileOutputStream output = new FileOutputStream("contacts.xml")) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return head;
    }
    public static void searchLinkedList(ContactList head, String searchTerm) {
        boolean found = false;
        while (head != null) {
            if (head.p.name.equalsIgnoreCase(searchTerm) || head.p.email.equalsIgnoreCase(searchTerm) ||
                    head.p.phone.equals(searchTerm) || head.p.address.equalsIgnoreCase(searchTerm) || head.p.age.equalsIgnoreCase(searchTerm)) {
                System.out.println("======================== FOUND LIST ========================");
                System.out.println("Name: " + head.p.name + "\nEmail: " + head.p.email + "\nPhone: " + head.p.phone + "\nAddress: " + head.p.address + "\nAge: " + head.p.age);
                found = true;
                break;
            }
            head = head.pNext;
        }
        if (!found) {
            System.out.println("Person not found.");
        }
    }
    public static void Option(Person p, ContactList head, Document doc)throws ParserConfigurationException, TransformerException
    {
        try (Scanner sc = new Scanner(System.in)) {
            char c;
            do{
                System.out.println("Contact List Menu");
                System.out.println("Choose Option ");
                System.out.println("1. Add Contact");
                System.out.println("2. Find Contact");
                System.out.println("3. Print Contact List");
                c = sc.next().charAt(0);
                switch(c)
                {
                    case '1' -> {
                        p = new Person(null, null, null, null, null);
                        p.inputPerson();
                        head = insertAtBeginning(head, p, doc);
                    }
                    case '2' -> {
                        System.out.println("Enter Search Term: ");
                        sc.nextLine();
                        String search = sc.nextLine();
                        searchLinkedList(head, search);
                    }
                    case '3' -> PrintList(head);
                }
                System.out.println("================================================================");
            }while(c !=  'q');
        }
    }
    public static void main(String[] args)  throws ParserConfigurationException, TransformerException, NullPointerException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        File xmlFile = new File("contacts.xml");

        if (xmlFile.exists()) {
            xmlFile.delete();
        }

        Element root = doc.createElement("contacts");
        doc.appendChild(root);


        Person person = new Person(null, null, null, null, null);
        Option(person, null, doc);

        writeXml(doc, System.out);
    }
    private  static void writeXml(Document doc, OutputStream output) throws TransformerFactoryConfigurationError, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(source, result);
    }
}
