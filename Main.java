import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Solution f
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void printName() {
        System.out.println(this.name);
    }
}

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Person> list = new ArrayList<>();
        char option;
        Person person = null;
        do
        {
        System.out.println("================================================================");
        System.out.println("Choose an option: \n1. Print Hello World \n2. Enter your name \n3. Print given name \n" + 
                        "4. Give 3 names ");
        option = sc.next().charAt(0);

        switch (option) {
            case '1':
                {System.out.println("Hello World!");
                break;}

            case '2':
                {System.out.println("Enter your name: ");
                String name = sc.next();
                person = new Person(name);
                list.add(person);
                break;}

            case '3':
                if (person != null) { 
                    person.printName(); 
                } else {
                    System.out.println("No person to display.");
                }
                break;
            case '4':
                {for(int i = 0; i < 3; i++)
                {
                    System.out.println("Enter names :");
                    String name2 = sc.next();
                    person = new Person(name2);
                    list.add(person);
                    //person.printName();
                    
                }
                printNames(list);
                break;}

        }
    }while(option != '5');
        sc.close();
    }
    //Solution e
    public static void printNames(List<Person> persons)
{
    System.out.println("The names you have entered:");
    for(Person p : persons)
        System.out.println(p.getName());
}
}

