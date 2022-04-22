import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StreamTest {

    public Main main = new Main();
    public static Collection<Person> persons = new ArrayList<>();

    @BeforeAll
    public static void CreatePersons() {
        Person person1 = new Person("Vasya","Pupkin", 25, Sex.MAN, Education.HIGHER);
        Person person2 = new Person("Fedya","Kudrov", 30, Sex.MAN, Education.HIGHER);
        Person person3 = new Person("Keks","Vasilyev", 10, Sex.MAN, Education.HIGHER);
        Person person4 = new Person("Maria","Petrova", 16, Sex.WOMAN, Education.HIGHER);
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
    }

    @Test
    public void getRandomPersonsListTest() {
        Collection<Person> persons =  Main.getRandomPersonsList();
        Assertions.assertTrue(persons != null);
        Assertions.assertFalse(persons.isEmpty());
    }

    @Test
    public void getListWorkableTest() {
        List<Person> list = Main.getListWorkable(persons, Sex.MAN, 65);
        Assertions.assertEquals(list.size(), 2);
    }

    @Test
    public void getListFamilySolderTest(){
        List<String> list = Main.getListFamilySolder(persons);
        Assertions.assertTrue(list.contains("Pupkin"));
        Assertions.assertFalse(list.contains("Kudrov"));
        Assertions.assertFalse(list.contains("Vasilyev"));
    }


}
