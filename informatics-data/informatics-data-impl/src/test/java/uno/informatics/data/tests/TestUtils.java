/*******************************************************************************
 * Copyright 2016 Guy Davenport
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package uno.informatics.data.tests;

import java.util.LinkedList;
import java.util.List;

import uno.informatics.data.Entity;
import uno.informatics.data.pojo.EntityPojo;

public class TestUtils {
    private static final int DEFAULT_MAX_SIZE = 20;

    public static List<String> createRandomStringItems() {
        return createRandomStringItems(randomSize(DEFAULT_MAX_SIZE));
    }

    public static List<String> createRandomStringItems(int count) {
        List<String> items = new LinkedList<String>();

        for (int i = 0; i < count; ++i)
            items.add(createRandomString());

        return items;
    }

    public static List<Object> createRandomIdentificationObjects() {
        return createRandomIdentificationObjects(randomSize(DEFAULT_MAX_SIZE));
    }

    public static List<Object> createRandomIdentificationObjects(int count) {
        List<Object> items = new LinkedList<Object>();

        for (int i = 0; i < count; ++i)
            items.add(createRandomIdentification());

        return items;
    }

    public static List<Entity> createRandomIdentificationItems() {
        return createRandomIdentificationItems(randomSize(DEFAULT_MAX_SIZE));
    }

    public static List<Entity> createRandomIdentificationItems(int count) {
        List<Entity> items = new LinkedList<Entity>();

        for (int i = 0; i < count; ++i)
            items.add(createRandomIdentification());

        return items;
    }

    public static List<Object> createRandomItems() {
        return createRandomItems(randomSize(DEFAULT_MAX_SIZE));
    }

    public static List<Object> createRandomItems(int count) {
        List<Object> items = new LinkedList<Object>();

        for (int i = 0; i < count; ++i) {
            switch (randomIndex(3)) {
                case 0:
                default:
                    items.add(createRandomIdentification());
                    break;
                case 2:
                    items.add(createRandomName());
                    break;
            }
        }

        return items;
    }

    public static String createRandomString() {
        return createRandomName();
    }

    public static Entity createRandomIdentification() {
        return new EntityPojo(null, createRandomName(), createRandomDescription());
    }

    public static String createRandomName() {
        return createRandomText(true, 20);
    }

    public static String createRandomDescription() {
        return createRandomText(false, 50);
    }

    public static String createRandomText(boolean startEachWordWithCapital, int wordCount) {
        StringBuffer buffer = new StringBuffer();

        buffer.append(createRandomWord(true));

        int size = randomSize(wordCount);

        for (int i = 1; i < size; ++i) {
            buffer.append(' ');
            buffer.append(createRandomWord(startEachWordWithCapital));
        }

        return buffer.toString();
    }

    public static Object createRandomWord(boolean startWithCapital) {
        StringBuffer buffer = new StringBuffer();

        buffer.append(createRandomLetter(startWithCapital));

        int size = randomSize(8);

        for (int i = 1; i < size; ++i) {
            buffer.append(createRandomLetter(false));
        }

        return buffer.toString();
    }

    public static char createRandomLetter(boolean capital) {
        if (capital)
            return (char) ((int) 'A' + randomIndex(26));
        else
            return (char) ((int) 'a' + randomIndex(26));
    }

    public static int randomIndex(int maxValue) {
        return (int) (Math.random() * (double) maxValue);
    }

    public static int randomSize(int maxValue) {
        return randomIndex(maxValue) + 1;
    }
}
