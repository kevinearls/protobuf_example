package dev.kearls.protobuf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {
    AddressBookProtos.Person person = AddressBookProtos.Person.getDefaultInstance();
    String email = "j@baeldung.com";
    int id = new Random().nextInt();
    String name = "Michael Program";
    String number = "01234567890";

    @BeforeEach
    protected void setUp() throws Exception {
        person = AddressBookProtos.Person.newBuilder()
                .setId(id)
                .setName(name)
                .setEmail(email)
                .addNumbers(number)
                .build();
    }

    @Test
    public void simpleTest() {
        assertEquals(person.getEmail(), email);
        assertEquals(person.getId(), id);
        assertEquals(person.getName(), name);
        assertEquals(person.getNumbers(0), number);
    }

    @Test
    public void testWriteAndRead() throws Exception {
        String testFileName = "src/test/resources/fred";
        var addressBook = AddressBookProtos.AddressBook.newBuilder().addPeople(person).build();
        var fos = new FileOutputStream(testFileName);
        addressBook.writeTo(fos);

        var deserialized = AddressBookProtos.AddressBook.newBuilder()
                .mergeFrom(new FileInputStream(testFileName))
                .build();

        assertEquals(deserialized.getPeople(0).getEmail(), email);
        assertEquals(deserialized.getPeople(0).getId(), id);
        assertEquals(deserialized.getPeople(0).getName(), name);
        assertEquals(deserialized.getPeople(0).getNumbers(0), number);
    }
}
