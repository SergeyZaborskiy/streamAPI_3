import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class speedTest {
    //Process time: 0.550631 s
    public static void calculate(Collection<Person> persons) {
        long startTime = System.nanoTime();

//Stream1
        {
            Stream<Person> stream = persons.stream();
            long count = stream
                    .filter(person -> person.getAge() < 18)
                    .count();
            System.out.println("Количество несовершеннолетних: " + count);
        }
//Stream2
        {
            Stream<Person> stream2 = persons.stream();
            List<String> conscripts;
            conscripts = stream2
                    .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                    .map(Person::getFamily)
                    .collect(Collectors.toList());
            System.out.println("Количество призывников: " + conscripts.size());
        }
//Stream3
        {
            Stream<Person> stream3 = persons.stream();
            List<Person> personList;
            personList = stream3
                    .filter(person -> person.getSex().equals(Sex.MAN) && person.getAge() > 18 && person.getAge() < 65 ||
                            person.getSex().equals(Sex.WOMEN) && person.getAge() > 18 && person.getAge() < 60)
                    .filter(person -> person.getEducation().equals(Education.HIGHER))
                    .sorted(Comparator.comparing(Person::getFamily))
                    .collect(Collectors.toList());
            System.out.println("Количество граждан пригодных к работе: " + personList.size());
        }

        long stopTime = System.nanoTime();
        double processTime = (double) (stopTime - startTime) / 1_000_000_000.0;
        System.out.println("Process time: " + processTime + " s");
    }

    //Process time: 0.4608058 s
    public static void calculateParallel(Collection<Person> persons) {

        long startTime = System.nanoTime();

//Stream1
        {
            Stream<Person> stream = persons.parallelStream();
            long count = stream
                    .filter(person -> person.getAge() < 18)
                    .count();
            System.out.println("Количество несовершеннолетних: " + count);
        }
//Stream2
        {
            Stream<Person> stream2 = persons.parallelStream();
            List<String> conscripts;
            conscripts = stream2
                    .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                    .map(Person::getFamily)
                    .collect(Collectors.toList());
            System.out.println("Количество призывников: " + conscripts.size());
        }
//Stream3
        {
            Stream<Person> stream3 = persons.parallelStream();
            List<Person> personList;
            personList = stream3
                    .filter(person -> person.getSex().equals(Sex.MAN) && person.getAge() > 18 && person.getAge() < 65 ||
                            person.getSex().equals(Sex.WOMEN) && person.getAge() > 18 && person.getAge() < 60)
                    .filter(person -> person.getEducation().equals(Education.HIGHER))
                    .sorted(Comparator.comparing(Person::getFamily))
                    .collect(Collectors.toList());
            System.out.println("Количество граждан пригодных к работе: " + personList.size());
        }

        long stopTime = System.nanoTime();
        double processTime = (double) (stopTime - startTime) / 1_000_000_000.0;
        System.out.println("Process time: " + processTime + " s");
    }
}
