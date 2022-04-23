import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StreamTest {
    public static Collection<Person> persons = new ArrayList<>();

    @BeforeAll
    public static void CreatePersons() {
        Person person1 = new Person("Vasya","Pupkin", 25, Sex.MAN, Education.HIGHER);
        Person person2 = new Person("Fedya","Kudrov", 27, Sex.MAN, Education.HIGHER);
        Person person3 = new Person("Keks","Vasilyev", 10, Sex.MAN, Education.HIGHER);
        Person person4 = new Person("Maria","Petrova", 16, Sex.WOMAN, Education.HIGHER);
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
    }

    @Test
    public void getRandomPersonsListReturnIsNotEmptyList() {
        Collection<Person> persons =  Main.getRandomPersonsList();
        assertThat(persons, is(not(nullValue())));
        assertThat(persons, is(not(empty())));
    }

    @Test
    public void getListWorkableExpectedSizeEquals2() {
        List<Person> list = Main.getListWorkable(persons, Sex.MAN, 65);
        assertThat(list, hasSize(2));
    }

    @Test
    public void getListFamilySolderHasItems(){
        List<String> list = Main.getListFamilySolder(persons);
        assertThat(list, hasItems("Pupkin","Kudrov"));
        assertThat(list, not(hasItems("Vasilyev", "Petrova")));
    }

    @AfterAll
    public static void clearPersons() {
        persons.clear();
    }


}
