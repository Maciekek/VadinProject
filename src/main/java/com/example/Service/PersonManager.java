package com.example.Service;

import com.example.Domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by Maciek on 2015-03-17.
 */
public class PersonManager {
    public static List<Person> personList = new ArrayList<Person>();

    private int radnomId(){
        Random random = new Random();
        return random.nextInt(100);
    }

    public void addPerson(Person person){
        person.setId(radnomId());
        System.out.println("dodaje persona");
        System.out.println(person.getId());
        System.out.println("");
        personList.add(person);
    }

    public List<Person> getPersons(){
        return personList;
    }

    public static int getUserIdByUserName(String usernameValue) {
        for(Person person : personList){
            if(person.getUserName().equals(usernameValue))
                return person.getId();
        }

        return 0;
    }
}
