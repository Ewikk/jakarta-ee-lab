package com.pg.jakartaeelab.serialization.component;


import jakarta.enterprise.context.Dependent;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Utility class for cloning objects. Storing objects in memory instead of database can be dangerous because of
 * reference sharing. WHen some object is returned from the data store is has the same reference as the on in the
 * store. Changing something in the object leads to changing the original (the same) objects in the data store. In
 * order to avoid that deep copy (new reference) of the object can be returned. One of the ways of obtaining deep copy
 * without external libraries is serialization mechanism.
 */
@Log
@Dependent
public class CloningUtility {

    /**
     * Created deep copy of provided object using serialization.
     *
     * @param object object to be cloned
     * @param <T>    type of the object
     * @return deep copy of the object
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T clone(T object) {
        try (ByteArrayInputStream is = new ByteArrayInputStream(writeObject(object).toByteArray()); ObjectInputStream ois = new ObjectInputStream(is)) {
            return (T) ois.readObject();
        }

    }

    /**
     * @param object object to be cloned
     *               * @param <T>    type of the object
     * @return close {@link ByteArrayOutputStream} with serialized object
     * @throws IOException on IO error
     */
    private <T extends Serializable> ByteArrayOutputStream writeObject(T object) throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(object);
            return os;
        }
    }

}
