package br.com.felipe.foo.services;

import br.com.felipe.foo.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();

    public Person findById(String id){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Felipe");
        person.setLastName("Fernandes");
        person.setAddress("Rua da Paz - 695");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll(){

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Person person = mockPerson(i);
            persons.add(person);

        }
        return persons;

    }

    public Person create(Person person){

        return person;
    }

    public Person update(Person person){

        return person;
    }

    public void delete(String id){

    }


    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last name " + i );
        person.setAddress("Some brazillian anddress " + i);
        person.setGender("Male");

        return person;
    }
}
