package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import structures.ListInterface;
import structures.RecursiveList;

// these are the same as public tests
public class ProjectTests {
  private ListInterface<String> list;

  @Before
  public void setup() {
    list = new RecursiveList<String>();
  }

  @Test(timeout = 500)
  public void testInsertFirstIsEmptySizeAndGetFirst1() {
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
    assertFalse("List should now have elements.", list.isEmpty());
    assertEquals("List should now have 1 element.", 1, list.size());
    assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
    list.insertFirst("world");
    assertEquals(2, list.size());
    list.insertFirst("foo");
    assertEquals(3, list.size());
    assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
  }

}
