package com.example.Service;

import com.example.Domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciek on 2015-03-17.
 */
public class PersonManager {
    public static List<Person> personList = new ArrayList<Person>();

    public void addPerson(Person person){
        System.out.println("dodaje persona");

        personList.add(person);
    }

    public List<Person> getPersons(){
        return personList;
    }

}
