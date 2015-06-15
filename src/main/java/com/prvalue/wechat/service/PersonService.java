package com.prvalue.wechat.service;

import com.prvalue.wechat.model.Person;
import java.util.List;

/**
 *
 * @author Heisaman
 */
public interface PersonService {
    public void addPerson(Person p);
    public void updatePerson(Person p);
    public List<Person> listPersons();
    public Person getPersonById(int id);
    public void removePerson(int id);
}
