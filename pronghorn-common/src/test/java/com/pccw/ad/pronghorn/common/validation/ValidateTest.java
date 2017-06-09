package com.pccw.ad.pronghorn.common.validation;

import com.pccw.ad.pronghorn.common.exception.ValidationException;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by FaustineP on 16/05/2017.
 */
public class ValidateTest {

    @Test
    public void isEmptyOrNull() throws Exception {
        try {
            Validate.isEmptyOrNull(null);
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof ValidationException);
        }
    }


    @Test
    public void testEquality() {
        HashSet<A> collection = new HashSet<>();
        A a1 = new A();
        a1.setId((long) 1);
        a1.setAge(18);
        a1.setName("A");
        collection.add(a1);

        A a2 = new A();
        a2.setId((long) 1);
        a2.setAge(18);
        a2.setName("B");
        collection.add(a2);
        System.out.println(collection);

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(new String("a"));
        hashSet.add(new String("a"));
        System.out.println(hashSet);
    }

    private class A {

        private Long id;
        private String name;
        private Integer age;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "A{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A a = (A) o;

            return id.equals(a.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

}