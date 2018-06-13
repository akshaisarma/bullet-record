/*
 *  Copyright 2016, Yahoo Inc.
 *  Licensed under the terms of the Apache License, Version 2.0.
 *  See the LICENSE file associated with the project for terms.
 */
package com.yahoo.bullet.record;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Data sent to Bullet should be wrapped in a class that extends this abstract class. It is
 * {@link Serializable} and {@link Iterable} so records can be used in for-each loops. The various
 * set methods can be used to insert fields into the record. They are implemented by default to use
 * the base {@link #set(String, Object)} method which should be overridden in child classes. Field
 * names are to be provided as Strings and must be unique, otherwise the duplicate takes precedence
 * over the first by default.
 *
 * When inserting into the Record, methods are explicitly provided for each type supported (listed
 * below). When reading from the Record, which may not be something needed by users of this very
 * frequently (as it is the entry point into Bullet), a generic Object is returned instead. Casting
 * it is left to the user.
 *
 * The record currently supports these fields:
 * <pre>
 * Primitives: Boolean, Integer, Long, Float, Double, String
 * Complex: {@code Map <String, "Primitives">, Map<String, Map<String, "Primitives">>, List<Map<String, "Primitives">},
 *           where "Primitives" refers to the afore-mentioned Primitives.
 * </pre>
 *
 * Instead of setting a field to null (you cannot for top level Java primitives), avoid setting it instead.
 */
public abstract class BulletRecord implements Iterable<Pair<String, Object>>, Serializable {
    private static final long serialVersionUID = 3319286957467020672L;

    /**
     * Insert a field into this BulletRecord. This is the base method used by the other set methods
     * and should remain protected in child classes to ensure type safety.
     *
     * @param field The non-null name of the field
     * @return This object for chaining.
     */
    protected abstract BulletRecord set(String field, Object object);

    /**
     * Gets a field stored in the record.
     *
     * @param field The non-null name of the field
     * @return The value of the field or null if it does not exist.
     */
    public abstract Object get(String field);

    /**
     * Returns true iff the given top-level field exists in the record.
     *
     * @param field The field to check if it exists.
     * @return A boolean denoting whether there was a mapping for the field.
     */
    public abstract boolean hasField(String field);

    /**
     * Returns the number of fields in the record.
     *
     * @return An int representing the number of fields stored.
     */
    public abstract int fieldCount();

    /**
     * Removes and returns a top-level field from the record.
     *
     * @param field The field to remove from the record.
     * @return The removed object or null.
     */
    public abstract Object getAndRemove(String field);

    /**
     * Removes a top-level field from the record.
     *
     * @param field The field to remove from the record.
     * @return This object for chaining.
     */
    public abstract BulletRecord remove(String field);

    // ******************************************** GETTERS ********************************************

    /**
     * Gets an object from a {@link Map} stored in the record.
     *
     * @param field The field name in the record that is a {@link Map}.
     * @param subKey The subfield in the {@link Map} that is desired.
     * @return The value of the subfield in the {@link Map} or null if the field does not exist.
     * @throws ClassCastException if the field is not a {@link Map}.
     */
    public Object get(String field, String subKey) {
        Object value = get(field);
        if (value == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> casted = (Map<String, Object>) value;
        return casted.get(subKey);
    }

    /**
     * Gets an object from a {@link List} stored in the record.
     *
     * @param field The field name in the record that is a {@link List}.
     * @param index The position in the {@link List} that is desired.
     * @return The object at the index or null if the field does not exist.
     * @throws IndexOutOfBoundsException for invalid indices.
     * @throws ClassCastException if the field is not a {@link List}.
     */
    public Object get(String field, int index) {
        Object value = get(field);
        if (value == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Object> casted = (List<Object>) value;
        return casted.get(index);
    }

    // ******************************************** SETTERS ********************************************

    /**
     * Insert a Boolean field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setBoolean(String field, Boolean object) {
        return set(field, object);
    }

    /**
     * Insert a String field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setString(String field, String object) {
        return set(field, object);
    }

    /**
     * Insert an Integer field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setInteger(String field, Integer object) {
        return set(field, object);
    }

    /**
     * Insert a Long field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setLong(String field, Long object) {
        return set(field, object);
    }

    /**
     * Insert a Float field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setFloat(String field, Float object) {
        return set(field, object);
    }

    /**
     * Insert a Double field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setDouble(String field, Double object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to Boolean field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setBooleanMap(String field, Map<String, Boolean> object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to String field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setStringMap(String field, Map<String, String> object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to Integer field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setIntegerMap(String field, Map<String, Integer> object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to Long field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setLongMap(String field, Map<String, Long> object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to Float field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setFloatMap(String field, Map<String, Float> object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to Double field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setDoubleMap(String field, Map<String, Double> object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to Map of String to Boolean field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setMapOfBooleanMap(String field, Map<String, Map<String, Boolean>> object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to Map of String to String field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setMapOfStringMap(String field, Map<String, Map<String, String>> object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to Map of String to Integer field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setMapOfIntegerMap(String field, Map<String, Map<String, Integer>> object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to Map of String to Long field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setMapOfLongMap(String field, Map<String, Map<String, Long>> object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to Map of String to Float field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setMapOfFloatMap(String field, Map<String, Map<String, Float>> object) {
        return set(field, object);
    }

    /**
     * Insert a Map of String to Map of String to Double field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setMapOfDoubleMap(String field, Map<String, Map<String, Double>> object) {
        return set(field, object);
    }

    /**
     * Insert a List of Map of String to Boolean field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setListOfBooleanMap(String field, List<Map<String, Boolean>> object) {
        return set(field, object);
    }

    /**
     * Insert a List of Map of String to String field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setListOfStringMap(String field, List<Map<String, String>> object) {
        return set(field, object);
    }

    /**
     * Insert a List of Map of String to Integer field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setListOfIntegerMap(String field, List<Map<String, Integer>> object) {
        return set(field, object);
    }

    /**
     * Insert a List of Map of String to Long field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setListOfLongMap(String field, List<Map<String, Long>> object) {
        return set(field, object);
    }

    /**
     * Insert a List of Map of String to Float field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setListOfFloatMap(String field, List<Map<String, Float>> object) {
        return set(field, object);
    }

    /**
     * Insert a List of Map of String to Double field.
     *
     * @param field The non-null name of the field.
     * @param object The value to insert.
     * @return This object for chaining.
     */
    public BulletRecord setListOfDoubleMap(String field, List<Map<String, Double>> object) {
        return set(field, object);
    }

    /**
     * Insert into this record a field from another record.
     *
     * @param field The name in this record to insert the field as.
     * @param that The non-null record to extract the field from.
     * @param thatField The name of the field in that record to get the field from.
     * @return This object for chaining.
     */
    public BulletRecord set(String field, BulletRecord that, String thatField) {
        return set(field, that.get(thatField));
    }

    /**
     * Insert into this record a Map sub-field from another record.
     *
     * @param field The name in this record to insert the field as.
     * @param that The non-null record to extract the field from.
     * @param thatMapField The name of the Map field in that record to get the key from.
     * @param thatMapKey The name of the key in the Map field in that record.
     * @return This object for chaining.
     */
    public BulletRecord set(String field, BulletRecord that, String thatMapField, String thatMapKey) {
        return set(field, that.get(thatMapField, thatMapKey));
    }

    /**
     * Insert into this record a List index from another record. The item is not copied.
     *
     * @param field The name in this record to insert the field as.
     * @param that The non-null record to extract the field from.
     * @param thatListField The name of the List field in that record to get the index of.
     * @param thatListIndex The index of the field in the List to get.
     * @return This object for chaining.
     */
    public BulletRecord set(String field, BulletRecord that, String thatListField, int thatListIndex) {
        return set(field, that.get(thatListField, thatListIndex));
    }

    /**
     * Renames a top-level field in the record.
     *
     * @param field The non-null original field name.
     * @param newName The non-null new name.
     * @return This object for chaining.
     */
    public BulletRecord rename(String field, String newName) {
        if (hasField(field)) {
            set(newName, getAndRemove(field));
        }
        return this;
    }
}
