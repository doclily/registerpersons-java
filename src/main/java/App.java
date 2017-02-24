/*
 * This is a screening question for potential hires. See the comment on the registerPersons function
 * for how to answer this question. In your response, you only need to send back your modified
 * version of this file; there is no need to upload the entire Gradle project. Sending your code in a
 * a message on Upwork is fine, as we can use clang-format to reformat your code.
*/

import java.util.Map;
import java.util.TreeMap;

public class App
{
    private static class Person
    {
        /**
         * The person's name.
         */
        final String name;

        /**
         * The name of the person's parent. Null if the person has no parent.
         */
        final String parentName;

        Person(String name, String parentName)
        {
            this.name = name;
            this.parentName = parentName;
        }
    }

    private static Person[] TEST_PERSONS = {new Person("Johnson", "John"),
                                            new Person("Benson", "Ben"),
                                            new Person("Bryson", "Bryce"),
                                            new Person("Adam", null),
                                            new Person("Eve", null),
                                            new Person("Eva", "Eve"),
                                            new Person("Evelyn", "Eve"),
                                            new Person("John", "Adam"),
                                            new Person("Ben", "Adam"),
                                            new Person("Bryce", "Adam")};

    private static class PersonManager
    {
        private Map<String, Person> registeredPeople = new TreeMap<>();

        /**
         * Registers a person. Throws an error if the person is already registered
         * or if the
         * person's parent is not registered.
         */
        void registerPerson(Person person)
        {
            if (registeredPeople.get(person.name) != null)
            {
                throw new RuntimeException("person is already registered.");
            }

            if (person.parentName != null && (registeredPeople.get(person.parentName) == null))
            {
                throw new RuntimeException("person has an unknown parent.");
            }

            registeredPeople.put(person.name, person);
        }

        /**
         * Prints descriptions of all registered people.
         */
        void printRegisteredNames()
        {
            System.out.println("The following people are registered:");
            for (Map.Entry<String, Person> mapEntry : registeredPeople.entrySet())
            {
                System.out.println(String.format(
                    "%s, whose parent is %s", mapEntry.getKey(), mapEntry.getValue().parentName));
            }
        }
    }

    /**
     * Inputs a list of persons and a person manager and registers those people
     * with the person manager. The persons may be in any order. This naive
     * implementation does not work because it may attempt to register a person
     * whose parent has not been registered.
     *
     * Your task is to modify this function so that it works no matter how
     * the persons are ordered in the list that is passed to this function.
     * You can assume that no two people have the same name.
     *
     * You are free to modify this function in any way, and you are free
     * to write additional, helper functions for this function to call.
     *
     * You may not modify other parts of the code, and you may not access
     * PersonManager's private members.
     *
     * You should be able to build and run this code with a simple
     * "gradle build" and "java -jar ./build/libs/registerpersons.jar" from the
     * project's
     * top directory. When you're done, the program's output should be the
     * following:
     *
     * The following people are registered:
     * Adam, whose parent is null
     * Ben, whose parent is Adam
     * Benson, whose parent is Ben
     * Bryce, whose parent is Adam
     * Bryson, whose parent is Bryce
     * Eva, whose parent is Eve
     * Eve, whose parent is null
     * Evelyn, whose parent is Eve
     * John, whose parent is Adam
     * Johnson, whose parent is John
     */
    private static void registerPersons(Person[] persons, PersonManager personManager)
    {
        for (Person person : persons)
        {
            personManager.registerPerson(person);
        }
    }

    public static void main(String[] args)
    {
        PersonManager personManager = new PersonManager();
        try
        {
            registerPersons(TEST_PERSONS, personManager);
            personManager.printRegisteredNames();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
