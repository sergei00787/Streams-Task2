import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Collection<Person> persons  = getRandomPersonsList();

        long countYoungPeople = getCountYongPeople(persons);
        System.out.println("Количество несовершеннолетних: " + countYoungPeople);

        List<String> listFamilySolder = getListFamilySolder(persons);

        System.out.println("Количество призывников: " + listFamilySolder.size());

        List<Person> listManWorkable = getListWorkable(persons, Sex.MAN, 65);
        List<Person> listWomanWorkable = getListWorkable(persons, Sex.WOMAN, 60);

        List<Person> listPersonWorable = new ArrayList<>();
        listManWorkable.addAll(listManWorkable);
        listPersonWorable.addAll(listWomanWorkable);

        System.out.println("Количество трудоспособных людей: " + listManWorkable.size());
    }

    public static Collection<Person>  getRandomPersonsList() {
        Collection<Person> persons = new ArrayList<>();

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        return persons;
    }

    public static List<Person> getListWorkable(Collection<Person> persons, Sex sex, int maxAge) {
        return persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getSex() == sex)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= maxAge)
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
    }

    public static List<String> getListFamilySolder(Collection<Person> persons) {
        List<String> listFamilySolder = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        return listFamilySolder;
    }

    public static long getCountYongPeople(Collection<Person> persons) {
        return persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
    }
}
