package br.com.felipe.foo.services;

import br.com.felipe.foo.models.Person;
import org.springframework.stereotype.Service;

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
}
