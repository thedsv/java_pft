package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest1 {

  @Test
  public void testDistance() {
    Point A = new Point(2, 4);
    Point B = new Point(7, 16);

    Assert.assertEquals(A.distance(B), 13.0);
  }
}
